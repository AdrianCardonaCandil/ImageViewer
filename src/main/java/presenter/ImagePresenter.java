package presenter;

import model.Image;
import view.ImageDisplay;

public class ImagePresenter {

    private final ImageDisplay imageDisplay;
    private Image image;

    public ImagePresenter(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
        this.imageDisplay.setOnShiftAdapter(this::manageShift);
        this.imageDisplay.setOnReleasedAdapter(this::manageRelease);
    }

    public void manageShift(int shiftAmount){
        this.imageDisplay.clearDisplay();
        this.imageDisplay.display(this.image, shiftAmount);
        if (shiftAmount > 0) imageDisplay.display(image.prev(), shiftAmount - imageDisplay.getWidth());
        else this.imageDisplay.display(this.image.next(), this.imageDisplay.getWidth() + shiftAmount);
    }

    public void manageRelease(int shiftAmount){
        if (Math.abs(shiftAmount) >= this.imageDisplay.getWidth() / 2)
            this.image = shiftAmount > 0 ? this.image.prev() : this.image.next();
        this.imageDisplay.clearDisplay();
        callDisplay();
    }

    public void setImageAndStartDisplaying(Image image) {
        this.image = image;
        callDisplay();
    }

    private void callDisplay() {
        this.imageDisplay.display(this.image, 0);
    }

    /*
    Adapters for the ImageDisplay (Adapting what happens on
    ImageDisplay when a shift/released event happens)
    */

    public interface OnShiftAdapter {
        void performShift(int shiftAmount);
    }

    public interface OnReleaseAdapter {
        void performRelease(int shiftAmount);
    }
}
