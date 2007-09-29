SELECT libreria.save_Categoria(null,'nuevacat1');
SELECT libreria.save_Categoria(null,'nuevacat2');
SELECT libreria.save_Categoria(null,'nuevacat3');

SELECT libreria.save_producto(null,'Vampiro','La mascarada',15.5,1,'11/11/2000') into @idCD1;
SELECT libreria.save_cd(@idCD1,'80m','magoya','sellito');
SELECT libreria.save_tema(@idCD1,null,'basta1','inf.','nosotros');
SELECT libreria.save_tema(@idCD1,null,'basta2','inf.','losgrosos');

SELECT libreria.save_producto(null,'TIJ','3.0',25.5,2,'11/11/2001') into @idLibro1;
SELECT libreria.save_libro(@idLibro1,300,'isbn','editor','yo','english');

SELECT libreria.save_producto(null,'La gran Pregunta','3.0',25.5,2,'11/11/2001') into @idPelicula1;
SELECT libreria.save_pelicula(@idPelicula1,'300m','DVD');

SELECT libreria.save_persona(null,'DNI','1294812','Apey','Nom','M','11/11/2001','Observa') into @idPersona;

select libreria.save_personadireccion(@idPersona,null,'P','9944A','Aca','Aqui','Sin Nro','Puerta?','Observado');
select libreria.save_personadireccion(@idPersona,null,'P','9944A','Aca2','Aqui2','Sin Nro2','Puerta2?','Observado2');

select libreria.save_personaemail(@idPersona,null,'P','alguna@direccion.blar','obs');
select libreria.save_personaemail(@idPersona,null,'P','alguna2@direccion.blar','obs');

select libreria.save_personatelefono(@idPersona,null,'C','telefono2','obs');
select libreria.save_personatelefono(@idPersona,null,'C','telefono2','obs');

select libreria.save_privilegio(null,'algoacuidar','describo','obsai') into @idPrivilegio1;
select libreria.save_privilegio(null,'algoacuidar2','describo2','obsai2') into @idPrivilegio2;

select libreria.save_rolsistema(null,'unrol','descri','obbb') into @idRol1;
select libreria.save_rolsistema(null,'otrorol','descri','obbb') into @idRol2;

select libreria.save_rolsistemaprivilegio(@idPrivilegio1,@idRol2,'1','obbb12132');
select libreria.save_rolsistemaprivilegio(@idPrivilegio2,@idRol1,'0','obbbbasta!!!!!estoycansado!');

SELECT libreria.save_usuario(null,'logos','pastie',@idPersona,'observa');

select libreria.save_transaccion(null,1,20.0,'20/02/1999') into @idCompra;

select libreria.save_detalletransaccion(@idCompra,@idLibro1,10.0,1);
select libreria.save_detalletransaccion(@idCompra,@idPelicula1,5.0,2);