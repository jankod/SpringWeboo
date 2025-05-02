package hr.ja.weboo.utils;

import lombok.Data;

@Data
public class CallerInfo {
    String className;
    String methodName;
    int lineNumber;
    String debugLink;

    String widgetName;
    String widgetId;

    public CallerInfo(String className, String methodName, int lineNumber, String debugLink) {
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.debugLink = debugLink;
    }
}
