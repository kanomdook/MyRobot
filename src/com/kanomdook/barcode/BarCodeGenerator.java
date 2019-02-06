package com.kanomdook.barcode;

import com.keepautomation.barcode.BarCode;
import com.keepautomation.barcode.IBarCode;

public class BarCodeGenerator {

	public static void main(String[] args) {
		try {
//			qrCode();
			EAN13();
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
		barcode.setCodeToEncode("123456789999");
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

}
