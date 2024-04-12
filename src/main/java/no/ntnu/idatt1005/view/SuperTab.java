package no.ntnu.idatt1005.view;

import javafx.scene.control.Tab;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//This "super" class is used to store general information about the tabs, such as font and title
public class SuperTab extends Tab {
    private final Font titleFont = Font.font("Arial", FontWeight.BOLD, 20);
    private final Font underTitleFont = Font.font("Arial", FontWeight.BOLD, 15);
    private final Font descriptionFont = Font.font("Arial", FontWeight.NORMAL, 12);
    private final Font boldDescriptionFont = Font.font("Arial", FontWeight.BOLD, 12);

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
}
