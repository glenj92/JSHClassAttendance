<?php
session_start();
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
if($_SESSION['perfilUser']==1||$_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
 
$idd="";
$codc="";
$cod2="";
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
if(isset($_GET['cod2'])){
$cod2=$_GET['cod2'];
}
if(isset($_GET['idd'])){
if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
$idd=$_GET['idd'];
}
}

if($idd!="" && $jor!="" && $per!="" && $ano!="" && $codm!="" && $codc!="" && $cod2!=""){
	

if($jor=="MANANA"){
$consulta="update grupo_as set codGrupo='M".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$cod2."'";
}
 
if($jor=="TARDE"){
$consulta="update grupo_as set codGrupo='T".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$cod2."'";
}
 
if($jor=="NOCHE"){
$consulta="update grupo_as set codGrupo='N".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$cod2."'";
}
 if($jor=="UNICA"){
$consulta="update grupo_as set codGrupo='U".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$cod2."'";
}
 
 
$resultado=mysql_query($consulta) or die("error");
				
				
					
					
					header ("Location:gestiongru.php");
			
	
	
}else{
header ("Location:gestiongru.php");	
}
}else{
echo"Acceso Restringido para este usurio";	
}
?>