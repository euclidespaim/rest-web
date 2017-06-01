package com.euclidespaim.rest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
 
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
 
public class TestDownFileService {
 
    public static final String DOWNLOAD_FILE_LOCATION = "C:\\Users\\Kid\\Desktop\\down\\";
 
    public static void main(String []args) throws IOException {
 
        String httpURL = "http://localhost:8080/rest-web/rest/fileservice/download/zip";
        String responseString = testDownloadService(httpURL);
        System.out.println("responseString : " + responseString);
    }
 
    /**
     * downloads zip file using the input HTTP URL Test
     * @param httpURL
     * @return
     * @throws IOException
     */
    public static String testDownloadService(String httpURL) throws IOException {
 
        // local variables
        ClientConfig clientConfig = null;
        Client client = null;
        WebTarget webTarget = null;
        Invocation.Builder invocationBuilder = null;
        Response response = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        int responseCode;
        String responseMessageFromServer = null;
        String responseString = null;
        String qualifiedDownloadFilePath = null;
 
        try{
            // invoke service after setting necessary parameters
            clientConfig = new ClientConfig();
            clientConfig.register(MultiPartFeature.class);
            client =  ClientBuilder.newClient(clientConfig);
            client.property("accept", "application/zip");
            webTarget = client.target(httpURL);
 
            // invoke service
            invocationBuilder = webTarget.request();
            //          invocationBuilder.header("Authorization", "Basic " + authorization);
            response = invocationBuilder.get();
 
            // get response code
            responseCode = response.getStatus();
            System.out.println("Response code: " + responseCode);
 
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + responseCode);
            }
 
            // get response message
            responseMessageFromServer = response.getStatusInfo().getReasonPhrase();
            System.out.println("ResponseMessageFromServer: " + responseMessageFromServer);
 
            // read response string
            inputStream = response.readEntity(InputStream.class);
            qualifiedDownloadFilePath = DOWNLOAD_FILE_LOCATION + "Zdicom.zip";
            outputStream = new FileOutputStream(qualifiedDownloadFilePath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            // set download SUCCES message to return
            responseString = "downloaded successfully at " + qualifiedDownloadFilePath;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally{
            // release resources, if any
            outputStream.close();
            response.close();
            client.close();
        }
        return responseString;
    }
}