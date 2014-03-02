# ListIndices elasticsearch plugin 

List all index names in a cluster.

# Install ListIndices

bin/plugin --url file:///.... ListIndicesPlugin-1.0-plugin.zip --install listindices

# Building & Installing ListIndices

```bash
$ mvn package
# in elasticsearch home
$ bin/plugin --url file:///<path-to-plugin>/target/releases/ListIndicesPlugin-1.0-SNAPSHOT-plugin.zip --install listindices
```

# Installing ListIndices 

```bash

```

# Using ListIndices 

GET /_indices

Example output:
{
   "indices": [
      ".marvel-2014.02.26",
      ".marvel-2014.02.27",
      ".marvel-2014.02.28",
      ".marvel-2014.03.01",
      ".marvel-2014.03.02",
      "events",
      "haystack",
      "itemresults"
   ]
}
