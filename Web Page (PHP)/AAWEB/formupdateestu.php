<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="gestionestu.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="gestionestu.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>

<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$cod="";

$nom="";
$ape="";


if(isset($_GET['usuario'])){
$cod=$_GET['usuario'];
}

if(isset($_GET['nombre'])){
$nom = $_GET['nombre'];
}
if(isset($_GET['apellido'])){
$ape = $_GET['apellido'];
}

if($cod!="" && $nom!="" && $ape!=""){

echo"
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Identificacion</th>
<th>Apellido</th>
    <th>Nombre</th>
	<th></th>

	
    
  </tr>
</thead>

";
	echo"
	<tbody>
  <tr class='alt'>
  <form action='updateestu.php?cod2=".$cod."' method='post'>
    <td><input name='codigo' type='text' value='".$cod."'/></td>
	 <td><input name='apellido' type='text' value='".$ape."' /></td>
    <td><input name='nombre' type='text' value='".$nom."' /></td>
   
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

?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>