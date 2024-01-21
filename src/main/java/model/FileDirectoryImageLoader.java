package model;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

public class FileDirectoryImageLoader implements ImageLoader {

    private final File[] imageRoutes;
    private static final Set<String> imageExtensions = Set.of(".jpg", ".png", ".jpeg");

    public FileDirectoryImageLoader(File directory) {
        this.imageRoutes = directory.listFiles(isValidImage());
    }

    private FilenameFilter isValidImage() {
        return (directory, name) -> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image loadImage() {
        return imageAt(0);
    }

    private Image imageAt(int position) {
        return new Image() {
            @Override
            public String name() {
                return imageRoutes != null ? imageRoutes[position].getAbsolutePath() : null;
            }

            @Override
            public Image next() {
                assert imageRoutes != null;
                return imageAt((position + 1) % (imageRoutes.length));
            }

            @Override
            public Image prev() {
                assert imageRoutes != null;
                return imageAt((position - 1 + imageRoutes.length) % (imageRoutes.length));
            }
        };
    }
}
