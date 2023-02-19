<?php
$string="";
$string = '{"cols": [
{"id":"","label":"Topping","pattern":"","type":"string"},
{"id":"","label":"Slices","pattern":"","type":"number"}],';
//COMO SON 2 'COLUMNAS' UNA DE NOMBRE Y OTRA PARA LOS DATOS
//SE DECLARAN 2 COLUMNAS, LO IMPORTANTE ES EL type
//YA QUE CON ESO DECIMOS SI VA A SER UN DATO STRING O NUMERICO
//AHORA DECLARAMOS LAS FILAS
//ESTA PRIMERA ES DEL TYPO STRING, ES LA QUE TENDRA LOS NOMBRES
//LOS NOMBRES VAN ENTRE COMILLAS, POR SER STRING
//AHORA DEL OTRO LADO VAN LAS CANTIDADES, COMO SON NUMERICAS
//NO NECESITAN IR ENTRE COMILLAS
$string.='"rows": ['.
        '{"c":[{"v":"Juan","f":null},{"v":100,"f":null}]},'.
        '{"c":[{"v":"Paco","f":null},{"v":200,"f":null}]},'.
        '{"c":[{"v":"Pedro","f":null},{"v":300,"f":null}]},'.
        '{"c":[{"v":"Luis","f":null},{"v":500,"f":null}]}]}';
		//POR ULTIMO HACEMOS UN ECHO PARA QUE SE LEAN LOS DATOS Y LOS RECIBAMOS CON JSON
echo $string;
?>