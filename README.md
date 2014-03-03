## ListIndices elasticsearch plugin

List all indices existing in an elasticsearch node.

### Install ListIndices

```bash
bin/plugin --url http://sourceforge.net/projects/elasticsearch-listindices/files/ListIndicesPlugin-1.0-plugin.zip/download --install listindices
```

### Building & Installing ListIndices from source

```bash
$ mvn package
# in elasticsearch home
$ bin/plugin --url file:///<path-to-plugin>/target/releases/ListIndicesPlugin-1.0-SNAPSHOT-plugin.zip --install listindices
```

### Uninstall Listindices plugin

```bash
$ bin/plugin --remove listindices
```

### Using ListIndices

Request indices:
```rest
GET /_indices
```

Example output:
```json
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
```
