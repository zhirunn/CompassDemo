<?php
  if($_SERVER['REQUEST_METHOD']=='POST') {

  $sql = "SELECT * FROM Loans INNER JOIN Users ON Loans.BorrowerID = Users.UserID
  WHERE Users.BorrowerType = $lenderID";
  }

  ?>