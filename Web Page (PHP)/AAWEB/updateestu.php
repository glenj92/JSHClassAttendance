<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$cod="";
$cod2="";
$nom="";
$ape ="";
 
if(isset($_POST['codigo'])){
$cod=$_POST['codigo'];
}
if(isset($_POST['nombre'])){
$nom = $_POST['nombre'];
}
if(isset($_POST['apellido'])){
$ape = $_POST['apellido'];
}
if(isset($_GET['cod2'])){
$cod2=$_GET['cod2'];
}

if($cod!="" && $nom!="" && $cod2!=""&&$ape!=""){
	
	
				   $consulta="update estudiante_as set idEstu='".$cod."', nomEstu='".$nom."', apellEstu='".$ape."' where idEstu='".$cod2."'";
				$resultado=mysql_query($consulta) or die("error");
				header ("Location:gestionestu.php");
				
	
}else{
header ("Location:gestionestu.php");	
}
?>