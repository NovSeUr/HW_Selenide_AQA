package ru.netology.java.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardObtainPositiveTest {

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void testShouldFillTheForm() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void testShouldNotFillTheDate() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(visible);
    }

    @Test
    void testShouldFillTheFormDoubleSurnameWithDash() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Дмитрий Мамин-Сибиряк");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void testShouldFillTheFormDoubleSurnameWithSpace() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Дмитрий Мамин Сибиряк");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void testShouldFillTheFormCityWithDash() {
        String planningDate = generateDate(3, "dd.MM.yyyy");

        $("[data-test-id=city] [placeholder='Город']").setValue("Ханты-Мансийск");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void testShouldFillTheFormWithDifficultWay() {
        String currentDate = generateDate(3, "dd.MM.yyyy");
        String defaultMonth = generateDate(3, "MM");
        String planningMonth = generateDate(7, "MM");
        String planningDay = generateDate(3, "d");

        $("[data-test-id=city] [placeholder='Город']").setValue("Ека");
        $(withText("Екатеринбург")).click();
        $("span[data-test-id='date'] button").click();

        if (!defaultMonth.equals(planningMonth)) {
            $$("div.calendar__arrow.calendar__arrow_direction_right").get(1).click();
        }

        $$("[data-day]").find(text(planningDay)).click();
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + currentDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}

