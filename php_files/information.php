<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$fullname = $_POST['FullName'];
        $phonenumber = $_POST['PhoneNumber'];
        $address = $_POST['Address'];
        $employment = $_POST['Employment'];
        $jobtitle = $_POST['JobTitle'];
        $userid = $_POST['UserID'];
		require_once "connect.php";
		$sql =
		"INSERT INTO AccountDetails (UserID, FullName,PhoneNumber,Address,Employment,JobTitle)
		VALUES ('$userid','$fullname','$phonenumber','$address','$employment','$jobtitle')
		ON DUPLICATE KEY UPDATE
		FullName=VALUES(FullName),
		PhoneNumber=VALUES(PhoneNumber),
		Address=VALUES(Address),
		Employment=VALUES(Employment),
		JobTitle=VALUES(JobTitle)";
		if(mysqli_query($conn, $sql)) {
			ob_clean();
			echo "Successfully Uploaded";
		} else {
			ob_clean();
			echo "Could not upload information";
		}
	}
?>