<?php
	require_once "connection_credentials.php";
	$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB);
	
	if(!$conn) {
		echo "We are unable to connect to the database. Please try again later";
	} else {
		echo "You have successfully connected to the database.";
	}
?>