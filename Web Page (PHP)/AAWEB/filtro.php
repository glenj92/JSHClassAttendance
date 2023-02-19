<?php session_start();  ?>



<?php

header( 'Content-Type: text/html;charset=utf-8' );
//$traslado=$_SESSION['traslado'];
if($_SESSION['perfilUser']==1){

//echo"BIENVENIDO ".$_SESSION['usuario'];
//$_SESSION['traslado']="gestionusuarios.php";
//conexion
include"conexion.php";
$busqueda="";
$tipob="";
$vere="";
$sch="";
$ide="";
$gru="";
$doc="";
$asi="";
$estu="";
$fec="";
$esta="";
$pro="";
//establecer y ejecutar consulta

//***********************************
if(isset($_POST['ide'])){
	$ide=$_POST['ide'];
}
if(isset($_POST['gru'])){
	$gru=$_POST['gru'];
}
if(isset($_POST['doc'])){
	$doc=$_POST['doc'];
}
if(isset($_POST['asi'])){
	$asi=$_POST['asi'];
}
if(isset($_POST['estu'])){
	$estu=$_POST['estu'];
}
if(isset($_POST['fec'])){
	$fec=$_POST['fec'];
}
if(isset($_POST['esta'])){
	$esta=$_POST['esta'];
}
if(isset($_POST['pro'])){
	$pro=$_POST['pro'];
}
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
if(isset($_GET['sh'])){
	$sch=$_GET['sh'];
}
if($vere!=""){
$consulta=mysql_query("select * from estudiante_as where idEstu='".$vere."' order by apellEstu ASC");
}else if($estu!=""){
	$consulta=mysql_query("select * from estudiante_as where nomEstu like '%".$estu."%' or apellEstu like '%".$estu."%' order by apellEstu ASC");
}else if($ide!=""){
	$consulta=mysql_query("select * from estudiante_as where idEstu='".$ide."' order by apellEstu ASC");

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
   <form action='filtro.php' method='post'>
   
 <tr>
    <td>Identificacion</td><td><input name='ide' type='text' /></td>
	<td>Docente</td><td><input name='doc' type='text' /></td>
	<td>Fecha</td><td><input name='fec' type='text' /></td>
	</tr>
	<tr>
    
	<td>Estudiante</td><td><input name='estu' type='text' /></td>
	<td>Asignatura</td><td><input name='asi' type='text' /></td>
	<td>Estado</td><td><input name='esta' type='text' /></td>
	
    </tr>
	<tr>
    <td>Grupo</td><td><input name='gru' type='text' /></td>
	<td>Programa o Curso</td><td><input name='pro' type='text' /></td>
	
	<td></td><td></td>
    </tr>
	<tr>
	<td colspan='6'><center><input type='submit' value='Buscar'/></center></td>
	
</tr>

	
 
  </form>
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
	<th>Docente</th>
	<th>Programa</th>
	<th>Fecha</th>
	<th>Estado</th>
    
	
	
	<th>
  
	</th>
	<th>
	</th>
 
  </tr>
  </thead>
 


";

while($fi2=mysql_fetch_array($consulta)){
	if($gru!=""){
		$consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$gru."'");
	}else if($asi!=""){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codMate IN(select codMate from asignatura_as where nomMate like '%".$asi."%'))");
	}else if($doc!=""){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where idDoc IN(select idDoc from docente_as where nomDoc like '%".$doc."%' or apeDoc like '%".$doc."%'))");
	
	}else if($pro!=""){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo IN(select codGrupo from grupo_as where codCurso IN(select codCurso from curso_as where nomCurso like '%".$pro."%'))");
		
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
		$consulta7=mysql_query("select * from curso_as where codCurso='".$c[2]."'");
		$fi7=mysql_fetch_array($consulta7);
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
					<td>".$fi4[2]." ".$fi4[1]."</td>	
					<td>".$fi7[1]."</td>
					<td>".$fi[2]."</td>
					<td>".$estado."</td>
			

					<td><a href='formupdateasis.php?id=".$fi[0]."&nombre=".$fi2[2]." ".$fi2[1]."&mate=".$fi3[0]."&gru=".$fi[1]."&doce=".$fi4[2]." ".$fi4[1]."&fec=".$fi[2]."&asi=".$fi[3]."'>Actualizar</a></td>
	
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
	
}else
{
echo"Acceso Restringido para este usurio";	
}
?>
