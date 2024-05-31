<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cv="http://univ.fr/cv24">
    <xsl:output method="html" version="5.0" encoding="UTF-8" doctype-system="about:legacy-compat" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
               <title>Curriculum Vitae</title>
               <link rel="stylesheet" type="text/css" href="styles.css"/>
            </head>
            <body>
                <h1>CV24 - XSLT V1.0</h1>
                <p>Date du jour : Mercredi 14 fevrier 2024</p>
                <h2> 
               <xsl:value-of select="concat(cv:cv24/cv:Identité/cv:genre, ' ', cv:cv24/cv:Identité/cv:nom, ' ', cv:cv24/cv:Identité/cv:prenom)"/>
                </h2>
                
                <p>
                    Tel : 
                    <xsl:call-template name="formatPhoneNumber">
    <xsl:with-param name="phone" select="cv:cv24/cv:Identité/cv:tel"/>
</xsl:call-template>
<!-- <xsl:value-of select="cv:cv24/cv:Identité/cv:tel"/> -->
                    <br/>
                    Mel : <xsl:value-of select="cv:cv24/cv:Identité/cv:mel"/>
                </p>
          
                <xsl:apply-templates select="cv:cv24/cv:Objectif"/>
                <xsl:apply-templates select="cv:cv24/cv:ExpériencePro"/>
                
                <h2>Diplômes</h2>
               
                  <table>
                    <tr>
                     <th></th>
                     <th></th>
                     <th></th>
                     <th></th>
                  </tr>
                    <xsl:apply-templates select="cv:cv24/cv:Compétences/cv:diplome"/>
                </table>
                
                
                 <h2>Certifications</h2>
                <table>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                    <xsl:apply-templates select="cv:cv24/cv:Compétences/cv:certif"/>
                </table>
                
                <xsl:apply-templates select="cv:cv24/cv:Divers"/>
                
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="cv:Objectif">
      <h2>
        <xsl:value-of select="."/>
      </h2>
      <p>
        <xsl:text> (demande de </xsl:text>
        <xsl:value-of select="@statut"/>
        <xsl:text>)</xsl:text>
      </p>
   </xsl:template>
   
   
   <xsl:template match="cv:ExpériencePro">
    <h2>Expérience professionnelles</h2>
    <ol>
        <xsl:apply-templates select="cv:detail"/>
    </ol>
   </xsl:template>
   
   
   <xsl:template match="cv:Divers">
    <h2>Langues</h2>
    <ul>
        <xsl:apply-templates select="cv:lv"/>
    </ul>
    <h2>Divers</h2>
    <ul>
        <xsl:apply-templates select="cv:autre"/>
    </ul>
</xsl:template>
   

<xsl:template match="cv:detail">
    <li class="cv-experience">
        <xsl:value-of select="cv:titre"/>
        <xsl:text> (du </xsl:text>
         <xsl:call-template name="formatDate">
          <xsl:with-param name="date" select="cv:datedeb"/>
         </xsl:call-template>
       <!--  <xsl:value-of select="cv:datedeb"/> -->
        <xsl:text> au </xsl:text>
         <xsl:call-template name="formatDate">
          <xsl:with-param name="date" select="cv:datefin"/>
         </xsl:call-template>
        <!-- <xsl:value-of select="cv:datefin"/> -->
        <xsl:text>)</xsl:text>
    </li>
</xsl:template>

<xsl:template match="cv:diplome">
    <tr>
        <td>
        <!-- <xsl:value-of select="cv:date"/> -->
        
         <xsl:call-template name="formatDate">
          <xsl:with-param name="date" select="cv:date"/>
         </xsl:call-template>
        
        </td>
        <td><xsl:value-of select="cv:diplome"/></td>
        <td>
            <xsl:text> (niveau </xsl:text>
            <xsl:value-of select="@niveau"/>
            <xsl:text>)</xsl:text>
        </td>
        <td><xsl:value-of select="cv:institut"/></td>
    </tr>
</xsl:template>


<xsl:template match="cv:certif">
    <tr>
        <td>
         <xsl:call-template name="formatDate">
          <xsl:with-param name="date" select="cv:datedeb"/>
         </xsl:call-template>
          <!--   <xsl:value-of select="cv:datedeb"/> -->
            <xsl:text> - </xsl:text>
            <xsl:call-template name="formatDate">
          <xsl:with-param name="date" select="cv:datefin"/>
         </xsl:call-template>
        <!--     <xsl:value-of select="cv:datefin"/> -->
        </td>
        <td><xsl:value-of select="cv:titre"/></td>
    </tr>
</xsl:template>


    
    
<xsl:template match="cv:lv">
    <li>
        <xsl:value-of select="@lang"/>
        <xsl:text>: </xsl:text>
        <xsl:value-of select="@cert"/>
        <xsl:text> (</xsl:text>
        <xsl:choose>
            <xsl:when test="@nivi != ''">
                <xsl:value-of select="@nivi"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="@nivs"/>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text>)</xsl:text>
    </li>
</xsl:template>

<xsl:template match="cv:autre">
    <li>
        <xsl:value-of select="@titre"/>
        <xsl:text>: </xsl:text>
        <xsl:value-of select="@comment"/>
    </li>
</xsl:template>


<!-- HELPER TEMPLATES  -->

<!-- template to format the date -->
<xsl:template name="formatDate">
    <xsl:param name="date"/>
    <xsl:variable name="day" select="substring($date, 9, 2)"/>
    <xsl:variable name="month">
        <xsl:choose>
            <xsl:when test="substring($date, 6, 2) = '01'">Jan</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '02'">Fevr</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '03'">Mar</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '04'">Avr</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '05'">Mai</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '06'">Juin</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '07'">Juil</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '08'">Août</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '09'">Sept</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '10'">Oct</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '11'">Nov</xsl:when>
            <xsl:when test="substring($date, 6, 2) = '12'">Dec</xsl:when>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="year" select="substring($date, 1, 4)"/>
    <xsl:value-of select="concat($day, ' ', $month, ' ', $year)"/>
</xsl:template>

<!-- template to format phone nb -->
<xsl:template name="formatPhoneNumber">
    <xsl:param name="phone"/>
    <xsl:choose>
        <xsl:when test="starts-with($phone, '+33')">
            <xsl:value-of select="concat('+33 (0)', substring($phone, 4, 1), ' ', substring($phone, 5, 2), ' ', substring($phone, 7, 2), ' ', substring($phone, 9, 2), ' ', substring($phone, 11))"/>
        </xsl:when>
        <xsl:otherwise>
            <xsl:value-of select="$phone"/>
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>



    
</xsl:stylesheet>
