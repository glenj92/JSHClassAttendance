<?php
session_start();
echo"BIENVENIDO ".$_SESSION['usuario'];
include"conexion.php";

if($_SESSION['perfilUser']==1||$_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){

$user=$_GET['gru'];


$consul="delete from grupo_as where codGrupo='".$user."'";

mysql_query($consul);
	if(isset($_SESSION['scodc'])){
unset($_SESSION['scodc']);
}

	if(isset($_SESSION['scodm'])){
unset($_SESSION['scodm']);
}
	if(isset($_SESSION['siddoc'])){
unset($_SESSION['siddoc']);
}
header ("Location:gestiongru.php"); 

}else{
echo"Acceso Restringido para este usurio";	
}
?>