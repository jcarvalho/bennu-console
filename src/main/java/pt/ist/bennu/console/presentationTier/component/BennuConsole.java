package pt.ist.bennu.console.presentationTier.component;

import java.util.Map;

import org.vaadin.console.Console;

import pt.ist.bennu.console.groovy.GroovyConsoleHandler;
import pt.ist.bennu.core.applicationTier.Authenticate;
import pt.ist.bennu.core.domain.RoleType;
import pt.ist.bennu.core.domain.User;
import pt.ist.vaadinframework.annotation.EmbeddedComponent;
import pt.ist.vaadinframework.ui.EmbeddedComponentContainer;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@EmbeddedComponent(path = { "BennuConsole" })
public class BennuConsole extends CustomComponent implements EmbeddedComponentContainer {

    private static final long serialVersionUID = -9054956655435582494L;

    private final Layout layout = new VerticalLayout();
    private final Console console = new Console();

    public BennuConsole() {
        layout.addComponent(new Label("Bennu Console"));
        layout.addComponent(console);

        GroovyConsoleHandler handler = new GroovyConsoleHandler();

        console.setHandler(handler);

        console.setPs("> ");
        console.setCols(110);
        console.setRows(40);
        console.setMaxBufferSize(50);
        console.setGreeting("Welcome to the Bennu Console!");
        console.reset();
        console.focus();

        handler.execute("init()");

        setCompositionRoot(layout);
    }

    @Override
    public void setArguments(Map<String, String> arguments) {
        // No-op
    }

    @Override
    public boolean isAllowedToOpen(Map<String, String> parameters) {
        User current = Authenticate.getCurrentUser();
        return current != null && current.hasRoleType(RoleType.MANAGER);
    }

}
