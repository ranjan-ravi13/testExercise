package com.ppro.qatest.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ppro.qatest.base.Base;
import com.ppro.qatest.pages.NetlifyPaymentPage;

public class PaymentPageTest extends Base {

	NetlifyPaymentPage PayPage;

	String expPayPageHeader = "Hello there 👋";
	String expErrMessage = "Transaction Failed";
	String expSucessMessage = "Transaction Succeded";
	String actMessage;
	String defaultPaymentStatusMessage = "Payment Pending";

	public PaymentPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		PayPage = new NetlifyPaymentPage();
	}

	@Test(priority = 1)
	public void PaymentPageHeaderTest() {
		String actPayPageHeader = PayPage.getHeader();
		Assert.assertEquals(actPayPageHeader, expPayPageHeader);
	}
	
	@Test(priority=2)
	
	public void defaultPaymentStatusMessageTest() {
		
		String actdefaultPaymentStatusMessage = PayPage.getTransactionMsg();
		Assert.assertEquals(actdefaultPaymentStatusMessage, defaultPaymentStatusMessage);
		
	}
	

	@Test(priority = 3)
	public void sucessPayTest() {

		PayPage.keyInAmount("555");
		PayPage.selectCurrency("EUR");
		PayPage.selectCountry("Germany");
		PayPage.clickonPay();
		actMessage = PayPage.getTransactionMsg();
		Assert.assertEquals(actMessage, expSucessMessage);

	}

	@Test(dataProvider = "getData")
	public void PaymentTest(String amt, String curr, String country, String expMessage) {

		PayPage.keyInAmount(amt);
		PayPage.selectCurrency(curr);
		PayPage.selectCountry(country);
		PayPage.clickonPay();
		actMessage = PayPage.getTransactionMsg();
		Assert.assertEquals(actMessage, expMessage, "DataDrivenTest" + amt);

	}

	@Test
	public void verifyMandatoryamount() {

		PayPage.selectCurrency("SGD");
		PayPage.selectCountry("Singapore");
		PayPage.clickonPay();
		// PayPage.getMandatoryToolTip();

	}

	@DataProvider(parallel = false)
	public Object[][] getData() {

		Object[][] data = new Object[4][4];

		data[0][0] = "555";
		data[0][1] = "EUR";
		data[0][2] = "Germany";
		data[0][3] = "Transaction Succeded";

		data[1][0] = "22";
		data[1][1] = "AUD";
		data[1][2] = "Germany";
		data[1][3] = "Transaction Failed";

		data[2][0] = "33";
		data[2][1] = "AUD";
		data[2][2] = "Australia";
		data[2][3] = "Transaction Succeded";

		data[3][0] = "22";
		data[3][1] = "SGD";
		data[3][2] = "Singapore";
		data[3][3] = "Transaction Succeded";

		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
