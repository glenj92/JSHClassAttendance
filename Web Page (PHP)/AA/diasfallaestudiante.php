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
 $query_search = "select fechaClas from clase_as where codGrupo='".$gru."' order by fechaClas DESC";
 $resul=mysql_query($query_search);
 //$cons=mysql_fetch_array($resul);
 while($cons=mysql_fetch_array($resul)){
	 	$query_search1 = "select fechaAsis from asistencia_as where codGrupo = '".$gru."' and idEstu='".$ide."' and fechaAsis='".$cons[0]."'";
 		$resul1=mysql_query($query_search1);
		$cons1=mysql_fetch_array($resul1);
		mysql_free_result($resul1);
		//	echo $cons1[0]."<br>";
			if($cons[0]!=$cons1[0]){
			 echo  $cons[0].",";
			}
		
		
		
	// echo $cons[0]."<br>";
   
 }
 mysql_free_result($resul);
}
  //}
  //$cons['codGrupo'];

?>