package Taller2;


public class Paralelos {
	private Paralelo itemA;
	private Usuario itemB;
	private Paralelos next;
	private float nota;
	
	public Paralelos(Paralelo itemA, Usuario itemB) {
		this.itemA=itemA;
		this.itemB=itemB;
		this.nota=0;
		this.next=null;
	}
	
	public boolean setParalelo(Paralelo itemA) {
		this.itemA=itemA;
		return true;
	}
	
	public boolean setNota(float nota) {
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
	
	public float getNota() {
		return this.nota;
	}
	
	public Paralelos getNext() {
		return this.next;
	}
}
