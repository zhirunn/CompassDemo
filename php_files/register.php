<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$userid = $_POST['UserID'];
		$email = $_POST['Email'];
		$password = $_POST['Password'];
		require_once "connect.php";
		$sql = "INSERT INTO Users (UserID, Email, Password) VALUES ('$userid', '$password', '$email')";
		
		if(mysqli_query($conn, $sql)) {
			echo "Successfully Registered";
		} else {
			echo "Registration could not be completed.";
		}
	} else {
		echo "Unexpected Error.";
	}
?>