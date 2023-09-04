import cc.robotdreams.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DemoQAAutomatedTest {
    @Test(groups = "homeTask")
    public void clickingOnCkickMeButton(){
        Session.get().webdriver().get("https://demoqa.com/elements");
        WebElement sidebarButtons = Session.get().webdriver().findElement(By.xpath("//*[@id=\"item-4\"]//*[ text()=\"Buttons\"]"));
        sidebarButtons.click();
        String secondURL = Session.get().webdriver().getCurrentUrl();

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(secondURL,"https://demoqa.com/buttons");

        WebElement buttonClickMe = Session.get().webdriver().findElement(By.xpath("//button[text()='Click Me']"));
        buttonClickMe.click();

        WebElement textMessage = Session.get().webdriver().findElement(By.xpath("//p[@id=\"dynamicClickMessage\"]"));
        String resultText = textMessage.getText();

        soft.assertEquals(resultText,"You have done a dynamic click");
        soft.assertAll();

        System.out.println(resultText);
    }

    @Test(groups = "homeTask")
    public void webTableInsertAndUpdateProcess() throws InterruptedException {
        Session.get().webdriver().get("https://demoqa.com/webtables");
        WebElement addButton = Session.get().webdriver().findElement(By.xpath("//button[@id=\"addNewRecordButton\"]"));
        addButton.click();

        WebDriverWait wait = new WebDriverWait(Session.get().webdriver(), Duration.ofSeconds(5) );
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"modal-content\"]")));

        WebElement firstNameInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"firstName\"]"));
        WebElement lastNameInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"lastName\"]"));
        WebElement emailInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"userEmail\"]"));
        WebElement ageInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"age\"]"));
        WebElement salaryInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"salary\"]"));
        WebElement departmentInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"department\"]"));


        firstNameInput.sendKeys("Kris");
        lastNameInput.sendKeys("Melnik");
        emailInput.sendKeys("kris111@gmail.com");
        ageInput.sendKeys("35");
        salaryInput.sendKeys("10000");
        departmentInput.sendKeys("testing dep");

        WebElement submitButton = Session.get().webdriver().findElement(By.xpath("//button[@id=\"submit\"]"));
        submitButton.click();

        WebElement createdLineInTable =  Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]"));
        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(createdLineInTable);

        WebElement firstNameInTable = Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]/*[1]"));
        WebElement lastNameInTable = Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]/*[2]"));
        WebElement  ageInTable = Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]/*[3]"));
        WebElement  emailInTable= Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]/*[4]"));
        WebElement salaryInTable = Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]/*[5]"));
        WebElement departmentInTable = Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]/*[6]"));

        soft.assertEquals(firstNameInTable.getText(), "Kris");
        soft.assertEquals(lastNameInTable.getText(), "Melnik");
        soft.assertEquals(emailInTable.getText(), "kris111@gmail.com");
        soft.assertEquals(ageInTable.getText(), "35");
        soft.assertEquals(salaryInTable.getText(), "10000");
        soft.assertEquals(departmentInTable.getText(), "testing dep");

        WebElement editButton =  Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]//div[contains(@class,\"action-buttons\")]/span[@title=\"Edit\"]"));
        editButton.click();

        try{
            firstNameInput.clear();
            firstNameInput.sendKeys("KrisUPDATE");
            lastNameInput.clear();
            lastNameInput.sendKeys("MelnikUpdate");
            ageInput.clear();
            ageInput.sendKeys("36");
            salaryInput.clear();
            salaryInput.sendKeys("15000");
            departmentInput.sendKeys("testing depUpdate");
            departmentInput.clear();
            submitButton.click();
        } catch(StaleElementReferenceException e){
            firstNameInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"firstName\"]"));
            lastNameInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"lastName\"]"));
            ageInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"age\"]"));
            salaryInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"salary\"]"));
            departmentInput = Session.get().webdriver().findElement(By.xpath("//input[@id=\"department\"]"));

            firstNameInput.clear();
            firstNameInput.sendKeys("KrisUPDATE");
            lastNameInput.clear();
            lastNameInput.sendKeys("MelnikUpdate");
            ageInput.clear();
            ageInput.sendKeys("36");
            salaryInput.clear();
            salaryInput.sendKeys("15000");
            departmentInput.clear();
            departmentInput.sendKeys("testing depUpdate");

            submitButton = Session.get().webdriver().findElement(By.xpath("//button[@id=\"submit\"]"));
            submitButton.click();
        }

            soft.assertEquals(firstNameInTable.getText(), "KrisUPDATE");
            soft.assertEquals(lastNameInTable.getText(), "MelnikUpdate");
            soft.assertEquals(emailInTable.getText(), "kris111@gmail.com");
            soft.assertEquals(ageInTable.getText(), "36");
            soft.assertEquals(salaryInTable.getText(), "15000");
            soft.assertEquals(departmentInTable.getText(), "testing depUpdate");

       WebElement deleteButton =  Session.get().webdriver().findElement(By.xpath("//div[div[text()=\"kris111@gmail.com\"]]//div[contains(@class,\"action-buttons\")]/span[@title=\"Delete\"]"));
       deleteButton.click();

        soft.assertAll();

    }
}
