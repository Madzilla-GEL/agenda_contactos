package aplicacion;

import interfaz.Interfaz;

/**
 * Punto de entrada de la aplicacion.
 */
public class Principal {
    /**
     * Ejecuta la interfaz de consola.
     *
     * @param args argumentos de ejecucion.
     */
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.ejecutar();
    }
}