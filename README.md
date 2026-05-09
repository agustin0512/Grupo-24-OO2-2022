# Grupo-24-OO2-2022

TP OO2 - Aplicación Spring Boot con PostgreSQL.

## Ejecución local

1. Crear una base PostgreSQL local, por ejemplo `grupo24`.
2. Configurar las variables de entorno si no se usan los valores por defecto:
   - `DB_HOST` (por defecto `localhost`)
   - `DB_PORT` (por defecto `5432`)
   - `DB_NAME` (por defecto `grupo24`)
   - `DB_USER` (por defecto `postgres`)
   - `DB_PASS` (por defecto `postgres`)
3. Ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.

## Usuario administrador inicial

Al arrancar, la aplicación crea automáticamente el rol `ROLE_ADMIN` y un usuario administrador si todavía no existe. Las credenciales se toman de variables de entorno:

- `ADMIN_USER` (por defecto `admin`)
- `ADMIN_PASSWORD` (por defecto `admin123`)
- `ADMIN_MAIL` (por defecto `admin@system.com`)
- `ADMIN_DNI` (por defecto `99999999`)

Para producción, especialmente en Render, cambiar `ADMIN_PASSWORD` por una contraseña segura antes del primer deploy.

## Deploy en Render

El repositorio incluye:

- `Dockerfile`: compila el proyecto con Maven y ejecuta el `.jar` usando el puerto dinámico `PORT` de Render.
- `render.yaml`: define un Web Service Docker y una base PostgreSQL administrada.

Pasos recomendados:

1. Subir el repositorio a GitHub/GitLab.
2. En Render, crear un **Blueprint** desde este repositorio. Render detecta `render.yaml` en la raíz.
3. Cuando Render solicite las variables con `sync: false`, completar `ADMIN_PASSWORD` con una contraseña segura.
4. Desplegar. En el primer arranque se crean las tablas, el rol `ROLE_ADMIN` y el usuario indicado por `ADMIN_USER`.
5. Entrar a la URL pública de Render y usar `/login` con el usuario administrador configurado.

También se puede crear manualmente un Web Service Docker y una base PostgreSQL en Render. En ese caso configurar estas variables en el servicio:

- `DB_URL`: Internal Database URL de Render, con formato `postgresql://usuario:password@host:5432/base`.
- `ADMIN_USER`: nombre del usuario administrador.
- `ADMIN_PASSWORD`: contraseña del usuario administrador.
- `ADMIN_MAIL`: correo del administrador.
- `ADMIN_DNI`: DNI único del administrador.
