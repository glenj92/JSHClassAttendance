<?php
session_start();
//$traslado=$_SESSION['traslado'];
//obtener las variables
//$pass=""&&$user!=""&&$nom!=""&&$ape!=""
$cod=$_POST['codigo'];

$nom=$_POST['nombre'];

//if($traslado!="formularioregistrar.php"){


include "conexion.php";
if($cod!=""&&$nom!=""){
$resul=mysql_query("insert into curso_as values('".$cod."','".$nom."')");

header ("Location:gestioncurso.php"); 
}else{
	header ("Location:gestioncurso.php"); 
}




?>