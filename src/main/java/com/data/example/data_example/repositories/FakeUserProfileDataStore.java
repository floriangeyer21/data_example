package com.data.example.data_example.repositories;

import com.data.example.data_example.model.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("01522f94-bf7b-4ee9-9282-e8770a8fffb3"), "janet", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("2e449356-c3f1-4418-b031-f096dd971092"), "antonio", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
