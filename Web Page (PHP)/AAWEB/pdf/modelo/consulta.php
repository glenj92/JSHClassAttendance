
<?php 

	//require "../conexion/conexion.php";
	include"conexion.php";
	class consulta{
	
		function reportePdfUsuarios(){			

$vere="";
$mate="";
$doc="";
$tc="";
//establecer y ejecutar consulta

if(isset($_GET['gru'])){
	$vere=$_GET['gru'];
}
if(isset($_GET['mate'])){
	$mate=$_GET['mate'];
}
if(isset($_GET['doc'])){
	$doc=$_GET['doc'];
}
if(isset($_GET['tc'])){
	$tc=$_GET['tc'];
}
			$html="";

			$i=0;

			$html=$html.'<div align="center">
			


			<center><h2>Reporte de Asistencias</h2></center>
			<br /><br />
<table border="0" bordercolor="#0000CC" bordercolordark="#FF0000">

  <tr bgcolor="#DF7401">

	<td><h3><font color="#FFFFFF">ASIGNATURA</font></h3></td>
    <td><h3><font color="#FFFFFF">GRUPO</font></h3></td>
	<td><h3><font color="#FFFFFF">DOCENTE</font></h3></td>
<td><h3><font color="#FFFFFF">TOTAL DE CLASES</font></h3></td>
  
  </tr>
   <tr>

	<td>'.$mate.'</td>	
					<td>'.$vere.'</td>	
					<td>'.$doc.'</td>
					<td>'.$tc.'</td>	
  
  </tr>
  </table>
 
			
			<br /><br />			
			<table border="0" bordercolor="#0000CC" bordercolordark="#FF0000">';	
			$html=$html.'<tr bgcolor="#DF7401"><td><h3><font color="#FFFFFF">Identificacion</font></h3></td><td><h3><font color="#FFFFFF">Nombre</font></h3></td><td><h3><font color="#FFFFFF"># Asistencias (%)</font></h3></td><td><h3><font color="#FFFFFF"># Fallas (%)</font></h3></td><td><h3><font color="#FFFFFF"># Excusas (%)</font></h3></td></tr>';
	
$consultaa=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo='".$vere."') order by apellEstu ASC");
while($fi2=mysql_fetch_array($consultaa)){
	 $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$vere."' group by idEstu");
	while($fi=mysql_fetch_array($consulta1)){
		$consulta2=mysql_query("select codMate,idDoc from grupo_as where codGrupo='".$fi[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select * from docente_as where idDoc='".$c[1]."'");
		$fi4=mysql_fetch_array($consulta4);
		$resul1=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$vere."'");
			while($cons1=mysql_fetch_array($resul1)){
			 $resul4=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='0'");//cantidad asistencias
				  while($cons4=mysql_fetch_array($resul4)){
					
						
						$resul5=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='1'");
										while($cons5=mysql_fetch_array($resul5)){	
						
												$Nofallas=($cons1[0]-$cons4[0])-$cons5[0];
												if($i%2==0){
														$html=  $html.'<tr bgcolor="#95B1CE">';
													}else{
														$html=$html.'<tr>';
													}
													$html = $html.'<td>';
													$html = $html. $fi[0];
													$html = $html.'</td><td>';
													$html = $html. $fi2[2]." ".$fi2[1];
													$html = $html.'</td><td>';
													$html = $html. $cons4[0]." (".round(($cons4[0]*100)/$cons1[0],1)." %)";
													$html = $html.'</td><td>';
													$html = $html. $Nofallas." (".round(($Nofallas*100)/$cons1[0],1)." %)";
													$html = $html.'</td><td>';
													$html = $html. $cons5[0]." (".round(($cons5[0]*100)/$cons1[0],1)." %)";
													$html = $html.'</td></tr>';		
													$i++;
												
													


												
										}
				  }
				
			}
		
	}
	
	
}
			 
			 $html=$html.'</table></div>';			
     		 return ($html);
			 
			 
			 
			 
			 
			 
			 
		}
		
		
			
		//-----------------------------------------------------------------------------------------------------------------------		
	}

?>

