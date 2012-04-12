/*
 * Copyright 2010-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.springfaces.traveladvisor.integrationtest.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.springfaces.integrationtest.selenium.page.PageURL;

@PageURL("/spring/advisor/cities/search")
public class CitySearchPage extends BasePage {

	public CitySearchPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected boolean isCorrectPage(String title) {
		return title.endsWith("Search");
	}

	public CityPage searchForSingleCity(String query) {
		WebElement input = getInputElement();
		input.sendKeys(query);
		waitOnUrlChange(getSearchButton()).click();
		return newPage(CityPage.class);
	}

	private WebElement getInputElement() {
		return formElementById("query_input");
	}

	private WebElement getSearchButton() {
		return formElementById("search");
	}
}
