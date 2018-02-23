package com.epam.khrypushyna.shop.img;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

public interface ImageStorage {

	void saveUserAvatarFile(Part filePart, String login) throws IOException;

	File getUserAvatarFile(String name) throws IOException;

}
