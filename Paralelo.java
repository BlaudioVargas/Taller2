package Taller2;

import java.util.ArrayList;


public class Paralelo {
	private int numero;
	private int total;
	private Asignatura itemA;
	private Paralelos itemB;//profesor del paralelo
	private ArrayList<Paralelos> itemC;//estudiantes del paralelo
	private Paralelo next;
	
	public Paralelo(Asignatura itemA, Paralelos itemB, int numero) {
		this.itemA=itemA;
		this.itemB=itemB;
		this.numero =numero;
		this.total=0;
		this.itemC= new ArrayList<Paralelos>();
		itemB.setParalelo(this);
		this.next=null;
	}
	
	public boolean addStudent (Paralelos itemC) {
		if(this.total<100) {
			this.itemC.add(itemC);
			itemC.setParalelo(this);
			this.total++;
			return true;
		}
		return false;
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
	
	public int getNumero() {
		return this.numero;
	}
	
	public boolean removeStudent (String rut) {
		for(int i=0; i<this.total;i++) {
			if(this.itemC.get(i).getItemB().getRut().equals(rut)) {
				this.itemC.remove(i);
				this.total--;
				return true;
			}
		}
		return false;
	}
}
