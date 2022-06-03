################################
## CREACIÓ DE LA BASE DE DADES##
################################

drop database dama_gimnas;
create database dama_gimnas;
use dama_gimnas;

drop table if exists sales;
create table sales(
	num_sala int (5) auto_increment,
    primary key (num_sala),
    descripció varchar (255),
    aforament int(10)
);

drop table if exists activitats;
create table activitats(
	ID int (9) auto_increment,
    primary key (id),
    nomActivitat varchar (50),
    descripció varchar (500),
    hora time,
    numero_sala int (5) not null,
    foreign key (numero_sala) references sales (num_Sala),
    imatge varchar(50),
    dia_semana int(1)
);

drop table if exists monitors;
create table monitors(
	DNI varchar(9),
    primary key (DNI),
    nom varchar (20),
    cognom varchar (35),
    telefon int(9),
    email varchar(50),
    sou int(5),
    sala_numero int(5) not null,
    foreign key (sala_numero) references sales (num_Sala)
);

drop table if exists socis;
create table socis(
	DNI varchar(9),
    primary key (DNI),
    nom varchar(15),
    cognom varchar(35),
    sexe varchar(10),
    data_naix date,
    mobil int(9),
    email varchar(50),
    usuari varchar(20),
    contrasenya varchar(32),
    condicio_fisica varchar(100),
    comunicacio_comercial int(1),
    compte_bancari varchar(50)
);

drop table if exists activitat_lliure;
create table activitat_lliure(
	IDL int(9) not null,
    foreign key (IDL) references activitats (ID)
);

drop table if exists activitat_colectiva;
create table activitat_colectiva(
	IDC int(9) not null,
    foreign key (IDC) references activitats (ID)
);

drop table if exists reserva_lliure;
create table reserva_lliure(
	idreserva int(5) auto_increment,
    primary key(idreserva),
	ID int(9) not null,
    foreign key (ID) references activitat_lliure (IDL),
    DNI varchar(9) not null,
    foreign key (DNI) references socis (DNI),
    data_act date,
    hora time
);

drop table if exists reserva_colectiva;
create table reserva_colectiva(
	idreserva int(5) auto_increment,
    primary key(idreserva),
	ID int(9) not null,
    foreign key (ID) references activitat_colectiva (IDC),
    DNI varchar(9) not null,
    foreign key (DNI) references socis (DNI),
    data_act date,
    hora time
);

drop table if exists curses;
create table curses(
	ID int(9) auto_increment,
    primary key(ID),
    nom varchar(30)
);

drop table if exists participar;
create table participar(
	DNI varchar(9) not null,
    foreign key (DNI) references socis (DNI),
    ID int(9) not null,
    foreign key (ID) references curses (ID),
    data date
);

drop table if exists web; 
create table web(
	ID int(5) auto_increment,
    primary key(ID)
);

drop table if exists registrat;
create table registrat(
	DNI varchar(9) not null,
    foreign key (DNI) references socis (DNI),
    ID int(9) not null,
    foreign key (ID) references web (ID),
    data_alta date,
    data_baixa date
);


##############################
## INSERCIÓ DE DADES A LA BD##
##############################

#Inserció Dades taula Socis
insert into socis values('60699248V','Albert','Vall','Home','1988-04-22','664298577','albertvr@gmail.com','albertvr',MD5('albert'),'no','0','ES7031591855891957489190');
insert into socis values('07986999L','Aina','Martí','Dona','2001-11-09','664399201','ainamc@gmail.com','ainamc',MD5('aina'),'no','0','ES7800813711884482814321');
insert into socis values('33200594W','Eric','Meseguer','Home','1992-07-01','664058004','enricml@gmail.com','enricml',MD5('enric'),'no','1','ES7714657949031311844354');
insert into socis values('59990219D','Edgar','Gomez','Home','2002-12-12','664995110','edgargp@gmail.com','edgargp',MD5('edgar'),'Lesió muscular al cuádriceps, dos setmanes de descans','0','ES6301289385192441434175');


