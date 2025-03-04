package org.example.until;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.enums.BrowserType;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class until {
    private static WebDriver driver;
    public static WebDriver getDriver(String browserType,String url){
        if(driver == null){
            switch (browserType){
                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "FireFox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("不支持的浏览器类型"+browserType);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);
            driver.manage().window().maximize();

        }
        return driver;
    }
}
