<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="gestionmateria.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>
<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$cod="";

$nom="";
$pro="";


if(isset($_GET['codigo'])){
$cod=$_GET['codigo'];
}

if(isset($_GET['nombre'])){
$nom = $_GET['nombre'];
}
if(isset($_GET['pro'])){
$pro = $_GET['pro'];
}
if($cod!="" && $nom!="" && $pro!=""){

echo"
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Codigo de Asignatura</th>

    <th>Nombre</th>
<th>Programa o Curso</th>
	<th></th>
	
    
  </tr>
</thead>

";
	echo"
  <tbody>
  <tr class='alt'>
  <form action='updatemate.php?cod2=".$cod."' method='post'>
    <td><input name='codigo' type='text' value='".$cod."'/></td>
    <td><input name='nombre' type='text' value='".$nom."' /></td>
   <td><input name='progra' type='text' value='".$pro."' /><a href='selccionprogramaup.php?codigo=".$cod."&nombre=".$nom."&pro=".$pro."'>Curso</a></td>
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