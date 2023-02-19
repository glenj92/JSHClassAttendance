<?php
//acceso al servidor, usuario y contraseña
$db=mysql_connect("localhost","root","");  
//si no conecta al servidor
if(!$db){
die('no a podido conectar a la base de datos: '.mysql_error());
}

//conectar con la base de datos
mysql_select_db("asistenciasdb",$db);




?>