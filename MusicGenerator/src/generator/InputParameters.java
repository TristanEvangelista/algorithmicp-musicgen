package generator;

public class InputParameters {
	private String name;
	private int numSections;
	private String[] emotions;
	private int[] intensity;
	private int[] totalEmotions;
	
	public InputParameters(String name, int numSections, String[] emotions, int[] intensity, int[] totalEmotions) {
		this.name = name;
		this.numSections = numSections;
		this.emotions = emotions;
		this.intensity = intensity;
		this.totalEmotions = totalEmotions;
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

	public int[] getTotalEmotions() {
		return totalEmotions;
	}

	public void setTotalEmotions(int[] totalEmotions) {
		this.totalEmotions = totalEmotions;
	}
}



