<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="gestionuser.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="gestionuser.php" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>
<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php
include "conexion.php";
$user="";
$pass="";
$nom="";
$ape="";
$per="";

if(isset($_GET['usuario'])){
$user=$_GET['usuario'];
}
if(isset($_GET['contrasena'])){
$pass=$_GET['contrasena'];
}
if(isset($_GET['nombre'])){
$nom = $_GET['nombre'];
}
if(isset($_GET['apellido'])){
$ape=$_GET['apellido'];
}
if(isset($_GET['perfil'])){
$per=$_GET['perfil'];
}


if($user!="" && $pass=="" && $nom!="" && $ape!="" && $per==""){

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

";
	echo"
	<tbody>
  <tr class='alt'>
  <form action='updateuser.php?user2=".$user."' method='post'>
    <td><input name='usuario' type='text' value='".$user."'/></td>
    <td><input name='nombre' type='text' value='".$nom."' /></td>
    <td><input name='apellido' type='text' value='".$ape."' /></td>
   
	<td><input type='submit' value='Actualizar'/></td>
	</form>
  </tr>
  </tbody>
</table></div>
<br/>
<br/>
<br/>

";

}else if($user!="" && $pass!="" && $nom!="" && $ape!="" && $per!=""){
echo"
<div class='datagrid'>
<table width='100%' border='1' cellspacing='2' cellpadding='2'>
<thead>
  <tr>
    <th>Identificacion (Usuario)</th>
    <th>Contrasena</th>
    <th>Nombre</th>
    <th>Apellido</th>
	<th>Perfil</th>
	<th></th>
    
  </tr>
</thead>

";

	echo"
	<tbody>
  <tr class='alt'>
  <form action='updateuser.php?user2=".$user."' method='post'>
    <td><input name='usuario' type='text' value='".$user."'/></td>
    <td><input name='contrasena' type='text' value='".$pass."' /></td>
    <td><input name='nombre' type='text' value='".$nom."' /></td>
    <td><input name='apellido' type='text' value='".$ape."' /></td>
    <td><select name='perfil'>";
	if($per==1){
	echo"
	<option selected='selected' value='1'>Administrador</option>
	<option  value='2'>Docente</option>
	<option  value='3'>Administrador Docente</option>
	
	</select></td>
	<td><input type='submit' value='Actualizar'/></td>
	</form>
  </tr>
  </tbody>
</table></div>

";
	}
	if($per==2){
	echo"
	<option value='1'>Administrador</option>
	<option selected='selected' value='2'>Docente</option>
	<option  value='3'>Administrador Docente</option>
	
	</select></td>
	<td><input type='submit' value='Actualizar'/></td>
	</form>
  </tr>
 </tbody>
</table></div>
";
	}
	if($per==3){
	echo"
	<option value='1'>Administrador</option>
	<option value='2'>Docente</option>
	<option selected='selected' value='3'>Administrador Docente</option>

	</select></td>
	<td><input type='submit' value='Actualizar'/></td>
	</form>
  </tr>
 </tbody>
</table></div>
";
	}
	if($per==4){
	echo"
	<option value='1'>Administrador</option>
	<option value='2'>Docentes Universotarios</option>
	<option value='3'>Docentes de Escuelas</option>
	<option selected='selected' value='4'>Escritor de Tag</option>
	</select></td>
	<td><input type='submit' value='Actualizar'/></td>
	</form>
  </tr>
 </tbody>
</table></div>

";
	}
	
	echo"<br/>
<br/>
<br/>
";
}

?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>