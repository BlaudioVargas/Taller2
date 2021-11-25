package Taller2;

public class Profesor  extends Usuario{
	private int salary;
	
	public Profesor(String rut, String email, String password, Paralelos paralel, int level) {
		super(rut,email,password,paralel);
		this.salary=level;
	}
	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return "Profesor";
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return this.salary;
	}
}
