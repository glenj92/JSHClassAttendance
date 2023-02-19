<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$gru="";
$fe="";

if(isset($_POST['Fecha'])){
$fe = $_POST['Fecha'];
}
if(isset($_POST['Grupo'])){
$gru = $_POST['Grupo'];
}

//$id = 55555;
//$idmate = 1;
if($fe!=""&&$gru!=""){
 $query_search = "select idEstu from asistencia_as where codGrupo = '".$gru."' and fechaAsis= '".$fe."' group by idEstu";
 $resul=mysql_query($query_search);
  while($cons=mysql_fetch_array($resul)){
	 $resul1=mysql_query("select * from estudiante_as where idEstu='".$cons[0]."'");
	 $cons1=mysql_fetch_array($resul1);
	 mysql_free_result($resul1);
  echo  $cons1[0].",".$cons1[1].",".$cons1[2].",";
}
mysql_free_result($resul);
}


?>