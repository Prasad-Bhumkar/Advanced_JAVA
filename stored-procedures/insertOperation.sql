DELIMITER $$

CREATE PROCEDURE insertOperation(
    IN id INT,
    IN name VARCHAR(255),  -- Assuming name is a string, adjust size as needed
    IN age INT,           -- Add age parameter
    IN salary DECIMAL(10, 2)  -- Add salary parameter, adjust precision as needed
)
BEGIN
    INSERT INTO employee_details (emp_id, emp_name, emp_age, emp_salary) 
    VALUES (id, name, age, salary);
END $$

DELIMITER ;

CALL insertOperation(1, 'John Doe', 30, 50000.00);

