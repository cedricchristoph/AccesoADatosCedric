package es.iespuertodelacruz.cc.institutorest.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import es.iespuertodelacruz.cc.institutorest.dto.AlumnoDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the alumnos database table.
 * 
 */
@Entity
@Table(name="alumnos")
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String apellidos;

	private BigInteger fechanacimiento;

	private String nombre;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="alumno", cascade = CascadeType.ALL)
	private List<Matricula> matriculas;

	public Alumno() {
	}

	public Alumno(AlumnoDTO dto) {
		this.dni = dto.getDni();
		this.apellidos = dto.getApellidos();
		this.fechanacimiento = dto.getFechanacimiento();
		this.nombre = dto.getNombre();
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, fechanacimiento, matriculas, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni)
				&& Objects.equals(fechanacimiento, other.fechanacimiento)
				&& Objects.equals(matriculas, other.matriculas) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return dni + " ; " + nombre + " ; " + apellidos + " ; " + fechanacimiento;
	}
	
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public BigInteger getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(BigInteger fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setAlumno(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setAlumno(null);

		return matricula;
	}
	
	public AlumnoDTO toDTO() {
		return new AlumnoDTO(this);
	}

}