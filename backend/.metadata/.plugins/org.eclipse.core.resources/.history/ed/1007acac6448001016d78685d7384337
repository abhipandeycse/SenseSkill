package com.senseskill.controller;

import com.senseskill.model.User;
import com.senseskill.model.UserProfile;
import com.senseskill.repository.UserRepository;
import com.senseskill.service.UserProfileService;
import com.senseskill.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<?> saveProfile(@RequestBody UserProfile profile, HttpServletRequest request) {
        String email = jwtUtil.extractUsername(request.getHeader("Authorization").substring(7));
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Unauthorized");

        profile.setUser(userOpt.get());
        return ResponseEntity.ok(profileService.saveOrUpdate(profile));
    }

    @GetMapping
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String email = jwtUtil.extractUsername(request.getHeader("Authorization").substring(7));
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Unauthorized");

        return ResponseEntity.ok(profileService.getByUser(userOpt.get()));
    }
}
