# 🦇 Tienda Halloween - API REST con Autenticación JWT 🦇

## 📋 Descripción del Proyecto

Esta es una aplicación completa para la gestión de productos de una tienda de Halloween que incluye:

- **Backend**: API REST desarrollada con Spring Boot 3.2.0 y Java 21
- **Frontend**: Aplicación React con Material-UI y tema oscuro de Halloween
- **Autenticación**: Sistema JWT con roles (USER/ADMIN)
- **Base de datos**: H2 en memoria para desarrollo
- **Documentación**: Swagger/OpenAPI integrada

## 🚀 Características Principales

### Backend
- ✅ API RESTful con Spring Boot
- ✅ Autenticación JWT stateless
- ✅ Control de acceso basado en roles
- ✅ Documentación automática con Swagger
- ✅ Base de datos H2 con JPA/Hibernate
- ✅ Validación de datos
- ✅ Manejo de errores centralizado

### Frontend
- ✅ Interfaz moderna con Material-UI
- ✅ Tema oscuro personalizado para Halloween
- ✅ Gestión de estado con Context API
- ✅ Rutas protegidas
- ✅ Control de acceso por roles en UI
- ✅ Persistencia de sesión JWT
- ✅ Formularios de login/registro

### Seguridad
- ✅ Autenticación JWT
- ✅ Control de acceso basado en roles
- ✅ Protección CSRF deshabilitada (stateless)
- ✅ CORS configurado
- ✅ Validación de tokens

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 21** - Runtime LTS
- **Spring Boot 3.2.0** - Framework web
- **Spring Security 6.2.0** - Autenticación y autorización
- **JWT (JJWT 0.11.5)** - Tokens de autenticación
- **H2 Database** - Base de datos en memoria
- **JPA/Hibernate** - ORM
- **Maven** - Gestión de dependencias
- **Swagger/OpenAPI** - Documentación API

### Frontend
- **React 18** - Framework UI
- **Material-UI** - Componentes UI
- **React Router** - Navegación
- **Axios** - Cliente HTTP
- **Context API** - Gestión de estado

## 📁 Estructura del Proyecto

```
tienda-halloween/
├── src/main/java/com/example/demo/
│   ├── config/
│   │   ├── OpenApiConfig.java          # Configuración Swagger
│   │   └── SecurityConfig.java         # Configuración de seguridad
│   ├── controller/
│   │   ├── AuthController.java         # Endpoints de autenticación
│   │   └── ProductoController.java     # Endpoints de productos
│   ├── models/
│   │   ├── Producto.java               # Entidad Producto
│   │   ├── Role.java                   # Entidad Role
│   │   └── User.java                   # Entidad User
│   ├── repository/
│   │   ├── ProductoRepository.java     # Repository de productos
│   │   ├── RoleRepository.java         # Repository de roles
│   │   └── UserRepository.java         # Repository de usuarios
│   ├── service/
│   │   ├── JwtAuthenticationFilter.java # Filtro JWT
│   │   ├── JwtUtil.java                # Utilidades JWT
│   │   └── UserDetailsServiceImpl.java # Servicio de usuarios
│   └── DemoApplication.java            # Clase principal
├── src/main/resources/
│   ├── application.properties          # Configuración
│   └── templates/                      # Templates Thymeleaf (no usados)
├── frontend/                           # Aplicación React
│   ├── src/
│   │   ├── components/
│   │   │   └── Login.js                # Componente de login/registro
│   │   ├── pages/
│   │   │   └── Productos.js            # Página de productos
│   │   ├── services/
│   │   │   └── ApiService.js           # Servicio API
│   │   ├── context/
│   │   │   └── AuthContext.js          # Contexto de autenticación
│   │   ├── App.js                      # Componente principal
│   │   └── index.js                    # Punto de entrada
│   └── public/
│       └── index.html                  # HTML principal
└── pom.xml                             # Configuración Maven
```

## 🔧 Instalación y Configuración

### Prerrequisitos
- Java 21 instalado
- Node.js 16+ instalado
- Maven 3.8+ (viene incluido en el wrapper)

