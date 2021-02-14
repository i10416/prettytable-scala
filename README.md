## prettytable-scala cli

A simple CLI tool to pritty print table (such as csv file) in terminal witten in Scala 3 available without jre.

### TODO
- [ ] rename scala3-simple to prettytable-scala

### example

```test.csv
id,name,icon,comment,favorite
1,tama,😼,meow,matatabi
2,pochi,🐶,bowwow,???
```

./prettytable-scala test.csv

```shell
id|name |icon|comment|favorite
==+=====+====+=======+========
1 |tama |😼  |meow   |matatabi
--+-----+----+-------+--------
2 |pochi|🐶  |bowwow |???
```

This project is compiled with Dotty and GraalVM native-package!

