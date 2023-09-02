import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class QaScooterPage {
    private WebDriver driver;

    public QaScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Блок вопроса с ответом FAQ
    private final By accordionItems = By.xpath(".//div[@class='accordion__item']");
    //Кнопка для раскрытия ответа в FAQ
    private final By accordionButton = By.xpath(".//div[@class='accordion__button']");
    //Текст ответа на вопрос
    private final By accordionText = By.xpath(".//div[@class='accordion__panel']/p");
    //Верхняя кнопка "Заказать"
    private final By orderButtonUp = By.xpath(".//div[starts-with(@class, 'Header_Nav')]/button[text()='Заказать']");
    //Нижняя кнопка "Заказать"
    private final By orderButtonDown = By.xpath(".//div[starts-with(@class, 'Home_FinishButton')]/button[text()='Заказать']");

    public int getFaqItemsCount() {
        return driver.findElements(accordionItems).size();
    }

    public String getAccordionText(String accordionHeader) {
        List<WebElement> items = driver.findElements(accordionItems);
        String itemText;
        String text = "";
        for (WebElement accordionItem : items) {
            itemText = accordionItem.findElement(accordionButton).getText();
            if (itemText.equals(accordionHeader)) {
                //Прокручиваем до найденного вопроса
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordionItem);
                accordionItem.click();
                //Прокручиваем до ответа
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordionItem.findElement(accordionText));
                text = accordionItem.findElement(accordionText).getText();
                break;
            }
        }
        return text;
    }

    public OrderPage clickOrderButtonDown() {
        return buttonClick(orderButtonDown);
    }

    public OrderPage clickOrderButtonUp() {
        return buttonClick(orderButtonUp);
    }

    private OrderPage buttonClick(By button) {
        WebElement element = driver.findElement(button);
        //Прокручиваем страницу
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        return new OrderPage(driver);
    }
}
