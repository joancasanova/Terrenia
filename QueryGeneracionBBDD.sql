-- Creacion de la tabla Terrenos
CREATE TABLE Terrenos (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    tamaño FLOAT NOT NULL,
    tipo_terreno NVARCHAR(255) NOT NULL,
    ubicacion NVARCHAR(255) NOT NULL
);

-- Creacion de la tabla Parcelas
CREATE TABLE Parcelas (
    id_parcela INT IDENTITY(1,1) PRIMARY KEY,
    tamaño FLOAT NOT NULL,
    limites NVARCHAR(255) NOT NULL,
    ubicacion NVARCHAR(255) NOT NULL,
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
    info_ingreso NVARCHAR(255),
    fecha_registro DATE NOT NULL
);

-- Creacion de la tabla Alquileres
CREATE TABLE Alquileres (
    id_parcela INT,
    id_arrendatario NVARCHAR(255),
    fecha_inicio DATE NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    periodo NVARCHAR(255) NOT NULL,
    PRIMARY KEY (id_parcela, id_arrendatario), -- Suponemos que una parcela no puede ser alquilada por mas de un arrendatario a la vez
    FOREIGN KEY (id_parcela) REFERENCES Parcelas(id_parcela),
    FOREIGN KEY (id_arrendatario) REFERENCES Arrendatarios(DNI)
);
