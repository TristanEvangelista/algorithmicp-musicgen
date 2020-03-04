package generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfugue.*;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Intervals;
import org.jfugue.theory.Note;

public class MusicUtil {
	public final static String[] chords = {"C","D","E","F","G","A","B"};

	static Chord generateJoyChord(String root) {
		String chordString = root + "maj";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
				
	}
	
	static Chord generateSadChord(String root) {

		String chordString = root + "min";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}
	
	static Chord generateAngerChord(String root) {

		String chordString = root + "dom9";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}

	static Chord generateAnticipationChord(String root) {

		String chordString = root + "sus4";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}
	
	static Chord generateFearChord(String root) {

		String chordString = root + "min9";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}

		
	static Chord generateSurpriseChord(String root) {

		String chordString = root + "maj";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}

		
	static Chord generateDisgustChord(String root) {

		String chordString = root + "min9";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}

		
	static Chord generateTrustChord(String root) {

		String chordString = root + "maj";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}
	
	static Note changeOctave(Note note, int delta) {
		int original = note.getValue();
		int newOctave = original + delta * 12;
		return new Note(newOctave);
	}
	
	public static List<Note> getChordNotes(Chord chord) {
		Intervals in = Chord.chordMap.get(chord.getChordType());
		in.setRoot(chord.getRoot());
		return in.getNotes();
	}
		
	public static String generateMelody(Chord chord) {
		
		int[] order1 = {0, 1, 2, 0};
		int[] order2 = {1, 2, 0, 1};
		int[] order;
		
		if(Math.random() > 0.5) {
			order = order1;
			
		}
		else {
			order = order2;
		}
		String pattern = "";
		Intervals in = Chord.chordMap.get(chord.getChordType());
		
		//System.out.println(chord.getChordType());
		in.setRoot(changeOctave(chord.getRoot(), 2));
		List<Note> notes = in.getNotes();


		for(int i = 0; i < order.length; i++) {
			pattern += notes.get(order[i]);
			if(i < order.length - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
			
		}
		//System.out.println("The melody is: " + pattern.toString());
			
		return pattern;
		
	}

	
}

