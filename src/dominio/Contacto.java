package dominio;

import java.io.Serializable;
import java.lang.StringBuilder;

/**
 * Representa un contacto de una libreta.
 * <p>
 * Dos contactos se consideran iguales si comparten nombre y apellido.
 */
public class Contacto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre, apellido, numeroDeTelefono;


    //Constructores
    /**
     * Crea un contacto vacio.
     */
    public Contacto() {
        nombre = "";
        apellido = "";
        numeroDeTelefono = "";
    }
    /**
     * Crea un contacto con nombre.
     *
     * @param nombre nombre del contacto.
     */
    public Contacto(String nombre) {
        this.nombre = nombre;
        apellido = "";
        numeroDeTelefono = "";
    }
    /**
     * Crea un contacto con nombre y telefono.
     *
     * @param nombre           nombre del contacto.
     * @param numeroDeTelefono numero de telefono.
     */
    public Contacto(String nombre, String numeroDeTelefono) {
        this.nombre = nombre;
        apellido = "";
        this.numeroDeTelefono = numeroDeTelefono;
    }
    /**
     * Crea un contacto completo.
     *
     * @param nombre           nombre del contacto.
     * @param apellido         apellido del contacto.
     * @param numeroDeTelefono numero de telefono.
     */
    public Contacto(String nombre, String apellido, String numeroDeTelefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDeTelefono = numeroDeTelefono;
    }


    //Getters y setters
    /**
     * @return nombre del contacto.
     */
    public String getNombre() {return nombre;}
    /**
     * @param nombre nuevo nombre.
     * @return instancia actual para encadenado.
     */
    public Contacto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    /**
     * @return apellido del contacto.
     */
    public String getApellido() {return apellido;}
    /**
     * @param apellido nuevo apellido.
     * @return instancia actual para encadenado.
     */
    public Contacto setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }
    /**
     * Elimina el apellido del contacto.
     *
     * @return instancia actual para encadenado.
     */
    public Contacto quitarApellido() {
        apellido = "";
        return this;
    }
    /**
     * @return numero de telefono.
     */
    public String getNumeroDeTelefono() {return numeroDeTelefono;}
    /**
     * @param numeroDeTelefono nuevo numero de telefono.
     * @return instancia actual para encadenado.
     */
    public Contacto setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
        return this;
    }
    /**
     * Elimina el telefono del contacto.
     *
     * @return instancia actual para encadenado.
     */
    public Contacto quitarTelefono() {
        numeroDeTelefono = "";
        return this;
    }


    //Métodos de Contacto
    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (this.getClass() != object.getClass()) return false;
        Contacto contacto = (Contacto) object;
        return nombre.equals(contacto.nombre) && apellido.equals(contacto.apellido);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (apellido.isEmpty()) {
            sb.append(nombre).append(": ").append(numeroDeTelefono);
        }
        else {
            sb.append(nombre).append(' ').append(apellido).append(": ").append(numeroDeTelefono);
        }
        return sb.toString();
    }
    @Override
    public int hashCode() {
        return (nombre.hashCode()-1)*33+apellido.hashCode();
    }
}