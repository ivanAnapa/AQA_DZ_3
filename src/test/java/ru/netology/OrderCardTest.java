package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class OrderCardTest {
    private final String fullName = "Исаак Ньютон-Первый";
    private final String phoneNum = "+70001112223";

    @BeforeEach
    void startBrowser() {
        open("http://localhost:9999/");
    }

    @Test
    public void positiveTest() {
        String expectedText = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        fillName(fullName);
        fillPhone(phoneNum);
        enableCheckbox();
        clickSendBtn();

        String actualText = $("[data-test-id=order-success]").getText();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void incorrectNamesTest() {
        String expectedErrorText = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";

        fillName("No Name");
        fillPhone(phoneNum);
        enableCheckbox();
        clickSendBtn();

        String actualErrorText = $("[data-test-id='name'].input_invalid .input__sub").getText();
        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    public void emptyNamesTest() {
        String expectedErrorText = "Поле обязательно для заполнения";

        fillName("");
        fillPhone(phoneNum);
        enableCheckbox();
        clickSendBtn();

        String actualErrorText = $("[data-test-id='name'].input_invalid .input__sub").getText();
        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    public void incorrectPhoneTest() {
        String expectedErrorText = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";

        fillName(fullName);
        fillPhone("@");
        enableCheckbox();
        clickSendBtn();

        String actualErrorText = $("[data-test-id='phone'].input_invalid .input__sub").getText();
        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    public void emptyPhoneTest() {
        String expectedErrorText = "Поле обязательно для заполнени я";

        fillName(fullName);
        fillPhone("");
        enableCheckbox();
        clickSendBtn();

        String actualErrorText = $("[data-test-id='phone'].input_invalid .input__sub").getText();
        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test
    public void notEnabledCheckboxTest() {
        fillName(fullName);
        fillPhone(phoneNum);
        clickSendBtn();

        assertTrue($("[data-test-id='agreement'].input_invalid").isDisplayed());
    }

    private void fillName(String name) {
        $x("//*[@name='name']").setValue(name);
    }

    private void fillPhone(String phone) {
        $x("//*[@name='phone']").setValue(phone);
    }

    private void enableCheckbox() {
        $x("//*[@class='checkbox__box']").click();
    }

    private void clickSendBtn() {
        $x("//button[@type='button']").click();
    }

}
