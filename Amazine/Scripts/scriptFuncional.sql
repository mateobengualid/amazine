DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_Categoria`$$
CREATE PROCEDURE  `libreria`.`delete_Categoria`(pin_idcategoria INTEGER)
BEGIN
    DELETE FROM Categoria WHERE idcategoria = pin_idcategoria;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_CD`$$
CREATE PROCEDURE  `libreria`.`delete_CD`(pin_idproducto INTEGER)
BEGIN
    DELETE FROM CD WHERE idproducto = pin_idproducto;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_detalletransaccion`$$
CREATE PROCEDURE  `libreria`.`delete_detalletransaccion`(
       pin_idtransaccion INTEGER,
       pin_idproducto INTEGER)
BEGIN
    DELETE FROM DetalleTransaccion WHERE idproducto = pin_idproducto AND idtransaccion = pin_idtransaccion;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_libro`$$
CREATE PROCEDURE  `libreria`.`delete_libro`(pin_idproducto INTEGER)
BEGIN
    DELETE FROM Libro WHERE idproducto = pin_idproducto;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_pelicula`$$
CREATE PROCEDURE  `libreria`.`delete_pelicula`(pin_idproducto INTEGER)
BEGIN
    DELETE FROM Pelicula WHERE idproducto = pin_idproducto;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_persona`$$
CREATE PROCEDURE  `libreria`.`delete_persona`(pin_idpersona INTEGER)
BEGIN
    DELETE FROM Persona WHERE idpersona = pin_idpersona;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_personadireccion`$$
CREATE PROCEDURE  `libreria`.`delete_personadireccion`(pin_idpersona INTEGER, pin_orden INTEGER)
BEGIN
    DELETE FROM PersonaDireccion WHERE idpersona = pin_idpersona AND orden = pin_orden;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_personaemail`$$
CREATE PROCEDURE  `libreria`.`delete_personaemail`(pin_idpersona INTEGER, pin_orden INTEGER)
BEGIN
    DELETE FROM PersonaEmail WHERE idpersona = pin_idpersona AND orden = pin_orden;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_personaprivilegio`$$
CREATE PROCEDURE  `libreria`.`delete_personaprivilegio`(pin_idpersona INTEGER, pin_idprivilegio INTEGER)
BEGIN
    DELETE FROM PersonaPrivilegio WHERE idpersona = pin_idpersona AND idprivilegio = pin_idprivilegio;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_personatelefono`$$
CREATE PROCEDURE  `libreria`.`delete_personatelefono`(pin_idpersona INTEGER, pin_orden INTEGER)
BEGIN
    DELETE FROM PersonaTelefono WHERE idpersona = pin_idpersona AND orden = pin_orden;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_privilegio`$$
CREATE PROCEDURE  `libreria`.`delete_privilegio`(pin_idprivilegio INTEGER)
BEGIN
    DELETE FROM Privilegio WHERE idprivilegio = pin_idprivilegio;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_producto`$$
CREATE PROCEDURE  `libreria`.`delete_producto`(pin_idproducto INTEGER)
BEGIN
    DELETE FROM Producto WHERE idproducto = pin_idproducto;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_rolsistema`$$
CREATE PROCEDURE  `libreria`.`delete_rolsistema`(pin_idrolsistema INTEGER)
BEGIN
    DELETE FROM RolSistema WHERE idrolsistema = pin_idrolsistema;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_rolsistemaprivilegio`$$
CREATE PROCEDURE  `libreria`.`delete_rolsistemaprivilegio`(pin_idrolsistema INTEGER, pin_idprivilegio INTEGER)
BEGIN
    DELETE FROM RolSistemaPrivilegio WHERE idrolsistema = pin_idrolsistema AND idprivilegio = pin_idprivilegio;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_tema`$$
CREATE PROCEDURE  `libreria`.`delete_tema`(
       pin_idproducto INTEGER,
       pin_idtema INTEGER)
BEGIN
    DELETE FROM Tema WHERE idproducto = pin_idproducto AND idtema = pin_idtema;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_transaccion`$$
CREATE PROCEDURE  `libreria`.`delete_transaccion`(pin_idtransaccion INTEGER)
BEGIN
    DELETE FROM Transaccion WHERE idtransaccion = pin_idtransaccion;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`delete_usuario`$$
