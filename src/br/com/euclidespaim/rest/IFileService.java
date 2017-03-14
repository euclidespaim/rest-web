package br.com.euclidespaim.rest;

import java.io.InputStream;
 
import javax.ws.rs.core.Response;
 
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
 
public interface IFileService {
 
    public Response downloadZippedFile();
    public Response uploadZippedFile(InputStream fileInputStream, FormDataContentDisposition fileFormDataContentDisposition);
}