<html>
<head>
<title>SocialAuth Demo</title>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script>
         function gotoHomePage(){
    	
    	var userName = $('#userName').val();
    	var password = $('#password').val();
    	
    	if(userName.length == 0 && userName == ''){
    		
    		alert("UserName can not be blank");
    		$('#userName').focus();
    		return false;
    	}
    	
		if(password.length == 0 && password == ''){
    		
    		alert("Password can not be blank");
    		$('#password').focus();
    		return false;
    	}
    	
    	var url = 'home.do?userName='+userName+ '&password=' + password;
    	location.href = url;
    }
         </script>
</head>
<body>
	<div align="center" style="color: red">${error}</div>
	<table cellpadding="10" cellspacing="10" align="center" border="2">
		<tr>
			<td colspan="8"><h3 align="center">Welcome to Social Auth
					Demo</h3></td>
		</tr>
		<tr>
			<td align="center">UserName:<input type="text" name="userName"
				id="userName"></td>
		</tr>
		<tr>
			<td align="center">Password:<input type="password"
				name="password" id="password"></td>
		</tr>
		<tr>
			<td align="center"><input type="button" name="submit"
				id="submit" value="submit" onclick="gotoHomePage();"> <input
				type="button" name="submit" id="submit" value="reset"></td>
		</tr>

		<tr>
			<td colspan="8"><p align="center">or Login with:</p></td>
		</tr>
		<tr>
			<td><a href="socialauth.do?id=facebook"> <img
					src="images/facebook_icon.png" alt="Facebook" title="Facebook"
					border="0" />
			</a></td>
			<td><a href="socialauth.do?id=twitter"> <img
					src="images/twitter_icon.png" alt="Twitter" title="Twitter"
					border="0" />
			</a></td>
			<td><a href="socialauth.do?id=google"> <img
					src="images/gmail-icon.png" alt="Gmail" title="Gmail" border="0" />
			</a></td>
			<td><a href="socialauth.do?id=linkedin"> <img
					src="images/linkedin_icon.png" alt="Linked In" title="Linked In"
					border="0" />
			</a></td>

		</tr>

	</table>

</body>
</html>