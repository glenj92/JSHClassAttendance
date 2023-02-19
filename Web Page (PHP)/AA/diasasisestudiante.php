<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$gru="";
$ide="";

if(isset($_POST['Grupo'])){
$gru = $_POST['Grupo'];
}
if(isset($_POST['IdE'])){
$ide = $_POST['IdE'];
}
//$id = $_POST['Idd'];
//$ide = 1118840809;
//$ano=2013;
//$gru="T1201414";

if($gru!=""&&$ide!=""){
 $query_search = "select fechaAsis from asistencia_as where codGrupo = '".$gru."' and idEstu='".$ide."' and excusa='0' order by fechaAsis DESC";
 $resul=mysql_query($query_search);
 //$cons=mysql_fetch_array($resul);
 while($cons=mysql_fetch_array($resul)){
	 
    echo  $cons[0].",";
 }
 mysql_free_result($resul);
}
  //}
  //$cons['codGrupo'];

?>