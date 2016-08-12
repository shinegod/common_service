/**
 * 
 */
package mybatis.common;

import mybatis.framework.components.code.CodeGenerator;

import java.io.IOException;
import java.net.URL;


/**
 * 生成基础代码, 注意两个配置文件: generatorConfig.xml, mybatis.properties
 */
public class CodeUtil
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// 前台
		String generatorConfig = ClassLoader.getSystemResource("").getFile() + "code/generator.xml";
		CodeGenerator cg = new CodeGenerator(generatorConfig);
		cg.run();
		
//		// 后台
//		String generatorConfig = CodeUtil.class.getClassLoader().getResource("code/generatorAdmin.xml").getFile();
//		CodeGenerator cg = new CodeGenerator(generatorConfig);
//		cg.run();
	}

}
