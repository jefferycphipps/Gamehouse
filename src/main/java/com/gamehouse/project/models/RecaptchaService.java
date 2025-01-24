package com.gamehouse.project.models;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService{

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verifyRecaptcha(String recaptchaToken, String secretKey) {
        String url = RECAPTCHA_VERIFY_URL + "?secret=" + secretKey + "&response=" + recaptchaToken;

        RestTemplate restTemplate = new RestTemplate();
        RecaptchaResponse response = restTemplate.postForObject(url, null, RecaptchaResponse.class);

        return response != null && response.isSuccess() && response.getScore() >= 0.5;
    }
}