CREATE PROCEDURE  `libreria`.`delete_usuario`(pin_idusuario INTEGER)
BEGIN
    DELETE FROM Usuario WHERE idusuario = pin_idusuario;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `libreria`.`get_usuario`$$
CREATE PROCEDURE  `libreria`.`get_usuario`(
   IN  pinout_idusuario INTEGER,
   OUT pout_login VARCHAR(32),
   OUT pout_password VARCHAR(32),
   OUT pout_idpersona INTEGER,
   OUT pout_observaciones VARCHAR(255))
BEGIN
    DECLARE registro CURSOR FOR
    SELECT U.idusuario, U.login, U.password, U.idpersona, U.observaciones
    FROM Usuario U
    WHERE idusuario = pinout_idusuario;

    OPEN registro;
    FETCH registro INTO pinout_idusuario, pout_login, pout_password, pout_idpersona, pout_observaciones;
    CLOSE registro;
END $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoCategoria`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoCategoria`() RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(C.idcategoria) FROM Categoria C);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoPersona`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoPersona`() RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(P.idpersona) FROM Persona P);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoPrivilegio`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoPrivilegio`() RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(P.idprivilegio) FROM Privilegio P);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoProducto`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoProducto`() RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(P.idproducto) FROM Producto P);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoRolSistema`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoRolSistema`() RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(RS.idrolsistema) FROM RolSistema RS);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoTema`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoTema`(pin_idProducto INTEGER) RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(T.idtema)
                                     FROM Tema T
                                     WHERE T.idproducto = pin_idProducto);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoTransaccion`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoTransaccion`() RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(T.idtransaccion) FROM Transaccion T);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxCodigoUsuario`$$
CREATE FUNCTION  `libreria`.`getMaxCodigoUsuario`() RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(U.idusuario) FROM usuario U);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxOrdenDireccion`$$
CREATE FUNCTION  `libreria`.`getMaxOrdenDireccion`(inp_idPersona INTEGER) RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(PD.orden)
                                     FROM PersonaDireccion PD
                                     WHERE PD.idpersona = inp_idPersona);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxOrdenEmail`$$
CREATE FUNCTION  `libreria`.`getMaxOrdenEmail`(inp_idPersona INTEGER) RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(PE.orden)
                                     FROM PersonaEmail PE
                                     WHERE PE.idpersona = inp_idPersona);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`getMaxOrdenTelefono`$$
CREATE FUNCTION  `libreria`.`getMaxOrdenTelefono`(inp_idPersona INTEGER) RETURNS int(11)
BEGIN
    DECLARE var_max INTEGER DEFAULT (SELECT MAX(PT.orden)
                                     FROM PersonaTelefono PT
                                     WHERE PT.idpersona = inp_idPersona);
    IF (var_max IS NULL) THEN
       SET var_max = 0;
    END IF;
    RETURN(var_max);
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Categoria`$$
CREATE FUNCTION  `libreria`.`save_Categoria`(
  pin_idcategoria                  SMALLINT(6),
  pin_descripcion                  VARCHAR(40)
) RETURNS int(11)
BEGIN
  DECLARE      var_idcategoria                  SMALLINT(6) DEFAULT pin_idcategoria;
  DECLARE      var_descripcion                  VARCHAR(40) DEFAULT pin_descripcion;

  DECLARE      var_count                        INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM categoria c
        WHERE c.idcategoria = var_idcategoria;

      IF (var_count > 0) THEN
        UPDATE categoria c SET
          c.descripcion = var_descripcion
        WHERE c.idcategoria = var_idcategoria;
      ELSE
        IF (var_idcategoria IS NULL) THEN
          SET var_idcategoria = getMaxCodigoCategoria()+1;
        END IF;
        INSERT INTO categoria(idcategoria, descripcion)
          VALUES (var_idcategoria, var_descripcion);
      END IF;

      RETURN var_idcategoria;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_CD`$$
CREATE FUNCTION  `libreria`.`save_CD`(
  pin_idproducto                   INTEGER,
  pin_duracion                     VARCHAR(10),
  pin_autor                        VARCHAR(25),
  pin_sellodiscografico            VARCHAR(15)
) RETURNS int(11)
BEGIN
  DECLARE      var_idproducto                   INTEGER  DEFAULT pin_idproducto;                   
  DECLARE      var_duracion                     VARCHAR(10) DEFAULT pin_duracion;
  DECLARE      var_autor                        VARCHAR(25) DEFAULT pin_autor;
  DECLARE      var_sellodiscografico            VARCHAR(15) DEFAULT pin_sellodiscografico;

  DECLARE      var_count                    INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM cd c
        WHERE c.idproducto = var_idproducto;

      IF (var_count > 0) THEN
        UPDATE cd SET
          duracion = var_duracion,
          autor = var_autor,
          sellodiscografico = var_sellodiscografico
        WHERE idproducto = var_idproducto;
      ELSE
        IF (var_idproducto IS NULL) THEN
          SET var_idproducto = getMaxCodigoProducto()+1;
        END IF;
        INSERT INTO cd(idproducto, duracion, autor, sellodiscografico)
          VALUES (var_idproducto, var_duracion, var_autor, var_sellodiscografico);
      END IF;

      RETURN var_idproducto;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_DetalleTransaccion`$$
