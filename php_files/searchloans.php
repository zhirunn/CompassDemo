<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$lender = $_POST['LenderType'];
		$sql ="";
		require_once "connect.php";
		if($lender == '3')
		{
		$sql = "SELECT * FROM Loans";
		}
		else
		{
		$sql = "SELECT * FROM Loans INNER JOIN Users ON
                Loans.BorrowerID = Users.UserID
                WHERE Users.BorrowerType = '$lender'
					AND Status ='Pending'
					ORDER BY Loans.LoanID ";
		}
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
                    "Grade" =>$row['Grade'],
                    "Status" =>$row['Status'],
                    "BorrowerID" =>$row['BorrowerID'],
                    "LenderID" =>$row['LenderID'],
                    "DateLastModified" =>$row['DateLastModified'],
                    )
                 );
}
        ob_clean();
        echo json_encode(array("Result"=>$result));
        mysqli_close($conn);
	}
?>