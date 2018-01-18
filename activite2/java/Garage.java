package it.activite2.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import it.activite2.java.vehicule.Vehicule;
/**
 * class garage to add and store each created car
 * @author Furanku
 */

public class Garage {
	private List<Vehicule> voitures ;
	private String file = "file_garage/garage.txt";
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	/**
	 * constructor of the class 
	 * the file where we will store each new car is created here 
	 */
	@SuppressWarnings("unchecked")
	public Garage() {
		
		voitures = new ArrayList<>();//creation of a new ArrayList
		
		//catching the exceptions if the file doesn't exist
		try {
			ois = new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream(
								new File(file))));
 		  this.voitures = (List<Vehicule>) ois.readObject(); //reading the file if it exists 
 			ois.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			System.err.print("Aucune voiture sauvegardée !\n"); //print this if the file doesn't exist 
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * this method add each new car with all the options
	 * in this method we will also save each car in the file 
	 * @param voiture
	 * 			 	 this car 
	 */
	public void addVoiture(Vehicule voiture) {
		this.voitures.add(voiture); //adding a new car
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(
							new File(file))));
			oos.writeObject(this.voitures); // writing in the file 
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * overriding the toString method of the Garage Class
	 * final visualization
	 */
	@Override
	public String toString() {
			
		String affichage = "***************************\n";
			   affichage += "*  Garage OpenClassrooms  *\n";
		       affichage += "***************************\n";
		
		 for (Vehicule vehicule : voitures) {
			 affichage += "+ Voiture " 
					   +vehicule.toString()
					   +"d'une valeur totale de " 
					   + vehicule.getPrix() 
					   + " €\n";
		 }
		 return affichage;
	}
}
