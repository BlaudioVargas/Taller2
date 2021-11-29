package Taller2;

public abstract class Usuario {
	private String rut;
	private String email;
	private String password;
	protected Paralelos paralel;
	
	public Usuario (String rut, String email, String password) {
		this.rut=rut;
		this.email=email;
		this.password=password;
		this.paralel=null;
	}
	
	abstract public boolean addParalel(Paralelos paralel);
	
	public String getRut(){return this.rut;}
	public String getEmail(){return this.email;}
	public String getPassword(){return this.password;}
	public Paralelos getParalel(){return this.paralel;}
	
	abstract public String getTipo() ;
	abstract public int getInt() ;

}
