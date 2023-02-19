
<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <h1 style="padding-top:0px; margin-top:0px; color:#666;">Asistencias</h1></center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <h1 style="padding-top:0px; margin-top:0px; color:#666;">Asistencias</h1></center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>

<?php

header( 'Content-Type: text/html;charset=utf-8' );
//$traslado=$_SESSION['traslado'];
$sch="";
$idestu="";
$estu="";

$doc="";
$asi="";
$feci="";
$fecf="";

//***********************************
if(isset($_POST['idestudiante'])){
	$idestu=$_POST['idestudiante'];
}
if(isset($_POST['nestu'])){
	$estu=$_POST['nestu'];
}

if(isset($_POST['doce'])){
	$doc=$_POST['doce'];
}
if(isset($_POST['materia'])){
	$asi=$_POST['materia'];
}
if(isset($_POST['feci'])){
	$feci=$_POST['feci'];
}
if(isset($_POST['fecf'])){
	$fecf=$_POST['fecf'];
}
if(isset($_GET['sh'])){
	$sch=$_GET['sh'];
}
if($_SESSION['perfilUser']==1){

//echo"BIENVENIDO ".$_SESSION['usuario'];
//$_SESSION['traslado']="gestionusuarios.php";
//conexion
include"conexion.php";
$busqueda="";
$tipob="";
$vere="";

//establecer y ejecutar consulta



//*************************************
if(isset($_POST['buscar'])){
	$busqueda=$_POST['buscar'];
}
if(isset($_POST['tipob'])){
	$tipob=$_POST['tipob'];
}
if(isset($_GET['verestu'])){
	$vere=$_GET['verestu'];
}

if($sch==1){
	//BUSQUEDA BAZANDA
	if($idestu!=""){
		$consulta=mysql_query("select * from estudiante_as where idEstu='".$idestu."'order  by apellEstu ASC");
	}else if($estu!=""){
			$consulta=mysql_query("select * from estudiante_as where nomEstu like '%".$estu."%' or apellEstu like '%".$estu."%' order  by apellEstu ASC");
		}else if($asi!=""){
			$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where codGrupo='".$asi."' or codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%'))) order by apellEstu ASC");
				}else if($doc!=""){
					$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc IN(select idDoc from docente_as where nomDoc like '%".$doc."%' or apeDoc like '%".$doc."%'))) order by apellEstu ASC");
				}else{
			
	$consulta=mysql_query("select * from estudiante_as order by apellEstu ASC");
		}
}else if($vere!=""){
$consulta=mysql_query("select * from estudiante_as where idEstu='".$vere."' order by apellEstu ASC");
/*}else if($busqueda!=""&&$tipob!=""){
	if($tipob=="id"){
	$consulta=mysql_query("select * from estudiante_as where idEstu='".$busqueda."' order by apellEstu ASC");
	}else if($tipob=="n"){
	$consulta=mysql_query("select * from estudiante_as where nomEstu like '%".$busqueda."%' or apellEstu like '%".$busqueda."%' order by apellEstu ASC");
	}else if($tipob=="g"){
		$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo='".$busqueda."') order by apellEstu ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}else if($tipob=="m"){
		$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%'))) order by apellEstu ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}else if($tipob=="d"){
		$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc IN(select idDoc from docente_as where nomDoc like '%".$busqueda."%' or apeDoc like '%".$busqueda."%'))) order by apellEstu ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}
*/
}else{
	$consulta=mysql_query("select * from estudiante_as order by apellEstu ASC");
}


//imprimir consulta
echo"
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  
  </thead>
  <tbody>
 
  ";
  ?>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <?php
  echo"
    <form action='gestionasis.php?sh=1' method='post'>
   
 <tr>
    <td>Identificacion (Estudiante)</td><td><input name='idestudiante' type='text' /></td>
	<td>Nombre o Apellido (Estudiante)</td><td><input name='nestu' type='text' /></td>
	<td>Docente</td><td><input name='doce' type='text' /></td>
	
	</tr>
	<tr>
	<td>Grupo o Asignatura</td><td><input name='materia' type='text' /></td>
	<td>Fecha inicial<br/><br/>Fecha final</td><td><input type='text' name='feci' id='fechai' /><br/><input type='text' name='fecf' id='fechaf'/></td>
	<td colspan='6'><center><input type='submit' value='Buscar'/></center></td>
    </tr>
	

	
 
  </form>
  ";
  ?>
  <script>
