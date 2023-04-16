package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.MainMenuController;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {
    private final MainMenuController controller;

    private JComboBox<String> difficultyDropdown;
    private JTextField ipAddressField;
    private String defaultName = "";
    private JTextField displayNameField;
    public MainMenuView(MainMenuController controller){
        this.controller = controller;
        generateMainMenu();
    }

    protected void generateMainMenu() {
        setLayout(new GridBagLayout());
        int horizontalInset = 100;
        // GAME NAME
        add(generateGameNameLabel(), new GridBagConstraintsBuilder(0, 0).gridWidth(2).build());

        // USERS DISPLAYNAME
        add(generateDisplayNamePanel(), new GridBagConstraintsBuilder(0, 1).fill(GridBagConstraints.HORIZONTAL).weight(1, 0.4).gridWidth(2).build());

        // MAIN PANEL -> 2 COLUMNS
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        // LEFT SIDE PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.add(generateIpPanel(), new GridBagConstraintsBuilder(0, 1).weightX(1).inset(0, 0, 30, 0).fill(GridBagConstraints.HORIZONTAL).build());
        JButton joinGameButton = getJoinGameButton();
        leftPanel.add(joinGameButton, new GridBagConstraintsBuilder(0, 2).fill(GridBagConstraints.HORIZONTAL).build());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, horizontalInset, 10, horizontalInset/2));

        // RIGHT SIDE PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        // Nr of players choice

        // Game difficulty choice dropdown
        JLabel difficultyLabel = new JLabel("DIFFICULTY:");
        difficultyLabel.setFont(Fonts.LARGE);
        rightPanel.add(difficultyLabel, new GridBagConstraintsBuilder(0, 0).inset(0, 0, 20, 20).anchor(GridBagConstraints.WEST).build());
        difficultyDropdown = getDifficultyDropdown();
        rightPanel.add(difficultyDropdown, new GridBagConstraintsBuilder(1, 0).weightX(0.7).inset(0, 0, 20, 0).fill(GridBagConstraints.HORIZONTAL).build());
        // Create lobby button
        JButton createLobbyButton = getCreateLobbyButton();
        rightPanel.add(createLobbyButton, new GridBagConstraintsBuilder(0, 1).gridWidth(2).weightX(1).inset(0, 0, 20, 0).fill(GridBagConstraints.HORIZONTAL).build());
        // Play AI button
        JButton playAiButton = getPlayAiButton();
        rightPanel.add(playAiButton, new GridBagConstraintsBuilder(0, 2).gridWidth(2).weightX(1).inset(0, 0, 20, 0).fill(GridBagConstraints.HORIZONTAL).build());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, horizontalInset/2, 10, horizontalInset));

        // ADD TO CONTAINER
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel, new GridBagConstraintsBuilder(0, 2).weightX(1).fill(GridBagConstraints.BOTH).build());
        add(Box.createGlue(), new GridBagConstraintsBuilder(0, 3).weightY(1).build());
    }


    private JButton getJoinGameButton(){
        // JOIN GAME BUTTON
        JButton joinGameButton = new JButton("Join game");
        joinGameButton.setFont(Fonts.LARGE);
        joinGameButton.addActionListener(e -> {
            // controller.userClickJoinGame();
            // check if lobby already full
        });
        return joinGameButton;
    }

    private JButton getCreateLobbyButton(){
        JButton createGameButton = new JButton("Create lobby");
        createGameButton.setFont(Fonts.LARGE);
        createGameButton.addActionListener(e -> {
            // need ip
            // need game configuration (nr of players, skin, etc..)
            // controller.userClickCreateLobby();
        });
        return createGameButton;
    }

    private JButton getPlayAiButton(){
        JButton playAIButton = new JButton("Play against AI");
        playAIButton.setFont(Fonts.LARGE);
        playAIButton.addActionListener(e -> {
            // need ip
            // need game configuration (nr of players, skin, etc..)
            // controller.userClickPlayAI();
        });
        return playAIButton;
    }

    private JLabel generateGameNameLabel(){
        JLabel gameNameLabel = new JLabel("ROBORALLY");
        gameNameLabel.setFont(Fonts.TITLE);
        return gameNameLabel;
    }

    private JPanel generateDisplayNamePanel(){
        // INPUT DISPLAYNAME FIELD
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());

        JLabel displayNameLabel = new JLabel("Display name");
        displayNameLabel.setFont(Fonts.LARGE);
        namePanel.add(displayNameLabel, new GridBagConstraintsBuilder(0, 0).gridWidth(3).inset(5).build());

        displayNameField = new JTextField();
        displayNameField.setFont(Fonts.LARGE);
        displayNameField.setText(defaultName);

        namePanel.add(Box.createGlue(), new GridBagConstraintsBuilder(0, 1).weightX(1).build());
        namePanel.add(displayNameField, new GridBagConstraintsBuilder(1, 1).weightX(1).fill(GridBagConstraints.HORIZONTAL).inset(5).build());
        namePanel.add(Box.createGlue(), new GridBagConstraintsBuilder(2, 1).weightX(1).build());
        return namePanel;
    }

    private JPanel generateIpPanel(){
        JPanel ipPanel = new JPanel();
        ipPanel.setLayout(new GridBagLayout());

        JLabel ipAddressLabel = new JLabel("IP:");
        ipAddressLabel.setFont(Fonts.LARGE);
        ipPanel.add(ipAddressLabel, new GridBagConstraintsBuilder(0, 0).inset(0, 0, 0, 20).anchor(GridBagConstraints.WEST).build());

        ipAddressField = new JTextField();
        ipAddressField.setFont(Fonts.LARGE);
        ipPanel.add(ipAddressField, new GridBagConstraintsBuilder(1, 0).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());
        return ipPanel;
    }

    private JComboBox<String> getDifficultyDropdown(){
        JComboBox<String> difficultyDropdown = new JComboBox<>();
        difficultyDropdown.setFont(Fonts.LARGE);
        difficultyDropdown.addItem(null);
        difficultyDropdown.addItem("EASY");
        difficultyDropdown.addItem("MEDIUM");
        difficultyDropdown.addItem("HARD");
        return difficultyDropdown;
    }







}
