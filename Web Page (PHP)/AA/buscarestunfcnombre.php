<?php
header( 'Content-Type: text/html;charset=utf-8' );
include "conexion.php";

$nom="";


if(isset($_POST['Nom'])){
$nom=$_POST['Nom'];
}
//$idd=920376;

if($nom!=""){
// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
// codGrupo
	if($nom=="all"){
		$resul=mysql_query("select * from estudiante_as");
		  while($cons=mysql_fetch_array($resul)){
			
		  echo  $cons[0].",".$cons[1].",".$cons[2].","; 
		  }
	}else{

 $resul=mysql_query("select * from estudiante_as where nomEstu like '%".$nom."%' or apellEstu like '%".$nom."%'");
		  while($cons=mysql_fetch_array($resul)){
			
		  echo  $cons[0].",".$cons[1].",".$cons[2].","; 
		  }
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