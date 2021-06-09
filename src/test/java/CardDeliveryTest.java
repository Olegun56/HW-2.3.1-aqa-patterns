import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardDeliveryTest {
    private final FakerMethods fakerMethods = new FakerMethods();

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @Test
    void shouldInputForm() {
        $("[data-test-id=city] input").setValue(fakerMethods.city());
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.chord(BACK_SPACE, fakerMethods.localDate(4)));
        $("[data-test-id=name] input").setValue(fakerMethods.name());
        $("[data-test-id=phone] input").setValue(fakerMethods.phoneNumber());
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(20));
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + fakerMethods.localDate(4)));
    }

    @Test
    void shouldReRegisterDate() {
        $("[data-test-id=city] input").setValue(fakerMethods.city());
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.chord(BACK_SPACE, fakerMethods.localDate(4)));
        $("[data-test-id=name] input").setValue(fakerMethods.name());
        $("[data-test-id=phone] input").setValue(fakerMethods.phoneNumber());
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(20));
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + fakerMethods.localDate(4)));
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.chord(BACK_SPACE, fakerMethods.localDate(8)));
        $(".button__text").click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible, Duration.ofSeconds(20));
        $("[data-test-id=replan-notification] .button__text").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + fakerMethods.localDate(8)));

    }
}
