package com.epam.khrypushyna.shop.servlet;

import com.epam.khrypushyna.shop.Validator;
import com.epam.khrypushyna.shop.captcha.CaptchaService;
import com.epam.khrypushyna.shop.captcha.CaptchaValue;
import com.epam.khrypushyna.shop.entity.User;
import com.epam.khrypushyna.shop.listener.ContextListener;
import com.epam.khrypushyna.shop.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignUpServletTest {

    private ServletConfig config = mock(ServletConfig.class);
    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpServletResponse resp = mock(HttpServletResponse.class);
    private CaptchaService captchaServiceMock = mock(CaptchaService.class);
    private ServletContextEvent eventMock = mock(ServletContextEvent.class);
    private ServletContext contextMock = mock(ServletContext.class);
    private RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
    private UserServiceImpl userServiceMock = mock(UserServiceImpl.class);
    private Validator validatorMock = mock(Validator.class);
    private User user = new User("user6", "user6", "user6@user.com", "Mihail", "Mihaylov");
    private User newUserNotValidData = new User("us7", "us7", "us7@us", "S", "S");
    private User newUserValidData = new User("user7", "user7", "user7@user.com", "Slava", "Slavov");

    private List<User> users = new ArrayList<>();
    private HttpSession sessionMock = mock(HttpSession.class);
    private SignUpServlet signUpServlet;

    private Map<String, CaptchaValue> captchaValuesMap = new ConcurrentHashMap<>();
    private Map<String, Object> messages = new HashMap<>();

    @Before
    public void before() throws ServletException {
        captchaValuesMap.put("555", new CaptchaValue("157"));
        users.add(user);
        when(config.getServletContext()).thenReturn(contextMock);
        when(req.getServletContext()).thenReturn(contextMock);
        when(eventMock.getServletContext()).thenReturn(contextMock);
        when(contextMock.getInitParameter("captchaType")).thenReturn("session");
        when(contextMock.getInitParameter("updateCaptchaTime")).thenReturn("20000");
        new ContextListener().contextInitialized(eventMock);
        when(contextMock.getAttribute("users")).thenReturn(userServiceMock);
        when(contextMock.getAttribute("captchaValidator")).thenReturn(validatorMock);
        when(validatorMock.getMessagesMap()).thenReturn(messages);

        when(userServiceMock.getAllUsers()).thenReturn(users);
        when(userServiceMock.checkLogin(user)).thenReturn(true);
        when(userServiceMock.checkLogin(newUserNotValidData)).thenReturn(true);
        when(userServiceMock.checkLogin(newUserValidData)).thenReturn(true);
        when(req.getSession()).thenReturn(sessionMock);
        when(contextMock.getAttribute("captcha")).thenReturn(captchaServiceMock);
//        when(captchaServiceMock.getCaptchaValuesMap()).thenReturn(captchaValuesMap);
        when(captchaServiceMock.generateCaptchaValue(3)).thenReturn("157");
//        when(captchaServiceMock.identifyCaptcha("157")).thenReturn("555");
        when(captchaServiceMock.getEncryptCaptchaValue(req)).thenReturn("555");
        signUpServlet = new SignUpServlet();
        signUpServlet.init(config);
    }

    @Test
    public void shouldGetRequestDispatcherWhenDoGet() throws ServletException, IOException {

        when(req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp")).thenReturn(requestDispatcherMock);
        signUpServlet.doGet(req, resp);
        String captchaValue = captchaServiceMock.generateCaptchaValue(3);
//        verify(captchaServiceMock).save(captchaValue, captchaServiceMock.identifyCaptcha(captchaValue), req, resp);
        verify(req).getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
    }

    @Test
    public void shouldRedirectToErrorWhenSignUpExistLoginUser() throws ServletException, IOException {
        when(req.getParameter("login")).thenReturn(user.getLogin());
        when(req.getParameter("password")).thenReturn(user.getPassword());
        when(req.getParameter("email")).thenReturn(user.getEmail());
        when(req.getParameter("firstname")).thenReturn(user.getFirstName());
        when(req.getParameter("lastname")).thenReturn(user.getLastName());
        when(req.getParameter("captchaValue")).thenReturn("157");
        signUpServlet.doPost(req, resp);
        verify(resp).sendRedirect("/eShop/signup");
    }

    @Test
    public void shouldRedirectToErrorWhenDataIsNotValid() throws ServletException, IOException {
        when(req.getParameter("login")).thenReturn(newUserNotValidData.getLogin());
        when(req.getParameter("password")).thenReturn(newUserNotValidData.getPassword());
        when(req.getParameter("email")).thenReturn(newUserNotValidData.getEmail());
        when(req.getParameter("firstname")).thenReturn(newUserNotValidData.getFirstName());
        when(req.getParameter("lastname")).thenReturn(newUserNotValidData.getLastName());
        when(req.getParameter("captchaValue")).thenReturn("749");
        signUpServlet.doPost(req, resp);
        verify(resp).sendRedirect("/eShop/signup");
    }

    @Test
    public void shouldRedirectToErrorWhenCaptchaIsIncorrect() throws ServletException, IOException {
        when(req.getParameter("login")).thenReturn(newUserNotValidData.getLogin());
        when(req.getParameter("password")).thenReturn(newUserNotValidData.getPassword());
        when(req.getParameter("email")).thenReturn(newUserNotValidData.getEmail());
        when(req.getParameter("firstname")).thenReturn(newUserNotValidData.getFirstName());
        when(req.getParameter("lastname")).thenReturn(newUserNotValidData.getLastName());
        when(req.getParameter("captchaValue")).thenReturn("000");
        signUpServlet.doPost(req, resp);
        verify(resp).sendRedirect("/eShop/signup");
    }

    @Test
    public void shouldAddNewUserWhenDataIsValid() throws ServletException, IOException {
        when(req.getParameter("login")).thenReturn(newUserValidData.getLogin());
        when(req.getParameter("password")).thenReturn(newUserValidData.getPassword());
        when(req.getParameter("email")).thenReturn(newUserValidData.getEmail());
        when(req.getParameter("firstname")).thenReturn(newUserValidData.getFirstName());
        when(req.getParameter("lastname")).thenReturn(newUserValidData.getLastName());
        when(req.getParameter("captchaValue")).thenReturn("749");
        signUpServlet.doPost(req, resp);
    }

}