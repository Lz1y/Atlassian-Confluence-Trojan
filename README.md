# Atlassian-Confluence-Trojan   
Ref:http://www.lz1y.cn/archives/1439.html
Atlassian-Confluence-Trojan  
Get a shell by deploy this Trojan-Marco  
制作方法:  
1. 先将quote-macro-1.0.jar解压  
2. 将 UpdateMethod.java 中的{path}替换为目录的绝对路径  
3. 然后替换 UpdateMethod.java 中的{Your payload}为要执行的代码。  
4. 然后在目录中执行:  
`javac -cp "javassist.jar:" UpdateMethod.java`    
`java -cp 'javassist.jar:confluence-5.10.7.jar:' UpdateMethod`
5. 目录中将会生成QuoteMacro.class文件，我们将JAR以压缩包打开，然后替换`/com/unlimax/confluence/plugin/macro`路径下的QuoteMacro.class。
