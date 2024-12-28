use jdbc;

delimiter $$

create function substract(a int, b int) 
returns integer
deterministic
begin
	return a-b;
    end $$
    delimiter ;
    select substract(45,12) as sub from dual;