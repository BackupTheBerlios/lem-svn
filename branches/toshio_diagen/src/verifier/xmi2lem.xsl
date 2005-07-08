<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:UML="org.omg.xmi.namespace.UML">

	<xsl:template match="/XMI">
		<xsl:apply-templates select="XMI.content"/>
	</xsl:template>

	<xsl:template match="XMI.content">
		<xsl:text>&#xA;</xsl:text>
		<xsl:apply-templates select="UML:Model"/>
	</xsl:template>

	<xsl:template match="UML:Model">
		<xsl:text>model </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text> is &#xA;</xsl:text>
		<xsl:apply-templates select="UML:Namespace.ownedElement"/>
		<xsl:text>end </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>;&#xA;</xsl:text>
	</xsl:template>

	<xsl:template match="UML:Namespace.ownedElement">
		<xsl:apply-templates select="UML:Class"/>
		<xsl:apply-templates select="UML:Association"/>
	</xsl:template>

	<xsl:key name="classes" match="/XMI/XMI.content/UML:Model/UML:Namespace.ownedElement/UML:Class" use="@xmi.id"/>
	
	<xsl:template match="UML:Class">
		<xsl:text>object </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text> is&#xA;</xsl:text>
		<xsl:text>end </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>;&#xA;</xsl:text>
	</xsl:template>

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

	<xsl:template match="UML:Association.connection/UML:AssociationEnd[position()=1]">
		<xsl:text> "</xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>" </xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@lower"/>
		<xsl:text>..</xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@upper"/>
		<xsl:text> </xsl:text>
	</xsl:template>

	<xsl:template match="UML:Association.connection/UML:AssociationEnd[position()=2]">
		<xsl:text> "</xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>" </xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@lower"/>
		<xsl:text>..</xsl:text>
		<xsl:value-of select="UML:AssociationEnd.multiplicity/UML:Multiplicity/UML:Multiplicity.range/UML:MultiplicityRange/@upper"/>
		<xsl:text> </xsl:text>
	</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c) 2004-2005. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="yes" name="requirementsModel.xmi" userelativepaths="yes" externalpreview="no" url="requirementsModel.xmi" htmlbaseurl="" outputurl="" processortype="saxon8" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no" ><SourceSchema srcSchemaPath="requirementsModel.xmi" srcSchemaRoot="XMI" AssociatedInstance="" loaderFunction="document" loaderFunctionUsesURI="no"/></MapperInfo><MapperBlockPosition><template match="/XMI"><block path="xsl:apply&#x2D;templates" x="247" y="0"/></template><template match="Association.connection"></template><template match="UML:AssociationEnd"></template><template match="UML:Class"></template><template match="UML:Association.connection/UML:AssociationEnd[position()=1]"></template></MapperBlockPosition></MapperMetaTag>
</metaInformation>
-->