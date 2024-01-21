package view;

import model.Image;
import presenter.ImagePresenter.OnShiftAdapter;
import presenter.ImagePresenter.OnReleaseAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private OnShiftAdapter onShiftAdapter;
    private OnReleaseAdapter onReleaseAdapter;
    private int initialShift;
    Map<BufferedImage, Integer> imagesToDisplay = new HashMap<>();

    public SwingImageDisplay() {
        this.addEventListeners();
    }

    private void addEventListeners() {
        this.addMouseListener(initializeMouseListener());
        this.addMouseMotionListener(initializeMouseMotionListener());
    }

    private MouseMotionListener initializeMouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {onShiftAdapter.performShift(e.getX() - initialShift);}

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    private MouseListener initializeMouseListener() {
        return new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {initialShift = e.getX();}

            @Override
            public void mouseReleased(MouseEvent e) {onReleaseAdapter.performRelease(e.getX() - initialShift);}

            @Override
            public void mouseClicked(MouseEvent e) {} public void mouseEntered(MouseEvent e) {} public void mouseExited(MouseEvent e) {}
        };
    }

    @Override
    public void paint(Graphics g) {
        for (Map.Entry<BufferedImage, Integer> entry : imagesToDisplay.entrySet()){
            int x = (this.getWidth() - entry.getKey().getWidth()) / 2;
            int y = (this.getHeight() - entry.getKey().getHeight()) / 2;
            g.drawImage(entry.getKey(), x + entry.getValue(), y, null);
        }
    }

    @Override
    public void clearDisplay() {
        this.imagesToDisplay.clear();
    }

    @Override
    public void display(Image image, int shiftAmount) {
        try {
            imagesToDisplay.put(ImageIO.read(new File(image.name())), shiftAmount);
            repaint();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getWidth() {
        return this.getSize().width;
    }

    @Override
    public int getHeight() {
        return this.getSize().height;
    }

    @Override
    public void setOnShiftAdapter(OnShiftAdapter onShiftAdapter) {
        this.onShiftAdapter = onShiftAdapter;
    }

    @Override
    public void setOnReleasedAdapter(OnReleaseAdapter onReleasedAdapter) {
        this.onReleaseAdapter = onReleasedAdapter;
    }
}
