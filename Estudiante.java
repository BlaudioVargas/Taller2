package Taller2;

public class Estudiante extends Usuario{
	private int level;
	
	public Estudiante(String rut, String email, String password, int level) {
		super(rut,email,password);
		this.level=level;
	}

	public String getTipo() {
		return "Estudiante";
	}

	public int getInt() {
		return this.level;
	}
	
	public boolean addParalel(Paralelos paralel){
		int total = 0;
		if(this.paralel==null) {
			this.paralel= paralel;
		}
		else {
			Paralelos indice = this.paralel;
			while(indice.getNext()!=null) {
				total += indice.getItemA().getItemA().getCredit();
				indice=indice.getNext();
			}
			if((total+paralel.getItemA().getItemA().getCredit())<40) {
				indice.setNext(paralel);
			}
			else {
				return false;
			}
		}
		return true;
	}

}
