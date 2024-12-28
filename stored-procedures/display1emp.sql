DELIMITER $$

CREATE PROCEDURE displayDetails(
    IN emp_id INT
)
BEGIN
    SELECT emp_id, emp_name, emp_age, emp_salary 
    FROM employee_details 
    WHERE emp_id = emp_id;  -- Use a different name for the parameter to avoid confusion
END $$

DELIMITER ;