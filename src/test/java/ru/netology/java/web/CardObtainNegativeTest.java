package ru.netology.java.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardObtainNegativeTest {
    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void testShouldNotFillTheForm() {
        $("[class=button__content]").click();
        $("span.input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldNotFillTheCity() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $("span.input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldFillTheCityIncorrectly() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Ухта");
        $("[class=button__content]").click();
        $("[data-test-id=city]")
                .shouldHave(Condition.text("Доставка в выбранный город недоступна"), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldFillTheDateIncorrectly() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue("11.11.1901");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $("[data-test-id=date]")
                .shouldHave(Condition.text("Заказ на выбранную дату невозможен "), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldNotFillTheAntroponim() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void testShouldNotFillTheTelephoneNumber() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $("[data-test-id=phone]")
                .shouldHave(Condition.text("Поле обязательно для заполнения"), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldFillTheTelephoneNumberWithTenFigure() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $("[data-test-id=phone]")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldFillTheTelephoneNumberWithTwelveFigure() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+799922241755");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $("[data-test-id=phone]")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldFillTheTelephoneNumberWithSpaces() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+7 999 222 41 75");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $("[data-test-id=phone]")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldFillTheTelephoneNumberWithDashes() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+7-999-222-41-75");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $("[data-test-id=phone]")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldNotClickOnCheckBox() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=button__content]").click();
        $(".input_invalid")
                .shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"), Duration.ofSeconds(15))
                .shouldBe(visible);
    }


}
