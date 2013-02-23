package pt.ist.bennu.console.presentationTier.commands;

import org.vaadin.console.Console;
import org.vaadin.console.Console.Command;

import pt.ist.bennu.core._development.PropertiesManager;

public class PropertyCommand implements Command {

    private static final long serialVersionUID = -2577144518231889568L;

    @Override
    public Object execute(Console console, String[] argv) throws Exception {
        if (argv.length < 3)
            return getUsage(console, argv);

        String key = argv[2];

        switch (argv[1]) {
        case "get":
            return PropertiesManager.getProperty(key);
        case "set":
            if (argv.length != 4)
                return getUsage(console, argv);
            PropertiesManager.setProperty(key, argv[3]);
            return key + " = " + argv[3];
        }
        return getUsage(console, argv);
    }

    @Override
    public String getUsage(Console console, String[] argv) {
        return "Usage: " + argv[0] + " <get/set> [propName] [propValue]";
    }

}
