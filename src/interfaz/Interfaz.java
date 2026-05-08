package interfaz;

import dominio.*;
import java.util.Scanner;

/**
 * Interfaz de consola para gestionar libretas y contactos.
 */
public class Interfaz {
    private Libreta libreta;
    private Idioma idioma = Idioma.ES;
    private final Scanner teclado = new Scanner(System.in);

    /**
     * Constructor por defecto.
     */
    public Interfaz() {
    }

    /**
     * Inicia la aplicacion y menus principales.
     */
    public void ejecutar() {
        seleccionarIdioma();
        System.out.println(Mensajes.get(idioma, "welcome"));
        abrirLibretaInicial();
        boolean continuar = true;
        while (continuar) {
            System.out.println(Mensajes.get(idioma, "mainMenu"));
            switch (leerOpcion()) {
                case "1":
                    menuContactos();
                    break;
                case "2":
                    menuLibreta();
                    break;
                case "3":
                    mostrarContactos();
                    break;
                case "4":
                    guardarLibreta();
                    break;
                case "5":
                    cambiarLibreta();
                    break;
                case "6":
                    seleccionarIdioma();
                    break;
                case "0":
                    continuar = salir();
                    break;
                default:
                    System.out.println(Mensajes.get(idioma, "invalidOption"));
            }
        }
    }

    private void seleccionarIdioma() {
        System.out.print(Mensajes.get(idioma, "chooseLanguage"));
        String entrada = teclado.nextLine().trim().toLowerCase();
        if ("en".equals(entrada)) {
            idioma = Idioma.EN;
        } else {
            idioma = Idioma.ES;
        }
    }

    private void abrirLibretaInicial() {
        System.out.print(Mensajes.get(idioma, "askNotebook"));
        String nombreLibreta = teclado.nextLine().trim();
        if (Libreta.existeEnDisco(nombreLibreta)) {
            libreta = Libreta.leer(nombreLibreta);
        } else {
            libreta = new Libreta(nombreLibreta);
        }
    }

    private void menuContactos() {
        boolean volver = false;
        while (!volver) {
            System.out.println(Mensajes.get(idioma, "contactMenu"));
            switch (leerOpcion()) {
                case "1":
                    anadirContacto();
                    break;
                case "2":
                    modificarContacto();
                    break;
                case "3":
                    borrarContacto();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println(Mensajes.get(idioma, "invalidOption"));
            }
        }
    }

    private void menuLibreta() {
        boolean volver = false;
        while (!volver) {
            System.out.println(Mensajes.get(idioma, "notebookMenu"));
            switch (leerOpcion()) {
                case "1":
                    renombrarLibreta();
                    break;
                case "2":
                    borrarLibreta();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println(Mensajes.get(idioma, "invalidOption"));
            }
        }
    }

    private void anadirContacto() {
        try {
            String nombre = pedirCampo("askName");
            String apellido = pedirCampo("askSurnameOptional");
            String telefono = pedirCampo("askPhone");
            if (nombre.isBlank() || telefono.isBlank()) {
                System.out.println(Mensajes.get(idioma, "inputError"));
                return;
            }

            if (apellido.isBlank()) {
                libreta.add(new Contacto(nombre, telefono));
            } else {
                libreta.add(new Contacto(nombre, apellido, telefono));
            }
        } catch (ContactDuplicated e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificarContacto() {
        try {
            String nombre = pedirCampo("askName");
            String apellido = pedirCampo("askSurnameOptional");
            String campo = pedirCampo("askField");
            String valor = pedirCampo("askValue");
            String remove = pedirCampo("askRemoveField");
            String instruccion = campo;
            String valorFinal = valor;
            if (!remove.equalsIgnoreCase("ninguno") && !remove.equalsIgnoreCase("none") && !remove.isBlank()) {
                instruccion = idioma == Idioma.EN ? "remove" : "quitar";
                valorFinal = remove;
            }

            Contacto base = apellido.isBlank() ? new Contacto(nombre) : new Contacto(nombre, apellido, "");
            boolean ok = libreta.modificarContacto(base, new String[]{instruccion, valorFinal});
            if (!ok) {
                System.out.println(Mensajes.get(idioma, "invalidOption"));
            }
        } catch (ContactNotFound e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Mensajes.get(idioma, "inputError"));
        }
    }

    private void borrarContacto() {
        try {
            String nombre = pedirCampo("askName");
            String apellido = pedirCampo("askSurnameOptional");
            Contacto contacto = apellido.isBlank() ? new Contacto(nombre) : new Contacto(nombre, apellido, "");
            if (confirmacion(pregunta("deleteContact", contacto.toString()))) {
                libreta.borrarContacto(contacto);
            }
        } catch (ContactNotFound e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Mensajes.get(idioma, "inputError"));
        }
    }

