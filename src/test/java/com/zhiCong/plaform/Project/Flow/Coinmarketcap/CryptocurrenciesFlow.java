package com.zhiCong.Plaform.Project.Flow.Coinmarketcap;

import com.zhiCong.Plaform.Base.BaseFlow;
import com.zhiCong.Plaform.Base.Config.WebDriverConfig;
import com.zhiCong.Plaform.Project.Page.Coinmarketcap.CryptocurrenciesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;

public class CryptocurrenciesFlow extends BaseFlow {

  private CryptocurrenciesPage cryptocurrenciesPage;
  private WebDriver webDriver;

  public CryptocurrenciesFlow() {
    cryptocurrenciesPage = new CryptocurrenciesPage();
    webDriver = WebDriverConfig.getDriver();
  }

  public boolean isCoinmarketcapScreenDisplayed() {
    return checkForElement(cryptocurrenciesPage.coinmarketcapTitle, 120);
  }

  public int swipeDownGetAllCurrencyListSize() {
    // 等待Currency 列表刷新
    waitForElements(cryptocurrenciesPage.currencyNameList);
    // 滑动到页面底部
    swipeToBottom();
    // 获取Currency list name
    List<WebElement> currencyNameList = cryptocurrenciesPage.currencyNameList;
    List<String> allCurrencyName = new ArrayList<>();
    for (WebElement webElement : currencyNameList) {
      allCurrencyName.add(webElement.getText());
    }
    System.out.println("All currency list : " + allCurrencyName);
    System.out.println("Currency list size : " + currencyNameList.size());
    return currencyNameList.size();
  }

  public void clickBackToTopButton() {
    waitForElementClickable(cryptocurrenciesPage.backToTopButton).click();
  }

  public void clickSearchBar() {
    waitForElement(cryptocurrenciesPage.searchBar).click();
  }

  public void selectFirstSearchResult() {
    // 等待search result刷新并选择第一个
    waitForElement(cryptocurrenciesPage.searchResultList, 120).click();
  }

  public void inputSearchInputBar(String data) {
    waitForElement(cryptocurrenciesPage.searchInputBar).sendKeys(data);
  }

  public void clickAcceptCookieButton() {
    // 如果Accept Cookie Button存在则点击
    if (checkForElement(cryptocurrenciesPage.acceptCookieButton, 30)) {
      swipeToDown(100);
      waitForSeconds(1);
      cryptocurrenciesPage.acceptCookieButton.click();
    }
  }
}
