package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.ApplicationController;
import App.RoborallyApplication.Controllers.MainMenuController;
import App.RoborallyApplication.Model.EnumDifficulty;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuView extends JPanel {
    private final MainMenuController controller;
    private JComboBox<String> difficultyDropdown;
    private JComboBox<String> playersDropdown;
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


        // MAIN PANEL -> 2 COLUMNS
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        //Logo
        JLabel logoLabel = generateLogoLabel();
        mainPanel.add(logoLabel, BorderLayout.NORTH);
        add(logoLabel, new GridBagConstraintsBuilder(0, 0).gridWidth(2).build());
        setVisible(true);


        // LEFT SIDE PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
       // leftPanel.add(generateIpPanel(), new GridBagConstraintsBuilder(0, 1).weightX(1).inset(0, 0, 30, 0).fill(GridBagConstraints.HORIZONTAL).build());
       // JButton joinGameButton = getJoinGameButton();
       // leftPanel.add(joinGameButton, new GridBagConstraintsBuilder(0, 2).fill(GridBagConstraints.HORIZONTAL).build());
       // leftPanel.setBorder(BorderFactory.createEmptyBorder(10, horizontalInset, 10, horizontalInset/2));
       // Play AI button

        JButton playAiButton = getPlayAiButton();
        leftPanel.add(playAiButton, new GridBagConstraintsBuilder(0, 5).gridWidth(2).weightX(1).inset(0, 0, 20, 0).fill(GridBagConstraints.HORIZONTAL).build());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, horizontalInset/2, 10, horizontalInset));
        // Game difficulty choice dropdown
        JLabel difficultyLabel = new JLabel("Difficulty: ");
        difficultyLabel.setFont(Fonts.LARGE);
        leftPanel.add(difficultyLabel, new GridBagConstraintsBuilder(0, 3).inset(0, 0, 25, 25).anchor(GridBagConstraints.CENTER).build());
        difficultyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        difficultyDropdown = getDifficultyDropdown();
        leftPanel.add(difficultyDropdown, new GridBagConstraintsBuilder(1, 3).weightX(0.75).inset(1, 1, 25, 0).fill(GridBagConstraints.HORIZONTAL).build());

        // RIGHT SIDE PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());

        // Nr of players choice drop down
        JLabel nrOfPlayers = new JLabel("Players: ");
        nrOfPlayers.setFont(Fonts.LARGE);
        rightPanel.add(nrOfPlayers,new GridBagConstraintsBuilder(0,2).inset(0,0,25,25).anchor(GridBagConstraints.CENTER).build());
        playersDropdown = getPlayersDropdown();
        rightPanel.add(playersDropdown,new GridBagConstraintsBuilder(1,2).weightX(0.75).inset(1,1,25,0).fill(GridBagConstraints.HORIZONTAL).build());


        difficultyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        difficultyDropdown = getDifficultyDropdown();
        leftPanel.add(difficultyDropdown, new GridBagConstraintsBuilder(1, 3).weightX(0.75).inset(1, 1, 25, 0).fill(GridBagConstraints.HORIZONTAL).build());




        // Continue game button
        JButton continueB = createContinueButton();
        continueB.setFont(Fonts.LARGE);
        rightPanel.add(continueB,new GridBagConstraintsBuilder(0,5).gridWidth(2).weightX(1).inset(75,0,0,0).fill(GridBagConstraints.CENTER).build());

        // Create lobby button
        JButton createLobbyButton = getCreateLobbyButton();
        rightPanel.add(createLobbyButton, new GridBagConstraintsBuilder(0, 5).gridWidth(2).weightX(1).inset(0, 0, 20, 0).fill(GridBagConstraints.HORIZONTAL).build());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, horizontalInset/2, 10, horizontalInset));

        // ADD TO CONTAINER
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel, new GridBagConstraintsBuilder(0, 2).weightX(1).fill(GridBagConstraints.BOTH).build());
        add(Box.createGlue(), new GridBagConstraintsBuilder(0, 3).weightY(1).build());
    }

    private JLabel generateLogoLabel() {
        JLabel logoLabel = new JLabel();
        try {
            BufferedImage logoImage = ImageIO.read(new File("src/main/java/App/Resources/RoboRallyLogo/logo.png"));
            logoLabel.setIcon(new ImageIcon(logoImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logoLabel;
    }


    private JButton createContinueButton() {
        JButton continueButton = new JButton("Continue previous game");
        continueButton.setFont(Fonts.LARGE);
        continueButton.addActionListener(e -> {
            //TODO
            // Continue the game from saved data
        });
        return continueButton;
    }
    private JButton getCreateLobbyButton(){
        JButton createGameButton = new JButton("Multiplayer");
        createGameButton.setFont(Fonts.LARGE);
        createGameButton.addActionListener(e -> {
            int numOfPlayers = Integer.parseInt(playersDropdown.getSelectedItem().toString());
            if (difficultyDropdown.getSelectedItem().toString().equals("HARD")){
                    // TODO
                    controller.userClickPlay(EnumDifficulty.HARD, numOfPlayers, true);
                } else if (difficultyDropdown.getSelectedItem().toString().equals("MEDIUM")) {
                    controller.userClickPlay(EnumDifficulty.MEDIUM, numOfPlayers, true);
                } else {
                    controller.userClickPlay(EnumDifficulty.EASY, numOfPlayers, true);
                }
            // need game configuration ( skin, etc..)


            // controller.userClickCreateLobby();
        });
        return createGameButton;
    }




    private JButton getPlayAiButton(){


        JButton playAIButton = new JButton("Play against AI");
        playAIButton.setFont(Fonts.LARGE);
        playAIButton.addActionListener(e -> {
            int numOfPlayers = Integer.parseInt(playersDropdown.getSelectedItem().toString());
            if (difficultyDropdown.getSelectedItem().toString().equals("HARD")){
                controller.userClickPlay(EnumDifficulty.HARD, numOfPlayers, false);
            } else if (difficultyDropdown.getSelectedItem().toString().equals("MEDIUM")) {
                controller.userClickPlay(EnumDifficulty.MEDIUM, numOfPlayers, false);
            } else {
                controller.userClickPlay(EnumDifficulty.EASY, numOfPlayers, false);
            }

            // controller.userClickPlayAI();
        });
        return playAIButton;
    }





    private JLabel generateGameNameLabel(){

        JLabel gameNameLabel = new JLabel("ROBORALLY");
        gameNameLabel.setFont(Fonts.TITLE);
        return gameNameLabel;
    }

    /*/ <-- should be on lobby
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
*/


    private JComboBox<String> getDifficultyDropdown(){
        JComboBox<String> difficultyDropdown = new JComboBox<>();
        difficultyDropdown.setFont(Fonts.LARGE);
        difficultyDropdown.addItem("EASY");
        difficultyDropdown.addItem("MEDIUM");
        difficultyDropdown.addItem("HARD");
        return difficultyDropdown;
    }

    private JComboBox<String> getPlayersDropdown(){
        JComboBox<String> playersDropdown = new JComboBox<>();
        playersDropdown.setFont(Fonts.LARGE);
        playersDropdown.addItem("1");
        playersDropdown.addItem("2");
        playersDropdown.addItem("3");
        playersDropdown.addItem("4");
        return playersDropdown;


    }

}
