package com.senseskill.service;

import com.senseskill.model.User;
import com.senseskill.model.UserProfile;
import com.senseskill.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository profileRepo;

    public Optional<UserProfile> getByUser(User user) {
        return profileRepo.findByUser(user);
    }

    public UserProfile saveOrUpdate(UserProfile profile) {
        return profileRepo.save(profile);
    }
}