    private void mostrarContactos() {
        if (libreta.size() == 0) {
            System.out.println(Mensajes.get(idioma, "emptyList"));
        } else {
            System.out.println(libreta);
        }
    }

    private void renombrarLibreta() {
        String nuevoNombre = pedirCampo("askNotebookRename");
        if (!nuevoNombre.isBlank() && confirmacion(pregunta("renameNotebook", libreta.getNombre(), nuevoNombre))) {
            libreta.setNombre(nuevoNombre);
        }
    }

    private void guardarLibreta() {
        if (libreta.existeEnDisco() && !confirmacion(pregunta("overwriteNotebook", libreta.getNombre()))) {
            return;
        }
        libreta.grabar();
        System.out.println(Mensajes.get(idioma, "saved"));
    }

    private void borrarLibreta() {
        if (confirmacion(pregunta("deleteNotebook", libreta.getNombre()))) {
            libreta.borrar();
            cambiarLibreta();
        }
    }

    private void cambiarLibreta() {
        if (confirmacion(pregunta("saveBeforeSwitch")) && (!libreta.existeEnDisco()
                || confirmacion(pregunta("overwriteNotebook", libreta.getNombre())))) {
            libreta.grabar();
        }
        String nueva = pedirCampo("askNotebookSwitch");
        if (Libreta.existeEnDisco(nueva)) {
            libreta = Libreta.leer(nueva);
        } else {
            libreta = new Libreta(nueva);
        }
    }

    private boolean salir() {
        if (confirmacion(pregunta("saveOnExit")) && (!libreta.existeEnDisco()
                || confirmacion(pregunta("overwriteNotebook", libreta.getNombre())))) {
            libreta.grabar();
        }
        System.out.println(Mensajes.get(idioma, "goodbye"));
        return false;
    }

    private String leerOpcion() {
        System.out.print(Mensajes.get(idioma, "promptOption"));
        return teclado.nextLine().trim();
    }

    private String pedirCampo(String clave) {
        System.out.print(Mensajes.get(idioma, clave));
        return teclado.nextLine().trim();
    }

    /**
     * Pide confirmacion en consola.
     *
     * @param pregunta texto de pregunta.
     * @return true si confirma.
     */
    public boolean confirmacion(String pregunta) {
        String siNo;
        do {
            System.out.print(pregunta + " " + Mensajes.get(idioma, "yesNo"));
            siNo = teclado.nextLine();
            if (esNo(siNo)) {
                return false;
            }
            if (!esSi(siNo)) {
                System.out.println(Mensajes.get(idioma, "invalidOption"));
            }
        } while (!esSi(siNo));
        return true;
    }

    private boolean esSi(String entrada) {
        return entrada.equalsIgnoreCase("si") || entrada.equalsIgnoreCase("s")
                || entrada.equalsIgnoreCase("yes") || entrada.equalsIgnoreCase("y");
    }

    private boolean esNo(String entrada) {
        return entrada.equalsIgnoreCase("no") || entrada.equalsIgnoreCase("n");
    }

    private String pregunta(String clave, String... valores) {
        String base;
        switch (clave) {
            case "deleteContact":
                base = idioma == Idioma.EN ? "Delete contact " + valores[0] + "?" : "Borrar contacto " + valores[0] + "?";
                break;
            case "renameNotebook":
                base = idioma == Idioma.EN ? "Rename notebook " + valores[0] + " to " + valores[1] + "?"
                        : "Cambiar nombre de " + valores[0] + " a " + valores[1] + "?";
                break;
            case "overwriteNotebook":
                base = idioma == Idioma.EN ? "Notebook " + valores[0] + " exists. Overwrite?"
                        : "La libreta " + valores[0] + " ya existe. Sobrescribir?";
                break;
            case "deleteNotebook":
                base = idioma == Idioma.EN ? "Delete notebook " + valores[0] + "?"
                        : "Borrar la libreta " + valores[0] + "?";
                break;
            case "saveBeforeSwitch":
                base = idioma == Idioma.EN ? "Save current notebook before switching?"
                        : "Guardar libreta actual antes de cambiar?";
                break;
            case "saveOnExit":
                base = idioma == Idioma.EN ? "Save changes before exit?"
                        : "Guardar cambios antes de salir?";
                break;
            default:
                base = Mensajes.get(idioma, "askConfirm");
        }
        return base;
    }
}