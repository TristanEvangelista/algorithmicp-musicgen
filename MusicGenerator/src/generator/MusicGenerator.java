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
	
	public void saveMusic(String name) {		
		try {
			MidiFileManager.savePatternToMidi(getMusic(), new File(name+".midi"));
		      
		} catch (IOException e) {
			e.printStackTrace();
			  	
		}
	}
	public void printMusicString() {
		System.out.println("Currently playing " + getMusic().toString());
	}

	public void generateMusic() {
		String musicString = "R R R R ";
		String[] emotions = params.getEmotions();
		int[] intensities = params.getIntensities();
		double[] intensityPercentage = {0,0,0,0,0,0,0,0};
		double intensityTotal = 0;
		for(int i = 0; i < intensities.length; i++){
			intensityTotal += intensities[i];
		}
		for(int i = 0; i < intensities.length; i++){
			intensityPercentage[i] = (intensities[i])/intensityTotal;
			System.out.println(intensities[i]/intensityTotal);
		}
		for(int i = 0; i < emotions.length; i++) {
			//System.out.println(emotions[i]);
			for(int j = 0; j < 1; j++) {
				musicString += generatePatternFromEmotion(emotions[i], intensityPercentage[i]);
				musicString += "R | ";
				//System.out.println(emotions[i]);
				System.out.println("creating " + emotions[i] + " pattern");
			}
		}
		setMusic(new Pattern(musicString));
	}

	public String generatePatternFromEmotion(String emotion, double intensityPercentage) {
		System.out.println("The emotion is: " + emotion + " with intensity of: " + intensityPercentage);
		String pattern = "";
	 
		// select the 2 pitches by random
		//Random rand = new Random();
		int pitchChosen = (int) intensityPercentage*7;
		String firstPitch = pitches[pitchChosen];
		String secondPitch = "";
		if(pitchChosen > 4){
			secondPitch = pitches[pitchChosen-1];
		} else {
			secondPitch = pitches[pitchChosen+1];
		}
		// select the chord
		
		Chord firstChord = generateChordFromEmotion(emotion, firstPitch);
		Chord secondChord = generateChordFromEmotion(emotion, secondPitch);
		
		String firstChordDuration = "";
		String secondChordDuration = "";
		
		List<Note> firstNotes = MusicUtil.getChordNotes(firstChord);
		List<Note> secondNotes = MusicUtil.getChordNotes(secondChord);

		switch(emotion) {
		case "trust":
			for(int i=0;i<firstNotes.size();i++){
				firstNotes.set( i, MusicUtil.changeOctave(firstNotes.get(i), 2));
			}
			for(int i=0;i<secondNotes.size();i++){
				secondNotes.set( i, MusicUtil.changeOctave(secondNotes.get(i), 2));
			}
			break;
		default:
			for(int i=0;i<firstNotes.size();i++){
				firstNotes.set( i, MusicUtil.changeOctave(firstNotes.get(i), 1));
			}
			for(int i=0;i<secondNotes.size();i++){
				secondNotes.set( i, MusicUtil.changeOctave(secondNotes.get(i), 1));
			}
			break;
		}

		//this is for emotions that dont 
		switch(emotion) {
		case "surprise":
			for (int i = 0; i < firstNotes.size(); i++) {
				pattern += firstNotes.get(i).getPattern().toString();
				if(i < firstNotes.size() - 1) {
					pattern += "I_";
				}
				else {
					pattern += "I";
				}
			}
			firstChordDuration = "q";
			pattern += "+" + firstChord.toString() + firstChordDuration;
			pattern += " ";
			
			for (int i = 0; i < secondNotes.size(); i++) {
				pattern += secondNotes.get(i).getPattern().toString();
				if(i < secondNotes.size() - 1) {
					pattern += "I_";
				}
				else {
					pattern += "I";
				}
			}
			secondChordDuration = "q";
			pattern += "+" + secondChord.toString() + secondChordDuration;
			pattern += " ";
			break;
		default:
			for (int i = 0; i < firstNotes.size(); i++) {
				pattern += firstNotes.get(i).getPattern().toString();
				if(i < firstNotes.size() - 1) {
					pattern += "q_";
				}
				else {
					pattern += "q";
				}
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
			}
			secondChordDuration = "w";
			pattern += "+" + secondChord.toString() + secondChordDuration;
			pattern += " ";
			break;
		}
		return pattern;
	}

	public Chord generateChordFromEmotion(String emotion, String pitch) {
		switch(emotion) {
		case "joy":
			return MusicUtil.generateJoyChord(pitch);
		case "sadness":
			return MusicUtil.generateSadChord(pitch);
		case "fear":
			return MusicUtil.generateFearChord(pitch);
		case "anticipation":
			return MusicUtil.generateAnticipationChord(pitch);
		case "anger":
			return MusicUtil.generateAngerChord(pitch);
		case "surprise":
			return MusicUtil.generateSurpriseChord(pitch);
		case "trust":
			return MusicUtil.generateTrustChord(pitch);
		case "disgust":
			return MusicUtil.generateDisgustChord(pitch);
		default:
			return MusicUtil.generateJoyChord(pitch);
		}
	}
}
