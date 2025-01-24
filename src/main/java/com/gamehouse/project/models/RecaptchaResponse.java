package com.gamehouse.project.models;

public class RecaptchaResponse {
    private boolean success;
    private double score;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
