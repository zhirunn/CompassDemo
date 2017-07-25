<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$image = $_POST['image'];
	 	require_once "connect.php";
	 	$sql = "SELECT id FROM Test ORDER BY id ASC";
	 	$res = mysqli_query($conn, $sql);
		$id = 0;
		while($row = mysqli_fetch_array($res)) {
			$id = $row['id'];
		}
		//$path = "Images/$id.png";
		$path = "Images/$id.jpg";
		$actualpath = "https://greatnorthcap.000webhostapp.com/$path";
		$sql = "INSERT INTO Test (Image) VALUES ('$actualpath')";
		if(mysqli_query($conn, $sql)) {
			file_put_contents($path, base64_decode($image));
			echo "Successfully Uploaded";
		}
		mysqli_close($conn);
	}
?>