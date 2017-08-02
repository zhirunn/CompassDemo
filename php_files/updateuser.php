<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
require_once "connect.php";
$id = $_POST["UserID"];
$borrower = $_POST["BorrowerType"];
$lender = $_POST["LenderType"];
$sql = "UPDATE Users SET BorrowerType = '$borrower',LenderType='$lender' WHERE UserID = '$id'";
if (mysqli_query($conn,$sql))
{
ob_clean();
echo "Successfully Updated";
}
mysqli_close($conn);
}

?>