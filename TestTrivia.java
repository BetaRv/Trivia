import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestTrivia {

	public static ChromeDriver driver=new ChromeDriver();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver.get("https://svcollegetest.000webhostapp.com/");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	// entered first question
	void APage()  {
		try {
			driver.findElement(By.id("startB")).click();
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("aaaa?");
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.getPageSource().contains("Please enter 4 possible answers"));
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Unable to connect to svcollege site ");
		}
	}
	
	@Test
	//entered answers for first question and selected correct answer
	void BPage()  {
		try {
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.getPageSource().contains("question number: 2"));
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	// entered second question
	void CPage()  {
		try
		{
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("bbb?");
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	//entered answers for second question and selected correct answer
	void DPage()  {
		try {
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			driver.findElement(By.id("nextquest")).click();		
			assertTrue(driver.findElement(By.id("questhead")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	// entered third question
	void EPage()  {
		try {
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("ccc");
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.findElement(By.name("answer1")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	//entered answers for third question and selected correct answer
	void FPage()  {
		try {
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			driver.findElement(By.id("nextquest")).click();		
			assertTrue(driver.findElement(By.id("needBackGround")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	//click button quit before starting game
	//bug number (260126)
	void GPage( ) {
		try {
			driver.findElement(By.xpath("//*[@id=\"secondepage\"]/center/button[2]")).click();
			assertNotEquals("https://svcollegetest.000webhostapp.com/", driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	//click play and start game 
	void GPage1()  {
		try {
			driver.findElement(By.xpath("//*[@id=\"secondepage\"]/center/button[1]")).click();
			assertTrue(driver.findElement(By.id("btnnext")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
	
	@Test
	//select answer for first question and go for second question page
	void GPage2()  {
		try {
			driver.findElement(By.xpath("//*[@id=\"2\"]/input[1]")).click();
			driver.findElement(By.id("btnnext")).click();
			assertTrue(driver.findElement(By.id("1")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
			
		}
	}
	
	@Test
	// check if you can go to the next page without marking a correct answer
	// bug number (260013)- an application moves to the next page without marking a correct answer to the second question
	void GPage3()  {
		try {
			driver.findElement(By.id("btnnext")).click();
			assertFalse(driver.findElement(By.id("0")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}

	@Test
	//select answer for second question and go for third question page
	void GPage4()  {
		try {
		driver.findElement(By.id("btnback")).click();
		driver.findElement(By.xpath("//*[@id=\"1\"]/input[1]")).click();
		driver.findElement(By.id("btnnext")).click();
		assertTrue(driver.findElement(By.id("0")).isDisplayed());
		Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
	
	@Test
	//check if user don't add a question mark - the application will  add it alone
	//bug number (260018) - An application does not add a question mark
	void GPage5() {
		try {
		String thirdQuestion=driver.findElement(By.xpath("//*[@id=\"0\"]/h3")).getText();
		assertEquals("?",thirdQuestion.charAt(thirdQuestion.length()-1)); 
		}
		catch(Exception e)
		{
			System.out.println("Element not found");
		}
	}
	
	@Test
	//select answer for third question
	void GPage6()  {
		try {
		driver.findElement(By.xpath("//*[@id=\"0\"]/input[1]")).click();
		driver.findElement(By.id("btnnext")).click();
		assertTrue(driver.findElement(By.xpath("//*[@id=\"markpage\"]/center/button[1]")).isDisplayed());
		Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
	
	@Test
	//click button try again
	void GPage7() {
		try {
		driver.findElement(By.xpath("//*[@id=\"markpage\"]/center/button[1]")).click();
		assertTrue(driver.findElement(By.xpath("//*[@id=\"testpage\"]/center/h1/u")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
	
	@Test
	//answer all questions again when only one answer is incorrect and check if getting failed at the end of the game
	//bug number (260121) - at the end of the game  get success instead of failed
	void GPage8( ) {
		try {
		driver.findElement(By.xpath("//*[@id=\"2\"]/input[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btnnext")).click();
		driver.findElement(By.xpath("//*[@id=\"1\"]/input[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btnnext")).click();
		driver.findElement(By.xpath("//*[@id=\"0\"]/input[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btnnext")).click();
		Thread.sleep(2000);
		assertEquals("Failed", driver.findElement(By.id("mark")).getText());
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
	
	
	@Test
	//bug number (260125)-click on quit 
	void GPage9() {
		try {
		driver.findElement(By.xpath("//*[@id=\"markpage\"]/center/button[2]")).click();
		assertNotEquals("https://svcollegetest.000webhostapp.com/", driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
}
