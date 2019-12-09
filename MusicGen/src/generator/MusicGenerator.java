package generator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

public class MusicGenerator {

	private Pattern music;
	private InputParameters params;
	private String[] pitches = {"C", "D", "E", "F", "G", "A", "B"};
	
	public MusicGenerator(InputParameters params) {
		this.params = params;
		this.music = new Pattern("R R R R");
		
	}
	
	public Pattern getMusic() {
		return music;
	}
	
	public void setMusic(Pattern music) {
		this.music = music;
	}
	
	public void playMusic() {
		Player player = new Player();
		player.play(getMusic());
	
	}
	
	public void saveMusic() {		
		
		try {
			MidiFileManager.savePatternToMidi(getMusic(), new File(params.getName()+".midi"));
		      
		} catch (IOException e) {
			e.printStackTrace();
			  	
		}
	}
	
	public void printMusicString() {
		System.out.println("Currently playing " + getMusic().toString());
	}
		
	public String createJoyPattern() {
		String pattern = "";
	 
		// select the 2 pitches by random
		
		Random rand = new Random();
		
		
		String firstPitch = pitches[rand.nextInt(7)];
		String secondPitch = pitches[rand.nextInt(7)];
		
		// select the chord
		
		Chord firstChord = MusicUtil.generateJoyChord(firstPitch);
		Chord secondChord = MusicUtil.generateJoyChord(secondPitch);
		
		String firstChordDuration = "";
		String secondChordDuration = "";
		
		List<Note> firstNotes = MusicUtil.getChordNotes(firstChord);
		List<Note> secondNotes = MusicUtil.getChordNotes(secondChord);
		
		for (int i = 0; i < firstNotes.size(); i++) {
			pattern += firstNotes.get(i).getPattern().toString();
			if(i < firstNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
			firstChordDuration += "q";

		}
		
		pattern += "+" + firstChord.toString() + firstChordDuration;
		pattern += " ";
		

		
		for (int i = 0; i < secondNotes.size(); i++) {
			pattern += secondNotes.get(i).getPattern().toString();
			if(i < secondNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
			
			secondChordDuration += "q";
		}
		
		pattern += "+" + secondChord.toString() + secondChordDuration;
		pattern += " ";
		
		return pattern;
	}
	
	
	public String createSadPattern() {
		String pattern = "";
	 
		// select the 2 pitches by random
		
		Random rand = new Random();
		
		
		String firstPitch = pitches[rand.nextInt(7)];
		String secondPitch = pitches[rand.nextInt(7)];
		
		// select the chord
		
		Chord firstChord = MusicUtil.generateSadChord(firstPitch);
		Chord secondChord = MusicUtil.generateSadChord(secondPitch);
		
		int firstChordDuration = 0;
		int secondChordDuration = 0;
		
		List<Note> firstNotes = MusicUtil.getChordNotes(firstChord);
		List<Note> secondNotes = MusicUtil.getChordNotes(secondChord);
		
		for (int i = 0; i < firstNotes.size(); i++) {
			pattern += firstNotes.get(i).getPattern().toString();
			if(i < firstNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
		}
		
		pattern += "+" + firstChord.toString() + "w";
		pattern += " ";
		

		
		for (int i = 0; i < secondNotes.size(); i++) {
			pattern += secondNotes.get(i).getPattern().toString();
			if(i < secondNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
		}
		
		pattern += "+" + secondChord.toString() + "w";
		pattern += " ";

		
		return pattern;
	}
	
	public String createFearPattern() {
		String pattern = "";
	 
		// select the 2 pitches by random
		
		Random rand = new Random();
		
		
		String firstPitch = pitches[rand.nextInt(7)];
		String secondPitch = pitches[rand.nextInt(7)];
		
		// select the chord
		
		Chord firstChord = MusicUtil.generateFearChord(firstPitch);
		Chord secondChord = MusicUtil.generateFearChord(secondPitch);
		
		String firstChordDuration = "";
		String secondChordDuration = "";
		
		List<Note> firstNotes = MusicUtil.getChordNotes(firstChord);
		List<Note> secondNotes = MusicUtil.getChordNotes(secondChord);
		
		for (int i = 0; i < firstNotes.size(); i++) {
			pattern += firstNotes.get(i).getPattern().toString();
			if(i < firstNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
			firstChordDuration += "q";

		}
		
		pattern += "+" + firstChord.toString() + firstChordDuration;
		pattern += " ";
		

		
		for (int i = 0; i < secondNotes.size(); i++) {
			pattern += secondNotes.get(i).getPattern().toString();
			if(i < secondNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
			
			secondChordDuration += "q";
		}
		
		pattern += "+" + secondChord.toString() + secondChordDuration;
		pattern += " ";
		
		return pattern;
	}
	
	public Chord generateChordFromEmotion(String emotion, String pitch) {
		switch(emotion) {
		case "joy":
			System.out.println("joy");
			return MusicUtil.generateJoyChord(pitch);
		case "sadness":
			return MusicUtil.generateSadChord(pitch);
		case "fear":
			return MusicUtil.generateFearChord(pitch);
		case "anticipation":
			return MusicUtil.generateAnticipationChord(pitch);
		case "anger":
			return MusicUtil.generateAngerChord(pitch);
		default:
			return MusicUtil.generateJoyChord(pitch);
			
		}
	}
	public String generatePatternFromEmotion(String emotion) {
		System.out.println("The emotion is: " + emotion);
		String pattern = "";
	 
		// select the 2 pitches by random
		
		Random rand = new Random();
		
		
		String firstPitch = pitches[rand.nextInt(7)];
		String secondPitch = pitches[rand.nextInt(7)];
		
		// select the chord
		
		Chord firstChord = generateChordFromEmotion(emotion, firstPitch);
		Chord secondChord = generateChordFromEmotion(emotion, secondPitch);
		
		String firstChordDuration = "";
		String secondChordDuration = "";
		
		List<Note> firstNotes = MusicUtil.getChordNotes(firstChord);
		List<Note> secondNotes = MusicUtil.getChordNotes(secondChord);
		
		for (int i = 0; i < firstNotes.size(); i++) {
			pattern += firstNotes.get(i).getPattern().toString();
			if(i < firstNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
			firstChordDuration += "q";

		}
		
		firstChordDuration = "w";
		
		pattern += "+" + firstChord.toString() + firstChordDuration;
		pattern += " ";
		

		
		for (int i = 0; i < secondNotes.size(); i++) {
			pattern += secondNotes.get(i).getPattern().toString();
			if(i < secondNotes.size() - 1) {
				pattern += "q_";
			}
			else {
				pattern += "q";
			}
			
			secondChordDuration += "q";
		}
		
		secondChordDuration = "w";
		
		pattern += "+" + secondChord.toString() + secondChordDuration;
		pattern += " ";
		
		return pattern;
	}
		
	public void generateMusic() {
		
		String musicString = "R R R R ";
		String[] emotions = params.getEmotions();
		int[] intensities = params.getIntensities();
		
		// each section is repeated by the number of the intensity
		
		for(int i = 0; i < emotions.length; i++) {
			System.out.println(emotions[i]);
			for(int j = 0; j < intensities[i]; j++) {
				musicString += generatePatternFromEmotion(emotions[i]);
				musicString += "| ";
				System.out.println(emotions[i]);
				System.out.println("creating " + emotions[i] + " pattern");
				
			}
		}
				
		
		setMusic(new Pattern(musicString));

	
	}
	
	
	
}
