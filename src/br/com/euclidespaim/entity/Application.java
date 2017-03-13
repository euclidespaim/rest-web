package br.com.euclidespaim.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Application {
	public static void main(String[] args) {
		try {
			String url = "http://localhost:8080/rest-web/rest/file/upload";
			File imgFile = new File("src/main/resource/img.jpg");
			File pdfFile = new File("src/main/resource/SamplePdf.pdf");
			File jsonFile = new File("src/main/resource/Organization.json");
			InputStream zipFile = new FileInputStream("src/main/resource/SoftwareData.rar");
			
			FileUploadClient.fileUpload(
					url,imgFile,pdfFile,
					jsonFile,zipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}