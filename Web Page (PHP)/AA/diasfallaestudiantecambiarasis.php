<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$gru="";
$ide="";
$fec="";

if(isset($_POST['Grupo'])){
$gru = $_POST['Grupo'];
}
if(isset($_POST['IdE'])){
$ide = $_POST['IdE'];
}
if(isset($_POST['Fec'])){
$fec = $_POST['Fec'];
}
//$id = $_POST['Idd'];
//$ide = 1118840809;
//$ano=2013;
//$gru="T1201414";

if($gru!=""&&$ide!=""&&$fec!=""){
 $query_search = "insert into asistencia_as values('".$ide."','".$gru."','".$fec."','0')";
 $resul=mysql_query($query_search);

$numfilas=mysql_affected_rows();

echo"asiscambio";
}
  //}
  //$cons['codGrupo'];

?>