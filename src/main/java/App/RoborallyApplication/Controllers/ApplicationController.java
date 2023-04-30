package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Views.ApplicationView;

import javax.swing.*;

public class ApplicationController {
    private final ApplicationView view = new ApplicationView();

    public void initiate() {
        MainMenuController menuController = new MainMenuController(this);
        menuController.display();
    }

    public void changePanel(JPanel panel) {
        SwingUtilities.invokeLater(() -> view.changePanel(panel));
    }

}
