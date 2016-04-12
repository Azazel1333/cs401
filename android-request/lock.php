<?php



/******************************************************************************************************************************************
				// This script is for unlocking the door for 10 seconds then locking it again
*******************************************************************************************************************************************/

	include("db_connect.php");
	
	error_reporting(E_ERROR | E_PARSE); // Error reporting is supressed due to the fact that phpseclib and my version of PHP has an encryption warning. This is a known bug with my version of PHP and may not be required for others
	set_include_path(get_include_path() . PATH_SEPARATOR . 'phpseclib');
	include('phpseclib/Net/SSH2.php');
	include('phpseclib/Crypt/RSA.php');
	include('functions.php');
	 
	////// ******* Define Variables ************ //////
	
	define('TIMEOUT', "10"); //must be defined globally within php when using sleep command

	
	

	$ip = "64.201.203.58";
	$port = "222";
	$username = "pi";
	$password = "raspberry";
	
	
	// connect to PI - take in to consideration of delay while connecting //
	
	
	echo "Connecting... <BR>";
	$ssh = new Net_SSH2($ip, $port);
	if (!$ssh->login($username, $password)) {
		//echo $ssh->getLog();
	    	exit('Login Failed');
	}


	// simply execute the script and echo the output for app to capture //
	
	//echo $ssh->exec('python python/unlock.py');
	
	//echo "Unlocked <BR>";

	//sleep(TIMEOUT);
	
	echo $ssh->exec('python python/lock.py');

	echo "Locked <BR>";

?>