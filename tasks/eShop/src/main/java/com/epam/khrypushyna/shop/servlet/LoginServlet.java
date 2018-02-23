package com.epam.khrypushyna.shop.servlet;

import com.epam.khrypushyna.shop.Validator;
import com.epam.khrypushyna.shop.entity.User;
import com.epam.khrypushyna.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.epam.khrypushyna.shop.Constants.PATH_ERROR_PAGE;
import static com.epam.khrypushyna.shop.Constants.PATH_MAIN_PAGE;
import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA_VALIDATOR;
import static com.epam.khrypushyna.shop.Constants.SERVLET_CONTEXT_ATTRIBUTE_USERS;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private Map<String, Object> messageMap;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(SERVLET_CONTEXT_ATTRIBUTE_USERS);
        messageMap = ((Validator) getServletContext().getAttribute(SERVLET_CONTEXT_ATTRIBUTE_CAPTCHA_VALIDATOR)).getMessagesMap();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        messageMap.clear();
        User user = userService.login(req.getParameter("login"), req.getParameter("password"));
        if(user != null){
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/eShop/items");
        }
        else {
            resp.sendRedirect("/eShop/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!messageMap.isEmpty() || req.getSession().getAttribute("role") != null){
            for(Map.Entry<String, Object> pair : messageMap.entrySet()){
                req.setAttribute(pair.getKey(), pair.getValue());
            }
            req.getRequestDispatcher(PATH_MAIN_PAGE).forward(req, resp);
            return;
        }
        req.setAttribute("message", "Only for authorized users");
        req.getRequestDispatcher(PATH_ERROR_PAGE).forward(req, resp);
    }

}
