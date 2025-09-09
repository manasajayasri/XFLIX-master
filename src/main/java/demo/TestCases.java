package demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    private static RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(120).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        if (driver != null) {
            driver.quit();
        }
    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://xflix-qa.vercel.app/");
        if(driver.getCurrentUrl().contains("xflix")){
            System.out.println("'xflix' is present in the URL");
        }
        System.out.println("End Test case: testCase01");
    }

    public void testCase02(){
        System.out.println("Start Test case: testCase02");
        WebElement search = driver.findElement(By.className("search-input"));
        search.sendKeys("frameworks");
        List<WebElement> frameworks = driver.findElements(By.className("video-card"));
        int siz = frameworks.size();
        if(siz>0){
            System.out.println("Frameworks are present");
        }
        search.clear();
        search.sendKeys("selenium");
        List<WebElement> selenium = driver.findElements(By.className("video-card"));
        WebElement noResult = driver.findElement(By.className("no-search-txt"));
        Boolean status = noResult.getText().equals("No Search is found :( . Please try searching some other movies.");
        siz = selenium.size();
        if(siz == 0 && status){
            System.out.println("Selenium videos are not present");
        }
        System.out.println("End Test case: testCase02");
    }

    public void testCase03(){
        System.out.println("Start Test case: testCase03");
        driver.navigate().refresh();
        List<WebElement> homePage = driver.findElements(By.className("video-title"));
        WebElement dropdown = driver.findElement(By.id("sortBySelect"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort By: View Count");
        List<WebElement> viewCount = driver.findElements(By.className("video-title"));
        for(int i = 0 ; i<viewCount.size() ; i++){
            if(!(homePage.get(i).getText().equals(viewCount.get(i).getText()))){
                System.out.println("Elements order is changed");
                break;
            }
        }
        System.out.println("End Test case: testCase03");
    }

    public void testCase04() throws InterruptedException{
        System.out.println("Start Test case: testCase04");
        WebElement upload = driver.findElement(By.className("btn-upload"));
        upload.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btn-flex-container")));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        System.out.println("Alert is Accepted!");
        driver.findElement(By.name("videoLink")).sendKeys("https://www.youtube.com/embed/HGk_ypEuS24");
        driver.findElement(By.name("previewImage")).sendKeys("https://in.pinterest.com/pin/2603712280698956/");
        driver.findElement(By.name("title")).sendKeys("Sorting");
        WebElement genre = driver.findElement(By.id("genre-modal-dropdown"));
        Select gender_dropdown = new Select(genre);
        gender_dropdown.selectByValue("Education");
        WebElement age = driver.findElement(By.id("age-modal-dropdown"));
        Select age_dropdown = new Select(age);
        age_dropdown.selectByValue("7+");
        driver.findElement(By.name("releaseDate")).sendKeys("17-08-2025");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Alert videoUpload = driver.switchTo().alert();
        videoUpload.accept();
        System.out.println("Details are filles and video is uploaded!");
        System.out.println("End Test case: testCase04");
    }

    public void testCase05(){
        System.out.println("Start Test case: testCase05");
        driver.findElement(By.className("video-img")).click();
        WebElement likes = driver.findElement(By.xpath("//button[@class = 'btn btn-like']"));
        likes.click();
        String likeCnt = likes.getText();
        String url = driver.getCurrentUrl();
        driver.switchTo().newWindow(WindowType.TAB).get(url);
        WebElement new_likes = driver.findElement(By.xpath("//button[@class = 'btn btn-like']"));
        String new_likeCnt = new_likes.getText();
        if(likeCnt.equals(new_likeCnt)){
            System.out.println("Video like count is updated!");
        }
        System.out.println("End Test case: testCase05");
    }
}
