package archive;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfugue.*;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Intervals;
import org.jfugue.theory.Note;

public class Master {
	public final static String[] chords = {"C","D","E","F","G","A","B"};

	static Chord generateJoyChord(String root) {
		String chordString = root + "majh ";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
				
	}
	
	static Chord generateSadChord(String root) {

		String chordString = root + "minh ";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}
	
	static Chord generateAngerChord(String root) {

		String chordString = root + "dom9W ";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}

	static Chord generateAnticipationChord(String root) {

		String chordString = root + "sus4W ";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}
	
	static Chord generateFearChord(String root) {

		String chordString = root + "min9W ";
		Chord chord = new Chord(chordString);
		return new Chord(chord);
	}
	
	static Note changeOctave(Note note, int delta) {
		int original = note.getValue();
		int newOctave = original + delta * 12;
		
		return new Note(newOctave);
		
	}
	
	
	
	public static String generateMelody(Chord chord) {
		
		int[] order1 = {0, 1, 2, 0};
		int[] order2 = {1, 2, 0, 1};
		int[] order3 = {0, 1, 0, 2};
		int[] order4 = {2, 1, 0, 2};

		int[] order;
		
		Double random = Math.random();
		
		if(random < 0.2) {
			order = order1;
			
		}
		else if(random < 0.4) {
			order = order2;
			
		}
		else if(random < 0.6) {
			order = order3;
		}
		else {
			order = order4;
		}
		
		String pattern = "";
		Intervals in = Chord.chordMap.get(chord.getChordType());
		
		System.out.println(chord.getChordType());
		in.setRoot(chord.getRoot());
		List<Note> notes = in.getNotes();


		for(int i = 0; i < order.length; i++) {
			pattern += notes.get(order[i]);
			if(i < order.length - 1) {
				pattern += "i_";
			}
			else {
				pattern += "i";
			}
			
		}
		System.out.println("The melody is: " + pattern.toString());
			
		return pattern;
		
	}
	public static void main(String[] args) {
		Player player = new Player();
		
		String[] prog = {"D", "Bb", "F", "C"};
		
		String music = "";
		
		for(int i = 0; i < prog.length; i++) {
			for(int j = 0; j < 4; j++) {
				if(i == 0) {
					Chord ch = generateSadChord(prog[i]);
					String melody = generateMelody(ch);
					String pattern = melody + "+" + ch.toString() + " ";
					music += pattern;
					
				}
				else {
					Chord ch = generateJoyChord(prog[i]);
					String melody = generateMelody(ch);
					String pattern = melody + "+" + ch.toString() + " ";
					music += pattern;
				}

			}
		}
		
		Pattern pattern = new Pattern(music);
		System.out.println(pattern.toString());
//		player.play(pattern);
		Note note = new Note("F");
		Chord chord = new Chord("CMaj");
		
		new Player().play(new ChordProgression("ii ii ii ii V V V V I I I I").setKey("Dmin9"
				+ ""));
//		player.play("Dminh Dminh Dminh Dminh Bbmajh Bbmajh Bbmajh Bbmajh Fmajh Fmajh Fmajh Fmajh Cmajh Cmajh Cmajh Cmajh");

		

	
	} 
	
}

