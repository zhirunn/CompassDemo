<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$email = $_POST['Email'];
		$password = $_POST['Password'];
		require_once "connect.php";
		$sql = "SELECT * FROM Users WHERE Email = '$email' AND Password = '$password'";
		$check = mysqli_fetch_array(mysqli_query($conn, $sql));
		if(isset($check)) {
			echo "Login Success";
		} else {
			echo "Login Failure";
		}
		mysqli_close($conn);
	}
?>