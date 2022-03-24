import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class MyMaildefs {

    private WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        js = (JavascriptExecutor) driver;

    }

    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
    }


    @When("I register")
    public void iRegister() {
        driver.findElement(By.id("marketing_newsletter")).click(); // klickar i att jag inte vill ha nyhetsbrev
        driver.findElement(By.id("create-account")).click(); // klickar på skapa konto

    }


    @Given("I choose my browser {string}")
    public void iChooseMyBrowserBrowser(String browser) {
        if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", "C:/Selenium/msedgedriver.exe"); // val vilken browser
            driver = new EdgeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.get("https://login.mailchimp.com/signup/");
    }


    @Given("I enter my mail {string}")
    public void i_enter_my_mail(String mail) {
        driver.findElement(By.id("email")).sendKeys(mail); // Fyller i mail

    }

    @Given("I enter my username {string}")
    public void i_enter_my_username(String username) {
        driver.findElement(By.id("new_username")).sendKeys(username); // Fyller i användarnamn


    }

    @Given("I enter my password {string}")
    public void i_enter_my_password(String password) {
        driver.findElement(By.id("new_password")).sendKeys(password); // Fyller i lösenord


    }


    @Then("I get result {string}")
    public void iGetResultResult(String result) {

        // så här blev min verifiering att se ut

        switch (result) {
            case "yes" -> {
                String actual = driver.findElement(By.cssSelector(".\\!margin-bottom--lv3")).getText();
                String expected = "Check your email";
                Assert.assertEquals(actual, expected);
                System.out.println("actual  = " + actual + " || expected = " + expected);

                break;
            }
            case "long" -> {
                String actual = driver.findElement(By.cssSelector(".invalid-error")).getText();
                String expected = "Enter a value less than 100 characters long";
                Assert.assertEquals(actual, expected);
                System.out.println("actual  = " + actual + " || expected = " + expected);

                break;
            }
            case "existing" -> {
                String actual = driver.findElement(By.cssSelector(".invalid-error")).getText();
                String expected = "Another user with this username already exists. Maybe it's your evil twin. Spooky.";
                Assert.assertEquals(actual, expected);
                System.out.println("actual  = " + actual + " || expected = " + expected);
                break;
            }
            case "missing" -> {
                String actual = driver.findElement(By.cssSelector(".invalid-error")).getText();
                String expected = "Please enter a value";
                Assert.assertEquals(actual, expected);
                System.out.println("actual  = " + actual + " || expected = " + expected);
                break;
            }
        }
    }
}



