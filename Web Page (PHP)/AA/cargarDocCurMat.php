<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$id="";
$idmate="";
$codc="";
if(isset($_POST['Idd'])){
$id = $_POST['Idd'];
}
if(isset($_POST['Codmate'])){
$idmate = $_POST['Codmate'];
}
if(isset($_POST['Curso'])){
$codc = $_POST['Curso'];
}
//$id = 55555;
//$idmate = 1;
if($id!=""){
 $query_search = "select nomDoc from docente_as where idDoc = '".$id."'";
 $resul=mysql_query($query_search);
$cons=mysql_fetch_array($resul);
	mysql_free_result($resul);
  echo  $cons[0]." "; 

}

if($idmate!=""){
 $query_search = "select * from asignatura_as";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1]; 
}
mysql_free_result($resul);
}

if($codc!=""){
 $query_search = "select * from curso_as";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1]; 
}
mysql_free_result($resul);
}


?>