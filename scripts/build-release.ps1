param(
    [string]$Version = "1.0.0"
)

$ErrorActionPreference = "Stop"

$root = Split-Path -Parent $PSScriptRoot
$binDir = Join-Path $root "bin"
$distDir = Join-Path $root "dist"
$manifestPath = Join-Path $root "manifest.txt"
$jarName = "agenda-contactos-$Version.jar"
$jarPath = Join-Path $distDir $jarName

if (Test-Path $binDir) {
    Remove-Item -Recurse -Force $binDir
}
if (-not (Test-Path $distDir)) {
    New-Item -ItemType Directory -Path $distDir | Out-Null
}
New-Item -ItemType Directory -Path $binDir | Out-Null

javac -d $binDir "$root\src\aplicacion\Principal.java" "$root\src\interfaz\*.java" "$root\src\dominio\*.java"

@"
Manifest-Version: 1.0
Main-Class: aplicacion.Principal
"@ | Set-Content -Path $manifestPath -Encoding ASCII

jar cfm $jarPath $manifestPath -C $binDir .

Remove-Item $manifestPath

Write-Host "Release JAR generated at: $jarPath"
