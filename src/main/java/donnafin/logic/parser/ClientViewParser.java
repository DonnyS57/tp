package donnafin.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import donnafin.commons.core.Messages;
import donnafin.logic.commands.Command;
import donnafin.logic.commands.ExitCommand;
import donnafin.logic.commands.HelpCommand;
import donnafin.logic.commands.HomeCommand;
import donnafin.logic.parser.exceptions.ParseException;

public class ClientViewParser implements ParserStrategy {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    @Override
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case HelpCommand.COMMAND_WORD:
            return !arguments.equals("") ? throwsInvalidInputMsg() : new HelpCommand();

        case HomeCommand.COMMAND_WORD:
            return !arguments.equals("") ? throwsInvalidInputMsg() : new HomeCommand();

        case ExitCommand.COMMAND_WORD:
            return !arguments.equals("") ? throwsInvalidInputMsg() : new ExitCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command throwsInvalidInputMsg() throws ParseException {
        throw new ParseException(Messages.MESSAGE_USE_HELP_COMMAND);
    }
}