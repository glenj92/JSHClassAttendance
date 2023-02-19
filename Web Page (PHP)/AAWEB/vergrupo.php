<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
<center>
  <a href="vistagrafica.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
<center>
  <a href="vistagrafica.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>

<?php

header( 'Content-Type: text/html;charset=utf-8' );
//$traslado=$_SESSION['traslado'];
if($_SESSION['perfilUser']==1){

//echo"BIENVENIDO ".$_SESSION['usuario'];
//$_SESSION['traslado']="gestionusuarios.php";
//conexion
include"conexion.php";

$vere="";
//establecer y ejecutar consulta

if(isset($_GET['vergru'])){
	$vere=$_GET['vergru'];
}

if($vere!=""){
$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo='".$vere."') order by apellEstu ASC");

}


//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>

	<th>Asignatura</th>
    <th>Grupo</th>
	<th>Docente</th>
<th>Total de clases</th>
<th></th>
<th></th>
    
	
	
	

  
  </tr>
   </thead>";
   
   
	if($vere!=""){
	  
	
	$r=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$vere."'");
$rc=mysql_fetch_array($r);
	
		$consulta2=mysql_query("select codMate,idDoc from grupo_as where codGrupo='".$vere."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select * from docente_as where idDoc='".$c[1]."'");
		$fi4=mysql_fetch_array($consulta4);
		

		
					echo"
					<tbody>";

						
				echo"<tr>";
				
					 echo"
	
					<td>".$fi3[0]."</td>	
					<td>".$vere."</td>	
					<td>".$fi4[2]." ".$fi4[1]."</td>
					<td>".$rc[0]."</td>	
					
			<td><a href='graficagrupo.php?gru=".$vere."' id='agc'>Ver Grafico del Grupo</a></td>
			<td><a href='pdf/vista/reporte_grupo_pdf.php?gru=".$vere."&mate=".$fi3[0]."&doc=".$fi4[2]." ".$fi4[1]."&tc=".$rc[0]."' id='agc' target='_blank'>Ver Informe</a></td>

	

</tr>
";

echo"
</tbody>";
	}

	
	
			
			

   
   echo"
  </table>
  </div>
  
  
<br/>
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Identificacion</th>
	<th>Nombre Completo</th>
	<th>No Asistencias</th>
    <th>No Fallas</th>
	<th>No Excusas</th>
	<th></th>

    
	
	
	
	
  
  </tr>
  </thead>
 


";

while($fi2=mysql_fetch_array($consulta)){
	if($vere!=""){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$vere."' group by idEstu");
	}
	$cont=1;
	while($fi=mysql_fetch_array($consulta1)){
		$consulta2=mysql_query("select codMate,idDoc from grupo_as where codGrupo='".$fi[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select * from docente_as where idDoc='".$c[1]."'");
		$fi4=mysql_fetch_array($consulta4);
		if($fi[3]==1){
				$estado="Excusado";
			}else if($fi[3]==0){
				$estado="Asistio";
			}
			
			$resul1=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$vere."'");
while($cons1=mysql_fetch_array($resul1)){
	
				  $resul4=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='0'");//cantidad asistencias
				  while($cons4=mysql_fetch_array($resul4)){
					
						
						$resul5=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='1'");
						while($cons5=mysql_fetch_array($resul5)){	
						
						$Nofallas=($cons1[0]-$cons4[0])-$cons5[0];  
						//	  echo $cons3[0].",".$cons3[2].",".$cons3[1].",".$cons4[0].",".$Nofallas.",".$cons5[0].",";
		
					echo"
					<tbody>";
				if($cont%2==0){
					echo"
					 <tr class='alt'>";
					 $cont++;
				}else{
				echo"<tr>";
				$cont++;
				}
					 echo"
					<td>".$fi[0]."</td>
					<td>".$fi2[2]." ".$fi2[1]."</td>	
					<td>".$cons4[0]."</td>	
					<td>".$Nofallas."</td>	
					<td>".$cons5[0]."</td>	
					
			

					<td><a href='graficaestu1.php?id=".$fi[0]."&nombre=".$fi2[2]." ".$fi2[1]."&asis=".$cons4[0]."&fall=".$Nofallas."&exc=".$cons5[0]."&gru=".$vere."&mat=".$fi3[0]."' id='agc'>Ver Grafico del Estudiante</a></td>
	

</tr>";

}
						mysql_free_result($resul5);
				  }
				  mysql_free_result($resul4);
}

echo"
</tbody>";
	

	
	}
			
			
}

