package com.swtest.calc;

import junit.framework.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestFactory {

	BetCalculator scoreCalculator;

	@DataProvider(name = "guessScore")
	public static Object[][] data_guess_result() {
		return new Object[][] { { "2-1", 1 }, { "2-2", 0 }, { "1-1", 0 },
				{ "1-2", 2 }, { "0-0", 0 } };
	}

	@DataProvider(name = "totalGoal")
	public static Object[][] data_total_goal() {
		return new Object[][] { { "2-1", 3 }, { "2-2", 4 }, { "1-1", 2 },
				{ "5-8", 13 }, { "1-0", 1 }, { "0-0", 0 }, { "", -1 } };
	}

	@DataProvider(name = "equalScore")
	public static Object[][] data_equal_score() {
		return new Object[][] { { "2-1", "2-1", true }, { "1-2", "1-2", true },
				{ "0-0", "0-0", true }, { "2-2", "2-1", false },
				{ "1-1", "2-1", false }, { "2-1", "0-0", false },
				{ "3-1", "1-3", false } };
	}

	@DataProvider(name = "totalpoint")
	public static Object[][] totalPoint() {
		return new Object[][] { { "2-1", "2-1", 6 }, { "2-1", "1-1", 0 },
				{ "1-2", "1-3", 3 }, { "2-0", "2-0", 5 }, { "0-0", "0-0", 4 },
				{ "3-3", "2-2", 3 }, { "3-3", "", 0 } };
	}

	@BeforeClass
	public void Before() {

		scoreCalculator = BetCalculator.getInstance();
	}

	@Test(dataProvider = "guessScore")
	public void testWinner(String score, int result) {
		Match m = new Match(score);
		scoreCalculator.setMatchs(m, null);
		Assert.assertEquals(result, scoreCalculator.findWinner(m));
	}

	@Test(dataProvider = "totalGoal")
	public void testTotalGoal(String score, int result) {
		Match m = new Match(score);
		scoreCalculator.setMatchs(m, null);
		Assert.assertEquals(result, scoreCalculator.findGoalNumber(m));
	}

	@Test(dataProvider = "equalScore")
	public void testScore(String realScore, String userScore, boolean result) {
		Match reamM = new Match(realScore);
		Match userM = new Match(userScore);
		scoreCalculator.setMatchs(reamM, userM);
		Assert.assertEquals(result, scoreCalculator.matchScore(reamM, userM));
	}

	@Test(dataProvider = "totalPoint")
	public void totalPoint(String realScore, String userScore, int totalPoint) {
		Match reamM = new Match(realScore);
		Match userM = new Match(userScore);
		scoreCalculator.setMatchs(reamM, userM);
		Assert.assertEquals(totalPoint,
				scoreCalculator.calculateMatchPoints(reamM, userM));
	}

}
