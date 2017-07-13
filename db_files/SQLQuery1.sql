CREATE DATABASE GreatNorthCapDB
USE GreatNorthCapDB
GO
DROP TABLE Users
DROP TABLE Loans
CREATE TABLE Users
(
	UserID INT IDENTITY(1000,1) NOT NULL CONSTRAINT PK_UserID PRIMARY KEY,
	UserName NVARCHAR(60) NOT NULL,
	Password NVARCHAR(60) NOT NULL,
	Activation BIT,
	AccountType INT NOT NULL,
	CONSTRAINT CK_AccountType CHECK (AccountType BETWEEN 0 AND 4)
)
CREATE TABLE BorrowerDetails
(
	UserID INT NOT NULL CONSTRAINT FK_UserID FOREIGN KEY REFERENCES Users(UserID), 
	FullName NVARCHAR(100) NOT NULL,
	PhoneNumber NVARCHAR(12) NOT NULL,
	Email NVARCHAR(60) NOT NULL,
	Address NVARCHAR(60) NOT NULL,
	Employment NVARCHAR(60) NOT NULL,
	PRIMARY KEY (FullName,PhoneNumber,Email,Address,Employment)
)
CREATE TABLE Loans
(
	LoanID INT IDENTITY(1000,1) NOT NULL CONSTRAINT PK_LoanID PRIMARY KEY,
	AmountApproved MONEY NOT NULL, 
	APR FLOAT NOT NULL, 
	TermDate DATE NOT NULL,
	PaymentDue DATE NOT NULL,
	Principal MONEY NOT NULL,
	Interest INT NOT NULL,
	Status NVARCHAR(8) NOT NULL CONSTRAINT CK_Status CHECK (Status IN ('Pending','Approved','Declined')),
	BorrowerID INT NOT NULL CONSTRAINT FK_BorrowerID FOREIGN KEY REFERENCES Users(UserID),
	LenderID INT NOT NULL CONSTRAINT FK_LenderID FOREIGN KEY REFERENCES Users(UserID)
)

USE GreatNorthCapDB 
GO
DROP PROCEDURE RegisterUser
CREATE PROCEDURE RegisterUser(  @UserName NVARCHAR(60) = NULL,
								@Password NVARCHAR(60) = NULL,
								@Activation BIT = 0,
								@AccountType INT = 0)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1

IF	@UserName  IS NULL
	RAISERROR('RegisterUser - Parameter Required: @UserName',16,1)
ELSE
	IF @Password IS NULL
	RAISERROR('RegisterUser - Parameter Required: @Password',16,1)
	ELSE
	BEGIN
		IF NOT EXISTS 
			( SELECT * FROM USERS WHERE UserName = @UserName)
				BEGIN
					INSERT INTO Users
					(UserName,Password,Activation,AccountType)
					VALUES
					(@UserName,@Password,@Activation,@AccountType)
				END
		ELSE
		RAISERROR('RegisterUser - User already exists',16,1)

		IF @@ERROR = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('RegisterUser INSERT Error: Users Table',16,1)
	END
	RETURN @ReturnCode

GO

CREATE PROCEDURE DeleteUser(    @UserName NVARCHAR(60) = NULL,
								@Password NVARCHAR(60) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1

IF	@UserName  IS NULL
	RAISERROR('DeleteUser - Parameter Required: @UserName',16,1)
ELSE
	IF @Password IS NULL
	RAISERROR('DeleteUser - Parameter Required: @Password',16,1)
	ELSE
	BEGIN
		SELECT UserID FROM Users WHERE Username = @UserName AND 
		

		IF @@ERROR = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('DeleteUser - Remove Error: Users Table',16,1)
	END
	RETURN @ReturnCode