CREATE FUNCTION  `libreria`.`save_DetalleTransaccion`(
  pin_idtransaccion                INTEGER,
  pin_idproducto                   INTEGER,
  pin_importe                      DOUBLE,
  pin_cantidad                     SMALLINT(6)
) RETURNS int(11)
BEGIN
  DECLARE      var_idtransaccion                INTEGER DEFAULT pin_idtransaccion;
  DECLARE      var_idproducto                   INTEGER DEFAULT pin_idproducto;
  DECLARE      var_importe                      DOUBLE DEFAULT pin_importe;
  DECLARE      var_cantidad                     SMALLINT(6) DEFAULT pin_cantidad;

  DECLARE      var_count                        INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
      FROM detalletransaccion dt
      WHERE dt.idtransaccion = pin_idtransaccion
        AND dt.idproducto = pin_idproducto;

      IF (var_count > 0) THEN
        UPDATE detalletransaccion dt SET
          dt.importe = var_importe,
          dt.cantidad = var_cantidad
        WHERE dt.idtransaccion = var_idtransaccion
          AND dt.idproducto = var_idproducto;
      ELSE
        INSERT INTO detalletransaccion(idtransaccion, idproducto, importe, cantidad)
          VALUES (var_idtransaccion, var_idproducto, var_importe, var_cantidad);
      END IF;

      RETURN var_idproducto;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Libro`$$
CREATE FUNCTION  `libreria`.`save_Libro`(
  pin_idproducto                   INTEGER,
  pin_duracion                     SMALLINT(6),
  pin_isbn                         VARCHAR(15),
  pin_editorial                    VARCHAR(25),
  pin_autor                        VARCHAR(40),
  pin_idioma                       VARCHAR(15)
) RETURNS int(11)
BEGIN
  DECLARE      var_idproducto                   INTEGER  DEFAULT pin_idproducto;
  DECLARE      var_duracion                     SMALLINT(6) DEFAULT pin_duracion;
  DECLARE      var_isbn                         VARCHAR(15) DEFAULT pin_isbn;
  DECLARE      var_editorial                    VARCHAR(25) DEFAULT pin_editorial;
  DECLARE      var_autor                        VARCHAR(40) DEFAULT pin_autor;
  DECLARE      var_idioma                       VARCHAR(15) DEFAULT pin_idioma;

  DECLARE      var_count                    INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM libro l
        WHERE l.idproducto = var_idproducto;

      IF (var_count > 0) THEN
        UPDATE libro l SET
          l.duracion = var_duracion,
          l.isbn = var_isbn,
          l.editorial = var_editorial,
          l.autor = var_autor,
          l.idioma = var_idioma
        WHERE l.idproducto = var_idproducto;
      ELSE
        IF (var_idproducto IS NULL) THEN
          SET var_idproducto = getMaxCodigoProducto()+1;
        END IF;
        INSERT INTO libro(idproducto, duracion, isbn, editorial, autor, idioma)
          VALUES (var_idproducto, var_duracion, var_isbn, var_editorial, var_autor, var_idioma);
      END IF;

      RETURN var_idproducto;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Pelicula`$$
