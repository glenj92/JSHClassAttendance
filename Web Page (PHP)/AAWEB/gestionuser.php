<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <a style="background:#3DAD39; border: 3px solid #6E6E6E; color:#FFF; text-decoration: none; margin:5px; padding:5px;">Usuario sin cuentas</a>
  <center>
  <h1 style="padding-top:0px; margin-top:0px; color:#666;">Gestion de Usuarios</h1></center>
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
//establecer y ejecutar consulta
if(isset($_POST['buscar'])){
	$busqueda=$_POST['buscar'];
}
if(isset($_POST['tipob'])){
	$tipob=$_POST['tipob'];
}

if($busqueda!=""&&$tipob!=""){
	if($tipob=="id"){
	$consulta=mysql_query("select * from docente_as where idDoc='".$busqueda."'");
	}else if($tipob=="n"){
	$consulta=mysql_query("select * from docente_as where nomDoc like '%".$busqueda."%' or apeDoc like '%".$busqueda."%'");
	}

}else{
	$consulta=mysql_query("select * from docente_as");
}


//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
  <thead>
  <tr>
    <th>Identificacion</th>
    <th>Nombre</th>
    <th>Apellido</th>
	<th></th>
  
  </tr>
  </thead>
  <tbody>
   <tr class='alt'>
  <form action='crearuser.php' method='post'>
    <td><input name='usuario' type='text' /></td>
    <td><input name='nombre' type='text' /></td>
    <td><input name='apellido' type='text' /></td>
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
    <th>Identificacion (Usuario)</th>
    <th>Contrasena</th>
    <th>Nombre</th>
    <th>Apellido</th>
	<th>Perfil</th>
	
	<form id='form1' name='form1' method='post' action='gestionuser.php'>
	<th>
  	<p>
  	<input type='text' name='buscar' id='buscar'/>
  	<input type='submit' name='bus' id='bus' value='Buscar' />
  	</p>
	</th>
	<th>
	Tipo de Busqueda
	<select name='tipob'>
	<option value='id'>Identificacion</option>
	<option value='n'>Nombre o Apellido</option>
	</select></th>
	<th></th>
    </form>
  </tr>
  </thead>



";
$cont=1;
while($fi=mysql_fetch_array($consulta)){
	$consulta1=mysql_query("select * from acceso_as where idDoc='".$fi[0]."'");
	$rows = mysql_num_rows($consulta1);
	if($rows --> 0==NULL){
		
					echo"
					<tbody>";
				
					echo"
					 <tr class='altt'>";
					 $cont++;
				
					 echo"
					<td>".$fi[0]." (Sin Usuario)</td>
					<td>(NINGUNA)</td>
					<td>".$fi[1]."</td>
					<td>".$fi[2]."</td>
					<td>(Sin Perfil)</td>
					<td><a href='formupdateuser.php?usuario=".$fi[0]."&nombre=".$fi[1]."&apellido=".$fi[2]."'>Actualizar</a></td>
	
	<td><a href='eliminarusuario.php?usuario=".$fi[0]."'>Eliminar Docente</a></td>
	<td><a href='formcrearpass.php?usuario=".$fi[0]."' id='agc'>Agregar Cuenta</a></td></tr>
	
	</tbody>";
	

	
	
					
			}else{
	while($fi1=mysql_fetch_array($consulta1)){
			if($fi1[2]==1){
				$tperf="Administrador";
			}else if($fi1[2]==2){
				$tperf="Docente";
			}else if($fi1[2]==3){
				$tperf="Administrador Docente";
			}else if($fi1[2]==4){
				$tperf="Escritor de Tags";
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
	<td>".$fi1[0]."</td>
	<td>".$fi1[1]."</td>	
	<td>".$fi[1]."</td>
	<td>".$fi[2]."</td>
	<td>".$tperf."</td>
	
	<td><a href='formupdateuser.php?usuario=".$fi1[0]."&contrasena=".$fi1[1]."&nombre=".$fi[1]."&apellido=".$fi[2]."&perfil=".$fi1[2]."'>Actualizar</a></td>
	<td><a href='eliminarusuario.php?usuario=".$fi[0]."'>Eliminar Docente</a></td>
	<td><a href='eliminarcuenta.php?usuario=".$fi1[0]."' id='elic'>Eliminar Cuenta</a></td>
	
	
	</tr>
	</tbody>
	";
	}
			}
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