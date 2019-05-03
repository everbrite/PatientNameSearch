package com.iit.mmp.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class PatientSearch
{
	WebDriver driver;
	WebDriverWait wait;
	By textPatientName = By.xpath("//input[@id='search']");
	By searchButton= By.xpath("//input[@value='search']");


	String Url = "http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/patients.php";

	public PatientSearch(WebDriver driver)
	{
		this.driver=driver;
		wait= new WebDriverWait(driver,10);

	}
	public void goToUrl(String url)
	{
		this.Url=url;
		driver.get(url);
	}



	// 1.   Enter input name into the text box
	//2. Click the search button
	// The page will display list of patients matching input name.
	// If the list is zero this method returns false.
	// If at least one matching patient is found click on the 
	// first patient in the list.
	public boolean searchPatientByName(String name)
	{
		// 1.   Enter input name into the text box
		driver.findElement(textPatientName).clear();		
		driver.findElement(textPatientName).sendKeys(name);

		//2. Click the search button
		driver.findElement(searchButton).click();

		// If the list is zero this method returns false.

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='show']//tbody//tr//td//a")));



		}

		catch(Exception e)
		{
			return false;
		}
		List<WebElement> PatientList = driver.findElements(By.xpath("//div[@id='show']//tbody//tr//td//a"));
		System.out.println(PatientList.size());
		if(PatientList.isEmpty())
		{
			return false;
		}

		int size= PatientList.size();


		if(size==1)
		{
			PatientList.get(0).click();
			return true;
		}

		// if we are here there must be more than one pateint to select.
		Random rand=new Random();

		// pick a random number between zero and size-1.
		int index = rand.nextInt(size);
		PatientList.get(index).click();

		return true;



	}
	
	public int Add(int x, int y)
	{
		int z = x+y;
		return z;
				
	}


	// This method returns true if it clicks on patient name and page refreshes to the next page 
	public boolean validatePageNavigation(String name)
	{
		WebElement s= driver.findElement(By.xpath("//td[contains(text(),'Patient Name')]"));
		String s1=s.getText();
		if(s1.contains(name))
		{
			return true;

		}
		else
		{

			return false;
		}


	}



}
