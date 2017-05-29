package br.com.euclidespaim.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class CompressGZIPFolder {
 
    public static void main(String[] args) throws Exception{
    	long ini = System.currentTimeMillis();
        String sourceFolderName =  "C:\\Users\\Kid\\Desktop\\up";
        String outputFileName = "C:\\Users\\Kid\\Desktop\\down\\JustExample.zip";
 
        FileOutputStream fos = new FileOutputStream(outputFileName);
        GZIPOutputStream zos = new GZIPOutputStream(fos);
        //level - the compression level (0-9)
        //zos.setLevel(9);
 
        System.out.println("Begin to compress folder : " + sourceFolderName + " to " + outputFileName);
        addFolder(zos, sourceFolderName, sourceFolderName);
 
        zos.close();
        long fim = System.currentTimeMillis();
        System.out.println("Program ended successfully!");
        System.out.println("Tempo de compressão: " + (fim - ini));
    }
 
    private static void addFolder(GZIPOutputStream zos,String folderName,String baseFolderName)throws Exception{
        File f = new File(folderName);
        if(f.exists()){
 
            if(f.isDirectory()){
                //Thank to peter 
                //For pointing out missing entry for empty folder
                if(!folderName.equalsIgnoreCase(baseFolderName)){
                    String entryName = folderName.substring(baseFolderName.length()+1,folderName.length()) + File.separatorChar;
                    System.out.println("Adding folder entry " + entryName);
                    ZipEntry ze= new ZipEntry(entryName);
                    //zos.putNextEntry(ze);    
                }
                File f2[] = f.listFiles();
                for(int i=0;i<f2.length;i++){
                    addFolder(zos,f2[i].getAbsolutePath(),baseFolderName);    
                }
            }else{
                //add file
                //extract the relative name for entry purpose
                String entryName = folderName.substring(baseFolderName.length()+1,folderName.length());
                System.out.print("Adding file entry " + entryName + "...");
                ZipEntry ze= new ZipEntry(entryName);
                zos.finish();
                FileInputStream in = new FileInputStream(folderName);
                int len;
                byte buffer[] = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
                zos.close();
                System.out.println("OK!");
 
            }
        }else{
            System.out.println("File or directory not found " + folderName);
        }
 
    }
 
}