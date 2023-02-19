<?php
session_start();
echo"BIENVENIDO ".$_SESSION['usuario'];
include"conexion.php";


if(isset($_GET['id'])){
$cod=$_GET['id'];
}

if(isset($_GET['gru'])){
$gru = $_GET['gru'];
}
if(isset($_GET['fec'])){
$fe = $_GET['fec'];
}
if(isset($_GET['asi'])){
$asi = $_GET['asi'];
}


$consul="delete from asistencia_as where idEstu='".$cod."' and codGrupo='".$gru."' and fechaAsis='".$fe."' and excusa='".$asi."'";

mysql_query($consul);
header ("Location:gestionasis.php"); 
?>