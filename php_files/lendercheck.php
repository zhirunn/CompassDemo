<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
require_once "connect.php";
$id = $_POST["UserID"];
$sql = "SELECT LenderType FROM Users WHERE UserID = $id";
$res = mysqli_query($conn,$sql);
$row = mysqli_fetch_array($res);
$admincheck = $row["LenderType"];
ob_clean();
echo $admincheck;
mysqli_close($conn);
}
?>
