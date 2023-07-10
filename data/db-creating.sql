SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `inforce` DEFAULT CHARACTER SET utf8 ;
USE `inforce` ;

CREATE TABLE IF NOT EXISTS `inforce`.`user` (
                                                `id` INT NOT NULL,
                                                `first_name` VARCHAR(45) NOT NULL,
                                                `last_name` VARCHAR(45) NOT NULL,
                                                `email` VARCHAR(45) NOT NULL,
                                                `country` VARCHAR(45) NOT NULL,
                                                `city` VARCHAR(45) NOT NULL,
                                                `password` VARCHAR(45) NOT NULL,
                                                PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `inforce`.`role` (
                                                `id` INT NOT NULL,
                                                `name` VARCHAR(45) NOT NULL,
                                                PRIMARY KEY (`id`))
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `inforce`.`user_roles` (
                                                      `user_id` INT NOT NULL,
                                                      `role_id` INT NOT NULL,
                                                      INDEX `fk_user_roles_user_idx` (`user_id` ASC) VISIBLE,
                                                      INDEX `fk_user_roles_role1_idx` (`role_id` ASC) VISIBLE,
                                                      CONSTRAINT `fk_user_roles_user`
                                                          FOREIGN KEY (`user_id`)
                                                              REFERENCES `inforce`.`user` (`id`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE CASCADE ,
                                                      CONSTRAINT `fk_user_roles_role1`
                                                          FOREIGN KEY (`role_id`)
                                                              REFERENCES `inforce`.`role` (`id`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE CASCADE )
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
