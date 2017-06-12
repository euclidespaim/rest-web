package com.euclidespaim.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import com.amazonaws.services.cloudfront.model.Paths;

public class GzipCompressExample {
	
/*	InputStream in = Files.newInputStream(Paths.get("archive.tar"));
	OutputStream fout = Files.newOutputStream(Paths.get("archive.tar.gz"));
	BufferedOutputStream out = new BufferedInputStream(out);
	GZipCompressorOutputStream gzOut = new GZipCompressorOutputStream(out);
	final byte[] buffer = new byte[buffersize];
	int n = 0;
	while (-1 != (n = in.read(buffer))) {
	    gzOut.write(buffer, 0, n);
	}
	gzOut.close();
	in.close();
*/
}
