create database consultorio;

use consultorio;

create table Dentista(
	idDentista integer not null auto_increment,
    nomeDentista varchar(30),
    especialidade varchar(30),
    primary key(idDentista)
);

select * from Dentista;

create table Paciente(
	idPaciente integer not null auto_increment,
    nomePaciente varchar(30),
    dataNascimento varchar(10),
    primary key(idPaciente)
);

select * from Paciente;

create table Consulta(
	idConsulta integer not null auto_increment,
    dataConsulta varchar(10),
    tipo varchar(30),
	preco double,
    dentistaAssociado varchar(30),
	pacienteAssociado varchar(30),
    primary key(idConsulta)
);

select * from Consulta;

