<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$image = $_POST['image'];
	 	require_once "connect.php";
	 	$sql = "SELECT LoanID FROM Loans ORDER BY LoanID ASC";
	 	$res = mysqli_query($conn, $sql);
		$id = 0;
		while($row = mysqli_fetch_array($res)) {
			$id = $row['id'];
		}
		$path = "uploads/$id.png";
		$actualpath = "http://simplifiedcoding.16mb.com/PhotoUploadWithText/$path";
		$sql = "INSERT INTO Loans (photo) VALUES ('$actualpath')";
		if(mysqli_query($conn, $sql)) {
			file_put_contents($path, base64_decode($image));
			echo "Successfully Uploaded";
		}
		mysqli_close($conn);
	}
?>