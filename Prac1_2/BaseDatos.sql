show databases;

create database practica1_2;

use practica1_2;


create table LamportCentral(
	IdCentral int not null primary key,
	ContNuevo int (10), 
    ContLocal int (10)
);

create table Equipos(
	IdEquipos int not null primary key,
    Ip varchar(20),
    Nombre varchar(50),
    Intervalo int(10)
);


create table TiempoEquipos(
	IdTiempoEq int not null primary key,
    IdCentral int,
    IdEquipo int,
    cEquipo varchar(50),
    cMensaje varchar(50),
    IdEquipoNext int,
    foreign key (idCentral) references LamportCentral(IdCentral) 
    on delete cascade on update cascade,
    foreign key (idEquipo) references Equipos(IdEquipos)
    on delete cascade on update cascade,
    foreign key (IdEquipoNext) references Equipos(IdEquipos)
    on delete cascade on update cascade
);


