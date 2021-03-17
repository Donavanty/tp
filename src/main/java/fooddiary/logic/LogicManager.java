package fooddiary.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import fooddiary.model.ReadOnlyFoodDiary;
import javafx.collections.ObservableList;
import fooddiary.commons.core.GuiSettings;
import fooddiary.commons.core.LogsCenter;
import fooddiary.logic.commands.Command;
import fooddiary.logic.commands.CommandResult;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.logic.parser.AddressBookParser;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.Model;
import fooddiary.model.entry.Entry;
import fooddiary.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getFoodDiary());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFoodDiary getAddressBook() {
        return model.getFoodDiary();
    }

    @Override
    public ObservableList<Entry> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
