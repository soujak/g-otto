<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/template/head.jspf"%>

<body>
<jsp:useBean id="DataExchange"
	class="g8.bookshop.presentation.content.manager.DataExchange"
	scope="session" />

<div id="top">
<h1>G8 Cart</h1>
</div>

<%@include file="/template/cart_menu.jspf"%>

<jsp:getProperty property="shoppingcart" name="DataExchange" />

<%@include file="/template/bottom.jspf"%>

</body>
</html>