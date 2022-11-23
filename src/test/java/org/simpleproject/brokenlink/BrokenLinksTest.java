package org.simpleproject.brokenlink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinksTest {
	public static void main(String[] args) throws IOException, Throwable {
		
	WebDriverManager.chromedriver().setup();
			WebDriver driver= new ChromeDriver();
		
			driver.manage().window().maximize();
		
			driver.manage().deleteAllCookies();
			
		
			driver.get("https://www.amazon.com/");
			Thread.sleep(3000);
			List<WebElement> linksList= driver.findElements(By.tagName("a"));
			linksList.addAll(driver.findElements(By.tagName("img")));
			
			System.out.println("size of full Links & images:"+ linksList.size());
			
			List<WebElement> activeLinks= new ArrayList<WebElement>();
			
			
			for (int i = 0; i < linksList.size(); i++) {
				System.out.println(linksList.get(i).getAttribute("href"));
				if (linksList.get(i).getAttribute("href") !=null && (! linksList.get(i).getAttribute("href").contains("javascript"))) {
					activeLinks.add(linksList.get(i));
				}
			}
			
			System.out.println("size of activeLinks & images:"+ activeLinks.size());
			
			
			for (int j = 0; j < activeLinks.size(); j++) {
				
				HttpURLConnection connection= (HttpURLConnection)new URL(activeLinks.get(j).getAttribute("href")).openConnection();
				connection.connect();
				String response= connection.getResponseMessage();
				connection.disconnect();
				System.out.println(activeLinks.get(j).getAttribute("href")+"---->"+response);
			}
		}


}
