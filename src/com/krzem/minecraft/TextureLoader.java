package com.krzem.minecraft;




import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;



public class TextureLoader extends Constants{
	private static List<String> _sl=null;
	private static List<BufferedImage> _il=null;
	private static List<Texture> _tl=null;



	public static BufferedImage get_image(String nm){
		return TextureLoader._il.get(TextureLoader._sl.indexOf(nm));
	}



	public static Texture get_texture(String nm){
		return TextureLoader._tl.get(TextureLoader._sl.indexOf(nm));
	}



	public static void init(GL2 gl){
		TextureLoader._sl=new ArrayList<String>();
		TextureLoader._il=new ArrayList<BufferedImage>();
		TextureLoader._tl=new ArrayList<Texture>();
		TextureLoader._load(IMAGE_DIR,"",gl);
	}



	private static void _load(String d,String p,GL2 gl){
		for (File f:new File(d).listFiles()){
			try{
				if (f.getName().endsWith(".png")){
					BufferedImage bi=(BufferedImage)ImageIO.read(f);
					BufferedImage i=SCREEN.getConfigurations()[0].createCompatibleImage(IMAGE_SIZE,IMAGE_SIZE,bi.getTransparency());
					Graphics ig=i.createGraphics();
					ig.drawImage(bi,0,0,IMAGE_SIZE,IMAGE_SIZE,null);
					ig.dispose();
					TextureLoader._sl.add(p+f.getName());
					TextureLoader._il.add(i);
					TextureLoader._tl.add(TextureIO.newTexture(AWTTextureIO.newTextureData(gl.getGLProfile(),i,false)));
					System.out.println("Loaded Image: "+p+f.getName());
				}
				else if (f.getName().indexOf(".")==-1){
					TextureLoader._load(d+"\\"+f.getName(),p+f.getName()+"/",gl);
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}