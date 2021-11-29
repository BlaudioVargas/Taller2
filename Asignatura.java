package Taller2;

public abstract class Asignatura {
	private String code;
	private String name;
	private int credit;
	private int lvl;
	protected Paralelo paralel;
	@SuppressWarnings("unused")
	protected Asignaturas prer;
	
	public Asignatura(String code, String name, int credit, int lvl) {
		this.code=code;
		this.name=name;
		this.credit=credit;
		this.lvl=lvl;
		this.paralel=null;
		this.prer=null;
	}
	
	abstract public boolean addCoruse(Asignatura course);
	
	abstract public boolean addParalel(Paralelos paralel) ;
	
	public String getCode() {return this.code;}
	public String getName() {return this.name;}
	public int getCredit() {return this.credit;}
	abstract public String getType() ;
	public int getLevel() {return this.lvl;}
	
	public Paralelo getParalel() {return this.paralel;} 
	abstract public Asignaturas getPrerequisits() ;

}
