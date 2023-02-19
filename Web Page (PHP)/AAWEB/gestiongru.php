<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
  <a style="background:#88A331; border: 3px solid #6E6E6E; color:#FFF; text-decoration: none; margin:5px; padding:5px;">Grupo sin registros</a>
  <center>
  <h1 style="padding-top:0px; margin-top:0px; color:#666;">Gestion de Grupos</h1></center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
  <a style="background:#88A331; border: 3px solid #6E6E6E; color:#FFF; text-decoration: none; margin:5px; padding:5px;">Grupo sin registros</a>
   <center>
  <h1 style="padding-top:0px; margin-top:0px; color:#666;">Gestion de Grupos</h1></center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>
<?php

	if(isset($_SESSION['scodcup'])){
unset($_SESSION['scodcup']);
}

	if(isset($_SESSION['scodmup'])){
unset($_SESSION['scodmup']);
}
	if(isset($_SESSION['siddocup'])){
unset($_SESSION['siddocup']);
}


header( 'Content-Type: text/html;charset=utf-8' );
//$traslado=$_SESSION['traslado'];
$scodm="";
$scodc="";
$siddoc="";
if($_SESSION['perfilUser']==1){
	
	if(isset($_GET['scodm'])){
$_SESSION['scodm']=$_GET['scodm'];
}
	
	if(isset($_SESSION['scodm'])){
$scodm=$_SESSION['scodm'];
}
	if(isset($_GET['scodc'])){
$_SESSION['scodc']=$_GET['scodc'];
}
	
	if(isset($_SESSION['scodc'])){
$scodc=$_SESSION['scodc'];
}
	if(isset($_GET['siddoc'])){
$_SESSION['siddoc']=$_GET['siddoc'];
}
	
	if(isset($_SESSION['siddoc'])){
$siddoc=$_SESSION['siddoc'];
}
//echo"BIENVENIDO ".$_SESSION['usuario'];
//$_SESSION['traslado']="gestionusuarios.php";
//conexion
include"conexion.php";
$busqueda="";
$tipob="";
$vere="";
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

if($vere!=""){
$consulta=mysql_query("select * from estudiante_as where idEstu='".$vere."' order by apellEstu ASC");
}else if($busqueda!=""&&$tipob!=""){
	if($tipob=="c"){
	$consulta=mysql_query("select * from grupo_as where codGrupo like '%".$busqueda."%' order by codGrupo ASC");
	}else if($tipob=="p"){
		$consulta=mysql_query("select * from grupo_as where codCurso IN(select codCurso from curso_as where nomCurso like '%".$busqueda."%') order by codGrupo ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}else if($tipob=="m"){
		$consulta=mysql_query("select * from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%') order by codGrupo ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}else if($tipob=="d"){
		$consulta=mysql_query("select * from grupo_as where idDoc IN(select idDoc from docente_as where nomDoc like '%".$busqueda."%' or apeDoc like '%".$busqueda."%') order by codGrupo ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}

}else{
	$consulta=mysql_query("select * from grupo_as order by codGrupo ASC");
}


//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
 <tr>
    <th>Codigo de Grupo</th>
	<th>Programa o Curso</th>
	<th>Asignatura</th>
    <th>Año</th>
	<th>Periodo</th>
	<th>Jornada</th>
	<th>Docente</th>
	<th></th>
  </tr>
  
</thead>
<tbody>
<tr class='alt'>
 <form action='creargru.php' method='post'>
    <td>(Automatico)</td>
    <td><input name='cur' type='text' value='".$scodc."'/><a href='selccioncur.php'>Curso</a></td>
	<td><input name='mate' type='text' value='".$scodm."'/><a href='selccionmate.php?curso=".$scodc."'>Materia</a></td>
    <td><input name='ano' type='text' value='".date('Y')."' /></td>
	<td><select name='per'>
	<option value='1'>1</option>
	<option value='2'>2</option>
	<option value='3'>3</option>
	<option value='4'>4</option>
	</select></td>
    <td><select name='jor'>
	<option value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>
	<option value='UNICA'>UNICA</option>
	</select></td>
	<td><input name='doc' type='text' value='".$siddoc."'/><a href='selcciondoc.php'>Docente</a></td>
	<td><input type='submit' value='Guardar'/></td>

	</form>
  </tr>
</tbody>
</table></div>
<br/>
<br/>
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Codigo de Grupo</th>
	<th>Programa o Curso</th>
	<th>Asignatura</th>
    <th>Año</th>
	<th>Periodo</th>
	<th>Jornada</th>
	<th>Docente</th>
    

	<form id='form1' name='form1' method='post' action='gestiongru.php'>
		<th>
  	<p>
  	<input type='text' name='buscar' id='buscar'/>
  	<input type='submit' name='bus' id='bus' value='Buscar' />
  	</p>
	</th>
	<th>
	Tipo de Busqueda
	<select name='tipob'>
	<option value='c'>Codigo</option>
	<option value='p'>Programa o Curso</option>
	<option value='m'>Asignatura</option>
	<option value='d'>Docente</option>
	</select></th>
    </form>
  </tr>
 </thead>


";
$cont=1;
while($fi2=mysql_fetch_array($consulta)){
/*	if($tipob=="g"){
		$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$busqueda."'");
	}else if($tipob=="m"){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%'))");
	}else if($tipob=="d"){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where idDoc IN(select idDoc from docente_as where nomDoc like '%".$busqueda."%' or apeDoc like '%".$busqueda."%'))");
	}else{
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."'");
	}
	while($fi=mysql_fetch_array($consulta1)){*/
		$consulta2=mysql_query("select nomCurso from curso_as where codCurso='".$fi2[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$fi2[2]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select nomDoc,apeDoc from docente_as where idDoc='".$fi2[6]."'");
		$fi4=mysql_fetch_array($consulta4);
$consulta11=mysql_query("select codGrupo from clase_as where codGrupo='".$fi2[0]."'");
 $rows = mysql_num_rows($consulta11);
					echo"
					<tbody>";
					if($rows --> 0==NULL){
						echo"
					 <tr class='altt2'>";
					 $cont++;
					}else if($cont%2==0){
					echo"
					 <tr class='alt'>";
					 $cont++;
				}else{
				echo"<tr>";
				$cont++;
				}
					 echo"
					<td>".$fi2[0]."</td>
					<td>".$c[0]."</td>	
					<td>".$fi3[0]."</td>	
					<td>".$fi2[3]."</td>	
					<td>".$fi2[4]."</td>	
					<td>".$fi2[5]."</td>
					<td>".$fi4[1]." ".$fi4[0]."</td>
			

					<td><a href='formupdategru.php?cod=".$fi2[0]."&cur=".$fi2[1]."&mate=".$fi2[2]."&ano=".$fi2[3]."&per=".$fi2[4]."&jor=".$fi2[5]."&doc=".$fi2[6]."'>Actualizar</a></td>
	
	<td><a href='eliminargru.php?gru=".$fi2[0]."' id='elic'>Eliminar Grupo</a></td>
</tr>
</tbody>
";
	

	
//	}
			
			
}

//añadir un registro
echo"
 
</table></div>
";
}
else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
	
		
	if(isset($_GET['scodm'])){
$_SESSION['scodm']=$_GET['scodm'];
}
	
	if(isset($_SESSION['scodm'])){
$scodm=$_SESSION['scodm'];
}
	if(isset($_GET['scodc'])){
$_SESSION['scodc']=$_GET['scodc'];
}
	
	if(isset($_SESSION['scodc'])){
$scodc=$_SESSION['scodc'];
}
	if(isset($_GET['siddoc'])){
$_SESSION['siddoc']=$_GET['siddoc'];
}
	
	if(isset($_SESSION['siddoc'])){
$siddoc=$_SESSION['siddoc'];
}

//echo"BIENVENIDO ".$_SESSION['usuario'];
//$_SESSION['traslado']="gestionusuarios.php";
//conexion
include"conexion.php";
$busqueda="";
$tipob="";
$vere="";
//establecer y ejecutar consulta

$idD="";

if(isset($_SESSION['ideUser'])){
$idD = $_SESSION['ideUser'];
}
if(isset($_POST['buscar'])){
	$busqueda=$_POST['buscar'];
}
if(isset($_POST['tipob'])){
	$tipob=$_POST['tipob'];
}
if(isset($_GET['verestu'])){
	$vere=$_GET['verestu'];
}

if($vere!=""){
$consulta=mysql_query("select * from estudiante_as where idEstu='".$vere."' order by apellEstu ASC");
}else if($busqueda!=""&&$tipob!=""&&$idD!=""){
	if($tipob=="c"){
	$consulta=mysql_query("select * from grupo_as where codGrupo like '%".$busqueda."%' and idDoc='".$idD."' order by codGrupo ASC");
	}else if($tipob=="p"){
		$consulta=mysql_query("select * from grupo_as where codCurso IN(select codCurso from curso_as where nomCurso like '%".$busqueda."%') and idDoc='".$idD."' order by codGrupo ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}else if($tipob=="m"){
		$consulta=mysql_query("select * from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%') and idDoc='".$idD."' order by codGrupo ASC");
	//where idEstu =(select idEstu from asistencia_as where codGrupo='".$busqueda."') 
	}

}else{
	$consulta=mysql_query("select * from grupo_as where idDoc='".$idD."' order by codGrupo ASC");
}

$consuldoc=mysql_query("select nomDoc,apeDoc from docente_as where idDoc='".$idD."'");
		$fidoc=mysql_fetch_array($consuldoc);
//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
 <tr>
   
 <th>Codigo de Grupo</th>
	<th>Programa o Curso</th>
	<th>Asignatura</th>
    <th>Año</th>
	<th>Periodo</th>
	<th>Jornada</th>
	<th>Docente</th>
	<th></th>
  </tr>
  
</thead>
<tbody>
<tr class='alt'>
 <form action='creargru.php?doc=".$idD."' method='post'>
    <td>(Automatico)</td>
    <td><input name='cur' type='text' value='".$scodc."'/><a href='selccioncur.php'>Curso</a></td>
	<td><input name='mate' type='text' value='".$scodm."'/><a href='selccionmate.php?curso=".$scodc."'>Materia</a></td>
    <td><input name='ano' type='text' value='".date('Y')."' /></td>
	<td><select name='per'>
	";
	if($_SESSION['perfilUser']==2){
	echo"
	<option value='1'>1</option>
	<option value='2'>2</option>
	</select></td>
    <td><select name='jor'>
	<option value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>";
	}else if($_SESSION['perfilUser']==3){
		echo"
		<option value='1'>1</option>
	<option value='2'>2</option>
	<option value='3'>3</option>
	<option value='4'>4</option>
	</select></td>
    <td><select name='jor'>
	<option value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>
	<option value='UNICA'>UNICA</option>";
	}
	echo"
	</select></td>
	<td>".$fidoc[1]." ".$fidoc[0]."</td>
	<td><input type='submit' value='Guardar'/></td>
	
	</form>
  </tr>
</tbody>
</table></div>

<br/>
<br/>
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Codigo de Grupo</th>
	<th>Programa o Curso</th>
	<th>Asignatura</th>
    <th>Año</th>
	<th>Periodo</th>
	<th>Jornada</th>
	<th>Docente</th>
    

	<form id='form1' name='form1' method='post' action='gestiongru.php'>
		<th>
  	<p>
  	<input type='text' name='buscar' id='buscar'/>
  	<input type='submit' name='bus' id='bus' value='Buscar' />
  	</p>
	</th>
	<th>
	Tipo de Busqueda
	<select name='tipob'>
	<option value='c'>Codigo</option>
	<option value='p'>Programa o Curso</option>
	<option value='m'>Asignatura</option>
	</select></th>
    </form>
  </tr>
 </thead>


";
$cont=1;
while($fi2=mysql_fetch_array($consulta)){
/*	if($tipob=="g"){
		$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$busqueda."'");
	}else if($tipob=="m"){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$busqueda."%'))");
	}else if($tipob=="d"){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where idDoc IN(select idDoc from docente_as where nomDoc like '%".$busqueda."%' or apeDoc like '%".$busqueda."%'))");
	}else{
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."'");
	}
	while($fi=mysql_fetch_array($consulta1)){*/
		$consulta2=mysql_query("select nomCurso from curso_as where codCurso='".$fi2[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$fi2[2]."'");
		$fi3=mysql_fetch_array($consulta3);
		$consulta4=mysql_query("select nomDoc,apeDoc from docente_as where idDoc='".$fi2[6]."'");
		$fi4=mysql_fetch_array($consulta4);
				
			$consulta11=mysql_query("select codGrupo from clase_as where codGrupo='".$fi2[0]."'");
 $rows = mysql_num_rows($consulta11);
					echo"
					<tbody>";
					if($rows --> 0==NULL){
						echo"
					 <tr class='altt2'>";
					 $cont++;
					}else if($cont%2==0){
					echo"
					 <tr class='alt'>";
					 $cont++;
				}else{
				echo"<tr>";
				$cont++;
				}
					 echo"
					<td>".$fi2[0]."</td>
					<td>".$c[0]."</td>	
					<td>".$fi3[0]."</td>	
					<td>".$fi2[3]."</td>	
					<td>".$fi2[4]."</td>	
					<td>".$fi2[5]."</td>
					<td>".$fi4[1]." ".$fi4[0]."</td>
			

					<td><a href='formupdategru.php?cod=".$fi2[0]."&cur=".$fi2[1]."&mate=".$fi2[2]."&ano=".$fi2[3]."&per=".$fi2[4]."&jor=".$fi2[5]."&doc=".$fi2[6]."'>Actualizar</a></td>
	";
	if($rows --> 0==NULL){
	
	echo"<td><a href='eliminargru.php?gru=".$fi2[0]."' id='elic'>Eliminar Grupo</a></td>
	
	";
	}else{
	echo"<td></td>
	
	";
	}
	echo"
</tr>
</tbody>
";
	

	
//	}
			
			
}

//añadir un registro
echo"
 
</table></div>
";
	
	
	
}else{
echo"Acceso Restringido para este usurio";	
}
?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>