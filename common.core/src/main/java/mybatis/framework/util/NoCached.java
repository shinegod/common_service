/**
 * 
 */
package mybatis.framework.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhiyuan.su
 * @create 2012-11-30上午11:20:37
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoCached
{
	
}
