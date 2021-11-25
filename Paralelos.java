package Taller2;

import java.util.ArrayList;

public class Paralelos {
	private Paralelo itemA;
	private Usuario itemB;
	private Paralelos next;
	private int nota;
	
	public Paralelos(Paralelo itemA, Usuario itemB) {
		this.itemA=itemA;
		this.itemB=itemB;
		this.nota=0;
		//this.itemC=itemC;
		this.next=null;
	}
	
	public boolean setNota(int nota) {
		this.nota=nota;
		return true;
	}
	
	public boolean setNext(Paralelos next) {
		this.next=next;
		return true;
	}
	
	public Paralelo getItemA() {
		return this.itemA;
	}
	
	public Usuario getItemB() {
		return this.itemB;
	}
	
	public int getNota() {
		return this.nota;
	}
	
	public Paralelos getNext() {
		return this.next;
	}
}
