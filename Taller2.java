package Taller2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**

 *@author BLAUDIO VARGAS / JORGE CORTES

 */

public class Taller2 {

	public static void main(String [] args) throws IOException, ParseException {
		SistemaIMPL system = new Sistema();
		if(cargarAsignaturas(system)) {
			if(cargarProfesor(system)) {
				if(cargarParalelos(system)) {
					if(cargarEstudiante(system)) {
						boolean exito=true;
						while(exito) {
							exito=inicioSecion(system);
						}
						//guardarAsignatura(system);
						//guardarProfesor(system);
						//guardarParalelo(system);
						guardarEstudiante(system);
					}
				}
			}
		}
	}
	
	private static boolean cargarAsignaturas(SistemaIMPL system) {//load asignaturas.txt @return false in case of an error while loading
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
	
	private static boolean cargarAsignaturasPrerequisitos(SistemaIMPL system) {//load asignaturas.txt @return false in case of an error while loading
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
			System.out.println("ARCHIVO DE ASIGNATURAS PREREQUISITO CORRUPTO");
			return false;
		}
	}
	
	private static boolean cargarProfesor(SistemaIMPL system) {//load profesores.txt @return false in case of an error while loading
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
	
	private static boolean cargarParalelos(SistemaIMPL system) {//load paralelos.txt @return false in case of an error while loading
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
	
	private static boolean cargarEstudiante(SistemaIMPL system) {//load estudiantes.txt @return false in case of an error while loading
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
	
	/*
	private static void guardarAsignatura(SistemaIMPL system) {
		try {
			File archivo = new File ("src/Taller2/asignaturas.txt"); 
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
			File archivo = new File ("src/Taller2/profesores.txt"); 
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
			File archivo = new File ("src/Taller2/paralelos.txt"); 
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
	*/
	
	private static void guardarEstudiante(SistemaIMPL system) {//save estudiantes.txt
		try {
			File archivo = new File ("src/Taller2/estudiantes.txt"); 
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
	
	//Codigo de emergencia
	private static boolean inicioSecion(SistemaIMPL system) throws ParseException {//Manage the start of the secion @return false to finalize the operation
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("INICIO DE SECION");
		System.out.println("Indique el correo del usuario(Sale con 0)");
		String seleccion = sc.nextLine();
		if(seleccion.equals(0) ) {
			return false;
		}
		else if (seleccion.equals("Admin")) {
			System.out.println("Indique la contraseña");
			String password = sc.nextLine();
			if(password.equals("GHI_789")) {
				inicioSecionFaseII( system, null);
			}
			else {
				System.out.println("PASSWORD INVALIDO");
			}
		}
		else {
			Usuario user = system.searchUser(seleccion);
			if(user!=null) {
				System.out.println("Indique la contraseña");
				String password = sc.nextLine();
				if(password.equals(user.getPassword())) {
					inicioSecionFaseII( system, user);
				}
				else {
					System.out.println("PASSWORD INVALIDO");
				}
			}
			else {
				System.out.println("USUARIO INVALIDO");
				System.out.println("SI DESEA SALIR ESCRIBA: salir");
				System.out.println("SI DESEA CONTINUAR ESCRIBA CUALQUIER OTRA COSA");
				String respuesta = sc.nextLine();
				if(respuesta.equals("salir")) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void inicioSecionFaseII(SistemaIMPL system,Usuario user) throws ParseException {//user data and date are classified to determine the action of the program to perform
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		String fecha =seleccionFecha();
		if(!fecha.equals(null)) {
			if(user==null) {
				if(fecha.equals("Cierre")){
					boolean exito =system.closeSemester();
					if(exito) {
						System.out.println("EXITO EN CIERRE DE SEMESTRE");
					}
					else {
						System.out.println("FRACASO / NINGUN ESTUDIANTE APROBO EL SEMESTRE");
					}
				}
				else {
					System.out.println("No hay acciones disponibles");
				}
			}
			else{
				if(user.getTipo().equals("Estudiante")) {
					if(fecha.equals("Inicio")) {
						System.out.println("1)Inscribir asignatura");
						System.out.println("2)Eliminar asignatura");
						System.out.println("Indique la opcion a usar");
						String respuesta = sc.nextLine();
						if(respuesta.equals("1")) {
							inscribirAsignatura( system, user);
						}
						else if(respuesta.equals("2")) {
							eliminarAsignatura(system, user);
						}
						else {
							System.out.println("ERROR RESPUESTA INVALIDA");
						}
					}
					else if(fecha.equals("Medio")) {
						eliminarAsignatura(system, user);
					}
					else {
						System.out.println("No hay acciones disponibles");
					}
				}
				else {
					if(fecha.equals("Inicio")) {
						chequeoAlumnos(system, user);
					}
					else if(fecha.equals("Final")) {
						ingresoNotas(system, user);
					}
					else {
						System.out.println("No hay acciones disponibles");
					}
				}
			}
		}
		else{
			System.out.println("Disfrute sus vacaciones");
		}
	}
	
	private static void ingresoNotas(SistemaIMPL system, Usuario user) {//For the teacher, the grade of a certain student is entered
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("INGRESO DE ALUMONS");
		Paralelos indice =user.getParalel();
		while (indice!=null) {
			System.out.println("Asignatura: "+indice.getItemA().getItemA().getCode()+"/ Paralelo: "+indice.getItemA().getNumero());
			indice = indice.getNext();
		}
		System.out.println("Indique el codigo de la asignatura del estudiante");
		String codigo = sc.nextLine();
		Asignatura course= system.searchCourse(codigo);
		if(course!=null) {
			System.out.println("Indique el RUT del estudiante");
			String rut = sc.nextLine();
			indice =user.getParalel();
			boolean exito=false;
			while (indice!=null) {
				if(codigo.equals(indice.getItemA().getItemA().getCode())) {
					ArrayList<Paralelos> Alumnos=indice.getItemA().getItemC();
					int total=Alumnos.size();
					for(int i=0;i<total;i++) {
						Paralelos temp=Alumnos.get(i);
						if(rut.equals(temp.getItemB().getRut())) {
							exito=true;
							try {
								System.out.println("Indique la nota del estudiante");
								Float nota=Float.parseFloat(sc.nextLine());
								temp.setNota(nota);
							}
							catch (Exception e) {
								System.out.println("ERROR NOTA INVALIDA");
							}
							break;
						}
					}
					if(!exito) {
						System.out.println("ERROR NO SE ECONTRO ESTUDIANTE AL ESTUDIANTE EN EL PARALELO");
					}
					exito=true;
					break;
				}
				if(!exito) {
					System.out.println("ERROR LA ASIGNATURA NO ESTA INSCRITA");
				}
				indice = indice.getNext();
			}
		}
		else {
			System.out.println("ERROR NO EXISTE LA ASIGNATURA");
		}
	}

	private static void chequeoAlumnos(SistemaIMPL system, Usuario user) {//For the teacher, the data of the students of the determined signature are observed
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("CHEQUEO DE ALUMONS");
		Paralelos indice =user.getParalel();
		while (indice!=null) {
			System.out.println("Asignatura: "+indice.getItemA().getItemA().getCode()+"/ Paralelo: "+indice.getItemA().getNumero());
			indice = indice.getNext();
		}
		System.out.println("Indique el codigo de la asignatura que desea observar");
		String codigo = sc.nextLine();
		Asignatura course= system.searchCourse(codigo);
		if(course!=null) {
			indice =user.getParalel();
			boolean exito=false;
			while (indice!=null) {
				if(codigo.equals(indice.getItemA().getItemA().getCode())) {
					exito=true;
					ArrayList<Paralelos> Alumnos=indice.getItemA().getItemC();
					int total=Alumnos.size();
					for(int i=0;i<total;i++) {
						Paralelos temp=Alumnos.get(i);
						System.out.println("Estudiante: "+temp.getItemB().getRut());
					}
					break;
				}
				if(!exito) {
					System.out.println("ERROR LA ASIGNATURA NO ESTA INSCRITA");
				}
				indice = indice.getNext();
			}
		}
		else {
			System.out.println("ERROR NO EXISTE LA ASIGNATURA");
		}
	}

	private static void eliminarAsignatura(SistemaIMPL system, Usuario user) {//For the student it is disassociated from the indicated course
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("DESERCION DE ASIGNATURA");
		Paralelos indice =user.getParalel();
		if(indice!=null) {
			while (indice!=null) {
				System.out.println("Asignatura: "+indice.getItemA().getItemA().getCode()+"/ Paralelo: "+indice.getItemA().getNumero());
				indice = indice.getNext();
			}
			System.out.println("Indique el codigo de la asignatura que desea eliminar");
			String codigo = sc.nextLine();
			Asignatura course= system.searchCourse(codigo);
			if(course!=null) {
				indice =user.getParalel();
				boolean exito = false;
				while (indice!=null) {
					if(codigo.equals(indice.getItemA().getItemA().getCode())) {
						exito = system.desertParalel((Estudiante)user, indice.getItemA());
					}
					indice = indice.getNext();
				}
				if(exito) {
					System.out.println("Se elimino con exito la asignatura");
				}
				else {
					System.out.println("ERROR NO SE PUDO ELIMINAR LA ASIGNATURA INDICADA");
				}
			}
			else {
				System.out.println("ERROR NO EXISTE LA ASIGNATURA");
			}
		}
		else {
			System.out.println("ERROR EL ALUMNO NO TIENE ASIGNATURAS INSCRITAS");
		}
		
		
	}

	@SuppressWarnings("null")
	private static void inscribirAsignatura(SistemaIMPL system, Usuario user) {//The indicated course is enrolled for the student
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		System.out.println("INSCRIPCION DE ASIGNATURA");
		Asignaturas indice = system.getCourse();
		while(indice!=null) {
			String codigo = indice.getItem().getCode();
			String nombre = indice.getItem().getName();
			int creditos = indice.getItem().getCredit();
			String tipo = indice.getItem().getType();
			int level = indice.getItem().getLevel();
			String extra="/ Prerequisitos:";
			if(tipo.equals("obligatoria")) {
				Asignaturas indixe = indice.getItem().getPrerequisits();
				while(indixe!=null) {
					extra=extra+" - "+indixe.getItem().getCode();
					indixe=indixe.getNext();
				}
				System.out.println("Codigo: "+codigo+" / Nombre: "+nombre+" / Creditos: "+creditos+" / Tipo: "+tipo+" / Nivel: "+level+extra);
			}
			else {
				System.out.println("Codigo: "+codigo+" / Nombre: "+nombre+" / Creditos: "+creditos+" / Tipo: "+tipo+" / Creditos Prerequisitos: "+level);
			}
			
			indice=indice.getNext();
		}
		System.out.println("Indique el codigo de la asignatura que desea inscribir");
		String codigo = sc.nextLine();
		Asignatura course= system.searchCourse(codigo);
		if(course!=null) {
			Paralelo indixe = course.getParalel();
			while(indixe!=null) {
				int total = indixe.getTotal();
				if(total<100) {
					System.out.println("Paralelo: "+indixe.getNumero());
				}
				indixe=indixe.getNext();
			}
			System.out.println("Indique el numero del paralelo de la asignatura que desea inscribir");
			String paralelo = sc.nextLine();
			indixe = course.getParalel();
			boolean exito = false;
			while(indixe!=null) {
				int total = indixe.getTotal();
				String numero =""+indixe.getNumero();
				if(total<100 && paralelo.equals(numero)) {
					system.enrollParalel((Estudiante)user, indixe);
					exito=true;
					break;
				}
				indixe=indixe.getNext();
			}
			if(exito) {
				System.out.println("Se inscribio al estudainte a la asignatura : " +indice.getItem().getName()+" en el paralelo "+paralelo);
			}
			else {
				System.out.println("ERROR NO SE PUDO INSCRIBIR LA ASIGNATURA");
			}
		}
		else {
			System.out.println("CODIGO INVALIDO");
		}
	}

	private static String seleccionFecha() throws ParseException {//The period is determined by the given date
		@SuppressWarnings("resource")
		var sc= new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		System.out.println("Indique el dia");
		String dia = sc.nextLine();
		System.out.println("Indique el mes");
		String mes = sc.nextLine();
		Date inicioSemestreA = dateFormat.parse("2021-03-07");//03-08-2021
		Date inicioSemestreB = dateFormat.parse("2021-05-03");//05-02-2021
		
		Date medioSemestreA = dateFormat.parse("2021-05-02");//05-03-2021
		Date medioSemestreB = dateFormat.parse("2021-07-12");//07-11-2021
		
		Date finalSemestreA = dateFormat.parse("2021-07-11");//07-12-2021
		Date finalSemestreB = dateFormat.parse("2021-07-26");//07-25-2021
		
		Date cierreSemestre = dateFormat.parse("2021-07-26");//07-26-2021
		
		try {
	        Date fecha = dateFormat.parse("2021-"+mes+"-"+dia);
	        if(fecha.after(inicioSemestreA) && fecha.before(inicioSemestreB)){
	        	return "Inicio";
	        } 
	        else if(fecha.after(medioSemestreA) && fecha.before(medioSemestreB)){
	        	return "Medio";
	        } 
	        else if(fecha.after(finalSemestreA) && fecha.before(finalSemestreB)){
	        	return "Final";
	        } 
	        else if(fecha.equals(cierreSemestre)){
	        	return "Cierre";
	        } 
	        else {
	        }
	        
		}
		catch (Exception e) {
		}
		System.out.println("FECHA INVALIDA");
		return "null";
	}
	
}
