package com.kanomdook.barcode;

import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import com.keepautomation.barcode.BarCode;
import com.keepautomation.barcode.IBarCode;

public class BarCodeGenerator {
	private static final String PATH = System.getProperty("user.dir");

	public static void main(String[] args) {
		try {
			qrCode();
//			EAN13();
//			getBase64();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void qrCode() {
		BarCode barcode = new BarCode();
		barcode.setCodeToEncode("KANOMDOOK");
		barcode.setSymbology(IBarCode.QRCODE);
		barcode.setQrCodeDataMode(IBarCode.QR_MODE_AUTO);
		barcode.setQrCodeEcl(IBarCode.QR_ECL_L);
		barcode.setQrCodeVersion(1);
		try {
			barcode.draw(PATH + "\\files\\qrcode.gif");
			System.out.println("QrCode Generate Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void EAN13() {
		BarCode barcode = new BarCode();
		barcode.setCodeToEncode("356478366622");
		barcode.setSymbology(IBarCode.EAN13);
		barcode.setX(2);
		barcode.setY(50);
		barcode.setRightMargin(0);
		barcode.setLeftMargin(0);
		barcode.setTopMargin(0);
		barcode.setBottomMargin(0);
		try {
			barcode.draw(PATH + "\\files\\ean13.gif");
			System.out.println("EAN13 Generate Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getBase64() {
		try {
			File file = new File(PATH + "\\files\\ean13.gif");
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
			System.out.println(base64Encoded);
			base64ToImageFile(base64Encoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void base64ToImageFile(String base64) {
		byte[] decoded = Base64.getDecoder().decode(base64);
		try {
			FileUtils.writeByteArrayToFile(new File(PATH + "\\files\\byteImg.png"), decoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
