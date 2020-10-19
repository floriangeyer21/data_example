package com.data.example.data_example.service;

import com.data.example.data_example.bucket.BucketName;
import com.data.example.data_example.exceptions.FileProcessingException;
import com.data.example.data_example.model.UserProfile;
import com.data.example.data_example.repositories.UserProfileRepository;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;
    private final FileStoreService fileStoreService;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository,
                              UserService userService,
                              FileStoreService fileStoreService) {
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
        this.fileStoreService = fileStoreService;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfileRepository.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileProcessingException("Cannot upload, file is empty. ["
                    + file.getSize() +"]");
        }
        if (!Arrays.asList(IMAGE_JPEG,
                IMAGE_PNG, IMAGE_GIF).contains(file.getContentType())) {
            throw new FileProcessingException("File must be an image.");
        }
       /* try {
            userService.getById(1L);
        } catch (Exception) {
            throw new FileProcessingException(
                    "Does not exist user with id " + userProfileId + ". Can't insert file.");
        }*/
        UserProfile user = userProfileRepository.getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new FileProcessingException(
                                String.format("User profile %s not found", userProfileId)));
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId());

        String fileName = String.format("%s-%s", file.getName(), UUID.randomUUID());

        try {
            fileStoreService.save(path, fileName, Optional.of(metaData), file.getInputStream());
        } catch (IOException e) {
            throw new FileProcessingException("%s", e);
        }
    }
}