$(function(){
	$( "#fechai" ).datepicker({
		currentText: "Now",
		dateFormat: "yy-mm-dd",
		appendText: "(yyyy-mm-dd)"
	});
	$("submit").button();
	$( "#fechaf" ).datepicker({
		currentText: "Now",
		dateFormat: "yy-mm-dd",
		appendText: "(yyyy-mm-dd)"
	});
	$("submit").button();
});
</script>
  <?php
  echo"
  </tbody>
  </table></div>
  
<br/>
<br/>

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Identificacion</th>
	<th>Estudiante</th>
	<th>Asignatura</th>
    <th>Grupo</th>
	<th>Programa o Curso</th>
	<th>Docente</th>
	<th>Fecha</th>
	<th>Estado</th>
    <th></th>
	<th></th>
	";
	/*<form id='form1' name='form1' method='post' action='gestionasis.php'>
	<th>
  	<p>
  	<input type='text' name='buscar' id='buscar'/>
  	<input type='submit' name='bus' id='busc' value='Buscar' />
  	</p>
	</th>
	<th>
	Tipo de Busqueda
	<select name='tipob'>
	<option value='id'>Identificacion</option>
	<option value='n'>Nombre o Apellido</option>
	<option value='g'>Grupo</option>
	<option value='m'>Asignatura</option>
	<option value='d'>Docente</option>
	</select></th>
    </form>*/
	echo"
  </tr>
  </thead>
 


";

while($fi2=mysql_fetch_array($consulta)){
	/*if($tipob=="g"){
		$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$busqueda."'");
	}else if($tipob=="m"){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%'))");
	}else if($tipob=="d"){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where idDoc IN(select idDoc from docente_as where nomDoc like '%".$busqueda."%' or apeDoc like '%".$busqueda."%'))");
	}else */if($sch==1){
		//BUSQUEDA BAZANDA     **************************************************************************************************************
		if(((($idestu!=""||$estu!="")&&$asi!="")||$asi!="")&&$doc==""&&$feci==""&&$fecf==""){
			$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codGrupo='".$asi."' or codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%'))");
		}else if((($doc!=""&&$asi!="")||$doc!="")&&$feci==""&&$fecf==""){
			$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%') and idDoc IN(select idDoc from docente_as where nomDoc like '%".$doc."%' or apeDoc like '%".$doc."%'))");
			
								//FECHAS ****************************************************************************************************
		}else if(((($idestu!=""||$estu!="")&&$asi!="")||$asi!=""||$idestu!=""||$estu!="")&&$doc==""&&$feci!=""&&$fecf!=""){
			$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codGrupo='".$asi."' or codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%')) and fechaAsis between '".$feci."' and '".$fecf."'");
		}else if((($doc!=""&&$asi!="")||$doc!="")&&$feci!=""&&$fecf!=""){
			$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%') and idDoc IN(select idDoc from docente_as where nomDoc like '%".$doc."%' or apeDoc like '%".$doc."%')) and fechaAsis between '".$feci."' and '".$fecf."'");
			
		}else{
			$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."'");
		}
		
		
		
}else{
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."'");
	}
	$cont=1;
	while($fi=mysql_fetch_array($consulta1)){
		$consulta2=mysql_query("select codMate,idDoc,codCurso from grupo_as where codGrupo='".$fi[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select * from docente_as where idDoc='".$c[1]."'");
		$fi4=mysql_fetch_array($consulta4);
		$querry=mysql_query("select nomCurso from curso_as where codCurso='".$c[2]."'");
		$resulQuerry=mysql_fetch_array($querry);
		if($fi[3]==1){
				$estado="Excusado";
			}else if($fi[3]==0){
				$estado="Asistio";
			}
		
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
					<td>".$fi3[0]."</td>	
					<td>".$fi[1]."</td>	
					<td>".$resulQuerry[0]."</td>
					<td>".$fi4[2]." ".$fi4[1]."</td>	
					<td>".$fi[2]."</td>
					<td>".$estado."</td>
			

					<td><a href='formupdateasis.php?id=".$fi[0]."&nombre=".$fi2[2]." ".$fi2[1]."&mate=".$fi3[0]."&gru=".$fi[1]."&prog=".$resulQuerry[0]."&doce=".$fi4[2]." ".$fi4[1]."&fec=".$fi[2]."&asi=".$fi[3]."'>Actualizar</a></td>
	
	<td><a href='eliminarasis.php?id=".$fi[0]."&gru=".$fi[1]."&fec=".$fi[2]."&asi=".$fi[3]."' id='elic'>Eliminar Asistencia</a></td>
</tr>
</tbody>";
	

	
	}
			
			
}

//añadir un registro
echo"
 
</table></div>
";
}else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
	
