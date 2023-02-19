<?php
session_start();
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$user="";
$pass="";
if(isset($_POST['username'])){
$user = $_POST['username'];
}


if(isset($_POST['password'])){
$pass = $_POST['password'];
}
if(isset($_SESSION['ideUser'])){
$user =	$_SESSION['ideUser'];
}
if(isset($_SESSION['APSbCCODnTgsaeN'])){
$pass =	$_SESSION['APSbCCODnTgsaeN'];
}

if($user!=""&&$pass!=""){
 $query_search = "select * from acceso_as where idDoc = '".$user."' AND pword = '".$pass. "'";
 $query_exec = mysql_query($query_search) or die(mysql_error());
 $rows = mysql_num_rows($query_exec); //canidad de registros encontrados
 $rowsDat=mysql_fetch_array($query_exec);
 mysql_free_result($query_exec);


 /*PERFILES
    	 			 * Adminitrador 			 1
    	 			 * Docente 	Universitario	 2
					 * Docente 	Colegio			 3
    	 			 * Escrito					 4*/
 
  if($rows --> 0==NULL) { 
  
  header("Location:logiin.php"); 

  }else{
	$_SESSION['ideUser']=$rowsDat[0];
	$_SESSION['APSbCCODnTgsaeN']=$rowsDat[1];
	$p=$rowsDat[2];
	
	
	if($p==1||$p==3){
	header ("Location:principaladmin.php"); 
	$_SESSION['perfilUser']=1;
	}
	if($p==2){
	header ("Location:principal.php"); 
	$_SESSION['perfilUser']=$p;
	}
	if($p==4){
		session_destroy ();
	header("Location:logiin.php");
	}
	
 }
}else{
	 header("Location:logiin.php"); 
}



?>