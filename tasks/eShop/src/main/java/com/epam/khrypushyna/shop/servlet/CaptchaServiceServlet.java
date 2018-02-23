package com.epam.khrypushyna.shop.servlet;

import com.epam.khrypushyna.shop.captcha.CaptchaService;
import com.github.cage.GCage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA;

@WebServlet("/captcha")
public class CaptchaServiceServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CaptchaServiceServlet.class);
    private CaptchaService captcha;

    @Override
    public void init() throws ServletException {
        captcha = (CaptchaService) getServletContext().getAttribute(SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("image/jpg");
        try (OutputStream outputStream = resp.getOutputStream()) {
            String encryptCaptcha = captcha.getEncryptCaptchaValue(req);
            String captchaValue = captcha.getCaptchaValue(encryptCaptcha);
            new GCage().draw(captchaValue, outputStream);
        } catch (IOException e) {
            LOG.error("Exception when using stream while captcha getting." + e.getMessage());
        }
    }
}
