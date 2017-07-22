<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
	    $id = $POST['UserID'];
        require_once "connect.php";
		$sql = "SELECT * FROM Loans WHERE BorrowerID = '$id' OR LenderID = '$id'";
        $res = mysqli_query($conn, $sql);
        $result = array();
while ($row = mysqli_fetch_array($res)){
        array_push($result, array(
            "LoanID"=>$row['LoanID'],
            "AmountApproved" =>$row['AmountApproved'],
            "APR" =>$row['APR'],
            "TermDate" =>$row['TermDate'],
            "PaymentDue" =>$row['PaymentDue'],
            "Principal" =>$row['Principal'],
            "Interest" =>$row['Interest'],
            "Status" =>$row['Status'],
            "BorrowerID" =>$row['BorrowerID'],
            "LenderID" =>$row['LenderID'],
            "ApprovalDate" =>$row['ApprovalDate']
            )
         );
}
        ob_clean();
        echo json_encode(array("Result"=>$result));
        mysqli_close($conn);
        }
?>