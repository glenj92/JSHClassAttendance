<?php
session_start();
echo"BIENVENIDO ".$_SESSION['usuario'];
include"conexion.php";

$cod=$_GET['codigo'];


$consul="delete from asignatura_as where codMate='".$cod."'";

mysql_query($consul);
header ("Location:gestionmateria.php"); 
?>