<?xml version='1.0'?> 
<xsl:stylesheet  
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    version="1.0"> 
    <xsl:import href="fo/docbook.xsl"/> 
    <xsl:include href="commonCustomization.xsl" />
    <xsl:param name="paper.type" select="'A4'"/> 
    <xsl:template match="processing-instruction('hard-pagebreak')">
        <fo:block break-after='page'/>
    </xsl:template>
</xsl:stylesheet>