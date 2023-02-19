<?php
header( 'Content-Type: text/html;charset=utf-8' );
include "conexion.php";

$idgrup="";
$mate="";

if(isset($_POST['CodGrupo'])){
$idgrup=$_POST['CodGrupo'];
}
if(isset($_POST['CodMate'])){
$mate = $_POST['CodMate'];
}
//$idgrup="M120143323930";
if($idgrup!=""){
/*	
	$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo='".$idgrup."') order by apellEstu ASC");
	while($fi2=mysql_fetch_array($consulta)){
	
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$idgrup."' group by idEstu");
	while($fi=mysql_fetch_array($consulta1)){
	
		$resul1=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$idgrup."'");
while($cons1=mysql_fetch_array($resul1)){
	
				  $resul4=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$idgrup."' and excusa='0'");//cantidad asistencias
				  while($cons4=mysql_fetch_array($resul4)){
					
						
						$resul5=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$idgrup."' and excusa='1'");
						while($cons5=mysql_fetch_array($resul5)){	
						
						$Nofallas=($cons1[0]-$cons4[0])-$cons5[0];  
						//	  echo $cons3[0].",".$cons3[2].",".$cons3[1].",".$cons4[0].",".$Nofallas.",".$cons5[0].",";
						    echo $fi[0].",".$fi2[2].",".$fi2[1].",".$cons4[0].",".$Nofallas.",".$cons5[0].",";
		
			

}
						mysql_free_result($resul5);
				  }
				  mysql_free_result($resul4);
}
}

	

	
	
	
	}
	
	
	*/
 

$resul1=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$idgrup."'");
while($cons1=mysql_fetch_array($resul1)){
	
	
	  $resul2=mysql_query("select * from asistencia_as where codGrupo='".$idgrup."' group by idEstu");
	  while($cons2=mysql_fetch_array($resul2)){
		  
	   		$resul3=mysql_query("select * from estudiante_as where idEstu='".$cons2[0]."'");
			while($cons3=mysql_fetch_array($resul3)){
			
				  $resul4=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$cons3[0]."' 
				  						and codGrupo='".$idgrup."' and excusa='0'");//cantidad asistencias
				  while($cons4=mysql_fetch_array($resul4)){
					
						
						$resul5=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$cons3[0]."' 
				  						and codGrupo='".$idgrup."' and excusa='1'");
						while($cons5=mysql_fetch_array($resul5)){	
						
						$Nofallas=($cons1[0]-$cons4[0])-$cons5[0];  
							  echo $cons3[0].",".$cons3[2].",".$cons3[1].",".$cons4[0].",".$Nofallas.",".$cons5[0].",".$cons1[0].",";
						}
						mysql_free_result($resul5);
				  }
				  mysql_free_result($resul4);
				  
			}
			mysql_free_result($resul3);
	  }
	  mysql_free_result($resul2);
}
mysql_free_result($resul1);

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