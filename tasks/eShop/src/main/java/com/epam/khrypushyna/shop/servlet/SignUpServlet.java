package com.epam.khrypushyna.shop.servlet;

import com.epam.khrypushyna.shop.Validator;
import com.epam.khrypushyna.shop.captcha.CaptchaService;
import com.epam.khrypushyna.shop.captcha.CaptchaValue;
import com.epam.khrypushyna.shop.entity.User;
import com.epam.khrypushyna.shop.img.ImageStorage;
import com.epam.khrypushyna.shop.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.epam.khrypushyna.shop.Constants.PATH_MAIN_PAGE;
import static com.epam.khrypushyna.shop.Constants.PATH_SIGNUP_PAGE;
import static com.epam.khrypushyna.shop.Constants.PATH_SIGNUP_SERVLET;
import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA;
import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA_VALIDATOR;
import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_USERS;

@MultipartConfig
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SignUpServlet.class);
    private UserService userService;
    private CaptchaService captchaService;
    private Validator validator;
    private Map<String, Object> messageMap;
    private ImageStorage imageStorage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(SERVLET_CONTEXT_ATTRIBUTE_USERS);
        captchaService = (CaptchaService) config.getServletContext().getAttribute(SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA);
        validator = (Validator) config.getServletContext().getAttribute(SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA_VALIDATOR);
        messageMap = validator.getMessagesMap();
        imageStorage = (ImageStorage) config.getServletContext().getAttribute("imageStorage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Map.Entry<String, Object> pair : messageMap.entrySet()) {
            req.setAttribute(pair.getKey(), pair.getValue());
        }
        String captchaValue = captchaService.generateCaptchaValue(3);
        String encryptCaptcha = captchaService.identifyCaptcha();
        captchaService.save(captchaValue, encryptCaptcha, req, resp);
        req.getRequestDispatcher(PATH_SIGNUP_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        messageMap.clear();
        resp.setContentType("text/html");
        User newUser = new User(req.getParameter("login"), req.getParameter("password"),
                req.getParameter("email"), req.getParameter("firstname"), req.getParameter("lastname"));
        newUser.setSubscription(req.getParameterValues("subscription"));
        String captchaExpected = captchaService.getCaptchaValue(captchaService.getEncryptCaptchaValue(req));
        String captchaActual = req.getParameter("captchaValue");
        if (!userService.checkLogin(newUser) || !validator.validateSignUpForm(newUser, captchaExpected, captchaActual)) {
            messageMap.put("loginvalue", newUser.getLogin());
            messageMap.put("passwordvalue", newUser.getPassword());
            messageMap.put("emailvalue", newUser.getEmail());
            messageMap.put("firstnamevalue", newUser.getFirstName());
            messageMap.put("lastnamevalue", newUser.getLastName());
            resp.sendRedirect(PATH_SIGNUP_SERVLET);
            return;
        }
        userService.addUser(newUser);
        LOG.info("New user " + newUser + " was added");
        imageStorage.saveUserAvatarFile(req.getPart("file"), newUser.getLogin());
        resp.sendRedirect(PATH_MAIN_PAGE);
    }

}
