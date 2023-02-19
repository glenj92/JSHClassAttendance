<?php
header( 'Content-Type: text/html;charset=utf-8' );
include "conexion.php";

$idd="123456";
$codc="4765";
$codm="888";
$ano="2014";
$per="1";
$jor="MANANA";
if(isset($_POST['CodCurso'])){
$codc=$_POST['CodCurso'];
}
if(isset($_POST['CodMate'])){
$codm = $_POST['CodMate'];
}
if(isset($_POST['Ano'])){
$ano=$_POST['Ano'];
}
if(isset($_POST['Periodo'])){
$per=$_POST['Periodo'];
}
if(isset($_POST['Jornada'])){
$jor=$_POST['Jornada'];
}
if(isset($_POST['IdDoc'])){
$idd=$_POST['IdDoc'];
}
if($idd!="" && $jor!="" && $per!="" && $ano!="" && $codm!="" && $codc!=""){
	if($ano==date('Y')){
// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
// codGrupo  
if($jor=="MANANA"){
 $resul=mysql_query("insert into grupo_as values('M".$per.$ano.$codc.$codm."','".$codc."','".$codm."','".$ano."','".$per."','".$jor."','".$idd."')") or die(mysql_error());
}
if($jor=="TARDE"){
 $resul=mysql_query("insert into grupo_as values('T".$per.$ano.$codc.$codm."','".$codc."','".$codm."','".$ano."','".$per."','".$jor."','".$idd."')")or die("Grupo ya existe");
}
if($jor=="NOCHE"){
 $resul=mysql_query("insert into grupo_as values('N".$per.$ano.$codc.$codm."','".$codc."','".$codm."','".$ano."','".$per."','".$jor."','".$idd."')")or die("Grupo ya existe");
}
if($jor=="UNICA"){
 $resul=mysql_query("insert into grupo_as values('U".$per.$ano.$codc.$codm."','".$codc."','".$codm."','".$ano."','".$per."','".$jor."','".$idd."')")or die("Grupo ya existe");
}
if($resul)
{
echo "Se guardo ".mysql_affected_rows()." nuevo grupo";
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