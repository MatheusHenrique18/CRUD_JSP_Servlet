create database crud_jsp;

create table usuario (
id serial not null,
nome varchar(150) not null,
email varchar (100) not null,
nacionalidade varchar (100),
primary key (id)
);