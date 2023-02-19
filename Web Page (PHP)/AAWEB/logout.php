<?PHP
   session_start ();
?>
<HTML LANG="es">
<HEAD>
<TITLE>Desconectar</TITLE>
<LINK REL="stylesheet" TYPE="text/css" HREF="estilo.css">

</HEAD>
<BODY>

<?PHP
   if (isset($_SESSION["ideUser"]))
   {
      session_destroy ();
      print ("<BR><BR><P ALIGN='CENTER'>Conexión finalizada</P>\n");
      print ("<P ALIGN='CENTER'>[ <A HREF='logiin.php'>Conectar</A> ]</P>\n");
   }
   else
   {
      print ("<BR><BR>\n");
      print ("<P ALIGN='CENTER'>No existe una conexión activa</P>\n");
      print ("<P ALIGN='CENTER'>[ <A HREF='logiin.php'>Conectar</A> ]</P>\n");
   }
?>

</BODY>
</HTML>
