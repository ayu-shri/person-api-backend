package com.example.personapi.controller;

import com.example.personapi.model.Person;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PersonController {

    @GetMapping("/person")
    public Person getPerson() {
        return new Person(
            "John Doe",
            "john.doe@example.com",
            "+1 (555) 123-4567",
            "San Francisco, CA",
            "Software Engineer",
            "January 15, 1990",
            "Passionate developer with 8 years of experience in full-stack development. Love building scalable applications and learning new technologies."
        );
    }
}