CREATE FUNCTION  `libreria`.`save_Pelicula`(
  pin_idproducto                   INTEGER,
  pin_duracion                     VARCHAR(10),
  pin_formato                      VARCHAR(10)
) RETURNS int(11)
BEGIN
  DECLARE      var_idproducto                   INTEGER  DEFAULT pin_idproducto;
  DECLARE      var_duracion                     VARCHAR(10) DEFAULT pin_duracion;
  DECLARE      var_formato                      VARCHAR(10) DEFAULT pin_formato;

  DECLARE      var_count                    INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM pelicula p
        WHERE p.idproducto = var_idproducto;

      IF (var_count > 0) THEN
        UPDATE pelicula p SET
          p.duracion = var_duracion,
          p.formato = var_formato
        WHERE p.idproducto = var_idproducto;
      ELSE
        IF (var_idproducto IS NULL) THEN
          SET var_idproducto = getMaxCodigoProducto()+1;
        END IF;
        INSERT INTO pelicula(idproducto, duracion, formato)
          VALUES (var_idproducto, var_duracion, var_formato);
      END IF;

      RETURN var_idproducto;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Persona`$$
CREATE FUNCTION  `libreria`.`save_Persona`(
  pin_idpersona                    INT,
  pin_tipodoc                      VARCHAR(8),
  pin_nrodoc                       VARCHAR(16),
  pin_apellido                     VARCHAR(32),
  pin_nombre                       VARCHAR(32),
  pin_sexo                         CHAR,
  pin_fecnac                       VARCHAR(10),
  pin_observaciones                VARCHAR(255)
) RETURNS int(11)
BEGIN
  DECLARE      var_idpersona                INTEGER  DEFAULT pin_idpersona;
  DECLARE      var_tipodoc                  VARCHAR(8) DEFAULT pin_tipodoc;
  DECLARE      var_nrodoc                   VARCHAR(16) DEFAULT pin_nrodoc;
  DECLARE      var_apellido                 VARCHAR(25) DEFAULT pin_apellido;
  DECLARE      var_nombre                   VARCHAR(25) DEFAULT pin_nombre;
  DECLARE      var_fecnac                   DATE;
  DECLARE      var_observaciones            VARCHAR(255) DEFAULT pin_observaciones;

  DECLARE      var_count                    INTEGER         DEFAULT 0;

     SET var_fecnac  = str_to_date(pin_fecnac, '%d/%m/%Y');

      SELECT COUNT(*)
        INTO var_count
        FROM persona p
        WHERE p.idpersona = var_idpersona;

      IF (var_count > 0) THEN
        UPDATE persona p SET
          tipodoc = var_tipodoc,
          nrodoc = var_nrodoc,
          apellido = var_apellido,
          nombre = var_nombre,
          sexo = pin_sexo,
          fecnac = var_fecnac,
          observaciones = var_observaciones
        WHERE p.idpersona = var_idpersona;
      ELSE
        IF (var_idpersona IS NULL) THEN
          SET var_idpersona = getMaxCodigoPersona()+1;
        END IF;
        INSERT INTO persona(idpersona, tipodoc, nrodoc, apellido, nombre, sexo, fecnac, observaciones)
          VALUES (var_idpersona, var_tipodoc, var_nrodoc, var_apellido, var_nombre, pin_sexo, var_fecnac, var_observaciones);
      END IF;

      RETURN var_idpersona;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_PersonaDireccion`$$
