<?php
 
/*
 * Following code will list all the products
 */
 
 
include("db_connect.php");
 
 
/*******************************************************************

		************** Template Query Script **************
	

*******************************************************************/ 


/*******************************************************************

		************** Variables **************

*******************************************************************/ 


$query = "SELECT * FROM users";
$response = array();

/*******************************************************************/



$result = mysqli_query($db, $query) or die(mysql_error());


// check for empty result
if (mysqli_num_rows($result) > 0) {

    $response["username"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
	$item= array();
    	foreach ($row as $key => $value) {
        	$item[$key] = $value;
        }

        array_push($response["username"], $item);
        
    }
    
    
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>