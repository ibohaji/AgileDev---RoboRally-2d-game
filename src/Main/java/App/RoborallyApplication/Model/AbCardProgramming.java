package App.RoborallyApplication.Model;

import javax.swing.*;

public abstract class AbCardProgramming implements ICardFunctionality {
    public abstract ImageIcon getCardImageIcon();
    public abstract String toString();
    public static final int CARD_WIDTH = 85;
    public static final int CARD_HEIGHT = 111;

}