CREATE FUNCTION  `libreria`.`save_PersonaDireccion`(
  pin_idpersona                INTEGER,
  pin_orden                    INTEGER,
  pin_tipo                     CHAR(1),
  pin_direccioncp              VARCHAR(12),
  pin_direccionlocalidad       VARCHAR(40),
  pin_direccioncalle           VARCHAR(40),
  pin_direccionnro             VARCHAR(8),
  pin_direccionpuerta          VARCHAR(8),
  pin_observaciones            VARCHAR(64)
) RETURNS int(11)
BEGIN
  DECLARE      var_idpersona                INTEGER DEFAULT pin_idpersona;
  DECLARE      var_orden                    INTEGER DEFAULT pin_orden;
  DECLARE      var_tipo                     CHAR(1) DEFAULT pin_tipo;
  DECLARE      var_direccioncp              VARCHAR(12) DEFAULT pin_direccioncp;
  DECLARE      var_direccionlocalidad       VARCHAR(40) DEFAULT pin_direccionlocalidad;
  DECLARE      var_direccioncalle           VARCHAR(40) DEFAULT pin_direccioncalle;
  DECLARE      var_direccionnro             VARCHAR(8) DEFAULT pin_direccionnro;
  DECLARE      var_direccionpuerta          VARCHAR(8) DEFAULT pin_direccionpuerta;
  DECLARE      var_observaciones            VARCHAR(64) DEFAULT pin_observaciones;

  DECLARE      var_count                        INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
      FROM personadireccion pd
      WHERE pd.idpersona = pin_idpersona
        AND pd.orden = pin_orden;

      IF (var_count > 0) THEN
        UPDATE personadireccion pd SET
          pd.tipo = var_tipo,
          pd.direccioncp = var_direccioncp,
          pd.direccionlocalidad = var_direccionlocalidad,
          pd.direccioncalle = var_direccioncalle,
          pd.direccionnro = var_direccionnro,
          pd.direccionpuerta = var_direccionpuerta,
          pd.observaciones = var_observaciones
        WHERE pd.idpersona = var_idpersona
          AND pd.orden = var_orden;
      ELSE
        IF (var_orden IS NULL) THEN
          SET var_orden = getMaxOrdenDireccion(var_idpersona)+1;
        END IF;
        INSERT INTO personadireccion(idpersona, orden, tipo, direccioncp, direccionlocalidad, direccioncalle, direccionnro, direccionpuerta, observaciones)
          VALUES (var_idpersona, var_orden, var_tipo, var_direccioncp, var_direccionlocalidad, var_direccioncalle, var_direccionnro, var_direccionpuerta, var_observaciones);
      END IF;

      RETURN var_orden;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_PersonaEmail`$$
CREATE FUNCTION  `libreria`.`save_PersonaEmail`(
  pin_idpersona                INTEGER,
  pin_orden                    INTEGER,
  pin_tipo                     CHAR(1),
  pin_email                    VARCHAR(40),
  pin_observaciones            VARCHAR(64)
) RETURNS int(11)
BEGIN
  DECLARE      var_idpersona                INTEGER DEFAULT pin_idpersona;
  DECLARE      var_orden                    INTEGER DEFAULT pin_orden;
  DECLARE      var_tipo                     CHAR(1) DEFAULT pin_tipo;
  DECLARE      var_email                    VARCHAR(40) DEFAULT pin_email;
  DECLARE      var_observaciones            VARCHAR(64) DEFAULT pin_observaciones;

  DECLARE      var_count                        INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
      FROM personaemail pe
      WHERE pe.idpersona = pin_idpersona
        AND pe.orden = pin_orden;

      IF (var_count > 0) THEN
        UPDATE personaemail pe SET
          pe.tipo = var_tipo,
          pe.email = var_email,
          pe.observaciones = var_observaciones
        WHERE pe.idpersona = var_idpersona
          AND pe.orden = var_orden;
      ELSE
        IF (var_orden IS NULL) THEN
          SET var_orden = getMaxOrdenEmail(var_idpersona)+1;
        END IF;
        INSERT INTO personaemail(idpersona, orden, tipo, email, observaciones)
          VALUES (var_idpersona, var_orden, var_tipo, var_email, var_observaciones);
      END IF;

      RETURN var_orden;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_PersonaPrivilegio`$$
