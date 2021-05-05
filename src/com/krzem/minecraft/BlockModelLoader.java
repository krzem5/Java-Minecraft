package com.krzem.minecraft;



import java.lang.Exception;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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



public class BlockModelLoader extends Constants{
	private static Map<String,BlockModel> _mmap;



	public static void _load_all(){
		for (File fp:new File(MODEL_DIR).listFiles()){
			try{
				Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fp.getAbsolutePath());
				doc.getDocumentElement().normalize();
				Element root=doc.getDocumentElement();
				for (Element p:IO._get_children_by_tag_name(root,"parents")){
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}



	private static ArrayList<Element> _get_children_by_tag_name(Element p,String tn){
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
}
