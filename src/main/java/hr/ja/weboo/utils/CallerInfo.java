package hr.ja.weboo.utils;

import lombok.Data;

@Data
public class CallerInfo {
    int lineNumber;
    String className;
    String methodName;
    String widgetId;
    String debugLink;

    public CallerInfo(String className, String methodName, int lineNumber) {
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }
}
