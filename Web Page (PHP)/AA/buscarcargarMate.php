<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";

$idmate="";
$nommate ="";
if(isset($_POST['Idm'])){
$idmate = $_POST['Idm'];
}
if(isset($_POST['Nom'])){
$nommate = $_POST['Nom'];
}
	$pro="";

if(isset($_POST['programa'])){
$pro = $_POST['programa'];
}

//$id = 55555;
//$idmate = 1;


if($idmate!=""){
 $query_search = "select * from asignatura_as where codMate='".$idmate."' and codCurso='".$pro."'";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1].","; 
}
mysql_free_result($resul);
}

if($nommate!=""){
 $query_search = "select * from asignatura_as where nomMate like '%".$nommate."%' and codCurso='".$pro."'";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1].","; 
}
mysql_free_result($resul);
}

?>