<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";

$idg="";

if(isset($_POST['IdGru'])){
$idg=$_POST['IdGru'];
}

if($idg!=""){
 
$consulta=("delete from grupo_as where codGrupo='".$idg."'");
$resultado=mysql_query($consulta);
$numfilas=mysql_affected_rows();
echo "si";
}
?>