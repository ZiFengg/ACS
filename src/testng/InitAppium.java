package testng;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class InitAppium {
    public static final String APP_PACKAGE = "com.mz.onethird_beta";
    public static final String APP_ACTIVITY = "com.mz.onethird.activity.login.SplashActivity";
    private static AndroidDriver driver;

    public static AndroidDriver AndroidDevices() {
        //        安卓对象
        //        存储参数对象
        DesiredCapabilities cap = new DesiredCapabilities();
//        随机数对象
//        Random random = new Random(1);
//        appium做自动化
        cap.setCapability("automationName", "Appium");
        //安卓apk
//        cap.setCapability("app","C:\\ACSdebug_1.1.0_20191113.apk");
        //设置HTML5自动化，打开谷歌浏览器
//        cap.setCapability("browserName","chrome");
        //设备名称d

        /*
        cap.setCapability("deviceName", "Nexus 4");
        //设备类型Android or iOS
        cap.setCapability("platformName", "Android");
        //系统版本
        cap.setCapability("platformVersion", "5.1.1");
        //设备udid，通过命令行adb -devices查看
        cap.setCapability("udid", "emulator-5554");
*/

        //夜神，设备名称
        cap.setCapability("deviceName", "SM-G955N");
        //设备类型Android or iOS
        cap.setCapability("platformName", "Android");
        //系统版本
        cap.setCapability("platformVersion", "5.1.1");
        //设备udid，通过命令行adb devices查看
        cap.setCapability("udid", "127.0.0.1:52001");


        //被测试app的包名
        cap.setCapability("appPackage", APP_PACKAGE);
        //被测试app入口activity
        cap.setCapability("appActivity", APP_ACTIVITY);
        //支持中文输入，两条都需要设置
        cap.setCapability("unicodeKeyboard", "True");
        cap.setCapability("resetKeyboard", "True");
        //不重新签名apk
        cap.setCapability("noSign", "True");
        //没有新命令，appium30秒退出
        cap.setCapability("newCommandTimeout", "60");
        //把以上配置传到appium服务端并连接手机
        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AndroidDriver getDriver() {
        if (driver == null) {
            driver = AndroidDevices();
        }
        return driver;
    }
}
