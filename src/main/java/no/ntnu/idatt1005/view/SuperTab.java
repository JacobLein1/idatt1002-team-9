package no.ntnu.idatt1005.view;

import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Class for creating a tab with custom general fonts
 */
public abstract class SuperTab extends Tab {
    private final Font titleFont = Font.font("Arial", FontWeight.BOLD, 35);
    private final Font underTitleFont = Font.font("Arial", FontWeight.BOLD, 25);
    private final Font descriptionFont = Font.font("Arial", FontWeight.NORMAL, 18);
    private final Font boldDescriptionFont = Font.font("Arial", FontWeight.BOLD, 17);
    private final Font smallTextFont = Font.font("Arial", FontWeight.NORMAL, 15);

    public SuperTab(String title) {
        super(title);
    }

    public Font getTitleFont() {
        return titleFont;
    }
    public Font getUnderTitleFont() {
        return underTitleFont;
    }
    public Font getDescriptionFont() {
        return descriptionFont;
    }

    public Font getBoldDescriptionFont() {
        return boldDescriptionFont;
    }
    public Font getSmallTextFont() {
        return smallTextFont;
    }

    /**
     * Method for creating a default tab, each subclass must implement this method
     * @return VBox
     */
    public abstract VBox defaultTabCreation();
}
