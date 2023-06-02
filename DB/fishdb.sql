-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fishdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fishdb` ;

-- -----------------------------------------------------
-- Schema fishdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fishdb` DEFAULT CHARACTER SET utf8 ;
USE `fishdb` ;

-- -----------------------------------------------------
-- Table `fish`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fish` ;

CREATE TABLE IF NOT EXISTS `fish` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `common_name` VARCHAR(45) NOT NULL,
  `scientific_name` VARCHAR(45) NULL,
  `water_type` TINYINT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS angler@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'angler'@'localhost' IDENTIFIED BY 'angler';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'angler'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `fish`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishdb`;
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `water_type`, `image_url`) VALUES (1, 'Greenback Cuttroat Trout', NULL, 0, NULL);
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `water_type`, `image_url`) VALUES (2, 'Kokanee Salmon', NULL, 0, NULL);

COMMIT;

