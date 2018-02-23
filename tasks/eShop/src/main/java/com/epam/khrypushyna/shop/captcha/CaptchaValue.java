package com.epam.khrypushyna.shop.captcha;

import java.util.Date;
import java.util.Objects;

public class CaptchaValue {
	private String value;
	private Date date;

	public CaptchaValue(String value) {
		this.value = value;
		this.date = new Date();
	}

	public String getValue() {
		return value;
	}

	public long getTime() {
		return date.getTime();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CaptchaValue that = (CaptchaValue) o;
		return Objects.equals(value, that.value) &&
				Objects.equals(date, that.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, date);
	}
}