#Inserció Dades taula Curses
insert into curses values('1','Natació');
insert into curses values(default,'Carrera');
insert into curses values(default,'Triatló');
insert into curses values(default,'Cycling');

#Inserció Dades taula Participar
#insert into participar values('DNI_soci', 'ID_curses','current_date()');

#Inserció Dades taula Web
insert into web values('1');

#Inserció Dades taula Registrat
#insert into registrat values('DNI_soci','ID_web','data_alta','data_baixa');

#Inserció Dades taula Sales
insert into sales values('1','Sala on hi ha una piscina gran per a poder fer natació que hi ha una capacitat per a 10 persona ja que hi han 10 carrils.','10');
insert into sales values(default,'Sala on estaràn les màquines per a poder fer exercici en solitari, com ara cintes per correr, bicics estàtiques, tot tipus de peses...','10');
insert into sales values(default,'Sala on hauran moltes bicicletes estàtiques per a poder fer bicileta amb una ruta que dissenya el tutor de la sala.','8');
insert into sales values(default,'Sala buida on hi haura materials com ara esterilles per a poder fer activitats de dança o yoga.','16');
insert into sales values(default,'Sala de boxeig amb un monitor que et fara una rutina depenen del teu nivell de aquest esport','5');
insert into sales values(default,'També hi haura una piscina una mica més petita per fer activitats col·lectives com el aquagym','6');
insert into sales values(default,'2 pistes de tennis a fora del gimnàsper poder fer 1 contra 1','4');


#Inserció Dades taula Activtats
INSERT INTO activitats VALUES(1,'Cinta de correr', ' A la sala 2 hi hauran 5 cintes de correr per a que cadascu es faci la seva rutina de correr','16:00:00', '2', 'IMG/1.jpg','1');
INSERT INTO activitats VALUES(default,'Boxeig', ' A la sala 5 hi hauran una sala amb un ring i uns quants sacs de boxeig que depen del teu nivvel seguiras una rutina o un altra per part del monitor','18:00:00', '5', 'IMG/2.jpg', '1');
INSERT INTO activitats VALUES(default,'Bici', ' A la sala 2 hi hauran 3 bicis estàtiques per a que cadascu vagi al seu ritme','16:00:00', '2', 'IMG/3.jpg', '2');
INSERT INTO activitats VALUES(default,'Cycling', ' Sala plena de bicis estàtiques que hi haura un monitor que creara una ruta llarga de alt rendiment (recomanem fer abans bici estàtica de manera solitaria si no tenim molt rendiment)', '18:00:00', '3', 'IMG/4.jpg', '2');
INSERT INTO activitats VALUES(default,'Piscina', ' Piscina gran amb 10 carrils per a poder fer piscines de manera lliure','16:00:00', '1', 'IMG/5.jpg', '3');
INSERT INTO activitats VALUES(default,'Fitness', ' Sala on hi hauran tot tipus de màquines per a poder entrenar el teu cos complet de forma lliure','16:00:00', '2', 'IMG/6.jpg','4');
INSERT INTO activitats VALUES(default,'Tennis', ' Activitat per fer tennis a una de les dos pistes de tennis que tenim, en el cas de ser imparell, el monitor jugaria amb la persona que estigues sola','18:00:00', '7', 'IMG/7.jpg', '4');
INSERT INTO activitats VALUES(default,'Aquagym', ' Es fara a la pisicina petita que la classe la fara el monitor de la piscina','16:00:00', '6', 'IMG/8.jpg', '5');
INSERT INTO activitats VALUES(default,'Zumba', ' Activitat de dança seguida per al monitor amb el ritme que vulguin els participants','18:00:00', '4', 'IMG/9.jpg', '5');
INSERT INTO activitats VALUES(default,'Yoga', ' Activitat també que es seguira les directrius que diu el monitor per a poder relaxar-se i millorar la flexibilitat','20:00:00', '4', 'IMG/10.jpg', '5');


