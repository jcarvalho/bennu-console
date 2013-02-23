package pt.ist.bennu.console.groovy;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vaadin.console.Console;
import org.vaadin.console.Console.Command;
import org.vaadin.console.Console.Handler;

import pt.ist.bennu.core.applicationTier.Authenticate;
import pt.ist.bennu.core.domain.MyOrg;
import pt.ist.bennu.core.domain.VirtualHost;

public class GroovyConsoleHandler implements Handler {

    private static final long serialVersionUID = -6836492756678152183L;

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final List<String> initialBindings;
    private final GroovyEngine engine;

    public GroovyConsoleHandler() {

        PrintStream out = new PrintStream(new BufferedOutputStream(baos));

        Map<String, Object> bindings = new HashMap<>();

        bindings.put("myorg", MyOrg.getInstance());
        bindings.put("self", Authenticate.getCurrentUser());
        bindings.put("virtualHost", VirtualHost.getVirtualHostForThread());
        bindings.put("out", out);

        initialBindings = new ArrayList<>(bindings.keySet());
        engine = new GroovyEngine(out, bindings);
    }

    @Override
    public Set<String> getSuggestions(Console console, String lastInput) {
        return Collections.emptySet();
    }

    @Override
    public void inputReceived(Console console, String lastInput) {
        String result = execute(lastInput);
        console.println(result);
        console.prompt();
    }

    public String execute(String line) {
        String result = null;
        try {
            if (line.equals("init()")) {
                result = init();
            } else {
                try {
                    engine.execute(line);
                    result = baos.toString();
                } finally {
                    resetIO();
                }
            }
        } catch (Throwable e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public void handleException(Console console, Exception e, Command cmd, String[] argv) {
        e.printStackTrace();
        console.println(e.getClass().getSimpleName() + ": " + e.getMessage());
    }

    @Override
    public void commandNotFound(Console console, String[] argv) {
        console.println("Error: Command '" + argv[0] + "' not found!");
    }

    public String init() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("                        ,===========    \n");
        buffer.append("                      :==============,  \n");
        buffer.append("                    :=================, \n");
        buffer.append("                   ~=================== \n");
        buffer.append("                  :==================== \n");
        buffer.append("                  ===================== \n");
        buffer.append("                  ====================: \n");
        buffer.append("                  =======+============  \n");
        buffer.append("                  ,==~    :=========:   \n");
        buffer.append("                   ~+    :=~~+=====~    \n");
        buffer.append("        ~=========       ==  ~========= \n");
        buffer.append("      ~=============,    =======~       \n");
        buffer.append("    ~================:   ========:      \n");
        buffer.append("   +==================~ ,==========     \n");
        buffer.append(" ,===================== ,==========,    \n");
        buffer.append(" ====================== ,=========:     \n");
        buffer.append("======================= ,=======        \n");
        buffer.append("======================: ,=======        \n");
        buffer.append("======================  ,=======        \n");
        buffer.append("=====================,  :=======        \n");
        buffer.append("=====================   ~=======        \n");
        buffer.append("===========~~========  ~========        \n");
        buffer.append("=========     =======~==========        \n");
        buffer.append(":=======:     ,==================,      \n");
        buffer.append("  ======~      ====================     \n");
        buffer.append("   ======     ,====================:    \n");
        buffer.append("    ~=====    =====================~    \n");
        buffer.append("        ,,    =====================     \n");
        buffer.append("              ===================:      \n");
        buffer.append("              ,~~~~~~~~~~~~~~~~~        \n");

        buffer.append("Available bindings:\n");

        for (String var : initialBindings) {
            buffer.append("  " + var + "\t= ");
            buffer.append(execute(var));
        }
        buffer.append("\n");

        return buffer.toString();
    }

    private void resetIO() {
        baos.reset();
    }
}
