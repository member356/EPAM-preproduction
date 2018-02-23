package com.epam.khrypushyna.shop.img;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;

public class AvatarImageStorage implements ImageStorage {

	private static final String PROJECT_ROOT = System.getProperty("user.dir");
	private static final String IMAGE_DIRECTORY = "\\src\\main\\webapp\\temp\\";
	private static final String DEFAULT_IMAGE_PATH = "\\src\\main\\webapp\\temp\\default.png";

	@Override
	public void saveUserAvatarFile(Part filePart, String login) throws IOException {
		String path = PROJECT_ROOT + IMAGE_DIRECTORY + login + ".png";
		File image = new File(path);
		OutputStream out = new FileOutputStream(image);
		InputStream fileContent = filePart.getInputStream();
		int read;
		byte[] bytes = new byte[1024];
		while ((read = fileContent.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.close();
	}

	@Override
	public File getUserAvatarFile(String name) throws IOException {
		File file = new File(PROJECT_ROOT + IMAGE_DIRECTORY + name);
		return file.exists() ? file : new File(PROJECT_ROOT + DEFAULT_IMAGE_PATH);
	}

}
