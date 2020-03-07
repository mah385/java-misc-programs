package com.mah;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DuplicateFileComparisionUsingMD5 {

	public static String checkMD5(String fileName) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		FileInputStream fileInputStream = new FileInputStream(fileName);

		byte[] dataBytes = new byte[1024];

		int nread = 0;
		while ((nread = fileInputStream.read(dataBytes)) != -1) {
			messageDigest.update(dataBytes, 0, nread);
		}

		fileInputStream.close();

		byte[] mdbytes = messageDigest.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		String checkedMD5_1 = checkMD5("A:\\sts-workspace\\java-misc-programs\\src\\DummyFiles_1.rar");
		System.out.println(checkedMD5_1);
		String checkedMD5_2 = checkMD5("A:\\sts-workspace\\java-misc-programs\\src\\DummyFiles_2.rar");
		System.out.println(checkedMD5_2);
	}

}
