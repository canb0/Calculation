package com.atkafasi.Bahis;

public class BetCalculator {

	static String emptyString = "";

	private static BetCalculator instance = null;
	private Match realMatch;
	private Match userMatch;

	protected BetCalculator() {

	}

	public static BetCalculator getInstance() {
		if (instance == null) {
			instance = new BetCalculator();
		}
		return instance;
	}

	public void setMatchs(Match realMatch, Match userMatch) {
		this.realMatch = realMatch;
		this.userMatch = userMatch;
		if (this.realMatch != null)
			this.realMatch.calculateScore();
		if (this.userMatch != null)
			this.userMatch.calculateScore();
	}


	public int findWinner(Match match) {

		if (!match.getScore().equalsIgnoreCase(emptyString)) {

			if (match.getFirstTeamScore() > match.getSecondTeamScore())
				return 1;
			else if (match.getFirstTeamScore() < match.getSecondTeamScore())
				return 2;
			else
				return 0;
		} else
			return -1;

	}

	public boolean matchScore(Match realMatch, Match userMatch) {
		if (realMatch.getScore().equalsIgnoreCase(userMatch.getScore()))
			return true;
		else
			return false;
	}

	public int findGoalNumber(Match match) {
		if (!match.getScore().equalsIgnoreCase(emptyString)) {
			return match.getTotalGoal();
		} else
			return -1;
	}

	public int calculateMatchState(Match realMatch, Match userMatch) {

		int realMatchWinner = findWinner(realMatch);
		int userMatchWinner = findWinner(userMatch);
		if (realMatchWinner == userMatchWinner) {
			// check if user guessed the score
			if (matchScore(realMatch, userMatch)) {
				return findGoalNumber(realMatch) + 3;
			} else {
				return 3;
			}

		} else {
			System.out
					.println("maç sonucunu bilemedin. Skor hesabı yapmıyorum.");
			return 0;
		}

	}

}
