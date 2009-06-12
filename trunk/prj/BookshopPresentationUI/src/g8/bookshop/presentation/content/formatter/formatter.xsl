<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>

    <!-- values for mode: search OR cart -->
    <xsl:param name="mode" select="'search'"/>
    <!-- values for search_type: simple OR authenticated -->
    <xsl:param name="search_type" select="'simple'"/>
    <xsl:param name="form_action" select="'action'"/>

    <!-- DON'T EVER SET mode="cart" AND search_type="authenticated"-->
    <!-- Possible combinations:
    mode="search" and search_type="simple"
    mode="search" and search_type="authenticated"
    mode="cart" AND NO VALUE FOR SEARCH_TYPE!
-->

    <xsl:template match="/">
        <xsl:choose>
            <xsl:when test="$mode = 'search'">
                <xsl:apply-templates select="*" mode="search"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:apply-templates select="*" mode="cart"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <!-- * * * * * * * * * < B O O K S > * * * * * * * * * * * * * * * * * * * * * * * -->
    <xsl:template match="books" mode="search">
        <xsl:element name="div">
            <xsl:attribute name="id">result</xsl:attribute>
            <xsl:choose>
                <!-- add form if the search is an authenticated one -->
                <xsl:when test="$search_type = 'authenticated'">
                    <xsl:element name="form">
                        <xsl:attribute name="action">
                            <xsl:value-of select="$form_action"/>
                            <!--                            <xsl:text>Update</xsl:text>-->
                        </xsl:attribute>
                        <xsl:element name="table">
                            <xsl:apply-templates select="book"/>
                            <xsl:call-template name="submitline"/>
                        </xsl:element>
                    </xsl:element>
                </xsl:when>
                <!-- no form needed -->
                <xsl:otherwise>
                    <xsl:element name="table">
                        <xsl:apply-templates select="book"/>
                    </xsl:element>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:element>
    </xsl:template>

    <!-- * * * * * * * * * < / B O O K S > * * * * * * * * * * * * * * * * * * * * * * -->

    <!-- * * * * * * * * * < B O O K >  * * * * * * * * * * * * * * * * * * * * * * * * -->

    <xsl:template match="book">
        <xsl:element name="tr">
            <xsl:attribute name="class">item</xsl:attribute>
            <xsl:apply-templates select="@title" mode="attributes"/>
            <xsl:apply-templates select="@author" mode="attributes"/>
            <xsl:apply-templates select="@editor" mode="attributes"/>
            <xsl:apply-templates select="@year" mode="attributes"/>
            <xsl:apply-templates select="@isbn" mode="attributes"/>
            <xsl:choose>
                <xsl:when test="$search_type = 'authenticated'">
                    <xsl:apply-templates select="@id" mode="attribute-id"/>
                </xsl:when>
                <xsl:when test="$mode = 'cart'">
                    <xsl:apply-templates select="parent::stock/@quantity"/>
                </xsl:when>
            </xsl:choose>
        </xsl:element>
    </xsl:template>


    <!-- GETTING ALL STANDARD ATTRIBUTES -->
    <!-- each attribute is transformed into a td element with a class attribute 
        whose value is set to the class name.
        Example: <element title="title0"> becomes (the attribute part)
        <td class="title">title0</td>
    -->
    <xsl:template match="@*" mode="attributes">
        <xsl:element name="td">
            <xsl:attribute name="class">
                <xsl:value-of select="name(.)"/>
            </xsl:attribute>
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>

    <!-- GETTING ID ATTRIBUTE
    if the transformation is due for an authenticated search, this template is 
    called: it produces a cell in the form
    <td><input type="checkbox" value="id-value"/></td>
    where id-value is the value of the attribute id of the book element
-->
    <xsl:template match="@id" mode="attribute-id">
        <xsl:element name="td">
            <xsl:attribute name="class">
                <xsl:text>checkbox</xsl:text>
            </xsl:attribute>
            <xsl:element name="input">
                <xsl:attribute name="type">
                    <xsl:text>checkbox</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="value">
                    <xsl:value-of select="."/>
                </xsl:attribute>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- * * * * * * * * * < / B O O K > * * * * * * * * * * * * * * * * * * * * * * * -->

    <!-- * * * * * * * * * * < S H O P P I N G C A R T > * * * * * * * * * * * * * * * -->

    <xsl:template match="shoppingcart" mode="cart">
        <xsl:element name="div">
            <xsl:attribute name="id">cart</xsl:attribute>
            <xsl:element name="form">
                <xsl:attribute name="action">
                    <xsl:value-of select="$form_action"/>
<!--                    <xsl:text>AddOrders</xsl:text>-->
                </xsl:attribute>
                <xsl:element name="table">
                    <xsl:apply-templates select="stock"/>
                    <xsl:call-template name="submitline"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="stock">
        <xsl:apply-templates select="book"/>
        <!--<xsl:apply-templates select="@quantity"/>-->
    </xsl:template>

    <xsl:template match="@quantity">
        <xsl:element name="td">
            <xsl:attribute name="class">
                <xsl:text>quantity</xsl:text>
            </xsl:attribute>
            <xsl:element name="input">
                <xsl:attribute name="type">
                    <xsl:text>text</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="size">
                    <xsl:text>4</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="name">
                    <xsl:value-of select="parent::node()/book/@id"/>
                </xsl:attribute>
                <xsl:attribute name="value">
                    <xsl:value-of select="."/>
                </xsl:attribute>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- * * * * * * * * * < / S H O P P I N G C A R T > * * * * * * * * * * * * * * * -->

    <!-- * * * * * * * * * < S U B M I T L I N E > * * * * * * * * * * * * * * * * * * -->

    <xsl:template name="submitline">
        <xsl:element name="tr">
            <xsl:attribute name="class">
                <xsl:text>submit</xsl:text>
            </xsl:attribute>
            <xsl:element name="td">
                <xsl:attribute name="class">
                    <xsl:text>empty</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="colspan">
                    <xsl:text>5</xsl:text>
                </xsl:attribute>
            </xsl:element>
            <xsl:element name="td">
                <xsl:attribute name="colspan">
                    <xsl:text>1</xsl:text>
                </xsl:attribute>
                <xsl:element name="input">
                    <xsl:attribute name="type">
                        <xsl:text>submit</xsl:text>
                    </xsl:attribute>
                    <xsl:attribute name="value">
                        <xsl:choose>
                            <xsl:when test="$mode = 'search'">
                                <xsl:text>select</xsl:text>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>commit changes</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:attribute>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- * * * * * * * * * < / S U B M I T L I N E > * * * * * * * * * * * * * * * * * -->

    <!-- * * * * * * * * * < T E X T > * * * * * * * * * * * * * * * * * * * * * * * * -->

    <xsl:template match="text()" priority="-1">
        <xsl:copy>
            <xsl:apply-templates mode="search"/>
        </xsl:copy>
    </xsl:template>

    <!-- * * * * * * * * * < / T E X T > * * * * * * * * * * * * * * * * * * * * * * * -->

</xsl:stylesheet>
