<?php
session_start();
echo"BIENVENIDO ".$_SESSION['usuario'];
include"conexion.php";

$user=$_GET['usuario'];


$consul="delete from docente_as where idDoc='".$user."'";

mysql_query($consul);
header ("Location:gestiondocente.php"); 
?>