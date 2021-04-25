package yswing.ui;

import javax.swing.*;
import java.awt.*;

public class UiUtils {

    public static void setWindowLook(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String htmlColor(String text, String color){
        return String.format("<span style='color:%s'>%s</span>", color, text);
    }

    public static void main(String[] args) {
        System.out.println(htmlColor("1111","red"));
    }
}
