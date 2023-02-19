<link rel="StyleSheet" href="menustyle.css" type="text/css">
<?php
session_start();
if($_SESSION['perfilUser']==1){
	if(isset($_SESSION['scodc'])){
unset($_SESSION['scodc']);
}

	if(isset($_SESSION['scodm'])){
unset($_SESSION['scodm']);
}
	if(isset($_SESSION['siddoc'])){
unset($_SESSION['siddoc']);
}
	if(isset($_SESSION['scodcup'])){
unset($_SESSION['scodcup']);
}

	if(isset($_SESSION['scodmup'])){
unset($_SESSION['scodmup']);
}
	if(isset($_SESSION['siddocup'])){
unset($_SESSION['siddocup']);
}


include "conexion.php";
$id="";

if(isset($_SESSION['ideUser'])){
$id = $_SESSION['ideUser'];
}

if($id!=""){
 $query_search = "select * from docente_as where idDoc = '".$id."'";

 $resul=mysql_query($query_search);
 $cons=mysql_fetch_array($resul);
 mysql_free_result($resul);
 


?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documento sin título</title>
</head>

<body>


<?php include "menu.php";   ?>
<div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
<div id="cuerp"  style="border: 1px solid #d3d3d3; border-radius:8px; margin-top:30px; padding-top:5px; padding-bottom:5px;">
<br />

<br />
<center><a class="button glow button-border-royal button-pill"  style="font-size:44px;">Bienvenido(a) Administrador: <?php echo $cons[1]." ".$cons[2];    ?></a></center>


<br />
<br />

<br />
<br />
<br />
<center>
<a href="logout.php" class="button glow button-caution button-circle">CERRAR</a>
</center>
<br />

<?php }else{
	header("Location:logiin.php"); 
      }
}else{
	header("Location:logiin.php"); 
}
?>


</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">
<center><h6 style="font-family:Georgia, 'Times New Roman', Times, serif; color:#FFF;">scaa.com</h6></center>
</div>



</body>
</html>