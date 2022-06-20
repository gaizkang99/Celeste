package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Jugador;

public class Dom {
	private Document d;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private TransformerFactory tf;
	private Transformer t;
	
	public Dom() throws ParserConfigurationException, TransformerConfigurationException{
		this.dbf = DocumentBuilderFactory.newInstance();
		this.db = dbf.newDocumentBuilder();	
		this.d = db.newDocument();
		this.tf = TransformerFactory.newInstance();
		this.t = tf.newTransformer();
	}
	
	
	public void generarDocumento(ArrayList<Jugador> j, ArrayList<String[][]> m) throws TransformerException, IOException {
		Element pantallas = d.createElement("pantallas");
		d.appendChild(pantallas);
		
		for(int i=0; i<j.size();i++) {
			int lvl=0;
			Element pantalla = d.createElement("pantalla");
			pantalla.setAttribute("nombre", j.get(i).getNombre());
			if(j.get(i).getEstado().equals("pendiente")) {
				lvl = j.get(i).getPantalla()-1;
				pantalla.setAttribute("nivel", String.valueOf(lvl));
			}
			else {
				lvl = j.get(i).getPantalla();
				pantalla.setAttribute("nivel", String.valueOf(lvl));
			}
			pantallas.appendChild(pantalla);
			
			for(int x=0; x<m.get(lvl).length;x++) {
				for(int y=0; y<m.get(lvl)[x].length;y++) {
					Element pixel = d.createElement("pixel");
					pixel.setAttribute("columna", String.valueOf(y));
					pixel.setAttribute("fila", String.valueOf(x));
					pixel.setTextContent(m.get(lvl-1)[x][y]);
					pantalla.appendChild(pixel);
				}	
			}
			Element puntuacion = d.createElement("puntuacion");
			puntuacion.setTextContent(String.valueOf(j.get(i).getPuntuacion()));
			pantalla.appendChild(puntuacion);
		}		
				
		generarXML();
	}
	
	private void generarXML() throws TransformerException, IOException {
		Source source = new DOMSource(d);
		File f = new File("Data/salida.xml");
		FileWriter fw = new FileWriter(f);
		PrintWriter pw = new PrintWriter(fw);
		Result r = new StreamResult(pw);
		t.transform(source, r);
	}
}
