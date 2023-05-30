package ru.netology.java.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardObtainTest {

    String[] monthNames = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };
    String date;
    String month;
    String day;

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
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
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
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
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
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
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
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void testShouldFillTheFormWithDifficultWay() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 100);
        date =  new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime());
        month = monthNames[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
        day = Integer.toString(calendar.get(Calendar.DATE));

//        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        String dateDelivery = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Ека");
        $(withText("Екатеринбург")).click();
        $("span[data-test-id='date'] button").click();
        while (!$("div.calendar__name").getText().equals(month)) {
            $$("div.calendar__arrow.calendar__arrow_direction_right").get(1).click();
        }
        $$("table.calendar__layout td").find(text(day)).click();

        $("[data-test-id=name] [type=text]").setValue("Антон Чехов");
        $("[data-test-id=phone] [type = tel]").setValue("+79992224175");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}

