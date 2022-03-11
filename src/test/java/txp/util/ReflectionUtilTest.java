package txp.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ReflectionUtilTest {
    
    ObjectUtil ru = new ObjectUtil();

    @Test
    public void test() throws Exception {
        ClassA instance = new ClassA();
        instance.setId(1L);
        instance.setName("test");
        
        Object object = ru.export(instance, new ArrayList<>());
        String objectContent = ru.print(object, 0);
        System.out.println(objectContent);
    }

}
