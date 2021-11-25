package Taller2;

public abstract class Asignatura {
	private String code;
	private String name;
	private int credit;
	private int lvl;
	private Paralelo paralel;
	private Asignaturas prer;
	
	public Asignatura(String code, String name, int credit, int lvl) {
		this.code=code;
		this.name=name;
		this.credit=credit;
		this.lvl=lvl;
		this.paralel=null;
		this.prer=null;
	}
	
	abstract public boolean addCoruse(Asignatura course);
	
	public boolean addParalel(Paralelos paralel) {
		return true;
	}
	
	public String getCode() {return this.code;}
	public String getName() {return this.name;}
	public int getCredit() {return this.credit;}
	abstract public String getType() ;
	public int getLevel() {return this.lvl;}
	
	abstract public Paralelo getParalel() ;
	abstract public Asignaturas getPrerequisits() ;

}
