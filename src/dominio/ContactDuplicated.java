package dominio;

public class ContactDuplicated extends Exception{
    private final Contacto contacto;

    public ContactDuplicated(Contacto contacto) {this.contacto = contacto;}

    public Contacto getContacto() {return contacto;}

    @Override
    public String getMessage() {
        return "El contacto " + contacto.getNombre() + " " + contacto.getApellido() + " ya existe.";
    }
}