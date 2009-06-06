<%@ include file="/template/top.jspf" %>

<%@ include file="/template/simple_search.jspf" %>

<jsp:useBean id="ContentManager" class="g8.bookshop.presentation.content.manager.ContentManager" scope="session" />
<jsp:getProperty property="booklist" name="ContentManager"/>

<%@ include file="/template/bottom.jspf" %>