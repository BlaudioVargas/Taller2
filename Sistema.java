package Taller2;

public class Sistema implements SistemaIMPL{

	private Usuarios users;
	private Asignaturas courses;

	public boolean addUser(Usuario user) {
		if(users==null) {
			users= new Usuarios(user);
		}
		else {
			Usuarios indice = users;
			while(indice.getNext()!=null) {
				indice = indice.getNext();
			}
			indice.setNext(new Usuarios(user));
		}
		return true;
	}

	public boolean addCourse(Asignatura course) {
		if(courses==null) {
			courses= new Asignaturas(course);
		}
		else {
			Asignaturas indice = courses;
			while(indice.getNext()!=null) {
				indice = indice.getNext();
			}
			indice.setNext(new Asignaturas(course));
		}
		return true;
	}

	public boolean addParalel(Asignatura course, int numero, Profesor teacher) {
		Paralelos paralelTeacher = new Paralelos(null,teacher);
		if(teacher.addParalel(paralelTeacher)) {
			course.addParalel(paralelTeacher, numero);
			return true;
		}
		return false;
	}

	public boolean enrollParalel(Estudiante student, Paralelo paralel) {
		Paralelos paralelStudent = new Paralelos(null,student);
		if(student.addParalel(paralelStudent)) {
			if(paralel.addStudent(paralelStudent)) {
				return true;
			}
			else{
				Paralelos indice = student.getParalel();
				while(indice!=null) {
					if(indice.getNext()!=null) {
						if(indice.getNext().getItemA()==null) {
							indice.setNext(indice.getNext().getNext());
						}
					}
					indice = indice.getNext();
				}
			}
		}
		return false;
	}

	public boolean desertParalel(Estudiante student, Paralelo paralel) {
		ArrayList<Paralelos> demo = paralel.getItemC();
		int tamano = demo.size();
		boolean exito = false;
		for (int i =0; i<tamano;i++) {
			if(demo.get(i).getItemB().getRut().equals(student.getRut())) {
				demo.remove(i);
				exito=true;
				break;
			}
		}
		if(exito) {
			student.removeParalel(paralel.getItemA().getCode());
		}
		return false;
	}

	public Usuario searchUser(String mail) {
		Usuarios indice = users;
		while(indice!=null) {
			if(indice.getItem().getEmail().equals(mail)) {
				return indice.getItem();
			}
			indice = indice.getNext();
		}
		return null;
	}

	@Override
	public Asignatura searchCourse(String code) {
		Asignaturas indice = courses;
		while(indice!=null) {
			if(indice.getItem().getCode().equals(code)) {
				return indice.getItem();
			}
			indice = indice.getNext();
		}
		return null;
	}

	public Profesor searchTeacher(String rut) {
		Usuarios indice = users;
		while(indice!=null) {
			if(indice.getItem().getRut().equals(rut)&& indice.getItem().getTipo().equals("Profesor")) {
				return (Profesor) indice.getItem();
			}
			indice = indice.getNext();
		}
		return null;
	}

	public boolean closeSemester() {
		String[] egresados = new String[9999];
		int total =0;
		Usuarios indice = users;
		this.users=null;
		while(indice!=null) {
			if((indice.getItem().getTipo().equals("Estudiante")) && (aproveAll((Estudiante)indice.getItem()))) {
					egresados[total]=indice.getItem().getRut();
					total++;
					Paralelos aux =indice.getItem().getParalel();
					while(aux!=null) {
						desertParalel((Estudiante)indice.getItem(),aux.getItemA());
						aux =indice.getItem().getParalel();
					}
			}
			else {
				addUser(indice.getItem());
			}
			indice = indice.getNext();
		}
		if(total>0) {
			try {
				File archivo = new File ("src/Taller2/egresados.txt"); 
				FileWriter text = new FileWriter (archivo); 
				BufferedWriter wr = new BufferedWriter(text);
				for(int i =0; i<total;i++) {
					wr.write(egresados[i]+"\n");
				}
				wr.close();
				return true;
			}
			catch (Exception e) {
				System.out.println("ARCHIVO NO GUARDADO");
				return false;
			}
		}
		return false;
	}

	public Usuarios getUsers() {
		return this.users;
	}

	public Asignaturas getCourse() {
		return this.courses;
	}
	
	private boolean aproveAll(Estudiante student) {
		Paralelos indice = student.getParalel();
		while(indice!=null) {
			if(indice.getNota()<3.95) {
				return false;
			}
			indice = indice.getNext();
		}
		return true;
	}
}
