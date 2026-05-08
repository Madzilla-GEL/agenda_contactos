package dominio;

public class ContactNotFound extends Exception {
    private final Contacto contacto;

    public ContactNotFound(Contacto contacto) {this.contacto = contacto;}

    public Contacto getContacto() {return contacto;}

    @Override
    public String getMessage() {
        return "Contacto " + contacto.getNombre() + " " + contacto.getApellido() + " no encontrado.";
    }
}