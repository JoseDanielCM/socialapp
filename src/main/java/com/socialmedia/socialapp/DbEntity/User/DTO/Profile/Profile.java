package com.socialmedia.socialapp.DbEntity.User.DTO.Profile;

public class Profile {
    private Long userId;
    private String name;
    private String lastname;
    private String bio;
    private String profilePhotoUrl;
    private Long phoneNumber;

    public Profile() {
    }

    public Profile(Long userId, String name, String lastname, String bio, String profilePhotoUrl, Long phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.bio = bio;
        this.profilePhotoUrl = profilePhotoUrl;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
