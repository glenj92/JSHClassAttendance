<?php
session_start();
//$traslado=$_SESSION['traslado'];
//obtener las variables
//$pass=""&&$user!=""&&$nom!=""&&$ape!=""
$user=$_POST['usuario'];
$userd=$_POST['usuariod'];
$pass=$_POST['contrasena'];
$nom=$_POST['nombre'];
$ape=$_POST['apellido'];
//if($traslado!="formularioregistrar.php"){
/*$categoria=$_POST['categoria'];
if($categoria=="adminitrador"){
	$perfil=1;
}
if($categoria=="docenteu") {
	$perfil=2;
}
if($categoria=="docentec"){
	$perfil=3;
}
if($categoria=="escritor"){
	$perfil=4;
}
*/
include "conexion.php";
if($pass==""&&$user!=""&&$nom!=""&&$ape!=""){
$resul=mysql_query("insert into docente_as values('".$user."','".$nom."','".$ape."')");

header ("Location:formcrearpass.php?usuario=".$user.""); 
}
if($pass==""&&$userd!=""&&$nom!=""&&$ape!=""){
$resul=mysql_query("insert into docente_as values('".$userd."','".$nom."','".$ape."')");

header ("Location:formcrearpass.php?usuariod=".$userd.""); 
}
/*else if($pass!=""&&$user!=""&&$nom!=""&&$ape!=""&&$perfil!=""){
	$resul=mysql_query("insert into docente_as values('".$user."','".$nom."','".$ape."')");
	if($resul){
	$resul1=mysql_query("insert into acceso_as values('".$user."','".$pass."','".$perfil."')");
	}
header ("Location:gestionuser.php"); 
}else{
	header ("Location:gestionuser.php"); 
}*/




?>