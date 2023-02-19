<?php
session_start();
//$traslado=$_SESSION['traslado'];
//obtener las variables
//$pass=""&&$user!=""&&$nom!=""&&$ape!=""
$user=$_POST['usuario'];

$nom=$_POST['nombre'];
$ape=$_POST['apellido'];
//if($traslado!="formularioregistrar.php"){

include "conexion.php";
if($user!=""&&$nom!=""&&$ape!=""){
$resul=mysql_query("insert into estudiante_as values('".$user."','".$nom."','".$ape."')");

header ("Location:gestionestu.php"); 
}else{
	header ("Location:gestionestu.php"); 
}




?>