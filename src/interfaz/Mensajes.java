package interfaz;

/**
 * Proveedor centralizado de textos en espanol e ingles.
 */
public final class Mensajes {
    private Mensajes() {
    }

    /**
     * Devuelve un texto localizado para una clave.
     *
     * @param idioma idioma activo.
     * @param clave  identificador del mensaje.
     * @return texto en el idioma seleccionado.
     */
    public static String get(Idioma idioma, String clave) {
        switch (clave) {
            case "chooseLanguage":
                return idioma == Idioma.EN ? "Choose language / Elige idioma (es/en): "
                        : "Elige idioma / Choose language (es/en): ";
            case "welcome":
                return idioma == Idioma.EN ? "Welcome to Contact Agenda." : "Bienvenido/a a Agenda de Contactos.";
            case "askNotebook":
                return idioma == Idioma.EN ? "Notebook name: " : "Nombre de la libreta: ";
            case "mainMenu":
                return idioma == Idioma.EN
                        ? "\nMain Menu\n1) Contacts\n2) Notebook\n3) List contacts\n4) Save\n5) Switch notebook\n6) Switch language\n0) Exit"
                        : "\nMenu Principal\n1) Contactos\n2) Libreta\n3) Ver contactos\n4) Guardar\n5) Cambiar libreta\n6) Cambiar idioma\n0) Salir";
            case "contactMenu":
                return idioma == Idioma.EN
                        ? "\nContact Menu\n1) Add contact\n2) Edit contact\n3) Delete contact\n0) Back"
                        : "\nMenu de Contactos\n1) Anadir contacto\n2) Modificar contacto\n3) Borrar contacto\n0) Volver";
            case "notebookMenu":
                return idioma == Idioma.EN
                        ? "\nNotebook Menu\n1) Rename notebook\n2) Delete notebook\n0) Back"
                        : "\nMenu de Libreta\n1) Cambiar nombre\n2) Borrar libreta\n0) Volver";
            case "promptOption":
                return idioma == Idioma.EN ? "Option: " : "Opcion: ";
            case "invalidOption":
                return idioma == Idioma.EN ? "Invalid option." : "Opcion invalida.";
            case "askName":
                return idioma == Idioma.EN ? "Name: " : "Nombre: ";
            case "askSurnameOptional":
                return idioma == Idioma.EN ? "Surname (optional): " : "Apellido (opcional): ";
            case "askPhone":
                return idioma == Idioma.EN ? "Phone number: " : "Telefono: ";
            case "askField":
                return idioma == Idioma.EN ? "Field to edit (name/surname/phone): "
                        : "Campo a modificar (nombre/apellido/telefono): ";
            case "askValue":
                return idioma == Idioma.EN ? "New value: " : "Nuevo valor: ";
            case "askRemoveField":
                return idioma == Idioma.EN ? "Remove field? (surname/phone/none): "
                        : "Quitar campo? (apellido/telefono/ninguno): ";
            case "saved":
                return idioma == Idioma.EN ? "Notebook saved." : "Libreta guardada.";
            case "goodbye":
                return idioma == Idioma.EN ? "Exiting program." : "Saliendo del programa.";
            case "emptyList":
                return idioma == Idioma.EN ? "No contacts in notebook." : "No hay contactos en la libreta.";
            case "askNotebookRename":
                return idioma == Idioma.EN ? "New notebook name: " : "Nuevo nombre de la libreta: ";
            case "askNotebookSwitch":
                return idioma == Idioma.EN ? "Notebook to open: " : "Libreta a abrir: ";
            case "askConfirm":
                return idioma == Idioma.EN ? "Confirm" : "Confirmar";
            case "yesNo":
                return idioma == Idioma.EN ? "(yes/no): " : "(si/no): ";
            case "inputError":
                return idioma == Idioma.EN ? "Input error. Please try again." : "Error de entrada. Intentalo de nuevo.";
            default:
                return clave;
        }
    }
}
