<?php

include "conexion.php";

$id="";
$nom="";
$gru="";
$fec="";
$ape="";
$exc="";
if(isset($_POST['Id'])){
$id=$_POST['Id'];
}
if(isset($_POST['Nom'])){
$nom = $_POST['Nom'];
}
if(isset($_POST['Ape'])){
$ape = $_POST['Ape'];
}
if(isset($_POST['Grupo'])){
$gru=$_POST['Grupo'];
}
if(isset($_POST['Fecha'])){
$fec=$_POST['Fecha'];
}
if(isset($_POST['Excusa'])){
$exc=$_POST['Excusa'];
}
if($id!="" && $nom!="" && $gru!="" && $fec!="" && $ape!="" && $exc!=""){
	
	if($fec==date('Y-m-d')){
// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";

$respuesta=mysql_query("select * from clase_as where codGrupo='".$gru."' and fechaClas='".$fec."'") or die("error");
$respuesta1=mysql_query("select * from asistencia_as where codGrupo='".$gru."' and fechaAsis='".$fec."'") or die("error");

	$dato=mysql_fetch_array($respuesta);
	mysql_free_result($respuesta);
$dato1=mysql_fetch_array($respuesta1);
mysql_free_result($respuesta1);
	if($dato!=""&&$dato1==""){
	echo "falla";
	}else{
	
 		$resul=mysql_query("insert into estudiante_as values('".$id."','".$nom."','".$ape."')");
 		$resul1=mysql_query("insert into asistencia_as values('".$id."','".$gru."','".$fec."','".$exc."')") or die("error");
 		$resul2=mysql_query("insert into clase_as values('".$gru."','".$fec."')");

			if($resul1)
			{
			echo "exito";
			}



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