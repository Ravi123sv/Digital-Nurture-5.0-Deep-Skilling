--EXERCISE6--
SET SERVEROUTPUT ON;

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
BEGIN
    FOR stmt_rec IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE('Statement -> ' || stmt_rec.Name || ' | ' || TO_CHAR(stmt_rec.TransactionDate, 'YYYY-MM-DD') || ' | ' || stmt_rec.TransactionType || ': $' || stmt_rec.Amount);
    END LOOP;
END;
/

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID FROM Accounts;
BEGIN
    FOR acc_rec IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - 50
        WHERE AccountID = acc_rec.AccountID;
    END LOOP;
    COMMIT;
END;
/

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans;
BEGIN
    FOR loan_rec IN UpdateLoanInterestRates LOOP
        UPDATE Loans
        SET InterestRate = loan_rec.InterestRate + 0.5
        WHERE LoanID = loan_rec.LoanID;
    END LOOP;
    COMMIT;
END;
/