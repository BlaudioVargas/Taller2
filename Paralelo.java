package Taller2;

import java.util.ArrayList;


public class Paralelo {
	private int numero;
	private int total;
	private Asignatura itemA;
	private Paralelos itemB;
	private ArrayList<Paralelos> itemC;
	private Paralelo next;
	
	public Paralelo(Asignatura itemA, Paralelos itemB, int numero) {
		this.itemA=itemA;
		this.itemB=itemB;
		this.numero =numero;
		this.total=0;
		//this.itemC=itemC;
		this.next=null;
	}
	
	public boolean addStudent (Paralelos itemC) {
		return true;
	}
	
	public boolean setNext(Paralelo next) {
		this.next=next;
		return true;
	}
	
	public Asignatura getItemA() {
		return this.itemA;
	}
	
	public Paralelos getItemB() {
		return this.itemB;
	}
	
	public ArrayList<Paralelos> getItemC() {
		return this.itemC;
	}
	
	public Paralelo getNext() {
		return this.next;
	}
	
	public boolean removeStudent (String rut) {
		return true;
	}
}
