# Agenda de Contactos

Creado por Matthew Puente-Villegas.

Aplicacion Java de consola para gestionar libretas de contactos con persistencia en ficheros `.ser`.

## Funcionalidades actuales

- Alta, modificacion y borrado de contactos.
- Gestion de varias libretas.
- Menus separados para contactos, libreta y utilidades.
- Interfaz bilingue (espanol/ingles).
- Generacion de JAR distribuible.

## Estructura del proyecto

- `src/aplicacion`: punto de entrada.
- `src/interfaz`: flujo de consola, menus, idioma y mensajes.
- `src/dominio`: entidades y logica de negocio.
- `docs/uml`: diagrama UML.
- `docs/release-checklist.md`: pasos de release.
- `scripts/build-release.ps1`: build reproducible del JAR.

## Uso rapido

### 1) Compilar

```bash
javac -d bin src/aplicacion/Principal.java src/interfaz/*.java src/dominio/*.java
```

### 2) Ejecutar

```bash
java -cp bin aplicacion.Principal
```

### 3) Flujo de menus

- Menu principal: contactos, libreta, listado, guardar, cambiar libreta, idioma y salir.
- Menu de contactos: anadir, modificar o borrar contacto.
- Menu de libreta: renombrar o borrar libreta.

## Javadoc

Generar documentacion:

```bash
javadoc -d docs/javadoc -encoding utf-8 -docencoding utf-8 -charset utf-8 src/aplicacion/*.java src/interfaz/*.java src/dominio/*.java
```

Abrir luego `docs/javadoc/index.html`.

## UML

Diagrama de clases:

- `docs/uml/agenda-contactos-class-diagram.md`

## Release (JAR)

Build de release en PowerShell:

```powershell
.\scripts\build-release.ps1 -Version 1.0.0
```

Salida esperada:

- `dist/agenda-contactos-1.0.0.jar`

Ejecutar release:

```bash
java -jar dist/agenda-contactos-1.0.0.jar
```

Checklist completa:

- `docs/release-checklist.md`
