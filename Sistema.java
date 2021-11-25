package Taller2;

public class Sistema implements SistemaIMPL{

	private Usuarios users;
	private Asignaturas courses;
	@Override
	public boolean addUser(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCourse(Asignatura course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addParalel(Asignatura course, Paralelo paralel, Profesor teacher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enrollParalel(Estudiante student, Paralelo paralel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean desertParalel(Estudiante student, Paralelo paralel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario searchUser(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asignatura searchCourse(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profesor searchTeacher(String rut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean closeSemester() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asignatura getCourse() {
		// TODO Auto-generated method stub
		return null;
	}

}
