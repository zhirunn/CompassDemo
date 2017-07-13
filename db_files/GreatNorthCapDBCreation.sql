CREATE DATABASE GreatNorthCapDB
USE GreatNorthCapDB
GO

CREATE TABLE Users
(
	UserID INT IDENTITY(1000,1) NOT NULL CONSTRAINT PK_UserID PRIMARY KEY,
	UserName NVARCHAR(60) NOT NULL CONSTRAINT UQ_UserName UNIQUE ,
	Password NVARCHAR(60) NOT NULL,
	Activation BIT, -- Activation Column is a BIT: 0 indicates it is a trial account, 1 indicates activated account
	AccountType INT NOT NULL,
					/* Users are screened and placed into different Account Types as follows:
					0 - Trial Account Type/Unscreened
					1 - Low Risk Borrower
					2 - High Risk Borrower
					3 - Low Risk Lender
					4 - High Risk Lender
					*/
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
	JobTitle NVARCHAR(60) NOT NULL,
	PRIMARY KEY (UserID,FullName)
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
			RAISERROR('RegisterUser - INSERT Error: Users Table',16,1)
	END
	RETURN @ReturnCode

GO

EXEC RegisterUser 'Test','Password'
SELECT * From Users
GO

CREATE PROCEDURE UpdateAccount( @UserID INT = NULL,
								@Activation BIT = NULL,
								@AccountType INT = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1

IF	@UserID  IS NULL
	RAISERROR('UpdateUser - Parameter Required: @UserID',16,1)
ELSE
	IF @Activation IS NULL
	RAISERROR('UpdateUser - Parameter Required: @Activation',16,1)
	ELSE
		IF @AccountType IS NULL 
		RAISERROR('UpdateUser - Parameter Required: @AccountType',16,1)
		ELSE
			IF EXISTS (SELECT * FROM Users WHERE UserID = @UserID )
			BEGIN
				Update Users
				SET Activation = @Activation, AccountType = @AccountType
				WHERE UserID = @UserID
				IF @@ERROR = 0
					SET @ReturnCode = 0
				ELSE
					RAISERROR('UpdateUser - Update Error: Users Table',16,1)
			END
			ELSE
				RAISERROR('UpdateUser - Update Error: UserID does not exist',16,1)
			RETURN @ReturnCode
GO

EXEC UpdateAccount 1000,1,3
SELECT * From Users 
GO

CREATE PROCEDURE DeleteUser(@UserID INT = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1

IF	@UserID  IS NULL
	RAISERROR('DeleteUser - Parameter Required: @UserID',16,1)
ELSE
	BEGIN
		IF EXISTS (SELECT * FROM Users WHERE UserID = @UserID)
			BEGIN
				DELETE FROM Users
				WHERE UserID = @UserID
			END
			ELSE
			RAISERROR('DeleteUser - User does not exist',16,1)
		IF @@ERROR = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('DeleteUser - Remove Error: Users Table',16,1)
	END
	RETURN @ReturnCode

EXEC DeleteUser 1000
SELECT * FROM Users
GO



CREATE PROCEDURE ApplyForLoan ( @Fullname NVARCHAR(100) = NULL,
								@PhoneNumber NVARCHAR(12) = NULL,
								@Email NVARCHAR(60) = NULL,
								@Address NVARCHAR (60) = NULL,
								@Employment NVARCHAR (60) = NULL,
								@JobTitle NVARCHAR (60) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1
IF @Fullname IS NULL
RAISERROR('ApplyForLoan - Parameter Required: @Fullname',16,1)
	ELSE
		IF @PhoneNumber IS NULL 
			RAISERROR('ApplyForLoan - Parameter Required: @PhoneNumber',16,1)
			ELSE
				IF @Email IS NULL
					RAISERROR('ApplyForLoan - Parameter Required: @Email',16,1)
					ELSE
						IF @Address IS NULL
						RAISERROR('ApplyForLoan - Parameter Required: @Address',16,1)
						ELSE
							If @Employment IS NULL
								RAISERROR('ApplyForLoan - Parameter Required: @Employment',16,1)