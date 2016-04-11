<?php


function packet_handler($str)
{
    echo $str;
    flush();
    ob_flush();
}





?>