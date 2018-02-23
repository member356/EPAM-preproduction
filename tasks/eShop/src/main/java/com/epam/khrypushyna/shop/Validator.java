package com.epam.khrypushyna.shop;

import com.epam.khrypushyna.shop.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Validator {

    private static final String REGEX_LOGIN = "^[a-z0-9]{4,15}$";
    private static final String REGEX_PASSWORD = "^[a-zA-Zа-яА-Я0-9]{5,15}$";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,5}$";
    private static final String REGEX_NAME = "^[a-zA-Z0-9]{4,15}$";

    private Map<String, Object> messages = new ConcurrentHashMap<>();
    private Map<String, Boolean> signUpFormValues = new HashMap<>();

    public Map<String, Object> getMessagesMap() {
        return messages;
    }

    public boolean validateSignUpForm(User user, String captchaExpected, String captchaActual) {
        return validateFormInputs(user) && validateCaptcha(captchaExpected, captchaActual);
    }

    private boolean validateFormInputs(User user){
        signUpFormValues.put("login", user.getLogin().matches(REGEX_LOGIN));
        signUpFormValues.put("password", user.getPassword().matches(REGEX_PASSWORD));
        signUpFormValues.put("email", user.getEmail().matches(REGEX_EMAIL));
        signUpFormValues.put("firstname", user.getFirstName().matches(REGEX_NAME));
        signUpFormValues.put("lastname", user.getLastName().matches(REGEX_NAME));
        boolean valid = true;
        for (Map.Entry<String, Boolean> pair : signUpFormValues.entrySet()) {
            if (!pair.getValue()) {
                valid = false;
                messages.put(pair.getKey() + "error", "Field " + pair.getKey() + " is incorrect");
            }
        }
        return valid;
    }

    private boolean validateCaptcha(String captchaExpected, String captchaActual) {
        if (captchaExpected != null && Objects.equals(captchaExpected, captchaActual)) {
            return true;
        }
        messages.put("captchaerror", "Captcha is invalid");
        return false;
    }
}
