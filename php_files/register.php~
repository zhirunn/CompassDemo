<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$userid = $_POST['UserID'];
		$email = $_POST['Email'];
		$password = $_POST['Password'];
		require_once "connect.php";
		$sql = "INSERT INTO Users (UserID, Email, Password) VALUES ('$userid', '$email', '$password')";
		
		if(mysqli_query($conn, $sql)) {
			ob_end_clean();
			echo "Successfully Registered";
		} else {
			ob_end_clean();
			echo "Registration could not be completed.";
		}
	} else {
		echo "Unexpected Error.";
	}
?>