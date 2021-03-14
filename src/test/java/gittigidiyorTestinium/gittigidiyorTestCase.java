package gittigidiyorTestinium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class gittigidiyorTestCase {

	@Test

	public void f() throws InterruptedException {

		// Define Chrome Browser
		System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Maximize the window and open the link
		driver.manage().window().maximize();
		driver.get("https://www.gittigidiyor.com/");

		// Manage time outs
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Check the link

		if (driver.getCurrentUrl().contains("https://www.gittigidiyor.com/")) {
			System.out.println("Gittigidiyor sayfasina giris yapildi");
			System.out.println("-----------------------------");
		} else {
			throw new WebDriverException("Gittigidiyor sayfasi acilamadi");
		}

		// AssertJUnit.assertEquals("https://www.gittigidiyor.com/",
		// driver.getCurrentUrl());

		// Navigate to the link

		driver.navigate().to("https://www.gittigidiyor.com/uye-girisi");

		// Write email,password and click the login button

		driver.findElement(By.xpath("//*[@id=\'L-UserNameField\']")).sendKeys("tnhnkky@gmail.com");
		driver.findElement(By.xpath("//*[@id=\'L-PasswordField\']")).sendKeys("Tnhnkky1.");
		driver.findElement(By.xpath("//*[@id='gg-login-enter']")).click();
		Thread.sleep(2000L);

//    Check the login page
		WebElement userCheck = driver.findElement(By.xpath("//span[contains(text(),'tunahanakkayaa6776')]"));
		if (userCheck.getText().contains("tunahanakkayaa6776")) {
			System.out.println("Login islemi basariyla gerceklesti");
			System.out.println("-----------------------------");

//			Search "Bilgisayar" 
			driver.findElement(
					By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"))
					.sendKeys("Bilgisayar");
			driver.findElement(
					By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button/span")).click();

//			Go to the second page 
			((JavascriptExecutor) driver).executeScript("scroll(0,9500)");
			driver.findElement(By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[7]/a")).click();

//			Check second page
			if (driver.getCurrentUrl().contains("sf=2")) {
				System.out.println("2. sayfaya gecis yaptiniz");
				System.out.println("-----------------------------");

			} else {
				System.out.println("2. sayfaya gidemediniz");
			}

//			Go to the item page
			((JavascriptExecutor) driver).executeScript("scroll(0,400)");
			driver.findElement(By.cssSelector(
					"body.desktop:nth-child(2) div.gray-content:nth-child(5) div.container:nth-child(8) div.clearfix div.ggfound-x-times.padding-none.mrb10.gg-uw-19.gg-w-18.gg-d-18.gg-t-24.gg-m-24.padding-none div.blueWrapper.clearfix:nth-child(3) div.clearfix:nth-child(4) ul.catalog-view.clearfix.products-container:nth-child(1) li.gg-uw-6.gg-w-8.gg-d-8.gg-t-8.gg-m-24.gg-mw-12.catalog-seem-cell.srp-item-list.browser:nth-child(2) a.product-link div.cell-border-css div.product-info-con.clearfix div.gg-w-24.gg-d-24.gg-t-24.gg-m-24.pl0.pr0.product-info-details:nth-child(1) div.gg-w-24.gg-d-24.gg-t-24.gg-m-24.product-title-info:nth-child(1) h3.product-title > span:nth-child(1)"))
					.click();
			WebElement productPriceInDetails = driver.findElement(By.xpath("/html//div[@id='sp-price-lowPrice']"));
			String listPrice = productPriceInDetails.getText();

//			Add to basket 
			((JavascriptExecutor) driver).executeScript("scroll(0,400)");
			driver.findElement(By.xpath("/html//button[@id='add-to-basket']")).click();

//	 		Go to the "Sepetim" page
			driver.findElement(By.xpath("/html//div[@id='header_wrapper']/div/div/div//div[@class='gg-d-12 pl0']/a[@href='https://www.gittigidiyor.com/sepetim']")).click();
			// driver.navigate().to("https://www.gittigidiyor.com/sepetim");

//			Check the product price
			WebElement productPriceInBasket = driver.findElement(By.xpath(".//*[@id='cart-price-container']/div[3]/p"));
			String basketPrice = productPriceInBasket.getText();
			Assert.assertEquals(listPrice, basketPrice);
			System.out.println("Urunun listelenen fiyati ile sepetteki fiyati esittir ve " + basketPrice + "'dir.");
			System.out.println("-----------------------------");
			
//			Product Quantity
			WebElement productQuantity = driver.findElement(By.xpath(
					"//body/div[@id='main-content']/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[4]/div[1]/div[2]/select[1]"));
			productQuantity.click();
			productQuantity.sendKeys("2");
			productQuantity.sendKeys(Keys.ENTER);
			Thread.sleep(2000L);

//	 		Check the product count
			WebElement productCount = driver.findElement(By.xpath(
					"//*[@id=\'submit-cart\']/div/div[2]/div[3]/div/div[1]/div/div[5]/div[1]/div/ul/li[1]/div[1]"));
			System.out.println(productCount.getText());
			System.out.println("-----------------------------");

			if (productCount.getText().contains("2 Adet")) {
				System.out.println("Sepetteki urun adedi 2'dir.");
				System.out.println("-----------------------------");

			} else {
				System.out.println("Sepetteki urun adedi 2 degildir.");
			}

//			Delete the item
			driver.findElement(By.xpath(
					"/html//div[@id='cart-items-container']/div[@class='products-container']/div/div//div[@class='row']/a[@title='Sil']/i[@class='gg-icon gg-icon-bin-medium']"))
					.click();

//			Close the window 
			driver.quit();

		}
	}
}
