package ch.iterativ.es.plugin.listindices;

import ch.iterativ.es.plugin.listindices.rest.IndicesRestHandler;
import org.elasticsearch.common.inject.AbstractModule;

public class ListIndicesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IndicesRestHandler.class).asEagerSingleton();
    }

}
