<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$username = $_POST['UserName'];
		$password = $_POST['Password'];
		$email = $_POST['Email'];
		require_once "connect.php";
		$sql = "INSERT INTO Users (UserName, Password, Email) VALUES ('$username', '$password', '$email')";
		
		if(mysqli_query($conn, $sql)) {
			echo "Successfully Registered";
		} else {
			echo "Registration could not be completed.";
		}
	} else {
		echo "Unexpected Error.";
	}
?>