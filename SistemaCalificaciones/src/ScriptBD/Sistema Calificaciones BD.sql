/*HORARIOS*/
/*DE la primer hora A*//*
'Lunes y Miercoles de 7:00 a.m. a 8:45 a.m.'
'Martes y Jueves de 7:00 a.m.  a 8:45 a.m.'
'Lunes y Miercoles de 7:00 a.m. a 8:45 a.m. y viernes de 7:00 a.m. a 7:50 a.m.'
'Lunes y Miercoles de 7:00 a.m. a 8:45 a.m. y viernes de 7:50 a.m. a 8:45 a.m.'
'Martes y Jueves de 7:00 a.m. a 8:45 a.m. y viernes de 7:00 a.m. a 7:50 a.m.'
'Martes y Jueves de 7:00 a.m. a 8:45 a.m. y viernes de 7:50 a.m. a 8:45 a.m.'
*/
/*DE la segunda hora B*//*
'Lunes y Miercoles de 8:45 a.m. a 10:25 a.m.'
'Martes y Jueves de 8:45 a.m. a 10:25 a.m.'
'Lunes y Miercoles de 8:45 a.m. a 10:25 a.m. y viernes de 8:50 a.m. a 9:35 a.m.'
'Lunes y Miercoles de 8:45 a.m. a 10:25 a.m. y viernes de 9:35 a.m. a 10:25 a.m.'
'Martes y Jueves de 8:45 a.m. a 10:25 a.m. y viernes de 8:50 a.m. a 9:35 a.m.'
'Martes y Jueves de 8:45 a.m. a 10:25 a.m. y viernes de 9:35 a.m. a 10:25 a.m.'
*/
/*DE la tercer hora C*//*
'Lunes y Miercoles de 10:30 a.m. a 12:10 p.m.'
'Martes y Jueves de 10:30 a.m. a 12:10 p.m.'
'Lunes y Miercoles de 10:30 a.m. a 12:10 p.m. y viernes de 10:30 a.m. a 11:20 a.m.'
'Lunes y Miercoles de 10:30 a.m. a 12:10 p.m. y viernes de 11:20 a.m. a 12:10 p.m.'
'Martes y Jueves de 10:30 a.m. a 12:10 p.m.  y viernes de 10:30 a.m. a 11:20 a.m.'
'Martes y Jueves de 10:30 a.m. a 12:10 p.m.  y viernes de 11:20 a.m. a 12:10 p.m.'

*/
/*DE la tercer hora D*//*
'Lunes y Miercoles de 12:15 p.m. a 1:55 p.m.'
'Martes y Jueves de 12:15 p.m. a 1:55 p.m.'
'Lunes y Miercoles de 12:15 p.m. a 1:55 p.m. y viernes de 12:15 p.m. a 1:05 p.m.'
'Lunes y Miercoles de 12:15 p.m. a 1:55 p.m. y viernes de 1:05 p.m. a 1:55 p.m.'
'Martes y Jueves de 12:15 p.m. a 1:55 p.m.  y viernes de 12:15 p.m. a 1:05 p.m.'
'Martes y Jueves de 12:15 p.m. a 1:55 p.m.  y viernes de 1:05 p.m. a 1:55 p.m.'
*/


/*La de mike
LA CHINGONA*/

DROP SCHEMA IF EXISTS IngenieriaSoftware;
CREATE DATABASE IngenieriaSoftware;
USE IngenieriaSoftware;




USE IngenieriaSoftware;

/******************CREACIÓN DE TABLAS**************************/

CREATE TABLE Semestre(
	idSemestre VARCHAR(10) NOT NULL
);

CREATE TABLE Profesor(
	NumControl VARCHAR(10) NOT NULL,
	CURP VARCHAR(18) NOT NULL,
	Nombre VARCHAR(60) NULL,
	PrimerApellido VARCHAR(30) NULL,
	SegundoApellido VARCHAR(30) NULL,
	sexo VARCHAR(1),
	Telefono VARCHAR(10),
    correo VARCHAR(30),
	FechaNacimiento date NULL,/*******Decia Nacimeinto************/
	login VARCHAR(30),
    passw VARCHAR(30),
	usuarioALta VARCHAR(10),
	fechaAlta date,
	usuarioMod VARCHAR(10),
	fechaMod date
) ; 



/****** Object:  Table dbo.Materia    Script Date: 02/22/2018 12:12:26 ******/

CREATE TABLE Materia(
	clave_materia VARCHAR(10) NOT NULL,
	nombre VARCHAR(50) NULL,
	tipo VARCHAR(30) NULL,
	SATCA VARCHAR(5) NULL,
	semestre VARCHAR(10), 
	planEstudios VARCHAR(15),
    totalUnidades CHAR(2),
	descripcion text null,
	usuarioALta VARCHAR(10),
	fechaAlta date,
	usuarioMod VARCHAR(10),
	fechaMod date
) ;


/****** Object:  Table dbo.Grupos    Script Date: 02/22/2018 12:12:26 ******/

CREATE TABLE Grupos(
	ClaveGrupo VARCHAR(10) NOT NULL,
	Grupo VARCHAR(1) NULL,
	Campus VARCHAR(1) NULL,
	horaAsignada VARCHAR(200) NULL,
	aula VARCHAR(6) NULL,
	capacidad int,
	maximoFaltas int,
	FK_Catedratico VARCHAR(10) NOT NULL, 
	FK_CarreraMateria int NOT NULL
) ;

/****** Object:  Table dbo.Alumno    Script Date: 02/22/2018 12:12:26 ******/

CREATE TABLE Alumno(
	NumControl VARCHAR(10) NOT NULL,
	CURP VARCHAR(18) NOT NULL,
	Nombre VARCHAR(60) NOT NULL,
	PrimerApellido VARCHAR(50) NULL,
	SegundoApellido VARCHAR(50) NULL,
	sexo VARCHAR(1) NULL,
	fechaNacimiento date NULL,
    Domicilio VARCHAR(50),
    Telefono VARCHAR(10),
    correo VARCHAR(30),
    semestreAlumno CHAR(2),
    login VARCHAR(30),
    passw VARCHAR(30),
	usuarioAlta VARCHAR(10),
	fechaAlta date,
	usuarioMod VARCHAR(10),
	fechaMod date
) ENGINE=InnoDB CHARSET=latin1;


/****** Object:  Table dbo.Administrador    Script Date: 02/22/2018 12:12:26 ******/

CREATE TABLE Administrador(
	NumControl VARCHAR(10) NOT NULL,
	Nombre VARCHAR(60) NULL,
	PrimerApellido VARCHAR(50) NULL,
	SegundoApellido VARCHAR(50) NULL,
    sexo VARCHAR(1) NULL,
	Cargo VARCHAR(80) NULL,
	Telefono VARCHAR(10),
    correo VARCHAR(30),
    login VARCHAR(30),
    passw VARCHAR(30),
	usuarioALta VARCHAR(10),
	fechaAlta date,
	usuarioMod VARCHAR(10),
	fechaMod date
) ;


/*Falta agregar fecha de inicio y final del parcial*/
CREATE TABLE Parcial(	
	IDParcial VARCHAR (10) NOT NULL,
	numParcial char(5),
	UnidadesProgramadas int,
	UnidadesCubiertas int,
	usuarioALta VARCHAR(10),
	fechaAlta date,
	usuarioMod VARCHAR(10),
	fechaMod date,
	FK_claveGrupo VARCHAR(10) NOT NULL
);


CREATE TABLE Carrera(
	idCarrera VARCHAR(10) NOT NULL,
	nombreCarrera VARCHAR (30),
	descripcion text,
	usuarioALta VARCHAR(10),
	fechaAlta date,
	usuarioMod VARCHAR(10),
	fechaMod date
);

CREATE TABLE Carrera_Materia(
	idCarreraMateria int AUTO_INCREMENT PRIMARY KEY,
	FK_Carrera VARCHAR(10) NOT NULL, 
	FK_Materias VARCHAR(10)NOT NULL
);


CREATE TABLE Permiso(
	IDPermiso VARCHAR(10) NOT NULL,
	fechaInicio date,
    fechaValidacion date,
    validacion tinyint,
    estadoPermiso boolean,
    description TEXT,
	FK_Profesor VARCHAR(10)NOT NULL,
	FK_Administrador VARCHAR(10) NOT NULL,
    FK_ClaveGrupo VARCHAR(10) NOT NULL
);


CREATE TABLE Actividad(
	idActividad VARCHAR(10) NOT NULL
);



CREATE TABLE Fecha(
	idRegistro int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fecha1 date,
	fecha2 date,
	fecha3 date,
    fechaFinal date,
    periodo VARCHAR(30)
);

CREATE TABLE Calificacion(
	idCalificacion INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	FK_Alumno VARCHAR(10)NOT NULL,
	FK_ClaveGrupo VARCHAR(10) NOT NULL , 
	CP1  FLOAT,
	faltasP1 tinyint,
	estadoAprobacion1 VARCHAR(15) NOT NULL,   
	CP2  FLOAT,
	faltasP2 tinyint,
	estadoAprobacion2 VARCHAR(15) NOT NULL,   
	CP3  FLOAT,
	faltasP3 tinyint,  
	estadoAprobacion3 VARCHAR(15) NULL, 
	calificacionFinal FLOAT,
    faltasFinal tinyint,
    EstadoAprobacionFinal VARCHAR(15),
    CONSTRAINT AlumnoGrupo_Unique  UNIQUE(FK_Alumno, FK_ClaveGrupo)
);



/**************************************CONSTRAINTS************************************/

/*Constraints tabla Administrador*/
ALTER TABLE Administrador ADD PRIMARY KEY (NumControl); /*Llave primaria*/

ALTER TABLE Administrador  ADD
CONSTRAINT FK_administrador_usuarioAlta6
FOREIGN KEY (usuarioAlta)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Administrador  ADD
CONSTRAINT FK_administrador_usuarioMod6
FOREIGN KEY (usuarioMod)/*Llave foranea*/
REFERENCES Administrador(NumControl);



