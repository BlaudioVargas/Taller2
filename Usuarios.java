package Taller2;

public class Usuarios {
	private Usuario item;
	private Usuarios next;
	
	public Usuarios(Usuario item) {
		this.item=item;
		this.next=null;
	}
	
	public boolean setNext(Usuarios next) {
		this.next=next;
		return true;
	}
	
	public Usuario getItem() {
		return this.item;
	}
	
	public Usuarios getNext() {
		return this.next;
	}
}
