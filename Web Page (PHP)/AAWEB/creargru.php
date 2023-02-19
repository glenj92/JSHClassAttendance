<?php
session_start();
header( 'Content-Type: text/html;charset=utf-8' );
include "conexion.php";
if($_SESSION['perfilUser']==1){

$idd="";
$codc="";
$codm="";
$ano="";
$per="";
$jor="";
if(isset($_POST['cur'])){
$codc=$_POST['cur'];
}
if(isset($_POST['mate'])){
$codm = $_POST['mate'];
}
if(isset($_POST['ano'])){
$ano=$_POST['ano'];
}
if(isset($_POST['per'])){
$per=$_POST['per'];
}
if(isset($_POST['jor'])){
$jor=$_POST['jor'];
}
if(isset($_POST['doc'])){
$idd=$_POST['doc'];
}
if($idd!="" && $jor!="" && $per!="" && $ano!="" && $codm!="" && $codc!=""){
	
// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
// codGrupo  
if($jor=="MANANA"){
 $resul=mysql_query("insert into grupo_as values('M".$per.$ano.$codc.$codm."','".$codc."','".$codm."','".$ano."','".$per."','".$jor."','".$idd."')") or die("Grupo ya existe");
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
	if(isset($_SESSION['scodc'])){
unset($_SESSION['scodc']);
}

	if(isset($_SESSION['scodm'])){
unset($_SESSION['scodm']);
}
	if(isset($_SESSION['siddoc'])){
unset($_SESSION['siddoc']);
}
header("Location:gestiongru.php"); 
}


}
}else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
	
	
$idd="";
$codc="";
$codm="";
$ano="";
$per="";
$jor="";
if(isset($_POST['cur'])){
$codc=$_POST['cur'];
}
if(isset($_POST['mate'])){
$codm = $_POST['mate'];
}
if(isset($_POST['ano'])){
$ano=$_POST['ano'];
}
if(isset($_POST['per'])){
$per=$_POST['per'];
}
if(isset($_POST['jor'])){
$jor=$_POST['jor'];
}
if(isset($_GET['doc'])){
$idd=$_GET['doc'];
}
if($idd!="" && $jor!="" && $per!="" && $ano!="" && $codm!="" && $codc!=""){
	
// $query_search = "select * from tbl_user where username = '".$useremail."' AND password = '".$password. "'";
// codGrupo  
if($jor=="MANANA"){
 $resul=mysql_query("insert into grupo_as values('M".$per.$ano.$codc.$codm."','".$codc."','".$codm."','".$ano."','".$per."','".$jor."','".$idd."')") or die("Grupo ya existe");
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
	if(isset($_SESSION['scodc'])){
unset($_SESSION['scodc']);
}

	if(isset($_SESSION['scodm'])){
unset($_SESSION['scodm']);
}
	if(isset($_SESSION['siddoc'])){
unset($_SESSION['siddoc']);
}
header("Location:gestiongru.php"); 
}
}

}else{
echo"Acceso Restringido para este usurio";	

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