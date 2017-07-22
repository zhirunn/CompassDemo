<?php
	if($_SERVER['REQUEST_METHOD']=='GET') {
        require_once "connect.php";
		$sql = "SELECT * FROM Users";
        $res = mysqli_query($conn, $sql);
        $result = array();
while ($row = mysqli_fetch_array($res)){
        array_push($result, array(
            "SearchUserID"=>$row['UserID'],
            "SearchEmail" =>$row['Email'],
            "BorrowerType" =>$row['BorrowerType'],
            "LenderType" =>$row['LenderType'],
            )
         );
}
        ob_clean();
        echo json_encode(array("Result"=>$result));
        mysqli_close($conn);
        }
?>