package dominio;

/**
 * Excepcion lanzada cuando se intenta crear un contacto ya existente.
 */
public class ContactDuplicated extends Exception{
    private final Contacto contacto;

    /**
     * @param contacto contacto duplicado.
     */
    public ContactDuplicated(Contacto contacto) {this.contacto = contacto;}

    /**
     * @return contacto que causo la excepcion.
     */
    public Contacto getContacto() {return contacto;}

    @Override
    public String getMessage() {
        return "El contacto " + contacto.getNombre() + " " + contacto.getApellido() + " ya existe.";
    }
}