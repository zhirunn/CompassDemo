
CREATE TABLE Users
(
	UserID INT NOT NULL AUTO_INCREMENT ,
	UserName VARCHAR(60) NOT NULL ,
	Password VARCHAR(60) NOT NULL ,
	AccountType INT NOT NULL, 
					/* Users are screened and placed into different Account Types as follows:
					0 - Trial Account Type/Unscreened
					1 - Low Risk Borrower
					2 - High Risk Borrower
					3 - Low Risk Lender
					4 - High Risk Lender
					*/
	CONSTRAINT CK_AccountType CHECK (accountType BETWEEN 0 AND 4),
	CONSTRAINT PK_UserID PRIMARY KEY (userID),
	CONSTRAINT UQ_UserName UNIQUE (userName)
);

CREATE TABLE BorrowerDetails
(
	UserID INT NOT NULL AUTO_INCREMENT, 
	FullName NVARCHAR(100) NOT NULL,
	PhoneNumber NVARCHAR(12) NOT NULL,
	Email NVARCHAR(60) NOT NULL,
	Address NVARCHAR(60) NOT NULL,
	Employment NVARCHAR(60) NOT NULL,
	JobTitle NVARCHAR(60) NOT NULL,
	PhotoDocuments LONGBLOB NOT NULL,
	PRIMARY KEY (UserID,FullName),
	CONSTRAINT CHK_AllDetails CHECK(Fullname !='''' AND PhoneNumber !='''' AND Email !='''' AND Address !='''' AND Employment !='''' AND JobTitle !=''''),
	CONSTRAINT FK_UserID FOREIGN KEY (UserID)REFERENCES Users(UserID)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Loans
(
	LoanID INT NOT NULL AUTO_INCREMENT,
	AmountApproved DECIMAL(15,2), 
	APR FLOAT, 
	TermDate DATE,
	PaymentDue DATE,
	Principal DECIMAL(15,2),
	Interest FLOAT,
	Status VARCHAR(8) NOT NULL,
	BorrowerID INT NOT NULL ,
	LenderID INT,
	CONSTRAINT PK_LoanID PRIMARY KEY (LoanID),
	CONSTRAINT CK_Status CHECK (Status='Pending' OR Status='Approved' OR Status='Declined'),
	CONSTRAINT FK_BorrowerID FOREIGN KEY (BorrowerID) REFERENCES Users(UserID),
	CONSTRAINT FK_LenderID FOREIGN KEY (LenderID) REFERENCES Users(UserID)	
);
/* 
Stored Procedures 
*/
-- Register User
CREATE PROCEDURE `RegisterUser`(
	IN `@Username` CHAR(60),
	IN `@Password` CHAR(60),
	IN `@Activation` BIT,
	IN `@AccountType` INT
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	INSERT INTO Users(UserName,Password,Activation,AccountType)
	VALUES(@UserName,@Password,@Activation,@AccountType);
END

-- Show all Users
CREATE DEFINER=`root`@`localhost` PROCEDURE `ShowAllUsers`()
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
SELECT * FROM USERS;
END

-- Update users activation and account type 
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateUser`(
	IN `paramUserID` INT,
	IN `paramAccountType` INT



)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
UPDATE Users
SET AccountType = paramAccountType
WHERE UserID = paramUserID;
END

-- Check if login information entered is correct, 
-- Output parameter returned will be NULL if incorrect, will be a value 0 - 4 if correct
CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckLogin`(
	IN `paramUsername` CHAR(60),
	IN `paramPassword` CHAR(60),
	OUT `paramLoggedAccountType` INT



)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
SET paramLoggedAccountType = NULL;
IF (SELECT COUNT(*) FROM Users WHERE Username = paramUsername AND Password = PASSWORD(paramPassword)) > 0
THEN
SET paramLoggedAccountType = (SELECT AccountType FROM Users WHERE Username = paramUsername AND Password = PASSWORD(paramPassword));
END IF;
END

--- Apply for loan stored procedure

CREATE DEFINER=`root`@`localhost` PROCEDURE `ApplyForLoan`(
	IN `paramUserID` INT,
	IN `paramFullName` CHAR(60),
	IN `paramPhoneNumber` CHAR(12),
	IN `paramEmail` CHAR(60),
	IN `paramAddress` CHAR(60),
	IN `paramEmployment` CHAR(60),
	IN `paramJobTitle` CHAR(60)




,
	IN `paramPhotDocuments` LONGBLOB
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
INSERT INTO BorrowerDetails(UserID,FullName,PhoneNumber,Email,Address,Employment,JobTitle,PhotoDocuments)
	VALUES(paramUserID,paramFullName,paramPhoneNumber,paramEmail,paramAddress,paramEmployment,paramJobTitle,paramPhotoDocuments);
INSERT INTO Loans(BorrowerID, Status)
	VALUES(paramUserID,'Pending');
END

-- Update Borrower Information Stored Procedure
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateBorrowers`(
	IN `paramUserID` INT,
	IN `paramFullName` CHAR(60),
	IN `paramPhoneNumber` CHAR(12)
,
	IN `paramEmail` CHAR(60),
	IN `paramAddress` CHAR(60),
	IN `paramEmployment` CHAR(60),
	IN `paramJobTitle` CHAR(60)


)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
UPDATE BorrowerDetails
SET FullName = paramFullName, PhoneNumber = paramPhoneNumber,Email = paramEmail, Address = paramAddress, Employment = paramEmployment, JobTitle = paramJobTitle 
WHERE UserID = paramUserID;
END
-- Deleting Borrower Information 
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteBorrowerDetails`(
	IN `paramUserID` INT

)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
DELETE FROM borrowerdetails WHERE UserID = paramUserID; 

END
-- Shows the status of the loans the user is part of (either borrower or lender) 
CREATE DEFINER=`root`@`localhost` PROCEDURE `ShowLoanStatus`(
	IN `paramID` INT

)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
SELECT * FROM Loans WHERE BorrowerID=paramID OR LenderID=paramID;
END
-- Shows the status of all high/low risk pending loans depending on the lender's account type
CREATE DEFINER=`root`@`localhost` PROCEDURE `ShowPendingLoans`(
	IN `paramAccountType` INT

)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
IF (paramAccountType = 3) THEN
	BEGIN
	SELECT * FROM Loans  INNER JOIN Users ON Loans.BorrowerID = Users.UserID 
				WHERE Loans.Status = 'Pending' AND Users.AccountType = 1;
	END;
ELSE 
	IF (paramAccountType = 4) THEN
	BEGIN
	SELECT * FROM Loans  INNER JOIN Users ON Loans.BorrowerID = Users.UserID 
			WHERE Loans.Status = 'Pending' AND Users.AccountType = 2;
	END;
	END IF;
END IF;
END
