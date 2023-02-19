<?php
header( 'Content-Type: text/html;charset=utf-8' );
include "conexion.php";

$gr="";


if(isset($_POST['Grupo'])){
$gr=$_POST['Grupo'];
}
//$gr="t1201414";

if($gr!="" ){
// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
// codGrupo  

 $resul=mysql_query("select idEstu from asistencia_as where codGrupo='".$gr."'");
  while($cons=mysql_fetch_array($resul)){
	  $resul2=mysql_query("select * from estudiante_as where idEstu='".$cons[0]."'");
  		while($cons2=mysql_fetch_array($resul2)){
	
 			 echo  $cons2[0].",".$cons2[1].",".$cons2[2].","; 
		}
		mysql_free_result($resul2);
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