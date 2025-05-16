package com.comcast.crm.objrepo.utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
	@FindBy (xpath="//img[@title='Create Product...']")
	private WebElement CreateProdBtn;

	public WebElement getCreateProdBtn() {
		return CreateProdBtn;
	}

}
