package com.krzem.minecraft;



import com.jogamp.opengl.GL2;
import java.lang.Exception;
import java.lang.reflect.Constructor;
import java.util.Map;
import org.w3c.dom.Element;



public abstract class Block extends Constants{
	public Main.Main_ cls;
	public Game game;
	public Chunk c;
	public Vector3 pos;
	private BlockModel _mdl;
	private static Map<String,Constructor> _bcl=null;



	abstract public String get_name();



	abstract public void update();



	abstract public void draw(GL2 gl);



	abstract public void _load_extra(Element e);



	abstract public void _export_extra(Element e);



	public final void draw_model(GL2 gl,String t){
		this._mdl.draw(gl,t);
	}



	private void _constructor(Main.Main_ cls,Game game,Chunk c,int x,int y,int z){
		this.cls=cls;
		this.game=game;
		this.c=c;
		this.pos=new Vector3(x,y,z);
		this._mdl=BlockModel._load(this.get_name());
	}



	public static final Block _create(String nm,Main.Main_ cls,Game game,Chunk c,int x,int y,int z){
		try{
			if (Block._bcl==null){
				Block._bcl=new BlockClassLoader()._load_b_map(BLOCK_CLASS_DIR);
			}
			Object _b=Block._bcl.get(nm.toLowerCase()).newInstance();
			Block b=(Block)_b;
			b._constructor(cls,game,c,x,y,z);
			return b;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}