package orders;

import helper.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FirstTC extends BaseClass {




    @Test
    public void login()
    {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dec.getdecryptPassword());
        driver.findElement(By.xpath("//button[@type='submit']")).click();


    }

}
