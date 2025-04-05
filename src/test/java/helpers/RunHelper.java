package helpers;

import drivers.BrowserstackDriver;
import drivers.EmulationMobileDriver;
import drivers.RealMobileDriver;

public class RunHelper {
    public static String deviceHost = System.getProperty("deviceHost");

    private RunHelper() {
    }

    public static RunHelper runHelper() {
        return new RunHelper();
    }

    public Class<?> getDriverClass() {
        if (deviceHost == null) {
            throw new RuntimeException("Необходимо ввести обязательный параметр -DdeviceHost");
        }

        switch (deviceHost) {
            case "browserstack":
                return BrowserstackDriver.class;
            case "emulation":
                return EmulationMobileDriver.class;
            case "real":
                return RealMobileDriver.class;
            default:
                throw new RuntimeException("Параметр -DdeviceHost может принимать только одно из следующих значений: " +
                        "browserstack/selenoid/emulation/real");
        }
    }
}
