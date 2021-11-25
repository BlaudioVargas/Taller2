package Taller2;

public abstract class Usuario {
	private String rut;
	private String email;
	private String password;
	private Paralelos paralel;
	
	public Usuario (String rut, String email, String password, Paralelos paralel) {
		this.rut=rut;
		this.email=email;
		this.password=password;
		this.paralel=paralel;
	}
	
	public String getRut(){return this.rut;}
	public String getEmail(){return this.email;}
	public String getPassword(){return this.password;}
	public Paralelos getParalel(){return this.paralel;}
	
	abstract public String getTipo() ;
	abstract public int getInt() ;

}
