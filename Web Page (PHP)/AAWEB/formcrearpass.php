<?php session_start();
$user="";
$user2="";
$user3="";
$user4="";

if(isset($_GET['usuario'])){
$user=$_GET['usuario'];
}
if(isset($_GET['usuariod'])){
$user2=$_GET['usuariod'];
}
if(isset($_GET['user'])){
$user3=$_GET['user'];
}
if(isset($_GET['userd'])){
$user4=$_GET['userd'];
}

if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
<?php if($user!=""||$user3!=""){ ?>
 <center>
  <a href="gestionuser.php" class="button button-border-highlight button-border" style="margin-top:5px; margin-bottom:5px;">Omitir</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }
 if($user2!=""||$user4!=""){ ?>
 <center>
  <a href="gestiondocente.php" class="button button-border-highlight button-border" style="margin-top:5px; margin-bottom:5px;">Omitir</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

<?php }}?>
<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";

if($user!=""||$user2!=""||$user3!=""||$user4!=""){

echo"
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Identificacion</th>

    <th>Contrasena</th>
    <th>Confirmar Contrasena</th>
	<th></th>
	<th></th>
	
    
  </tr>

</thead>
";
	echo"
  <tbody>
  <tr class='alt'>
  <form action='crearpass.php' method='post'>";
  if($user!=""||$user3!=""){
    echo"<td><input name='usuario' type='text' value='".$user.$user3."'/></td>";
  }
  if($user2!=""||$user4!=""){
	   echo"<td><input name='usuariod' type='text' value='".$user2.$user4."'/></td>";
  }
  /* if($user3!=""){
	   echo"<td><input name='usuariod' type='text' value='".$user3."'/></td>";
  }*/
	echo"
    <td><input name='contrasena' type='password' /></td>
    <td><input name='contrasena2' type='password'/></td>
	 <td><select name='perfil'>
	 <option selected='selected' value=''>Ninguno</option>
	 <option value='1'>Administrador</option>
	<option value='2'>Docente</option>
	<option value='3'>Administrador Docente</option>
	
	</select></td>
	<td><input type='submit' value='Guardar Contrasena'/></td>
	</form>
  </tr>
   </tbody>
</table></div>
<br/>
<br/>

  
";

}else{
header ("Location:gestionuser.php"); 
}

?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>