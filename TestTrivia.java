import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class TestTrivia {

	public static ChromeDriver driver=new ChromeDriver();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver.manage().window().setPosition(new Point(400,0));
		driver.get("https://svcollegetest.000webhostapp.com/");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	@Order(1)
	// entered first question
	void firstQuestion()  
	{
		try 
		{
			driver.findElement(By.id("startB")).click();
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("aaaa?");
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.getPageSource().contains("Please enter 4 possible answers"));
		}
		catch(Exception e)
		{
			System.out.println("Unable to connect to svcollege site ");
		}
	}
	
	@Test
	@Order(2)
	//entered answers for first question and selected correct answer
	void firstQuestionAnswers() 
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.getPageSource().contains("question number: 2"));
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(3)
	// entered second question
	void secondQuestion() 
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("bbb?");
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(4)
	//entered answers for second question and selected correct answer
	void secondQuestionAnswers() 
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();		
			assertTrue(driver.findElement(By.id("questhead")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(5)
	// entered third question
	void thirdQuestion()  
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("ccc");
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.findElement(By.name("answer1")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(6)
	//entered answers for third question and selected correct answer
	void  thirdQuestionAnswers() 
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();		
			assertTrue(driver.findElement(By.id("needBackGround")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(7)
	//click button quit before starting game
	//bug number (260126)
	void quitButtonAvailable( )
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"secondepage\"]/center/button[2]")).click();
			assertNotEquals("https://svcollegetest.000webhostapp.com/", driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(8)
	//click play and start game 
	void playButtonAvailable()  
	{
		try
		{
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
	@Order(9)
	//select answer for first question and go for second question page
	void selectCorrectAnswerToFistQuestion()  
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"2\"]/input[1]")).click();
			Thread.sleep(1000);
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
	@Order(10)
	// check if you can go to the next page without marking a correct answer
	// bug number (260013)- an application moves to the next page without marking a correct answer to the second question
	void nextPageWithoutAnswerToSecondQuestion()  
	{
		try 
		{
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
	@Order(11)
	//select answer for second question and go for third question page
	void selectCorrectAnswerToSecondQuestion() 
	{
		try 
		{
			driver.findElement(By.id("btnback")).click();
			driver.findElement(By.xpath("//*[@id=\"1\"]/input[1]")).click();
			Thread.sleep(1000);
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
	@Order(12)
	//check if user don't add a question mark - the application will  add it alone
	//bug number (260018) - An application does not add a question mark
	void addQuestionMarkByApp() 
	{
		try 
		{
			String thirdQuestion=driver.findElement(By.xpath("//*[@id=\"0\"]/h3")).getText();
			assertEquals("?",thirdQuestion.charAt(thirdQuestion.length()-1)); 
		}
		catch(Exception e)
		{
			System.out.println("Element not found");
		}
	}
	
	@Test
	@Order(13)
	//select answer for third question
	void selectCorrectAnswerToThirdQuestion() 
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"0\"]/input[1]")).click();
			Thread.sleep(1000);
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
	@Order(14)
	//click button try again
	void tryAgainButtonAvailable()
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"markpage\"]/center/button[1]")).click();
			assertTrue(driver.findElement(By.xpath("//*[@id=\"testpage\"]/center/h1/u")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
	
	@Test
	@Order(15)
	//answer all questions again when only one answer is incorrect and check if getting failed at the end of the game
	//bug number (260121) - at the end of the game  get success instead of failed
	void onlyOneAnswerIsIncorrect( )
	{
		try
		{
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
	@Order(16)
	//bug number (260125)-click on quit 
	void quitButtonAtTheEndOfGameAvailable()
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"markpage\"]/center/button[2]")).click();
			Thread.sleep(2000);
			assertNotEquals("https://svcollegetest.000webhostapp.com/", driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			System.out.println("Element not found cannot be selected");
		}
	}
}
