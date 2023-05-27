package ru.netology.java.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardObtainTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void testShouldFillTheForm() {

        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateDelivery = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(dateDelivery);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void testShouldFillTheFormDoubleSurnameWithDash() {

        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateDelivery = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(dateDelivery);
        $("[data-test-id=name] [type=text]").setValue("Дмитрий Мамин-Сибиряк");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void testShouldFillTheFormDoubleSurnameWithSpace() {

        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateDelivery = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Екатеринбург");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(dateDelivery);
        $("[data-test-id=name] [type=text]").setValue("Дмитрий Мамин Сибиряк");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void testShouldFillTheFormCityWithDash() {

        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateDelivery = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Ханты-Мансийск");
        $("[data-test-id=date]  [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]  [placeholder='Дата встречи']").setValue(dateDelivery);
        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

//    @Test
//    void testShouldFillTheFormWithDifficultWay(){
//
//        //LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
//        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        //String dateDelivery = deliveryDateCard.format(formatter);
//
//        $("[data-test-id=city] [placeholder='Город']").setValue("Ека");
//        $(withText("Екатеринбург")).click();
//        $("[class=icon icon_size_s icon_name_close icon_theme_alfa-on-color]").click();
//        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
//        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
//        $("[class=checkbox__box]").click();
//        $("[class=button__content]").click();
//        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
}

