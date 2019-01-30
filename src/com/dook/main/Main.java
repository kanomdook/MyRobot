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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
//			requestApi();
			execCMD();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void requestApi() {
		try {
			String url = "https://randomuser.me/api/";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			logger.debug("\nSending 'GET' request to URL : " + url);
			logger.debug("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();

			if (responseCode == 200) {
				JSONObject res = new JSONObject(response.toString());
				JSONArray results = res.getJSONArray("results");
				for (int i = 0; i < results.length(); i++) {
					JSONObject result = results.getJSONObject(i);
					String gender = result.getString("gender");
					JSONObject dob = result.getJSONObject("dob");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
					Date birthDay = dateFormat.parse(dob.getString("date").replaceAll("Z$", "+0000"));
					logger.debug(gender);
					logger.debug(dob);
					logger.debug(birthDay);
				}
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String prettyJson = gson.toJson(res);
				logger.debug(prettyJson);
			} else {
				logger.warn("warning responseCode: " + responseCode);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void execCMD() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		// Windows
		processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");

		try {
			StringBuffer sb = new StringBuffer();
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			int exitCode = process.waitFor();
			logger.debug("Exited with error code : " + exitCode);
			writeFile(sb.toString());
		} catch (IOException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

	public static void writeFile(String content) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			BufferedWriter bw = new BufferedWriter(new FileWriter(prop.getProperty("filename")));
			bw.write(content);
			bw.close();
			logger.debug("write file done!");
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
}
