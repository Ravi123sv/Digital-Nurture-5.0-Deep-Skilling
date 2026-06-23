--EXERCISE3--
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Processed 1% monthly interest for all savings accounts.');
END;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (p_bonus_percent / 100))
    WHERE Department = p_department;
    
    IF SQL%NOTFOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: No employees found in the ' || p_department || ' department.');
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Success: ' || p_bonus_percent || '% bonus applied to ' || p_department || ' department.');
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_current_balance NUMBER;
BEGIN
    SELECT Balance INTO v_current_balance
    FROM Accounts
    WHERE AccountID = p_from_account;
    
    IF v_current_balance >= p_amount THEN
        UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
        UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;
        
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Success: Transferred $' || p_amount || ' from Account ' || p_from_account || ' to Account ' || p_to_account);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Error: Transfer declined. Insufficient balance in Account ' || p_from_account);
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: One or both of the account IDs do not exist in the system.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Critical Error: ' || SQLERRM);
END;
/