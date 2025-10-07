# Battleship — Juego de Batalla Naval (Java)

[![Made with Java](https://img.shields.io/badge/Language-Java-blue?logo=java)](https://www.java.com) <!-- badge -->
[![Status](https://img.shields.io/badge/status-WIP-orange)](#)

---

<!-- Imagen principal -->

![Battleship — película](https://www.accioncine.es/wp-content/uploads/2012/03/battleship-TR.jpg)

> 🎯 **Resumen rápido**: *Battleship* es una implementación en Java del clásico juego "Hundir la Flota". Pensado como proyecto didáctico y demostración de buenas prácticas en programación orientada a objetos, fácil de extender (IA, GUI, red) y con una estructura modular limpia.

---

## ¿Por qué esta imagen?

La imagen que he añadido (cartel de la película *Battleship*) aporta un impacto visual inmediato: ayuda a conectar el proyecto con la temática y hace el README más atractivo a primera vista. **Sin embargo** ten en cuenta que es material con copyright — si vas a publicar el repositorio públicamente y quieres evitar riesgos, te recomiendo sustituirla por:

* Capturas reales del propio juego (mejor opción: muestra tu trabajo con pruebas de ejecución).
* Una ilustración o banner creada por ti (SVG/PNG) con licencia abierta.
* Una imagen con licencia Creative Commons compatible.

He dejado la imagen aquí porque me pediste usar esa URL; si prefieres que la cambie por una captura propia o una alternativa libre, lo hago.

---

## Características principales

* 🧭 **Diseño modular**: clases separadas para `Board`, `Ship`, `Coordinate`, `Game`, etc.
* ⚙️ **Orientado a objetos**: código Java limpio y fácil de entender.
* 🧪 **Preparado para tests**: estructura adecuada para añadir pruebas unitarias.
* ♻️ **Extensible**: permite añadir IA, interfaz gráfica, o juego en red sin reescribir la lógica central.

---

## Estructura del repositorio

```
/ (raíz)
├─ README.md                # Este archivo (actualizado)
├─ pom.xml / build.gradle   # Configuración de build (si aplica)
├─ src/
│  ├─ main/
│  │  └─ java/              # Código fuente principal
│  │     └─ ...             # p.ej. Board.java, Ship.java, Coordinate.java, Game.java, Main.java
│  └─ test/
│     └─ java/              # Tests unitarios
├─ docs/                    # Imágenes, gif, especificaciones
├─ LICENSE
└─ .gitignore
```

**Explicación rápida de carpetas**

* `src/main/java` — Lógica del juego: tablero, barcos, colocación, mecánica de disparo y gestión del estado.
* `src/test/java` — Tests unitarios (recomendado añadir pruebas para colocación/impactos/hundimiento).
* `docs/` — Imágenes y material visual que acompañe al README.

---

## Cómo ejecutar (guía rápida)

### Con Maven (si existe `pom.xml`)

```bash
# Compilar
mvn clean package

# Ejecutar (si se genera un jar ejecutable)
java -jar target/battleship-1.0-SNAPSHOT.jar
```

### Sin Maven — compilación manual

```bash
# Compilar todos los .java
javac -d out $(find src/main/java -name "*.java")

# Ejecutar
java -cp out com.tu.paquete.Main
```

> Ajusta el paquete `com.tu.paquete.Main` al package real de tu proyecto.

---

## Uso básico (ejemplo CLI)

```
$ java -jar target/battleship.jar
Welcome to Battleship!
> Place your ships or choose random placement
> Player 1 fires at: B4
> Miss!
> Computer fires at: E2
> Hit!
```

> Si implementas GUI, sustituye esta sección por instrucciones de la interfaz gráfica.

---

## Ideas de mejora / TODO

* ✅ Añadir pruebas unitarias completas para `Board`, `Ship` y la lógica de impacto.
* ✅ Documentación (Javadoc) de las clases públicas.
* 🔲 Implementar IA adversaria con niveles de dificultad.
* 🔲 Interfaz gráfica con JavaFX o Swing.
* 🔲 Modo multijugador por red (sockets) o vía WebSocket.

---

## Cómo contribuir

1. Haz fork del repositorio.
2. Crea una rama: `git checkout -b feat/nueva-caracteristica`.
3. Haz commits pequeños y con mensaje descriptivo.
4. Abre un Pull Request explicando el cambio.

---

## Licencia

Añade un archivo `LICENSE` (por ejemplo MIT) si quieres permitir contribuciones y uso con claridad.

---

## Créditos

Proyecto original: `AlexSeguii/battleship` — ejercicio práctico de Java.

---

> Si quieres, reemplazo la imagen por:
>
> * una captura real del juego (sube la imagen), o
> * busco alternativas con licencia CC0/CC-BY, o
> * diseño un banner simple (SVG) para el README.
