<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$id="";

$ano="";
if(isset($_POST['IdDoc'])){
$id = $_POST['IdDoc'];
}
if(isset($_POST['Ano'])){
$ano = $_POST['Ano'];
}
//$id = 123456;
//$ano = 2014;
if($id!=""&&$ano!=""){
 $query_search = "select codGrupo,codMate from grupo_as where idDoc = '".$id."' and ano='".$ano."' and codGrupo not in(select CodGrupo from clase_as)";
 $resul=mysql_query($query_search) or die("vacio");
  while($cons=mysql_fetch_array($resul)){
	  $query_search1 = "select nomMate from asignatura_as where codMate = '".$cons[1]."'";
		 $resul1=mysql_query($query_search1) or die("vacio");
		 while($cons1=mysql_fetch_array($resul1)){
		  echo  $cons[0].",".$cons1[0].","; 
		 }
		 mysql_free_result($resul1);
 
}
mysql_free_result($resul);
}


?>