<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$userid = $_POST['UserID'];
		require_once "connect.php";
		$sql = "SELECT * FROM Users WHERE UserID = '$userid'";
		$res = mysqli_fetch_array(mysqli_query($conn, $sql));
		$result = array();
	 	array_push($result, array(
 			"Money"=>$res['Money']
 			)
 		);
 		ob_clean();
 		echo json_encode(array("Result"=>$result));
 		mysqli_close($conn);
	}
?>