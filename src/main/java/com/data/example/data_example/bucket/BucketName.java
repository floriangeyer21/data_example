package com.data.example.data_example.bucket;

public enum BucketName {

    PROFILE_IMAGE("aws-images-test");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
