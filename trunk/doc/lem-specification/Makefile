RAIL = $(HOME)/xtuml.old/utils/rail/rail

lem_spec.pdf: lem_spec.tex action_language_spec.tex syntax_specification.tex xtuml_semantics.tex snippets/*.lem
	make -C $(PWD)/snippets all
	make -C $(PWD)/images all
	pdflatex lem_spec.tex
	$(RAIL) lem_spec
	bibtex lem_spec
	pdflatex lem_spec.tex
	pdflatex lem_spec.tex

lem_spec.dvi: lem_spec.tex snippets/*.lem
	latex lem_spec.tex
	bibtex lem_spec
	$(RAIL) lem_spec
	latex lem_spec.tex
	latex lem_spec.tex

clean:
	make -C $(PWD)/snippets clean
	make -C $(PWD)/images clean
	-rm *.aux *.bbl *.blg *.log *.out *.pdf *.toc *.rai *.rao
