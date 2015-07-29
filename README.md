# netty-JBoss-Marshalling
使用JBoss-marshalling编码包进行java的序列号编码<br>

JBoss Marshalling是一个Java对象的序列化API包，修正了JDK自带的序列化包的很多问题，<br>
但又保持跟java.io.Serializable接口的兼容；同时增加了一些可调的参数和附加的特性，并且这些参数和特性可通过工厂类进行配置。<br>

相比于传统的Java序列化机制，它的优点如下：<br>

1) 可插拔的类解析器，提供更加便捷的类加载定制策略，通过一个接口即可实现定制；<br>

2) 可插拔的对象替换技术，不需要通过继承的方式；<br>

3) 可插拔的预定义类缓存表，可以减小序列化的字节数组长度，提升常用类型的对象序列化性能；<br>

4) 无须实现java.io.Serializable接口，即可实现Java序列化；<br>

5) 通过缓存技术提升对象的序列化性能。<br>

JBoss Marshalling更多是在JBoss内部使用，应用范围有限。<br>
