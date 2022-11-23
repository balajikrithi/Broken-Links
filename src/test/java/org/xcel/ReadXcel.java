package org.xcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy.Simple;

public class ReadXcel {

	public static void main(String[] args) throws Throwable {
		
		File f= new File("C:\\Users\\DELL\\eclipse-workspace\\SimpleProject\\target\\writeExcel.xlsx");
		FileInputStream ip= new FileInputStream(f);
		Workbook w= new XSSFWorkbook(ip);
		Sheet s= w.getSheet("Oneplus");
		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
			Row r= s.getRow(i);
			for (int j = 0; j < r.getPhysicalNumberOfCells(); j++) {
				Cell c= r.getCell(j);
				int cellType= c.getCellType();
				if (cellType==1) {
					String val= c.getStringCellValue();
					System.out.println(val);
				}
				else if (cellType==0) {
					if (DateUtil.isCellDateFormatted(c)) {
						Date d= c.getDateCellValue();
						System.out.println(d);
						SimpleDateFormat s1= new SimpleDateFormat("yyyy/MM/dd");
						String val= s1.format(d);
						System.out.println(val);
					}
					else {
						double d= c.getNumericCellValue();
						long l= (long)d;
						String val= String.valueOf(l);
						System.out.println(val);
				}
				}
			}
		}
		
	}
}
