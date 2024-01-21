package view;

import model.Image;
import presenter.ImagePresenter.OnShiftAdapter;
import presenter.ImagePresenter.OnReleaseAdapter;

public interface ImageDisplay {
    void clearDisplay();
    void display(Image image, int shiftAmount);

    int getWidth();
    int getHeight();

    void setOnShiftAdapter(OnShiftAdapter onShiftAdapter);
    void setOnReleasedAdapter(OnReleaseAdapter onReleasedAdapter);
}
