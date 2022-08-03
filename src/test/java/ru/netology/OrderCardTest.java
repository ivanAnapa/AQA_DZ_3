package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class OrderCardTest {
    @Test
    public void PositiveTest() {
        String expectedAttrValue = "order-success";
        open("http://localhost:9999/");
        $x("//*[@name='name']").setValue("Исаак Ньютон");
        $x("//*[@name='phone']").setValue("+12345678901");
        $x("//*[@class='checkbox__box']").click();
        $x("//*[@type='button']").click();
        String actualAttrValue = $x("//*[@data-test-id]").getAttribute("data-test-id");

        Assertions.assertEquals(expectedAttrValue, actualAttrValue);

        System.out.println("Test 1 - Ok");
    }

    @Test
    public void NegativeTestNames() {
        String expectedErrorText = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        open("http://localhost:9999/");
        $x("//*[@name='name']").setValue("No Name");
        $x("//*[@name='phone']").setValue("+12345678901");
        $x("//*[@class='checkbox__box']").click();
        $x("//*[@type='button']").click();

        String actualErrorText = $x("(//*[@class='input__sub'])[1]").getText();

        Assertions.assertEquals(expectedErrorText, actualErrorText);

        System.out.println("Test 2 - Ok");
    }

    @Test
    public void NegativeTestPhone() {
        String expectedErrorText = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        open("http://localhost:9999/");
        $x("//*[@name='name']").setValue("Владимир Ленин");
        $x("//*[@name='phone']").setValue("@");
        $x("//*[@class='checkbox__box']").click();
        $x("//*[@type='button']").click();

        String actualErrorText =
                $x("//span[./span[text()='Мобильный телефон']]//span[@class='input__sub']").getText();

        Assertions.assertEquals(expectedErrorText, actualErrorText);

        System.out.println("Test 3 - Ok");
    }

    @Test
    public void NegativeTestCheckbox() {
        String expectedErrorText = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        open("http://localhost:9999/");
        $x("//*[@name='name']").setValue("Владимир Ленин");
        $x("//*[@name='phone']").setValue("@");
        $x("//*[@type='button']").click();
        $x("//*[@class='checkbox__text']/ancestor::label[contains(@class,'input_invalid')]").isDisplayed();

        System.out.println("Test 4 - Ok");
    }

}
