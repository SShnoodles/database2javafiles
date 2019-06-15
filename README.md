# database2javafiles
Generating java files from database tables.

download [jar.zip](https://github.com/SShnoodles/database2javafiles/releases)

**New plugin!!!** [Idea Plugin](https://github.com/SShnoodles/db2j-idea-plugin)

# Support database
- oracle
- postgresql
- mysql

# Two config properties
1. first load outside "app.properties"
2. second load resources "app.properties"

# Configuration file
```
db=oracle
host=localhost
port=1521
servername=ORCL
username=test
password=123456
outpath=./tmp/
templates=jpa,repository
author=ssnoodles
overwritefiles=false
single.table.name=
single.table.rename=
```

# Templates
Jpa、Dto、Do、Repository、Controller

# Quick start
```java
// load config
final Config config = FileUtil.PROPERTIES;
// create factory
DbFactory dbFactory = new DbFactoryImpl();
// generate template
dbFactory.create(config.getDb(), new JpaTemplate());
```

# other
[PredicateUtils](code.md)