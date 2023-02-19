<?php
session_start();
echo"BIENVENIDO ".$_SESSION['usuario'];
include"conexion.php";

$cod=$_GET['codigo'];


$consul="delete from curso_as where codCurso='".$cod."'";

mysql_query($consul);
header ("Location:gestioncurso.php"); 
?>