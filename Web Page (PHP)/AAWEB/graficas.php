<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Mi primer ejemplo en Google Charts</title>
</head>
 
<script type="text/javascript" src="https://www.google.com/jsapi"></script> 
<script>
   google.load("visualization", "1", {packages:["corechart"]});
   google.setOnLoadCallback(dibujarGrafico);
   function dibujarGrafico() {
     // Tabla de datos: valores y etiquetas de la gráfica
     var data = google.visualization.arrayToDataTable([
       ['Texto', 'Valor numérico'],
       ['Texto1', 20],
       ['Texto2', 40],
       ['Texto3', 30],
       ['Texto4', 20]    
     ]);
     var options = {
       title: 'Nuestro primer ejemplo con Google Charts',
	   width: 450,//ANCHO
			heigth: 250,//ALTO
			is3D: true,//PROPIEDAD PARA VER SI LA QUEREMOS EN 3D O NO
			backgroundColor:'#EEE'//COLOR DEL FONDO
     }
     // Dibujar el gráfico
     new google.visualization.PieChart( 
     //ColumnChart sería el tipo de gráfico a dibujar
       document.getElementById('GraficoGoogleChart-ejemplo-1')
     ).draw(data, options);
   }
 </script> 
<body>
Comenzando con Google Charts....
<div id="GraficoGoogleChart-ejemplo-1" style="width: 800px; height: 600px">
</div>
</body>
</html>