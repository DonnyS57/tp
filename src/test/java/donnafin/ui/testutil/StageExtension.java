package donnafin.ui.testutil;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testfx.api.FxToolkit;

//@@author bharathcs-reused
//Reused from https://github.com/se-edu/addressbook-level4/ with minor modifications.

/**
 * Properly sets up and tears down a JavaFx stage for our testing purposes.
 */
public class StageExtension implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        FxToolkit.cleanupStages();
    }
}

//@@author
