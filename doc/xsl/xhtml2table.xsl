<?xml version='1.0'?> 
<xsl:stylesheet  
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  version="1.0"		
    xmlns:exsl="http://exslt.org/common"
    xmlns:str="http://exslt.org/strings"
    xmlns:h="http://www.w3.org/1999/xhtml"> 

    <xsl:template match="//h:div[@class='article']/h:div[@class='section']">
        <div class="article">
            <xsl:copy-of select="."/>
        </div>
    </xsl:template>

    <xsl:template match="//h:div[@class='article']/h:div[@class='toc']"/>

</xsl:stylesheet>
