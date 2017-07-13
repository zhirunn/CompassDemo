USE DataBaist
GO

CREATE TABLE Program
(
	ProgramCode NVARCHAR(10) NOT NULL CONSTRAINT PK_ProgramCode PRIMARY KEY,
	Description NVARCHAR(60) NOT NULL
)

USE DataBaist
GO

CREATE TABLE Student
(
	StudentID NVARCHAR(10) NOT NULL CONSTRAINT PK_StudentID PRIMARY KEY,
	FirstName NVARCHAR(25) NOT NULL,
	LastName NVARCHAR(25) NOT NULL,
	Email NVARCHAR(50) NULL,
	ProgramCode NVARCHAR(10) NOT NULL CONSTRAINT FK_ProgramCode FOREIGN KEY 
	REFERENCES Program(ProgramCode)
)


USE DataBaist
GO

CREATE PROCEDURE AddProgram(@ProgramCode NVARCHAR(10) = NULL, 
							@Description NVARCHAR(60) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1

IF	@ProgramCode IS NULL
	RAISERROR('AddProgram - Parameter Required: @ProgramCode',16,1)
ELSE
	IF @Description IS NULL
	RAISERROR('AddProgram - Parameter Required: @ProgramCode',16,1)
	ELSE
	BEGIN
		INSERT INTO Program
		(ProgramCode,Description)
		VALUES
		(@ProgramCode,@Description)

		IF @@ERROR = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('AddProgram - INSERT Error: Program Table',16,1)
	END
	RETURN @ReturnCode


USE DataBaist
GO
EXECUTE AddProgram 'BAIST','Bachelor of Applied Information Systems Technology'
Select * From Program

USE DataBaist
GO

CREATE PROCEDURE GetProgram(@ProgramCode NVARCHAR(10) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1
IF @ProgramCode IS NULL
	RAISERROR('GetProgram - Parameter Required: @ProgramCode',16,1)
ELSE
	BEGIN
		SELECT ProgramCode,Description
		FROM	Program
		WHERE ProgramCode LIKE @ProgramCode
		IF @@ERROR = 0
		SET @ReturnCode = 0
		ELSE
		RAISERROR('GetProgram - SELECT Error: Program Table',16,1)
	END
RETURN @ReturnCode

USE DataBaist
GO

CREATE PROCEDURE AddStudent(@StudentID NVARCHAR(10) = NULL,
							@FirstName NVARCHAR(25) = NULL,
							@LastName NVARCHAR(25) = NULL,
							@Email NVARCHAR(50) = NULL,
							@ProgramCode NVARCHAR(10) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1
IF @StudentID IS NULL
	RAISERROR('AddStudent - Parameter Required: @StudentID',16,1)
ELSE
	IF @FirstName IS NULL
	RAISERROR('AddStudent - Parameter Required: @FirstName',16,1)
	ELSE
		IF @LastName IS NULL
			RAISERROR('AddStudent - Parameter Required: @LastName',16,1)
			ELSE
				IF @ProgramCode IS NULL
					RAISERROR('AddStudent - Parameter Required: @ProgramCode',16,1)
					ELSE
					BEGIN
						INSERT INTO Student
						(StudentID,FirstName,LastName,Email,ProgramCode)
						VALUES
						(@StudentID,@FirstName,@LastName,@Email,@ProgramCode)
						IF @@ERROR = 0
							SET @ReturnCode = 0
						ELSE 
							RAISERROR('AddStudent - INSERT Error: Student Table',16,1)
					END
					RETURN @ReturnCode

					
USE DataBaist
GO
EXECUTE AddProgram 'CNT','Computer Engineering Technology'

					

CREATE PROCEDURE GetStudent(@StudentID NVARCHAR(10) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1
IF @StudentID IS NULL
	RAISERROR('GetStudent - Parameter required: @StudentID',16,1)
ELSE
	BEGIN
		SELECT StudentID,FirstName,LastName,Email,ProgramCode
		FROM Student
		WHERE StudentID = @StudentID
		IF @@ERROR = 0
		SET @ReturnCode = 1
		ELSE
			RAISERROR('GetStudent - SELECT error: Student Table',16,1)
	END
	RETURN @ReturnCode

USE DataBaist
GO

CREATE PROCEDURE UpdateStudent(@StudentID NVARCHAR(10) = NULL,
							   @FirstName NVARCHAR(25) = NULL,
							   @LastName NVARCHAR(25) = NULL,
							   @Email NVARCHAR(50) = NULL,
							   @ProgramCode NVARCHAR(10) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1
IF @StudentID IS NULL
	RAISERROR('UpdateStudent - Parameter Required: @StudentID',16,1)
ELSE
	IF @FirstName IS NOT NULL
		BEGIN
		UPDATE Student
		SET FirstName=@FirstName
		WHERE StudentID = @StudentID
		IF @@Error = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('UpdateStudent - UPDATE Error: Student Table -  @FirstName',16,1)
		END
	IF @LastName IS NOT NULL
		BEGIN
		UPDATE Student
		SET LastName = @LastName
		WHERE StudentID = @StudentID 
				IF @@Error = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('UpdateStudent - UPDATE Error: Student Table -  @LastName',16,1)
		END
	IF @ProgramCode IS NOT NULL
		BEGIN
		UPDATE Student
		SET ProgramCode = @ProgramCode
		WHERE StudentID = @StudentID
				IF @@Error = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('UpdateStudent - UPDATE Error: Student Table -  @ProgramCode',16,1)
		END
	
		UPDATE Student
		SET Email = @Email
		WHERE StudentID = @StudentID
		IF @@Error = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('UpdateStudent - UPDATE Error: Student Table -  @Email',16,1)
RETURN @ReturnCode

USE DataBaist
GO

EXECUTE UpdateStudent '2',NULL ,'ValjeanEDITED',NULL,'CNT'
SELECT * FROM Student

USE DataBaist
GO

CREATE PROCEDURE DeleteStudent(@StudentID NVARCHAR(10) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1
IF @StudentID IS NULL
	RAISERROR('DeleteStudent - Required parameter: @StudentID',16,1)
ELSE
	BEGIN
		DELETE FROM Student
		WHERE StudentID = @StudentID
		IF @@ERROR = 0
			SET @ReturnCode = 0
		ELSE
			RAISERROR('DeleteStudent - DELETE Error: Student Table',16,1) 
	END
RETURN @ReturnCode
	
USE DataBaist
GO
EXECUTE AddStudent '2','Jean','Valjean',NULL,'CNT'
SELECT * FROM Student

USE DataBaist
GO

CREATE PROCEDURE GetStudents(@ProgramCode NVARCHAR(10) = NULL)
AS
	DECLARE @ReturnCode INT
	SET @ReturnCode = 1
IF @ProgramCode IS NULL
	RAISERROR('GetStudents - Required parameter: @ProgramCode',16,1)
ELSE
	BEGIN
		SELECT StudentID,FirstName,LastName,Email,ProgramCode
		FROM Student
		WHERE ProgramCode = @ProgramCode
		IF @@ERROR = 0
			SET @ReturnCode = 0
		ELSE
		   RAISERROR('GetStudents - SELECT Error: Student table',16,1)
	END
RETURN @ReturnCode

USE DataBaist
GO
EXECUTE UpdateStudent '1','Dan','Danson','TEST','CNT'
SELECT * FROM STUDENT
