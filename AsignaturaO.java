package Taller2;

public class AsignaturaO extends Asignatura{

	public AsignaturaO(String code, String name, int credit, int lvl) {
		super(code,name,credit,lvl);
	}
	
	public boolean addCoruse(Asignatura course) {
		if(this.prer==null) {
			this.prer= new Asignaturas(course);
		}
		else {
			Asignaturas indice = this.prer;
			while(indice.getNext()!=null) {
				indice=indice.getNext();
			}
			indice.setNext(new Asignaturas(course));
		}
		return true;
	}

	
	public String getType() {
		return "obligatoria";
	}

	
	public Asignaturas getPrerequisits() {
		return this.prer;
	}
	
	public boolean addParalel(Paralelos paralel, int numero) {
		if(this.paralel==null) {
			this.paralel= new Paralelo(this, paralel, numero);
		}
		else {
			Paralelo indice = this.paralel;
			while(indice.getNext()!=null) {
				indice=indice.getNext();
			}
			indice.setNext(new Paralelo(this, paralel, numero));
		}
		return true;
	}

}
