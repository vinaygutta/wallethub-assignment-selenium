package com.wallethub.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wallethub.BaseTest;

public class ReviewPage extends BaseTest {

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(1)")
	private WebElement firstStartSvg;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(1) > g path")
	private List<WebElement> firstStartPathEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(1) g")
	private List<WebElement> firstStartGrpEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(2)")
	private WebElement secondStartSvg;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(2) > g path")
	private List<WebElement> secondStartPathEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(2) g")
	private List<WebElement> secondStartGrpEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(3)")
	private WebElement thirdStartSvg;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(3) > g path")
	private List<WebElement> thirdStartPathEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(3) g")
	private List<WebElement> thirdStartGrpEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(4)")
	private WebElement fourthStartSvg;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(4) > g path")
	private List<WebElement> fourthStartPathEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(4) g")
	private List<WebElement> fourthStartGrpEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(5)")
	private WebElement fifthStartSvg;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(5) > g path")
	private List<WebElement> fifthStartPathEle;

	@FindBy(css = ".ng-enter-element .rating-box-wrapper .rvs-star-svg:nth-of-type(5) g")
	private List<WebElement> fifthStartGrpEle;

	@FindBy(css = ".bold-font.rsba-h3")
	private WebElement whatRatingText;

	@FindBy(css = ".ng-enter-element.rvtab-citem.rvtab-item-user > .rvtab-ci-top.text-select > .rvtab-ci-author.semi-bold-font > .rvtab-ci-name")
	private WebElement yourReviewText;

	@FindBy(css = "[data-pos='0']")
	private WebElement yourReviewDesc;

	public boolean checkAllRatingStarsNotLit() {

		/*
		 * check all the stars in review are not lit
		 */

		scrollToElement(whatRatingText, "Scrolling to rating submit location");

		int countg1 = getTotalElementsinList(firstStartGrpEle, "Getting number of g elements in the first Star");
		int countp1 = getTotalElementsinList(firstStartPathEle, "Getting number of path elements in the first Star");
		int countg2 = getTotalElementsinList(secondStartGrpEle, "Getting number of g elements in the second Star");
		int countp2 = getTotalElementsinList(secondStartPathEle, "Getting number of path elements in the second Star");
		int countg3 = getTotalElementsinList(thirdStartGrpEle, "Getting number of g elements in the third Star");
		int countp3 = getTotalElementsinList(thirdStartPathEle, "Getting number of path elements in the third Star");
		int countg4 = getTotalElementsinList(fourthStartGrpEle, "Getting number of g elements in the fourth Star");
		int countp4 = getTotalElementsinList(fourthStartPathEle, "Getting number of path elements in the fourth Star");
		int countg5 = getTotalElementsinList(fifthStartGrpEle, "Getting number of g elements in the fifth Star");
		int countp5 = getTotalElementsinList(fifthStartPathEle, "Getting number of path elements in the fifth Star");

		return ((countg1 == 1 && countp1 == 1 && countg2 == 1 && countp2 == 1 && countg3 == 1 && countp3 == 1
				&& countg4 == 1 && countp4 == 1 && countg5 == 1 && countp5 == 1));
	}

	public ReviewSubmitPage clickFourStars() {

		click(fourthStartSvg, "Clicking on 4 Stars");

		return new ReviewSubmitPage();
	}

	public boolean checkAllRatingStarsLitOnHover() {

		/*
		 * check all the stars in review are not lit
		 */

		scrollToElement(whatRatingText, "Scrolling to rating submit location");

		hoverOnElement(firstStartSvg, "Hovering Mouse on First Star");
		waitForSeconds(2);
		Assert.assertTrue(checkStarLit(firstStartPathEle, "Checking if the star is lit"));

		hoverOnElement(secondStartSvg, "Hovering Mouse on Second Star");
		waitForSeconds(2);
		Assert.assertTrue(checkStarLit(secondStartPathEle, "Checking if the star is lit"));

		hoverOnElement(thirdStartSvg, "Hovering Mouse on Second Star");
		waitForSeconds(2);
		Assert.assertTrue(checkStarLit(thirdStartPathEle, "Checking if the star is lit"));

		hoverOnElement(fourthStartSvg, "Hovering Mouse on Second Star");
		waitForSeconds(2);
		Assert.assertTrue(checkStarLit(fourthStartPathEle, "Checking if the star is lit"));

		hoverOnElement(fifthStartSvg, "Hovering Mouse on Second Star");
		waitForSeconds(2);
		Assert.assertTrue(checkStarLit(fifthStartPathEle, "Checking if the star is lit"));

		return (true);
	}

	public String returnYourReview() {
		scrollToElement(yourReviewText, "Scrolling to rating submit location");
		String actDispText = getElementText(yourReviewDesc, "Getting text");
		return actDispText;

	}

}
