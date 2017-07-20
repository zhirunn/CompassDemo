<?php
	if($_SERVER['REQUEST_METHOD']=='GET') {
        require_once "connect.php";
		$sql = "SELECT * FROM Users";
        $res = mysqli_fetch_array(mysqli_query($conn, $sql));
        $result = array();
        array_push($result, array(
            "UserID"=>$res['UserID'],
            "Email" =>$res['Email'],
            "BorrowerType" =>$res['BorrowerType'],
            "LenderType" =>$res['LenderType'],
            )
         );
        ob_clean();
        echo json_encode(array("Result"=>$result));
        mysqli_close($conn);
        }
?>