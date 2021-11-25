package Taller2;

public class AsignaturaL extends Asignatura{

	public AsignaturaL(String code, String name, int credit, int lvl) {
		super(code,name,credit,lvl);
	}
	@Override
	public boolean addCoruse(Asignatura course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paralelo getParalel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asignaturas getPrerequisits() {
		// TODO Auto-generated method stub
		return null;
	}

}
