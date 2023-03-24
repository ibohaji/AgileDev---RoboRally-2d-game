package App.Controllers.MainMenuController;

import App.Controllers.ApplicationController.ApplicationController;
import App.Views.Menus.MainMenuView;

public class MainMenuController {

    private final ApplicationController applicationController;
    private final MainMenuView view;

    public MainMenuController(ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.view = new MainMenuView(this);
    }



    // LOBBY CREATION

    // JOINING A LOBBY

    public void display() {
        applicationController.changePanel(view);
    }
}