include"conexion.php";
$busqueda="";
$tipob="";
$vere="";
$id="";

if(isset($_SESSION['ideUser'])){
$id = $_SESSION['ideUser'];
}
//establecer y ejecutar consulta
if(isset($_POST['buscar'])){
	$busqueda=$_POST['buscar'];
}
if(isset($_POST['tipob'])){
	$tipob=$_POST['tipob'];
}
if(isset($_GET['verestu'])){
	$vere=$_GET['verestu'];
}
if($sch==1){
	//BUSQUEDA BAZANDA
	if($idestu!=""){
		$consulta=mysql_query("select * from estudiante_as where idEstu='".$idestu."' and idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) order  by apellEstu ASC");
	}else if($estu!=""){
			$consulta=mysql_query("select * from estudiante_as where (nomEstu like '%".$estu."%' or apellEstu like '%".$estu."%') and idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) order  by apellEstu ASC");
		}else if($asi!=""){
			$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where codGrupo='".$asi."' or codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%'))) and idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) order by apellEstu ASC");
				}else{
			
	$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) order by apellEstu ASC");
		}
}else if($vere!=""){
$consulta=mysql_query("select * from estudiante_as where idEstu='".$vere."' order by apellEstu ASC");
/*}else if($busqueda!=""&&$tipob!=""){
	if($tipob=="id"){
	$consulta=mysql_query("select * from estudiante_as where idEstu='".$busqueda."' and idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) order by apellEstu ASC");
	}else if($tipob=="n"){
	$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) and (nomEstu like '%".$busqueda."%' or apellEstu like '%".$busqueda."%') order by apellEstu ASC");
	}else if($tipob=="g"){
		$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo='".$busqueda."' and codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) order by apellEstu ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}else if($tipob=="m"){
		$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."' and codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%'))) order by apellEstu ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}
*/
}else{
	$consulta=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')) order by apellEstu ASC");
}


//imprimir consulta
echo"
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  
  </thead>
  <tbody>
  ";
  ?>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <?php
  echo"
   <form action='gestionasis.php?sh=1' method='post'>
   
 <tr>
    <td>Identificacion (Estudiante)</td><td><input name='idestudiante' type='text' /></td>
	<td>Nombre o Apellido (Estudiante)</td><td><input name='nestu' type='text' /></td>
	
	
	</tr>
	<tr>
	<td>Grupo o Asignatura</td><td><input name='materia' type='text' /></td>
	<td>Fecha inicial<br/><br/>Fecha final</td><td><input type='text' name='feci' id='fechai'  /><br/><input type='text' name='fecf' id='fechaf'  /></td>
	
    </tr>
	<tr>
<td colspan='6'><center><input type='submit' value='Buscar'/></center></td>
	</tr>
 
  </form>
  
   ";
  ?>
    <script>
