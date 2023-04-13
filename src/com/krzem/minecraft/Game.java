package com.krzem.minecraft;



import com.jogamp.opengl.GL2;
import java.util.ArrayList;
import java.util.List;



public class Game extends Constants{
	public Main.Main_ cls;
	public List<Chunk> cl;
	public long _tn;
	private long _dt=-1;
	private double _ltm=0;



	public Game(Main.Main_ cls){
		this.cls=cls;
		this.cl=new ArrayList<Chunk>();
	}



	public void update(GL2 gl){
		long tm=System.nanoTime();
		if (this._dt==-1){
			this._dt=tm;
		}
		else{
			double dff=(double)(tm-this._dt);
			this._ltm=dff*1e-9;
			this._dt=tm+0;
		}
		this.cls.cam.draw(gl);
		for (Chunk c:this.cl){
			c.update(gl);
		}
		this._tn=0;
	}



	public void draw(GL2 gl){
		// gl.glBegin(GL2.GL_TRIANGLES);
		// gl.glColor3d(1,1,0);
		// Vector3 ax=new Vector3(1,0,0);
		// Vector3 o=new Vector3(5,0,0);
		// Vector3 p=new Vector3(TRACK_PIECE_BIGGER_RADIUS,0,0).rotate(new Vector3(0,1,0),-Math.atan2(ax.z,ax.x)+Math.PI/2).rotate(ax,Math.PI/2);
		// for (double i=0;i<Math.PI*2;i+=Math.PI*2/TRACK_PIECE_DETAIL){
		// 	p.rotate(ax,i).add(o).vertex(gl);
		// 	p.rotate(ax,i+Math.PI*2/TRACK_PIECE_DETAIL).add(o).vertex(gl);
		// 	o.vertex(gl);
		// 	this._tn++;
		// }
		// gl.glEnd();



		for (Chunk c:this.cl){
			c.draw(gl);
		}
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		this.cls.glu.gluOrtho2D(0,this.cls.WINDOW_SIZE.width,0,this.cls.WINDOW_SIZE.height);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(1,1,1);
		Vector c=new Vector(this.cls.WINDOW_SIZE.width/2,this.cls.WINDOW_SIZE.height/2);
		c.add(-CROSSHAIR_SIZE/2,-CROSSHAIR_SIZE/2/8).vertex(gl);
		c.add(-CROSSHAIR_SIZE/2,CROSSHAIR_SIZE/2/8).vertex(gl);
		c.add(CROSSHAIR_SIZE/2,CROSSHAIR_SIZE/2/8).vertex(gl);
		c.add(CROSSHAIR_SIZE/2,-CROSSHAIR_SIZE/2/8).vertex(gl);
		c.add(-CROSSHAIR_SIZE/2/8,-CROSSHAIR_SIZE/2).vertex(gl);
		c.add(-CROSSHAIR_SIZE/2/8,CROSSHAIR_SIZE/2).vertex(gl);
		c.add(CROSSHAIR_SIZE/2/8,CROSSHAIR_SIZE/2).vertex(gl);
		c.add(CROSSHAIR_SIZE/2/8,-CROSSHAIR_SIZE/2).vertex(gl);
		gl.glEnd();
	}
}
