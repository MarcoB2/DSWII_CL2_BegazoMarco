Create database Control2BD;
use  Control2BD;

create table producto (
idpro int primary key auto_increment,
nombrepro varchar(40),
descripcionpro varchar(40),
cantidadpro int,
fecvenpro date
);

insert into Producto values (1,'Coca-Cola','Bajo en azucar',156,12/04/2024);
insert into Producto values (2,'Inka-Cola','Bajo en azucar',3,02/02/2022);
insert into Producto values (3,'Fanta','Bajo en azucar',2,12/09/2016);
insert into Producto values (4,'Pepsi','Bajo en azucar',12,09/09/2009);