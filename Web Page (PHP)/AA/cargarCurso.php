<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";


 $query_search = "select * from curso_as";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	
  echo  $cons[0]."-".$cons[1].","; 
}
mysql_free_result($resul);



?>