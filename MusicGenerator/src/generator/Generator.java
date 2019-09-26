package generator;

import jm.JMC;
import jm.gui.show.ShowScore;
import jm.music.data.*;
import jm.midi.*;
import jm.music.tools.*;
import jm.util.*;

public class Generator implements JMC {

	private static Score s = new Score("Sample");
	private static Part p = new Part("Piano", 0, 0);

	public static void main(String[] args) {

		Note cMaj[] = { new Note(C3, Q), new Note(E3, Q), new Note(G3, Q) };
		Note cm[] = { new Note(C3, Q), new Note(EF3, Q), new Note(G3, Q) };
		Note c7[] = { new Note(C3, Q), new Note(E3, Q), new Note(G3, Q), new Note(BF3, Q) };
		Note cMaj7[] = { new Note(C3, Q), new Note(E3, Q), new Note(G3, Q), new Note(A3, Q) };
		Note cm7[] = { new Note(C3, Q), new Note(EF3, Q), new Note(G3, Q), new Note(BF3, Q) };
		Note c9[] = { new Note(C3, Q), new Note(E3, Q), new Note(G3, Q), new Note(BF3, Q), new Note(D4, Q) };
		Note cDim[] = { new Note(C3, Q), new Note(EF3, Q), new Note(GF3, Q) };
		Note cSus4[] = { new Note(C3, Q), new Note(F3, Q), new Note(G3, Q) };
		Note cm9[] = { new Note(C3, Q), new Note(E3, Q), new Note(G3, Q), new Note(BF3, Q), new Note(D4, Q) };
		Note cAdd9[] = { new Note(C3, Q), new Note(E3, Q), new Note(G3, Q), new Note(D4, Q) };

		// Section 1
		happyChord();
		
		// Section 2
		scaryChord();
		
		// Section 3
		sadChord();


		// pack the part into a score
		s.addPart(p);

		// write the score to a MIDIfile
		Write.midi(s, "Output.mid");

	}

	public static void happyChord() {
		Note cMaj[] = { new Note(C3, Q), new Note(E3, Q), new Note(G3, Q) };
		for (int i = 0; i < 4; i++) {
			CPhrase chord = new CPhrase();
			chord.addChord(cMaj);
			p.addCPhrase(chord);

		}

	}

	public static void scaryChord() {
		Note cDim[] = { new Note(C3, Q), new Note(EF3, Q), new Note(GF3, Q) };
		for (int i = 0; i < 4; i++) {
			CPhrase chord = new CPhrase();
			chord.addChord(cDim);
			p.addCPhrase(chord);

		}

	}

	public static void sadChord() {
		Note cm[] = { new Note(C3, Q), new Note(EF3, Q), new Note(G3, Q) };

		for (int i = 0; i < 4; i++) {
			CPhrase chord = new CPhrase();
			chord.addChord(cm);
			p.addCPhrase(chord);

		}

	}

}
