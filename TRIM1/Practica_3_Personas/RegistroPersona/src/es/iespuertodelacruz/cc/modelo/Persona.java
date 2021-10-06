
package es.iespuertodelacruz.cc.modelo;

/**
 *
 * @author Cedric Christoph
 */
public class Persona {
    
    String dni;
    String nombre;
    String apellido;
    int edad;
    
    public Persona(){}

    public Persona(String dni, String nombre, String apellido, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getDataRow() {
        // FORMATO: nombre;apellido;dni;edad;altura;peso
        String dataRow = getNombre() + ";" + getApellido() + ";" + getDni() + ";" + getEdad() + "\n";
        return dataRow;
    }
    
}
