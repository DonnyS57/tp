package seedu.address.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.model.person.Attribute;

import static java.util.Objects.requireNonNull;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class AttributePanel extends UiPart<Region> implements Attribute {

    private static final String FXML = "AttributePanel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label label;

    @FXML
    private TextField textField;

    private Attribute attribute;

    private String packagedExtraField = "class seedu.address.ui.Att";
    public AttributePanel(Attribute attribute) {
        super(FXML);
        this.attribute = attribute;
        label.setText("salsd");
        textField.setText(attribute.toString());
//        label.setText(attribute.getClass().toString());
//        textField.setText(attribute.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributePanel attributePanel = (AttributePanel) o;
        return Objects.equals(attributePanel, attributePanel) &&
                Objects.equals(label, attributePanel.label) &&
                Objects.equals(textField, attributePanel.textField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, label, textField);
    }
}

