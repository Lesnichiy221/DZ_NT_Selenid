package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardTestSuccsesfuly {

    private String generateDate(long addDays, String pattern){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldReturnSuccessIfFieldsAreFilledInCorrectl(){

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + generateDate(3, "dd.MM.yyyy")));
    }
    @Test
    void shouldReturnSuccsesfulyIfNameOrSurnameHaveDefise(){
        LocalDate DeliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = DeliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова-Антон");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + generateDate(3, "dd.MM.yyyy")));


    }

    @Test
    void shouldReturnSuccsesfulyIfCityHaveDefise(){

        $("[data-test-id=city] [placeholder='Город']").setValue("Ростов-на-Дону");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова-Антон");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + generateDate(3, "dd.MM.yyyy")));


    }




}