package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\.jenkins\\seleniumDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		//DesiredCapabilities cap = DesiredCapabilities.chrome();
		//WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.187:4040/wd/hub"), cap);
		//driver.navigate().to("http://192.168.1.187:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("29/01/2024");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", message);

		} finally {
			// fechar o browser
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("29/01/2024");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);

		} finally {
			// fechar o browser
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);

		} finally {
			// fechar o browser
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("29/01/2021");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);

		} finally {
			// fechar o browser
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
	}
}
