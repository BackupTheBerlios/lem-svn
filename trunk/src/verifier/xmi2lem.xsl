<!--
 * xmi2lem.xsl
 *
 * Copyright (C) 2005
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
-->

<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:UML="org.omg.xmi.namespace.UML">

<!--the real uml content "class name","attributes","association",etc. is in teh xmi.content tag-->
	<xsl:template match="/XMI">
		<xsl:apply-templates select="XMI.content"/>
	</xsl:template>

	<xsl:template match="XMI.content">
		<xsl:text>&#xA;</xsl:text>
		<xsl:apply-templates select="UML:Model"/>
	</xsl:template>
<!--select the the name of the model of uml diagram and put it as the the name for lem model-->
	<xsl:template match="UML:Model">
		<xsl:text>model </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text> is &#xA;</xsl:text>
		<xsl:apply-templates select="UML:Namespace.ownedElement"/>
		<xsl:text>end </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>;&#xA;</xsl:text>
	</xsl:template>
<!--find all the calsses and associations in the model-->
	<xsl:template match="UML:Namespace.ownedElement">
		<xsl:apply-templates select="UML:Class"/>
		<xsl:apply-templates select="UML:Association"/>
	</xsl:template>

	<xsl:key name="classes" match="/XMI/XMI.content/UML:Model/UML:Namespace.ownedElement/UML:Class" use="@xmi.id"/>
<!--put all the classes into the model-->	
	<xsl:template match="UML:Class">
		<xsl:text>object </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text> is&#xA;</xsl:text>
		<xsl:text>end </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>;&#xA;</xsl:text>
	</xsl:template>
<!--put all the associations into the model. include the association name, two classes at the end of this assiociation-->
	<xsl:template match="UML:Association">
		<xsl:text>association </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text> is&#xA;</xsl:text>
		<xsl:variable name="class1" select="UML:Association.connection/UML:AssociationEnd[position()=1]/UML:AssociationEnd.participant/UML:Class/@xmi.idref"/>
		<xsl:value-of select="key('classes',$class1)/@name"/>
		<xsl:apply-templates select="UML:Association.connection/UML:AssociationEnd[position()=1]"/>
		<xsl:variable name="class2" select="UML:Association.connection/UML:AssociationEnd[position()=2]/UML:AssociationEnd.participant/UML:Class/@xmi.idref"/>
		<xsl:value-of select="key('classes',$class2)/@name"/>
		<xsl:text>;&#xA;</xsl:text>
		<xsl:value-of select="key('classes',$class2)/@name"/>
		<xsl:apply-templates select="UML:Association.connection/UML:AssociationEnd[position()=2]"/>
		<xsl:value-of select="key('classes',$class1)/@name"/>
		<xsl:text>;&#xA;end</xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>;&#xA;</xsl:text>
	</xsl:template>
<!--put the value of "multiplicity" on one end of the association "class 1"-->
	<xsl:template match="UML:Association.connection/UML:AssociationEnd[position()=1]">
		<xsl:text> "</xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>" </xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@lower"/>
		<xsl:text>..</xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@upper"/>
		<xsl:text> </xsl:text>
	</xsl:template>
<!--put the value of "multiplicity" on the other end of the association "class 2"-->
	<xsl:template match="UML:Association.connection/UML:AssociationEnd[position()=2]">
		<xsl:text> "</xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>" </xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@lower"/>
		<xsl:text>..</xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@upper"/>
		<xsl:text> </xsl:text>
	</xsl:template>
</xsl:stylesheet>