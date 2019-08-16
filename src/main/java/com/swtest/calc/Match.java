package com.swtest.calc;

public class Match {

	private String score;
	private int firstTeamScore;
	private int secondTeamScore;
	static private String EMPTYSTRING = "";

	public Match(String score) {
		this.score = score;
	}

	public String getScore() {
		return this.score;
	}

	private int convertInt(String score) {
		return Integer.parseInt(score);
	}

	public void calculateScore() {
		if (!this.score.equalsIgnoreCase(EMPTYSTRING)) {
			String[] modified_score = score.split("-");
			this.firstTeamScore = convertInt(modified_score[0]);
			this.secondTeamScore = convertInt(modified_score[1]);
		}
	}

	public int getFirstTeamScore() {
		return this.firstTeamScore;
	}

	public int getSecondTeamScore() {
		return this.secondTeamScore;
	}

	public int getTotalGoal() {
		return getFirstTeamScore() + getSecondTeamScore();
	}

}
