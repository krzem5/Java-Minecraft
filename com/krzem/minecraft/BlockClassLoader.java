package com.krzem.minecraft;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Class;
import java.lang.ClassLoader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;



public class BlockClassLoader extends ClassLoader{
	@Override
	public Class<?> findClass(String nm) throws ClassNotFoundException{
		try{
			byte[] b=this._load(nm);
			return this.defineClass(nm.substring(nm.lastIndexOf("\\")+1,nm.length()-5),b,0,b.length);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}



	private byte[] _load(String fn) throws FileNotFoundException,IOException,InterruptedException{
		ProcessBuilder b=new ProcessBuilder("cmd.exe","/c","cd \""+fn.substring(0,fn.lastIndexOf("\\"))+"\"&&javac -cp ../modules/jogl-all.jar;../modules/jogl-all-natives-windows-amd64.jar;../modules/gluegen-rt.jar;../modules/gluegen-rt-natives-windows-amd64.jar; -sourcepath "+fn.substring(0,fn.lastIndexOf("\\com\\"))+" "+fn.substring(fn.lastIndexOf("\\")+1));
		b.inheritIO();
		Process p=b.start();
		p.waitFor();
		InputStream inpS=new FileInputStream(new File(fn.replace(".java",".class")));
		ByteArrayOutputStream bS=new ByteArrayOutputStream();
		int nv=0;
		try{
			while ((nv=inpS.read())!=-1){
				bS.write(nv);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return bS.toByteArray();
	}



	public Map<String,Constructor> _load_b_map(String dir){
		try{
			Map<String,Constructor> bl=new HashMap<String,Constructor>();
			for (File f:new File(dir).listFiles()){
				if (f.isFile()&&f.getName().endsWith(".java")){
					Class<?> o=this.loadClass(f.getAbsolutePath());
					if (o!=null){
						bl.put(f.getName().split("\\.")[0].toLowerCase(),o.getConstructor());
					}
				}
			}
			return bl;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}