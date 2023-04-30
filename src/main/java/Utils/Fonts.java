package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Fonts {
    public static final String FONT_NAME = new JLabel().getFont().getName();
    public static final int DEFAULT_SIZE = new JLabel().getFont().getSize();
    public static final Font TITLE = new Font(FONT_NAME, Font.BOLD,52);
    public static final Font HUGE = new Font(FONT_NAME, Font.PLAIN,40);
    public static final Font LARGE = new Font(FONT_NAME, Font.PLAIN,20);
    public static final Font MEDIUM = new Font(FONT_NAME, Font.PLAIN,16);
    public static final Font BOLD = new Font(FONT_NAME, Font.BOLD, DEFAULT_SIZE);
    public static final Font BOLDMEDIUM = new Font(FONT_NAME, Font.BOLD, 16);

    public static final Font BOLD_SMALL = new Font(FONT_NAME, Font.BOLD, 10);
    public static final Font DEFAULT = new Font(FONT_NAME, Font.PLAIN, DEFAULT_SIZE);
}
