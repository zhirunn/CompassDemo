<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$email = $_POST['Email'];
		$fullname = $_POST['FullName'];
        $phonenumber = $_POST['PhoneNumber'];
        $address = $_POST['Address'];
        $employment = $_POST['Employment'];
        $jobtitle = $_POST['JobTitle'];
        $photodocuments = $_POST['PhotoDocuments'];
		require_once "connect.php";

		$sql = "INSERT INTO AccountDetails (UserID, FullName,PhoneNumber,Address,Employment,JobTitle,PhotoDocuments) VALUES ('$userid','$fullname','$phonenumber','$address','$employment','$jobtitle','$photodocuments')";

		if(mysqli_query($conn, $sql)) {
			ob_clean();
			echo "Successfully Uploaded";
		} else {
			ob_clean();
			echo "Could not upload information";
		}
	} else {
		echo "Unexpected Error.";
	}
?>