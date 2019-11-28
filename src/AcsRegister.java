import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AcsRegister {
    public static final String FILE_PATH = "D:/acs_regsiter.xls";
    public static final String APP_PACKAGE = "com.mz.acs_beta";
    public static final String APP_ACTIVITY = "com.mz.acs.activity.login.SplashActivity";
    public static final String TX_CODE = "1111";
    public static final String DX_CODE = "1234";
    public static final String DL_PSD = "z11111111";
    public static final String ZF_PSD = "z11111";

    public static void main(String[] args) throws Exception {
//        安卓对象
        AndroidDriver driver;
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
        //夜神，设备名称
        cap.setCapability("deviceName", "SM-G955N");
        //设备类型Android or iOS
        cap.setCapability("platformName", "Android");
        //系统版本
        cap.setCapability("platformVersion", "5.1.1");
        //设备udid，通过命令行adb devices查看
        cap.setCapability("udid", "127.0.0.1:52001");
/*
        //idea模拟器，设备名
        cap.setCapability("deviceName", "Nexus 4");
        //设备类型Android or iOS
        cap.setCapability("platformName", "Android");
        //系统版本
        cap.setCapability("platformVersion", "5.1.1");
        //设备udid，通过命令行adb -devices查看
        cap.setCapability("udid", "emulator-5554");
        */
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
        cap.setCapability("newCommandTimeout", "30");
        //把以上配置传到appium服务端并连接手机
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        //隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
//            创建Excel的对象
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(FILE_PATH));
//
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//            得到Excel对象后指定sheet页
//            也可以使用getSheetAt(int index)按索引引用，在Excel文档中，第一张表的缺省索引值为0，其语句为：
            HSSFSheet sheet = workbook.getSheetAt(0);
//            HSSFSheet sheet = workbook.getSheet("model");

//            获取第一个和最后一个有数据化的行，打印
            int rowbegin = sheet.getFirstRowNum();
            int rowend = sheet.getLastRowNum()+1;
            System.out.println("第"+rowbegin+"行开始有数据，第"+rowend+"行后没有数据");
            HSSFRow row = sheet.getRow(0);
//            获取第一个和最后一个有数据的列，打印
            int begincell = row.getFirstCellNum();
            int endcell = row.getLastCellNum();
            System.out.println("第"+begincell+"列开始有数据，第"+endcell+"列后没有数据");
//             循环从Excel有数据的单元格中取数据，并且打印
            for (int i = rowbegin;i < rowend; i ++){
//                for (int s = begincell; s < endcell; s ++){
                    int n = 0;
                    HSSFRow rows = sheet.getRow(i);
                    HSSFCell cell1 = rows.getCell((short) n);
                    HSSFCell cell2 = rows.getCell((short) n+1);
                    System.out.println("注册账号："+getCellData(cell1,evaluator)+"____邀请账号："+getCellData(cell2,evaluator));
                    //通过Appium命令定位元素，并且执行操作
                    driver.findElement(By.name("註冊")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.id("com.mz.acs_beta:id/usernameEt")).sendKeys((CharSequence) getCellData(cell1,evaluator));
                    driver.findElement(By.id("com.mz.acs_beta:id/et_pic_code")).sendKeys(TX_CODE);
                    driver.findElement(By.id("com.mz.acs_beta:id/btGetCode")).click();
                    driver.findElement(By.id("com.mz.acs_beta:id/codeEt")).sendKeys(DX_CODE);
                    driver.findElement(By.id("com.mz.acs_beta:id/bt_register")).click();
                    driver.findElement(By.id("com.mz.acs_beta:id/loginPwdEt")).sendKeys(DL_PSD);
                    driver.findElement(By.id("com.mz.acs_beta:id/payPwdEt")).sendKeys(ZF_PSD);
                    driver.findElement(By.id("com.mz.acs_beta:id/payTui")).sendKeys((CharSequence) getCellData(cell2,evaluator));
                    driver.findElement(By.id("com.mz.acs_beta:id/bt_register")).click();
                    Thread.sleep(2000);
                }
//            }
            driver.quit();
        } catch (Exception e) {
            System.out.println("错误：" + e);
        }
    }
    private static Object getCellData(Cell cell, FormulaEvaluator formula) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return formula.evaluate(cell).getNumberValue();
            default:
                return null;
        }
    }

}
