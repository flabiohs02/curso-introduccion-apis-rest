# Curso Introducción APIs REST

API REST desarrollada con Spring Boot para gestión de productos y categorías.

## 🛠️ Tecnologías

| Tecnología | Versión |
|------------|---------|
| Java | 25 |
| Spring Boot | 4.0.0 |
| PostgreSQL | - |
| Lombok | - |
| SpringDoc OpenAPI | 2.8.14 |
| Bean Validation | - |

## 📁 Estructura del Proyecto

```
src/main/java/com/cursoapis/curso_introduccion_apis_rest/
├── CursoIntroduccionApisRestApplication.java   # Clase principal
├── config/                                      # Configuraciones
│   └── ModelMapperConfig.java
├── controllers/                                 # Controladores REST
│   ├── ProductController.java
│   └── CategoryController.java
├── dto/                                         # Data Transfer Objects
│   ├── ProductDTO.java
│   └── CategoryDTO.java
├── entity/                                      # Entidades JPA
│   ├── Product.java
│   ├── Category.java
│   └── IsActive.java (enum)
├── exception/                                   # Manejo de excepciones
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── DuplicateResourceException.java
│   └── ErrorResponse.java
├── mapper/                                      # Conversores DTO ↔ Entity
│   ├── CategoryMapper.java
│   ├── CategoryModelMapper.java
│   ├── ProductMapper.java
│   └── ProductModeMapper.java
├── repositories/                                # Repositorios JPA
│   ├── ProductRepository.java
│   └── CategoryRepository.java
└── service/                                     # Capa de servicios
    ├── ProductService.java
    ├── CategoryService.java
    └── impl/
        ├── ProductServiceImpl.java
        └── CategoryServiceImpl.java
```

## ⚙️ Configuración

### application.properties

```properties
spring.application.name=curso-introduccion-apis-rest
server.port=8097

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:15432/bbdd_curso_apis
spring.datasource.username=postgres
spring.datasource.password=12345678

# JPA
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs
```

## 🗃️ Modelo de Datos

### Product

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | ID autoincremental (sequence) |
| name | String | Nombre único del producto |
| description | String | Descripción del producto |
| price | double | Precio del producto |
| quantity | int | Cantidad en stock |
| isActive | IsActive | Estado: ACTIVE / INACTIVE |
| category | Category | Categoría asociada (FK) |

### Category

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | ID autoincremental (sequence) |
| name | String | Nombre único de la categoría |

### IsActive (Enum)

```java
public enum IsActive {
    ACTIVE,
    INACTIVE
}
```

## 🔌 API Endpoints

### Products - `/api/v1/products`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Listar todos los productos |
| GET | `/{id}` | Obtener producto por ID |
| GET | `/name/{name}` | Buscar producto por nombre |
| GET | `/is-active/{status}` | Filtrar por estado (ACTIVE/INACTIVE) |
| POST | `/` | Crear nuevo producto |
| PUT | `/{id}` | Actualizar producto |
| PUT | `/{id}/is-active` | Cambiar estado del producto |
| DELETE | `/{id}` | Eliminar producto |

### Categories - `/api/v1/categories`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Listar todas las categorías |
| GET | `/{id}` | Obtener categoría por ID |
| GET | `/name/{name}` | Buscar categoría por nombre |
| POST | `/` | Crear nueva categoría |
| PUT | `/{id}` | Actualizar categoría |
| DELETE | `/{id}` | Eliminar categoría |

## ✅ Validaciones

### ProductDTO

| Campo | Validaciones |
|-------|-------------|
| name | `@NotBlank`, `@Size(min=2, max=100)` |
| description | `@Size(max=500)` |
| price | `@NotNull`, `@Positive` |
| quantity | `@NotNull`, `@Min(0)` |
| isActive | `@NotNull` |

### CategoryDTO

| Campo | Validaciones |
|-------|-------------|
| name | `@NotBlank`, `@Size(min=2, max=100)` |

## 🚨 Manejo de Errores

### Excepciones Personalizadas

| Excepción | HTTP Status | Uso |
|-----------|-------------|-----|
| `ResourceNotFoundException` | 404 | Recurso no encontrado |
| `DuplicateResourceException` | 409 | Recurso duplicado |
| `MethodArgumentNotValidException` | 400 | Error de validación |
| `IllegalArgumentException` | 400 | Argumento inválido |
| `ObjectOptimisticLockingFailureException` | 409 | Conflicto de concurrencia |

### Formato de Error

```json
{
  "error": "RESOURCE_NOT_FOUND",
  "message": "Product not found with id: '999'",
  "status": 404,
  "path": "/api/v1/products/999",
  "timestamp": "2025-12-07 23:55:00"
}
```

## 🚀 Ejecución

### Requisitos previos

- Java 25
- PostgreSQL corriendo en `localhost:15432`
- Base de datos `bbdd_curso_apis` creada

### Comandos

```bash
# Compilar
./mvnw compile

# Ejecutar tests
./mvnw test

# Ejecutar aplicación
./mvnw spring-boot:run
```

### URLs

- **API Base**: http://localhost:8097/api/v1
- **Swagger UI**: http://localhost:8097/swagger-ui.html
- **API Docs**: http://localhost:8097/api-docs

## 📝 Ejemplos de Requests

### Crear Producto

```bash
curl -X POST http://localhost:8097/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop Gaming",
    "description": "Laptop para gaming de alta gama",
    "price": 1500.00,
    "quantity": 10,
    "isActive": "ACTIVE",
    "categoryId": 1
  }'
```

### Crear Categoría

```bash
curl -X POST http://localhost:8097/api/v1/categories \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Electrónicos"
  }'
```

## 📄 Licencia

Este proyecto es parte del curso de introducción a APIs REST.
