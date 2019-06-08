package eu.minewars.cloudspigot.updater;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import eu.minewars.cloudspigot.util.Versioning;

public class Updater {

	private static final String BASE_URL = "https://ci.server24-7.eu/job/CloudSpigot-1.12/latest/";
	private static final String URL = BASE_URL + "CloudSpigot-Server/cloudspigot-1.12.2.jar";

	public static String update() {
		if (Versioning.getDistance(Versioning.getCurrentVersion()) > 0) {
			try {
				URL url = new URL(URL);
				File jar = new File(Updater.class.getProtectionDomain().getCodeSource().getLocation().getPath());
				if (jar.isDirectory()) {
					return "§cError: Not running from JAR file!";
				} else {
					try {
						download(url, jar);
					} catch (IOException e) {
						e.printStackTrace();
						return "§cError: " + e.getMessage();
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return "§cError: " + e.getMessage();
			}
			return "§9Update successful, please restart the server!";
		} else {
			return "§9No updates available!";
		}
	}

	private static void download(URL url, File f) throws IOException {
		BufferedInputStream in = new BufferedInputStream(url.openStream());
		FileOutputStream fileOutputStream = new FileOutputStream(f);
		byte dataBuffer[] = new byte[1024];
		int bytesRead;
		while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
			fileOutputStream.write(dataBuffer, 0, bytesRead);
		}
		fileOutputStream.close();
	}

}