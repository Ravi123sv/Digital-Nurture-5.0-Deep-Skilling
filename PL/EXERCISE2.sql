--EXERCISE2_SCENARIO1--
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_source_account IN NUMBER,
    p_target_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_source_balance NUMBER;
    insufficient_funds EXCEPTION; 
BEGIN
    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_source_account;

    IF v_source_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_source_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_target_account;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Transferred $' || p_amount);

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK; 
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in Account ' || p_source_account);
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Critical Error: ' || SQLERRM);
END;
/

--EEXERCISE2-SCENARIO2--
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_increase_percent IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (p_increase_percent / 100))
    WHERE EmployeeID = p_employee_id;

    IF SQL%NOTFOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' does not exist.');
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Success: Salary updated.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Critical Error: ' || SQLERRM);
END;
/

--EXERISE2_SCENARIO3--
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Customer ' || p_name || ' added to the database.');

EXCEPTION
    WHEN DUP_VAL_ON_IND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: A customer with ID ' || p_customer_id || ' already exists!');
            WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Critical Error: ' || SQLERRM);
END;
/