package com.krzem.minecraft;



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;



public class BlockModelPart extends Constants{
	private Vector3[] quad;
	private Texture t;



	public BlockModelPart(Vector3[] quad,Texture t){
		this.quad=quad;
		this.t=t;

	}



	public void draw(GL2 gl,String part,Vector3 off){
		this.t.enable(gl);
		this.t.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		TextureCoords txc=this.t.getImageTexCoords();
		this.quad[0].add(off).vertex(gl);
		gl.glTexCoord2f(txc.left(),txc.top());
		this.quad[1].add(off).vertex(gl);
		gl.glTexCoord2f(txc.right(),txc.top());
		this.quad[2].add(off).vertex(gl);
		gl.glTexCoord2f(txc.right(),txc.bottom());
		this.quad[3].add(off).vertex(gl);
		gl.glTexCoord2f(txc.left(),txc.bottom());
		gl.glEnd();
	}
}