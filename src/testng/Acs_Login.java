package testng;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Acs_Login {

    @Test
    public void initDriver() throws InterruptedException {
        System.out.println("@beforeClass driver is:");
        AndroidDriver driver =InitAppium.getDriver();
        //隐式等待
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("com.mz.onethird_beta:id/tv_to_register")).click();
        driver.findElement(By.id("com.mz.onethird_beta:id/usernameEt")).sendKeys("18219271855");
        driver.findElement(By.id("com.mz.onethird_beta:id/et_pic_code")).sendKeys("1111");
        driver.findElement(By.id("com.mz.onethird_beta:id/btGetCode")).click();
        driver.findElement(By.id("com.mz.onethird_beta:id/codeEt")).sendKeys("111111");
        driver.findElement(By.id("com.mz.onethird_beta:id/loginPwdEt")).sendKeys("qqwwee123");
        driver.findElement(By.id("com.mz.onethird_beta:id/payPwdEt")).sendKeys("111111");
        driver.findElement(By.id("com.mz.onethird_beta:id/inviteCodeEt")).sendKeys("18219271854");
        driver.findElement(By.id("com.mz.onethird_beta:id/bt_register")).click();
        Thread.sleep(2000);
//        driver.quit();
    }
}