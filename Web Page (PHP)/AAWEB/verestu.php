<?php
session_start();
include "conexion.php";
$id="";

if(isset($_GET['ide'])){
$id = $_GET['ide'];
}

if($id!=""){
 $query_search = "select * from estudiante_as where idEstu = '".$id."'";

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
Estudiante: <?php echo $cons[1]." ".$cons[2];    ?>
<br />
<br />
<a href="grupoestuasis.php">GRUPOS A LOS QUE A ASISTIDO</a>

<?php }else{
	header("Location:gestionestu.php"); 
      }

?>
</body>
</html>