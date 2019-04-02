package iitwf.PatientNameSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.iit.mmp.pages.PatientSearch;

import io.github.bonigarcia.wdm.WebDriverManager;


public class PatientSearchTest
{
	public WebDriver driver;

	@BeforeClass
	public void initialize()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();

	}



	@Parameters({"patientName"})
	@Test
	public void patientSearchEnterText(String patientName)
	{
		PatientSearch ps = new PatientSearch(driver);
		ps.goToUrl( "http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/patients.php");
		boolean result=	ps.searchPatientByName(patientName);
		boolean result1 = false;
		if(result==true)
		{
			result1	=ps.validatePageNavigation(patientName);
		}

		SoftAssert sa= new SoftAssert();
		sa.assertFalse(result);
		sa.assertFalse(result1);
		sa.assertAll();



	}


}

