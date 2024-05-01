import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class numbergamenew extends Frame implements ActionListener {
    // Buttons
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, nextButton, exitButton, aboutButton, outputButton;
    // Text field for username
    TextField usernameField;
    // Labels for the number puzzle
    String[] labels;
    // Index to track the current puzzle challenge
    int currentIndex;
    // Username entered by the user
    String username;
    // Total moves made by the user
    int totalMoves;
    // Panel to display final arrangement
    Panel finalArrangementPanel;
    // Flag to track visibility of final arrangement
    boolean finalArrangementVisible = false;
    // Timer for game duration
    Timer gameTimer;
    // Label to display timer
    JLabel timerLabel;

    numbergamenew() {
        super("Number Puzzle ");
        labels = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "", "H", "E", "Y"};
        currentIndex = 0;
        totalMoves = 0;

        setupButtons();
        setupTextField();
        setupTimer();

        addComponents();

        setSize(400, 600);
        setLayout(null);
        setVisible(true);
    }

    private void setupButtons() {
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = new Color(0, 128, 0);
        Color textColor = Color.WHITE;

        Color aboutButtonColor = new Color(0, 0, 128);
        Color outputButtonColor = new Color(128, 0, 128);
        Color nextButtonColor = new Color(255, 165, 0);
        Color exitButtonColor = new Color(220, 20, 60);

        b1 = createStyledButton(labels[0], buttonFont, buttonColor, textColor);
        b2 = createStyledButton(labels[1], buttonFont, buttonColor, textColor);
        b3 = createStyledButton(labels[2], buttonFont, buttonColor, textColor);
        b4 = createStyledButton(labels[3], buttonFont, buttonColor, textColor);
        b5 = createStyledButton(labels[4], buttonFont, buttonColor, textColor);
        b6 = createStyledButton(labels[5], buttonFont, buttonColor, textColor);
        b7 = createStyledButton(labels[6], buttonFont, buttonColor, textColor);
        b8 = createStyledButton(labels[7], buttonFont, buttonColor, textColor);
        b9 = createStyledButton(labels[8], buttonFont, buttonColor, textColor);

        b10 = createStyledButton(labels[9], buttonFont, buttonColor, textColor);
        b11 = createStyledButton(labels[10], buttonFont, buttonColor, textColor);
        b12 = createStyledButton(labels[11], buttonFont, buttonColor, textColor);

        nextButton = createStyledButton("Next", buttonFont, nextButtonColor, textColor);
        exitButton = createStyledButton("Exit", buttonFont, exitButtonColor, textColor);
        aboutButton = createStyledButton("About", buttonFont, aboutButtonColor, textColor);
        outputButton = createStyledButton("Output", buttonFont, outputButtonColor, textColor);

        setupButtonPositions();
        addActionListeners();
    }

    private Button createStyledButton(String label, Font font, Color backgroundColor, Color textColor) {
        Button button = new Button(label);
        button.setFont(font);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        return button;
    }

    private void setupButtonPositions() {
        b1.setBounds(50, 100, 40, 40);
        b2.setBounds(100, 100, 40, 40);
        b3.setBounds(150, 100, 40, 40);
        b4.setBounds(50, 150, 40, 40);
        b5.setBounds(100, 150, 40, 40);
        b6.setBounds(150, 150, 40, 40);
        b7.setBounds(50, 200, 40, 40);
        b8.setBounds(100, 200, 40, 40);
        b9.setBounds(150, 200, 40, 40);

        b10.setBounds(50, 250, 40, 40);
        b11.setBounds(100, 250, 40, 40);
        b12.setBounds(150, 250, 40, 40);

        nextButton.setBounds(50, 300, 80, 40);
        exitButton.setBounds(150, 300, 80, 40);
        aboutButton.setBounds(250, 300, 80, 40);
        outputButton.setBounds(250, 250, 80, 40);
    }

    private void addActionListeners() {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        b11.addActionListener(this);
        b12.addActionListener(this);

        nextButton.addActionListener(this);
        exitButton.addActionListener(this);
        aboutButton.addActionListener(this);
        outputButton.addActionListener(this);
    }

    private void setupTextField() {
        Label usernameLabel = new Label("Enter Name:");
        usernameLabel.setBounds(50, 50, 80, 30);
        add(usernameLabel);

        usernameField = new TextField();
        usernameField.setBounds(150, 50, 150, 30);
        usernameField.setBackground(Color.pink);
        usernameField.setForeground(Color.BLACK);
        add(usernameField);
    }

    private void addComponents() {
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(b10);
        add(b11);
        add(b12);
        add(nextButton);
        add(exitButton);
        add(usernameField);
        add(aboutButton);
        add(outputButton);

        finalArrangementPanel = new Panel(new GridLayout(4, 3));
        finalArrangementPanel.setBounds(50, 350, 280, 120);
        add(finalArrangementPanel);
        finalArrangementPanel.setVisible(false);
    }

    private void setupTimer() {
        timerLabel = new JLabel("Time: 30 seconds");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setBounds(50, 500, 150, 30);

        gameTimer = new Timer(1000, new ActionListener() {
            int timeRemaining = 30;

            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                if (timeRemaining >= 0) {
                    timerLabel.setText("Time: " + timeRemaining + " seconds");
                } else {
                    endGame();
                }
            }
        });

        add(timerLabel); // Adding timer label to frame
    }

    private void startGame() {
        gameTimer.start();
    }

    private void endGame() {
        gameTimer.stop();
        JOptionPane.showMessageDialog(this, "Time's up! Game over.");
        System.exit(0); // Exit the application
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            nextChallenge();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == aboutButton) {
            showAboutDialog();
        } else if (e.getSource() == outputButton) {
            toggleFinalArrangementVisibility();
        } else {
            moveButton((Button) e.getSource());
            totalMoves++;
            checkWin();
        }
    }

    private void nextChallenge() {
        currentIndex++;
        if (currentIndex >= labels.length) {
            currentIndex = 0;
        }
        shuffleLabels();
        updateButtonLabels();
    }

    private void shuffleLabels() {
        for (int i = labels.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = labels[i];
            labels[i] = labels[j];
            labels[j] = temp;
        }
    }

    private void updateButtonLabels() {
        b1.setLabel(labels[0]);
        b2.setLabel(labels[1]);
        b3.setLabel(labels[2]);
        b4.setLabel(labels[3]);
        b5.setLabel(labels[4]);
        b6.setLabel(labels[5]);
        b7.setLabel(labels[6]);
        b8.setLabel(labels[7]);
        b9.setLabel(labels[8]);
        b10.setLabel(labels[9]);
        b11.setLabel(labels[10]);
        b12.setLabel(labels[11]);
    }

    private void moveButton(Button clickedButton) {
        String label = clickedButton.getLabel();
        if (label.equals("")) {
            return;
        }

        Button emptyButton = null;

        if (b1.getLabel().equals("")) emptyButton = b1;
        else if (b2.getLabel().equals("")) emptyButton = b2;
        else if (b3.getLabel().equals("")) emptyButton = b3;
        else if (b4.getLabel().equals("")) emptyButton = b4;
        else if (b5.getLabel().equals("")) emptyButton = b5;
        else if (b6.getLabel().equals("")) emptyButton = b6;
        else if (b7.getLabel().equals("")) emptyButton = b7;
        else if (b8.getLabel().equals("")) emptyButton = b8;
        else if (b9.getLabel().equals("")) emptyButton = b9;
        else if (b10.getLabel().equals("")) emptyButton = b10;
        else if (b11.getLabel().equals("")) emptyButton = b11;
        else if (b12.getLabel().equals("")) emptyButton = b12;

        String temp = label;
        clickedButton.setLabel("");
        emptyButton.setLabel(temp);
    }

    private void checkWin() {
        if (b1.getLabel().equals("1") && b2.getLabel().equals("2") && b3.getLabel().equals("3")
                && b4.getLabel().equals("4") && b5.getLabel().equals("5") && b6.getLabel().equals("6")
                && b7.getLabel().equals("7") && b8.getLabel().equals("8") && b9.getLabel().equals("")
                && b10.getLabel().equals("H") && b11.getLabel().equals("E") && b12.getLabel().equals("Y")) {
            JOptionPane.showMessageDialog(this, "Congratulations! You won.");

            username = usernameField.getText();
            int score = currentIndex + 1;

            insertScoreIntoDatabase(username, score, totalMoves);
        }
    }

    private void insertScoreIntoDatabase(String username, int score, int totalMoves) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javagame", "cur", "cur"
            );

            createScoresTable(connection);

            String selectQuery = "SELECT score, total_moves FROM scores WHERE username = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int existingScore = resultSet.getInt("score");
                int existingMoves = resultSet.getInt("total_moves");
                score = Math.max(existingScore, score);
                totalMoves += existingMoves;
                String updateQuery = "UPDATE scores SET score = ?, total_moves = ? WHERE username = ?";
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setInt(1, score);
                preparedStatement.setInt(2, totalMoves);
                preparedStatement.setString(3, username);
                preparedStatement.executeUpdate();
            } else {
                String insertQuery = "INSERT INTO scores (username, score, total_moves) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, score);
                preparedStatement.setInt(3, totalMoves);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    private void createScoresTable(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS scores ("
                + "id SERIAL PRIMARY KEY,"
                + "username VARCHAR(255) NOT NULL,"
                + "score INT NOT NULL,"
                + "total_moves INT NOT NULL)";
        PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, "This is a Number puzzle game developed in Java by Vipul, Phani and Madan ", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void toggleFinalArrangementVisibility() {
        finalArrangementVisible = !finalArrangementVisible;
        finalArrangementPanel.setVisible(finalArrangementVisible);

        if (finalArrangementVisible) {
            showFinalArrangement();
        }
    }

    private void showFinalArrangement() {
        finalArrangementPanel.removeAll();

        Font finalArrangementFont = new Font("Arial", Font.BOLD, 17);
        Color finalArrangementBackgroundColor = new Color(255, 240, 200); // Light Gray background color
        Color finalArrangementTextColor = Color.red; // Black text color

        String[] finalArrangementLabels = {"1", "2", "3", "4", "5", "6", "7", "8", "", "H", "E", "Y"};
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button(finalArrangementLabels[index]);
                button.setFont(finalArrangementFont);
                button.setBackground(finalArrangementBackgroundColor);
                button.setForeground(finalArrangementTextColor);
                button.setEnabled(false);
                finalArrangementPanel.add(button);
                index++;
            }
        }

        finalArrangementPanel.validate();
    }

    public static void main(String[] args) {
        numbergamenew game = new numbergamenew();
        game.startGame();
    }
}
