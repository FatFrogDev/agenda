-- Active: 1706908217747@@127.0.0.1@3306@agenda
CREATE DATABASE agenda;

use agenda;

delimiter $$
CREATE DEFINER=`root`@`localhost` FUNCTION `save_person`(
	in_address varchar(70) ,
	in_is_manager tinyint(1) ,
	in_lastname varchar(20) ,
	in_name varchar(20) ) RETURNS tinyint(1)
    DETERMINISTIC
begin
	declare is_found int;
	select id from person where `name`=in_name and lastname=in_lastname into is_found ;
	if is_found is not null then
		return false;
	else
		insert into person (`address`, `is_manager`, `lastname`, `name`) 
			values (in_address, in_is_manager, in_lastname, in_name);
		return  true;
	end if;
end $$
delimiter ;

delimiter $$
CREATE DEFINER=`root`@`localhost` FUNCTION `check_phone_exists`(
	in_phone varchar(15)
    ) RETURNS tinyint(1)
    DETERMINISTIC
begin
	declare is_found int;
	select id from cellphone where `number`=in_phone limit 1 into is_found ;
	if is_found is not null then
		return true;
	else
		return false;
	end if;
end $$
delimiter ;