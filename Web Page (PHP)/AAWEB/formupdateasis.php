<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="gestionasis.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="gestionasis.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>
<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$cod="";

$nom="";
$gru="";
$fe="";
$asi="";
$mate="";
$doc ="";
$pro="";

if(isset($_GET['id'])){
$cod=$_GET['id'];
}

if(isset($_GET['nombre'])){
$nom = $_GET['nombre'];
}
if(isset($_GET['gru'])){
$gru = $_GET['gru'];
}
if(isset($_GET['fec'])){
$fe = $_GET['fec'];
}
if(isset($_GET['asi'])){
$asi = $_GET['asi'];
}
if(isset($_GET['mate'])){
$mate = $_GET['mate'];
}
if(isset($_GET['doce'])){
$doc = $_GET['doce'];
}
if(isset($_GET['prog'])){
$pro = $_GET['prog'];
}

if($cod!="" && $nom!="" && $gru!="" && $fe!="" && $asi!="" && $mate!="" && $doc!="" && $pro!=""){

echo"
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

	
    
  </tr>
  </thead>


";
	echo"
	<tbody>
  <tr class='alt'>
  ";
  ?>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
   <?php
  echo"
  <form action='updateasis.php?cod2=".$cod."&gru=".$gru."&fecha=".$fe."' method='post'>
    <td>".$cod."</td>
    <td>".$nom."</td>
	<td>".$mate."</td>
	 <td>".$gru."</td>
	 <td>".$pro."</td>
	 <td>".$doc."</td>
	  <td><input name='fec' type='text' value='".$fe."' id='fec' /></td>
	  <td><select name='estado'>";
	  if($asi==1){
echo"	  
	<option value='0'>Asistio</option>
	<option selected='selected' value='1'>Excusado</option>";
	  }
	   if($asi==0){
echo"	  
	<option selected='selected' value='0'>Asistio</option>
	<option  value='1'>Excusado</option>";
	  }
	
echo"	
	</select></td>
	   
   
	<td><input type='submit' id='boton' value='Actualizar'/></td>
	</form>
    ";
  ?>
      <script>
$(function(){
	$( "#fec" ).datepicker({
		currentText: "Now",
		dateFormat: "yy-mm-dd",
		appendText: "(yyyy-mm-dd)"
	});
	$("submit").button();
});
</script>
     <?php
  echo"
  </tr>
  </tbody>
</table></div>
<br/>
<br/>
<br/>

";

}

?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>