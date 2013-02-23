package pt.ist.bennu.console.groovy;

import groovy.lang.Closure;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.groovy.tools.shell.IO;

public class GroovyResultHook extends Closure<Object> {

    private static final long serialVersionUID = -2689737412788025005L;

    private final IO io;

    public GroovyResultHook(Object owner, IO io) {
        super(owner);
        this.io = io;
    }

    @Override
    public Object call(final Object... args) {
        Object result = args[0];
        Iterator<?> itty;
        if (result instanceof Iterator) {
            itty = (Iterator<?>) result;
        } else if (result instanceof Iterable) {
            itty = ((Iterable<?>) result).iterator();
        } else if (result instanceof Object[]) {
            itty = Arrays.asList((Object[]) result).iterator();
        } else if (result instanceof Map) {
            itty = ((Map<?, ?>) result).entrySet().iterator();
        } else if (result == null) {
            itty = Collections.singleton("").iterator();
        } else {
            itty = Collections.singleton(result).iterator();
        }

        while (itty.hasNext()) {
            this.io.out.println(itty.next());
        }

        return null;
    }

}
