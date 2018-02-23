package com.epam.khrypushyna.shop.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class CaptchaService {
    private Map<String, CaptchaValue> captchaValuesMap;
    private Random random = new Random();
    private AtomicLong captchaIdentifier = new AtomicLong();

    public CaptchaService() {
        this.captchaValuesMap = new ConcurrentHashMap<>();
    }

    Map<String, CaptchaValue> getCaptchaValuesMap() {
        return captchaValuesMap;
    }

	public String generateCaptchaValue(int seed) {
		char[] number = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder captchaValue = new StringBuilder();
		for (int i = 0; i < seed; i++) {
			captchaValue.append(number[random.nextInt(number.length)]);
		}
		return captchaValue.toString();
	}

    public String identifyCaptcha() {
        return String.valueOf(captchaIdentifier.getAndIncrement());
    }

	public void save(String captchaValue, String encryptCaptcha, HttpServletRequest request, HttpServletResponse response) {
		captchaValuesMap.put(encryptCaptcha, new CaptchaValue(captchaValue));
		saveEncryptCaptchaValue(encryptCaptcha, request, response);
	}

	public String getCaptchaValue(String encryptCaptcha) {
		CaptchaValue captcha = captchaValuesMap.get(encryptCaptcha);
		return captcha == null ? null : captcha.getValue();
	}

    public abstract String getEncryptCaptchaValue(HttpServletRequest request);

    protected abstract void saveEncryptCaptchaValue(String encryptCaptcha, HttpServletRequest request, HttpServletResponse response);

}
