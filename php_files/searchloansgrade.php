<?php
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$lender = $_POST['LenderType'];		
		$grade = $_POST['Grade'];
		$sql ="";
		require_once "connect.php";
		if($grade == 'All')
		{
		$sql = "SELECT * FROM Loans INNER JOIN Users ON
      Loans.BorrowerID = Users.UserID
      WHERE BorrowerType = '$lender'
      AND Status ='Pending'
		ORDER BY Loans.LoanID ";
		} else if($grade == 'A+B')
		{
		$sql = "SELECT * FROM Loans WHERE Grade='A' AND Status ='Pending' OR Grade='B' AND Status ='Pending' ORDER BY Loans.LoanID";
		} else if($grade == 'B+C')
		{
		$sql = "SELECT * FROM Loans WHERE Grade='B' AND Status ='Pending' OR Grade='C' AND Status ='Pending' ORDER BY Loans.LoanID";
		} else {
			$sql = "SELECT * FROM Loans WHERE Grade = '$grade' AND Status ='Pending' ORDER BY Loans.LoanID ";		
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