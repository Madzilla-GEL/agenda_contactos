package dominio;

import java.io.*;
import java.util.ArrayList;

/**
 * Modelo de una libreta de contactos con persistencia en fichero serializado.
 */
public class Libreta implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final ArrayList<Libreta> LIBRETAS = new ArrayList<>();
    private String nombre;
    private ArrayList<Contacto> contactos;


    //Constructores
    /**
     * Crea una libreta vacia sin nombre.
     */
    public Libreta() {
        nombre = "";
        contactos = new ArrayList<>();
        LIBRETAS.add(this);
    }
    /**
     * Crea una libreta vacia con nombre.
     *
     * @param nombre nombre de la libreta.
     */
    public Libreta(String nombre) {
        this.nombre = nombre;
        contactos = new ArrayList<>();
        LIBRETAS.add(this);
    }


    //Getters y Setters
    /**
     * @return nombre de la libreta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de la libreta y renombra su fichero si existe.
     *
     * @param nombre nuevo nombre de la libreta.
     * @return libreta actual.
     */
    public Libreta setNombre(String nombre) {
        File file = new File(this.nombre+".ser");
        if (file.exists()) file.renameTo(new File(nombre + ".ser"));
        this.nombre = nombre;
        return this;
    }
    /**
     * @return todas las libretas cargadas en memoria.
     */
    public ArrayList<Libreta> getLibretas() {return LIBRETAS;}

    /**
     * Busca una libreta por nombre entre las cargadas en memoria.
     *
     * @param nombre nombre buscado.
     * @return libreta encontrada o {@code null}.
     */
    public static Libreta getLibreta(String nombre) {
        for (Libreta libreta : LIBRETAS) {
            if (libreta.nombre.equals(nombre)) {
                return libreta;
            }
        }
        return null;
    }

    /**
     * @param nombre nombre de libreta.
     * @return true si existe en memoria.
     */
    public static boolean exists(String nombre) {return getLibreta(nombre) != null;}


    //Trabajo con Contacto
    /**
     * @return lista de contactos de la libreta.
     */
    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    /**
     * Reemplaza la lista de contactos.
     *
     * @param contactos nuevos contactos.
     * @return libreta actual.
     */
    public Libreta setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
        return this;
    }

    /**
     * Obtiene un contacto por posicion.
     *
     * @param index indice.
     * @return contacto en esa posicion.
     */
    public Contacto getContacto(int index) {
        return contactos.get(index);
    }

    /**
     * Obtiene un contacto por nombre.
     *
     * @param nombre nombre del contacto.
     * @return contacto encontrado o un contacto vacio.
     */
    public Contacto getContacto(String nombre) {
        Contacto contacto = new Contacto(nombre);
        if (contactos.contains(contacto)) return contactos.get(contactos.indexOf(contacto));
        else return new Contacto();
    }

    /**
     * Obtiene un contacto por nombre y apellido.
     *
     * @param nombre nombre del contacto.
     * @param apellido apellido del contacto.
     * @return contacto encontrado o un contacto vacio.
     */
    public Contacto getContacto(String nombre, String apellido) {
        Contacto contacto = new Contacto(nombre, apellido, "");
        if (contactos.contains(contacto)) return contactos.get(contactos.indexOf(contacto));
        else return new Contacto();
    }

    /**
     * Anade un contacto a la libreta.
     *
     * @param contacto contacto a insertar.
     * @return libreta actual.
     * @throws ContactDuplicated si ya existe.
     */
    public Libreta add(Contacto contacto) throws ContactDuplicated {
        if (contactos.contains(contacto)) throw new ContactDuplicated(contacto);
        contactos.add(contacto);
        return this;
    }

    /**
     * @return total de contactos.
     */
    public int size() {
        return contactos.size();
    }

    /**
     * Busca un contacto exacto por igualdad.
     *
     * @param contacto contacto a buscar.
     * @return contacto encontrado o {@code null}.
     */
    public Contacto buscar(Contacto contacto) {
        int index = contactos.indexOf(contacto);
        if (index == -1) {
            return null;
        } else {
            return contactos.get(index);
        }
    }

    /**
     * Borra un contacto de la libreta.
     *
     * @param contacto contacto a borrar.
     * @return true si fue borrado.
     * @throws ContactNotFound si no existe.
     */
    public boolean borrarContacto(Contacto contacto) throws ContactNotFound{
        if (!contactos.contains(contacto)) {
            throw new ContactNotFound(contacto);
        }
        contacto = contactos.get(contactos.indexOf(contacto));
        contactos.remove(contacto);
        return true;
    }

    /**
     * Modifica un contacto con una instruccion y valor.
     *
     * @param contacto    contacto a modificar.
     * @param modificacion array con [instruccion, valor].
     * @return true si la modificacion se aplico.
     * @throws ContactNotFound si el contacto no existe.
     */
    public boolean modificarContacto(Contacto contacto,String[] modificacion)
            throws ArrayIndexOutOfBoundsException, ContactNotFound {

        boolean res = true;
        String instruccion = modificacion[0]; String valor = modificacion[1];
        if (contactos.contains(contacto)) {
            contacto = contactos.get(contactos.indexOf(contacto));
            if (instruccion.equalsIgnoreCase("nombre") || instruccion.equalsIgnoreCase("name")) {
                contacto.setNombre(valor);
            } else if (instruccion.equalsIgnoreCase("apellido") || instruccion.equalsIgnoreCase("surname")) {
                contacto.setApellido(valor);
            } else if (instruccion.equalsIgnoreCase("numerodetelefono")
                    || instruccion.equalsIgnoreCase("telefono")
                    || instruccion.equalsIgnoreCase("numero")
                    || instruccion.equalsIgnoreCase("phone")) {
                contacto.setNumeroDeTelefono(valor);
            } else if (instruccion.equalsIgnoreCase("quitar")
                    || instruccion.equalsIgnoreCase("remove")) {
                if (valor.equalsIgnoreCase("apellido") || valor.equalsIgnoreCase("surname")) {
                    contacto.quitarApellido();
                } else if (valor.equalsIgnoreCase("numerodetelefono")
                        || valor.equalsIgnoreCase("telefono")
                        || valor.equalsIgnoreCase("numero")
                        || valor.equalsIgnoreCase("phone")) {
                    contacto.quitarTelefono();
                } else {
                    res = false;
                }
            } else {
                res = false;
            }
        } else {
            throw new ContactNotFound(contacto);
        }
        return res;
    }


    //Métodos de Libreta
    /**
     * Lee una libreta desde disco.
     *
     * @param nombre nombre de la libreta.
     * @return libreta leida o una vacia si hay error.
     */
    public static Libreta leer(String nombre) {
        try {
            ObjectInput fi = new ObjectInputStream(new FileInputStream(nombre+".ser"));
            Libreta libreta = (Libreta) fi.readObject();
            fi.close();
            return libreta;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error de lectura.");
            return new Libreta();
        }
    }
    /**
     * Guarda la libreta en disco sobrescribiendo su fichero.
     */
    public void grabar() {
        try {
            ObjectOutputStream fo = new ObjectOutputStream(new FileOutputStream(nombre + ".ser"));
                fo.writeObject(this);
                fo.close();
        } catch (IOException e) {
            System.out.println("Error de escritura.");
        }
    }

    /**
     * @return true si existe fichero para este nombre.
     */
    public boolean existeEnDisco() {
        return new File(nombre + ".ser").exists();
    }

    /**
     * @param nombre nombre de libreta.
     * @return true si existe su fichero.
     */
    public static boolean existeEnDisco(String nombre) {
        return new File(nombre + ".ser").exists();
    }

    /**
     * Borra el fichero de la libreta en disco.
     *
     * @return true si se borro correctamente.
     */
    public boolean borrar() {
        File file = new File(nombre + ".ser");
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {return false;}
        if (this.getClass() != object.getClass()) {return false;}
        Libreta libreta = (Libreta) object;
        return nombre.equals(libreta.nombre);
    }

    /**
     * @return descripcion de contactos para consola.
     */
    @Override
    public String toString() {
        if (!contactos.isEmpty()) {
            StringBuilder sb = new StringBuilder("Contactos ").append(nombre);
            int index = 1;
            for (Contacto contacto : contactos) {
                sb.append("\n\t").append(index).append(". ").append(contacto);
                index++;
            }
            return sb.toString();
        }
        return "No hay contactos en la libreta " + nombre + ".";
    }

    @Override
    public int hashCode() {
        return (nombre.hashCode()-1)*33;
    }
}