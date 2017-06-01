package com.euclidespaim.sqsAws;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 3/19/13
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoProcessor {

    public static void  generateImage(String imagePath, String origName, String targetName, int scalabity){
        String origImage =   null;
        String targetImage = null;
        File origFile = null;
        BufferedImage buffImg = null;
        File targetFile = null;
        try{
            origImage =   imagePath + "/" + origName;
            targetImage = imagePath + "/" + targetName;
            origFile = new File(origImage);
            buffImg = ImageIO.read(origFile);
            buffImg = Scalr.resize(buffImg, Scalr.Method.SPEED, scalabity);
            targetFile = new File(targetImage);
            ImageIO.write(buffImg, "jpeg", targetFile);

        }catch (Exception e){
            System.out.println("Exception in processing image : " + e);
        }finally {
            buffImg = null;

        }
    }
}