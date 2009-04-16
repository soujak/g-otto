<?xml version='1.0'?> 
<xsl:stylesheet  
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  version="1.0"> 
    <xsl:import href="fo/docbook.xsl"/> 
    <xsl:include href="customizedToc.xsl" />
    <xsl:param name="paper.type" select="'A4'"/> 
</xsl:stylesheet>