<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$image = $_POST['Image'];
		$type = $_POST['Type'];
		$loanid = $_POST['LoanID'];
	 	require_once "connect.php";
		$path = "Images/$loanid/$type.jpg";
		$actualpath = "https://greatnorthcap.000webhostapp.com/$path";
		$sql = "UPDATE LoanCredentials SET $type = '$actualpath' WHERE LoanID = '$loanid'";
		if(mysqli_query($conn, $sql)) {
			ob_clean();
			file_put_contents("../$path", base64_decode($image));
			echo "Successfully Uploaded";
		} else {
			ob_clean();
			echo "Image could not be uploaded.";
		}
		mysqli_close($conn);
	}
?>