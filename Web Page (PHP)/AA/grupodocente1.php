<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$id="";
$idmate="";
$ano="";
if(isset($_POST['Idd'])){
$id = $_POST['Idd'];
}
if(isset($_POST['Codmate'])){
$idmate = $_POST['Codmate'];
}
if(isset($_POST['Ano'])){
$ano = $_POST['Ano'];
}
//$id = 55555;
//$idmate = 1;
if($id!=""&&$idmate!=""&&$ano!=""){
 $query_search = "select codGrupo from grupo_as where idDoc = '".$id."' and codMate= '".$idmate."' and ano='".$ano."'";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]." "; 
}
mysql_free_result($resul);
}


?>