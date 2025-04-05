package helpers;

import io.qameta.allure.Attachment;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.RunHelper.deviceHost;

public class Attach {



    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }

    private static Object getVideoUrl(String sessionId) {
        if (deviceHost.equals("browserstack")) {
            return Browserstack.videoUrl(sessionId);
        }
        return null;
    }
}
