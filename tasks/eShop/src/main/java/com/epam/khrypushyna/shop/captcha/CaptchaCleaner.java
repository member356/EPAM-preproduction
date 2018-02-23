package com.epam.khrypushyna.shop.captcha;

import org.apache.log4j.Logger;

import java.util.Map;

public class CaptchaCleaner implements Runnable {

    private static final Logger LOG = Logger.getLogger(CaptchaCleaner.class);
    private final long timeExpired;
    private Map<String, CaptchaValue> captchaValuesMap;

    public CaptchaCleaner(long timeExpired, CaptchaService captchaService) {
        this.timeExpired = timeExpired;
        this.captchaValuesMap = captchaService.getCaptchaValuesMap();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(timeExpired);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.error("Exception when interrupting cleaning captcha thread while sleeping. " + e.getMessage());
            }
            for (Map.Entry<String, CaptchaValue> entry : captchaValuesMap.entrySet()) {
                long timeAlive = System.currentTimeMillis() - entry.getValue().getTime();
                if (timeAlive > timeExpired) {
                    captchaValuesMap.remove(entry.getKey());
                }
            }
        }
    }
}
