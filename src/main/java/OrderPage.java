import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Загловок заказа первого этапа
    private final By orderHeaderStepOne = By.xpath(".//div[starts-with(@class, 'Order_Header') and text()='Для кого самокат']");
    //Имя покупателя
    private final By nameField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Имя')]");
    //Фамилия покупателя
    private final By surnameField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Фамилия')]");
    //Адрес покупателя
    private final By addressField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Адрес')]");
    //Станция метро покупателя
    private final By metroField = By.xpath(".//div[@class='select-search__value']/input[contains(@placeholder, 'Станция метро')]");
    private final By selectedMetro = By.xpath(".//ul[@class='select-search__options']/li[@class='select-search__row']");
    //Номер телефона покупателя
    private final By phoneField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Телефон')]");
    //Кнопка "Далее"
    private final By nextButton = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button[text()='Далее']");
    //Заголовок второго этапа заказа
    private final By orderHeaderStepTwo = By.xpath(".//div[starts-with(@class, 'Order_Header') and text()='Про аренду']");
    //Поле даты
    private final By dateField = By.xpath(".//div[@class='react-datepicker-wrapper']//input[contains(@placeholder, 'Когда привезти самокат')]");
    private final By selectedDate = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");
    //Поле срок аренды
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-control']/div[contains(text(), 'Срок аренды')]");
    //Выпадающее меню срок аренды
    private final By rentalPeriodDropDown = By.xpath(".//div[@class='Dropdown-menu']");
    private final By rentalDropDownOption = By.xpath(".//div[@class='Dropdown-option']");
    //Чекбокс "чёрный жемчуг"
    private final By blackCheckBox = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]/label[@for='black']");
    //Чекбокс "серая безысходность"
    private final By greyCheckBox = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]/label[@for='grey']");
    //Поле комментария
    private final By commentField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[@placeholder='Комментарий для курьера']");
    //Кнопка заказать
    private final By orderButtonDown = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[text()='Заказать'] ");
    //Заголовок модального окна
    private final By modalHeader = By.xpath(".//div[starts-with(@class, 'Order_Modal')]/div[text()='Хотите оформить заказ?']");
    //Кнопка "Да"
    private final By modalYesButton = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//button[text()='Да']");
    //Сообщение "Заказ оформлен"
    private final By orderCompleted = By.xpath(".//div[starts-with(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");


    public boolean isStepOne() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeaderStepOne));
        return driver.findElement(orderHeaderStepOne).isDisplayed();
    }

    private void putText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void setNameField(String name) {
        putText(driver.findElement(nameField), name);
    }

    public void setSurnameField(String surname) {
        putText(driver.findElement(surnameField), surname);
    }

    public void setAddressField(String address) {
        putText(driver.findElement(addressField), address);
    }

    public void setMetroField(String metro) {
        putText(driver.findElement(metroField), metro);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(selectedMetro));
        WebElement element = driver.findElement(selectedMetro);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void setPhoneField(String phone) {
        putText(driver.findElement(phoneField), phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public boolean isStepTwo() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeaderStepTwo));
        return driver.findElement(orderHeaderStepTwo).isDisplayed();
    }

    public void setDateField(String date) {
        putText(driver.findElement(dateField), date);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(selectedDate));
        WebElement element = driver.findElement(selectedDate);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.sendKeys(Keys.ENTER);
    }

    public void choiceRentPeriod(String period) {
        driver.findElement(rentalPeriodField).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodDropDown));
        List<WebElement> dropDownItems = driver.findElement(rentalPeriodDropDown).findElements(rentalDropDownOption);
        for (WebElement element : dropDownItems) {
            System.out.println(element.getText());
            if (element.getText().equals(period)) {
                element.click();
                break;

            }
        }

    }

    public void selectBlackCheckbox() {
        driver.findElement(blackCheckBox).click();
    }

    public void selectGreyCheckbox() {
        driver.findElement(greyCheckBox).click();
    }

    public void setCommentField(String comment) {
        putText(driver.findElement(commentField), comment);
    }

    public void clickOrderButtonDown() {
        driver.findElement(orderButtonDown).click();

    }

    public boolean isModalOrderVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
        return driver.findElement(modalHeader).isDisplayed();
    }

    public void clickYesButton() {
        driver.findElement(modalYesButton).click();
    }

    public boolean isOrderCompleted() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderCompleted));
        return driver.findElement(orderHeaderStepTwo).isDisplayed();
    }
}
