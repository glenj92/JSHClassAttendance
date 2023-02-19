<?php

include "conexion.php";

$gru="";
$fe = "";
$gru=$_POST['Grupo'];
$fe = $_POST['Fecha'];

// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
if($gru!="" && $fe!=""){
	if($fe==date('Y-m-d')){
 $resul=mysql_query("insert into clase_as values('".$gru."','".$fe."')") or die("Esta clase ya fue dada");
if($resul)
{
	$num=mysql_affected_rows();

	echo "La clase se dio por vista!!\nFalla para los estudiantes del grupo ".$gru;

}
}else{
	echo"nofecha";
	}
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