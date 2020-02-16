package generator;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Tester {
	
	public static void main(String[] args) throws Exception {
		// read the input json
		Gson gson = new Gson();
		String name = "finaloutput";
		File file = new File("./Parameters/data1.json");
		JsonReader reader;
		reader = new JsonReader(new FileReader(file));
		InputParameters params = gson.fromJson(reader, InputParameters.class);
		
		MusicGenerator musicGenerator = new MusicGenerator(params);
		musicGenerator.generateMusic();
		musicGenerator.printMusicString();
		//musicGenerator.playMusic();
		musicGenerator.saveMusic("./MusicOutputs/finaloutput");
		
		
		
//		for(String emo : emotions) {
//			switch(emo) {
//			case "anger":
//				SampleGenerator.generateAngerSample();
//				break;
//				
//			case "joy":
//				SampleGenerator.generateJoySample();
//				break;
//				
//			case "sadness":
//				SampleGenerator.generateSadSample();
//				break;
//				
//			case "fear":
//				SampleGenerator.generateFearSample();
//				break;
//				
//			case "anticipation":
//				SampleGenerator.generateAnticipationSample();
//				break;
//				
//			default:
//				SampleGenerator.generateJoySample();
//				
//			}
//			
//		}
			
			
	}

}
