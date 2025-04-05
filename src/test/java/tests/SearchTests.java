package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Tag("Android")
    @Test
    @DisplayName("Проверка поиска статей")
    void successfulAndroidSearchTest() {
        step("close onboarding ", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        });
        step("Поиск статей по заданному запросу", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка того, что найден контент по запросу", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Tag("Browserstack")
    @Test
    @DisplayName("Проверка открытия статьи")
    void successfulBrowserstackOpenArticle() {
//        step("close onboarding ", () -> {
//            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
//        });
        step("Поиск статей по заданному запросу", () -> {
            $(id("org.wikipedia.alpha:id/search_container")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Git");
        });
        step("Открыть первую статью", () ->
                $$(id("page_list_item_title")).get(0).click());
        step("Проверить статью", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(text("An error occurred")));
    }

    @Tag("Local")
    @Test
    @DisplayName("Проверка открытия статьи")
    void successfulLocalOpenArticle() {
        step("close onboarding ", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        });
        step("Поиск статей по заданному запросу", () -> {
            $(id("org.wikipedia.alpha:id/search_container")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Git");
        });
        step("Открыть первую статью", () ->
                $$(id("page_list_item_title")).get(0).click());
        step("close game banner ", () -> {
            $(className("android.widget.ImageButton")).click();
        });
        step("Открыть первую статью", () ->
                $$(id("page_list_item_title")).get(0).click());
        step("Проверить статью", () ->
                $(id("org.wikipedia.alpha:id/page_contents_container"))
                        .shouldBe(visible));
    }

    @Tag("Emulator")
    @Test
    @DisplayName("Проверка открытия статьи")
    void successfulEmulatorOpenArticle() {
        step("close onboarding ", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        });
        step("Поиск статей по заданному запросу", () -> {
            $(id("org.wikipedia.alpha:id/search_container")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Git");
        });
        step("Открыть первую статью", () ->
                $$(id("page_list_item_title")).get(0).click());
        step("close game banner ", () -> {
            $(className("android.widget.ImageButton")).click();
        });
        step("Открыть первую статью", () ->
                $$(id("page_list_item_title")).get(0).click());
        step("Проверить статью", () ->
                $(id("org.wikipedia.alpha:id/page_contents_container"))
                        .shouldBe(visible));
    }
}
