package App;

import App.Controllers.ApplicationController.ApplicationController;

import javax.swing.*;

public class RoboRally {
    public static void main(String[] args) {
        System.out.println("Starting roborally");
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