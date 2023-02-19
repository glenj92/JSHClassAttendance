<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>

<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<?php } ?>
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
if(isset($_GET['mat'])){
	$mat=$_GET['mat'];
}
?>

<html>
  <head>
    <!--Load the Ajax API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript">

    // Load the Visualization API and the piechart package.
    google.load('visualization', '1', {'packages':['corechart']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);

    function drawChart() {

      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.arrayToDataTable([
       ['Texto', 'Valor numérico'],
       ['Asistencia', <?php echo $asis;?>],
       ['Fallas', <?php echo $fall;?>],
       ['Excusa', <?php echo $exc;?>]
        
     ]);
      var options = {
           title: 'ASIGNATURA: <?=$mat?>\nGRUPO: <?=$gru?>\n\n<?=$nom?>',
          is3D: 'true',
          width: 1300,
          height: 450,
		  backgroundColor:'#EEE'
        };
      // Instantiate and draw our chart, passing in some options.
      // Do not forget to check your div ID
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }
    </script>
  </head>

  <body>
  <div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>

 <center>
  <a href="vergrupo.php?vergru=<?php echo $gru;?>" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
    <!--this is the div that will hold the pie chart-->
    <center>
    <div id="chart_div" style="border: 1px solid #d3d3d3;
border-radius:8px;"></div>
</center>


<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>
  </body>
</html>