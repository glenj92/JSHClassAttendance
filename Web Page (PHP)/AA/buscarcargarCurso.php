<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$idcur="";
$nomcur ="";
if(isset($_POST['Idc'])){
$idcur = $_POST['Idc'];
}
if(isset($_POST['Nom'])){
$nomcur = $_POST['Nom'];
}

if($idcur!=""){
 $query_search = "select * from curso_as where codCurso='".$idcur."'";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1].","; 
}
mysql_free_result($resul);
}
if($nomcur!=""){
 $query_search = "select * from curso_as where nomCurso like '%".$nomcur."%'";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1].","; 
}
mysql_free_result($resul);
}

?>