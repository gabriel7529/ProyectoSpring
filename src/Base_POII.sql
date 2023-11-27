CREATE DATABASE GestorCamisetas;
USE GestorCamisetas;

CREATE TABLE Camisetas (
    CamisetaID VARCHAR(10) PRIMARY KEY,
    Descripcion VARCHAR(100),
    Talla VARCHAR(10),
    Color VARCHAR(50),
    PrecioUnitario DECIMAL(10, 2),
    Stock INT
);

CREATE TABLE Administrador (
    AdminID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Usuario VARCHAR(50) UNIQUE,
    Contraseña VARCHAR(100)
);

CREATE TABLE Ventas (
    VentaID INT PRIMARY KEY,
    FechaVenta DATETIME,
    Cantidad INT,
    PrecioUnitario DECIMAL(10, 2),
    Total DECIMAL(10, 2),
    CamisetaID VARCHAR(10),
    AdminID INT,
    FOREIGN KEY (CamisetaID) REFERENCES Camisetas(CamisetaID),
    FOREIGN KEY (AdminID) REFERENCES Administrador(AdminID)
);

CREATE TABLE DetalleVentas (
    DetalleVentaID INT PRIMARY KEY,
    VentaID INT,
    Cantidad INT,
    Total DECIMAL(10, 2),
    AdminID INT,
    FOREIGN KEY (VentaID) REFERENCES Ventas(VentaID),
    FOREIGN KEY (AdminID) REFERENCES Administrador(AdminID)
);
