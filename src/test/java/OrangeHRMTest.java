import static org.testng.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
//@Ignore
public class OrangeHRMTest {
	public static WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();

	}

	@Test(priority =-1, description = "Test pour lancer l'application")
	public void testcase1() {
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		WebDriverWait w1 = new WebDriverWait(driver, 3);
		w1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='company-branding']"))).isDisplayed();
		
		boolean exist_img=driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
		Assert.assertTrue(exist_img);
		
	}

	@Test(priority = 2, description="Test pour demander la connexion" )
	public void testcase2() {

		WebDriverWait w = new WebDriverWait(driver, 3);
		w.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		boolean img1=driver.findElement(By.xpath("//img[@alt='client brand banner']")).isDisplayed();
		Assert.assertTrue(img1);
		

	}

	@Test(priority = 3, description="vérifier l'URL", invocationCount = 3) // invocationCount c-a-d le nombre de fois que cette méthode doit etre invoquée
	public void testcase3() {
		String actualurl = driver.getCurrentUrl();
		String expectedURL="https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
		Assert.assertEquals(actualurl,expectedURL);
		
	}

	// @Ignore
	@Test(priority = 4,description="Verifier le Titre ",dependsOnMethods = {"testcase3"}) // alwaysRun c-a-d toujour executer 
	public void testcase4() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";
		Assert.assertEquals(actualTitle,expectedTitle);

	}

	@Test(priority = 5,description="Test de déconnexion")
	public void testcase5() {

		driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	    String actualURL=driver.getCurrentUrl();
	    String expectedlURL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	    Assert.assertEquals(actualURL, expectedlURL);

	}

	//Close browser
	
	@Test(priority = 6)
	public void tearDown() {

		//driver.close();

	}
}
