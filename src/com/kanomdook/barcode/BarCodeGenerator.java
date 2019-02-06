package com.kanomdook.barcode;

import com.keepautomation.barcode.BarCode;
import com.keepautomation.barcode.IBarCode;

public class BarCodeGenerator {

	public static void main(String[] args) {
		try {
			qrCode();
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

}
