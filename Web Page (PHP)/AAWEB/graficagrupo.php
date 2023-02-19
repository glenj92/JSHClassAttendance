<?php session_start();
if($_SESSION['perfilUser']==1){   ?>
<?php include "menu.php";   ?>

<?php }else if($_SESSION['perfilUser']==2||$_SESSION['perfilUser']==3){   ?>
<?php include "menuu.php";   ?>
<?php } ?>
<link rel="StyleSheet" href="formStyle.css" type="text/css">

<?php
include"conexion.php";
$vere="";
if(isset($_GET['gru'])){
	$vere=$_GET['gru'];
}

if($vere!=""){
$sth=mysql_query("select * from estudiante_as where idEstu IN(select idEstu from asistencia_as where codGrupo='".$vere."') order by apellEstu ASC");

}

/*
---------------------------
example data: Table (Chart)
--------------------------
weekly_task     percentage
Sleep           30
Watching Movie  40
work            44
*/

$rows = array();
//flag is not needed
$flag = true;
$table = array();
$table['cols'] = array(

    // Labels for your chart, these represent the column titles
    // Note that one column is in "string" format and another one is in "number" format as pie chart only required "numbers" for calculating percentage and string will be used for column title
    array('label' => 'Nombre', 'type' => 'string'),
    array('label' => 'No Asistencias', 'type' => 'number'),
	  array('label' => 'No Fallas', 'type' => 'number'),
	    array('label' => 'No Excusas', 'type' => 'number')

);

$rows = array();

while($fi2=mysql_fetch_array($sth)){
	if($vere!=""){
	  $consulta1=mysql_query("select * from asistencia_as where idEstu='".$fi2[0]."' and codGrupo='".$vere."' group by idEstu");
	}
	while($fi=mysql_fetch_array($consulta1)){
		$consulta2=mysql_query("select codMate,idDoc from grupo_as where codGrupo='".$fi[1]."'");
		$c=mysql_fetch_array($consulta2);
		$consulta3=mysql_query("select nomMate from asignatura_as where codMate='".$c[0]."'");
		$fi3=mysql_fetch_array($consulta3);
	$resul1=mysql_query("select count(codGrupo) from clase_as where codGrupo='".$vere."'");
while($cons1=mysql_fetch_array($resul1)){
	
				  $resul4=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='0'");//cantidad asistencias
				  while($cons4=mysql_fetch_array($resul4)){
					
						
						$resul5=mysql_query("select count(idEstu) from asistencia_as where idEstu='".$fi[0]."' 
				  						and codGrupo='".$vere."' and excusa='1'");
						while($cons5=mysql_fetch_array($resul5)){	
						
						$Nofallas=($cons1[0]-$cons4[0])-$cons5[0];  
						//	  echo $cons3[0].",".$cons3[2].",".$cons3[1].",".$cons4[0].",".$Nofallas.",".$cons5[0].",";
		
//while($r = mysql_fetch_assoc($sth)) {
    $temp = array();
    // the following line will be used to slice the Pie chart
    $temp[] = array('v' => (string) $fi2[2]." ".$fi2[1]); 

    // Values of each slice
    $temp[] = array('v' => (int) $cons4[0]); 
	$temp[] = array('v' => (int) $Nofallas);
	$temp[] = array('v' => (int) $cons5[0]);
    $rows[] = array('c' => $temp);
//}
						}
				  }
}
	}
}



$table['rows'] = $rows;
$jsonTable = json_encode($table);
//echo $jsonTable;
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
      var data = new google.visualization.DataTable(<?=$jsonTable?>);
      var options = {
           title: 'ASIGNATURA: <?=$fi3[0]?> \nGRUPO: <?=$vere?>',
          is3D: 'true',
          width: 1300,
          height: 450,
		  backgroundColor:'#EEE'
        };
      // Instantiate and draw our chart, passing in some options.
      // Do not forget to check your div ID
      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }
    </script>
  </head>

  <body>

   <div id="peP" style="border: 2px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px; background:#33B5E5;">

</div>
 <center>
  <a href="vergrupo.php?vergru=<?php echo $vere;?>" class="button button-border" style="margin-top:5px; margin-bottom:5px;">Atras</a>
    
    </center>
    <!--this is the div that will hold the pie chart-->
   <center>
    <div id="chart_div" style="border: 1px solid #d3d3d3;
border-radius:8px;"></div>
  </center>
  
</div>
<div id="pe" style="border: 3px solid #33B5E5; border-radius:8px; margin-top:10px; padding-top:5px; padding-bottom:5px;">

</div>
  </body>
</html>