<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$username = $_POST['UserName'];
		$password = $_POST['Password'];
		require_once "connect.php";
		$sql = "SELECT * FROM Users WHERE UserName = '$username' AND Password = '$password'";
		$check = mysqli_fetch_array(mysqli_query($conn, $sql));
		if(isset($check)) {
			echo "Success";
		} else {
			echo "Failure";
		}
		
?>