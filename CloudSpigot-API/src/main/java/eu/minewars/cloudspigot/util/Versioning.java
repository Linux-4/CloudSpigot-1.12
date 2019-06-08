package eu.minewars.cloudspigot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Versioning {

	private static String getLatestVersion() {
		try {
			HttpsURLConnection connection = (HttpsURLConnection) new URL(
					"https://ci.server24-7.eu/job/CloudSpigot-1.12/latest/.gitrev").openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			return reader.readLine().substring(0, 7);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static int getDistance(String hash) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					"https://api.github.com/repos/Server24-7/CloudSpigot-1.12/compare/" + hash + "..." + getLatestVersion())
							.openConnection();
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
				return -2; // Unknown commit
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
				JSONObject obj = (JSONObject) new JSONParser().parse(reader);
				String status = (String) obj.get("status");
				switch (status) {
				case "identical":
					return 0;
				case "behind":
					return ((Number) obj.get("behind_by")).intValue();
				default:
					return -1;
				}
			} catch (ParseException | NumberFormatException e) {
				e.printStackTrace();
				return -1;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static String getCurrentVersion() {
		if (Bukkit.getVersion().startsWith("git-CloudSpigot-")) {
			return Bukkit.getVersion().substring("git-CloudSpigot-".length()).split("[-\\s]")[0].replace("\"", "");
		} else {
			return "Unknown";
		}
	}
	
	public static double getJavaVersion() {
		String version = System.getProperty("java.version");
		int pos = version.indexOf('.');
		pos = version.indexOf('.', pos + 1);
		return Double.parseDouble(version.substring(0, pos));
	}

}
