package config;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/browserstack.properties"
})
public interface BrowserstackConfig extends Config {
    String browserstackUser();
    String browserstackKey();
    String app();
    String device();
    String osVersion();
    String projectName();
    String buildName();
    String name();
    String browserstackUrl();
}
