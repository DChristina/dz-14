import cc.robotdreams.Session;
import cc.robotdreams.automation.base.BaseGUITest;
import org.testng.annotations.Test;

public class FirstGUITest  extends BaseGUITest{
    @Test
    public void testExample(){
        Session.get().webdriver().get("https://google.com");
    }
}
