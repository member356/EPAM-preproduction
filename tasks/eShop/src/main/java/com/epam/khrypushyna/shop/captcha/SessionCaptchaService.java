package com.epam.khrypushyna.shop.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.khrypushyna.shop.Constants.ATTRIBUTE_ENCRYPT_CAPTCHA;

public class SessionCaptchaService extends CaptchaService {

    @Override
    public String getEncryptCaptchaValue(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(ATTRIBUTE_ENCRYPT_CAPTCHA);
    }

    @Override
    protected void saveEncryptCaptchaValue(String encryptCaptcha, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(ATTRIBUTE_ENCRYPT_CAPTCHA, encryptCaptcha);
    }
}
