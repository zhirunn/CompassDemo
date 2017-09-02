<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
        require_once "connect.php";
        $id = $_POST['UserID'];
        $grade = $_POST['Grade'];
		$sql = "INSERT INTO Loans (Grade,Status,BorrowerID,DateLastModified)
		VALUES('$grade','Pending','$id',NOW());
INSERT INTO LoanCredentials (BankStatement,FirstPayStub,SecondPayStub,ThirdPayStub,DriversID,EmploymentLetter,ProofofAddress,PreAuthorizedDebit,SocialInsuranceNumber,OtherID,PreAuthorizedAgreement)
		VALUES(null,null,null,null,null,null,null,null,null,null,null)";
   		if(mysqli_query($conn, $sql)) {
ob_clean();
   			echo "Successfully created a new loan";
   		} else {
   			ob_clean();
   			echo "Could create a new loan";
   		}
        mysqli_close($conn);
        }
?>