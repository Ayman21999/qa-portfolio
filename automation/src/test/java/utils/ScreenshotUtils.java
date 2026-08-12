package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
	
	public static void TakeScreenshot(WebDriver driver, String testName) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String time_stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String destination = "screenshots/" + testName + "_" + time_stamp + ".png";

	    // إنشاء الفولدر لو مش موجود
	    File screenshotDir = new File("screenshots");
	    if (!screenshotDir.exists()) {
	        screenshotDir.mkdirs();
	    }

	    try {
	        FileUtils.copyFile(src, new File(destination));
	        System.out.println("Screenshot saved: " + destination);
	    } catch (IOException e) {
	        System.out.println("Screenshot failed: " + e.getMessage());
	    }
	}
}