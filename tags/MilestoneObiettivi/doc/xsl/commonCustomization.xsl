<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:param name="generate.toc">
        appendix  nop
        article   nop,title
        book      toc,title,figure,table,example,equation
        chapter   nop
        part      nop
        preface   nop
        qandadiv  nop
        qandaset  nop
        reference toc,title
        section   nop
        set       toc
    </xsl:param>
    <xsl:param name="glossary.sort" select="1"></xsl:param>
<!--    <xsl:param name="admon.graphics" select="1"/>-->
</xsl:stylesheet>
