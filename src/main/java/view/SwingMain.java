package view;

import model.FileDirectoryImageLoader;
import model.ImageLoader;
import presenter.ImagePresenter;

import java.io.File;

public class SwingMain {

    private static ImageLoader imageLoader;
    private static ImagePresenter imagePresenter;

    public static void main(String[] args) {
        createViewerComponents();
        imagePresenter.setImageAndStartDisplaying(imageLoader.loadImage());
    }

    private static void createViewerComponents() {
        MainFrame frame = new MainFrame();
        imageLoader = new FileDirectoryImageLoader(new File("Images"));
        imagePresenter = new ImagePresenter(frame.getImageDisplay());
    }
}
