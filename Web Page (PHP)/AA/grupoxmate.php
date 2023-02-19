<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$id="";
$ano="";
$mat="";
if(isset($_POST['Idd'])){
$id = $_POST['Idd'];
}
if(isset($_POST['Ano'])){
$ano = $_POST['Ano'];
}
if(isset($_POST['Mate'])){
$mat = $_POST['Mate'];
}
//$id = $_POST['Idd'];
//$id = 55555;
//$ano=2013;

if($id!=""&&$ano!=""&&$mat!=""){
 $query_search = "select codGrupo from grupo_as where idDoc = '".$id."' and ano='".$ano."' and codMate='".$mat."'";
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