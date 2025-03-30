package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Tag("Android")
    @Test
    @DisplayName("Проверка поиска статей")
    void successfulAndroidSearchTest() {
        step("Поиск статей по заданному запросу", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка того, что найден контент по запросу", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Tag("Android")
    @Test
    @DisplayName("Проверка открытия статьи")
    void successfulAndroidOpenArticle() {
        step("Поиск статей по заданному запросу", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Открыть первую статью", () ->
                $$(id("page_list_item_title")).get(0).click());
        step("Проверить статью", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                        .shouldHave(text("An error occurred")));
    }

    @Tag("IOS")
    @Test
    @DisplayName("Проверка поля ввода")
    void successfulIosInputInTextArea() {
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
