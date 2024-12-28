CREATE DEFINER=`root`@`localhost` PROCEDURE `displayAllEmployees`()
BEGIN
    SELECT emp_id, emp_name, emp_age, emp_salary 
    FROM employee_details;
END