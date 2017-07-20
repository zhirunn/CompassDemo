<?php
	 if($_SERVER['REQUEST_METHOD']=='GET') {
	 	$email = $_GET['Email'];
	 	require_once "connect.php";
	 	$sql = "SELECT * FROM Users WHERE Email='".$email."'";
	 	$res = mysqli_fetch_array(mysqli_query($conn, $sql));
	 	$result = array();
	 	array_push($result, array(
 			"Email"=>$res['Email'],
 			"UserID"=>$res['UserID'],
 			)
 		);
 		ob_end_clean();
 		echo json_encode(array("Result"=>$result));
 		mysqli_close($conn);
 	}
?>