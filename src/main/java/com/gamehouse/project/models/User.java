package com.gamehouse.project.models;

public class User extends AbstractEntity{

    private String pwHash;

    private String email;

    private Image profileImage;

    public User(){}


    public User(String name, String pwHash, String email, Image profileImage) {
        super();
        this.setName(name);
        this.pwHash = pwHash;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }
}
