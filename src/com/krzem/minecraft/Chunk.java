package com.krzem.minecraft;



import com.jogamp.opengl.GL2;
import java.util.ArrayList;
import java.util.List;



public class Chunk extends Constants{
	public Main.Main_ cls;
	public Game game;
	public int x;
	public int z;
	public List<Block> bl;
	private int[][][] _bl;



	public Chunk(Main.Main_ cls,Game game,int x,int z){
		this.cls=cls;
		this.game=game;
		this.x=x;
		this.z=z;
		this.bl=new ArrayList<Block>();
		this._bl=new int[CHUNK_SIZE][CHUNK_HEIGHT][CHUNK_SIZE];
		for (int i=0;i<CHUNK_SIZE;i++){
			for (int j=0;j<CHUNK_HEIGHT;j++){
				for (int k=0;k<CHUNK_SIZE;k++){
					this._bl[i][j][k]=-1;
				}
			}
		}
	}



	public void update(GL2 gl){
		for (Block b:this.bl){
			b.update();
		}
	}



	public void draw(GL2 gl){
		for (Block b:this.bl){
			b.draw(gl);
		}
	}



	public void _add_bl(Block b){
		if (this._bl[(int)b.pos.x][(int)b.pos.y][(int)b.pos.z]!=-1){
			System.out.printf("Removing block at: x=%f, y=%f, z=%f\n",b.pos.x,b.pos.y,b.pos.z);
			this.bl.remove(this._bl[(int)b.pos.x][(int)b.pos.y][(int)b.pos.z]);
		}
		this.bl.add(b);
		this._bl[(int)b.pos.x][(int)b.pos.y][(int)b.pos.z]=this.bl.size()-1;
	}
}