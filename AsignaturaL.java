package Taller2;

public class AsignaturaL extends Asignatura{

	public AsignaturaL(String code, String name, int credit, int lvl) {
		super(code,name,credit,lvl);
	}
	
	public boolean addCoruse(Asignatura course) {
		//Las asignaturas libres no tienen prerequisitos
		return false;
	}

	public String getType() {
		return "opcional";
	}

	public Asignaturas getPrerequisits() {
		//Las asignaturas libres no tienen prerequisitos
		return null;
	}

	public boolean addParalel(Paralelos paralel) {
		if(this.paralel==null) {
			this.paralel= new Paralelo(this, paralel, 1);
			return true;
		}
		return false;
	}

}
