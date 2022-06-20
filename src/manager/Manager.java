package manager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import dao.Dom;
import dao.Leer;
import dao.Sax;
import model.Jugador;

public class Manager{
	private static Manager manager;
	private SAXParserFactory spf;
	private SAXParser sp;
	private Dom d;
	private ArrayList<Jugador> j;
	private ArrayList<String[][]> mapas;
	
	private Manager ( ) throws TransformerConfigurationException, ParserConfigurationException {
		spf = SAXParserFactory.newInstance();
		sp = null;
		d = new Dom();
		j = new ArrayList<Jugador>();
		mapas = new ArrayList <String[][]>();
	}
	
	public static Manager getInstance ( ) throws TransformerConfigurationException, ParserConfigurationException {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}

	public void init() throws ParserConfigurationException, TransformerException, IOException {
		sax();
		leerLineas();
		d.generarDocumento(j, mapas);
	}

	private void sax() throws ParserConfigurationException {
		File entrada = new File("Data/entrada.xml");
        Sax handler = new Sax(); 
        try {
        	sp = spf.newSAXParser();
			sp.parse(entrada, handler);
			j = handler.getJ();
			for(Jugador player : j) {
				System.out.println(player);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void leerLineas() {
		Leer l = new Leer("Data/pantallas.txt");
		String linea = "";
		String[][] array = new String[5][10];
		int contador = 0;
		while(!(linea = l.pantallas()).equals("#")) {
			//System.out.println(linea);
			if(!linea.contains("#")) {
				//OPERACIONES AÑADIR FILAS
				String[] split = linea.split(" ");
				array[contador] = split;
				contador++;
			}else {
				//REINICIO VARIABLES Y AÑADO AL ARRAYLIST
				if (contador > 0) {
					mapas.add(array);
				}
				array = new String[5][10];
				contador = 0;					
			}
		}
		mapas.add(array);
		l.bfClose();
		for(String[][] m : mapas) {
			for(String[] s : m) {
				for(String x : s) {
					System.out.print(x + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
}
