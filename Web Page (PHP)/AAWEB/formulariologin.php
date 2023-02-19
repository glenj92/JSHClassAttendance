<html>
<head>
<link rel="StyleSheet" href="loginStyle.css" type="text/css">
<script language="javascript">
function validarCampo(form){
if(form.usuario.value==""||form.contrasena.value==""){
alert("No se admiten datos en blanco");
}
}

</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="login.php">
  <p>
    <label for="usuario">Usuario:</label>
  <input type="text" name="user" id="usuario" />
  </p>
  <p>
    <label for="contrasena">Contrasena:</label>
    <input type="password" name="pass" id="contrasena" />
  </p>
  <p>
    <input type="submit" name="button" id="button" value="Login" onClick="validarCampo(this.form)" />
  </p>
</form>


</body>
</html>



