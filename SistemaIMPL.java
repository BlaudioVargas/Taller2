package Taller2;

interface SistemaIMPL {

	public boolean addUser(Usuario user);
	public boolean addCourse(Asignatura course);
	public boolean addParalel(Asignatura course, Paralelo paralel, Profesor teacher);
	
	public boolean enrollParalel(Estudiante student, Paralelo paralel);
	public boolean desertParalel(Estudiante student, Paralelo paralel);
	
	public Usuario searchUser(String mail);
	public Asignatura searchCourse(String code);
	public Profesor searchTeacher(String rut);
	
	public boolean closeSemester();
	
	public Usuario getUsers();
	public Asignatura getCourse();
}