/*Constraints Tabla Semestre*/
ALTER TABLE Semestre ADD PRIMARY KEY (idSemestre); /*Llave primaria*/


/*Constraints Tabla Profesor*/
ALTER TABLE Profesor ADD PRIMARY KEY (NumControl); /*Llave primaria*/

ALTER TABLE Profesor  ADD
CONSTRAINT FK_administrador_usuarioAlta2
FOREIGN KEY (usuarioAlta)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Profesor  ADD
CONSTRAINT FK_administrador_usuarioMod2
FOREIGN KEY (usuarioMod)/*Llave foranea*/
REFERENCES Administrador(NumControl);


/*Constraints Tabla Materia*/
ALTER TABLE Materia ADD PRIMARY KEY (clave_materia); /*Llave primaria*/

ALTER TABLE Materia  ADD
CONSTRAINT FK_administrador_usuarioAlta3
FOREIGN KEY (usuarioAlta)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Materia  ADD
CONSTRAINT FK_administrador_usuarioMod3
FOREIGN KEY (usuarioMod)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Materia  ADD
CONSTRAINT FK_Materia_semestre
FOREIGN KEY (semestre)/*Llave foranea*/
REFERENCES Semestre(idSemestre);


/*Constraints tabla Grupos*/
ALTER TABLE Grupos ADD PRIMARY KEY (ClaveGrupo); /*Llave primaria*/

ALTER TABLE Grupos  ADD
CONSTRAINT FK_grupo_profesor
FOREIGN KEY (FK_Catedratico)/*Llave foranea*/
REFERENCES Profesor(NumControl)
ON UPDATE CASCADE;

ALTER TABLE Grupos  ADD
CONSTRAINT FK_grupo_carreramateria
FOREIGN KEY (FK_CarreraMateria)/*Llave foranea*/
REFERENCES Carrera_Materia(idCarreraMateria);


ALTER TABLE Alumno ADD PRIMARY KEY (NumControl); /*Llave primaria*/

ALTER TABLE Alumno  ADD
CONSTRAINT FK_administrador_usuarioAlta5
FOREIGN KEY (usuarioAlta)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Alumno  ADD
CONSTRAINT FK_administrador_usuarioMod5
FOREIGN KEY (usuarioMod)/*Llave foranea*/
REFERENCES Administrador(NumControl);

/*Constraints tabla Parcial*/
ALTER TABLE Parcial ADD PRIMARY KEY (IDParcial); /*Llave primaria*/

ALTER TABLE Parcial  ADD
CONSTRAINT FK_administrador_usuarioAlta7
FOREIGN KEY (usuarioAlta)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Parcial  ADD
CONSTRAINT FK_administrador_usuarioMod7
FOREIGN KEY (usuarioMod)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Parcial  ADD
CONSTRAINT FK_claveGrupo_parcial
FOREIGN KEY (FK_ClaveGrupo)/*Llave foranea*/
REFERENCES Grupos(ClaveGrupo);


/*Constraints tabla Carrera*/
ALTER TABLE Carrera ADD PRIMARY KEY (idCarrera); /*Llave primaria*/

ALTER TABLE Carrera  ADD
CONSTRAINT FK_administrador_usuarioAlta8
FOREIGN KEY (usuarioAlta)/*Llave foranea*/
REFERENCES Administrador(NumControl);

ALTER TABLE Carrera  ADD
CONSTRAINT FK_administrador_usuarioMod8
FOREIGN KEY (usuarioMod)/*Llave foranea*/
REFERENCES Administrador(NumControl);


/*Constraints tabla Carrera_Materia*/

ALTER TABLE Carrera_Materia  ADD
CONSTRAINT FK_carrera_materia
FOREIGN KEY (FK_Carrera)/*Llave foranea*/
REFERENCES Carrera(idCarrera);

ALTER TABLE Carrera_Materia  ADD
CONSTRAINT FK_carrera_materias
FOREIGN KEY (FK_Materias)/*Llave foranea*/
REFERENCES Materia(clave_materia);


/*Constraints tabla Permiso*/
ALTER TABLE Permiso ADD PRIMARY KEY (IDPermiso); /*Llave primaria*/

ALTER TABLE Permiso  ADD
CONSTRAINT FK_profesor_permiso
FOREIGN KEY (FK_Profesor)/*Llave foranea*/
REFERENCES Profesor(NumControl);

ALTER TABLE Permiso  ADD
CONSTRAINT FK_administrador_permiso
FOREIGN KEY (FK_Administrador)/*Llave foranea*/
REFERENCES Administrador(NumControl);


ALTER TABLE Permiso  ADD
CONSTRAINT FK_claveGrupo_permiso
FOREIGN KEY (FK_ClaveGrupo)/*Llave foranea*/
REFERENCES Grupos(ClaveGrupo);


/*Constraints tabla Actividad*/
ALTER TABLE Actividad ADD PRIMARY KEY (idActividad); /*Llave primaria*/



/*Constraints tabla Calificación*/
ALTER TABLE Calificacion  ADD
CONSTRAINT FK_alumno_semestre
FOREIGN KEY (FK_Alumno)/*Llave foranea*/
REFERENCES Alumno(NumControl)
ON DELETE CASCADE;

ALTER TABLE Calificacion  ADD
CONSTRAINT FK_claveGrupo_semestre
FOREIGN KEY (FK_ClaveGrupo)/*Llave foranea*/
REFERENCES Grupos(ClaveGrupo);




/*AUN NO INSERTO DATOS*/
/*INSERTAR DATOS */
/*DATOS Administrador*/
INSERT INTO Administrador VALUES ('1','Estefania','Arocas','Pasadas','M', 'Jefe de departamento', '4772341746', 'ppantera465@gmail.com', 'Admin0', '1234', '1','2018/04/25','1','2018/04/25');




