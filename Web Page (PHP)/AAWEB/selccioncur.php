<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 
  
  <center>
  <a href="gestiongru.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
  <center>
  <a href="gestiongru.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
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
	$consulta=mysql_query("select * from curso_as where codCurso='".$busqueda."'");
	}else if($tipob=="n"){
	$consulta=mysql_query("select * from curso_as where nomCurso like '%".$busqueda."%'");
	}

}else{
	$consulta=mysql_query("select * from curso_as");
}


//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Codigo de Programa o Curso</th>
	<th>Nombre</th>

    
	
	<form id='form1' name='form1' method='post' action='selccioncur.php'>
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
$cont=0;
while($fi=mysql_fetch_array($consulta)){
	
								
			
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
	
	
	<td><a href='gestiongru.php?scodc=".$fi[0]."'>Seleccionar</a></td>
	<td></td>
	


	</tr>
	</tbody>
	";
	
			
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
	$consulta=mysql_query("select * from curso_as where codCurso='".$busqueda."'");
	}else if($tipob=="n"){
	$consulta=mysql_query("select * from curso_as where nomCurso like '%".$busqueda."%'");
	}

}else{
	$consulta=mysql_query("select * from curso_as");
}


//imprimir consulta
echo"

<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Codigo de Programa o Curso</th>
	<th>Nombre</th>

    

	<form id='form1' name='form1' method='post' action='selccioncur.php'>
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
$cont=0;
while($fi=mysql_fetch_array($consulta)){
	
								
			
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
	
	
	<td><a href='gestiongru.php?scodc=".$fi[0]."'>Seleccionar</a></td>
	
<td></td>


	</tr>
	</tbody>
	";
	
			
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