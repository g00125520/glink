# glink

stream programming

## vscode

vscode默认的插件安装路径为：HOME/.vscode/extension，默认工作空间为：HOME/.vscode/workspaceStorage，可通过修改快捷方式中的目标修改上面的位置：Code.exe" --user-data-dir  "E:\vscode\workspaceStorage"  --extensions-dir "E:\vscode\extensions"

可通过mvn的archetype快速生成java项目：mvn archetype:generate -DarchetypeGroupId=org.apache.flink  -DarchetypeArtifactId=flink-walkthrough-datastream-java -DarchetypeVersion=1.12.0 -DgroupId=com.ld -DartifactId=glink -Dversion=0.1 -Dpackage=com.ld -DinteractiveMode=false，由于vscode是基于eclipse项目的方式进行管理的，所以可先将项目转换为标准的eclipse项目后导入vscode，mvn eclipse:eclipse -DdownloadSources -DdownloadJavadocs。

mvn -Declipse.workspace="path/to/your/eclipse/workspace" eclipse:configure-workspace
可以生成.classpath中的m2_repo环境变量，当然，也可以手工添加到项目的.settings/org.eclipse.jdt.core.prefs文件中。

vscode不使用系统的mvn下conf的setting.xml，而默认使用系统用户下面的：.m2/settings.xml文件，可以在.vscode/settings.json中进行修改："java.configuration.maven.userSettings": "M2_HOME/conf/settings.xml"。
