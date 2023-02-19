<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <h1 style="padding-top:0px; margin-top:0px; color:#666;">Asignaturas</h1></center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php
session_start();
header( 'Content-Type: text/html;charset=utf-8' );

//$traslado=$_SESSION['traslado'];
if($_SESSION['perfilUser']==1){

//echo"BIENVENIDO ".$_SESSION['usuario'];
//$_SESSION['traslado']="gestionusuarios.php";
//conexion
include"conexion.php";
$busqueda="";
$tipob="";
$cod="";
$nom="";
$datocurso="";
if(isset($_GET['codigo'])){
$cod=$_GET['codigo'];
}

if(isset($_GET['nombre'])){
$nom = $_GET['nombre'];
}

	if(isset($_GET['datcurso'])){
$datocurso=$_GET['datcurso'];
}
//establecer y ejecutar consulta
if(isset($_POST['buscar'])){
	$busqueda=$_POST['buscar'];
}
if(isset($_POST['tipob'])){
	$tipob=$_POST['tipob'];
}

if($busqueda!=""&&$tipob!=""){
	if($tipob=="id"){
	$consulta=mysql_query("select * from asignatura_as where codMate='".$busqueda."'");
	}else if($tipob=="n"){
	$consulta=mysql_query("select * from asignatura_as where nomMate like '%".$busqueda."%'");
	}

}else{
	$consulta=mysql_query("select * from asignatura_as");
}


//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
  <th>Programa o Curso</th>
    <th>Codigo de Asignatura</th>
	<th>Nombre</th>
	

   
	<th></th>

  </tr>
  </thead>
  <tbody>
   <tr class='alt'>
  <form action='crearmateria.php' method='post'>
  <td><input name='progra' type='text' value='".$datocurso."' /><a href='selccionprograma.php?codigo=".$cod."&nombre=".$nom."'>Curso</a></td>
    <td><input name='codigo' type='text' value='".$cod."' /></td>
    <td><input name='nombre' type='text' value='".$nom."'/></td>
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
    <th>Codigo de Asignatura</th>
	<th>Nombre</th>
	<th>Programa o Curso</th>


	<form id='form1' name='form1' method='post' action='gestionmateria.php'>
  <th>
  	<p>
  	<input type='text' name='buscar' id='buscar'/>
  	<input type='submit' name='bus' id='bus' value='Buscar' />
  	</p>
	</th>
	<th>
	Tipo de Busqueda
	<select name='tipob'>
	<option value='id'>Codigo</option>
	<option value='n'>Nombre</option>
	</select></th>
    </form>
  </tr>
  </thead>
   


";
$cont=1;
while($fi=mysql_fetch_array($consulta)){
	$querycur=mysql_query("select nomCurso from curso_as where codCurso='".$fi[2]."'");
	$fiquery=mysql_fetch_array($querycur);
						
			
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
	<td>".$fi[1]."</td>	
	<td>".$fiquery[0]."</td>	

	
	<td><a href='formupdatemate.php?codigo=".$fi[0]."&nombre=".$fi[1]."&pro=".$fi[2]."'>Actualizar</a></td>
	
	<td><a href='eliminarmateria.php?codigo=".$fi[0]."' id='elic'>Eliminar Asignatura</a></td>

	
	
	</tr>
	</tbody>
	";
	
			
}

//añadir un registro
echo"
 
</table></div>
";
}
else
{
echo"Acceso Restringido para este usurio";	
}
?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>