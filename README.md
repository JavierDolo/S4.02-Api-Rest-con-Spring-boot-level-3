# 🍎 S04T02N03 - Fruits CRUD API (Spring Boot + MongoDB + Docker)

Este proyecto es una **API RESTful** para gestionar frutas y su stock en kilogramos (`quantityKilos`).  
Se ha desarrollado con **Spring Boot 3**, **Spring Data MongoDB**, **Docker Compose** y un **replica set de MongoDB** para habilitar transacciones.

---

## 🚀 Tecnologías usadas
- Java 17
- Spring Boot (Web, Data MongoDB, Validation, Lombok)
- MongoDB 7 (con réplica configurada)
- Docker + Docker Compose
- Maven

---

## 📂 Estructura del proyecto
```
S04T02N03/
├─ src/
│  ├─ main/java/cat/itacademy/s04/t02/n03/
│  │  ├─ config/        # Configuración (MongoConfig, DataLoader)
│  │  ├─ controller/    # Controladores REST
│  │  ├─ dto/           # Objetos de transferencia (Request/Response/Error)
│  │  ├─ exception/     # Excepciones y handler global
│  │  ├─ model/         # Entidades MongoDB
│  │  ├─ repository/    # Repositorios Mongo
│  │  └─ service/       # Lógica de negocio
│  └─ resources/
│     └─ application.properties
├─ pom.xml
├─ .env.example
├─ Dockerfile
└─ docker-compose.yml
```

---

## ⚙️ Configuración

### 1. Variables de entorno
La aplicación usa **variables de entorno** para configurarse.  
Ejemplo de archivo `.env.example`:

```dotenv
SERVER_PORT=8080
SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/fruitdb?replicaSet=rs0
LOGGING_LEVEL_ROOT=INFO
```

> ⚠️ Copia `.env.example` a `.env` y edita los valores según tu entorno.  
> **Nunca subas tu `.env` a GitHub**, añádelo a `.gitignore`.

---

### 2. Construir el proyecto
```bash
mvn clean package -DskipTests
```

### 3. Levantar con Docker Compose
```bash
docker compose up --build
```

Esto levantará:
- **MongoDB** en `localhost:27017` (con réplica set `rs0`)
- **API** en `http://localhost:8080`

El `DataLoader` insertará datos iniciales idempotentes (ej: `apple`, `banana`).

---

## 📌 Endpoints principales

| Método | Endpoint           | Descripción               |
|--------|--------------------|---------------------------|
| GET    | `/api/fruits`      | Listar frutas (paginado)  |
| GET    | `/api/fruits/{id}` | Obtener fruta por ID      |
| POST   | `/api/fruits`      | Crear nueva fruta         |
| PUT    | `/api/fruits/{id}` | Actualizar fruta por ID   |
| DELETE | `/api/fruits/{id}` | Eliminar fruta por ID     |

Ejemplo de **JSON** para `POST` y `PUT`:
```json
{
  "name": "Mango",
  "quantityKilos": 12
}
```

---

## ❌ Formato de error

Los errores siguen un formato unificado (`ApiError`):

```json
{
  "timestamp": "2025-09-25T18:12:45.123Z",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid request",
  "path": "/api/fruits",
  "errors": [
    { "field": "quantityKilos", "message": "must be greater than or equal to 0" }
  ]
}
```

---

## 🐳 Ejecutar con Docker

```bash
# Compilar y levantar contenedores
docker compose up --build

# Ver logs de la aplicación
docker logs s04t02n03-app -f
```

---

## 🧪 Testing

Ejecutar los tests unitarios e integración:

```bash
mvn test
```

Incluye:
- Tests de servicio (con Mockito)
- Tests de controlador (con MockMvc)

---

## 📚 Recursos adicionales
- [Spring Data MongoDB Reference](https://docs.spring.io/spring-data/mongodb/reference/)
- [Spring Boot Externalized Configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html)
- [Spring Boot + MongoDB Transactions](https://www.baeldung.com/spring-data-mongodb-transactions)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [Docker Multi-stage Builds](https://docs.docker.com/build/building/multi-stage/)

---
