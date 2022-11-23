package org.xcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelWrite {

	public static void main(String[] args) throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		driver.findElement(By.name("q")).sendKeys("Oneplus mobiles", Keys.ENTER);
		File f= new File("C:\\Users\\DELL\\eclipse-workspace\\SimpleProject\\target\\flipkartExcel.xlsx");
		FileOutputStream F = new FileOutputStream(f);
		XSSFWorkbook w= new XSSFWorkbook();
		Sheet s= w.createSheet("Oneplus");
		Map<String, String> m= new HashMap<String, String>();
		List<WebElement> mobile= driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		List<WebElement> rate= driver.findElements(By.xpath("//div[@class='_25b18c']"));
		for (int i = 0; i < mobile.size(); i++) {
			WebElement mobile1= mobile.get(i);
			String text= mobile1.getText();
		for (int j = 0; j < rate.size(); j++) {
			WebElement rates=  rate.get(j);
			String rate1 =  rates.getText();
			m.put(text, rate1);
		}
			}
		Set<java.util.Map.Entry<String, String>> d = m.entrySet();
		for (java.util.Map.Entry<String, String> entry : d) {
			System.out.println(entry);		
		}
			Thread.sleep(2000);
			Row r1= s.createRow(i);
			Row r2= s.createRow(j);
			Cell c1= r1.createCell(0);
			Cell c2= r2.createCell(0);
			c1.setCellValue(text);
			c2.setCellValue(rate1);
			
		
		w.write(F);
		F.close();
	
		
		FileInputStream ip= new FileInputStream(f);
		Workbook w1= new XSSFWorkbook(ip);
		
		Sheet s1= w1.getSheet("Oneplus");
		Row row= s1.getRow(0);
		Cell cell= row.getCell(0);
		String S= cell.getStringCellValue();
		System.out.println("Third Oneplus mobile is: ");
		System.out.println(S);
		}
		
		}
}


