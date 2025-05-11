# Demo testjars

### Steps
1) Download https://github.com/s-barabash/bookshelf and create package ('mvn clean install -Pintegration')
2) Run `mvn clean test` it is necessary to download [bookshelf.jar](target%2Ftest-libs%2Fbookshelf.jar) to target folder. If you run test via IJ IDEA without `mvn clean test` the test will fail because it will not download the bookshelf.jar
3) Enjoy
