LEMTARGETS :=
LEMTARGETS += example_attributes1.pdf
LEMTARGETS += example_author.pdf
LEMTARGETS += example_associationDeclaration.pdf
LEMTARGETS += example_baseAttributeDeclaration.pdf
LEMTARGETS += example_behaviourDeclaration.pdf
LEMTARGETS += example_bridgeDeclaration.pdf
LEMTARGETS += example_classDeclaration.pdf
LEMTARGETS += example_classDeclaration2.pdf
LEMTARGETS += example_concurrentAttributeManipulation.pdf
LEMTARGETS += example_concurrentAttributeManipulation1.pdf
LEMTARGETS += example_createAction.pdf
LEMTARGETS += example_createContext.pdf
LEMTARGETS += example_declarations.pdf
LEMTARGETS += example_derivedAttributeDeclaration.pdf
LEMTARGETS += example_domainSpecificDataType.pdf
LEMTARGETS += example_arbitraryidDomainSpecificType.pdf
LEMTARGETS += example_enumeratedlistDomainSpecificType.pdf
LEMTARGETS += example_booleanDomainSpecificType.pdf
LEMTARGETS += example_dateDomainSpecificType.pdf
LEMTARGETS += example_objrefDomainSpecificType.pdf
LEMTARGETS += example_setDomainSpecificType.pdf
LEMTARGETS += example_numericDomainSpecificType.pdf
LEMTARGETS += example_stringDomainSpecificType.pdf
LEMTARGETS += example_eventDeclaration.pdf
LEMTARGETS += example_expression1.pdf
LEMTARGETS += example_forEach.pdf
LEMTARGETS += example_forEach1.pdf
LEMTARGETS += example_generalisation.pdf
LEMTARGETS += example_generalisationDeclaration.pdf
LEMTARGETS += example_generateAction.pdf
LEMTARGETS += example_government.pdf
LEMTARGETS += example_identifierDeclaration.pdf
LEMTARGETS += example_modelDeclaration.pdf
LEMTARGETS += example_objectCreation.pdf
LEMTARGETS += example_objectCreation1.pdf
LEMTARGETS += example_objectDeletion.pdf
LEMTARGETS += example_perspectiveDefinition.pdf
LEMTARGETS += example_referentialAttributeDeclaration.pdf
LEMTARGETS += example_relateAction.pdf
LEMTARGETS += example_relateActionWithClass.pdf
LEMTARGETS += example_rintimeObject.pdf
LEMTARGETS += example_scenarioDeclaration.pdf
LEMTARGETS += example_selectExpression.pdf
LEMTARGETS += example_selectExpressionWhere.pdf
LEMTARGETS += example_selectionClause.pdf
LEMTARGETS += example_signalCancel.pdf
LEMTARGETS += example_stringConcatentation.pdf
LEMTARGETS += example_stateDeclaration.pdf
LEMTARGETS += example_subsystemDeclaration.pdf
LEMTARGETS += example_testModel.pdf
LEMTARGETS += example_transitionDeclaration.pdf
LEMTARGETS += example_unrelateAction.pdf
LEMTARGETS += example_variableReference.pdf
LEMTARGETS += example_whileLoop.pdf
LEMTARGETS += exapmle_aircon1.pdf
LEMTARGETS += exapmle_aircon2.pdf
LEMTARGETS += example_DSTprice.pdf
LEMTARGETS += example_delayedSignals.pdf
LEMTARGETS += example_domainDeclaration.pdf
LEMTARGETS += example_while_break.pdf

all: $(LEMTARGETS)

%.eps: %.lem
	enscript -B -C -T 4 --color "$*.lem" -o "$*.ps"
	ps2epsi "$*.ps" "$*.eps"

%.pdf: %.eps
	epstopdf "$*.eps"

clean:
	-rm *.eps *.ps *.pdf
