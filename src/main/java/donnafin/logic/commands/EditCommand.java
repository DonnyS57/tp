package donnafin.logic.commands;

import static donnafin.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static donnafin.logic.parser.CliSyntax.PREFIX_EMAIL;
import static donnafin.logic.parser.CliSyntax.PREFIX_NAME;
import static donnafin.logic.parser.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import java.util.function.Consumer;

import donnafin.logic.PersonAdapter;
import donnafin.logic.commands.exceptions.CommandException;
import donnafin.logic.parser.exceptions.ParseException;
import donnafin.model.Model;
import donnafin.ui.Ui;


/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "Example: " + COMMAND_WORD
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Successful edit.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final PersonAdapter personAdapter;
    private final Consumer<PersonAdapter> editor;


    /**
     * @param personAdapter of the person in the filtered person list to edit
     * @param editor new editor for the contact.
     */
    public EditCommand(PersonAdapter personAdapter, Consumer<PersonAdapter> editor) {
        requireNonNull(personAdapter);
        requireNonNull(editor);

        this.personAdapter = personAdapter;
        this.editor = editor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        editor.accept(personAdapter);

        //Used to refresh UI to display new attribute added
        Consumer<Ui> refresh = x -> {
            try {
                x.switchClientViewTab(x.getUiState());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };

        return new CommandResult(MESSAGE_EDIT_PERSON_SUCCESS, refresh);
    }
}
