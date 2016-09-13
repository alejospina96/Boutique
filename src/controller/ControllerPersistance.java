package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import mundo.Boutique;

public class ControllerPersistance {
	public final static String RUTA_PROP = "./data/boutique.txt";
	private Boutique boutiqueCargada;
	private String rutaPers, rutaBackup;
	public Boutique darBoutiqueCargada() {
		return boutiqueCargada;
	}

	public ControllerPersistance() {
		cargarInformacion();
	}
	public ControllerPersistance(Boutique b){
		
		try {
			File prop = new File(RUTA_PROP);
			BufferedReader br = new BufferedReader(new FileReader(prop));
			rutaPers = br.readLine()+"/boutique.pers";
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boutiqueCargada = b;
	}
	private void cargarInformacion() {
		try {
			File prop = new File(RUTA_PROP);
			BufferedReader br = new BufferedReader(new FileReader(prop));
			rutaPers = br.readLine()+"/boutique.pers";
			rutaBackup = br.readLine()+"/boutique.backup";
			File aux = new File(rutaPers);
			if(!aux.exists()){
				boutiqueCargada = new Boutique();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(aux));
				oos.writeObject(boutiqueCargada);
				aux = new File(rutaBackup);
				oos = new ObjectOutputStream(new FileOutputStream(aux));
				oos.writeObject(boutiqueCargada);
				oos.close();
				return;
			}
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream(rutaPers));
			boutiqueCargada = (Boutique) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void guardarInfo(){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(rutaPers));
			oos.writeObject(boutiqueCargada);
			int dia = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
			if(dia==28){
				oos = new ObjectOutputStream(new FileOutputStream(rutaBackup));
				oos.writeObject(boutiqueCargada);
			}
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
