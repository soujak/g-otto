<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:h="http://www.w3.org/1999/xhtml">

    <xsl:output method="html" indent="yes" encoding="UTF-8"/>


    <xsl:template match="/">
        <pippo>
            <xsl:apply-templates select="*" mode="asearch"/>
        </pippo>
    </xsl:template>

        <xsl:template match="books" mode="asearch">
        <xsl:element name="h:div">
                <xsl:attribute name="h:id">result</xsl:attribute>
                <xsl:element name="h:table">
                    <xsl:apply-templates mode="asearch"/>
                </xsl:element>
            </xsl:element>
        </xsl:template>
    
        <xsl:template match="book" mode="asearch">
            <xsl:element name="h:tr">
                <xsl:attribute name="h:class">item</xsl:attribute>
    
                <xsl:element name="h:td">
                    <xsl:attribute name="h:class">title</xsl:attribute>
                    <xsl:value-of select="@title"/>
                    <xsl:apply-templates mode="asearch"/>
                </xsl:element>
                <xsl:element name="h:td">
                    <xsl:attribute name="h:class">author</xsl:attribute>
                    <xsl:value-of select="@author"/>
                    <xsl:apply-templates mode="asearch"/>
                </xsl:element>
    
            </xsl:element>
        </xsl:template>
    
        <xsl:template match="text()" priority="-1" mode="asearch">
            <xsl:copy>
                <xsl:apply-templates mode="asearch"/>
            </xsl:copy>
        </xsl:template>

</xsl:stylesheet>
