package gui;

import javax.swing.SwingUtilities;

import model.TaskPlanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          	MainWindow window = new MainWindow();
        	window.setVisible(true);
        });
    }
}
