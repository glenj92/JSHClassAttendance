<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$gru="";

if(isset($_POST['Grupo'])){
$gru = $_POST['Grupo'];
}

//$id = $_POST['Idd'];
//$id = 55555;
//$ano=2013;
//$gru="T1201414";

if($gru!=""){
 $query_search = "select fechaClas from clase_as where codGrupo = '".$gru."' order by fechaClas DESC";
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