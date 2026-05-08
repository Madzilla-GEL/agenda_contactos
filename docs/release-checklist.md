# Release Checklist

1. Verify working tree and tests:
   - `git status`
   - Compile: `javac -d bin src/aplicacion/Principal.java src/interfaz/*.java src/dominio/*.java`
2. Build release artifact:
   - PowerShell: `.\scripts\build-release.ps1 -Version 1.0.0`
3. Smoke test:
   - `java -jar dist/agenda-contactos-1.0.0.jar`
4. Update release notes in `README.md` if behavior changed.
5. Commit and tag:
   - `git add .`
   - `git commit -m "release: prepare v1.0.0"`
   - `git tag v1.0.0`
6. Push branch and tags:
   - `git push`
   - `git push --tags`
7. Create GitHub release and upload JAR from `dist/`.
