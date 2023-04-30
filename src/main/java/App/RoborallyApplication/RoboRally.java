package App.RoborallyApplication;

import App.RoborallyApplication.Controllers.ApplicationController;

import javax.swing.*;

public class RoboRally {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {
            // do nothing, use default look and feel
        }
        ApplicationController app = new ApplicationController();
        app.initiate();
    }
}