package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.util.Strings;

import base.setup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook extends setup {
	public static String url;
	public static String name;
	public static String company;
	public static String email;
	public static String phonenumber;
	public static String message;
	public static String driverType = System.getProperty("browser");
	public static String envData = System.getProperty("env");

    @Before
    public void startTest(){
        if (Strings.isNullOrEmpty(driverType)){
            driverType = "ch";
        }
        if (Strings.isNullOrEmpty(envData)){
            envData = "prod";
        }
        driver = setupBrowser(driverType);
        switch (envData){
            case "qa":
                url = "https://qa.healthcourse.com/contact/";
                break;
            case "stage":
                url = "https://stage.healthcourse.com/contact/";
                break;
            case "prod":
                url = "https://www.healthcourse.com/contact/";
           
                break;
        }
        driver.get(url);
    }

    @After
    public void endTest(Scenario scenario){
        try {
            if (scenario.isFailed()){
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                // embed it in the report.
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } finally {
            driver.quit();
        }
    }

}