CREATE FUNCTION  `libreria`.`save_PersonaPrivilegio`(
  pin_idpersona                    INTEGER,
  pin_idprivilegio                 INTEGER,
  pin_permit                       CHAR(1),
  pin_observaciones                VARCHAR(255)
) RETURNS int(11)
BEGIN
  DECLARE      var_idpersona                   INTEGER DEFAULT pin_idpersona;
  DECLARE      var_idprivilegio                INTEGER DEFAULT pin_idprivilegio;
  DECLARE      var_permit                      CHAR(1) DEFAULT pin_permit;
  DECLARE      var_observaciones               VARCHAR(255) DEFAULT pin_observaciones;

  DECLARE      var_count                       INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
      FROM personaprivilegio pp
      WHERE pp.idpersona = pin_idtransaccion
        AND pp.idprivilegio = pin_idproducto;

      IF (var_count > 0) THEN
        UPDATE personaprivilegio pp SET
          pp.permit = var_permit,
          pp.observaciones = var_observaciones
        WHERE pp.idpersona = var_idpersona
          AND pp.idprivilegio = var_idprivilegio;
      ELSE
        INSERT INTO personaprivilegio(idpersona, idproducto, permit, observaciones)
          VALUES (var_idpersona, var_idprivilegio, var_permit, var_observaciones);
      END IF;

      RETURN var_idprivilegio;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_PersonaTelefono`$$
CREATE FUNCTION  `libreria`.`save_PersonaTelefono`(
  pin_idpersona                INTEGER,
  pin_orden                    INTEGER,
  pin_tipo                     CHAR(1),
  pin_telefono                 VARCHAR(24),
  pin_observaciones            VARCHAR(64)
) RETURNS int(11)
BEGIN
  DECLARE      var_idpersona                INTEGER DEFAULT pin_idpersona;
  DECLARE      var_orden                    INTEGER DEFAULT pin_orden;
  DECLARE      var_tipo                     CHAR(1) DEFAULT pin_tipo;
  DECLARE      var_telefono                 VARCHAR(24) DEFAULT pin_telefono;
  DECLARE      var_observaciones            VARCHAR(64) DEFAULT pin_observaciones;

  DECLARE      var_count                        INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
      FROM personatelefono pt
      WHERE pt.idpersona = pin_idpersona
        AND pt.orden = pin_orden;

      IF (var_count > 0) THEN
        UPDATE personatelefono pt SET
          pt.tipo = var_tipo,
          pt.telefono = var_telefono,
          pt.observaciones = var_observaciones
        WHERE pt.idpersona = var_idpersona
          AND pt.orden = var_orden;
      ELSE
        IF (var_orden IS NULL) THEN
          SET var_orden = getMaxOrdenTelefono(var_idpersona)+1;
        END IF;
        INSERT INTO personatelefono(idpersona, orden, tipo, telefono, observaciones)
          VALUES (var_idpersona, var_orden, var_tipo, var_telefono, var_observaciones);
      END IF;

      RETURN var_orden;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Privilegio`$$
CREATE FUNCTION  `libreria`.`save_Privilegio`(
  pin_idprivilegio                 INTEGER,
  pin_cgoprivilegio                VARCHAR(32),
  pin_descripcion                  VARCHAR(64),
  pin_observaciones                VARCHAR(255)
) RETURNS int(11)
BEGIN
  DECLARE      var_idprivilegio                 INTEGER  DEFAULT pin_idprivilegio;
  DECLARE      var_cgoprivilegio                VARCHAR(32) DEFAULT pin_cgoprivilegio;
  DECLARE      var_descripcion                  VARCHAR(64) DEFAULT pin_descripcion;
  DECLARE      var_observaciones                VARCHAR(255) DEFAULT pin_observaciones;

  DECLARE      var_count                    INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM privilegio p
        WHERE p.idprivilegio = var_idprivilegio;

      IF (var_count > 0) THEN
        UPDATE privilegio p SET
          p.cgoprivilegio = var_cgoprivilegio,
          p.descripcion = var_descripcion,
          p.observaciones = var_observaciones
        WHERE p.idprivilegio = var_idprivilegio;
      ELSE
        IF (var_idprivilegio IS NULL) THEN
          SET var_idprivilegio = getMaxCodigoPrivilegio()+1;
        END IF;
        INSERT INTO privilegio(idprivilegio, cgoprivilegio, descripcion, observaciones)
          VALUES (var_idprivilegio, var_cgoprivilegio, var_descripcion, var_observaciones);
      END IF;

      RETURN var_idprivilegio;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Producto`$$
CREATE FUNCTION  `libreria`.`save_Producto`(
  pin_idproducto                   INTEGER,
  pin_nombre                       VARCHAR(40),
  pin_descripcion                  VARCHAR(300),
  pin_precio                       DOUBLE,
  pin_idcategoria                  SMALLINT(6),
  pin_fechasalida                  VARCHAR(10)
) RETURNS int(11)
BEGIN
  DECLARE      var_idproducto                   INTEGER  DEFAULT pin_idproducto;
  DECLARE      var_nombre                       VARCHAR(40) DEFAULT pin_nombre;
  DECLARE      var_descripcion                  VARCHAR(300) DEFAULT pin_descripcion;
  DECLARE      var_precio                       DOUBLE DEFAULT pin_precio;
  DECLARE      var_idcategoria                  SMALLINT(6) DEFAULT pin_idcategoria;
  DECLARE      var_fechasalida                  DATE DEFAULT STR_TO_DATE(pin_fechasalida, '%d/%m/%Y');

  DECLARE      var_count                        INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM producto p
        WHERE p.idproducto = var_idproducto;

      IF (var_count > 0) THEN
        UPDATE producto SET
          nombre = var_nombre,
          descripcion = var_descripcion,
          precio = var_precio,
          idcategoria = var_idcategoria,
          fechasalida = var_fechasalida
        WHERE idproducto = var_idproducto;
      ELSE
        IF (var_idproducto IS NULL) THEN
          SET var_idproducto = getMaxCodigoProducto()+1;
        END IF;
        INSERT INTO producto(idproducto, nombre, descripcion, precio, idcategoria, fechasalida)
          VALUES (var_idproducto, var_nombre, var_descripcion, var_precio, var_idcategoria, var_fechasalida);
      END IF;

      RETURN var_idproducto;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_RolSistema`$$
