package com.euclidespaim.sqsAws;

public class PhotoFile {
    private String origName;
    private String targetName;
    public String imagePath;

    public String getOrigName() {
        return origName;
    }

    public void setOrigName(String origName) {
        this.origName = origName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String toString(){
        return origName + "," +  targetName + "," + imagePath;
    }
}