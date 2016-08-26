
package com.qtz.ht.manage.annotinter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * ClassName:RandomCodeInter <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年6月14日 下午5:50:08 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomCodeInter {
	
	boolean save() default false;

    boolean remove() default false;
}
  