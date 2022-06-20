package dao;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Jugador;

public class Sax  extends DefaultHandler{
	private ArrayList <Jugador> j;
	private Jugador player;
	private StringBuilder sb;
	
	
	public Sax() {
		this.j = new ArrayList<Jugador>();
		this.sb = new StringBuilder();
	}
	
		
	public ArrayList<Jugador> getJ() {
		return j;
	}



	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(qName) {
			case "partida":
				player = new Jugador();
				j.add(player);
				player.setNombre(attributes.getValue("jugador"));
				break;
			case "puntuacion":
				sb.delete(0, sb.length());
				break;
			case "pantalla":
				player.setEstado(attributes.getValue("estado"));
				sb.delete(0, sb.length());
				break; 
		}
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		sb.append(ch, start, length);
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch(qName) {
			case "puntuacion":
				player.setPuntuacion(Integer.parseInt(sb.toString()));
				break;
			case "pantalla":
				player.setPantalla(Integer.parseInt(sb.toString().substring(1)));
				break;
		}
	}
}

