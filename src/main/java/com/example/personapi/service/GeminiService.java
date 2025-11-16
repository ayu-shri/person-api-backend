package com.example.personapi.service;

import com.example.personapi.model.focusgroup.Profile;
import com.example.personapi.model.focusgroup.SimulationResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public GeminiService() {
        this.webClient = WebClient.builder().build();
        this.objectMapper = new ObjectMapper();
    }

    public List<Profile> generateProfiles(String groupDescription, int numProfiles) {
        try {
            String prompt = String.format(
                "Generate a focus group of exactly %d distinct individuals matching the description: \"%s\". " +
                "For each person, provide the following details.", 
                numProfiles, groupDescription
            );

            Map<String, Object> profileSchema = Map.of(
                "type", "OBJECT",
                "properties", Map.of(
                    "name", Map.of("type", "STRING", "description", "A realistic name."),
                    "age", Map.of("type", "NUMBER", "description", "The person's age."),
                    "occupation", Map.of("type", "STRING", "description", "Their professional role."),
                    "trait", Map.of("type", "STRING", "description", "A unique personality trait or background detail."),
                    "techLiteracy", Map.of("type", "STRING", "description", "Rating (Low, Moderate, High)."),
                    "financialStatus", Map.of("type", "STRING", "description", "Rating (Budget-Conscious, Middle-Income, Affluent).")
                ),
                "required", List.of("name", "age", "occupation", "trait", "techLiteracy", "financialStatus")
            );

            Map<String, Object> payload = Map.of(
                "contents", List.of(Map.of("parts", List.of(Map.of("text", prompt)))),
                "generationConfig", Map.of(
                    "responseMimeType", "application/json",
                    "responseSchema", Map.of(
                        "type", "ARRAY",
                        "items", profileSchema
                    )
                )
            );

            String response = webClient.post()
                .uri(apiUrl + "?key=" + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            JsonNode root = objectMapper.readTree(response);
            String jsonText = root.path("candidates").get(0)
                .path("content").path("parts").get(0)
                .path("text").asText();

            Profile[] profiles = objectMapper.readValue(jsonText, Profile[].class);
            
            for (int i = 0; i < profiles.length; i++) {
                profiles[i].setIndex(i + 1);
            }

            return List.of(profiles);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate profiles: " + e.getMessage(), e);
        }
    }

    public SimulationResult runSimulation(List<Profile> profiles, String simulationPrompt) {
        try {
            StringBuilder profileList = new StringBuilder();
            for (Profile p : profiles) {
                profileList.append(String.format(
                    "[P%d] Name: %s, Age: %d, Occupation: %s, Trait: %s, Tech: %s, Finance: %s\n",
                    p.getIndex(), p.getName(), p.getAge(), p.getOccupation(), 
                    p.getTrait(), p.getTechLiteracy(), p.getFinancialStatus()
                ));
            }

            String systemPrompt = String.format(
                "You are a professional market research analyst. Your task is to analyze the following " +
                "focus group profiles and predict their collective reaction to the user's scenario. " +
                "You must output the response in the requested JSON format, ensuring 'profileReactions' " +
                "array has an entry for every profile listed below. The 'overallSentimentScore' must " +
                "range from -5 (Highly Negative) to +5 (Highly Positive).\n\nFOCUS GROUP PROFILES:\n%s",
                profileList.toString()
            );

            String userQuery = "SIMULATION SCENARIO: " + simulationPrompt;

            Map<String, Object> simulationSchema = Map.of(
                "type", "OBJECT",
                "properties", Map.of(
                    "overallSentimentScore", Map.of(
                        "type", "NUMBER",
                        "description", "A score from -5 (Highly Negative) to +5 (Highly Positive)"
                    ),
                    "executiveSummary", Map.of(
                        "type", "STRING",
                        "description", "A concise paragraph summarizing overall sentiment"
                    ),
                    "keyFindings", Map.of(
                        "type", "ARRAY",
                        "items", Map.of("type", "STRING"),
                        "description", "3 to 4 bullet points of critical factors"
                    ),
                    "profileReactions", Map.of(
                        "type", "ARRAY",
                        "items", Map.of(
                            "type", "OBJECT",
                            "properties", Map.of(
                                "profileIndex", Map.of("type", "NUMBER"),
                                "name", Map.of("type", "STRING"),
                                "sentiment", Map.of("type", "STRING", "enum", List.of("Positive", "Neutral", "Negative")),
                                "reason", Map.of("type", "STRING")
                            ),
                            "required", List.of("profileIndex", "name", "sentiment", "reason")
                        )
                    )
                ),
                "required", List.of("overallSentimentScore", "executiveSummary", "keyFindings", "profileReactions")
            );

            Map<String, Object> payload = Map.of(
                "contents", List.of(Map.of("parts", List.of(Map.of("text", userQuery)))),
                "systemInstruction", Map.of("parts", List.of(Map.of("text", systemPrompt))),
                "generationConfig", Map.of(
                    "responseMimeType", "application/json",
                    "responseSchema", simulationSchema
                )
            );

            String response = webClient.post()
                .uri(apiUrl + "?key=" + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            JsonNode root = objectMapper.readTree(response);
            String jsonText = root.path("candidates").get(0)
                .path("content").path("parts").get(0)
                .path("text").asText();

            return objectMapper.readValue(jsonText, SimulationResult.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to run simulation: " + e.getMessage(), e);
        }
    }
}