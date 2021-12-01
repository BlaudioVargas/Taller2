package Taller2;

public class Profesor  extends Usuario{
	private int salary;
	
	public Profesor(String rut, String email, String password, int level) {
		super(rut,email,password);
		this.salary=level;
	}

	public String getTipo() {
		return "Profesor";
	}

	public int getInt() {
		return this.salary;
	}

	public boolean addParalel(Paralelos paralel) {
		int total = 0;
		if(this.paralel==null) {
			this.paralel= paralel;
		}
		else {
			Paralelos indice = this.paralel;
			while(indice.getNext()!=null) {
				total ++;
				indice=indice.getNext();
			}
			if(total<4) {
				indice.setNext(paralel);
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public boolean removeParalel(String code) {
		//no tiene uso en esta clase
		return false;
	}
}
