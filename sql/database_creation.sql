-- MySQL Workbench Forward Engineering


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema servlet_exhibitions_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema servlet_exhibitions_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `servlet_exhibitions_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `servlet_exhibitions_db` ;

-- -----------------------------------------------------
-- Table `servlet_exhibitions_db`.`exhibitions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servlet_exhibitions_db`.`exhibitions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description_english` VARCHAR(255) NOT NULL,
  `description_ukrainian` VARCHAR(255) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `theme_english` VARCHAR(255) NOT NULL,
  `theme_ukrainian` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `servlet_exhibitions_db`.`exhibition_events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servlet_exhibitions_db`.`exhibition_events` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `date_from` DATE NOT NULL,
  `date_to` DATE NOT NULL,
  `event_status` VARCHAR(255) NOT NULL,
  `max_available_places` BIGINT NOT NULL,
  `sold_places` BIGINT NOT NULL,
  `ticket_cost` DECIMAL(19,2) NOT NULL,
  `time_from` TIME NOT NULL,
  `time_to` TIME NOT NULL,
  `exhibition_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcaee8h2r3wkk23stbgmwt6j4c` (`exhibition_id` ASC) VISIBLE,
  CONSTRAINT `FKcaee8h2r3wkk23stbgmwt6j4c`
    FOREIGN KEY (`exhibition_id`)
    REFERENCES `servlet_exhibitions_db`.`exhibitions` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `servlet_exhibitions_db`.`halls`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servlet_exhibitions_db`.`halls` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description_english` VARCHAR(1024) NOT NULL,
  `description_ukrainian` VARCHAR(1024) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `name_english` VARCHAR(255) NOT NULL,
  `name_ukrainian` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `servlet_exhibitions_db`.`exhibition_events_halls`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servlet_exhibitions_db`.`exhibition_events_halls` (
  `exhibition_event_id` BIGINT NOT NULL,
  `halls_id` BIGINT NOT NULL,
  INDEX `FK2b4b2x01n77sxmslx012oqwhm` (`halls_id` ASC) VISIBLE,
  INDEX `FKc0l1hm1d7vvitlq2g5oi1x9ti` (`exhibition_event_id` ASC) VISIBLE,
  CONSTRAINT `FK2b4b2x01n77sxmslx012oqwhm`
    FOREIGN KEY (`halls_id`)
    REFERENCES `servlet_exhibitions_db`.`halls` (`id`),
  CONSTRAINT `FKc0l1hm1d7vvitlq2g5oi1x9ti`
    FOREIGN KEY (`exhibition_event_id`)
    REFERENCES `servlet_exhibitions_db`.`exhibition_events` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `servlet_exhibitions_db`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servlet_exhibitions_db`.`roles` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ofx66keruapi6vyqpv6f2or37` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `servlet_exhibitions_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servlet_exhibitions_db`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `role_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_r43af9ap4edm43mmtq01oddj6` (`username` ASC) VISIBLE,
  INDEX `FKp56c1712k691lhsyewcssf40f` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f`
    FOREIGN KEY (`role_id`)
    REFERENCES `servlet_exhibitions_db`.`roles` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `servlet_exhibitions_db`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servlet_exhibitions_db`.`orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `exhibition_event_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKbpjvifk88ctxkisuunyhptm5g` (`exhibition_event_id` ASC) VISIBLE,
  INDEX `FK32ql8ubntj5uh44ph9659tiih` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih`
    FOREIGN KEY (`user_id`)
    REFERENCES `servlet_exhibitions_db`.`users` (`id`),
  CONSTRAINT `FKbpjvifk88ctxkisuunyhptm5g`
    FOREIGN KEY (`exhibition_event_id`)
    REFERENCES `servlet_exhibitions_db`.`exhibition_events` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
