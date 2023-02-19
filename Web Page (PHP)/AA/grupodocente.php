<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";

$id = $_POST['Idd'];
//$id = 55555;

 $query_search = "select codGrupo from grupo_as where idDoc = '".$id."'";
/* $query_exec = mysql_query($query_search) or die(mysql_error());
 $rows = mysql_num_rows($query_exec);
 */
 $resul=mysql_query($query_search);
// $cons=mysql_fetch_array($resul);

  //if($rows --> 0) { 
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]." "; 
}
mysql_free_result($resul);

  //}
  //$cons['codGrupo'];

?>