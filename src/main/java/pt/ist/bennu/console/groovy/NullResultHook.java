package pt.ist.bennu.console.groovy;

import groovy.lang.Closure;

public class NullResultHook extends Closure<Object> {

    private static final long serialVersionUID = -6305206715273979894L;

    public NullResultHook(Object owner) {
        super(owner);
    }

    @Override
    public Object call(Object obj) {
        return null;
    }

}
