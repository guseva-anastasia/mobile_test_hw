package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/emulation.properties"
})
public interface EmulationConfig extends Config{
    String deviceName();
    String platformName();
    String version();
    String locale();
    String language();
    String appPackage();
    String appActivity();
    String app();
    String remoteURL();
    String automationName();
}
