package com.swtest.calc;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class TestFactory {

	private static BetCalculator scoreCalculator;

	@DataProvider
	public static Object[][] guessScore() {
		return new Object[][] { { "2-1", 1 }, { "2-2", 0 }, { "1-1", 0 },
				{ "1-2", 2 }, { "0-0", 0 } };
	}

	@DataProvider
	public static Object[][] totalGoal() {
		return new Object[][] { { "2-1", 3 }, { "2-2", 4 }, { "1-1", 2 },
				{ "5-8", 13 }, { "1-0", 1 }, { "0-0", 0 }, { "", -1 } };
	}

	@DataProvider
	public static Object[][] equalScore() {
		return new Object[][] { { "2-1", "2-1", true }, { "1-2", "1-2", true },
				{ "0-0", "0-0", true }, { "2-2", "2-1", false },
				{ "1-1", "2-1", false }, { "2-1", "0-0", false },
				{ "3-1", "1-3", false } };
	}

	@DataProvider
	public static Object[][] totalPoint() {
		return new Object[][] { { "2-1", "2-1", 6 }, { "2-1", "1-1", 0 },
				{ "1-2", "1-3", 3 }, { "2-0", "2-0", 5 }, { "0-0", "0-0", 4 },
				{ "3-3", "2-2", 3 }, { "3-3", "", 0 } };
	}

	@BeforeClass
	public static void Before() {

		scoreCalculator = BetCalculator.getInstance();
	}

	@Test
	@UseDataProvider("guessScore")
	public void testWinner(String score, int result) {
		Match m = new Match(score);
		scoreCalculator.setMatchs(m, null);
		Assert.assertEquals(result, scoreCalculator.findWinner(m));
	}

	@Test
	@UseDataProvider("totalGoal")
	public void testTotalGoal(String score, int result) {
		Match m = new Match(score);
		scoreCalculator.setMatchs(m, null);
		Assert.assertEquals(result, scoreCalculator.findGoalNumber(m));
	}

	@Test
	@UseDataProvider("equalScore")
	public void testScore(String realScore, String userScore, boolean result) {
		Match reamM = new Match(realScore);
		Match userM = new Match(userScore);
		scoreCalculator.setMatchs(reamM, userM);
		Assert.assertEquals(result, scoreCalculator.matchScore(reamM, userM));
	}

	@Test
	@UseDataProvider("totalPoint")
	public void totalPoint(String realScore, String userScore, int totalPoint) {
		Match reamM = new Match(realScore);
		Match userM = new Match(userScore);
		scoreCalculator.setMatchs(reamM, userM);
		Assert.assertEquals(totalPoint,
				scoreCalculator.calculateMatchPoints(reamM, userM));
	}

}
