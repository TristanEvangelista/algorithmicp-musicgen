package generator;

import jm.JMC;
import jm.gui.show.ShowScore;
import jm.music.data.*;
import jm.midi.*;
import jm.music.tools.*;
import jm.util.*;

public class Chord implements JMC {

	private static Score s = new Score("Sample");
	private static Part p = new Part("Piano", 0, 0);

	public static void main(String[] args) {

        Note cMaj[] = {new Note(C3, Q), new Note(E3, Q), new Note(G3, Q)};
        Note cm[] = {new Note(C3, Q), new Note(EF3, Q), new Note(G3, Q)};
        Note c7[] = {new Note(C3, Q), new Note(E3, Q), new Note(G3, Q), new Note(AF3, Q)};
        Note fMaj[] = {new Note(F3, Q), new Note(A3, Q), new Note(C3, Q)};
        Note gMaj[] = {new Note(G3, M), new Note(B3, M), new Note(D3, M)};
        
		// add a final chord
//		ending(rootPitch);
		
        for(int i = 0; i < 6; i++) {
    		CPhrase chord = new CPhrase();
    		chord.addChord(cm);
    		
    		CPhrase chord2 = new CPhrase();
    		chord2.addChord(c7);
    		
    		p.addCPhrase(chord);
    		p.addCPhrase(chord2);
        	
        }
		

		// pack the part into a score
		s.addPart(p);

		// write the score to a MIDIfile
		Write.midi(s, "Chords.mid");

	}
	
	

	private static void rootPosition(int rootPitch) {
		// build the chord from the rootPitch
		int[] pitchArray = new int[4];
		pitchArray[0] = rootPitch;
		pitchArray[1] = rootPitch + 4;
		pitchArray[2] = rootPitch + 7;
		pitchArray[3] = rootPitch + 10;
		// add chord to the part
		CPhrase chord = new CPhrase();
		chord.addChord(pitchArray, C);
		p.addCPhrase(chord);
	}

	private static void secondInversion(int rootPitch) {
		// build the chord from the rootPitch
		int[] pitchArray = new int[4];
		pitchArray[0] = rootPitch;
		pitchArray[1] = rootPitch + 4;
		pitchArray[2] = rootPitch - 2;
		pitchArray[3] = rootPitch - 5;
		// add chord to the part
		CPhrase chord = new CPhrase();
		chord.addChord(pitchArray, C);
		p.addCPhrase(chord);
	}

	private static void ending(int rootPitch) {
		// build the chord from the rootPitch
		int[] pitchArray = new int[4];
		pitchArray[0] = rootPitch;
		pitchArray[1] = rootPitch + 4;
		pitchArray[2] = rootPitch + 7;
		pitchArray[3] = rootPitch + 12;
		// add chord to the part
		CPhrase chord = new CPhrase();
		chord.addChord(pitchArray, SB);
		p.addCPhrase(chord);
	}
}
