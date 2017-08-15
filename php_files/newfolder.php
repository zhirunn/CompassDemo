<?php
	if($_SERVER['REQUEST_METHOD']=='GET') {
		require_once "connect.php";
		$sql = "SELECT * FROM `Loans`";
		$res = mysqli_query($conn,$sql);
		$id = 0;
		while ($row = mysqli_fetch_array($res)) {
			$id = $row["LoanID"];
		}
		$path = "Images/$id/";
		mkdir("../$path");
		mysqli_close($conn);
	}
?>