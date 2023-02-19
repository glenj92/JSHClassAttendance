<?php

include "conexion.php";



	

for($i=1;$i<20;$i++){
	
 		$resul=mysql_query("insert into estudiante_as values('".$i."','nomebre ".$i."','apellido ".$i."')");
 	
			
			echo " -  ".$i;
			
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