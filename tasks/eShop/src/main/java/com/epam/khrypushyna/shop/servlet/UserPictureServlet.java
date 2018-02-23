package com.epam.khrypushyna.shop.servlet;

import com.epam.khrypushyna.shop.entity.User;
import com.epam.khrypushyna.shop.img.ImageStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/avatar")
public class UserPictureServlet extends HttpServlet {

	private ImageStorage imageStorage;

	@Override
	public void init() throws ServletException {
		imageStorage = (ImageStorage) getServletContext().getAttribute("imageStorage");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("image/jpeg");
		try {
			OutputStream out = resp.getOutputStream();
			User user = (User) req.getSession().getAttribute("user");
			File image = imageStorage.getUserAvatarFile(user.getLogin() + ".png");
			FileInputStream in = new FileInputStream(image);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) >= 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
