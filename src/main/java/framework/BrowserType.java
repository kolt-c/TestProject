package framework;

import java.util.HashMap;
import java.util.Map;

public enum BrowserType {
    FIREFOX,
    GC;

    private static Map<String, BrowserType> browsersMap = new HashMap<String, BrowserType>();

    static {
        browsersMap.put("firefox", BrowserType.FIREFOX);
        browsersMap.put("gc", BrowserType.GC);
    }

    public static BrowserType Browser(String name) {
        BrowserType browserType = browsersMap.get(name.toLowerCase().trim());
        return browserType;
    }
}
