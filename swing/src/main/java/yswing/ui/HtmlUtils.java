package yswing.ui;

public class HtmlUtils {
    public static String htmlColor(String text, String color){
        return String.format("<span style='color:%s'>%s</span>", color, text);
    }

    public static String htmlRed(String text){
        return htmlColor(text, "red");
    }

    public static String htmlBlue(String text){
        return htmlColor(text, "blue");
    }
}
