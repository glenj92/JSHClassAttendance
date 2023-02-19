<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
/*
$idmate="";

if(isset($_POST['Codmate'])){
$idmate = $_POST['Codmate'];
}
*/
//$id = 55555;
//$idmate = 1;


//if($idmate!=""){
	$pro="";

if(isset($_POST['programa'])){
$pro = $_POST['programa'];
}
 $query_search = "select * from asignatura_as where codCurso='".$pro."'";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1].","; 
}
mysql_free_result($resul);
//}

?>