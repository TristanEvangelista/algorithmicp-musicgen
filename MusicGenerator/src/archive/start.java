package archive;


import org.jfugue.*;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.*;
import java.util.*;

public class start {
	public final static String[] chords = {"C","D","E","F","G","A","B"};
	
	public static void main(String[] args) {
		File file = new File("parameters.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int sections = 0;
		String music = "";
		sections = Integer.parseInt(sc.next());
		
		String[] overallParameters = new String[8];
		int[] overallIntensity = new int[8];
		for(int i=0;i<8;i++) {
			overallParameters[i] = sc.next();
			overallIntensity[i] = Integer.parseInt(sc.next());
		}
		String[] parameters = new String[sections];
		int[] intensity = new int[sections];
		for(int i=0;i<sections;i++) {
			parameters[i] = sc.next();
			intensity[i] = Integer.parseInt(sc.next());
		}
		for(int i=0;i<sections;i++) {
			if(parameters[i].equals("joy")) {
				music = Joy(intensity[i], music);
			}
			if(parameters[i].equals("sadness")) {
				music = Sadness(intensity[i], music);
			}
			if(parameters[i].equals("anger")) {
				music = Anger(intensity[i], music);
			}
			if(parameters[i].equals("anticipation")) {
				music = Anticipation(intensity[i], music);
			}
			if(parameters[i].equals("disgust")) {
				music = Disgust(intensity[i], music);
			}
			if(parameters[i].equals("fear")) {
				music = Fear(intensity[i], music);
			}
			if(parameters[i].equals("surprise")) {
				music = Surprise(intensity[i], music);
			}
			if(parameters[i].equals("trust")) {
				music = Trust(intensity[i], music);
			}
		}
		
		
		Player player = new Player();
		System.out.println(music);
		Pattern pattern = new Pattern(music);
		player.play(pattern);
        try {
            MidiFileManager
                    .savePatternToMidi(pattern, new File("TestOutput.midi"));
        } catch (IOException ex) {
        	
        }
	}
	
	static String Joy(int intensity, String music) {
		String joyChords = " ";
		for(int i=0; i<intensity;i++) {
			joyChords = chords[i] + "maj ";
			music = music + joyChords;
		}
		System.out.println(music);
		return music;
	}
	static String Sadness(int intensity, String music) {
		String sadChords = " ";
		for(int i=0; i<intensity;i++) {
			sadChords = chords[i] + "min ";
			music = music + sadChords;
		}
		System.out.println(music);
		return music;
	}
	static String Anger(int intensity, String music) {
		String angryChords = " ";
		for(int i=0; i<intensity;i++) {
			angryChords = chords[i] + "dom9 ";
			music = music + angryChords;
		}
		System.out.println(music);
		return music;
	}
	static String Anticipation(int intensity, String music) {
		String anticipationChords = " ";
		for(int i=0; i<intensity;i++) {
			anticipationChords = chords[i] + "sus4 ";
			music = music + anticipationChords;
		}
		System.out.println(music);
		return music;
	}
	static String Disgust(int intensity, String music) {
		String disgustChords = " ";
		for(int i=0; i<intensity;i++) {
			disgustChords = chords[i] + "w ";
			music = music + disgustChords;
		}
		System.out.println(music);
		return music;
	}
	static String Fear(int intensity, String music) {
		String scaryChords = " ";
		for(int i=0; i<intensity;i++) {
			scaryChords = chords[i] + "min9 ";
			music = music + scaryChords;
		}
		System.out.println(music);
		return music;
	}
	static String Surprise(int intensity, String music) {
		String surpriseChords = "Fw ";
		for(int i=0; i<intensity;i++) {
			surpriseChords = chords[i] + "w ";
			music = music + surpriseChords;
		}
		System.out.println(music);
		return music;
	}
	static String Trust(int intensity, String music) {
		String trustChords = "Fw ";
		for(int i=0; i<intensity;i++) {
			trustChords = chords[i] + "w ";
			music = music + trustChords;
		}
		System.out.println(music);
		return music;
	}
	
}

