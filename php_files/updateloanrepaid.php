<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$id = $_POST['id'];
		require_once "connect.php";
		$sql = "UPDATE Loans SET Status = 'Repaid' WHERE LoanID = '$id'";

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