CREATE FUNCTION  `libreria`.`save_RolSistema`(
  pin_idrolsistema                 INTEGER,
  pin_cgorolsistema                VARCHAR(16),
  pin_descripcion                  VARCHAR(64),
  pin_observaciones                VARCHAR(255)
) RETURNS int(11)
BEGIN
  DECLARE      var_idrolsistema                 INTEGER  DEFAULT pin_idrolsistema;
  DECLARE      var_cgorolsistema                VARCHAR(16) DEFAULT pin_cgorolsistema;
  DECLARE      var_descripcion                  VARCHAR(64) DEFAULT pin_descripcion;
  DECLARE      var_observaciones                VARCHAR(255) DEFAULT pin_observaciones;

  DECLARE      var_count                    INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM rolsistema rs
        WHERE rs.idrolsistema = var_idrolsistema;

      IF (var_count > 0) THEN
        UPDATE rolsistema rs SET
          rs.cgorolsistema = var_cgorolsistema,
          rs.descripcion = var_descripcion,
          rs.observaciones = var_observaciones
        WHERE rs.idrolsistema = var_idrolsistema;
      ELSE
        IF (var_idrolsistema IS NULL) THEN
          SET var_idrolsistema = getMaxCodigoRolSistema()+1;
        END IF;
        INSERT INTO rolsistema(idrolsistema, cgorolsistema, descripcion, observaciones)
          VALUES (var_idrolsistema, var_cgorolsistema, var_descripcion, var_observaciones);
      END IF;

      RETURN var_idrolsistema;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_RolSistemaPrivilegio`$$
CREATE FUNCTION  `libreria`.`save_RolSistemaPrivilegio`(
  pin_idrolsistema                 INTEGER,
  pin_idprivilegio                 INTEGER,
  pin_permit                       CHAR(1),
  pin_observaciones                VARCHAR(255)
) RETURNS int(11)
BEGIN
  DECLARE      var_idrolsistema                INTEGER DEFAULT pin_idrolsistema;
  DECLARE      var_idprivilegio                INTEGER DEFAULT pin_idprivilegio;
  DECLARE      var_permit                      CHAR(1) DEFAULT pin_permit;
  DECLARE      var_observaciones               VARCHAR(255) DEFAULT pin_observaciones;

  DECLARE      var_count                       INTEGER DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
      FROM rolsistemaprivilegio rsp
      WHERE rsp.idrolsistema = var_idrolsistema
        AND rsp.idprivilegio = var_idprivilegio;

      IF (var_count > 0) THEN
        UPDATE rolsistemaprivilegio rsp SET
          rsp.permit = var_permit,
          rsp.observaciones = var_observaciones
        WHERE rsp.idrolsistema = var_idrolsistema
          AND rsp.idprivilegio = var_idprivilegio;
      ELSE
        INSERT INTO rolsistemaprivilegio(idrolsistema, idprivilegio, permit, observaciones)
          VALUES (var_idrolsistema, var_idprivilegio, var_permit, var_observaciones);
      END IF;

      RETURN var_idprivilegio;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Tema`$$
