
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.TreeMap;

public class MyLightTestFramework {

    public static class TestRunner {

        public static void start(Class classForTest) {
            Method[] methods = classForTest.getDeclaredMethods();
            Method beforeSuite = null;
            Method afterSuite = null;
            TreeMap<Integer, LinkedList<Method>> tests = new TreeMap<>();
            for (Method m : methods) {
                Annotation[] annotations = m.getDeclaredAnnotations();
                if (annotations.length != 0) {
                    for (int i = 0; i < annotations.length; i++) {
                        String buf = annotations[i].toString();
                        if (buf.startsWith("@Test")) {
                            Integer key = Integer.valueOf(buf.split("[=)]")[1]);
                            if (tests.containsKey(key)) {
                                tests.get(key).add(m);
                            } else {
                                LinkedList<Method> bufList = new LinkedList<>();
                                bufList.add(m);
                                tests.put(key, bufList);
                            }
                        } else if (buf.startsWith("@BeforeSuite")) {
                            if (beforeSuite == null) {
                                beforeSuite = m;
                            } else {
                                throw new RuntimeException("There are more than one BeforeSuite method");
                            }
                        } else if (buf.startsWith("@AfterSuite")) {
                            if (afterSuite == null) {
                                afterSuite = m;
                            } else {
                                throw new RuntimeException("There are more than one AfterSuite method");
                            }
                        }
                    }
                }
            }
            if (afterSuite == null) throw new RuntimeException("There is no AfterSuite method");
            Object objForTests;
            try {
                objForTests = classForTest.getConstructor().newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Can not create an object for testing");
            }
            if (beforeSuite != null) {
                try {
                    beforeSuite.setAccessible(true);
                    beforeSuite.invoke(objForTests);
                    beforeSuite.setAccessible(false);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    throw new RuntimeException("There is a problem in BeforeSuite method");
                }
            }

            tests.forEach((x, y) -> {
                y.forEach(z -> {
                    try {
                        z.setAccessible(true);
                        z.invoke(objForTests);
                        z.setAccessible(false);
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        throw new RuntimeException("There is a problem in test: " + z.toString());
                    }
                });
            });
            try {    
                afterSuite.setAccessible(true);
                afterSuite.invoke(objForTests);
                afterSuite.setAccessible(false);
            }
            catch (IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException("There is a problem in AfterSuite method");
            }
        }

    }

}
