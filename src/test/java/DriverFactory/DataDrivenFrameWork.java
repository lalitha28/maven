package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDrivenFrameWork
{
WebDriver driver;
@BeforeTest
public void setUp()
{
	String launch=ERP_Functions.lauchApp("http://webapp.qedge.com");
	System.out.println(launch);
	String login=ERP_Functions.verifyLogin("admin", "master");
			System.out.println(login);
}
@Test
public void supplierCre()throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil();
	int rc=xl.rowCount("supplier");
	int cc=xl.colCount("supplier");
	System.out.println(rc+"  "+cc);
	for(int i=1;i<=rc;i++)
	{
		String sname=xl.getCellData("supplier", i, 0);
		String address=xl.getCellData("supplier", i, 1);
		String city=xl.getCellData("supplier", i, 2);
		String country=xl.getCellData("supplier", i, 3);
		String cperson=xl.getCellData("supplier", i, 4);
		String pnumber=xl.getCellData("supplier", i, 5);
		String email=xl.getCellData("supplier", i, 6);
		String mnumber=xl.getCellData("supplier", i, 7);
		String notes=xl.getCellData("supplier", i, 8);
		String results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
	    xl.setCellData("supplier", i, 9, results);
	}
}
@AfterTest
public void tearDown()
{
	ERP_Functions.closeapp();
}
}