$(function(){
	$( "#fechai" ).datepicker({
		currentText: "Now",
		dateFormat: "yy-mm-dd",
		appendText: "(yyyy-mm-dd)"
	});
	$("submit").button();
	$( "#fechaf" ).datepicker({
		currentText: "Now",
		dateFormat: "yy-mm-dd",
		appendText: "(yyyy-mm-dd)"
	});
	$("submit").button();
});
</script>
  <?php
  echo"
  </tbody>
  </table></div>
  
<br/>
<br/>

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Identificacion</th>
	<th>Nombre Completo</th>
	<th>Asignatura</th>
    <th>Grupo</th>
	<th>Programa o Curso</th>
	<th>Docente</th>
	<th>Fecha</th>
	<th>Estado</th>
    <th></th>
	<th></th>
	";
/*	<form id='form1' name='form1' method='post' action='gestionasis.php'>
	<th>
  	<p>
  	<input type='text' name='buscar' id='buscar'/>
  	<input type='submit' name='bus' id='busc' value='Buscar' />
  	</p>
	</th>
	<th>
	Tipo de Busqueda
	<select name='tipob'>
	<option value='id'>Identificacion</option>
	<option value='n'>Nombre o Apellido</option>
	<option value='g'>Grupo</option>
	<option value='m'>Asignatura</option>
	</select></th>
    </form>*/
	
	echo"
  </tr>
 </thead>


";

while($fi2=mysql_fetch_array($consulta)){
	/*if($tipob=="g"){
		$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupoIN(select codGrupo from grupo_as where codGrupo like '%".$busqueda."%' and idDoc='".$id."')");
	}else if($tipob=="m"){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."' and codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%'))");
	}*/
	
if($sch==1){
		//BUSQUEDA BAZANDA     **************************************************************************************************************
		if(((($idestu!=""||$estu!="")&&$asi!="")||$asi!="")&&$feci==""&&$fecf==""){
		$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where (codGrupo='".$asi."' or codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%')) and idDoc='".$id."')");
		
							//FECHAS ****************************************************************************************************
		}else if(((($idestu!=""||$estu!="")&&$asi!="")||$asi!=""||$idestu!=""||$estu!="")&&$feci!=""&&$fecf!=""){
           $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."'  and fechaAsis between '".$feci."' and '".$fecf."' and codGrupo IN(select codGrupo from grupo_as where  idDoc='".$id."' and (codGrupo='".$asi."' or codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%')))");
		}else{
		  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')");
		}
	
}else{
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where idDoc='".$id."')");
	}
	$cont=1;
	while($fi=mysql_fetch_array($consulta1)){
		$consulta2=mysql_query("select codMate,idDoc,codCurso from grupo_as where codGrupo='".$fi[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select * from docente_as where idDoc='".$c[1]."'");
		$fi4=mysql_fetch_array($consulta4);
		$querry=mysql_query("select nomCurso from curso_as where codCurso='".$c[2]."'");
		$resulQuerry=mysql_fetch_array($querry);
		if($fi[3]==1){
				$estado="Excusado";
				
			}else if($fi[3]==0){
				$estado="Asistio";
				
			}
		
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
					<td>".$fi3[0]."</td>	
					<td>".$fi[1]."</td>	
					<td>".$resulQuerry[0]."</td>
					<td>".$fi4[2]." ".$fi4[1]."</td>	
					<td>".$fi[2]."</td>
					<td>".$estado."</td>
			

					<td><a href='formupdateasis.php?id=".$fi[0]."&nombre=".$fi2[2]." ".$fi2[1]."&mate=".$fi3[0]."&gru=".$fi[1]."&prog=".$resulQuerry[0]."&doce=".$fi4[2]." ".$fi4[1]."&fec=".$fi[2]."&asi=".$fi[3]."'>Actualizar</a></td>
	
	<td><a href='eliminarasis.php?id=".$fi[0]."&gru=".$fi[1]."&fec=".$fi[2]."&asi=".$fi[3]."' id='elic'>Eliminar Asistencia</a></td>
</tr>
</tbody>
";
	

	
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