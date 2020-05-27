package com.ecommerce.beans;

import org.apache.commons.codec.digest.DigestUtils;

public final class BeanHelper {
	private BeanHelper() {
	};
	public static String generateId(String txt) {
		return DigestUtils.sha256Hex(txt);
	}
}
