package com.epam.khrypushyna.shop.captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA;

public class CookieCaptchaService extends CaptchaService {

    @Override
    public String getEncryptCaptchaValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Override
    protected void saveEncryptCaptchaValue(String encryptCaptcha, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("captcha", encryptCaptcha);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
}
