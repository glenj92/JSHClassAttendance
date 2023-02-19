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

	if(isset($_SESSION['scodc'])){
unset($_SESSION['scodc']);
}

	if(isset($_SESSION['scodm'])){
unset($_SESSION['scodm']);
}
	if(isset($_SESSION['siddoc'])){
unset($_SESSION['siddoc']);
}


$_SESSION['pag']="formupgru";
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$scodmup="";
$scodcup="";
$siddocup="";

if($_SESSION['perfilUser']==1||$_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
	
		
	if(isset($_GET['scodmup'])){
$_SESSION['scodmup']=$_GET['scodmup'];
}
	
	if(isset($_SESSION['scodmup'])){
$scodmup=$_SESSION['scodmup'];
}
	if(isset($_GET['scodcup'])){
$_SESSION['scodcup']=$_GET['scodcup'];
}
	
	if(isset($_SESSION['scodcup'])){
$scodcup=$_SESSION['scodcup'];
}
	if(isset($_GET['siddocup'])){
$_SESSION['siddocup']=$_GET['siddocup'];
}
	
	if(isset($_SESSION['siddocup'])){
$siddocup=$_SESSION['siddocup'];
}



$idd="";
$codc="";
$cod="";
$codm="";
$ano="";
$per="";
$jor="";




if(isset($_GET['cod'])){
$cod=$_GET['cod'];
}
if(isset($_GET['cur'])){
$codc=$_GET['cur'];
}
if(isset($_GET['mate'])){
$codm = $_GET['mate'];
}
if(isset($_GET['ano'])){
$ano=$_GET['ano'];
}
if(isset($_GET['per'])){
$per=$_GET['per'];
}
if(isset($_GET['jor'])){
$jor=$_GET['jor'];
}
if(isset($_GET['doc'])){
$idd=$_GET['doc'];
}

if(($idd!="" && $jor!="" && $per!="" && $ano!="" && $codm!="" && $codc!="" && $cod!="")||$scodcup!="" ||$scodmup!=""||$siddocup!=""){

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

";
	echo"
	<tbody>
  <tr class='alt'>
  <form action='updategru.php?cod2=".$cod."&idd=".$idd."' method='post'>
    <td>".$cod."</td>";
	if($scodcup!=""){
		echo"
	<td><input name='cur' type='text' value='".$scodcup."'/><a href='selccioncurup.php?cod=".$cod."&cur=".$codc."&mate=".$codm."&ano=".$ano."&per=".$per."&jor=".$jor."&doc=".$idd."'>Curso</a></td>";
	}else{
	echo"
	<td><input name='cur' type='text' value='".$codc."'/><a href='selccioncurup.php?cod=".$cod."&cur=".$codc."&mate=".$codm."&ano=".$ano."&per=".$per."&jor=".$jor."&doc=".$idd."'>Curso</a></td>";
	}
	
	if($scodmup!=""){
		echo"
	<td><input name='mate' type='text' value='".$scodmup."'/><a href='selccionmateup.php?cod=".$cod."&cur=".$codc."&mate=".$codm."&ano=".$ano."&per=".$per."&jor=".$jor."&doc=".$idd."'>Materia</a></td>";
	}else{
	echo"
	<td><input name='mate' type='text' value='".$codm."'/><a href='selccionmateup.php?cod=".$cod."&cur=".$codc."&mate=".$codm."&ano=".$ano."&per=".$per."&jor=".$jor."&doc=".$idd."'>Materia</a></td>";
	}
	
	echo"
    <td><input name='ano' type='text' value='".date('Y')."' /></td>
	<td><select name='per'>";
	if($_SESSION['perfilUser']==1||$_SESSION['perfilUser']==3){
	if($per==1){
	echo"
	<option selected='selected' value='1'>1</option>
	<option value='2'>2</option>
	<option value='3'>3</option>
	<option value='4'>4</option>";
	}
	if($per==2){
	echo"
	<option value='1'>1</option>
	<option selected='selected' value='2'>2</option>
	<option value='3'>3</option>
	<option value='4'>4</option>";
	}
	if($per==3){
	echo"
	<option  value='1'>1</option>
	<option value='2'>2</option>
	<option selected='selected' value='3'>3</option>
	<option value='4'>4</option>";
	}
	if($per==4){
	echo"
	<option value='1'>1</option>
	<option value='2'>2</option>
	<option value='3'>3</option>
	<option selected='selected' value='4'>4</option>";
	}
	echo"
	</select></td>
    <td><select name='jor'>";
	
	if($jor=="MANANA"){
	echo"
	<option selected='selected' value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>
	<option value='UNICA'>UNICA</option>";
	}
	if($jor=="TARDE"){
	echo"
	<option value='MANANA'>MAÑANA</option>
	<option selected='selected' value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>
	<option value='UNICA'>UNICA</option>";
	}
	if($jor=="NOCHE"){
	echo"
	<option value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option selected='selected' value='NOCHE'>NOCHE</option>
	<option value='UNICA'>UNICA</option>";
	}
	if($jor=="UNICA"){
	echo"
	<option value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>
	<option selected='selected' value='UNICA'>UNICA</option>";
	}
	}else if($_SESSION['perfilUser']==2){
	if($per==1){
	echo"
	<option selected='selected' value='1'>1</option>
	<option value='2'>2</option>";
	}
	if($per==2){
	echo"
	<option value='1'>1</option>
	<option selected='selected' value='2'>2</option>";
	}
	echo"
	</select></td>
    <td><select name='jor'>";
	
	if($jor=="MANANA"){
	echo"
	<option selected='selected' value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>";
	}
	if($jor=="TARDE"){
	echo"
	<option value='MANANA'>MAÑANA</option>
	<option selected='selected' value='TARDE'>TARDE</option>
	<option value='NOCHE'>NOCHE</option>";
	}
	if($jor=="NOCHE"){
	echo"
	<option value='MANANA'>MAÑANA</option>
	<option value='TARDE'>TARDE</option>
	<option selected='selected' value='NOCHE'>NOCHE</option>";
	}
	
	}
	echo"
	</select></td>";
	if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){
	if($siddocup!=""){
		echo"
	<td>".$siddocup."</td>";
	}else{
	echo"
	<td>".$idd."</td>";
	}
	} else if($_SESSION['perfilUser']==1){
		if($siddocup!=""){
		echo"
	<td><input name='doc' type='text' value='".$siddocup."'/><a href='selcciondocup.php?cod=".$cod."&cur=".$codc."&mate=".$codm."&ano=".$ano."&per=".$per."&jor=".$jor."&doc=".$idd."'>Docente</a></td>";
	}else{
	echo"
	<td><input name='doc' type='text' value='".$idd."'/><a href='selcciondocup.php?cod=".$cod."&cur=".$codc."&mate=".$codm."&ano=".$ano."&per=".$per."&jor=".$jor."&doc=".$idd."'>Docente</a></td>";
	}
	}
	echo"
   
	<td><input type='submit' value='Actualizar'/></td>
	</form>
  </tr>
  </tbody>
</table></div>
<br/>
<br/>
<br/>

";

}
}else{
echo"Acceso Restringido para este usurio";	
}
?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>