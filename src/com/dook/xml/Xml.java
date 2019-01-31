package com.dook.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;

public class Xml {

	public static void main(String[] args) {
		try {
			System.out.println(isFoundAlarm2658());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Boolean isFoundAlarm2658() {
		Boolean isFound = false;
		try {
			File inputFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\MyRobot\\src\\com\\dook\\xml\\alarmXml.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("status");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println(eElement.getElementsByTagName("code").item(0).getTextContent().toString());
				if (eElement.getElementsByTagName("code").item(0).getTextContent().equals("2658")) {
					isFound = true;
				} else {
					isFound = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isFound;
	}

//	public static void isFoundAlarm2658-2() {
//		try {
//			File inputFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\MyRobot\\src\\com\\dook\\xml\\alarmXml2.xml");
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(inputFile);
//			doc.getDocumentElement().normalize();
//			NodeList nList = doc.getElementsByTagName("student");
//			JsonArray students = new JsonArray();
//			
//			for (int i = 0; i < nList.getLength(); i++) {
//				JsonObject student = new JsonObject();
//				Node nNode = nList.item(i);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					student.addProperty("firstname", eElement.getElementsByTagName("firstname").item(0).getTextContent());
//					student.addProperty("lastname", eElement.getElementsByTagName("lastname").item(0).getTextContent());
//					student.addProperty("nickname", eElement.getElementsByTagName("nickname").item(0).getTextContent());
//					student.addProperty("marks", eElement.getElementsByTagName("marks").item(0).getTextContent());
//					students.add(student);
//				}
//			}
//			System.out.println(students);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
