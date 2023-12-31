package cc.robotdreams.automation.base;

import cc.robotdreams.Session;
import cc.robotdreams.automation.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseGUITest extends BaseTestNG
{
   /* @BeforeMethod(alwaysRun = true)
    public void before() {
        Session.get().webdriver().get(String.format("http://%s:%s",
                Config.HTTP_BASE_URL.value,
                Config.HTTP_BASE_PORT.value
        ));
    }*/

    @AfterMethod(alwaysRun = true)
    public void after() {
        Session.get().close();
    }

    protected WebDriver wd() {
        return Session.get().webdriver();
    }
}
