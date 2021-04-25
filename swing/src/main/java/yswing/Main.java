package yswing;

import yswing.ui.MainUI;
import yswing.ui.UiUtils;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UiUtils.setWindowLook();
        SwingUtilities.invokeLater(() -> {
            new MainUI();
        });
    }
}
