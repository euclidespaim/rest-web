package br.com.euclidespaim.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class GzipRawtoBytes {
	InputStream is = new BufferedInputStream(new GZIPInputStream(
			new FileInputStream("zip")));
    
	for (int b; (b = is.read()) != -1;) {
        // process byte
    }
}


