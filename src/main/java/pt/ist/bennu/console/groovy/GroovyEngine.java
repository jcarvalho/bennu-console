package pt.ist.bennu.console.groovy;

import groovy.lang.Binding;

import java.io.PrintStream;
import java.util.Map;

import org.codehaus.groovy.tools.shell.Groovysh;
import org.codehaus.groovy.tools.shell.IO;

public class GroovyEngine {

    private final Groovysh groovy;

    public GroovyEngine(PrintStream stream, Map<String, Object> bindings) {

        IO io = new IO(System.in, stream, stream);
        Binding binding = new Binding(bindings);

        groovy = new Groovysh(binding, io);

        groovy.setResultHook(new NullResultHook(groovy));

        for (String imports : GroovyImports.getImports()) {
            groovy.execute("import " + imports);
        }

        groovy.setResultHook(new GroovyResultHook(groovy, io));
    }

    public void execute(String var) {
        groovy.execute(var);
    }

}
