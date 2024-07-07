--NOTAS: anadir facilitador id a tarjeta o ci


USE sys;
DROP DATABASE Banco;

CREATE DATABASE Banco;
GRANT ALL PRIVILEGES ON Banco.* TO 'pooUser'@'localhost';
USE Banco;

CREATE TABLE Cliente (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nombre_completo VARCHAR(100) NOT NULL,
 direccion VARCHAR(100) NOT NULL,
 num_telefono VARCHAR(9) NOT NULL
);

CREATE TABLE Tarjeta (
 id INT AUTO_INCREMENT PRIMARY KEY,
 num_tarjeta VARCHAR(19) NOT NULL,
 fecha_expiracion DATE NOT NULL,
 tipo_tarjeta VARCHAR(10) NOT NULL

);

CREATE TABLE Compras_Inteligentes(
 id_tarjeta_CI INT PRIMARY KEY,
 id_cliente_CI INT NOT NULL,
 id_facilitador_CI INT NOT NULL,
 INDEX(id_tarjeta_CI),
 FOREIGN KEY (id_tarjeta_CI) REFERENCES Tarjeta(id) ON DELETE CASCADE,
 INDEX(id_cliente_CI),
 FOREIGN KEY (id_cliente_CI) REFERENCES Cliente(id) ON DELETE CASCADE,
 INDEX(id_facilitador_CI),
 FOREIGN KEY (id_facilitador_CI) REFERENCES Facilitador(id) ON DELETE CASCADE
);

CREATE TABLE Facilitador(
 id INT AUTO_INCREMENT PRIMARY KEY,
 facilitador VARCHAR(40) NOT NULL
);

CREATE TABLE Compra(
 id INT AUTO_INCREMENT PRIMARY KEY,
 fecha_compra DATE NOT NULL,
 monto DECIMAL(10,2) NOT NULL,
 descripcion VARCHAR(100) NOT NULL,
 id_tarjeta_C INT NOT NULL,
 INDEX(id_tarjeta),
 FOREIGN KEY (id_tarjeta_C) REFERENCES Tarjeta(id) ON DELETE CASCADE
);
