<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$useramount = $_POST['AmountApproved'];
		$loanid = $_POST['LoanID'];
		require_once "connect.php";
		$sql = "UPDATE Loans SET AmountApproved = '$useramount' WHERE LoanID = '$loanid'";
		
		if(mysqli_query($conn, $sql)) {
			ob_end_clean();
			echo "Success";
		} else {
			ob_end_clean();
			echo "Failure";
		}
		mysqli_close($conn);
	} else {
		echo "Unexpected Error.";
	}
?>