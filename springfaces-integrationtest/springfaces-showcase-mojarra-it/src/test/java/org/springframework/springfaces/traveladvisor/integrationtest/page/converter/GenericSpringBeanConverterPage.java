package org.springframework.springfaces.traveladvisor.integrationtest.page.converter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.springfaces.integrationtest.selenium.page.PageObject;
import org.springframework.springfaces.integrationtest.selenium.page.PageURL;

@PageURL("/converter/genericspringbean")
public class GenericSpringConverterPage extends PageObject {

	public GenericSpringConverterPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected boolean isCorrectPage(String title) {
		return title.equals("Converter - Generic Spring (by ID)");
	}

	public void setInputText(CharSequence value) {
		getWebDriver().findElement(By.id("form:input")).sendKeys(value);
	}

	public GenericSpringConverterPage clickSubmitButton() {
		getWebDriver().findElement(By.id("form:submit")).click();
		return newPage(GenericSpringConverterPage.class);
	}

	public String getConversionText() {
		return getWebDriver().findElement(By.id("output")).getText();
	}
}