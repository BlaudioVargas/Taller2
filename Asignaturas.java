package Taller2;

public class Asignaturas {
	private Asignatura item;
	private Asignaturas next;
	
	public Asignaturas(Asignatura item) {
		this.item=item;
		this.next=null;
	}
	
	public boolean setNext(Asignaturas next) {
		this.next=next;
		return true;
	}
	
	public Asignatura getItem() {
		return this.item;
	}
	
	public Asignaturas getNext() {
		return this.next;
	}
}
