import java.lang.reflect.*;

/*
 * When printing, try to follow the structure of the program 
 * Makes it easy to understand
 * 
 * 
 * Do this at the end of inspecting the current class
 * If recursion = set and c.getSuperClass != null then{
 * inspectClass(superClass)
 * }
 * 
 * Need to find out how to stop recursion?
 * How do we know that the superclass needs recursion too?
 * Instantiate superclass and check if it has a superclass
 * 
 * Add tabs/spaces to output.
 */


public class Inspector {
    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }
	Class clz;
	public void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		if(c!=null) {
			System.out.println(addTabs(depth) + c.getName());
			if(c.getName() != "java.lang.Object"){
				System.out.println(addTabs(depth) + "SuperClass:" + c.getSuperclass().getSimpleName());
			
				System.out.println(addTabs(depth) + c.isInterface());
				//System.out.println(clz.);   Can use this to check for all kinds of things
			
				//Use declared!!
				if(c.getDeclaredFields().length>0) {
					inspectFields(c, depth);
				}
				if(c.getDeclaredMethods().length>0) {
					inspectMethods(c, depth);
				}
				if(recursive == true){
					if(c.getSuperclass().getSimpleName() == "Object"){
						inspectClass(c.getSuperclass(), obj, false, depth + 1);
					}
					inspectClass(c.getSuperclass(), obj, true, depth + 1);
				}
			}
		}
	}
	
	
	
	private void inspectFields(Class c, int depth) {
		Field[] flds = c.getDeclaredFields();
		//Loop through fields array to find info from them as per assignment specs (name, type, modifiers, currentValue)
		for(Field fld :flds) {
			String name = fld.getName();
			Class type = fld.getType();
			int mods = fld.getModifiers();
			
			System.out.println(" " + addTabs(depth) + Modifier.toString(fld.getModifiers()) + " " + type.getName() + " " + name);
		}
		
	}
	
	private void inspectMethods(Class c, int depth) {
		Method[] mtds = c.getDeclaredMethods();
		
		for(Method m: mtds) {
			String name = m.getName();
			Class returnType = m.getReturnType();
			
			//Get modifiers is the same as Fields
			System.out.println(" " + addTabs(depth) + returnType.getName() + " " + name);
			Parameter[] params = m.getParameters();
			for(Parameter p : params) {
				System.out.println(" " + addTabs(depth) + p.getType() + " " + p.getName());
			}
		}
		
		/*Method m = null;
		try {
			m = clz.getMethod("priceCode");
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Allows for the invocation of private methods in the object you are trying to run
		m.setAccessible(true);
		try {
			Object o = m.invoke(new Movie());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
    	
	private String addTabs(int depth){
		String tabs = "";
		for(int i = 0; i < depth; i++){
			tabs = tabs + "    ";
		}
		return tabs;
	}
	
}
