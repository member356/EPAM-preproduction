package com.epam.khrypushyna.shop.listener;

import com.epam.khrypushyna.shop.Validator;
import com.epam.khrypushyna.shop.captcha.CaptchaCleaner;
import com.epam.khrypushyna.shop.captcha.CaptchaService;
import com.epam.khrypushyna.shop.captcha.CookieCaptchaService;
import com.epam.khrypushyna.shop.captcha.FieldCaptchaService;
import com.epam.khrypushyna.shop.captcha.SessionCaptchaService;
import com.epam.khrypushyna.shop.dao.UserDAO;
import com.epam.khrypushyna.shop.dao.UserDAOImpl;
import com.epam.khrypushyna.shop.db.JDBCTransactionManager;
import com.epam.khrypushyna.shop.db.TransactionManager;
import com.epam.khrypushyna.shop.img.AvatarImageStorage;
import com.epam.khrypushyna.shop.service.UserService;
import com.epam.khrypushyna.shop.service.UserServiceImpl;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA;
import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA_VALIDATOR;
import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_USERS;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        setLogger(context);
        setUserServiceAttribute(context);
        setCaptchaAttribute(context);
        setImageStorage(context);
    }

    private void setImageStorage(ServletContext context) {
        context.setAttribute("imageStorage", new AvatarImageStorage());
    }

    private void setUserServiceAttribute(ServletContext context) {
        Validator validator = new Validator();
        context.setAttribute(SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA_VALIDATOR, validator);

        UserDAO userDAO = new UserDAOImpl();
        TransactionManager transactionManager = new JDBCTransactionManager();
        UserService userService = new UserServiceImpl(userDAO, transactionManager, validator);
        context.setAttribute(SERVLET_CONTEXT_ATTRIBUTE_USERS, userService);
    }

    private void setCaptchaAttribute(ServletContext context) {
        final Map<String, Supplier<CaptchaService>> captchaServices = new HashMap<>();
        captchaServices.put("session", SessionCaptchaService::new);
        captchaServices.put("cookie", CookieCaptchaService::new);
        captchaServices.put("field", FieldCaptchaService::new);

        String captchaType = context.getInitParameter("captchaType");
        context.setAttribute("captchaType", captchaType);
        CaptchaService captcha = captchaServices.get(captchaType).get();
        context.setAttribute(SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA, captcha);
        new Thread(new CaptchaCleaner(Long.parseLong(context.getInitParameter("updateCaptchaTime")), captcha)).start();
    }

    private void setLogger(ServletContext context) {
        String fullPath = context.getRealPath("") + File.separator + context.getInitParameter("log4j-config");
        PropertyConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
