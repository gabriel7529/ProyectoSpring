-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS bd_hotel;

-- Seleccionar la base de datos
USE bd_hotel;

-- Tabla de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL -- Asegúrate de tener una longitud definid
);

-- Tabla de Habitaciones
CREATE TABLE IF NOT EXISTS habitaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    precio DOUBLE NOT NULL
    
);

-- Tabla de Reservas
CREATE TABLE IF NOT EXISTS reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    habitacion_id INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    total DOUBLE NOT NULL,
    estado VARCHAR(20) DEFAULT 'Pendiente', -- Puede ser 'Confirmada', 'Cancelada', etc.
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE,
    FOREIGN KEY (habitacion_id) REFERENCES habitaciones(id) ON DELETE CASCADE
);


-- Insertar datos en la tabla de clientes
INSERT INTO clientes (nombre, email, telefono) VALUES
    ('Juan Pérez', 'juan@example.com', '123-456-7890'),
    ('María Rodríguez', 'maria@example.com', '987-654-3210'),
    ('Carlos Sánchez', 'carlos@example.com', '555-123-4567'),
    ('Laura García', 'laura@example.com', '111-222-3333'),
    ('Roberto Martínez', 'roberto@example.com', '444-555-6666'),
    ('Ana Gómez', 'ana@example.com', '777-888-9999'),
    ('Eduardo López', 'eduardo@example.com', '666-777-8888'),
    ('Carmen Torres', 'carmen@example.com', '999-000-1111'),
    ('Miguel González', 'miguel@example.com', '222-333-4444'),
    ('Isabel Fernández', 'isabel@example.com', '888-999-0000');

-- Insertar datos en la tabla de habitaciones
INSERT INTO habitaciones (numero, tipo, precio) VALUES
    (101, 'Individual', 50.00),
    (102, 'Doble', 80.00),
    (103, 'Suite', 120.00),
    (201, 'Individual', 55.00),
    (202, 'Doble', 85.00),
    (203, 'Suite', 130.00),
    (301, 'Individual', 60.00),
    (302, 'Doble', 90.00),
    (303, 'Suite', 140.00),
    (401, 'Individual', 65.00);
    
