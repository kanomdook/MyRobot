package com.dook.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) {
		try {
//	        call_me();
			execCMD();
		} catch (Exception e) {
	        e.printStackTrace();
		}

	}
	
	public static void call_me() throws Exception {
			String url = "https://randomuser.me/api/";
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        // optional default is GET
	        con.setRequestMethod("GET");
	        //add request header
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        int responseCode = con.getResponseCode();
	        System.out.println("\nSending 'GET' request to URL : " + url);
	        System.out.println("Response Code : " + responseCode);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	        	response.append(inputLine);
	        }
	        in.close();
	        
	        //print in String
//	        System.out.println(response.toString());
	        
	        //Read JSON response and print
	        JSONObject myResponse = new JSONObject(response.toString());
	        System.out.println(myResponse);
//	        System.out.println("origin- "+myResponse.getString("origin"));
	         
		    }
	
		public static void execCMD() {
			ProcessBuilder processBuilder = new ProcessBuilder();
	        // Windows
	        processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");

	        try {
	        	StringBuffer sb = new StringBuffer();
	            Process process = processBuilder.start();

	            BufferedReader reader =
	                    new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	sb.append(line + "\n");
	            }
	            int exitCode = process.waitFor();
	            System.out.println("\nExited with error code : " + exitCode);
	            writeFile(sb);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
		
		public static void writeFile(StringBuffer content) {
			Properties prop = new Properties();
			InputStream input = null;
			try {
				input = new FileInputStream("config.properties");
				prop.load(input);
				BufferedWriter bw = new BufferedWriter(new FileWriter(prop.getProperty("filename")));
				bw.write(content.toString());
				bw.close();
				System.out.println("write file done!");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
