# Full Slab E-commerce System

## Descripción del Proyecto
Sistema de gestión de catálogo y ventas diseñado para optimizar el procesamiento de datos de productos y el control de órdenes de compra. El sistema asegura la integridad histórica de los datos y permite la escalabilidad del negocio.

## Arquitectura de Datos
El sistema utiliza una base de datos relacional (PostgreSQL) garantizando consistencia ACID. A continuación, el esquema relacional definido:

## Estructura de la Base de Datos
El diseño implementa las siguientes entidades principales:
* **Users:** Gestión de identidades con control de roles y auditoría (Soft Delete).
* **Categories:** Estructura jerárquica mediante auto-referencia para subcategorías.
* **Products:** Registro de inventario con trazabilidad de precios y stock.
* **Orders & Order_Items:** Manejo de transacciones con persistencia de precio histórico para asegurar la validez de los reportes financieros.

## Configuración Técnica
- **Motor:** PostgreSQL
- **Diagrama Entidad-Relación:** (Adjunto en `/docs/erd.png`)
- **Script de Inicialización:** El archivo `schema.sql` contiene todas las sentencias DDL para desplegar el esquema.

## Auditoría de Datos
Todas las entidades clave implementan los campos `createdAt` y `deletedAt` (Soft Delete), permitiendo mantener el historial transaccional sin pérdida de datos ante bajas de catálogo o de usuarios.

---
*Desarrollado para Ruben Horacio Giulietti*