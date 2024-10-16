package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTestNotSuccsesfuly {


    LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateText = deliveryDateCard.format(formatter);



    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    // 1. Отправка заявки с пустым полем "Выберите ваш город";
    @Test
    public void shouldReturnErrorMessageIfCityEmpty() {
        $("[data-test-id='city'] [placeholder='Город']").setValue("");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id='name'] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id='phone'] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // 2. Отправка заявки с городом не из административных центров субъектов РФ;
    @Test
    public void shouldReturnErrorMessageIfCityInvalid() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Париж");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id='name'] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id='phone'] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    // 3. Отправка заявки с пустым полем "Дата встречи";
    @Test
    public void shouldReturnErrorMessageIfDateIsEmpty() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue("");
        $("[data-test-id='name'] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id='phone'] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='date'] .input__sub").shouldBe(visible).
                shouldHave(exactText("Неверно введена дата"));
    }

    // 4. Отправка заявки с пустым полем "Фамилия и Имя";
    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameIsEmpty() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id='name'] [type=text]").setValue("");
        $("[data-test-id='phone'] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // 5. Отправка заявки с полем "Фамилия и Имя" на английском языке;
    @Test
    public void shouldReturnErrorMessageIfInvalidSurnameAndName() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id='name'] [type=text]").setValue("Irina Pirogova");
        $("[data-test-id='phone'] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // 6. Отправка заявки с полем "Фамилия и Имя", состоящий из цифр;
    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameConsistsOfNumbers() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id='name'] [type=text]").setValue("5555");
        $("[data-test-id='phone'] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // 7. Отправка заявки с полем "Фамилия и Имя", состоящий из спецсимволов;
    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameConsistsOfSpecialCharacters() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id='name'] [type=text]").setValue("%#$%$#");
        $("[data-test-id='phone'] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // 8. Отправка заявки с пустым полем "Мобильный телефон";
    @Test
    public void shouldReturnSuccessIfFieldsAreFilledInCorrectly() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [type=tel]").setValue("");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // 9. Отправка заявки с введенным в поле "Мобильный телефон" букв;
    @Test
    public void shouldReturnErrorMessageIfPhoneWithLetters() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [type=tel]").setValue("АБВ");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 10. Отправка заявки с введенным в поле "Мобильный телефон" спецсимволов;
    @Test
    public void shouldReturnErrorMessageIfPhoneHasSpecialCharacters() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [type=tel]").setValue("#$%#$%");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 11. Отправка заявки с неверно заполненным полем "Мобильный телефон", без "+";
    @Test
    public void shouldReturnErrorMessageIfPhoneIsWrong() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [type=tel]").setValue("79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 12. Отправка заявки с введенным в поле "Мобильный телефон" неверного количества цифр;
    @Test
    public void shouldReturnErrorMessageIfPhoneWrong() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [type=tel]").setValue("+7988339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 13. Отправка заявки без согласия с условиями обработки персональных данных;
    @Test
    public void shouldReturnErrorMessageIfDoNotTick() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[role=button] .button__content").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldBe(visible)
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
