<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$id="";
$ano="";
if(isset($_POST['Idd'])){
$id = $_POST['Idd'];
}
if(isset($_POST['Ano'])){
$ano = $_POST['Ano'];
}
//$id = $_POST['Idd'];
//$id = 55555;
//$ano=2013;

if($id!=""&&$ano!=""){
 $query_search = "select codMate from grupo_as where idDoc = '".$id."' and ano='".$ano."' group by codMate";
 $resul=mysql_query($query_search);
 //$cons=mysql_fetch_array($resul);
 while($cons=mysql_fetch_array($resul)){
	 
	
$query_search1 = "select * from asignatura_as where codMate = '".$cons[0]."'";
 $resul1=mysql_query($query_search1);
  $cons1=mysql_fetch_array($resul1);
  mysql_free_result($resul1);
    echo  $cons1[0]."-".$cons1[1].",";
 }
 mysql_free_result($resul);
}
  //}
  //$cons['codGrupo'];

?>