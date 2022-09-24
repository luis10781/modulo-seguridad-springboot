
create table candidatosalcaldia
(
	id int,
	name varchar(255),
	lastname varchar(255),
	username varchar(20),
	pass varchar(100),
	partido varchar(20),
	enable int
)

alter table candidatosalcaldia add primary key (id)
alter table candidatosalcaldia modify column id int auto_increment not null;

insert into candidatosalcaldia (name, lastname, username, pass, partido, enable)
values ('Elizabeth','León','DNI12345678','123456','Frente de la esperanza',1);

insert into candidatosalcaldia (name, lastname, username, pass, partido, enable)
values ('Gonzalo','Alegría','DNI72738909','123456','Juntos por el Perú',1);

insert into candidatosalcaldia (name, lastname, username, pass, partido, enable)
values ('Daniel','Urresti','DNI78392123','123456','Podemos Perú',1);

insert into candidatosalcaldia (name, lastname, username, pass, partido, enable)
values ('Rafael','López Aliaga','DNI45183212','123456','Renovación Popular',1);

insert into candidatosalcaldia (name, lastname, username, pass, partido, enable)
values ('Omar','Chehade','DNI45183212','123456','Alianza Para el Progreso',1);

insert into candidatosalcaldia (name, lastname, username, pass, partido, enable)
values ('George','Forsyth','DNI93728324','123456','Somos Perú',1);


/*CREATE*/
create procedure insertCandidatos(
	p_name varchar(255),
	P_lastname varchar(255),
	p_username varchar(20),
	p_pass varchar(100),
	p_partido varchar(20),
	p_enable int)
begin
	declare countUsers INT default 0;
	select count(*) into countUsers from candidatosalcaldia where username=p_username;
	if	(countUsers>0)
	then
		select 'Usuario exisente';
	else
		insert into candidatosalcaldia (name, lastname, username, pass, partido, enable)
		values (p_name,p_lastname,p_username,p_pass,p_partido,p_enable);
	end if;
end

call insertCandidatos('Alan','Garcia','DNI6323678','123456','APRA',1)

/*DELETE*/
create procedure deleteCandidateByName(in p_name varchar(255),
										in P_lastname varchar(255))
begin declare countUsers int default 0;
select count(*) into countUsers from candidatosalcaldia where name = p_name and lastname = P_lastname;

	if(countUsers<0)then
		select 'Usuario no existe';
	else
		delete from candidatosalcaldia where name = p_name and lastname = P_lastname;
		end if;
end

/*LEER*/
create procedure selectAllCandidates()
begin
	select * from candidatosalcaldia ;
end

call selectAllCandidates();

