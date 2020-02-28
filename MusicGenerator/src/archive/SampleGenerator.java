// package archive;

// import java.io.File;
// import java.io.IOException;

// import org.jfugue.midi.MidiFileManager;
// import org.jfugue.pattern.Pattern;
// import org.jfugue.theory.Chord;

// import generator.MusicUtil.*;
// public class SampleGenerator {
	
// 	public static void saveSample(Pattern pattern, String filename) {
		
// 		try {
// 			MidiFileManager.savePatternToMidi(pattern, new File("./samples/"+filename));
		      
// 		} catch (IOException e) {
// 			e.printStackTrace();
			  	
// 		}
		
// 	}
	
// 	public static void generateJoySample() {
		
// 		String[] prog = {"C", "C", "G", "G", "D", "D", "E", "E"};
// 		String music = "I[ELECTRIC_GRAND] R R R R ";
// 		for(int i = 0; i < prog.length; i++) {
// 			Chord ch = MusicUtil.generateJoyChord(prog[i]);
// 			String melody = MusicUtil.generateMelody(ch);
// 			String pattern = melody + "+" + ch.toString() + " ";
// 			music += pattern;
// 		}
		
// 		Pattern joyPattern = new Pattern(music);
// 		saveSample(joyPattern, "JoyOctave4.midi");
// 		System.out.println("Saved Joy Sample");

// 	}
// 	public static void generateSadSample() {
// 		String[] prog = {"C5", "C5", "G5", "G5", "D5", "D5", "E5", "E5"};
// 		String music = "I[ELECTRIC_GRAND] R R R R ";
// 		for(int i = 0; i < prog.length; i++) {
// 			Chord ch = MusicUtil.generateSadChord(prog[i]);
// 			String melody = MusicUtil.generateMelody(ch);
// 			String pattern = melody + "+" + ch.toString() + " ";
// 			music += pattern;
// 		}
		
// 		Pattern sadPattern = new Pattern(music);
		
// 		saveSample(sadPattern, "SadOctave5.midi");
// 		System.out.println("Saved Sad Sample");



// 	}
// 	public static void generateAngerSample() {
		
// 		String[] prog = {"C", "C", "G", "G", "D", "D", "E", "E"};
// 		String music = "I[ELECTRIC_GRAND] R R R R ";
// 		for(int i = 0; i < prog.length; i++) {
// 			Chord ch = MusicUtil.generateAngerChord(prog[i]);
// 			String melody = MusicUtil.generateMelody(ch);
// 			String pattern = melody + "+" + ch.toString() + " ";
// 			music += pattern;
// 		}
		
// 		Pattern angerPattern = new Pattern(music);
// 		saveSample(angerPattern, "AngerOctave4.midi");
// 		System.out.println("Saved Anger Sample");


// 	}
	
// 	public static void generateAnticipationSample() {
// 		String[] prog = {"C", "C", "G", "G", "D", "D", "E", "E"};
// 		String music = "I[ELECTRIC_GRAND] C C C C R R R R ";
// 		for(int i = 0; i < prog.length; i++) {
// 			Chord ch = MusicUtil.generateAnticipationChord(prog[i]);
// 			String melody = MusicUtil.generateMelody(ch);
// 			String pattern = melody + "+" + ch.toString() + " ";
// 			music += pattern;
// 		}
		
// 		Pattern antPattern = new Pattern(music);
// 		saveSample(antPattern, "AnticipationOctave4.midi");
// 		System.out.println("Saved Anticipation Sample");


// 	}
	
// 	public static void generateFearSample() {
// 		String[] prog = {"C", "C", "G", "G", "D", "D", "E", "E"};
// 		String music = "I[ELECTRIC_GRAND] C C C C R R R R ";
// 		for(int i = 0; i < prog.length; i++) {
// 			Chord ch = MusicUtil.generateFearChord(prog[i]);
// 			String melody = MusicUtil.generateMelody(ch);
// 			String pattern = melody + "+" + ch.toString() + " ";
// 			music += pattern;
// 		}
		
// 		Pattern fearPattern = new Pattern(music);
// 		saveSample(fearPattern, "FearOctave4.midi");
// 		System.out.println("Saved Fear Sample");


// 	}	
// 	public static void main(String[] args) {
// 		generateJoySample();
// 		generateSadSample();
// 		generateAngerSample();
// 		generateAnticipationSample();
// 		generateFearSample();
		
// 	}

// }
