<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>SocialAuth Demo</title>
<style>
.sectiontableheader {
	background-color: #C8D7E3;
	color: #293D6B;
	font-size: 8pt;
	font-weight: bold;
	padding: 2px;
}

.sectiontableentry2 {
	background: none repeat scroll 0 0 #F7F7F7;
	padding: 2px;
}

.sectiontableentry1 {
	background: none repeat scroll 0 0 #FFFFF0;
	padding: 2px;
}
</style>

</head>
<body>

	<h2 align="center">Authentication has been successful.</h2>
	<br />
	<div align="center">
		<a href="index.jsp">Back</a>
	</div>
	<br />
	<h3 align="center">Profile Information</h3>
	<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5"
		width="60%" align="center">
		<tr>
			<td><b>Profile Field</b></td>
			<td><b>Value</b></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><c:out value="${profile.email}" /></td>
		</tr>
		<tr>
			<td>First Name:</td>
			<td><c:out value="${profile.firstName}" /></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><c:out value="${profile.lastName}" /></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><c:out value="${profile.country}" /></td>
		</tr>
		<tr>
			<td>Language:</td>
			<td><c:out value="${profile.language}" /></td>
		</tr>
		<tr>
			<td>Full Name:</td>
			<td><c:out value="${profile.fullName}" /></td>
		</tr>
		<tr>
			<td>Display Name:</td>
			<td><c:out value="${profile.displayName}" /></td>
		</tr>
		<tr>
			<td>DOB:</td>
			<td><c:out value="${profile.dob}" /></td>
		</tr>
		<tr>
			<td>Gender:</td>
			<td><c:out value="${profile.gender}" /></td>
		</tr>
		<tr>
			<td>Location:</td>
			<td><c:out value="${profile.location}" /></td>
		</tr>
		<tr>
			<td>Profile Image:</td>
			<td><c:if test="${profile.profileImageURL != null}">
					<img src='<c:out value="${profile.profileImageURL}"/>' />
				</c:if></td>
		</tr>
	</table>

</body>
</html>