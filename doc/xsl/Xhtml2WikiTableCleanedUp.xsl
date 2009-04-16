<?xml version='1.0'?> 
<xsl:stylesheet  
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  version="1.0"		
    xmlns:exsl="http://exslt.org/common"
    xmlns:str="http://exslt.org/strings"
    xmlns:h="http://www.w3.org/1999/xhtml"> 
 
    <xsl:template match="//h:html|//h:body|//h:div[@class='article']">
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="//h:head|//h:meta|//h:hr|//h:a[@shape='rect']"/>
    
    <xsl:template match="//h:div[@class='titlepage']/h:div/h:div/h:h2[@class='title'][not(normalize-space(.))]"/>
    
    <xsl:template match="//h:div[@class='table']/h:p[@class='title']">
        <br/><br/>
    </xsl:template>
    
    <xsl:template match="/|comment()|processing-instruction()">
        <xsl:copy>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="*">
        <xsl:element name="{local-name()}">
            <xsl:apply-templates select="@*|node()"/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="@*">
        <xsl:attribute name="{local-name()}">
            <xsl:value-of select="."/>
        </xsl:attribute>
    </xsl:template>
    
    <xsl:template match="text()">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
    
   <!--<xsl:template match="*|@*|text()">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>-->
    
</xsl:stylesheet>