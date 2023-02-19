<?php

include "conexion.php";

$id=$_POST['Id'];
$nom = $_POST['Nom'];


// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
 $resul=mysql_query("insert into estudiante_as values('".$id."','".$nom."')");

if($resul)
{
echo "Se insertaron ".mysql_affected_rows()." registros";
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