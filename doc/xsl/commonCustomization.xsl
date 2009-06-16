<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:param name="generate.toc"> appendix nop article nop,title book
        toc,title,figure,table,example,equation chapter nop part nop preface nop qandadiv nop
        qandaset nop reference toc,title section nop set toc </xsl:param>
    <xsl:param name="glossary.sort" select="1"/>
    
    <xsl:param name="appendix.autolabel" select="'A'"/>
    <xsl:param name="chapter.autolabel" select="1"/>
    <xsl:param name="part.autolabel" select="'I'"/>
    <xsl:param name="reference.autolabel" select="'I'"/>
    <xsl:param name="section.autolabel" select="1"/>
    <xsl:param name="section.autolabel.max.depth" select="2"/>
    <xsl:param name="section.label.includes.component.label" select="1"/>
    
    
    <!--    <xsl:param name="admon.graphics" select="1"/>-->
    
    
    
</xsl:stylesheet>
