package com.simplilearn.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
public class FileDownloadController {
	
	@RequestMapping(value="/upload", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public String fileupload(@RequestParam("file")MultipartFile file) throws IOException {
		
		File convFile = new File("/Users/snigdha.mittadoddiicloud.com/Desktop/tmp/"+file.getOriginalFilename());
				convFile.createNewFile();
				
				 try (FileOutputStream fos = new FileOutputStream(convFile)) {
					fos.write(file.getBytes());
				}
					return "File Uploaded Succesfully";
		
	}
	
	
	
	// @RestController = @Controller + @ResponseBody
	
	@RequestMapping(value="/download", method= RequestMethod.GET)
	public ResponseEntity<Object> downloadFile(@RequestParam("fileName") String fileName) throws FileNotFoundException{
		
		File file= new File("/Users/snigdha.mittadoddiicloud.com/Desktop/tmp/"+fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; fileName=" +fileName);
		return ResponseEntity.ok().headers(headers).contentType(contentType(MediaType.parseMediaType("application/txt"))).body(resource);
	
	}


	private MediaType contentType(MediaType parseMediaType) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
