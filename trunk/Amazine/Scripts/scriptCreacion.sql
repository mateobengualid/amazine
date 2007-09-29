DROP TABLE IF EXISTS `libreria`.`categoria`;
CREATE TABLE  `libreria`.`categoria` (
  `idcategoria` smallint(6) NOT NULL,
  `descripcion` varchar(40) NOT NULL,
  PRIMARY KEY  (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`persona`;
CREATE TABLE  `libreria`.`persona` (
  `idpersona` int(11) NOT NULL,
  `tipodoc` varchar(8) NOT NULL default 'DNI',
  `nrodoc` varchar(16) NOT NULL,
  `apellido` varchar(32) NOT NULL,
  `nombre` varchar(32) NOT NULL,
  `sexo` char(1) NOT NULL,
  `fecnac` date NOT NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`usuario`;
CREATE TABLE  `libreria`.`usuario` (
  `idusuario` int(11) NOT NULL,
  `login` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `idpersona` int(11) NOT NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`idusuario`),
  UNIQUE KEY `login` (`login`),
  KEY `idpersona` (`idpersona`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`producto`;
CREATE TABLE  `libreria`.`producto` (
  `idproducto` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(300) default NULL,
  `precio` double NOT NULL,
  `idcategoria` smallint(6) NOT NULL,
  `fechasalida` date NOT NULL,
  PRIMARY KEY  (`idproducto`),
  KEY `idcategoria` (`idcategoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`cd`;
CREATE TABLE  `libreria`.`cd` (
  `idproducto` int(11) NOT NULL,
  `duracion` varchar(10) NOT NULL,
  `autor` varchar(25) NOT NULL,
  `sellodiscografico` varchar(15) default NULL,
  PRIMARY KEY  (`idproducto`),
  CONSTRAINT `cd_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`libro`;
CREATE TABLE  `libreria`.`libro` (
  `idproducto` int(11) NOT NULL,
  `duracion` smallint(6) NOT NULL,
  `isbn` varchar(15) NOT NULL,
  `editorial` varchar(25) NOT NULL,
  `autor` varchar(40) NOT NULL,
  `idioma` varchar(15) NOT NULL default 'ESPANOL',
  PRIMARY KEY  (`idproducto`),
  UNIQUE KEY `isbn` (`isbn`),
  CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`pelicula`;
CREATE TABLE  `libreria`.`pelicula` (
  `idproducto` int(11) NOT NULL,
  `duracion` varchar(10) default NULL,
  `formato` varchar(10) NOT NULL default 'DVD',
  PRIMARY KEY  (`idproducto`),
  CONSTRAINT `pelicula_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`personadireccion`;
CREATE TABLE  `libreria`.`personadireccion` (
  `idpersona` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `tipo` char(1) NOT NULL default 'P',
  `direccioncp` varchar(12) NOT NULL,
  `direccionlocalidad` varchar(40) NOT NULL,
  `direccioncalle` varchar(40) NOT NULL,
  `direccionnro` varchar(8) NOT NULL,
  `direccionpuerta` varchar(8) default NULL,
  `observaciones` varchar(64) default NULL,
  PRIMARY KEY  (`idpersona`,`orden`),
  CONSTRAINT `personadireccion_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`personaemail`;
CREATE TABLE  `libreria`.`personaemail` (
  `idpersona` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `tipo` char(1) NOT NULL default 'P',
  `email` varchar(40) default NULL,
  `observaciones` varchar(64) default NULL,
  PRIMARY KEY  (`idpersona`,`orden`),
  CONSTRAINT `personaemail_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`personatelefono`;
CREATE TABLE  `libreria`.`personatelefono` (
  `idpersona` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `tipo` char(1) NOT NULL default 'C',
  `telefono` varchar(24) NOT NULL,
  `observaciones` varchar(64) default NULL,
  PRIMARY KEY  (`idpersona`,`orden`),
  CONSTRAINT `personatelefono_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`privilegio`;
CREATE TABLE  `libreria`.`privilegio` (
  `idprivilegio` int(11) NOT NULL,
  `cgoprivilegio` varchar(32) NOT NULL,
  `descripcion` varchar(64) NOT NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`idprivilegio`),
  UNIQUE KEY `cgoprivilegio` (`cgoprivilegio`),
  KEY `privilegio_descripcion` (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`rolsistema`;
CREATE TABLE  `libreria`.`rolsistema` (
  `idrolsistema` int(11) NOT NULL,
  `cgorolsistema` varchar(16) NOT NULL,
  `descripcion` varchar(64) NOT NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`idrolsistema`),
  UNIQUE KEY `cgorolsistema` (`cgorolsistema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`rolsistemaprivilegio`;
CREATE TABLE  `libreria`.`rolsistemaprivilegio` (
  `idrolsistema` int(11) NOT NULL,
  `idprivilegio` int(11) NOT NULL,
  `permit` char(1) NOT NULL default 'S',
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`idrolsistema`,`idprivilegio`),
  KEY `idprivilegio` (`idprivilegio`),
  CONSTRAINT `rolsistemaprivilegio_ibfk_1` FOREIGN KEY (`idrolsistema`) REFERENCES `rolsistema` (`idrolsistema`),
  CONSTRAINT `rolsistemaprivilegio_ibfk_2` FOREIGN KEY (`idprivilegio`) REFERENCES `privilegio` (`idprivilegio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`personaprivilegio`;
CREATE TABLE  `libreria`.`personaprivilegio` (
  `idpersona` int(11) NOT NULL,
  `idprivilegio` int(11) NOT NULL,
  `permit` char(1) NOT NULL default 'S',
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`idpersona`,`idprivilegio`),
  KEY `idprivilegio` (`idprivilegio`),
  CONSTRAINT `personaprivilegio_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`),
  CONSTRAINT `personaprivilegio_ibfk_2` FOREIGN KEY (`idprivilegio`) REFERENCES `privilegio` (`idprivilegio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`personarolsistema`;
CREATE TABLE  `libreria`.`personarolsistema` (
  `idpersona` int(11) NOT NULL,
  `idrolsistema` int(11) NOT NULL,
  `suspendido` char(1) NOT NULL default 'N',
  `observaciones` varchar(64) default NULL,
  PRIMARY KEY  (`idpersona`,`idrolsistema`),
  KEY `idrolsistema` (`idrolsistema`),
  CONSTRAINT `personarolsistema_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`),
  CONSTRAINT `personarolsistema_ibfk_2` FOREIGN KEY (`idrolsistema`) REFERENCES `rolsistema` (`idrolsistema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`tema`;
CREATE TABLE  `libreria`.`tema` (
  `idproducto` int(11) NOT NULL,
  `idtema` smallint(6) NOT NULL,
  `nombretema` varchar(30) NOT NULL,
  `duracion` varchar(6) default NULL,
  `autortema` varchar(30) NOT NULL,
  PRIMARY KEY  (`idtema`,`idproducto`),
  KEY `fk_tema_idproducto` (`idproducto`),
  CONSTRAINT `tema_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `cd` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`transaccion`;
CREATE TABLE  `libreria`.`transaccion` (
  `idtransaccion` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `importe` double NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY  (`idtransaccion`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `transaccion_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;

DROP TABLE IF EXISTS `libreria`.`detalletransaccion`;
CREATE TABLE  `libreria`.`detalletransaccion` (
  `idtransaccion` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `importe` double NOT NULL,
  `cantidad` smallint(6) NOT NULL,
  PRIMARY KEY  (`idtransaccion`,`idproducto`),
  KEY `idproducto` (`idproducto`),
  CONSTRAINT `detalletransaccion_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`),
  CONSTRAINT `detalletransaccion_ibfk_2` FOREIGN KEY (`idtransaccion`) REFERENCES `transaccion` (`idtransaccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
GRANT ALL ON categoria TO "librero";
commit;