CREATE FUNCTION  `libreria`.`save_Tema`(
  pin_idproducto                INTEGER,
  pin_idtema                    SMALLINT(6),
  pin_nombretema                VARCHAR(30),
  pin_duracion                  VARCHAR(6),
  pin_autortema                 VARCHAR(30)
) RETURNS int(11)
BEGIN
  DECLARE      var_idproducto                INTEGER     DEFAULT pin_idproducto;
  DECLARE      var_idtema                    SMALLINT(6) DEFAULT pin_idtema;
  DECLARE      var_nombretema                VARCHAR(30) DEFAULT pin_nombretema;
  DECLARE      var_duracion                  VARCHAR(6)  DEFAULT pin_duracion;
  DECLARE      var_autortema                 VARCHAR(30) DEFAULT pin_autortema;

  DECLARE      var_count                     INTEGER     DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
      FROM tema t
      WHERE t.idproducto = var_idproducto
        AND t.idtema = var_idtema;

      IF (var_count > 0) THEN
        UPDATE tema t SET
          t.nombretema = var_nombretema,
          t.duracion = var_duracion,
          t.autortema = var_autortema
        WHERE t.idproducto = var_idproducto
          AND t.idtema = var_idtema;
      ELSE
        IF (var_idtema IS NULL) THEN
          SET var_idtema = getMaxCodigoTema(var_idproducto)+1;
        END IF;
        INSERT INTO tema(idproducto, idtema, nombretema, duracion, autortema)
          VALUES (var_idproducto, var_idtema, var_nombretema, var_duracion, var_autortema);
      END IF;

      RETURN var_idtema;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Transaccion`$$
CREATE FUNCTION  `libreria`.`save_Transaccion`(
  pin_idtransaccion                INTEGER,
  pin_idusuario                    INTEGER,
  pin_importe                      DOUBLE,
  pin_fecha                        VARCHAR(10)
) RETURNS int(11)
BEGIN
  DECLARE      var_idtransaccion                INTEGER DEFAULT pin_idtransaccion;
  DECLARE      var_idusuario                    INTEGER DEFAULT pin_idusuario;
  DECLARE      var_importe                      DOUBLE DEFAULT pin_importe;
  DECLARE      var_fecha                        DATE DEFAULT STR_TO_DATE(pin_fecha, '%d/%m/%Y');

  DECLARE      var_count                        INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM transaccion t
        WHERE t.idtransaccion = var_idtransaccion;

      IF (var_count > 0) THEN
        UPDATE transaccion t SET
          t.idusuario = var_idusuario,
          t.importe = var_importe,
          t.fecha = var_fecha
        WHERE t.idtransaccion = var_idtransaccion;
      ELSE
        IF (var_idtransaccion IS NULL) THEN
          SET var_idtransaccion = getMaxCodigoTransaccion()+1;
        END IF;
        INSERT INTO transaccion(idtransaccion, idusuario, importe, fecha)
          VALUES (var_idtransaccion, var_idusuario, var_importe, var_fecha);
      END IF;

      RETURN var_idtransaccion;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `libreria`.`save_Usuario`$$
CREATE FUNCTION  `libreria`.`save_Usuario`(
  pin_idusuario                    INTEGER,
  pin_login                        VARCHAR(32),
  pin_password                     VARCHAR(32),
  pin_idpersona                    INTEGER,
  pin_observaciones                VARCHAR(255)
) RETURNS int(11)
BEGIN
  DECLARE      var_idusuario                INTEGER DEFAULT pin_idusuario;
  DECLARE      var_login                    VARCHAR(32) DEFAULT pin_login;
  DECLARE      var_password                 VARCHAR(32) DEFAULT pin_password;
  DECLARE      var_idpersona                INTEGER DEFAULT pin_idpersona;
  DECLARE      var_observaciones            VARCHAR(255) DEFAULT pin_observaciones;

  DECLARE      var_count                    INTEGER         DEFAULT 0;

      SELECT COUNT(*)
        INTO var_count
        FROM usuario u
        WHERE u.idusuario = var_idusuario;

      IF (var_count > 0) THEN
        UPDATE usuario u SET
          u.login = var_login,
          u.password = var_password,
          u.idpersona = var_idpersona,
          u.observaciones = var_observaciones
        WHERE u.idusuario = var_idusuario;
      ELSE
        IF (var_idusuario IS NULL) THEN
          SET var_idusuario = getMaxCodigoUsuario()+1;
        END IF;
        INSERT INTO usuario(idusuario, login, password, idpersona, observaciones)
          VALUES (var_idusuario, var_login, var_password, var_idpersona, var_observaciones);
      END IF;

      RETURN var_idusuario;
END;

 $$

DELIMITER ;