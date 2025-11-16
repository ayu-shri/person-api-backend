package com.example.personapi.controller;

import com.example.personapi.model.focusgroup.*;
import com.example.personapi.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/focus-group")
@CrossOrigin(origins = "*")
public class FocusGroupController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/generate-profiles")
    public ResponseEntity<?> generateProfiles(@RequestBody GenerateProfilesRequest request) {
        try {
            List<Profile> profiles = geminiService.generateProfiles(
                request.getGroupDescription(), 
                request.getNumProfiles()
            );
            return ResponseEntity.ok(profiles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Failed to generate profiles: " + e.getMessage())
            );
        }
    }

    @PostMapping("/run-simulation")
    public ResponseEntity<?> runSimulation(@RequestBody RunSimulationRequest request) {
        try {
            SimulationResult result = geminiService.runSimulation(
                request.getProfiles(), 
                request.getSimulationPrompt()
            );
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Failed to run simulation: " + e.getMessage())
            );
        }
    }
}