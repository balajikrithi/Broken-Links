package org.xcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.format.CellFormat;
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


public class FlipkartXcel {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
			
			driver.findElement(By.xpath("//button[text()='✕']")).click();
			driver.findElement(By.name("q")).sendKeys("Oneplus mobiles", Keys.ENTER);
			File f= new File("C:\\Users\\DELL\\eclipse-workspace\\SimpleProject\\target\\writeExcel.xlsx");
			FileOutputStream F = new FileOutputStream(f);
			XSSFWorkbook w= new XSSFWorkbook();
			Sheet s= w.createSheet("Oneplus");
			
			List<WebElement> mobile= driver.findElements(By.xpath("//div[@class='_4rR01T']"));
			for (int i = 0; i < mobile.size(); i++) {
				WebElement mobile1= mobile.get(i);
				String text= mobile1.getText();
				System.out.println(text);
				Thread.sleep(2000);
				Row r= s.createRow(i);
				Cell c= r.createCell(0);
				c.setCellValue(text);
				
			}
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	