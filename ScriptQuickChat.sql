-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bd_proyecto_fct
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bd_proyecto_fct
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_proyecto_fct` DEFAULT CHARACTER SET utf8 ;
USE `bd_proyecto_fct` ;

-- -----------------------------------------------------
-- Table `bd_proyecto_fct`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_proyecto_fct`.`chat` (
  `chat_id` INT NOT NULL AUTO_INCREMENT,
  `chat_foto` VARCHAR(255) NULL DEFAULT NULL,
  `chat_nombre` VARCHAR(255) NULL DEFAULT NULL,
  `chat_puerto` INT NULL DEFAULT NULL,
  PRIMARY KEY (`chat_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bd_proyecto_fct`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_proyecto_fct`.`usuario` (
  `usu_id` INT NOT NULL AUTO_INCREMENT,
  `usu_activo` INT NULL DEFAULT NULL,
  `usu_contrasenha` VARCHAR(255) NULL DEFAULT NULL,
  `usu_foto` VARCHAR(255) NULL DEFAULT NULL,
  `usu_nombre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`usu_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bd_proyecto_fct`.`participantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_proyecto_fct`.`participantes` (
  `part_id` INT NOT NULL AUTO_INCREMENT,
  `id_chat` INT NULL DEFAULT NULL,
  `id_usu` INT NULL DEFAULT NULL,
  PRIMARY KEY (`part_id`),
  INDEX `FKelk1nx1w0d94fpf0kque5qycs` (`id_chat` ASC) VISIBLE,
  INDEX `FK99tt8b67frgt5vkjdibpu5tx9` (`id_usu` ASC) VISIBLE,
  CONSTRAINT `FK99tt8b67frgt5vkjdibpu5tx9`
    FOREIGN KEY (`id_usu`)
    REFERENCES `bd_proyecto_fct`.`usuario` (`usu_id`),
  CONSTRAINT `FKelk1nx1w0d94fpf0kque5qycs`
    FOREIGN KEY (`id_chat`)
    REFERENCES `bd_proyecto_fct`.`chat` (`chat_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bd_proyecto_fct`.`mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_proyecto_fct`.`mensaje` (
  `msj_id` INT NOT NULL AUTO_INCREMENT,
  `mensaje` VARCHAR(255) NULL DEFAULT NULL,
  `id_part` INT NULL DEFAULT NULL,
  PRIMARY KEY (`msj_id`),
  INDEX `FKjs52csnxcrpu96aq5ddrgvyk6` (`id_part` ASC) VISIBLE,
  CONSTRAINT `FKjs52csnxcrpu96aq5ddrgvyk6`
    FOREIGN KEY (`id_part`)
    REFERENCES `bd_proyecto_fct`.`participantes` (`part_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `bd_proyecto_fct`.`usuario` (`usu_id`, `usu_activo`, `usu_contrasenha`, `usu_foto`, `usu_nombre`) VALUES ('1', '0', 'MQ==', 'C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoEstandar.png', 'prueba1');
INSERT INTO `bd_proyecto_fct`.`usuario` (`usu_id`, `usu_activo`, `usu_contrasenha`, `usu_foto`, `usu_nombre`) VALUES ('2', '0', 'Mg== ', 'C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoEstandar.png', 'prueba2');

INSERT INTO `bd_proyecto_fct`.`chat` (`chat_id`, `chat_foto`, `chat_nombre`, `chat_puerto`) VALUES ('1', 'C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilHombre.png', 'Grupo1', '6000');
INSERT INTO `bd_proyecto_fct`.`chat` (`chat_id`, `chat_foto`, `chat_nombre`, `chat_puerto`) VALUES ('2', 'C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilMujer.png', 'Grupo2', '6001');

INSERT INTO `bd_proyecto_fct`.`participantes` (`part_id`, `id_chat`, `id_usu`) VALUES ('1', '1', '1');
INSERT INTO `bd_proyecto_fct`.`participantes` (`part_id`, `id_chat`, `id_usu`) VALUES ('2', '2', '1');
INSERT INTO `bd_proyecto_fct`.`participantes` (`part_id`, `id_chat`, `id_usu`) VALUES ('3', '2', '2');

INSERT INTO `bd_proyecto_fct`.`mensaje` (`msj_id`, `mensaje`, `id_part`) VALUES ('1', 'El usuario prueba1 se ha unido', '1');
INSERT INTO `bd_proyecto_fct`.`mensaje` (`msj_id`, `mensaje`, `id_part`) VALUES ('2', 'El usuario prueba1 se ha unido', '2');
INSERT INTO `bd_proyecto_fct`.`mensaje` (`msj_id`, `mensaje`, `id_part`) VALUES ('3', 'El usuario prueba2 se ha unido', '3');
INSERT INTO `bd_proyecto_fct`.`mensaje` (`msj_id`, `mensaje`, `id_part`) VALUES ('4', 'hola', '2');
INSERT INTO `bd_proyecto_fct`.`mensaje` (`msj_id`, `mensaje`, `id_part`) VALUES ('5', 'hola', '3');
