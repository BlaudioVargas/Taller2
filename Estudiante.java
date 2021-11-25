package Taller2;

public class Estudiante extends Usuario{
	private int level;
	
	public Estudiante(String rut, String email, String password, Paralelos paralel, int level) {
		super(rut,email,password,paralel);
		this.level=level;
	}
	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return "Estudiante";
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return this.level;
	}

}
