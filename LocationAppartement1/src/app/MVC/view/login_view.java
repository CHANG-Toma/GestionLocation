package app.MVC.view;

import app.MVC.controller.login_controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login_view extends JFrame implements ActionListener {
    private JLabel title, usernameLabel, passwordLabel, userTypeLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton, resetButton;
    private menu_view menuV;
    private login_controller login_controller;

    public login_view() {

        this.login_controller = new login_controller(this.menuV);

        setTitle("Login Form");
        setBounds(500, 200, 400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(null);

        // Titre du formulaire
        title = new JLabel("Login Form");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(140, 10, 150, 30);
        container.add(title);

        // Champ pour le nom d'utilisateur
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 60, 80, 25);
        container.add(usernameLabel);

        usernameField = new JTextField(10);
        usernameField.setBounds(140, 60, 200, 25);
        container.add(usernameField);

        // Champ pour le mot de passe
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 25);
        container.add(passwordLabel);

        passwordField = new JPasswordField(10);
        passwordField.setBounds(140, 100, 200, 25);
        container.add(passwordField);

        // Choix du type d'utilisateur
        userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setBounds(50, 140, 80, 25);
        container.add(userTypeLabel);

        String[] userTypes = {"Propriétaire", "Client", "admin"};
        userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setBounds(140, 140, 200, 25);
        container.add(userTypeComboBox);

        // Boutons de connexion et de réinitialisation
        loginButton = new JButton("Login");
        loginButton.setBounds(90, 200, 100, 25);

        this.loginButton.addActionListener(this.login_controller);

        this.loginButton.addActionListener(e -> {
            this.login_controller.setType((String) userTypeComboBox.getSelectedItem());
            this.login_controller.setLogin(usernameField.getText());
            this.login_controller.setMdp(passwordField.getText());
        });

        container.add(loginButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(210, 200, 100, 25);
        resetButton.addActionListener(this);
        container.add(resetButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == resetButton) {
            usernameField.setText("");
            passwordField.setText("");
            userTypeComboBox.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        new login_view();
    }
}


