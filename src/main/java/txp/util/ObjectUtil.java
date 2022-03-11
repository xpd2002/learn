package txp.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectUtil {
    
    public String firstLetterUpper(String text) {
        if (text != null) {
            if (text.equals("")) {
                return "";
            } else {
                return text.substring(0,1).toUpperCase() + text.substring(1);
            }
        } else {
            return null;
        }
    }
    
    public String getIndent(int indent) {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<indent;i++) {
            sb.append("    ");
        }
        return sb.toString();
    }
    
    public String print(Object object, int indent) {
        if (object instanceof String) {
            return object.toString();
        } else if (object instanceof List) {
            List list = (List)object;
            StringBuffer sb = new StringBuffer();
            sb.append("[").append("\n");
            for (int i=0;i<list.size();i++) {
                sb.append(getIndent(indent+1)).append(i).append(": ").append(print(list.get(i), indent+1)).append("\n");
            }
            sb.append(getIndent(indent)).append("]");
            
            return sb.toString();
        } else if (object instanceof Map) {
            Map map = (Map)object;
            StringBuffer sb = new StringBuffer();
            sb.append("{").append("\n");
            for (Object key : map.keySet()) {
                sb.append(getIndent(indent+1)).append(key).append(": ").append(print(map.get(key), indent+1)).append("\n");
            }
            sb.append(getIndent(indent)).append("}");
            
            return sb.toString();
        } else {
            throw new IllegalArgumentException("Invalid parameter: "+object.getClass().getCanonicalName());
        }
    }
    
    public boolean isPrimitiveWrapperClass(Class c) {
        if (java.lang.String.class.equals(c) || java.lang.Boolean.class.equals(c) || java.lang.Byte.class.equals(c) ||
                java.lang.Short.class.equals(c) || java.lang.Integer.class.equals(c) || java.lang.Long.class.equals(c) ||
                java.lang.Float.class.equals(c) || java.lang.Double.class.equals(c) || java.lang.Character.class.equals(c) ||
                java.math.BigDecimal.class.equals(c) || java.math.BigInteger.class.equals(c)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * export an object which represents the internal data of the parameter object
     * @param object the object to export internal data
     * @param knownObjects all known objects (so will export a simplified string for such objects)
     * @return if the object is primitive data, return a string
     *         if the object is array / list, reutrn a list
     *         if the object is map, return a map
     *         if the object is a real object, return a map
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object export(Object object, List<Object> knownObjects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (object == null) {
            return "Null";
        } else {
            Class c = object.getClass();
            if (c.isPrimitive()) {
                return object.toString();
            } else if (c.isArray()) {
                List<Object> result = new ArrayList<>();
                for (int i=0;i<Array.getLength(object);i++) {
                    result.add(export(Array.get(object, i), knownObjects));
                }
                return result;
            } else if (object instanceof List) {
                List list = (List)object;
                List<Object> result = new ArrayList<>();
                for (int i=0;i<list.size();i++) {
                    result.add(export(list.get(i), knownObjects));
                }
                return result;
            } else if (object instanceof Map) {
                Map map = (Map)object;
                Map<String, Object> result = new HashMap<>();
                for (Object key : map.keySet()) {
                    result.put(key.toString(), export(map.get(key), knownObjects));
                }
                return result;
            } else if (isPrimitiveWrapperClass(c)) {
                return object.toString();
            } else {
                return exportObject(object, knownObjects);
            }
        }
    }
    
    public Object exportObject(Object object, List<Object> knownObjects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (knownObjects.contains(object)) {
            return object.toString();
        } else {
            knownObjects.add(object);
        }
        
        Map<String, Object> result = new HashMap<>();
        
        Class c = object.getClass();
        result.put("class", c.getCanonicalName());
        boolean done = false;
        while (!done) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                Class fieldType = field.getType();
                
                Method method = null;
                try {
                    if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
                        method = c.getDeclaredMethod("is" + firstLetterUpper(field.getName()), new Class[0]);
                    } else {
                        method = c.getDeclaredMethod("get" + firstLetterUpper(field.getName()), new Class[0]);
                    }
                } catch(NoSuchMethodException e) {
                    ;
                }

                if (method != null) {
                    Object value = method.invoke(object, new Object[0]);
                    
                    if (value == null) {
                        result.put(field.getName(), "Null");
                    } else {
                        result.put(field.getName(), export(value, knownObjects));
                    }
                }
            }
            
            c = c.getSuperclass();
            
            if (c == null || c.equals(Object.class)) {
                done = true;
            }
        }
        
        return result;
    }
    
}
