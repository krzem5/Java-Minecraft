package com.krzem.minecraft;



import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class IO extends Constants{
	private static String fp="";



	public static void load_from_file(Game game,String fp){
		IO.fp=fp;
		try{
			Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fp);
			doc.getDocumentElement().normalize();
			Element root=doc.getDocumentElement();
			Element cam=IO._get_children_by_tag_name(root,"camera").get(0);
			Vector3 p=IO._xml_to_vector3(IO._get_children_by_tag_name(cam,"pos").get(0));
			Vector3 r=IO._xml_to_vector3(IO._get_children_by_tag_name(cam,"rot").get(0));
			game.cls.cam.x=p.x;
			game.cls.cam.dx=p.x;
			game.cls.cam.y=p.y;
			game.cls.cam.dy=p.y;
			game.cls.cam.z=p.z;
			game.cls.cam.dz=p.z;
			game.cls.cam.rx=r.x;
			game.cls.cam.drx=r.x;
			game.cls.cam.ry=r.y;
			game.cls.cam.dry=r.y;
			game.cls.cam.rz=r.z;
			game.cls.cam.drz=r.z;
			Element wd=IO._get_children_by_tag_name(root,"world-data").get(0);
			for (Element chk:IO._get_children_by_tag_name(wd,"chunk")){
				int cx=Integer.parseInt(chk.getAttribute("x"));
				int cz=Integer.parseInt(chk.getAttribute("z"));
				Chunk c=new Chunk(game.cls,game,cx,cz);
				for (Element blk:IO._get_children_by_tag_name(IO._get_children_by_tag_name(chk,"blocks").get(0),"block")){
					int x=Integer.parseInt(blk.getAttribute("x"));
					int y=Integer.parseInt(blk.getAttribute("z"));
					int z=Integer.parseInt(blk.getAttribute("z"));
					String tp=IO._get_children_by_tag_name(blk,"type").get(0).getTextContent();
					Block b=Block._create(tp,game.cls,game,c,x,y,z);
					b._load_extra(blk);
					c._add_bl(b);
				}
				game.cl.add(c);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	public static void save_to_file(Game game,String fp){
		if (fp.length()==0){
			fp=IO.fp+"";
		}
		try{
			Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root=doc.createElement("minecraft");
			Element cam=doc.createElement("camera");
			cam.appendChild(IO._vector3_to_xml(new Vector3(game.cls.cam.x,game.cls.cam.y,game.cls.cam.z),"pos",doc));
			cam.appendChild(IO._vector3_to_xml(new Vector3(game.cls.cam.rx,game.cls.cam.ry%360,game.cls.cam.rz%360),"rot",doc));
			root.appendChild(cam);
			Element wd=doc.createElement("world-data");
			for (Chunk c:game.cl){
				Element chk=doc.createElement("chunk");
				chk.setAttribute("x",Integer.toString(c.x));
				chk.setAttribute("z",Integer.toString(c.z));
				Element bl=doc.createElement("blocks");
				for (Block b:c.bl){
					Element be=doc.createElement("block");
					be.setAttribute("x",Integer.toString((int)b.pos.x));
					be.setAttribute("y",Integer.toString((int)b.pos.y));
					be.setAttribute("z",Integer.toString((int)b.pos.z));
					Element tp=doc.createElement("type");
					tp.appendChild(doc.createTextNode(b.get_name()));
					be.appendChild(tp);
					b._export_extra(be);
					bl.appendChild(be);
				}
				chk.appendChild(bl);
				wd.appendChild(chk);
			}
			root.appendChild(wd);
			doc.appendChild(root);
			StreamResult o=new StreamResult(new FileWriter(new File(fp)));
			Transformer t=TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT,"yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
			t.setOutputProperty(OutputKeys.VERSION,"1.0");
			t.setOutputProperty(OutputKeys.ENCODING,"utf-8");
			t.transform(new DOMSource(doc),o);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	public static ArrayList<Element> _get_children_by_tag_name(Element p,String tn){
		ArrayList<Element> o=new ArrayList<Element>();
		NodeList cl=p.getChildNodes();
		for (int j=0;j<cl.getLength();j++){
			if (cl.item(j).getNodeType()!=Node.ELEMENT_NODE){
				continue;
			}
			Element e=(Element)cl.item(j);
			if (e.getTagName().equals(tn)){
				o.add(e);
			}
		}
		return o;
	}



	private static Vector3 _xml_to_vector3(Element e){
		return new Vector3(Double.parseDouble(IO._get_children_by_tag_name(e,"x").get(0).getTextContent()),Double.parseDouble(IO._get_children_by_tag_name(e,"y").get(0).getTextContent()),Double.parseDouble(IO._get_children_by_tag_name(e,"z").get(0).getTextContent()));
	}



	private static Element _vector3_to_xml(Vector3 v,String en,Document doc){
		Element e=doc.createElement(en);
		Element x=doc.createElement("x");
		x.appendChild(doc.createTextNode(Double.toString(v.x)));
		e.appendChild(x);
		Element y=doc.createElement("y");
		y.appendChild(doc.createTextNode(Double.toString(v.y)));
		e.appendChild(y);
		Element z=doc.createElement("z");
		z.appendChild(doc.createTextNode(Double.toString(v.z)));
		e.appendChild(z);
		return e;
	}
}
