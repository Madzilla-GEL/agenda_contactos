package dominio;

/**
 * Excepcion lanzada cuando no se encuentra un contacto.
 */
public class ContactNotFound extends Exception {
    private final Contacto contacto;

    /**
     * @param contacto contacto no encontrado.
     */
    public ContactNotFound(Contacto contacto) {this.contacto = contacto;}

    /**
     * @return contacto no encontrado.
     */
    public Contacto getContacto() {return contacto;}

    @Override
    public String getMessage() {
        return "Contacto " + contacto.getNombre() + " " + contacto.getApellido() + " no encontrado.";
    }
}