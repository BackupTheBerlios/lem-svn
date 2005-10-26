" LEM syntax file
" Language:	LEM
" Maintainer:	James Greenhalgh <james.greenhalgh@gmail.com>
" Last Change:	2005 Sep 20

" For version 5.x: Clear all syntax items
" For version 6.x: Quit when a syntax file was already loaded
if version < 600
  syntax clear
elseif exists("b:current_syntax")
  finish
endif

" Keyword definitions
syn keyword lemIdentifier	association generalisation specialisation attribute end between bridge class is or and domain scenario subsystem model type state transition event behaviour to from by at on null carries creation deletion range pattern numeric objref set units length precision refers active passive calculation derived referential identifier
syn keyword lemAction 	self generate delay cancel relate unrelate creating across reclassify of create print atomic delete with while for each in break select one many all any where instances related return if else elseif var

" Operators
syn match   lemArray		"!"
syn match   lemArray		":="
syn match   lemArray		"="
syn match   lemArray		"<"
syn match   lemArray		"<="
syn match   lemArray		">"
syn match   lemArray		">="
syn match   lemArray		"+"
syn match   lemArray		"-"
syn match   lemArray		"*"
syn match   lemArray		"/"
syn match   lemArray		"^"
syn match   lemArray		"&&"
syn match   lemArray		"||"
syn match   lemArray		"mod"
syn match   lemArray		"<<"
syn match   lemArray		"<<"
syn match   lemArray		">>"
syn match   lemOperator	"[@#|&][^ \e\t\b%]*"


"syn match	lemTo ".."
"syn region 	lemMultiplicity	contained start=+[0-9]+ start=+*+ end=+[0-9]+ end=+*+ contains=lemTo

" Constants
syn keyword lemBool		true false
syn region  lemString	start=+"+ skip=+%"+ end=+"+ contains=lemStringEscape,lemStringError
syn match   lemStringEscape	contained "%[^/]"
syn match   lemStringEscape	contained "%/\d\+/"
syn match   lemStringEscape	contained "^[ \t]*%"
syn match   lemStringEscape	contained "%[ \t]*$"
syn match   lemStringError	contained "%/[^0-9]"
syn match   lemStringError	contained "%/\d\+[^0-9/]"
syn match   lemBadConstant	"'\(%[^/]\|%/\d\+/\|[^'%]\)\+'"
syn match   lemBadConstant	"''"
syn match   lemCharacter	"'\(%[^/]\|%/\d\+/\|[^'%]\)'" contains=lemStringEscape
syn match   lemNumber	"-\=\<\d\+\(_\d\+\)*\>"
syn match   lemNumber	"\<[01]\+[bB]\>"
syn match   lemNumber	"-\=\<\d\+\(_\d\+\)*\.\(\d\+\(_\d\+\)*\)\=\([eE][-+]\=\d\+\(_\d\+\)*\)\="
syn match   lemNumber	"-\=\.\d\+\(_\d\+\)*\([eE][-+]\=\d\+\(_\d\+\)*\)\="
syn match   lemComment	"--.*" contains=lemTodo
syn region  lemCommentL	start="//" skip="\\$" end="$" keepend contains=lemTodo
syn case match

" Case sensitive stuff

syn keyword lemTodo		contained TODO XXX FIXME POO

" Catch mismatched parentheses
syn match lemParenError	"}"
syn region lemParen		transparent start="{" end="}" contains=ALLBUT,lemParenError,lemStringError,lemStringEscape

" Should suffice for even very long strings and expressions
syn sync lines=40

" Define the default highlighting.
" For version 5.7 and earlier: only when not done already
" For version 5.8 and later: only when an item doesn't have highlighting yet
if version >= 508 || !exists("did_lem_syntax_inits")
  if version < 508
    let did_lem_syntax_inits = 1
    command -nargs=+ HiLink hi link <args>
  else
    command -nargs=+ HiLink hi def link <args>
  endif

  HiLink lemKeyword		Statement
  HiLink lemProperty		Statement
  HiLink lemInheritClause	Statement
  HiLink lemStatement		Statement
  HiLink lemDeclaration		Statement
  HiLink lemAssertion		Statement
  HiLink lemDebug		Statement
  HiLink lemException		Statement
  HiLink lemGenericCreate	Statement
  HiLink lemAction		Statement

  HiLink lemTopStruct		PreProc

  HiLink lemIdentifier		Special
  HiLink lemAll			Special
  HiLink lemAnchored		Special
  HiLink lemBitType		Special


  HiLink lemBool		Boolean
  HiLink lemString		String
  HiLink lemCharacter		Character
  HiLink lemMultiplicity	Type
  HiLink lemNumber		Number

  HiLink lemStringEscape	Special

  HiLink lemOperator		Special
  HiLink lemArray		Special
  HiLink lemExport		Special
  HiLink lemCreation		Special
  HiLink lemBrackets		Special
  HiLink lemGeneric		Special
  HiLink lemGenericDecl		Special
  HiLink lemConstraint		Special
  HiLink lemCreate		Special

  HiLink lemPredefined		Constant

  
  HiLink lemCommentL		lemComment
  HiLink lemComment		Comment

  HiLink lemError		Error
  HiLink lemBadConstant		Error
  HiLink lemStringError		Error
  HiLink lemParenError		Error
  HiLink lemBracketError	Error

  HiLink lemTodo		Todo

  delcommand HiLink
endif

let b:current_syntax = "lem"

" vim: ts=8
