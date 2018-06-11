drop database pokepro1;

create database pokePro1;

use pokePro1;




create table cartas(
	num int (11) not null primary key,
	nombre varchar (30),
	tipo1 varchar(30),
	tipo2 varchar(30),
	hp int(11),
	ataque int(11),
	defensa int (11)
);

create table jugadores(
	numJugador int(11) not null primary key,
    ipJugador varchar(30),
    puerto int(11)
);

create table jugadorCartas(
	numJugador int(11),
    cartaSeleccionada int(11),
    horaLamport varchar(30),
    ronda int(3),
    foreign key (numJugador) references jugadores(numJugador)
    on delete cascade on update cascade,
    foreign key (cartaSeleccionada) references cartas(num)
    on delete cascade on update cascade
);

create table servidor(
	clienteConectado int(11),
	cartasRepartidas int (11),
    horaLamport varchar(30),
    ronda int(3),
    foreign key (cartasRepartidas) references cartas(num)
    on delete cascade on update cascade,
    foreign key (clienteConectado) references jugadores(numJugador)
    on delete cascade on update cascade
);


SET SQL_SAFE_UPDATES = 0;

select * from cartas; -- LISTO
select * from jugadores; -- LISTO
select * from jugadorCartas; -- nada prog
select * from servidor; -- falta ronda y cliente


delete from cartas;
delete from servidor;
delete from jugadores;
delete from jugadorCartas;


-- Prueba para obtener el mazo de un jugador
select c.num, c.nombre, c.tipo1, c.hp, c.ataque, c.defensa
from cartas c, jugadorCartas j
where c.num = j.cartaSeleccionada
and j.numJugador = 1;


-- Replicacar BD
CREATE USER 'root'@'192.168.0.14' IDENTIFIED BY '1234';
CREATE USER 'root'@'192.168.0.3' IDENTIFIED BY '1234';


GRANT ALL ON pokepro1.* TO 'root'@'192.168.0.3' IDENTIFIED BY '1234';
GRANT ALL ON *.* TO 'root'@'192.168.0.3' IDENTIFIED BY '1234';


GRANT REPLICATION SLAVE on *.* TO 'root'@'192.168.0.14';

-- Base de prueba
drop database clusterdb1;
create database clusterdb1;
create table clusterdb1.simples (id int not null primary key);
insert into clusterdb1.simples values (999),(1),(2),(3), (4), (5);
select * from clusterdb1.simples;

SELECT COUNT(1) FROM information_schema.processlist WHERE user='root';
sHOW MASTER STATUS;

SHOW PROCESSLIST;

SHOW SLAVE HOSTS;

SET GLOBAL general_log = 'ON';


show tables;
desc cartas;
SHOW FULL COLUMNS FROM cartas;


