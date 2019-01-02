package com.letbeclear.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letbeclear.config.ImageConfig;
import com.letbeclear.request.dto.ImageRequest;
import com.letbeclear.services.ImageService;

@RestController
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	ImageConfig imageConfig;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="rest/post/{type}", method=RequestMethod.POST)
	public long addImage(@RequestBody ImageRequest imageRequest, @PathVariable("type") String type)
	{
		
	 return imageService.addImage(imageRequest,type);
		
	}
	
	
	
	/*
	 * for view the images
	 */
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/view" ,method=RequestMethod.GET)
	public void photo(@RequestParam("path") String path,HttpServletResponse response) throws IOException
	{
		response.setContentType("image/jpeg");

		System.out.println(path);
		String imagePath=imageConfig.getPath()+path;
		System.out.println(imagePath);
		ServletOutputStream out;
		FileInputStream fin;
		BufferedInputStream bin;
		
		
			out=response.getOutputStream();
		BufferedOutputStream bout=new BufferedOutputStream(out);
		try
		{
			fin=new FileInputStream(imagePath);
			bin=new BufferedInputStream(fin);
			int ch=0;
			while((ch=bin.read())!=-1)
			{
			bout.write(ch);	
			}
			bin.close();
			fin.close();
			bout.close();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			bout.close();
			out.close();
		}
		

	
	}
}
