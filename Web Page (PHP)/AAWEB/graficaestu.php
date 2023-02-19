<?php include "menu.php";   ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">

<?php
if(isset($_GET['asis'])){
	$asis=$_GET['asis'];
}
if(isset($_GET['fall'])){
	$fall=$_GET['fall'];
}
if(isset($_GET['exc'])){
	$exc=$_GET['exc'];
}
if(isset($_GET['id'])){
	$id=$_GET['id'];
}
if(isset($_GET['nombre'])){
	$nom=$_GET['nombre'];
}
if(isset($_GET['gru'])){
	$gru=$_GET['gru'];
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Mi primer ejemplo en Google Charts</title>
</head>
 
<script type="text/javascript" src="https://www.google.com/jsapi"></script> 
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript">


   google.load("visualization", "1", {packages:["corechart"]});
   google.setOnLoadCallback(dibujarGrafico);
   function dibujarGrafico() {
     // Tabla de datos: valores y etiquetas de la gráfica
     var data = google.visualization.arrayToDataTable([
       ['Texto', 'Valor numérico'],
       ['Asistencia', <?php echo $asis;?>],
       ['Fallas', <?php echo $fall;?>],
       ['Excusa', <?php echo $exc;?>]
        
     ]);
     var options = {
       title: '<?php echo $nom;?>',
	   width: 1300,//ANCHO
			heigth: 600,//ALTO
			is3D: true,//PROPIEDAD PARA VER SI LA QUEREMOS EN 3D O NO
		//	backgroundColor:'#EEE'//COLOR DEL FONDO
     };
     // Dibujar el gráfico
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      chart.draw(data, options);
   }
 </script> 
<body>


<a href='principaladmin.php'>Inicio</a>

<br/>
<center>
<div id="chart_div" style="width:auto"></div>
</center>
<br/>
<br/>
<a href='vergrupo.php?vergru=<?php echo $gru;?>'>Atras</a>

</body>
</html>