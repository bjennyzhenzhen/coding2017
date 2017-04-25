package com.coderising.jvm.loader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		byte[] buff = new byte[1024];
		
		URL base = this.getClass().getResource("/");
		String path = base.toString().replace("file:/", "")+className.replace(".", "/")+".class";
		File file = new File(path);
		ByteArrayOutputStream aos = new ByteArrayOutputStream();
		try {
			InputStream is = new FileInputStream(file);
			int count = 0;
			while((count=is.read(buff, 0, buff.length))!=-1){
				aos.write(buff, 0, count);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return aos.toByteArray();	
		
		
	}
	
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<clzPaths.size();i++){
			if(i!=clzPaths.size()-1){
				sb.append(clzPaths.get(i)).append(";");
			}else{
				sb.append(clzPaths.get(i));
			}
			
		}
		
		return sb.toString();
	}

	

	

}
