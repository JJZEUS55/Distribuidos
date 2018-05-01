create database jugador;
use jugador;

create table tirada(
	idTirada int(11) not null primary key,
    idPartida int(11),
    idDatos int(11),
    foreign key (idPartida) references partida(idPartida)
    on update cascade on delete cascade,
    foreign key (idDatos) references datos(num)
    on update cascade on delete cascade
);

create table partida(
	idPartida int(11) not null primary key,
    fecha date,
    horaInicio varchar(50), -- si no sirve cambialo por string
    horaFin varchar(50) -- si no sirve cambialo por string    
);

CREATE TABLE `datos` (
  `num` int(11) NOT NULL primary key,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `tipo1` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `tipo2` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `hp` int(11) NOT NULL,
  `ataque` int(11) NOT NULL,
  `defensa` int(11) NOT NULL
);


create table marca(
	idMarca int(11) not null primary key,
    idTirada int(11),
	tiempo varchar(50),
    foreign key (idTirada) references tirada(idTirada)
    on delete cascade on update cascade
);
