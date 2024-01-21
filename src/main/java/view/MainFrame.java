package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private SwingImageDisplay imageDisplay;

    public MainFrame() {
        this.initialize();
        this.setVisible(true);
    }

    private void initialize() {
        this.setTitle("Image Viewer");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280, 720);
        this.add(imageDisplay());
    }

    public SwingImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private Component imageDisplay() {
        imageDisplay = new SwingImageDisplay();
        return this.imageDisplay;
    }
}
