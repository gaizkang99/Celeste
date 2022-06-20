package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leer {
	private FileReader fr;
	private BufferedReader bf;
	
	public Leer(String archivo) {
		try {
			this.fr = new FileReader(archivo);
			this.bf = new BufferedReader(this.fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String pantallas() { 
		try {
			return bf.readLine();
		}catch(IOException e){
			System.out.println("El archivo no se puede leer correctamente");	
		}
		return null;
	}
	public void bfClose() {
		try {
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		
}
	
