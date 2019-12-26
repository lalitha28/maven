package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions
{
	public static WebDriver driver;
	//launch url in a browser
	public static String lauchApp(String url)
	{
	String res="";
	System.setProperty("webdriver.chrome.driver", "D:\\Lalithasri\\FrameWorks\\ERP_Accounting\\CommonDrivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	if(driver.findElement(By.name("btnsubmit")).isDisplayed())
	{
	res="Application launch Success";
	}
	else
	{
		res="Application launch Fail";
	}
	return res;
	}
	//method for login
	public static String verifyLogin(String username,String password)
	{
		String res="";
		WebElement user=driver.findElement(By.name("username"));
		user.clear();
		user.sendKeys(username);
		WebElement pass=driver.findElement(By.name("password"));
		pass.clear();
		pass.sendKeys(password);
		driver.findElement(By.name("btnsubmit")).click();
		if(driver.findElement(By.id("mi_logout")).isDisplayed())	
		{
			res="Login Success";
		}
		else
		{
			res="Login Fail";
		}
		return res;
		}
		//method for supplier creation
		public static String verifySupplier(String sname,String address,String city,
				String country,String cperson,String pnumber,String email,String mnumber	,
				String notes) throws Throwable
		{
			String res="";
		driver.findElement(By.linkText("Suppliers")).click();	
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
		Thread.sleep(4000);
		String Exp_data=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
		driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
		driver.findElement(By.name("x_Address")).sendKeys(address);
		driver.findElement(By.xpath("//input[@id='x_City']	")).sendKeys(city);
		driver.findElement(By.name("x_Country")).sendKeys(country);
		driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
		driver.findElement(By.name("x__Email")).sendKeys(email);
		driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
		driver.findElement(By.name("x_Notes")).sendKeys(notes);
		driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		Thread.sleep(4000);
		if(!driver.findElement(By.name("psearch")).isDisplayed())
		
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
		driver.findElement(By.name("psearch")).clear();
		driver.findElement(By.name("psearch")).sendKeys(Exp_data);
		driver.findElement(By.name("btnsubmit")).click();
		Thread.sleep(4000);
		String Act_Data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		System.out.println(Exp_data+"   "+Act_Data);
		if(Exp_data.equals(Act_Data))
		{
			res="Pass";
		}
		else
		{
			res="fail";
		}
		
		return res;
		}
		public static void closeapp()
		{
			driver.close();
		}
		
		

	}