/*DATOS Alumno*/
INSERT INTO Alumno VALUES('1','ARPE910806MGTOSE01','Estefania','Arocas','Pasadas','M','1991-08-06','PADRÓ , 109','546212121','africa@altecom.es',3,'Estefania','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('2','VIGQ930430MGTIAE95','Queralt','Viso','Gilabert','M','1993-04-30','CASA CORDELLAS , ','625215452','agata@hotmail.com',9,'Queralt','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('3','AYFJ961205HGTYRO24','Joan','Ayala','Ferreras','H','1996-12-05','DOCTOR FLEMING , 11','649212123','',7,'Joan','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('4','BATJ921208HGTAJO95','Joan','Baez','Tejado','H','1992-12-08','BERTRAND I SERRA , 11, 3R.','','albatros@wandoo.es',1,'Joan','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('5','BASM920303HGTTOA43','Marc','Bastardes','Soto','H','1992-03-03','CARRIÓ , 12, 5È A','','albert@intercom.es',9,'Marc','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('6','ANVJ860403HGTGFO48','Josep','Anguera','Vilafranca','H','1986-04-03','PIRINEUS , 10','','alien@intercom.es',8,'Josep','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('7','PAAE940805MGTSLT85','Esther','Pascual','Aloy','M','1994-08-05','JACINT VERDAGUER , 43','','amores@hotmail.com',3,'Esther','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('8','VAGL940202MGTLRA28','Laura','Vallés','Girvent','M','1994-02-02','NOU , 9, 2N.','645121212','anabel@altecom.es',11,'Laura','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('9','RAGR950225MGTARQ82','Raquel','Raya','Garcia','M','1995-02-25','JACINT VERDAGUER , 52, 3R, 1A.','','antiga@minorisa.es',5,'Raquel','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('10','ANCJ871015HGTDRO74','Joan','Andreu','Cruz','H','1987-10-15','JOAN MIRÓ , 10','648221111','ballador@hotmail.com',9,'Joan','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('11','BACM950728MGTAO 49','Maria Isabel','Baraldés','Comas','M','1995-07-28','JAUME GALOBART , 12','658444412','balladora@altecom.es',1,'Maria Isabel','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('12','BECA920209HGTNLD76','Adrià','Berengueras','Cullerés','H','1992-02-09','PINTOR SERT , 12, 1R., 1A.','654899994','barbilla@hotmail.com',11,'Adrià','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('13','LÓSG930531HGTÓUR17','Gerard','López','Sauceda','H','1993-05-31','BELLAVISTA , 30','','besugo@minorisa.es',11,'Gerard','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('14','ARME850118HGTRRL25','Eliot','Arnau','Moreno','H','1985-01-18','MONTURIOL , 10, 1R.','','bogart@terra.es',3,'Eliot','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('15','RAGJ961203HGTAVO33','Jordi','Raya','Gavilan','H','1996-12-03','JACINT VERDAGUER , 52, 2N., 4A.','','bond@terra.es',13,'Jordi','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('16','ZAFL960617HGTBGL85','Lluís','Zambudio','Figuls','H','1996-06-17','CASA NOVA , ','625411320','',12,'Lluís','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('17','BICL940818MGTDLA47','Laura','Bidault','Cullerés','M','1994-08-18','DE LA CAÇA , 12, 1R., C','690522200','cabeza larga@cataloniamail.com',8,'Laura','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('18','BIFJ860119HGTOTO13','Jordi','Biosca','Fontanet','H','1986-01-19','PINTOR SERT , 12, 2N., 1A.','647821111','',10,'Jordi','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('19','ZAFD931016MGTAGU17','Dounya','Zafra','Figuls','M','1993-10-16','CASA SARA , ','','',13,'Dounya','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('20','ALIJ950206HGTLCU06','Julio','Aleu','Icart','H','1995-02-06','ARTÈS , 1, 1R, 1A.','','cangur@intercom.es',6,'Julio','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('21','BATA900711HGTAOD36','Andreu','Badia','Torné','H','1990-07-11','GENERAL PRIM , 11, 2N.','','',4,'Andreu','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('22','MOGR850613HGTREA91','Ramon','Morales','Gese','H','1985-06-13','CAU DE LA GUINEU , 4','','',9,'Ramon','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('23','BLFD880209HGTATD71','David-Jese','Blanco','Fontanet','H','1988-02-09','JOAN SANMARTÍ , 12, 2N., 2A.','632548744','caparranas@altecom.es',2,'David-Jese','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('24','ALFA920213HGTVNR75','Aran','Alvarez','Fernández','H','1992-02-13','PROL. PADRÓ , 1, 3R. 1A.','632986321','',12,'Aran','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('25','GAAG890807MGTROE41','Gemma','Garcia','Almoguera','M','1989-08-07','SALLENT , 22, 2N.','647111021','',8,'Gemma','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('26','LIFI921208HGTBUV60','Ivan','Libori','Figueras','H','1992-12-08','JOAN MIRÓ , 3','625481111','carretero@intercom.es',8,'Ivan','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('27','BIPD960928HGTDUA91','David','Bidault','Pueyo','H','1996-09-28','LLUÍS CASTELLS , 12, 2N.','','cela@altecom.es',12,'David','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('28','BEJX931002HGTNOV28','Xavier','Benitez','Jose','H','1993-10-02','SANT VALENTÍ , 12, 1R.','','coco@hotmail.com',7,'Xavier','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('29','PAFM861218HGTSOA05','Mario','Pascual','Flores','H','1986-12-18','ÀNGEL GUIMERÀ , 43, 2N','','conejo@minorisa.es',13,'Mario','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('30','AYTJ860522HGTYOE24','Jesus','Ayala','Torné','H','1986-05-22','JAUME GALOBART , 11','','corcel@altecom.es',5,'Jesus','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('31','LIFG960416MGTSUE65','Gemma','Listan','Figueras','M','1996-04-16','AVINGUDA TRES , 3, 1R., 1A.','','',6,'Gemma','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('32','RAGS911031MGTSVL85','Silvia','Rasero','Gavilan','M','1991-10-31','JACINT VERDAGUER , 52, 2N., 1A.','648555214','curie@minorisa.es',6,'Silvia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('33','ARPA961105HGTNUB35','Albert','Arnalot','Puig','H','1996-11-05','DIPUTACIÓ , 10','621114452','dalí@wandoo.es',7,'Albert','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('34','MOGM940408MGTLRA13','Maria','Moliner','Garrido','M','1994-04-08','VIC , 39, 1R., 2A.','','',7,'Maria','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('35','GAGB861002MGTORE64','Berta','Galobart','Garcia','M','1986-10-02','GERMAN DURAN , 21','','dolça@cidet.com',3,'Berta','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('36','LÓGB921126MGTÓGE62','Berta','López','Garrigassait','M','1992-11-26','BELLAVISTA , 30','','dorada@intercom.es',4,'Berta','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('37','SÁGM900426MGTNÓR81','Mireia','Sánchez','Gómez','M','1990-04-26','NOU , 7, 2N.','645874112','elisa@altecom.es',4,'Mireia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('38','ALSG930807MGTVUE35','Gemma','Alavedra','Sunyé','M','1993-08-07','MANELIC , 1','632125482','encarna@hotmail.com',4,'Gemma','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('39','ALBM940710MGTIN 96','Maria Isabel','Aligué','Bonvehí','M','1994-07-10','DE LA PESCA , 1, 1R., 1A.','','entesa@altecom.es',12,'Maria Isabel','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('40','MAFT961209HGTMAO48','Toni','Mas','Franch','H','1996-12-09','PIRINEUS , 34','621445221','',2,'Toni','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('41','ALCA860108HGTLMJ25','Alejandro','Aloy','Compte','H','1986-01-08','PROL. JACINT VERDAGUER , 1, 2N., 2A.','','',11,'Alejandro','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('42','ASVJ850503HGTEE 87','Joan Martí','Asensio','Vega','H','1985-05-03','MALLORCA , 11','','et@altecom.es',5,'Joan Martí','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('43','BIPI940805MGTDÉG75','Ingrid','Bidault','Pérez','M','1994-08-05','SANT BENET , 12, 2N.','','',7,'Ingrid','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('44','ALCO860829HGTLII76','Oliver','Aloy','Codinachs','H','1986-08-29','PROL. PADRÓ , 1, 2N., 2A.','','',1,'Oliver','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('45','ALAS870610MGTINN21','Sandra','Altimiras','Armenteros','M','1987-06-10','ARTÈS , 1, 2N., 2A.','','experta@wandoo.es',8,'Sandra','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('46','BESJ931201HGTMNO88','Jordi','Belmonte','Sánchez','H','1993-12-01','JOAN XXIII , 12, 1R, 2A.','621455662','f5@wandoo.es',7,'Jordi','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('47','BAGM950713HGTJRA76','Marc','Bajona','Garcia','H','1995-07-13','BERTRAND I SERRA , 11, 3R.','','',3,'Marc','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('48','AGRJ900102MGTURR46','Jordina','Aguilar','Rodriguez','M','1990-01-02','LA SARDANA , 1','','',6,'Jordina','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('49','BASM881201MGTROA04','Maria José','Barriga','Soto','M','1988-12-01','GALILEU , 12','','fina@hotmail.com',5,'Maria José','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('50','AVMR910610MGTVSQ28','Raquel','Avila','Masjuan','M','1991-06-10','SANT VALENTÍ , 11','658954422','fuego@altecom.es',5,'Raquel','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('51','PAFE910114HGTRON73','Enric','Parramon','Flores','H','1991-01-14','JOAN XXIII , 43','','',8,'Enric','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('52','AGRM880707MGTUAA32','Marta','Aguilar','Ramos','M','1988-07-07','DE LA PAU , 1','','garota@hotmail.com',9,'Marta','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('53','AYAC860905MGTYSA85','Carla','Ayala','Alsina','M','1986-09-05','SANT ANTONI MARIA CLARET , 11','','',4,'Carla','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('54','ALTM920307MGTVO 53','Maria Noelia','Alvarez','Troyano','M','1992-03-07','AVINGUDA TRES , 1, 3R., 1A.','','gateta@cataloniamail.com',5,'Maria Noelia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('55','ALGC870813MGTLZS60','Cristina','Alins','González','M','1987-08-13','PROL. PADRÓ , 1, 2N., 1A.','652148555','',10,'Cristina','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('56','ACTC880126HGTCOR19','Carlos','Acuña','Tort','H','1988-01-26','SANT JOAN , 0, C, 3R. A','','groucho@intercom.es',9,'Carlos','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('57','ALTD940321HGTLAA80','David','Algué','Trancho','H','1994-03-21','PROL. JACINT VERDAGUER , 1, 1R., 1A.','','harpo@hotmail.com',8,'David','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('58','BACC870711HGTATS52','Cristian','Badia','Castillo','H','1987-07-11','JOAN XXIII , 11, 1R., 1A.','','hispa@cataloniamail.com',11,'Cristian','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('59','BEFJ900307HGTNO 12','Julio Alberto','Benitez','Flores','H','1990-03-07','LLUÍS CASTELLS , 12, 1R.','625477881','',6,'Julio Alberto','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('60','TOGS961011HGTRRE38','Sergi','Torruella','Garcia','H','1996-10-11','PADRÓ , 83','','huevo@terra.es',11,'Sergi','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('61','ALRA930927HGTERL06','Aleix','Alberich','Rodriguez','H','1993-09-27','SANT ISCLE , 1','648555552','james dean@intercom.es',5,'Aleix','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('62','ARPV880116MGTEUÒ26','Verònica','Armencot','Puig','M','1988-01-16','MONTSERRAT , 10','690254111','jéssica@hotmail.com',2,'Verònica','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('63','ALRM891018MGTIVR12','Mariona','Aligué','Rivera','M','1989-10-18','PROL. JACINT VERDAGUER , 1, 1R., 2A.','','',11,'Mariona','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('64','BARM861218HGTRRA25','Marc','Barriga','Riu','H','1986-12-18','TRABUCAIRES , 12','','',13,'Marc','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('65','POGG940122MGTTSE57','Gemma','Portella','Gispets','M','1994-01-22','JACINT VERDAGUER , 49, 4T., 2A.','632587441','',3,'Gemma','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('66','AGBR900708HGTIAC82','Ricard','Aguilera','Baena','H','1990-07-08','MANELIC , 1','','',1,'Ricard','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('67','ROGJ891205HGTRRU59','Juan','Rodriguez','García','H','1989-12-05','VERGE DE FÀTIMA , 6, BX., 2A.','602412052','llus@hotmail.com',9,'Juan','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('68','AGSM880414MGTUUA71','Marta','Aguilar','Sunyé','M','1988-04-14','SANT JOAN , 0, D, 3R. A','','',2,'Marta','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('69','BATN870405MGTRAT19','Natàlia','Barriga','Tardà','M','1987-04-05','GALILEU , 12','','madonna@wandoo.es',2,'Natàlia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('70','BALM960312MGTRAA19','Marta','Barcons','Lara','M','1996-03-12','ESPORTS , 12','','mata hari@intercom.es',12,'Marta','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('71','AGTL900628MGTIAA02','Laura','Aguilera','Tatjé','M','1990-06-28','JOSEP BOIXADERAS , 1','625411014','melanie@minorisa.es',6,'Laura','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('72','ALPJ960620HGTLRO70','Joan','Aleu','Prat','H','1996-06-20','CERVANTES , 1, 1R.','','melquíades@hotmail.com',9,'Joan','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('73','VAGA910802MGTLRE59','Alexia','Vallés','Girvent','M','1991-08-02','CERVANTES , 9, 1R.','654822414','merche@terra.es',10,'Alexia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('74','MOGF911107HGTLRR19','Ferran','Molina','Garrido','H','1991-11-07','JOAN XXIII , 39','698774444','midas@cataloniamail.com',4,'Ferran','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('75','ARHC850406MGTIRS37','Cristina','Arissa','Hermoso','M','1985-04-06','DOCTOR BARNARD , 10','602111254','mona lisa@minorisa.es',7,'Cristina','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('76','BAPJ940417HGTAAA97','José Antonio','Baraldés','Pardo','H','1994-04-17','ESPORTS , 12','','moro@minorisa.es',10,'José Antonio','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('77','SUGJ880120HGTARO71','Jordi','Suarez','Garzón','H','1988-01-20','DE LA PAU , 8','','mozart@wandoo.es',1,'Jordi','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('78','ARMB920315MGTRRG23','Begonya','Arpa','Moreno','M','1992-03-15','SANT VALENTÍ , 11','','',10,'Begonya','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('79','ALFI860830MGTLRG09','Ingrid','Aloy','Farrando','M','1986-08-30','PROL. PADRÓ , 1, 2N., 2A.','','',12,'Ingrid','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('80','LUGM950205HGTUIQ88','Miquel','Luque','Garrigasait','H','1995-02-05','VIC , 30 (TORROELLA)','625401445','napoleon@cidet.com',2,'Miquel','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('81','RIGA931009HGTIÓU92','Agustí','Ridó','Gómez','H','1993-10-09','SANT ISCLE , 6','','orondo@altecom.es',8,'Agustí','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('82','SAFA941016HGTAOT68','Antoni','Santamaria','Flotats','H','1994-10-16','JAUME BALMES , 70, 3R, 1A.','','papagayo@altecom.es',6,'Antoni','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('83','HEGJ860308HGTEÓO32','Joan','Herms','Gómez','H','1986-03-08','GERMAN DURAN , 27, 3R., 1A.','658955442','',8,'Joan','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('84','ARMM870218MGTTUN77','Mònica','Artigas','Maturano','M','1987-02-18','SANT JOAN , 11','','pasión@altecom.es',4,'Mònica','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('85','AGMG881008HGTUSR89','Gerard','Aguilar','Masana','H','1988-10-08','PUIG , 1','','',9,'Gerard','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('86','ALSG880603MGTIAE79','Gemma','Altimiras','Serarols','M','1988-06-03','PROL. JACINT VERDAGUER , 1, 2N., 2A.','','pelusa@hotmail.com',4,'Gemma','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('87','TOGM850316MGTSRA64','Maria','Torrescasana','Garcia','M','1985-03-16','RAMON I CAJAL , 81, 2N.','625458445','perla@cataloniamail.com',5,'Maria','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('88','ARPO950422HGTRIR47','Oriol','Ariza','Puigbó','H','1995-04-22','MORAGUES , 10','','',8,'Oriol','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('89','ALAV930317MGTVNG58','Virginia','Alvarez','Armenteros','M','1993-03-17','PROL. PADRÓ , 1, 3R., 2A.','','',10,'Virginia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('90','BATD961004HGTARA37','Damià','Baraldés','Tarragó','H','1996-10-04','FRANCESC DE VITÒRIA , 11, 4T 2A','625410101','psicosis@intercom.es',12,'Damià','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('91','GAGV911130HGTRRL69','Valentí','Garcia','García','H','1991-11-30','ALBÉNIZ , 22, 2N.','','',3,'Valentí','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('92','ARGA910616MGTRÓI67','Aina','Aroca','Gómez','M','1991-06-16','TRES ROURES , 10, 4T 2A','632514125','rebeca@cataloniamail.com',2,'Aina','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('93','ALRD850808HGTORA33','David','Alonso','Rodriguez','H','1985-08-08','PROL. PADRÓ , 1, 2N., 1A.','','rebelde@intercom.es',7,'David','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('94','CAGG940322HGTAÓR14','Gerard','Cano','Gómez','H','1994-03-22','ALBÉNIZ , 13, 2N., 1A.','','',2,'Gerard','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('95','ALMM941202MGTCLA97','Marta','Alcaide','Molina','M','1994-12-02','FONT DEL GAT , 1','632544145','',6,'Marta','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('96','AGPM930704MGTIRR32','Mireia','Aguilera','Prat','M','1993-07-04','MONTCAU , 1','','rene@intercom.es',8,'Mireia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('97','ALIE930725HGTACL73','Eloi','Alapont','Icart','H','1993-07-25','MONTURIOL , 1','658789875','',7,'Eloi','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('98','RIFA960808MGTVON66','Anna','Rivero','Florido','M','1996-08-08','VILATORRADA , 6','654112144','rica@terra.es',3,'Anna','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('99','AVMA850207MGTVSL39','Alba','Avila','Masjuan','M','1985-02-07','JAUME GALOBART , 11','','rockera@terra.es',13,'Alba','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('100','GRAS890402MGTNDN07','Sandra','Granados','Andrés','M','1989-04-02','SANT GENÍS , 25','','',2,'Sandra','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('101','FEGE870901HGTRSR34','Eric','Ferrer','Gasset','H','1987-09-01','VERGE DE FÀTIMA , 2, 3R., 1A.','','roman@wandoo.es',13,'Eric','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('102','AMML930405HGTMDL18','Lluís','Amigo','Modrego','H','1993-04-05','JAUME I , 10','654401401','',9,'Lluís','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('103','ABTC870425HGTBAS22','Cristian','Abdin','Tatjè','H','1987-04-25','SANT JOAN , 0, C, 1R., B','','romeo@intercom.es',6,'Cristian','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('104','CAGG940815HGTEOI78','Guillem','Canellas','Gomez','H','1994-08-15','JACINT VERDAGUER , 13','','',9,'Guillem','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('105','HIAD911130HGTDII06','Dimas','Hidalgo','Altimiras','H','1991-11-30','SANT BENET , 28, 2N., 2A.','602120214','ronc@cataloniamail.com',10,'Dimas','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('106','BAFA890521MGTTA 70','Ana Inés','Bastardas','Franch','M','1989-05-21','FONERIA , 12','','sabrosa@hotmail.com',9,'Ana Inés','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('107','ABMI861007MGTASV57','Ivet','Abadias','Masana','M','1986-10-07','ALFONS XII , 9, 4T 1A','622101451','salsa@cataloniamail.com',9,'Ivet','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('108','ARSJ960203MGTENÚ56','Júlia','Arevalo','Sanchez','M','1996-02-03','JAUME I , 10','654874412','',6,'Júlia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('109','ALMD850718HGTLUN64','Daniel','Alins','Mulet','H','1985-07-18','DE LA PESCA , 1, 1R., 2A.','610025001','',13,'Daniel','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('110','GAGA941215HGTRZB29','Abel','Garcia','González','H','1994-12-15','PIRINEUS , 22','','sincer@altecom.es',2,'Abel','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('111','ALPI921205MGTVCR02','Irene','Alvarez','Parcerisa','M','1992-12-05','PROL. JACINT VERDAGUER , 1, 3R., 2A.','','sincera@hotmail.com',11,'Irene','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('112','CAAA870117HGTADD53','Adrià','Casas','Andrés','H','1987-01-17','JAUME GALOBART , 14','','suau@hotmail.com',7,'Adrià','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('113','MOGJ900930HGTREA75','Jairo','Morales','Gese','H','1990-09-30','SANT JOAN, EDIFICI D , 3R A','600541125','superman@altecom.es',13,'Jairo','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('114','BAMC910317MGTATS98','Cristina','Baraldés','Martorell','M','1991-03-17','VIC , 119, 3R., 2A.','','superwoman@wandoo.es',7,'Cristina','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('115','ARGD850310HGTRÓA73','David','Aroca','Gómez','H','1985-03-10','CARLES BUÏGAS , 10, 1R., 1A.','','tablon@hotmail.com',4,'David','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('116','RUAA890512HGTUVD67','Adrià','Rueda','Alvarez','H','1989-05-12','JAUME BALMES , 67, 2N.','','tendre@terra.es',9,'Adrià','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('117','ALDL960331MGTVEU40','Lucia','Alvarez','Domenech','M','1996-03-31','PROL. PADRÓ , 1, 3R., 2A.','','teta@intercom.es',6,'Lucia','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('118','BOGC901014MGTOZA65','Carla','Boix','González','M','1990-10-14','DE LA CAÇA , 12, 2N., C','624487554','tomasa@hotmail.com',12,'Carla','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('119','BAMA950530HGTAND40','Adrià','Baraldés','Monrós','H','1995-05-30','VIC , 119, 2N., 1A.','','',10,'Adrià','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('120','AGMM920213MGTIRA72','Marta','Aguilera','Merino','M','1992-02-13','MORAGUES , 1','621145584','tremenda@altecom.es',10,'Marta','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('121','BADM850922HGTAHA94','Marc','Barea','Haene','H','1985-09-22','TRABUCAIRES , 12','','tripa@intercom.es',8,'Marc','1234','1','2018-05-19','1','2018-05-19');
INSERT INTO Alumno VALUES('122','BADA890129HGTRHL33','Alex','Barroso','Haene','H','1989-01-29','FONERIA , 12','','verruga@hotmail.com',6,'Alex','1234','1','2018-05-19','1','2018-05-19');



SELECT*FROM Alumno;



/*Poblar*/
INSERT INTO administrador  VALUES ('Ad0001','Miguel','Ramirez','Lira','H','Admin','4281033410','migueram_5@hotmailcom','Admin', '1234', '1','2018/04/04','1','2018/04/04');


/*Poblar Semestres*/
INSERT INTO Semestre VALUES(1);
INSERT INTO Semestre VALUES(2);
INSERT INTO Semestre VALUES(3);
INSERT INTO Semestre VALUES(4);
INSERT INTO Semestre VALUES(5);
INSERT INTO Semestre VALUES(6);
INSERT INTO Semestre VALUES(7);
INSERT INTO Semestre VALUES(8);
INSERT INTO Semestre VALUES(9);
INSERT INTO Semestre VALUES(10);
INSERT INTO Semestre VALUES(11);
INSERT INTO Semestre VALUES(12);
INSERT INTO Semestre VALUES(13);



/*Insert de Carrera y grupos*/
INSERT INTO carrera values
(	'ISC',	'Ingeniería en Sistemas Computacionales','Carrera enfocada a..',		'Ad0001',	'2018-04-06',	'Ad0001',	'2018-04-06'),
(	'TICS',	'Tecnologías de la Información y Comunicación',	'Carrera enfocada a..','Ad0001',	'2018-04-06',	'Ad0001',	'2018-04-06');

SELECT * FROM materia;/*Tambien lllenar la relacion carrera_materia*/
INSERT INTO materia values(	'SCC-1027',	'Métodos Numéricos',				'Especialidad',	'2-2-4',	'4',	'2016',	'5',	'Primer materia de cálculo',	'Ad0001',	'2018-04-06',	'Ad0001',	'2018-04-06');
INSERT INTO Carrera_Materia VALUES (null, "ISC", "SCC-1027");


INSERT INTO Materia VALUES('SCC-1007','Fundamentos de ingenieria de software','Equivalente','2-2-4','5','2016','6', 'Primer materia de la rama de ingenieria de software','Ad0001','2018-04-06','Ad0001','2018-04-06');
INSERT INTO Carrera_Materia VALUES (null, "ISC", "SCC-1007");
INSERT INTO Carrera_Materia VALUES (null, "TICS", "SCC-1007");





/*INSERT de Fecha*/
INSERT INTO Fecha VALUES("1", "2018-03-15", "2018-04-28", "2018-05-30", "2018-06-5", "Enero-Julio");










INSERT INTO profesor VALUES ('P0001','gthj052698hgtvgh41','Jorge','Mendoza','Zapata','H','4775688910','hotmail','1980/11/19','P0001', '1234', 'Ad0001','2018/04/04','Ad0001','2018/04/04');
INSERT INTO profesor VALUES ('P0002','gthj052df4f4d5gh50','Joel','Rico','Ibarra','H','4772341458','hotmail','1980/11/19','P0002', '1234', 'Ad0001','2018/04/04','Ad0001','2018/04/04');





                
                
/*PROCEDIMIENTOS ALMACENADOS*/
/*Proc Fechas--------------------------------------------------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS getFecha;
CREATE PROCEDURE getFecha ()
	SELECT * FROM Fecha;



DROP PROCEDURE IF EXISTS updateFecha;
CREATE PROCEDURE updateFecha(
fecha1 date,
fecha2 date,
fecha3 date,
fechaFinal date,
periodo VARCHAR(30))
UPDATE Fecha SET Fecha.fecha1 = fecha1, Fecha.fecha2 = fecha2, Fecha.fecha3 = fecha3, 
Fecha.fechaFinal = fechaFinal, Fecha.periodo = periodo
WHERE Fecha.idRegistro = 1;






/*Proc Administrador--------------------------------------------------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS getAdministradores;
CREATE PROCEDURE getAdministradores ()
	SELECT * FROM Administrador;

DROP PROCEDURE IF EXISTS addAdministrador;
CREATE PROCEDURE addAdministrador(
numControl VARCHAR(10), 
nombre VARCHAR(60), 
primerApellido VARCHAR(50), 
segundoApellido VARCHAR(50), 
sexo VARCHAR(1), 
cargo VARCHAR(80), 
telefono VARCHAR(10),
correo VARCHAR(30),
login VARCHAR(30),
passw VARCHAR(30),
usuarioMod VARCHAR(10))
INSERT INTO Administrador VALUES(numControl, nombre, primerApellido, segundoApellido, sexo, 
            cargo, telefono, correo, login, passw, usuarioMod, curdate(), usuarioMod, curdate());



DROP PROCEDURE IF EXISTS updateAdministrador;
CREATE PROCEDURE updateAdministrador(
actualID VARCHAR(10), 
nombre VARCHAR(60), 
primerApellido VARCHAR(50), 
segundoApellido VARCHAR(50), 
sexo VARCHAR(1), 
cargo VARCHAR(80), 
telefono VARCHAR(10),
correo VARCHAR(30),
login VARCHAR(30),
passw VARCHAR(30),
usuarioMod VARCHAR(10))
UPDATE Administrador SET Administrador.nombre = nombre, Administrador.primerApellido = primerApellido, Administrador.segundoApellido = segundoApellido, 
Administrador.sexo = sexo, Administrador.cargo = cargo, Administrador.telefono = telefono, Administrador.correo = correo, 
Administrador.login = login, Administrador.passw = passw, Administrador.usuarioMod = usuarioMod, 
Administrador.fechaMod = curdate() WHERE Administrador.NumControl = actualID;


DROP PROCEDURE IF EXISTS deleteAdministrador;
CREATE PROCEDURE deleteAdministrador(ID VARCHAR(10))
DELETE FROM Administrador WHERE NumControl = ID;         

DROP PROCEDURE IF EXISTS getBusquedaAdministrador;
CREATE PROCEDURE getBusquedaAdministrador( 
numControl VARCHAR(10), 
nombre VARCHAR(60), 
primerApellido VARCHAR(50), 
segundoApellido VARCHAR(50), 
sexo VARCHAR(1), 
cargo VARCHAR(80), 
telefono VARCHAR(10),
correo VARCHAR(30))
SELECT * from Administrador AS a
where a.numControl like (CONCAT('%',numControl,'%'))
AND  a.nombre like (CONCAT('%',nombre,'%'))
AND  a.primerApellido like (CONCAT('%',primerApellido,'%'))
AND  a.segundoApellido like (CONCAT('%',segundoApellido,'%'))
AND  a.sexo like (CONCAT('%',sexo,'%'))
AND  a.cargo like (CONCAT('%',cargo,'%'))
AND  a.telefono like (CONCAT('%',telefono,'%')) 
AND  a.correo like (CONCAT('%',correo,'%'));



/*Proc Alumno--------------------------------------------------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS getALumnos;
CREATE PROCEDURE getAlumnos ()
	SELECT * FROM Alumno;

DROP PROCEDURE IF EXISTS addAlumno;
CREATE PROCEDURE addAlumno(
ID VARCHAR(10), 
curp VARCHAR(18), 
nombre VARCHAR(60), 
apellidoP VARCHAR(50), 
apellidoM VARCHAR(50), 
sexo VARCHAR(1), 
fechaNac date,
domicilio VARCHAR(50),
telefono VARCHAR(10),
correo VARCHAR(30),
semestreAlumno CHAR(1),
user VARCHAR(30),
password VARCHAR(30),
usuarioMod VARCHAR(10))
INSERT INTO Alumno VALUES(ID, curp, nombre, apellidoP, apellidoM, sexo, fechaNac, domicilio, telefono, correo, semestreAlumno, user, password, usuarioMod, curdate(), usuarioMod, curdate());



DROP PROCEDURE IF EXISTS updateAlumno;
CREATE PROCEDURE updateAlumno(
actualID VARCHAR(10), 
curp VARCHAR(18), 
nombre VARCHAR(60), 
apellidoP VARCHAR(50), 
apellidoM VARCHAR(50), 
sexo VARCHAR(1), 
fechaNac date,
domicilio VARCHAR(50),
telefono VARCHAR(10),
correo VARCHAR(30),
semestreAlumno CHAR(1),
user VARCHAR(30),
password VARCHAR(30),
usuarioMod VARCHAR(10))
UPDATE Alumno SET Alumno.curp = curp, Alumno.Nombre = nombre, Alumno.PrimerApellido = apellidoP, 
Alumno.SegundoAPellido = apellidoM, Alumno.sexo = sexo, Alumno.fechaNacimiento = fechaNac, Alumno.domicilio = domicilio, 
Alumno.Telefono = telefono, Alumno.Correo = correo, Alumno.semestreAlumno = semestreAlumno, Alumno.login = user, Alumno.passw = password, Alumno.usuarioMod = usuarioMod, 
Alumno.fechaMod = curdate() WHERE Alumno.NumControl = actualID;



DROP PROCEDURE IF EXISTS deleteAlumno;
CREATE PROCEDURE deleteAlumno(ID VARCHAR(10))
DELETE FROM Alumno WHERE NumControl = ID;         

DROP PROCEDURE IF EXISTS getBusquedaAlumno;
CREATE PROCEDURE getBusquedaAlumno( NumControl VARCHAR(10), CURP VARCHAR(18), Nombre VARCHAR(60),PrimerApellido VARCHAR(50),SegundoApellido VARCHAR(50),sexo VARCHAR(1),
				 Domicilio VARCHAR(50), Telefono VARCHAR(10),correo VARCHAR(30), semestreAlumno CHAR(1)) 
SELECT * from Alumno AS a
where a.NumControl like (CONCAT('%',NumControl,'%'))
AND  a.CURP like (CONCAT('%',CURP,'%'))
AND  a.Nombre like (CONCAT('%',Nombre,'%'))
AND  a.PrimerApellido like (CONCAT('%',PrimerApellido,'%'))
AND  a.SegundoApellido like (CONCAT('%',SegundoApellido,'%'))
AND  a.sexo like (CONCAT('%',sexo,'%'))
AND  a.Domicilio like (CONCAT('%',Domicilio,'%'))
AND  a.Telefono like (CONCAT('%',Telefono,'%')) 
AND  a.correo like (CONCAT('%',correo,'%'))
AND  a.semestreAlumno like (CONCAT('%',semestreAlumno,'%'));




/*PROC Profesor--------------------------------------------------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS getProfesor;
CREATE PROCEDURE getProfesor ()
	SELECT * FROM Profesor;



DROP PROCEDURE IF EXISTS addProfesor;
CREATE PROCEDURE addProfesor(
ID VARCHAR(10), 
curp VARCHAR(18), 
nombre VARCHAR(60), 
apellidoP VARCHAR(50), 
apellidoM VARCHAR(50), 
sexo VARCHAR(1), 
telefono VARCHAR(10),
correo VARCHAR(30),
fechaNac date,
login VARCHAR(30),
passw VARCHAR(30),
usuarioMod VARCHAR(10))
INSERT INTO Profesor VALUES(ID, curp, nombre, apellidoP, apellidoM, sexo, telefono, correo, fechaNac, login,passw,usuarioMod, curdate(), usuarioMod, curdate());



DROP PROCEDURE IF EXISTS updateProfesor;
CREATE PROCEDURE updateProfesor(
actualID VARCHAR(10), 
curp VARCHAR(18), 
nombre VARCHAR(60), 
apellidoP VARCHAR(50), 
apellidoM VARCHAR(50), 
sexo VARCHAR(1), 
telefono VARCHAR(10),
correo VARCHAR(30),
fechaNac date,
login VARCHAR(30),
passw VARCHAR(30),
usuarioMod VARCHAR(10))
UPDATE profesor SET profesor.CURP = curp, profesor.Nombre = nombre, profesor.PrimerApellido = apellidoP, 
profesor.SegundoApellido = apellidoM, profesor.sexo = sexo, profesor.Telefono = telefono, profesor.correo = correo, 
profesor.FechaNacimiento = fechaNac, profesor.login=login, profesor.passw=passw, profesor.usuarioMod = usuarioMod, 
profesor.fechaMod = curdate() WHERE profesor.NumControl = actualID;



DROP PROCEDURE IF EXISTS deleteProfesor;
CREATE PROCEDURE deleteProfesor(ID VARCHAR(10))
DELETE FROM profesor WHERE NumControl = ID;




DROP PROCEDURE IF EXISTS getBusquedaProfesor;
CREATE PROCEDURE getBusquedaProfesor( NumControl VARCHAR(10), CURP VARCHAR(18), Nombre VARCHAR(60),PrimerApellido VARCHAR(30),SegundoApellido VARCHAR(30) ,sexo VARCHAR(1),Telefono VARCHAR(10), correo VARCHAR(30)) 
SELECT p.NumControl,p.CURP,p.Nombre,p.PrimerApellido,p.SegundoApellido,p.sexo,p.Telefono,p.correo,p.FechaNacimiento,p.login,p.passw,p.usuarioALta,p.fechaAlta,p.usuarioMod,p.fechaMod from Profesor AS p
where p.NumControl like (CONCAT('%',NumControl,'%'))
AND  p.CURP like (CONCAT('%',CURP,'%'))
AND  p.Nombre like (CONCAT('%',Nombre,'%'))
AND  p.PrimerApellido like (CONCAT('%',PrimerApellido,'%'))
AND  p.SegundoApellido like (CONCAT('%',SegundoApellido,'%'))
AND  p.sexo like (CONCAT('%',sexo,'%'))
AND  p.Telefono like (CONCAT('%',Telefono,'%')) 
AND  p.correo like (CONCAT('%',correo,'%'));



/*PROC Materias--------------------------------------------------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS geMaterias;
CREATE PROCEDURE getMaterias ()
	SELECT * FROM Materia;
    

 
 
DROP PROCEDURE IF EXISTS addMateria;
CREATE PROCEDURE addMateria(
clave_materia VARCHAR(10), 
nombre VARCHAR(50),  
tipo VARCHAR(30),
SATCA VARCHAR(5),
semestre VARCHAR(10),
planEstudios VARCHAR(15),
totalUnidades CHAR(2),
descripcion TEXT,
usuarioMod VARCHAR(10))
INSERT INTO Materia VALUES(clave_materia, nombre, tipo, SATCA, semestre, planEstudios, totalUnidades, descripcion, usuarioMod, curdate(), usuarioMod, curdate());



DROP PROCEDURE IF EXISTS updateMateria;
CREATE PROCEDURE updateMateria(
clave_materia VARCHAR(10), 
nombre VARCHAR(50), 
tipo VARCHAR(30),
SATCA VARCHAR(5),
semestre VARCHAR(10),
planEstudios VARCHAR(15),
totalUnidades CHAR(5),
descripcion TEXT,
usuarioMod VARCHAR(10))
UPDATE Materia SET Materia.clave_materia = clave_materia, Materia.nombre = nombre, Materia.tipo = tipo, Materia.SATCA = SATCA,
	Materia.semestre = semestre, Materia.planEstudios = planEstudios, Materia.totalUnidades = totalUnidades, Materia.descripcion = descripcion, Materia.usuarioMod = usuarioMod, 
	Materia.fechaMod = curdate() WHERE Materia.clave_materia = clave_materia;
    
    


DROP PROCEDURE IF EXISTS deleteMateria;
CREATE PROCEDURE deleteMateria(clave_materia VARCHAR(10))
DELETE FROM Materia WHERE Materia.clave_materia = clave_materia;


DROP PROCEDURE IF EXISTS getBusquedaMateria;
CREATE PROCEDURE getBusquedaMateria(clave_materia VARCHAR(10), nombre VARCHAR(50), tipo VARCHAR(30), SATCA VARCHAR(5), semestre VARCHAR(10) ,planEstudios VARCHAR(15), descripcion TEXT)
SELECT * from Materia AS m
where m.clave_materia like (CONCAT('%',clave_materia,'%'))
AND  m.nombre like (CONCAT('%',nombre,'%'))
AND  m.tipo like (CONCAT('%',tipo,'%'))
AND  m.SATCA like (CONCAT('%',SATCA,'%'))
AND  m.semestre like (CONCAT('%',semestre,'%'))
AND  m.planEstudios like (CONCAT('%',planEstudios,'%'))
AND  m.descripcion like (CONCAT('%',descripcion,'%'));


/*Procedimientos de Carrera*/
DROP PROCEDURE IF EXISTS getCarreras;
CREATE PROCEDURE getCarreras()
SELECT * FROM Carrera;


DROP PROCEDURE IF EXISTS addCarrera;
CREATE PROCEDURE addCarrera(
idCarrera VARCHAR(10),
nombreCarrera VARCHAR(50),
descripcion TEXT,
usuarioMod VARCHAR(10))
INSERT INTO Carrera VALUES (idCarrera, nombreCarrera, descripcion, usuarioMod, curdate(), usuarioMod, curdate());



DROP PROCEDURE IF EXISTS updateCarrera;
CREATE PROCEDURE updateCarrera(
idCarrera VARCHAR(50),
nombreCarrera VARCHAR(50),
descripcion TEXT,
usuarioMod VARCHAR(10))
UPDATE Carrera SET Carrera.nombreCarrera = nombreCarrera, Carrera.descripcion = descripcion, 
Carrera.usuarioMod = usuarioMod, Carrera.fechaMod = CURDATE() WHERE Carrera.idCarrera = idCarrera;

DROP PROCEDURE IF EXISTS deleteCarrera;
CREATE PROCEDURE deleteCarrera(idCarrera VARCHAR(10))
DELETE FROM Carrera WHERE Carrera.idCarrera = idCarrera;





/*Procedimientos de Materia-Carrera*/
DROP PROCEDURE IF EXISTS addCarreraMateria;
CREATE PROCEDURE addCarreraMateria(
FKcarrera VARCHAR(10),
FKmateria VARCHAR(10))
INSERT INTO Carrera_materia VALUES (null, FKCarrera, FKMateria);
/*all addCarreraMateria('ISC','SCC-1021');*/


DROP PROCEDURE IF EXISTS updateCarreraMateria;
CREATE PROCEDURE updateCarreraMateria(id VARCHAR(10),
FKcarrera VARCHAR(10),
FKmateria VARCHAR(10))
UPDATE Carrera_materia SET FK_Carrera = carrera, FK_Materia = materia WHERE idCarreraMateria = id;




DROP PROCEDURE IF EXISTS getCarrerasOfMateria;
CREATE PROCEDURE getCarrerasOfMateria(FKmateria VARCHAR(10))
SELECT FK_Carrera FROM Carrera_Materia WHERE FK_materias = FKmateria;



/*PROC Validacion logins--------------------------------------------------------------------------------------------------------------------------------------------------*/
/*ADMINISTRADOR*/
 DROP PROCEDURE IF EXISTS buscarLoginAdministrador;
 CREATE PROCEDURE buscarLoginAdministrador(login VARCHAR(30), passw VARCHAR(30))
 select * from Administrador as a
 where a.login = login
 AND a.passw =passw;
 
 call buscarLoginAdministrador('Admin','1234'); 
 
 /*PROFESOR*/
  DROP PROCEDURE IF EXISTS buscarLoginProfesor;
 CREATE PROCEDURE buscarLoginProfesor(login VARCHAR(30), passw VARCHAR(30))
 select * from Profesor as p
 where p.login = login
 AND p.passw =passw;
 
 
 /*ALUMNO*/
   DROP PROCEDURE IF EXISTS buscarLoginAlumno;
 CREATE PROCEDURE buscarLoginAlumno(login VARCHAR(30), passw VARCHAR(30))
 select * from Alumno as a
 where a.login = login
 AND a.passw =passw;
	
    

/*PROC GruposAlumnos*/
DROP PROCEDURE IF EXISTS filtrarAlumnosGrupos;
CREATE PROCEDURE filtrarAlumnosGrupos( NumControl VARCHAR(10), apellidoPaterno VARCHAR(60), semestreAlumno CHAR(1)) 
SELECT * from Alumno AS a
where a.NumControl like (CONCAT('%',NumControl,'%'))
AND  a.PrimerApellido like (CONCAT('%',apellidoPaterno,'%'))
AND  a.semestreAlumno like (CONCAT('%',semestreAlumno,'%'));


DROP PROCEDURE IF EXISTS addCalificacion;
CREATE PROCEDURE addCalificacion(	FK_Alumno VARCHAR(10), FK_ClaveGrupo VARCHAR(10))
INSERT INTO Calificacion (Calificacion.FK_Alumno, Calificacion.FK_ClaveGrupo) VALUES (FK_Alumno, FK_ClaveGrupo);

DROP PROCEDURE IF EXISTS addAlumnoAGrupo;
CREATE PROCEDURE addAlumnoAGrupo(FK_ClaveGrupo VARCHAR(10), FK_Alumno VARCHAR(10))
CALL addCalificacion(FK_Alumno, FK_ClaveGrupo);


DROP PROCEDURE IF EXISTS getAlumnosFromGrupo;
CREATE PROCEDURE getAlumnosFromGrupo(FK_ClaveGrupo VARCHAR(10))
SELECT * FROM Alumno INNER JOIN Calificacion ON Alumno.NumControl = Calificacion.FK_Alumno WHERE Calificacion.FK_ClaveGrupo = FK_ClaveGrupo;





/***********************************************************************************************PARA VISTA GESTION Grupos**/
/*---------------------------------------------Obtener todas las carreras*/
DROP PROCEDURE IF EXISTS getCarrerasGestionGrupos;
CREATE PROCEDURE getCarrerasGestionGrupos()
SELECT c.idCarrera,c.nombreCarrera
FROM carrera as c;
/*CALL getCarrerasGestionGrupos();*/

/*-------------------------------------Obtener todas las materias de una carrera seleccionada*/
DROP PROCEDURE IF EXISTS getMateriasDeCarrera;
CREATE PROCEDURE getMateriasDeCarrera(
idCarreraSeleccionada varchar(15)
)
SELECT m.clave_materia, m.nombre, m.tipo, m.SATCA, m.semestre, m.planEstudios, m.totalUnidades, m.descripcion, m.usuarioALta, m.fechaAlta, m.usuarioMod, m.fechaMod
FROM carrera as c INNER JOIN carrera_materia as cm ON cm.FK_Carrera=c.idCarrera
INNER JOIN Materia as m ON cm.FK_Materias=m.clave_materia
WHERE c.idCarrera=idCarreraSeleccionada;
/*CALL getMateriasDeCarrera('ISC');*/

/*-------------------------------------------------------get IDCarreraMateria*/
DROP PROCEDURE IF EXISTS getIdCarreraMateria;
CREATE PROCEDURE getIdCarreraMateria(
FK_Carrera varchar(50),
FK_Materias varchar(50)
)
SELECT cm.idCarreraMateria  FROM carrera_materia as cm
WHERE cm.FK_Carrera=FK_Carrera and cm.FK_Materias=FK_Materias;
/*CALL getIdCarreraMateria ('ISC','SCD-1027');*/



/*-------------------------------------------------------getGruposDeUnaCarrera*/
DROP PROCEDURE IF EXISTS getGruposDeUnaCarrera;
CREATE PROCEDURE getGruposDeUnaCarrera(
ID_CarreraMateria VARCHAR (10)
)
SELECT g.ClaveGrupo,g.Grupo,g.Campus,g.horaAsignada,g.aula,g.capacidad,g.maximoFaltas,g.FK_Catedratico, g.FK_CarreraMateria
FROM carrera as c 
INNER JOIN carrera_materia as cm ON c.idCarrera=cm.FK_Carrera 
INNER JOIN materia as m ON m.clave_materia=cm.FK_Materias
INNER JOIN grupos as g ON cm.idCarreraMateria=g.FK_CarreraMateria
INNER JOIN profesor as p ON g.FK_Catedratico=p.NumControl
WHERE cm.idCarreraMateria=ID_CarreraMateria;

/*CALL getGruposDeUnaCarrera('1');*/


/*------------------------------------------------------------------------------addGrupo*/
DROP PROCEDURE IF EXISTS addGrupo;
CREATE PROCEDURE addGrupo(
clave VARCHAR(10), 
letraGrupo VARCHAR(1), 
campus VARCHAR(1), 
horaAsignada VARCHAR(200), 
aula VARCHAR(6), 
capacidad VARCHAR(11), 
maxfaltas VARCHAR(11),
FK_profesor VARCHAR(10),
FK_carreraMateria VARCHAR(11))
INSERT INTO grupos VALUES(clave,letraGrupo,campus,horaAsignada,aula,capacidad,maxfaltas,FK_profesor,FK_carreraMateria); 
/*CALL addGrupo('0003','B','1','Martes y Jueves de 8:45 a.m. a 10:25 a.m. y viernes de 9:35 a.m. a 10:25 a.m.','lc2','30','1','P0001','1','Ad0001');*/

	

/*----------------------------------------------------------------------------UPDATE GRUPO************/
SET SQL_SAFE_UPDATES =0;
DROP PROCEDURE IF EXISTS updateGrupo;
CREATE PROCEDURE updateGrupo(
claveGru varchar(10),
gru varchar(1),
camp varchar(1),
horario varchar(200),
au varchar(6),
cap int,
maxFaltas int,
FK_Profesor varchar(10)
)
UPDATE grupos 
set Grupo=gru,Campus=camp,horaAsignada=horario,aula=au,capacidad=cap,maximoFaltas=maxFaltas,FK_Catedratico=FK_Profesor
WHERE ClaveGrupo=claveGru;

/*CALL updateGrupo('0001','B','2','Lunes y Miercoles de 10:30 a.m. a 12:10 p.m. y viernes de 10:30 a.m. a 11:20 a.m.','LC5',15,59,'P0001');*/
SELECT * FROM grupos;



/*----------------------------------------------------------getAlumnosDeUnGrupo*/
DROP PROCEDURE IF EXISTS getAlumnosDeUnGrupo;
CREATE PROCEDURE getAlumnosDeUnGrupo(
ID_CarreraMateria  VARCHAR (10),
claveGrupo VARCHAR (15)
)
SELECT al.NumControl,concat(al.PrimerApellido,' ',al.SegundoApellido,' ',al.Nombre) as nombreCompleto
FROM carrera as c 
INNER JOIN carrera_materia as cm ON c.idCarrera=cm.FK_Carrera 
INNER JOIN materia as m ON cm.FK_Materias=m.clave_materia
INNER JOIN grupos as g ON cm.idCarreraMateria=g.FK_CarreraMateria
INNER JOIN profesor as p ON g.FK_Catedratico=p.NumControl
INNER JOIN calificacion as cal ON g.ClaveGrupo=cal.FK_ClaveGrupo
INNER JOIN alumno as al ON al.NumControl=cal.FK_Alumno
WHERE cm.idCarreraMateria=ID_CarreraMateria and g.ClaveGrupo=claveGrupo;
/*CALL getAlumnosDeUnGrupo('2','0002');*/





/*---------------------------------------------------------------------Eliminar Alumnos de un grupo*/
select * from calificacion;

DROP PROCEDURE IF EXISTS deleteAlumnoDeGrupo;
CREATE PROCEDURE deleteAlumnoDeGrupo(
clave_Grupo varchar(15),
FKAlumno varchar(15)
)
DELETE from calificacion 
WHERE FK_ClaveGrupo=clave_Grupo and FK_Alumno=FKAlumno;

/*CALL deleteAlumnoDeGrupo('0002','10');*/

/*---------------------------------------------------------------eliminar grupo-----------------------*/

SELECT * from grupos;
DROP PROCEDURE IF EXISTS deleteGrupo;
CREATE PROCEDURE deleteGrupo(
id_grupo varchar(15)
)
DELETE FROM grupos
WHERE ClaveGrupo=id_grupo;

/*CALL deleteGrupo('0003');   */





























/***********************************************************************************************PARA VISTA GESTION PARCIAL**/
/*************************************************************************************SELECT***********/
/************************************************materias del profesor logeado*/
DROP PROCEDURE IF EXISTS getMateriasDeProfesor;
CREATE PROCEDURE getMateriasDeProfesor(
IDProfesor varchar(20)
)
SELECT m.clave_materia,m.nombre, m.tipo, m.SATCA, m.semestre, m.planEstudios, m.totalUnidades, m.descripcion,m.usuarioALta,m.fechaAlta,m.usuarioMod,m.fechaMod
FROM carrera_materia as cm
INNER JOIN materia as m ON cm.FK_Materias=m.clave_materia
INNER JOIN grupos as g ON cm.idCarreraMateria=g.FK_CarreraMateria
INNER JOIN profesor as p ON g.FK_Catedratico=p.NumControl
WHERE p.NumControl=IDProfesor 
group by clave_materia;
/*CALL getMateriasDeP;rofesor('P0001');*/


/*********************************************grupos  del profesor logeado de una materia*/
DROP PROCEDURE IF EXISTS getGruposDeMateriaDeProfesor;
CREATE PROCEDURE getGruposDeMateriaDeProfesor(
IDProfesor varchar(20),
ID_Materia varchar(15)
)
SELECT g.ClaveGrupo, g.Grupo, g.Campus, g.horaAsignada, g.aula, g.capacidad, g.maximoFaltas, g.FK_Catedratico, g.FK_CarreraMateria
FROM carrera_materia as cm
INNER JOIN materia as m ON cm.FK_Materias=m.clave_materia
INNER JOIN grupos as g ON cm.idCarreraMateria=g.FK_CarreraMateria
INNER JOIN profesor as p ON g.FK_Catedratico=p.NumControl
WHERE p.NumControl=IDProfesor and m.clave_materia=ID_Materia;
/*CALL getGruposDeMateriaDeProfesor('P0001','SCD-1027');*/



/*alumnos grupos  del profesor logedo de una materia (PARA TABLA CALIFICACIONES), vamos a usar el id del grupo seleccionado */
DROP PROCEDURE IF EXISTS getAlumnosYCalificacionDeUnGrupo;
CREATE PROCEDURE getAlumnosYCalificacionDeUnGrupo(
ID_grupo varchar(20)
)
SELECT al.NumControl, concat(al.PrimerApellido,' ',al.SegundoApellido,' ',al.Nombre) as nombreCompleto,al.semestreAlumno,cal.CP1,cal.faltasP1,cal.estadoAprobacion1,cal.CP2,cal.faltasP2,cal.estadoAprobacion2,cal.CP3,cal.faltasP3,cal.estadoAprobacion3,cal.calificacionFinal,cal.faltasFinal,cal.EstadoAprobacionFinal
FROM carrera as c 
INNER JOIN carrera_materia as cm ON c.idCarrera=cm.FK_Carrera 
INNER JOIN materia as m ON cm.FK_Materias=m.clave_materia
INNER JOIN grupos as g ON cm.idCarreraMateria=g.FK_CarreraMateria
INNER JOIN profesor as p ON g.FK_Catedratico=p.NumControl
INNER JOIN calificacion as cal ON g.ClaveGrupo=cal.FK_ClaveGrupo
INNER JOIN alumno as al ON al.NumControl=cal.FK_Alumno
WHERE g.ClaveGrupo=ID_grupo
ORDER BY nombreCompleto;



/*CALL getAlumnosYCalificacionDeUnGrupo('0002');	*/


/*regresar los datos para vista GEstionParcial (LA parte del encabezado)*/
DROP PROCEDURE IF EXISTS getEncabezadoGestionParcial;
CREATE PROCEDURE getEncabezadoGestionParcial(
ID_grupo varchar(20)
)
SELECT concat(p.PrimerApellido,' ',p.SegundoApellido,' ',p.Nombre) as nombreCompleto,m.nombre,c.nombreCarrera,g.ClaveGrupo,g.Grupo,g.Campus,g.aula,g.horaAsignada
FROM carrera as c 
INNER JOIN carrera_materia as cm ON c.idCarrera=cm.FK_Carrera 
INNER JOIN materia as m ON cm.FK_Materias=m.clave_materia
INNER JOIN grupos as g ON cm.idCarreraMateria=g.FK_CarreraMateria
INNER JOIN profesor as p ON g.FK_Catedratico=p.NumControl
INNER JOIN calificacion as cal ON g.ClaveGrupo=cal.FK_ClaveGrupo
INNER JOIN alumno as al ON al.NumControl=cal.FK_Alumno
WHERE g.ClaveGrupo=ID_grupo
group by g.ClaveGrupo;
/*CALL getEncabezadoGestionParcial('0002');	*/

/*CALL getAlumnosYCalificacionDeUnGrupo('0002');*/




/*-------------------------------------------------------MOdificarCalificaciones de Alumnos de un grupo*/
DROP PROCEDURE IF EXISTS updateCalificacionesDeUnGrupo;
CREATE PROCEDURE updateCalificacionesDeUnGrupo(
ID_Grupo VARCHAR(15),
NumControlAlumno VARCHAR(15),
cP1 float,
fP1 int,
EP1 VARCHAR(15),
cP2 float,
fP2 int,
EP2 VARCHAR(15),
cP3 float,
fP3 int,
EP3 VARCHAR(15),
cFinal float,
fFinal int,
EFinal VARCHAR(15)
)
UPDATE carrera as c 
INNER JOIN carrera_materia as cm ON c.idCarrera=cm.FK_Carrera 
INNER JOIN materia as m ON cm.FK_Materias=m.clave_materia
INNER JOIN grupos as g ON cm.idCarreraMateria=g.FK_CarreraMateria
INNER JOIN profesor as p ON g.FK_Catedratico=p.NumControl
INNER JOIN calificacion as cal ON g.ClaveGrupo=cal.FK_ClaveGrupo
INNER JOIN alumno as al ON al.NumControl=cal.FK_Alumno
set cal.CP1=cP1,cal.faltasP1=fP1,cal.estadoAprobacion1=EP1,cal.CP2=cP2,cal.faltasP2=fP2,cal.estadoAprobacion2=EP2,cal.CP3=cP3,cal.faltasP3=fP3,cal.estadoAprobacion3=EP3,cal.calificacionFinal=cFinal,cal.faltasFinal=fFinal,cal.EstadoAprobacionFinal=EFinal
WHERE g.ClaveGrupo=ID_Grupo and al.NumControl=NumControlAlumno;

/*CALL updateCalificacionesDeUnGrupo('0001','10',8,13,1,7,0,1,0,0,1,0,0,1)*/
/*CALL updateCalificacionesDeUnGrupo('0002','10',5,2,1,3,0,1,0,0,1,0,0,1)*/

/*CALL getAlumnosYCalificacionDeUnGrupo('0002');*/




/*Inserciones de Prueba*/



INSERT INTO GRUPOS VALUES ('0001','A','1','Lunes y Miercoles de 7:00 a.m. a 8:45 a.m. y viernes de 7:00 a.m. a 7:50 a.m.','LC1','30','','P0001','1');
INSERT INTO GRUPOS VALUES ('0002','B','1','Lunes y Miercoles de 8:45 a.m. a 10:25 a.m.','LC1','30','','P0001','3');

INSERT INTO calificacion values(null,'1','0001',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'10','0001',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'100','0001',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'101','0001',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'102','0001',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');

INSERT INTO calificacion values(null,'10','0002',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'112','0002',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'113','0002',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'114','0002',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'115','0002',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');
INSERT INTO calificacion values(null,'116','0002',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado',0,0,'Reprobado');




/********************************************PARA TABLA Y GRAFICAS*/
/*Obtener tabla de vistagraficas alumno*/
DROP PROCEDURE IF EXISTS getTablaVistaGraficas;
CREATE PROCEDURE getTablaVistaGraficas(
numControl varchar(10)
)
SELECT m.clave_materia,m.nombre,g.ClaveGrupo,cal.faltasP1,cal.CP1,cal.faltasP2,cal.CP2,cal.faltasP3,cal.CP3,cal.faltasFinal,calificacionFinal,concat(p.PrimerApellido,' ',p.SegundoApellido,' ',p.nombre) as nombreCompletoProfesor
FROM 
materia as m INNER JOIN carrera_materia as cm ON m.clave_materia=cm.FK_Materias
INNER JOIN grupos as g ON g.FK_CarreraMateria=cm.idCarreraMateria
INNER JOIN calificacion as cal ON g.clavegrupo=cal.FK_ClaveGrupo
INNER JOIN ALumno as a ON cal.FK_Alumno=a.numControl
INNER JOIN PROFESOR as p ON p.numControl=g.FK_Catedratico
WHERE a.numControl=numControl;

/*CALL getTablaVistaGraficas('10'); */



/*--------------------------------------------------------------------------------------------*/

/*Obtener tabla de vistagraficas de un alumno que tenga como profesor el especificado*/
DROP PROCEDURE IF EXISTS getTablaVistaGraficasAlumnosDeUnProfesor;
CREATE PROCEDURE getTablaVistaGraficasAlumnosDeUnProfesor(
numControlAlumno varchar(10),
numControlProfesor varchar(10)
)
SELECT m.clave_materia,m.nombre,g.ClaveGrupo,cal.faltasP1,cal.CP1,cal.faltasP2,cal.CP2,cal.faltasP3,cal.CP3,cal.faltasFinal,calificacionFinal,concat(p.PrimerApellido,' ',p.SegundoApellido,' ',p.nombre) as nombreCompletoProfesor
FROM 
materia as m INNER JOIN carrera_materia as cm ON m.clave_materia=cm.FK_Materias
INNER JOIN grupos as g ON g.FK_CarreraMateria=cm.idCarreraMateria
INNER JOIN calificacion as cal ON g.clavegrupo=cal.FK_ClaveGrupo
INNER JOIN ALumno as a ON cal.FK_Alumno=a.numControl
INNER JOIN PROFESOR as p ON p.numControl=g.FK_Catedratico
WHERE a.numControl=numControlAlumno and p.numControl=numControlProfesor;

/*CALL getTablaVistaGraficasAlumnosDeUnProfesor('10','P0001'); */



/*******************************************************************Obtener grupos del profesor*/
DROP PROCEDURE IF EXISTS getGruposProfesor;
CREATE PROCEDURE getGruposProfesor(
numControlProfesor varchar(10)
)
SELECT m.clave_materia,m.nombre,g.ClaveGrupo
FROM materia as m INNER JOIN carrera_materia as cm ON m.clave_materia=cm.FK_Materias
INNER JOIN grupos as g ON g.FK_CarreraMateria=cm.idCarreraMateria
INNER JOIN PROFESOR as p ON p.numControl=g.FK_Catedratico
where P.NumControl=numControlProfesor
group by g.ClaveGrupo;
/*CALL getGruposProfesor('P0001')*/


/*******************************************************************Obtener informacion para TAblas profesor*/
/*clave,materia,grupo, porcentajeAprobados, reprobados, desertados X4;*/
/*Obtener todos los estados de los alumnos en un grupo*/
DROP PROCEDURE IF EXISTS getTablaGrupoProfesor;
CREATE PROCEDURE getTablaGrupoProfesor(
claveGrupo varchar(10)
)
SELECT m.clave_materia,m.nombre,g.ClaveGrupo,a.numControl, cal.estadoAprobacion1,cal.estadoAprobacion2,cal.estadoAprobacion3,cal.EstadoAprobacionFinal
FROM materia as m INNER JOIN carrera_materia as cm ON m.clave_materia=cm.FK_Materias
INNER JOIN grupos as g ON g.FK_CarreraMateria=cm.idCarreraMateria
INNER JOIN calificacion as cal ON g.clavegrupo=cal.FK_ClaveGrupo
INNER JOIN ALumno as a ON cal.FK_Alumno=a.numControl
INNER JOIN PROFESOR as p ON p.numControl=g.FK_Catedratico
where g.ClaveGrupo=claveGrupo
group by a.numControl;

/*CALL getTablaGrupoProfesor('0002')*/
