import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class InputTest {
	
	public static ChromeDriver driver=new ChromeDriver();
	public static String [] questions ;
	public static int index;
	public static WebElement quesTextBox;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver.manage().window().setPosition(new Point(400,0));;
		driver.get("https://svcollegetest.000webhostapp.com/");
		questions =new String [] {"a","aaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
								  "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"};
		index=0;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	
	@Test
	@Order(1)
	//Entered a question in English only
	void firstQuestionOnlyEnglishLetters() 
	{
		try 
		{
			driver.findElement(By.id("startB")).click();
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("aaaaaaa?");
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.findElement(By.name("answer1")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Unable to connect to svcollege site ");
		}
	}
	
	@Test
	@Order(2)
	//Entered answers to the first question , don't select correct answer and click next button
	void firstQuestionOneAnswerNotSelected()
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("AAAAAAA");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("BBBBBBB");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("CCCCCCC");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("DDDDDDDD");
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
			assertFalse(driver.findElement(By.id("questhead")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Test 3 : Element not found . Data cannot be entered");
		}
	}
	
	
	@Test
	@Order(3)
	//Entered answers for first question in English only with different lengths (10,20,30,31)
	void firstQuestionAnswersCheckLength()
	{
		try
		{
			List<WebElement> quesTextBox=driver.findElements(By.name("answer1"));
			for (WebElement webElements : quesTextBox) 
				webElements.clear();
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("AAAAAAAAAA");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("BBBBBBBBBBBBBBB");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("CCCCCCCCCCCCCCCCCCCCCCCCC");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
			Thread.sleep(2000);
			assertFalse(driver.findElement(By.id("questhead")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Test 2 : Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(4)
	//Entered answer with correct length
	void firstQuestionAllCorrectAnswers()
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).clear();
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("DDDDDDDD");
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.findElement(By.id("questhead")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Test 4 : Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(5)
	// Entered second question include numbers
	void secondQuestionNotOnlyEnglishLetters() 
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("bbb22345bb?");
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();
			assertFalse(driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@RepeatedTest(value= 4,name="Check length of second question {currentRepetition}/{totalRepetitions}")
	@Order(6)
	//Entered second question with different lengths (1,20,40,49) 
	void secondQuestionCheckingLength()  
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys(questions[index]);
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();
			index++;
			assertTrue(driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).isDisplayed());
			Thread.sleep(2000);
			if(index!=questions.length)
			{
				driver.findElement(By.id("backquest")).click();
				driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).clear();
			}
			Thread.sleep(2000);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.getMessage();
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(7)
	//entered answers for second question include numbers 
	//bug - answers can be entered not only in English
	void secondQuestionAnswersNotOnlyEnglishLetters()  
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A452");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B1245");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C1245");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D1232");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("nextquest")).click();		
			assertFalse(driver.findElement(By.id("questhead")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(8)
	//Check that a question over 50 characters cannot be entered
	void thirdQuestionOverCharacters() 
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys(questions[3]+"bb");
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();
			assertFalse(driver.findElement(By.name("answer1")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(9)
	//Entered a third question in English only
	void thirdQuestion()
	{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"myform1\"]/div/div/div/input")).sendKeys("ccc");
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();
			assertTrue(driver.findElement(By.name("answer1")).isDisplayed());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(10)
	//Entered answers to the third question when one answer is empty
	void thirdQuestionEmptyAnswer() 
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[2]/div[2]/input")).sendKeys("B");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[3]/div[2]/input")).sendKeys("C");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[4]/div[2]/input")).sendKeys("D");
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[1]/input")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("nextquest")).click();	
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
			alert.accept();
			assertFalse(driver.findElement(By.id("needBackGround")).isDisplayed());
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
	}
	
	@Test
	@Order(11)
	void thirdQuestionAllAnswers()
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"answers\"]/div[1]/div[2]/input")).sendKeys("A");
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
	@Order(12)
	//Checking share on facebook 
	//bug - Clicking the share button opens an incorrect site
	//Error Message:The requested URL was not found on this server.404. That’s an error.
	void facebookIntagration()
	{
		try
		{
			driver.findElement(By.xpath("//*[@id=\"secondepage\"]/center/button[1]")).click();
			driver.findElement(By.xpath("//*[@id=\"2\"]/input[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnnext")).click();
			driver.findElement(By.xpath("//*[@id=\"1\"]/input[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnnext")).click();
			driver.findElement(By.xpath("//*[@id=\"0\"]/input[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnnext")).click();
			driver.findElement(By.xpath("//*[@id=\"fackBook2\"]/img")).click();
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
			assertEquals("https://www.facebook.com/", driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			System.out.println("Element not found . Data cannot be entered");
		}
		
	}

}
