package com.epam.khrypushyna.shop.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.khrypushyna.shop.Constants.ATTRIBUTE_ENCRYPT_CAPTCHA;

public class FieldCaptchaService extends CaptchaService {

    @Override
    public String getEncryptCaptchaValue(HttpServletRequest request) {
        return request.getParameter(ATTRIBUTE_ENCRYPT_CAPTCHA);
    }

    @Override
    protected void saveEncryptCaptchaValue(String encryptCaptcha, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ATTRIBUTE_ENCRYPT_CAPTCHA, encryptCaptcha);
    }
}