### Backend

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd tienda-halloween
   ```

2. **Compilar el proyecto**
   ```bash
   ./mvnw clean compile
   ```

3. **Ejecutar la aplicación**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acceder a la documentación Swagger**
   - URL: http://localhost:8080/swagger-ui.html
   - API Docs: http://localhost:8080/v3/api-docs

5. **Consola H2** (opcional)
   - URL: http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:testdb
   - Usuario: sa
   - Contraseña: (vacía)

### Frontend

1. **Instalar dependencias**
   ```bash
   cd frontend
   npm install
   ```

2. **Ejecutar la aplicación**
   ```bash
   npm start
   ```

3. **Acceder a la aplicación**
   - URL: http://localhost:3000
   - Se redirigirá automáticamente a http://localhost:3000/login

## 👥 Usuarios de Prueba

La aplicación incluye usuarios de prueba pre-configurados:

### Administrador
- **Usuario**: admin
- **Contraseña**: admin123
- **Roles**: ADMIN, USER
- **Permisos**: Crear/editar productos, ver todos los productos

### Usuario Normal
- **Usuario**: user
- **Contraseña**: user123
- **Roles**: USER
- **Permisos**: Ver productos únicamente

## 📚 API Endpoints

### Autenticación (`/api/auth`)

| Método | Endpoint | Descripción | Autorización |
|--------|----------|-------------|--------------|
| POST | `/login` | Iniciar sesión | Público |
| POST | `/register` | Registrar usuario | Público |
| GET | `/me` | Obtener usuario actual | JWT Token |
| GET | `/test` | Endpoint de prueba | Público |

### Productos (`/api/productos`)

| Método | Endpoint | Descripción | Autorización |
|--------|----------|-------------|--------------|
| GET | `/` | Listar productos | USER/ADMIN |
| POST | `/` | Crear producto | ADMIN |

## 🔐 Sistema de Autenticación

### Flujo de Autenticación
1. **Registro**: Usuario se registra con username/password
2. **Login**: Usuario envía credenciales
3. **Token JWT**: Backend genera token con información del usuario
4. **Autorización**: Frontend incluye token en header `Authorization: Bearer <token>`
5. **Verificación**: Backend valida token en cada request protegido

### Roles y Permisos

#### USER
- ✅ Ver lista de productos
- ❌ Crear/editar productos

#### ADMIN
- ✅ Ver lista de productos
- ✅ Crear/editar productos
- ✅ Acceso a funciones administrativas en UI

### Control de Acceso en Frontend
- **Rutas protegidas**: Solo usuarios autenticados pueden acceder
- **Elementos condicionales**: Botones y funciones se muestran/ocultan según roles
- **Persistencia**: Token se guarda en localStorage
- **Auto-login**: Al recargar página, se verifica token automáticamente

## 🎨 Interfaz de Usuario

### Tema
- **Modo oscuro** personalizado con colores de Halloween
- **Naranja (#ff6b35)** como color primario
- **Amarillo (#f8f32b)** como color secundario
- **Fondos oscuros** para mejor experiencia visual

### Componentes Principales
- **Login/Register**: Formulario con pestañas para autenticación
- **Productos**: Tabla responsive con lista de productos
- **FAB (Admin)**: Botón flotante para crear productos (solo admin)
- **Barra de navegación**: Información de usuario y logout

### Responsive Design
- Adaptable a diferentes tamaños de pantalla
- Tabla responsive para productos
- Diálogos modales para formularios

## 🧪 Testing

### Backend
```bash
./mvnw test
```

### Frontend
```bash
cd frontend
npm test
```

## 📦 Build y Despliegue

### Backend
```bash
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd frontend
npm run build
# Los archivos se generan en build/
```

## 🔍 Solución de Problemas

### Problemas Comunes

1. **Puerto ocupado**
   ```bash
   # Ver procesos usando el puerto
   netstat -ano | findstr :8080
   # Matar proceso
   taskkill /PID <PID> /F
   ```

2. **Error de CORS**
   - Verificar que el backend tenga `@CrossOrigin(origins = "*")`
   - Verificar que el frontend use la URL correcta del backend

3. **Token expirado**
   - Los tokens JWT no expiran por defecto
   - Si se implementa expiración, el frontend manejará automáticamente

4. **Base de datos vacía**
   - Los productos se crean desde el frontend (solo admin)
   - O se pueden crear manualmente en la consola H2

### Logs Útiles
- **Backend**: Revisa la consola donde ejecutas `./mvnw spring-boot:run`
- **Frontend**: Abre las herramientas de desarrollo del navegador (F12)

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autor

**Tu Nombre** - [Tu GitHub](https://github.com/tuusuario)

## 🙏 Agradecimientos

- Spring Boot por el excelente framework
- Material-UI por los componentes
- La comunidad open source

---

¡Disfruta tu tienda de Halloween! 🎃👻🦇