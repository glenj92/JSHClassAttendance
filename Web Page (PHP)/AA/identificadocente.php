<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$id="";

if(isset($_POST['id'])){
$id = $_POST['id'];
}
//$id="123456";
if($id!=""){
 $query_search = "select * from docente_as where idDoc = '".$id."'";
/* $query_exec = mysql_query($query_search) or die(mysql_error());
 $rows = mysql_num_rows($query_exec);
 */
 $resul=mysql_query($query_search);
 $cons=mysql_fetch_array($resul);
 mysql_free_result($resul);
 
  //if($rows --> 0) { 
  echo $cons[1].",".$cons[2];  
  //}
}
?>