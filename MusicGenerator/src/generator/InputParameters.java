package generator;

public class InputParameters {
	private String name;
	private int numSections;
	private String[] emotions;
	private int[] intensity;
	private int[] overall;
	
	public InputParameters(String name, int numSections, String[] emotions, int[] intensity, int[] overall) {
		this.name = name;
		this.numSections = numSections;
		this.emotions = emotions;
		this.intensity = intensity;
		this.overall = overall;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumSections() {
		return numSections;
	}

	public void setNumSections(int numSections) {
		this.numSections = numSections;
	}

	public String[] getEmotions() {
		return emotions;
	}

	public void setEmotions(String[] emotions) {
		this.emotions = emotions;
	}

	public int[] getIntensities() {
		return intensity;
	}

	public void setIntensities(int[] intensities) {
		this.intensity = intensities;
	}

	public int[] getOverall() {
		return overall;
	}

	public void setOverall(int[] overall) {
		this.overall = overall;
	}
}



