package com.ppro.qatest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ppro.qatest.base.Base;

public class NetlifyPaymentPage extends Base {

	// Pagefactory - Object Repository

	@FindBy(xpath = "//h3[@class='greeting']")
	WebElement header;

	@FindBy(id = "amount")
	WebElement amount;

	@FindBy(id = "currency")
	WebElement currency;

	@FindBy(id = "country")
	WebElement country;

	@FindBy(xpath = "//button[text()='Pay']")
	WebElement payBtn;

	@FindBy(xpath = "//p[@id='pay']")
	WebElement message;

	// initializing the page objects
	public NetlifyPaymentPage() {
		PageFactory.initElements(driver, this);

	}

	// Actions

	public String getHeader() {
		header.isDisplayed();
		return header.getText();
	}

	public void keyInAmount(String amt) {

		amount.sendKeys(amt);
	}
	
	public void selectCurrency(String curr) {
		
		Select select = new Select(currency);
		select.selectByVisibleText(curr);
		
		
	}
	
	public void selectCountry(String cuntry) {
		
		Select select = new Select(country);
		select.selectByVisibleText(cuntry);
		
	}
	
	public void clickonPay() {
		
		payBtn.click();
		
	}

	public String getTransactionMsg() {

		String transactionMsg = message.getText();
		return transactionMsg;
	}

}
