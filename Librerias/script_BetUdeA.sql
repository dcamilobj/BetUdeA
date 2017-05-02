
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
--
-- Estructura de la tabla usuario
--
CREATE TABLE IF NOT EXISTS `usuario` (
  `cedula`        VARCHAR(15)   NOT NULL,
  `nombre`        VARCHAR(30)   NOT NULL,
  `apellidos`  	  VARCHAR(30)   NOT NULL,
  `email`         VARCHAR(125)  NOT NULL,
  `password`      VARCHAR(125)  NOT NULL,
  PRIMARY KEY(`cedula`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


--
-- Estructura de la tabla `periodo_simulacion`
--
CREATE TABLE IF NOT EXISTS `periodo_simulacion` (
  `id`            INT(12)     NOT NULL AUTO_INCREMENT,
  `usuario_id`    VARCHAR(15) NOT NULL,
  `saldo`         INT(8)      NOT NULL,
  `fecha_inicio`  DATE        NOT NULL,
  `fecha_fin`     DATE,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`usuario_id`) REFERENCES usuario(`cedula`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

--
-- Estructura de la tabla `apuesta`
--
CREATE TABLE IF NOT EXISTS `apuesta` (
  `id`                     INT(12)      NOT NULL AUTO_INCREMENT,
  `evento`                 VARCHAR(50)  NOT NULL,
  `fecha_evento`           DATE         NOT NULL,
  `valor_apostado`         INT(8)       NOT NULL,
  `cuota`                  DECIMAL(5,2) NOT NULL,
  `opcion_seleccionada`    VARCHAR(15)  NOT NULL,
  `estado`                 VARCHAR(20)  NOT NULL,
  `fecha_apuesta`          DATE         NOT NULL,
  `periodo_simulacion_id`  INT(12)      NOT NULL,
  `opcion_correcta`        VARCHAR(15),
  PRIMARY KEY(`id`),
  FOREIGN KEY(`periodo_simulacion_id`) REFERENCES periodo_simulacion(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
