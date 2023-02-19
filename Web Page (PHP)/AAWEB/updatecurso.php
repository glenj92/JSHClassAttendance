<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$cod="";
$cod2="";
$nom="";
 
if(isset($_POST['codigo'])){
$cod=$_POST['codigo'];
}
if(isset($_POST['nombre'])){
$nom = $_POST['nombre'];
}
if(isset($_GET['cod2'])){
$cod2=$_GET['cod2'];
}

if($cod!="" && $nom!="" && $cod2!=""){
	
	$consul="select * from grupo_as where codCurso='".$cod2."'";
	$resul=mysql_query($consul) or die("error");
	$rows = mysql_num_rows($resul);
	
		  if($rows --> 0==NULL) { 
				   $consulta="update curso_as set codCurso='".$cod."', nomCurso='".$nom."' where codCurso='".$cod2."'";
				$resultado=mysql_query($consulta) or die("error");
				header ("Location:gestioncurso.php");
				  }else{
					  
			while($c=mysql_fetch_array($resul)){
				
				
				
				$consulta="update curso_as set codCurso='".$cod."', nomCurso='".$nom."' where codCurso='".$cod2."'";
				$resultado=mysql_query($consulta) or die("error");
				
if($c[5]=="MANANA"){
$consulta="update grupo_as set codGrupo='M".$c[4].$c[3].$cod.$c[2]."', codCurso='".$cod."', codMate='".$c[2]."', ano='".$c[3]."', periodo='".$c[4]."' , jornada='".$c[5]."', idDoc='".$c[6]."' where codGrupo ='".$c[0]."'";
}
 
if($c[5]=="TARDE"){
$consulta="update grupo_as set codGrupo='T".$c[4].$c[3].$cod.$c[2]."', codCurso='".$cod."', codMate='".$c[2]."', ano='".$c[3]."', periodo='".$c[4]."' , jornada='".$c[5]."', idDoc='".$c[6]."' where codGrupo ='".$c[0]."'";
}
 
if($c[5]=="NOCHE"){
$consulta="update grupo_as set codGrupo='N".$c[4].$c[3].$cod.$c[2]."', codCurso='".$cod."', codMate='".$c[2]."', ano='".$c[3]."', periodo='".$c[4]."' , jornada='".$c[5]."', idDoc='".$c[6]."' where codGrupo ='".$c[0]."'";
}
 if($c[5]=="UNICA"){
$consulta="update grupo_as set codGrupo='U".$c[4].$c[3].$cod.$c[2]."', codCurso='".$cod."', codMate='".$c[2]."', ano='".$c[3]."', periodo='".$c[4]."' , jornada='".$c[5]."', idDoc='".$c[6]."' where codGrupo ='".$c[0]."'";
}
 
$resultado=mysql_query($consulta) or die("error");
				
				
					
					
					header ("Location:gestioncurso.php");
			}
			}
	
	
}else{
header ("Location:gestioncurso.php");	
}
?>