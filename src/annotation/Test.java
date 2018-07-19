package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 模拟一个orm框架实现由对象到关系的转换，第三方框架如Hibernate、Mybatis也是一样的套路
 * 1.创建注解
 * 2.在类中使用注解
 * 3.利用反射解析注解
 * @author DonkeyHu
 *
 */
public class Test {
	public static void main(String[] args) {
		try {
			Class c=Class.forName("com.donkeyhu.annotation.Student");
			//获取类的有效注解
			Annotation[] annotations = c.getAnnotations();
			for (Annotation annotation : annotations) {
				System.out.println(annotation);
			}
			//获得指定的注解
			AnnoTable at=(AnnoTable) c.getDeclaredAnnotation(AnnoTable.class);
			System.out.println(at.value());
			
			Field f=c.getDeclaredField("studentName");
			AnnoField af = f.getDeclaredAnnotation(AnnoField.class);
			System.out.println(af.columnName()+"--"+af.type()+"--"+af.length());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//然后来拼接sql语句
	}
}
