package pt.ist.bennu.console.presentationTier.commands;

import java.lang.reflect.Field;

import org.apache.commons.pool.KeyedObjectPool;
import org.apache.ojb.broker.core.PersistenceBrokerFactoryFactory;
import org.vaadin.console.Console;
import org.vaadin.console.Console.Command;

public class DBCommand implements Command {

    private static final long serialVersionUID = -8239633307971030328L;

    @Override
    public Object execute(Console console, String[] argv) throws Exception {
        Object brokerFactory = PersistenceBrokerFactoryFactory.instance();
        Field field = brokerFactory.getClass().getSuperclass().getDeclaredField("brokerPool");
        field.setAccessible(true);
        KeyedObjectPool<?, ?> pool = (KeyedObjectPool<?, ?>) field.get(brokerFactory);
        return "Active: " + pool.getNumActive() + ". Idle: " + pool.getNumIdle();
    }

    @Override
    public String getUsage(Console console, String[] argv) {
        // TODO Auto-generated method stub
        return null;
    }

}
