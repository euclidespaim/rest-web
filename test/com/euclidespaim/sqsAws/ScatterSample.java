package com.euclidespaim.sqsAws;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.zip.ZipOutputStream;


public class ScatterSample {
	 //Creating a zip file with multiple threads
	  ParallelScatterZipCreator scatterZipCreator = new ParallelScatterZipCreator();
	  ScatterZipOutputStream dirs = ScatterZipOutputStream.fileBased(File.createTempFile("scatter-dirs", "tmp"));

	  public ScatterSample() throws IOException {
	  }

	  public void addEntry(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier streamSupplier) throws IOException {
	     if (zipArchiveEntry.isDirectory() && !zipArchiveEntry.isUnixSymlink())
	        dirs.addArchiveEntry(ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, streamSupplier));
	     else
	        scatterZipCreator.addArchiveEntry( zipArchiveEntry, streamSupplier);
	  }

	  public void writeTo(ZipArchiveOutputStream zipArchiveOutputStream)
	  throws IOException, ExecutionException, InterruptedException {
	     dirs.writeTo(zipArchiveOutputStream);
	     dirs.close();
	     scatterZipCreator.writeTo(zipArchiveOutputStream);
	  }
	}