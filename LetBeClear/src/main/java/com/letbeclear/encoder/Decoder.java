package com.letbeclear.encoder;

import java.io.FileOutputStream;
import java.util.Base64;

public class Decoder {
	
	public static int decoder(String encodedString ,String Path)
	{
		try(FileOutputStream outputFile=new FileOutputStream(Path))
		{
			byte[] imageByte=Base64.getDecoder().decode(encodedString);
			outputFile.write(imageByte);
			return 1;
		}catch (Exception e){
			System.out.println(e);
		return 0;}
	}
}