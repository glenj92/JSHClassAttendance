<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$fe="";
$cod2="";
$asi="";
$fecha="";
$gru="";

 
if(isset($_POST['fec'])){
$fe=$_POST['fec'];
}
if(isset($_POST['estado'])){
$asi = $_POST['estado'];
}

if(isset($_GET['cod2'])){
$cod2=$_GET['cod2'];
}
if(isset($_GET['gru'])){
$gru=$_GET['gru'];
}
if(isset($_GET['fecha'])){
$fecha=$_GET['fecha'];
}

if($asi!="" && $fe!="" && $cod2!="" && $fecha!="" && $gru!=""){
	
	
				   $consulta="update asistencia_as set fechaAsis='".$fe."', excusa='".$asi."' where idEstu='".$cod2."' and codGrupo='".$gru."' and fechaAsis='".$fecha."'";
				$resultado=mysql_query($consulta) or die("error");
				header ("Location:gestionasis.php");
				
	
}else{
header ("Location:gestionasis.php");	
}
?>