<?php
session_start();
echo"BIENVENIDO ".$_SESSION['usuario'];
include"conexion.php";

$user=$_GET['usuario'];


$consul="delete from estudiante_as where idEstu='".$user."'";

mysql_query($consul);
header ("Location:gestionestu.php"); 
?>