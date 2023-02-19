<?php

include "conexion.php";


$gru="T2201421";
$fec="2014-03-04";


// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";

$respuesta=mysql_query("select * from clase_as where codGrupo='$gru' and fechaClas='$fec'") or die("error");
$respuesta1=mysql_query("select * from asistencia_as where codGrupo='$gru' and fechaAsis='$fec'") or die("error");
$dato=mysql_fetch_array($respuesta);
mysql_free_result($respuesta);
$dato1=mysql_fetch_array($respuesta1);
mysql_free_result($respuesta1);
	if($dato!=""&&$dato1==""){
	echo "falla";
	}

 /*$query_exec = mysql_query($query_search) or die(mysql_error());
 $rows = mysql_num_rows($query_exec);
 
  if($rows --> 0) { 
  echo "Y"; 
  }
else  {
	echo "N";
 }



$consul="select * from user where nombre='".$nom."' and id='".$id."'";
$resul=mysql_query($consul)or die (mysql_error());
if(mysql_num_rows($resul)>0){
echo 1;
}
*/

/*
$dato = $_POST['data'];
$info = $_POST['info'];

echo $dato."\n".$info;

//echo"que tal man";
*/

?>