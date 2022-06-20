package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import manager.Manager;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
		Manager m = Manager.getInstance();
		m.init();
	}

}
