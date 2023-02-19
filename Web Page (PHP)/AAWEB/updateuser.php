<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";

$per="";
$pass="";
if(isset($_POST['usuario'])){
$user=$_POST['usuario'];
}
if(isset($_POST['contrasena'])){
$pass=$_POST['contrasena'];
}
if(isset($_POST['nombre'])){
$nom = $_POST['nombre'];
}
if(isset($_POST['apellido'])){
$ape=$_POST['apellido'];
}
if(isset($_POST['perfil'])){
$per=$_POST['perfil'];
}
if(isset($_GET['user2'])){
$user2=$_GET['user2'];
}

if($user!="" && $nom!="" && $ape!="" &&$user2!=""){

$consulta="update docente_as set idDoc='".$user."', nomDoc='".$nom."', apeDoc='".$ape."' where idDoc='".$user2."'";

$resultado=mysql_query($consulta) or die("error");

header ("Location:gestionuser.php");


}else if($user!="" && $pass!="" && $nom!="" && $ape!="" && $per!=""){

$consulta="update docente_as set idDoc='".$user."', nomDoc='".$nom."', apeDoc='".$ape."' where idDoc='".$user2."'";
$resultado=mysql_query($consulta);

$consulta1="update acceso_as set idDoc='".$user."', pword='".$pass."', perfil='".$per."' where idDoc='".$user2."'";

$resultado1=mysql_query($consulta1) or die("error");
header ("Location:gestionuser.php");
}else{
header ("Location:gestionuser.php");	
}
?>