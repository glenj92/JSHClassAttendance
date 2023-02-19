<?php
header( 'Content-Type: text/html;charset=utf-8' );
include "conexion.php";

$idd="";


if(isset($_POST['Idd'])){
$idd=$_POST['Idd'];
}
//$idd=1118840809;

if($idd!="" ){
// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
// codGrupo  

 $resul=mysql_query("select * from estudiante_as where idEstu='".$idd."'");
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]; 
}
mysql_free_result($resul);
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