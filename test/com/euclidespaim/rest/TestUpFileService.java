package com.euclidespaim.rest;


import java.io.File;
 
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
 
public class TestUpFileService {
 
    public static void main(String []args) throws Exception {
 
        // set file upload parameters
        String httpURL = "http://localhost:8080/rest-web/rest/fileservice/upload/zip";
        File filePath = new File("C:\\Users\\Kid\\Desktop\\up\\dicom.zip");
 
        // invoke file upload service using above parameters
        String responseString = testUploadService(httpURL, filePath);
        System.out.println("responseString : " + responseString);
    }
 
    /**
     * uploads zip file using the input HTTP URL Test
     * @param httpURL
     * @param filePath
     * @param filename
     * @return
     * @throws Exception
     */
    public static String testUploadService(String httpURL, File filePath)  throws Exception {
 
        // local variables
        ClientConfig clientConfig = null;
        Client client = null;
        WebTarget webTarget = null;
        Invocation.Builder invocationBuilder = null;
        Response response = null;
        FileDataBodyPart fileDataBodyPart = null;
        FormDataMultiPart formDataMultiPart = null;
        int responseCode;
        String responseMessageFromServer = null;
        String responseString = null;
 
        try{
            // invoke service after setting necessary parameters
            clientConfig = new ClientConfig();
            clientConfig.register(MultiPartFeature.class);
            client =  ClientBuilder.newClient(clientConfig);
            webTarget = client.target(httpURL);
 
            // set file upload values
            fileDataBodyPart = new FileDataBodyPart("uploadFile", filePath, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            formDataMultiPart = new FormDataMultiPart();
            formDataMultiPart.bodyPart(fileDataBodyPart);
 
            // invoke service
            invocationBuilder = webTarget.request();
            //          invocationBuilder.header("Authorization", "Basic " + authorization);
            response = invocationBuilder.post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));
 
            // get response code
            responseCode = response.getStatus();
            System.out.println("Response code: " + responseCode);
 
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + responseCode);
            }
 
            // get response message
            responseMessageFromServer = response.getStatusInfo().getReasonPhrase();
            System.out.println("ResponseMessageFromServer: " + responseMessageFromServer);
 
            // get response string
            responseString = response.readEntity(String.class);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally{
            // release resources, if any
            fileDataBodyPart.cleanup();
            formDataMultiPart.cleanup();
            formDataMultiPart.close();
            response.close();
            client.close();
        }
        return responseString;
    }
}