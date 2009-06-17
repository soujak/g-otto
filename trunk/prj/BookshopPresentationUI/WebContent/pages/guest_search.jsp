<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/template/head.jspf"%>

<body>
<jsp:useBean id="DataExchange"
	class="g8.bookshop.presentation.content.manager.DataExchange"
	scope="session" />
	
<%@include file="/template/top.jspf"%>

<%@include file="/template/guest_menu.jspf"%>

<p class="message"><jsp:getProperty property="message" name="DataExchange" /></p>

<%@ include file="/template/simple_search.jspf"%>

<div id="results_message">
    <p><jsp:getProperty property="resultsMessage" name="DataExchange" /></p>
</div>

<jsp:getProperty property="booklist" name="DataExchange" />

<%@include file="/template/bottom.jspf"%>

</body>
</html>