#Inserció Dades taula Activitats Lliures
insert into activitat_lliure values('1');
insert into activitat_lliure values('2');
insert into activitat_lliure values('3');
insert into activitat_lliure values('5');
insert into activitat_lliure values('6');
insert into activitat_lliure values('7');

#Inserció Dades taula Activitats col·lectives
insert into activitat_colectiva values('4');
insert into activitat_colectiva values('8');
insert into activitat_colectiva values('9');
insert into activitat_colectiva values('10');

#Insercio Dades taula Reserva_lliure
# insert into reserva_lliure values('','','');
#insert into reserva_lliure values('1','07986999L','','');

#Insercio Dades taula Reserva_col·lectiva
#insert into reserva_col·lectiva values('4','07986999L','current_date()','current_time()');

#Inserció Dades taula Monitors
insert into monitors values('55599685Z','Joan','Angel Toribio','664539201','joanat@gmail.com','1500','1');
insert into monitors values('54845203R','Guillem','Portillo Diego','644220138','guillempd@gmail.com','1250','2');
insert into monitors values('41282712N','Silvia','Urbano Giner','644302989','silviaug@gmail.com','1800','3');
insert into monitors values('06438276R','Maximo','Villar Espino','644399201','maximove@gmail.com','1500','4');
insert into monitors values('61241545C','Josep','Llluis Valdes','644553980','josepllv@gmail.com','2300','5');
insert into monitors values('34536990Y','Xavier','Neus Rosa','664532978','xaviernr@gmail.com','1000','6');
insert into monitors values('37844924B','Maria','Alicia Sarmiento','644220894','aliciaas@gmail.com','1150','7');



############################
############################
## CONSULTES DE LES TAULES##
############################

# Consultar Dades taula socis:
select * from socis;
#update socis set mobil = '667882222' where DNI = '05735180S';

#Consultar Dades taula Web
#select * from web;

#Consultar Dades taula Sales
#select * from sales;

#Consultar Dades taula Activitats
# Consulta només activitats_lliures
SELECT A.imatge, A.nomActivitat, A.hora, S.aforament, M.nom, A.numero_sala
                from activitats A, sales S, monitors M where A.numero_sala = S.num_sala 
                and S.num_sala = M.sala_numero and A.ID = (select *
														from activitat_lliure S2
														where A.ID = S2.IDL)
														order by imatge;

# Consulta només activitats_col·lectives
SELECT A.imatge, A.nomActivitat, A.hora, S.aforament, M.nom, A.numero_sala
                from activitats A, sales S, monitors M where A.numero_sala = S.num_sala 
                and S.num_sala = M.sala_numero and A.ID = (select *
														from activitat_colectiva S2
														where A.ID = S2.IDC)
														order by imatge;
                                                        

#Consultar Dades taula Reserva_lliure

# into reserva_lliure values ('')
SELECT socis.*,count(reserva_colectiva.DNI) + (SELECT count(reserva_lliure.DNI) FROM reserva_lliure WHERE reserva_lliure.DNI = socis.DNI)as Reserves FROM reserva_colectiva, 
socis WHERE reserva_colectiva.DNI = socis.DNI GROUP BY reserva_colectiva.DNI  ORDER BY count(reserva_colectiva.DNI) + (SELECT count(reserva_lliure.DNI) FROM reserva_lliure WHERE reserva_lliure.DNI = socis.DNI) desc;

select * from reserva_lliure;

select * from reserva_colectiva;

select * from sales;

SELECT A.imatge, R.ID, R.DNI, R.data_act, R.hora 
                        from reserva_lliure R, activitat_lliure L, activitats A
                        where R.ID = L.IDL and R.DNI= "60699248V";
