package pt.ist.bennu.console.groovy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import pt.ist.fenixframework.FenixFramework;

import com.google.common.collect.Sets;

public class GroovyImports {

    public static Collection<String> getImports() {

        @SuppressWarnings("unchecked")
        Set<Class<? extends Object>> classes = Sets.newHashSet(FenixFramework.class, DateTime.class, LocalDate.class);

        Set<String> packages = Sets.newHashSet("pt.ist.bennu.core", "pt.ist.bennu.core.domain");

        List<String> imports = new ArrayList<>();

        for (Class<?> clazz : classes) {
            imports.add(clazz.getName());
        }

        for (String pack : packages) {
            imports.add(pack + ".*");
        }

        return imports;

    }

}
