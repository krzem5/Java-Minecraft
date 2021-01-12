package com.krzem.minecraft;



import com.jogamp.opengl.GL2;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;



public class BlockModel extends Constants{
	private Map<String,BlockModelPart[]> _data;



	private BlockModel(){

	}



	public void draw(GL2 gl,String part){

	}



	public static BlockModel _load(String nm){
		try{

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}