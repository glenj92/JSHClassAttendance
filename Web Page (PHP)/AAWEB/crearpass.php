<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>

<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; margin-bottom:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<?php } ?>
<?php
header( 'Content-Type: text/html;charset=utf-8' );  //permite mostrar tildes y ñ en php

//$traslado=$_SESSION['traslado'];
//obtener las variables
//$pass=""&&$user!=""&&$nom!=""&&$ape!=""
$user="";
if(isset($_POST['usuario'])){
	$user=$_POST['usuario'];
}
$userd="";
if(isset($_POST['usuariod'])){
	$userd=$_POST['usuariod'];
}
$pass=$_POST['contrasena'];
$pass2=$_POST['contrasena2'];
$per=$_POST['perfil'];
//if($traslado!="formularioregistrar.php"){


include "conexion.php";
if($pass!=""&&$user!=""&&$pass2!=""&&$per!=""){
	
	if($pass==$pass2){

	$resul1=mysql_query("insert into acceso_as values('".$user."','".$pass."','".$per."')");
if($resul1){
	echo"Exito al guardar contraseña";
	?>
	<center>
  <a href="gestionuser.php" class="button button-border-action button-circle" style="margin-top:5px; margin-bottom:5px;">Continuar</a>
    
    </center>
<?php

	}
	}else{
	echo"<center> Contraseñas no coinciden <br/><br/>
	<a href='formcrearpass.php?user=".$user."' class='button button-border-caution'>Intentar nuevamente</a>
	</center>";
	}
}else if($pass!=""&&$userd!=""&&$pass2!=""&&$per!=""){
	
	if($pass==$pass2){

	$resul1=mysql_query("insert into acceso_as values('".$userd."','".$pass."','".$per."')");
if($resul1){
	echo"Exito al guardar contraseña";
	?>
	<center>
  <a href="gestiondocente.php" class="button button-border-action button-circle" style="margin-top:5px; margin-bottom:5px;">Continuar</a>
    
    </center>
<?php

	}
	}else{
	echo"<center> Contraseñas no coinciden <br/><br/>
	<a href='formcrearpass.php?userd=".$userd."' class='button button-border-caution'>Intentar nuevamente</a>
	</center>";
	}
}else if($per==""&&$pass==""&&$pass2==""){
if($user!=""){
	echo"<center> No se permiten datos en Blanco<br/><br/>
	<a href='formcrearpass.php?user=".$user."' class='button button-border-caution'>Intentar nuevamente</a>
	</center>";
	}
	if($userd!=""){
	echo"<center> No se permiten datos en Blanco<br/><br/>
	<a href='formcrearpass.php?userd=".$userd."' class='button button-border-caution'>Intentar nuevamente</a> 
	</center>";
	}
}else if($per==""){
	if($user!=""){
	echo"<center> Debe seleccionar un perfil<br/><br/>
	<a href='formcrearpass.php?user=".$user."' class='button button-border-caution'>Intentar nuevamente</a> 
	</center>";
	}
	if($userd!=""){
	echo"<center> Debe seleccionar un perfil<br/><br/>
	<a href='formcrearpass.php?userd=".$userd."' class='button button-border-caution'>Intentar nuevamente</a>
	</center>";
	}
}else{
	header ("Location:formcrearpass.php"); 
}




?>
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>