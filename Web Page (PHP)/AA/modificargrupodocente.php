<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";

$id="";
$idg="";
$codc="";
$codm="";
$ano="";
$per="";
$jor="";
if(isset($_POST['CodCurso'])){
$codc=$_POST['CodCurso'];
}
if(isset($_POST['IdGru'])){
$idg=$_POST['IdGru'];
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

if($idd!="" && $jor!="" && $per!="" && $ano!="" && $codm!="" && $codc!="" && $idg!=""){
	
	if($ano==date('Y')){
 
if($jor=="MANANA"){
$consulta="update grupo_as set codGrupo='M".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$idg."'";
}
 
if($jor=="TARDE"){
$consulta="update grupo_as set codGrupo='T".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$idg."'";
}
 
if($jor=="NOCHE"){
$consulta="update grupo_as set codGrupo='N".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$idg."'";
}
 if($jor=="UNICA"){
$consulta="update grupo_as set codGrupo='U".$per.$ano.$codc.$codm."', codCurso='".$codc."', codMate='".$codm."', ano='".$ano."', periodo='".$per."' , jornada='".$jor."', idDoc='".$idd."' where codGrupo ='".$idg."'";
}
 
$resultado=mysql_query($consulta) or die("error");
$numfilas=mysql_affected_rows();

echo $numfilas;

}else{
	echo"nofecha";
	}

}
?>