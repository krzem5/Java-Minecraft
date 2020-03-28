import com.jogamp.opengl.GL2;
import com.krzem.minecraft.Block;
import org.w3c.dom.Element;



public class Stone extends Block{
	public String get_name(){
		return "stone";
	}



	public void update(){

	}



	public void draw(GL2 gl){
		this.draw_model(gl,"main");
	}



	public void _load_extra(Element e){

	}



	public void _export_extra(Element e){

	}
}