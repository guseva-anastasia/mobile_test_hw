package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Tag("Android")
    @Test
    void successfulAndroidSearchTest() {
        step("Поиск статей по заданному запросу", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка того, что найден контент по запросу", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Tag("IOS")
    @Test
    void successfulIosSearchTest() {
        step("Нажать на кнопку Text", () -> {
            $(accessibilityId("Text")).click();
        });

        step("Ввести текст в текстовое поле", () -> {
            $(name("Text Input")).sendKeys("test");
            $(name("Return")).click();
        });

        step("Проверить изменение текста", () -> {
            $(className("XCUIElementTypeStaticText")).shouldHave(Condition.value("test"));
        });
    }
}
