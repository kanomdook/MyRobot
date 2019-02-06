package com.kanomdook.barcode;

import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import com.keepautomation.barcode.BarCode;
import com.keepautomation.barcode.IBarCode;

public class BarCodeGenerator {

	public static void main(String[] args) {
		try {
//			qrCode();
//			EAN13();
			getBase64();
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
			barcode.draw("C:\\Users\\ASUS\\eclipse-workspace\\MyRobot\\files\\qrcode.gif");
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
			barcode.draw("C:\\Users\\ASUS\\eclipse-workspace\\MyRobot\\files\\ean13.gif");
			System.out.println("EAN13 Generate Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getBase64() {
		try {
			File file = new File("C:\\\\Users\\\\ASUS\\\\eclipse-workspace\\\\MyRobot\\\\files\\\\ean13.gif");
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			System.out.println(encodedString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}