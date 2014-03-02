package ch.iterativ.es.plugin.listindices;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;

import java.util.Collection;

public class ListIndicesPlugin extends AbstractPlugin {

    @Override public String name() {
        return "listindices";
    }

    @Override public String description() {
        return "ListIndicesPlugin plugin";
    }

    @Override public Collection<Class<? extends Module>> modules() {
        Collection<Class<? extends Module>> modules = Lists.newArrayList();
        modules.add(ListIndicesModule.class);
        return modules;
    }
}
