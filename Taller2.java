package Taller2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Taller2 {

	public static void main(String [] args) throws IOException {
		SistemaIMPL system = new Sistema();
		if(cargarAsignaturas(system)) {
			if(cargarProfesor(system)) {
				if(cargarParalelos(system)) {
					if(cargarEstudiante(system)) {
						//System.out.println("EXITO AL CARGAR");
						//String demo ="demo,demo \ndemo\ndemo";
						//System.out.println(demo);
						system.closeSemester();
						guardarAsignatura(system);
						guardarProfesor(system);
						guardarParalelo(system);
						guardarEstudiante(system);
					}
				}
			}
		}
	}
	
	/*	esta parte ordena la fecha y redirecciona al usuario dependiendo de la fecha (no se  en que parte del inicio de sesion deberia ponerlo xd)
	public static boolean inicioSecion(SistemaIMPL system) {
		var nc= new Scanner(System.in);
		System.out.println("INICIO DE SECION");
		System.out.println("Indique su correo");
		System.out.println("Se finaliza con 0:");
		String seleccion = nc.nextLine();
				
		if((seleccion).equals("0")){
			System.out.println("Se a finalizado la operacion");
				
		}
		var sc= new Scanner(System.in);
		System.out.println("indique la fecha actual");
		String fechahoy = sc.nextLine();
		
		// 20-02-2021
		String anho = fechahoy.substring(6, 10);
		String mes = fechahoy.substring(3, 5);
		String dia = fechahoy.substring(0, 2);
		String nFechaHoy = anho+"-"+mes+"-"+dia;
		//System.out.println(nFechaHoy); 
		int result = funcionalidad(nFechaHoy);
		if (result==1) {
			//System.out.println("inicio de semestre");
			menuInicioSem();
		}
		if (result==2) {
			//System.out.println("mitad de semestre");
			menuMitadSem();
		}
		if (result==3) {
			//System.out.println("final de semestre");
			menuFinalSem();
		}
		if (result==4) {
			//System.out.println("cierre de semestre");
			menucloseSemester();
		}
		if (result==0) {
			System.out.println("Disfrute sus vacaciones");
		}
		*/
	
	public static int funcionalidad(String fecha){ //establece los limites de las fechas de sus respectivos menus
		 LocalDate fechaHoy = LocalDate.parse(fecha); 
		
		
	    //inicio de semestre
		
	    LocalDate start = LocalDate.parse("2021-03-10"),
	      end   = LocalDate.parse("2021-05-02");
	
	    // Busqueda de la fecha
	    LocalDate next = start.minusDays(1);
	    while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
	        if (fechaHoy.equals(	next)) {
	        	return 1;
	        }
	    }
	    //mitad de semestre
		
	     start = LocalDate.parse("2021-05-03");
	     end   = LocalDate.parse("2021-07-11");
	
	    // Busqueda de la fecha
	    next = start.minusDays(1);
	    while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
	        if (fechaHoy.equals(next)) {
	        	return 2;
	        }
	    }
	    //final de semestre
		
	     start = LocalDate.parse("2021-07-12");
	     end   = LocalDate.parse("2021-07-25");
	
	    // Busqueda de la fecha
	    next = start.minusDays(1);
	    while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
	        if (fechaHoy.equals(next)) {
	        	return 3;
	        }
	    }
	    //cierre de semestre
		
	     start = LocalDate.parse("2021-07-26");
	     end   = LocalDate.parse("2021-07-26");
	
	    // Busqueda de la fecha
	    next = start.minusDays(1);
	    while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
	        if (fechaHoy.equals(next)) {
	        	return 4;
	        }
	    }
	    return 0;
	}
	
	private static void menuInicioSem() {
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("---Menú inicio de semestre---");
		System.out.println("1) inscripcion de asignaturas");
		System.out.println("1) eliminacion de asignaturas");
		System.out.println("0)Salir");
		String respuesta = sc.nextLine();
		
		if(respuesta.equals("1")) {
			
		}
		else if(respuesta.equals("2")) {
			
		}
		else if(respuesta.equals("0")) {
			//valido = false;
			System.out.println("Saliendo de menú inicio de semestre");
		}
		else {
			System.out.println("ERROR RESPUESTA NO VALIDA");
		}
	}
	
	private static void menuMitadSem() {
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("---Menú mitad de semestre---");
		System.out.println("1) eliminar asignaturas");
		System.out.println("0)Salir");
		String respuesta = sc.nextLine();
		
		if(respuesta.equals("1")) {
			
		}
		
		else if(respuesta.equals("0")) {
			//valido = false;
			System.out.println("Saliendo de menú mitad de semestre");
		}
		else {
			System.out.println("ERROR RESPUESTA NO VALIDA");
		}
	}
	
	private static void menuFinalSem() {
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("---Menú final de semestre---");
		System.out.println("1) ingreso de nota final ");
		System.out.println("0)Salir");
		String respuesta = sc.nextLine();
		
		if(respuesta.equals("1")) {
			
		}
		
		else if(respuesta.equals("0")) {
			//valido = false;
			System.out.println("Saliendo de menú final de semestre");
		}
		else {
			System.out.println("ERROR RESPUESTA NO VALIDA");
		}
	}
	
	private static void menucloseSemester() {
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("---Menú cierre de semestre---");
		System.out.println("1) cierre de semestre ");
		System.out.println("0)Salir");
		String respuesta = sc.nextLine();
		
		if(respuesta.equals("1")) {
			system.closeSemester();
		}
		
		else if(respuesta.equals("0")) {
			//valido = false;
			System.out.println("Saliendo de menú cierre de semestre");
		}
		else {
			System.out.println("ERROR RESPUESTA NO VALIDA");
		}
	}
	
	
	private static boolean cargarAsignaturas(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/asignaturas.txt"); 
			FileReader text = new FileReader (archivo); 
			BufferedReader reader = new BufferedReader(text); 
			String linea;
			String[] partes;
			while((linea = reader.readLine())!=null){
				partes = linea.split(",");
				String codigo = partes[0];
				String nombre = partes[1];
				int credito =Integer.parseInt(partes[2]);
				String tipo = partes[3];
				int nivel =Integer.parseInt(partes[4]);
				Asignatura nuevo;
				if(tipo.equals("obligatoria")) {
					nuevo = new AsignaturaO(codigo, nombre, credito, nivel);
				}
				else {
					nuevo = new AsignaturaL(codigo, nombre, credito, nivel);
				}
				system.addCourse(nuevo);
			}
			reader.close();
			return cargarAsignaturasPrerequisitos(system);
		}
		catch (Exception e) {
			System.out.println("ARCHIVO DE ASIGNATURAS CORRUPTO");
			return false;
		}
	}
	
	private static boolean cargarAsignaturasPrerequisitos(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/asignaturas.txt"); 
			FileReader text = new FileReader (archivo); 
			BufferedReader reader = new BufferedReader(text); 
			String linea;
			String[] partes;
			Asignatura temp;
			while((linea = reader.readLine())!=null){
				partes = linea.split(",");
				String codigo = partes[0];
				String tipo = partes[3];
				temp = system.searchCourse(codigo);
				if(temp!=null && tipo.equals("obligatoria")) {
					int tamano = partes.length;
					for(int i =6;i<tamano;i++) {
						Asignatura preriquisito = system.searchCourse(partes[i]);
						if(preriquisito!=null) {
							temp.addCoruse(preriquisito);
						}
					}
				}
			}
			reader.close();
			return true;
		}
		catch (Exception e) {
			System.out.println("ARCHIVO DE ASIGNATURAS 2 CORRUPTO");
			return false;
		}
	}
	
	private static boolean cargarProfesor(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/profesores.txt"); 
			FileReader text = new FileReader (archivo); 
			BufferedReader reader = new BufferedReader(text); 
			String linea;
			String[] partes;
			while((linea = reader.readLine())!=null){
				partes = linea.split(",");
				String rut = partes[0];
				String correo = partes[1];
				String contraseña = partes[2];
				int salario =Integer.parseInt(partes[3]);
				Usuario nuevo = new Profesor(rut,correo,contraseña,salario);
				system.addUser(nuevo);
			}
			reader.close();
			return true;
		}
		catch (Exception e) {
			System.out.println("ARCHIVO DE PROFESORES CORRUPTO");
			return false;
		}
	}
	
	private static boolean cargarParalelos(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/paralelos.txt"); 
			FileReader text = new FileReader (archivo); 
			BufferedReader reader = new BufferedReader(text); 
			String linea;
			String[] partes;
			while((linea = reader.readLine())!=null){
				partes = linea.split(",");
				int numero =Integer.parseInt(partes[0]);
				String codigo = partes[1];
				String rut = partes[2];
				Asignatura course = system.searchCourse(codigo);
				Profesor teacher = system.searchTeacher(rut);
				if(course!=null && teacher !=null) {
					Paralelos paralel = new Paralelos(null,teacher);
					course.addParalel(paralel, numero);
				}
			}
			reader.close();
			return true;
		}
		catch (Exception e) {
			System.out.println("ARCHIVO DE PARALELOS CORRUPTO");
			return false;
		}
	}
	
	private static boolean cargarEstudiante(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/estudiantes.txt"); 
			FileReader text = new FileReader (archivo); 
			BufferedReader reader = new BufferedReader(text); 
			String linea;
			String[] partes;
			while((linea = reader.readLine())!=null){
				partes = linea.split(",");
				String rut = partes[0];
				String correo = partes[1];
				int nivel =Integer.parseInt(partes[2]);
				String contraseña = partes[3];
				Usuario nuevo = new Estudiante(rut,correo,contraseña,nivel);
				system.addUser(nuevo);
				if((linea = reader.readLine())!=null) {
					int total =Integer.parseInt(linea);
					for(int i=0;i<total;i++) {
						linea = reader.readLine();
						partes = linea.split(",");
						String codigo = partes[0];
						Float nota = Float.parseFloat(partes[1]);
						Asignatura course = system.searchCourse(codigo);
						if(course!=null) {
							Paralelo  paralel =course.getParalel();
							int valor = paralel.getTotal();
							while(valor>=100 && paralel!=null) {
								paralel =paralel.getNext();
								valor = paralel.getTotal();
							}
							if(paralel!=null) {
								system.enrollParalel((Estudiante)nuevo, paralel);
								Paralelos indice = nuevo.getParalel();
								while(indice!=null) {
									if(indice.getItemA().getItemA().getCode().equals(codigo)) {
										indice.setNota(nota);
										break;
									}
									indice=indice.getNext();
								}
							}
						}
					}
				}
			}
			reader.close();
			return true;
		}
		catch (Exception e) {
			System.out.println("ARCHIVO DE ESTUDIANTES CORRUPTO");
			return false;
		}
	}
	
	private static void guardarAsignatura(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/asignaturas2.txt"); 
			FileWriter text = new FileWriter (archivo); 
			BufferedWriter wr = new BufferedWriter(text);
			Asignaturas indice = system.getCourse();
			while(indice!=null) {
				String codigo = indice.getItem().getCode();
				String nombre = indice.getItem().getName();
				int creditos = indice.getItem().getCredit();
				String tipo = indice.getItem().getType();
				int level = indice.getItem().getLevel();
				String extra="";
				if(tipo.equals("obligatoria")) {
					int contador =0;
					Asignaturas indixe = indice.getItem().getPrerequisits();
					while(indixe!=null) {
						extra=extra+","+indixe.getItem().getCode();
						indixe=indixe.getNext();
						contador++;
					}
					extra = ","+contador+extra;
				}
				
				wr.write(codigo+","+nombre+","+creditos+","+tipo+","+level+extra+"\n");
				indice=indice.getNext();
			}
			wr.close();
		}
		catch (Exception e) {
			System.out.println("ARCHIVO ASIGNATURA NO GUARDADO");
		}
	}
	
	private static void guardarProfesor(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/profesores2.txt"); 
			FileWriter text = new FileWriter (archivo); 
			BufferedWriter wr = new BufferedWriter(text);
			Usuarios indice = system.getUsers();
			while(indice!=null) {
				String tipo = indice.getItem().getTipo();
				if(tipo.equals("Profesor")) {
					String rut = indice.getItem().getRut();
					String email = indice.getItem().getEmail();
					String password = indice.getItem().getPassword();
					int salario = indice.getItem().getInt();
					wr.write(rut+","+email+","+password+","+salario+"\n");
				}
				indice=indice.getNext();
			}
			wr.close();
		}
		catch (Exception e) {
			System.out.println("ARCHIVO ASIGNATURA NO GUARDADO");
		}
	}
	
	private static void guardarParalelo(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/paralelos2.txt"); 
			FileWriter text = new FileWriter (archivo); 
			BufferedWriter wr = new BufferedWriter(text);
			Asignaturas indice = system.getCourse();
			while(indice!=null) {
				String codigo = indice.getItem().getCode();
				Paralelo indixe = indice.getItem().getParalel();
				while(indixe!= null) {
					int numeor = indixe.getNumero();
					String rut = indixe.getItemB().getItemB().getRut();
					wr.write(numeor+","+codigo+","+rut+"\n");
					indixe=indixe.getNext();
				}
				indice=indice.getNext();
			}
			wr.close();
		}
		catch (Exception e) {
			System.out.println("ARCHIVO ASIGNATURA NO GUARDADO");
		}
	}
	
	private static void guardarEstudiante(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/estudiantes2.txt"); 
			FileWriter text = new FileWriter (archivo); 
			BufferedWriter wr = new BufferedWriter(text);
			Usuarios indice = system.getUsers();
			while(indice!=null) {
				String tipo = indice.getItem().getTipo();
				if(tipo.equals("Estudiante")) {
					String rut = indice.getItem().getRut();
					String email = indice.getItem().getEmail();
					String password = indice.getItem().getPassword();
					int nivel = indice.getItem().getInt();
					wr.write(rut+","+email+","+nivel+","+password+"\n");
					Paralelos indixe = indice.getItem().getParalel();
					int contador=0;
					String datos="";
					while(indixe!=null) {
						datos=datos+"\n"+indixe.getItemA().getItemA().getCode()+","+indixe.getNota();
						indixe=indixe.getNext();
						contador++;
					}
					wr.write(contador+datos+"\n");
				}
				indice=indice.getNext();
			}
			wr.close();
		}
		catch (Exception e) {
			System.out.println("ARCHIVO ESTUDIANTES NO GUARDADO");
		}
	}
}
