LEMTARGETS :=
LEMTARGETS += 1to1.pdf
LEMTARGETS += signalDelivery1.pdf
LEMTARGETS += signalSources.pdf
LEMTARGETS += objects.pdf

all: $(LEMTARGETS)

%.eps: %.dia
	dia "$*.dia" -e "$*.eps" -t eps-builtin

%.pdf: %.eps
	epstopdf "$*.eps"

clean:
	-rm *.eps *.ps *.pdf
