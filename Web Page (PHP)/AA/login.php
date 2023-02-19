<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$user="";
$pass="";
if(isset($_POST['user'])){
$user = $_POST['user'];
}


if(isset($_POST['pass'])){
$pass = $_POST['pass'];
}
//$user = $_POST['user'];
//$pass = $_POST['pass'];
if($user!=""&&$pass!=""){
 $query_search = "select * from acceso_as where idDoc = '".$user."' AND pword = '".$pass. "'";
 $query_exec = mysql_query($query_search) or die(mysql_error());
 $rows = mysql_num_rows($query_exec); //canidad de registros encontrados
 $rowsDat=mysql_fetch_array($query_exec);
 mysql_free_result($query_exec);
/* $consul="select * from docente_as where idDoc='".$user."'";
 $consul_exec = mysql_query($consul) or die(mysql_error());
 $cons=mysql_fetch_array($consul_exec);*/
 /*PERFILES
    	 			 * Adminitrador 			 1
    	 			 * Docente 	Universitario	 2
					 * Docente 	Colegio			 3
    	 			 * Escrito					 4*/
 
  if($rows --> 0==NULL) { 
  echo "NO"; 
  }
else{
	echo "OK".",".$rowsDat[2];
 }
}

/*
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