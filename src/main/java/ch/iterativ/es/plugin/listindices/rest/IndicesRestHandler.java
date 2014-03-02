package ch.iterativ.es.plugin.listindices.rest;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.*;
import org.elasticsearch.rest.action.support.RestXContentBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static org.elasticsearch.rest.RestRequest.Method.GET;
import static org.elasticsearch.rest.RestStatus.OK;

public class IndicesRestHandler extends BaseRestHandler {

    @Inject
    public IndicesRestHandler(Settings settings, Client client, RestController controller) {
        super(settings, client);
        controller.registerHandler(GET, "/_indices", this);
    }

    @Override
    public void handleRequest(final RestRequest request, final RestChannel channel) {
        IndicesStatsRequest indicesStatsRequest = new IndicesStatsRequest();
        indicesStatsRequest.listenerThreaded(false);
        indicesStatsRequest.clear();

        client.admin().indices().stats(indicesStatsRequest, new ActionListener<IndicesStatsResponse>() {
            @Override
            public void onResponse(IndicesStatsResponse response) {
                try {
                    XContentBuilder builder = RestXContentBuilder.restContentBuilder(request);
                    builder.startObject();
                    buildIndexList(builder, response.getIndices());
                    builder.endObject();
                    channel.sendResponse(new XContentRestResponse(request, OK, builder));
                } catch (Throwable e) {
                    onFailure(e);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                try {
                    channel.sendResponse(new XContentThrowableRestResponse(request, e));
                } catch (IOException e1) {
                    logger.error("Failed to send failure response", e1);
                }
            }

            public XContentBuilder buildIndexList(XContentBuilder builder, Map<String, IndexStats> indexStatsHashMap) throws IOException {
                ArrayList<String> indexNames = new ArrayList<String>();
                for (IndexStats indexStats : indexStatsHashMap.values()) {
                    indexNames.add(indexStats.getIndex());
                }
                Collections.sort(indexNames);
                String[] indices = indexNames.toArray(new String[indexNames.size()]);
                builder.array("indices", indices);
                return builder;
            }

        });
    }
}
