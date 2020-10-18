package com.data.example.data_example.repositories;

import com.data.example.data_example.model.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "janet", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "antonio", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
