package br.ce.wc.acquino.tasks.prod;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HealthCheckIT {

	@Test
	public void healthCheck() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\.jenkins\\seleniumDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("http://localhost:9999/tasks/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}
	}
}
