package RecyclingMachine.UserInterface;

import java.util.ArrayList;

import RecyclingMachine.Residues.Residue;

public class User {
	// Attributes
	private String name;
	private int number;
	private double balance;
	private ArrayList<Residue> residues;

	// Getters
	public String getName() { return name; }
	public int getNumber() { return number; }
	public double getBalance() { return balance; }
	public ArrayList<Residue> getResidues() { return residues; }

	// Setters
}