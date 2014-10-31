<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	request.getSession(false).invalidate(); 
	response.sendRedirect("./");
%>