--EXERCISE1_SCENARIO1--
BEGIN
    FOR customer_rec IN (SELECT CustomerID, DOB FROM Customers) LOOP
        IF (MONTHS_BETWEEN(SYSDATE, customer_rec.DOB) / 12) > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = customer_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;

--EXERCISE1_SCENARIO2--

ALTER TABLE Customers ADD IsVIP VARCHAR2(5) DEFAULT 'FALSE';

BEGIN
    FOR cust_rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
 /

 --EXERCISE1_SCENARIO3--

 SET SERVEROUTPUT ON;

BEGIN
    FOR loan_rec IN (
        SELECT c.Name, l.EndDate 
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND (SYSDATE + 30)
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: ' || loan_rec.Name || ', your loan is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/