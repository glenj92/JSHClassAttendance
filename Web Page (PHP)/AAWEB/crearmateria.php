<?php
session_start();
//$traslado=$_SESSION['traslado'];
//obtener las variables
//$pass=""&&$user!=""&&$nom!=""&&$ape!=""

$cod=$_POST['codigo'];

$nom=$_POST['nombre'];
$pro=$_POST['progra'];

//if($traslado!="formularioregistrar.php"){


include "conexion.php";
if($cod!=""&&$nom!=""){
$resul=mysql_query("insert into asignatura_as values('".$cod."','".$nom."','".$pro."')");

header ("Location:gestionmateria.php"); 
}else{
	header ("Location:gestionmateria.php"); 
}




?>