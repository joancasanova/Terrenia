
-- Creación de la base de datos Terrenia
CREATE DATABASE terrenia;
GO

USE terrenia;
GO

-- Creacion de la tabla Terrenos
CREATE TABLE Terrenos (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    tamaño FLOAT NOT NULL,
    tipo_terreno NVARCHAR(255) NOT NULL CHECK (tipo_terreno IN ('latifundio', 'finca')),
    ubicacion NVARCHAR(255) NOT NULL
);

-- Creacion de la tabla Parcelas
CREATE TABLE Parcelas (
    id_parcela INT IDENTITY(1,1) PRIMARY KEY,
    tamaño FLOAT NOT NULL,
    limites NVARCHAR(255) NOT NULL,
    ubicacion NVARCHAR(255) NOT NULL CHECK (ubicacion IN ('norte', 'sur', 'este', 'oeste')),
    id_terreno INT NOT NULL,
    alquilada BIT NOT NULL, -- 0 para no alquilada y 1 para alquilada
    FOREIGN KEY (id_terreno) REFERENCES Terrenos(ID)
);

-- Creacion de la tabla Arrendatarios
CREATE TABLE Arrendatarios (
    DNI NVARCHAR(255) PRIMARY KEY,
    nombre NVARCHAR(255) NOT NULL,
    edad INT NOT NULL,
    sexo NVARCHAR(50),
    email NVARCHAR(255),
    info_ingreso NVARCHAR(255) NOT NULL CHECK (info_ingreso IN ('contrato', 'nomina', 'aval_persona', 'aval_bancario')),
    fecha_registro DATE NOT NULL
);

-- Creacion de la tabla Alquileres
CREATE TABLE Alquileres (
    id_parcela INT,
    id_arrendatario NVARCHAR(255),
    fecha_inicio DATE NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    periodo INT NOT NULL,
    PRIMARY KEY (id_parcela, fecha_inicio),
    FOREIGN KEY (id_parcela) REFERENCES Parcelas(id_parcela)
);
