<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$id="";

if(isset($_POST['IdG'])){
$id = $_POST['IdG'];
}

//$id = 55555;
//$idmate = 1;
if($id!=""){
 $query_search = "select * from grupo_as where codGrupo = '".$id."'";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0].", ".$cons[1].", ".$cons[2].", ".$cons[3].", ".$cons[4].", ".$cons[5]; 
}
mysql_free_result($resul);
}


?>