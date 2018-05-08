create database pokemonCom;

use pokemonCom;

CREATE TABLE `datos` (
  `num` int(11) NOT NULL primary key,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `tipo1` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `tipo2` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `hp` int(11) NOT NULL,
  `ataque` int(11) NOT NULL,
  `defensa` int(11) NOT NULL
);

create table partida(
	idPartida int(11) not null primary key,
    fecha varchar(50),
    horaInicio varchar(50), -- si no sirve cambialo por string
    horaFin varchar(50) -- si no sirve cambialo por string    
);

create table jugador(
	idJugador int(11) not null primary key,
    ip varchar(30),
    nombre varchar(30),
    latencia varchar(30)
);

create table tirada(
	idTirada int(11) not null primary key,
    idPartida int(11),
    idDatos int(11),
    foreign key (idPartida) references partida(idPartida)
    on update cascade on delete cascade,
    foreign key (idDatos) references datos(num)
    on update cascade on delete cascade
);

create table jugadorCartas(
	idJugadorCartas int(11) not null primary key,
    idJugador int(11),
    idTirada int(11),
    tiempoJugador varchar(50),
    lugarJugador varchar(50),
    foreign key (idJugador) references jugador(idJugador)
    on update cascade on delete cascade,
    foreign key (idTirada) references tirada(idTirada)
    on update cascade on delete cascade    
);







