package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
public static WebDriver driver;
public static ExtentReports report;
public static ExtentTest test;
public void ERP_Test()throws Throwable
{
	//creating excel util methods
	ExcelFileUtil xl=new ExcelFileUtil();
	//itterrating all rows in master testcase sheet
	for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
	{
		if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
		{
			//store module name into TCModuleS
			String TcModule=xl.getCellData("MasterTestCases", i, 1);
			//generate user define html report
			report=new ExtentReports("./Reports//"+TcModule+FunctionLibrary.genarateDate()+".html");
			//iterate all rows inTCModule sheet
			for(int j=1;j<=xl.rowCount(TcModule);j++)
			{
				test=report.startTest(TcModule);
				//read all columns for TCModule
				String Description=xl.getCellData(TcModule, j, 0);
				String Function_Name=xl.getCellData(TcModule, j, 1);
				String Locator_Type=xl.getCellData(TcModule, j, 2);
				String Locator_value=xl.getCellData(TcModule, j, 3);
				String Test_Data=xl.getCellData(TcModule, j, 4);
				try{
				if(Function_Name.equalsIgnoreCase("startBrowser"))
				{
					driver=FunctionLibrary.startBrowser();
					System.out.println("Exicuting Start Browser");
					test.log(LogStatus.INFO,Description);
				}
				else if(Function_Name.equalsIgnoreCase("openApplication"))
				{
					FunctionLibrary.openApplication(driver);
					System.out.println("Exicuting open Application");
					test.log(LogStatus.INFO,Description);
				}
				else if(Function_Name.equalsIgnoreCase("waitForElement"))
				{
					FunctionLibrary.waitForElement(driver, Locator_Type, Locator_value, Test_Data);
					System.out.println("Exicuting wait for element");
					test.log(LogStatus.INFO,Description);
				}
				else if(Function_Name.equalsIgnoreCase("typeAction"))
				{
					FunctionLibrary.typeAction(driver, Locator_Type, Locator_value, Test_Data);
					System.out.println("Exicuting type action");
					test.log(LogStatus.INFO,Description);
				}
				else if(Function_Name.equalsIgnoreCase("clickAction"))
				{
					FunctionLibrary.clickAction(driver, Locator_Type, Locator_value);
					System.out.println("Exicuting click action");
					test.log(LogStatus.INFO,Description);
					
				}
				else if(Function_Name.equalsIgnoreCase("closeBrowser"))
				{
					FunctionLibrary.closeBrowser(driver);
					System.out.println("Exicuting close Browser");
					test.log(LogStatus.INFO,Description);
				}
				else if(Function_Name.equalsIgnoreCase("captureData"))
				{
					FunctionLibrary.captureData(driver, Locator_Type, Locator_value);
					test.log(LogStatus.INFO,Description);
				}
				else if (Function_Name.equalsIgnoreCase("tableValidation"))
				{
					FunctionLibrary.tableValidation(driver, Test_Data);
					test.log(LogStatus.INFO,Description);
				}
					
					
				//write as pass into status coloumn
				xl.setCellData(TcModule, j, 5, "PASS");
				test.log(LogStatus.PASS, Description);
				xl.setCellData("MasterTestCases", i, 3, "PASS");
				
	
				}catch(Exception e)
				{
					System.out.println("Exception Handeled");
					xl.setCellData(TcModule, j, 5, "FAIL");
					test.log(LogStatus.FAIL, Description);
					xl.setCellData("MasterTestCases", i, 3, "FAIL");
					//take a screenshot
					File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(screen, new File("./Screens//"+TcModule+FunctionLibrary.genarateDate()+"MyScreen.png"));
				}
				report.endTest(test);
				report.flush();
			}
			
		}
		else
		{
			//write as not exicuted in status coloumn
			xl.setCellData("MasterTestCases", i, 3, "Not Exicuted");
			
		}
	}
	
}
}









