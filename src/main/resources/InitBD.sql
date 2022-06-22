/*
	Inicio de Proyecto
    1.- Se debe eliminar la BD en caso de que exista, asi se crea desde cero
    2.- Se debe crear la BD (Solo crear la BD, no insertar nada por ahora. La tablas se crean desde java)
    3.- Antes de inicar el Proyecto, verificar en el archivo application.properties en user y pass de la BD
    4.- Iniciar el Proyecto TEST Java (DemoApplicationTest.java), asi se crean las tablas y los usuarios
	5.- Una vez terminada la ejecucion de DemoApplicationTest.java, Ejecutar los INSERT's para tener datos 
		precargados en el proyecto
	6.- Por ultimo ejecutar DemoApplication.java lo cual dará inicio al proyecto
*/

/************************ CREACION DE LA BD (Pasos 1 y 2) *******************************/
-- DROP SCHEMA `bdspring`;
CREATE SCHEMA IF NOT EXISTS `bdspring`;

/************************ CREACION DE TABLAS (Pasos 3 y 4) *******************************/

/************************ INSERT'S DE LA BD (Paso 5) *******************************/
USE `bdspring`;
-- Creacion de Datos para Departamentos
INSERT INTO `bdspring`.`departamentos` (`nombre_depto`) VALUES ('Desarrollo Productivo y Tecnologico');

-- Creacion de Datos para Carreras
INSERT INTO `bdspring`.`carreras` (`nombre_carrera`, `id_dpto`) VALUES ('Sistemas', '1');

-- Creacion de Datos para Materias
INSERT INTO `bdspring`.`materias` (`cod_materia`, `nombre_materia`, `id_carrera`) VALUES ('8016', 'Programacion de Computadoras', '1');
INSERT INTO `bdspring`.`materias` (`cod_materia`, `nombre_materia`, `id_carrera`) VALUES ('8017', 'Matematicas I', '1');

-- Creacion de datos para edificios
INSERT INTO `bdspring`.`edificios` values (1,"Jose Hernandez");
INSERT INTO `bdspring`.`aulas` values (1,1,1);
/************************ INICIAR PROYECTO JAVA SPRING BOOT (Paso 6) *******************************/