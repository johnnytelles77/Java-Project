# 🧾 API de Gestión de Productos y Clientes  
### Product and Client Management API

![Java](https://img.shields.io/badge/Java-21-red?logo=java)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.4.10-green?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?logo=mysql)
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger-API%20Docs-brightgreen?logo=swagger)
![Lombok](https://img.shields.io/badge/Lombok-Annotations-yellow?logo=java)

---

## 🌍 Descripción / Description

Esta API REST fue desarrollada con **Spring Boot** y **MySQL** para gestionar clientes, productos y ventas.  
El objetivo principal del proyecto fue aprender sobre **arquitectura REST**, **entidades JPA**, **servicios**, **DTOs** y **controladores** dentro del ecosistema de **Spring Boot**.

> This REST API was developed using **Spring Boot** and **MySQL** to manage clients, products, and sales.  
> The main purpose was to learn **REST architecture**, **JPA entities**, **services**, **DTOs**, and **controllers** using **Spring Boot**.

---

## 🧩 Tecnologías usadas / Technologies Used

- **Java 21**
- **Spring Boot 3.4.10**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Lombok**
- **Swagger / OpenAPI**
- **Maven**

---

## ⚙️ Instalación y ejecución / Installation & Run

### 1️⃣ Clonar el repositorio / Clone the repository
```bash
git clone https://github.com/tu-usuario/java-project.git
cd java-project


## 🗄️ Scripts de Base de Datos (MySQL)

Antes de ejecutar la API, asegúrate de crear la base de datos y verificar las tablas:

```sql
CREATE DATABASE IF NOT EXISTS coderhouse;
USE coderhouse;

SELECT * FROM CLIENTES;
SELECT * FROM PRODUCTOS;
SELECT * FROM CATEGORIAS;
SELECT * FROM producto_cliente;
