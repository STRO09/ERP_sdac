<form method="post" action="/RegisterServlet">
		
<table>
<tr>
	<td>Port ID: </td>
	<td><input type="text" name="portid"></td>
</tr>
<tr>
	<td>Password: </td>
	<td><input type="text" name="password"></td>
</tr>
<tr>
	<td>Location: </td>
	<td><input type="text" name="location"></td>
</tr>
<tr>
	<td>Role: </td>
	<td><input type="radio" name="role" value ="Consumer">Consumer</td>
	<td><input type="radio" name="role" value = "Seller">Seller</td>

</tr>
<tr>
	<td><input type="submit" value = "Register"></td>
	<td><input type="reset" value ="Reset"></td>
</tr>
</table>
	</form>