//añadir un registro
echo"
 
</table></div>

";
}else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
	
//echo"BIENVENIDO ".$_SESSION['usuario'];
//$_SESSION['traslado']="gestionusuarios.php";
//conexion
include"conexion.php";

$vere="";
//establecer y ejecutar consulta

if(isset($_GET['vergru'])){
	$vere=$_GET['vergru'];
}

if($vere!=""){
$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo='".$vere."') order by apellEstu ASC");

}


//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>

	<th>Asignatura</th>
    <th>Grupo</th>
	<th>Docente</th>
<th>Total de clases</th>
<th></th>
<th></th>
    
	
	
	

  
  </tr>
   </thead>";
   
   
	if($vere!=""){
	  
	
	$r=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$vere."'");
$rc=mysql_fetch_array($r);
	
		$consulta2=mysql_query("select codMate,idDoc from grupo_as where codGrupo='".$vere."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select * from docente_as where idDoc='".$c[1]."'");
		$fi4=mysql_fetch_array($consulta4);
		

		
					echo"
					<tbody>";

						
				echo"<tr>";
				
					 echo"
	
					<td>".$fi3[0]."</td>	
					<td>".$vere."</td>	
					<td>".$fi4[2]." ".$fi4[1]."</td>
					<td>".$rc[0]."</td>	
					
			<td><a href='graficagrupo.php?gru=".$vere."' id='agc'>Ver Grafico del Grupo</a></td>
			<td><a href='pdf/vista/reporte_grupo_pdf.php?gru=".$vere."&mate=".$fi3[0]."&doc=".$fi4[2]." ".$fi4[1]."&tc=".$rc[0]."' id='agc' target='_blank'>Ver Informe</a></td>

	

</tr>
";

echo"
</tbody>";
	}

	
	
			
			

   
   echo"
  </table>
  </div>
  
  
<br/>
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Identificacion</th>
	<th>Nombre Completo</th>
	<th>No Asistencias</th>
    <th>No Fallas</th>
	<th>No Excusas</th>
	<th></th>

    
	
	
	
	
  
  </tr>
  </thead>
 


";

while($fi2=mysql_fetch_array($consulta)){
	if($vere!=""){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$vere."' group by idEstu");
	}
	$cont=1;
	while($fi=mysql_fetch_array($consulta1)){
		$consulta2=mysql_query("select codMate,idDoc from grupo_as where codGrupo='".$fi[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select * from docente_as where idDoc='".$c[1]."'");
		$fi4=mysql_fetch_array($consulta4);
		if($fi[3]==1){
				$estado="Excusado";
			}else if($fi[3]==0){
				$estado="Asistio";
			}
			
			$resul1=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$vere."'");
while($cons1=mysql_fetch_array($resul1)){
	
				  $resul4=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='0'");//cantidad asistencias
				  while($cons4=mysql_fetch_array($resul4)){
					
						
						$resul5=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='1'");
						while($cons5=mysql_fetch_array($resul5)){	
						
						$Nofallas=($cons1[0]-$cons4[0])-$cons5[0];  
						//	  echo $cons3[0].",".$cons3[2].",".$cons3[1].",".$cons4[0].",".$Nofallas.",".$cons5[0].",";
		
					echo"
					<tbody>";
				if($cont%2==0){
					echo"
					 <tr class='alt'>";
					 $cont++;
				}else{
				echo"<tr>";
				$cont++;
				}
					 echo"
					<td>".$fi[0]."</td>
					<td>".$fi2[2]." ".$fi2[1]."</td>	
					<td>".$cons4[0]."</td>	
					<td>".$Nofallas."</td>	
					<td>".$cons5[0]."</td>	
					
			

					<td><a href='graficaestu1.php?id=".$fi[0]."&nombre=".$fi2[2]." ".$fi2[1]."&asis=".$cons4[0]."&fall=".$Nofallas."&exc=".$cons5[0]."&gru=".$vere."&mat=".$fi3[0]."' id='agc'>Ver Grafico del Estudiante</a></td>
	

</tr>";

}
						mysql_free_result($resul5);
				  }
				  mysql_free_result($resul4);
}

echo"
</tbody>";
	

	
	}
			
			
}

//añadir un registro
echo"
 
</table></div>


";

}else
{
echo"Acceso Restringido para este usurio";	
}
?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>