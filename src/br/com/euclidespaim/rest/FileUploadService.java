package br.com.euclidespaim.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
	
	@Path("/fileservice")
	public class FileUploadService implements IFileService {
	 
	    public static final String UPLOAD_FILE_SERVER = "C:\\Users\\Kid\\Desktop\\up\\";
	 
	    // http://localhost:8080/rest-web/rest/fileservice/download/zip
	    @GET
	    @Path("/download/zip")
	    @Produces("application/zip")
	    public Response downloadZippedFile() {
	 
	        // set file (and path) to be download
	        File file = new File("C:\\Users\\Kid\\Desktop\\up\\dicom.zip");
	 
	        ResponseBuilder responseBuilder = Response.ok((Object) file);
	        responseBuilder.header("Content-Disposition", "attachment; filename=\"Zdicom.zip\"");
	        return responseBuilder.build();
	    }
	 
	    // http://localhost:8080/rest-web/rest/fileservice/upload/zip
	    @POST
	    @Path("/upload/zip")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    public Response uploadZippedFile(
	            @FormDataParam("uploadFile") InputStream fileInputStream,
	            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {
	 
	        // local variables
	        String fileName = null;
	        String uploadFilePath = null;
	 
	        try {
	            fileName = fileFormDataContentDisposition.getFileName();
	            uploadFilePath = writeToFileServer(fileInputStream, fileName);
	        }
	        catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	        finally{
	            // release resources, if any
	        }
	        return Response.ok("File uploaded successfully at " + uploadFilePath).build();
	    }
	 
	    /**
	     * write input stream to file server
	     * @param inputStream
	     * @param fileName
	     * @throws IOException
	     */
	    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
	 
	        OutputStream outputStream = null;
	        String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;
	 
	        try {
	            outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
	            int read = 0;
	            byte[] bytes = new byte[1024];
	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	            outputStream.flush();
	        }
	        catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	        finally{
	            //release resource, if any
	            outputStream.close();
	        }
	        return qualifiedUploadFilePath;
	    }
	}
