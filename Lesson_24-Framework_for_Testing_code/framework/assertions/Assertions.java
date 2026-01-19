package framework.assertions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class Assertions {

    public static <T> void equals(T expected, T actual) throws AssertException {
        boolean success = Objects.equals(expected, actual);
        throw new AssertException(new EqualsAssertResult<>(expected, actual, success));
    }

    public static void contains(String line, String textToFetch) throws AssertException {
        boolean success = line != null && textToFetch != null && line.contains(textToFetch);
        throw new AssertException(new ContainsAssertResult(line, textToFetch, success));
    }
    
    public static <T> void contains(T[] current, T[] toContain) throws AssertException {
        boolean success = current != null && toContain != null && current.length >= toContain.length;
        if (success){
            boolean contains = false;
            for (int i = 0; i <= current.length-toContain.length; i++){
                boolean equal = true;
                for (int j = 0; j < toContain.length; j++){
                    equal &= Objects.deepEquals(current[i + j], toContain[j]);
                }
                contains |= equal;
            }
            success &= contains;
        }
        throw new AssertException(new ContainsMasAssertResult(current, toContain, success));
    }
    
    public static <T> void equalRecursively(T expected, T actual) throws AssertException {
        boolean success = Assertions.equalDeepAny(expected, actual);
        throw new AssertException(new EqualsRecursevlyAssertResult<>(expected, actual, success));
    }
    
    private static <T> boolean equalDeepAny(T t1, T t2){
        if (t1 == t2) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.getClass() != t2.getClass()) return false;
        String nameClass = t1.getClass().getSimpleName();
        if (nameClass.endsWith("Boolean")) return (Boolean)t1 == (Boolean)t2;
        if (nameClass.endsWith("Character")) return ((Character)t1).compareTo((Character)t2) == 0;
        if (nameClass.endsWith("String")) return ((String)t1).compareTo((String)t2) == 0;
        if (nameClass.endsWith("Byte")) return (Byte)t1 == (Byte)t2;
        if (nameClass.endsWith("Short")) return (Short)t1 == (Short)t2;
        if (nameClass.endsWith("Integer")) return (Integer)t1 == (Integer)t2;
        if (nameClass.endsWith("Long")) return (Long)t1 == (Long)t2;
        if (nameClass.endsWith("Float")) return (Float)t1 == (Float)t2;
        if (nameClass.endsWith("Double")) return (Double)t1 == (Double)t2;
        boolean result = true;
        Field[] fields = t1.getClass().getDeclaredFields();
        for (Field field : fields){
            Field field_2;
            try {
                field_2 = t2.getClass().getDeclaredField(field.getName());
            } catch (NoSuchFieldException ex) {
                throw new RuntimeException("Can't find field");
            }
            field.setAccessible(true);
            field_2.setAccessible(true);
            try {
                result &= Assertions.equalDeepAny(field.get(t1), field_2.get(t2));
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new RuntimeException("Can't compare values of fields");
            }
            field.setAccessible(false);
            field_2.setAccessible(false);
        }
        return result;
     }
}
