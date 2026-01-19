package framework.runner;

import framework.execution.Execution;
import framework.printer.Printer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ExtendedTestAutomationRunner implements Runner{
        private final List<Printer> printers = new LinkedList<>();

    public ExtendedTestAutomationRunner(Printer printer) {
        this.printers.add(printer);
    }
        
    public ExtendedTestAutomationRunner(Collection<Printer> inPrinters) {
        for (Printer printer : inPrinters){
            boolean add = true;
            for (Printer p : this.printers){
                if (ExtendedTestAutomationRunner.equalPrinters(printer, p)) add = false;
            }
            if (add) printers.add(printer);
        }
    }

    public void addPrinter(Printer printer){
        boolean add = true;
        for (Printer p : this.printers) {
            if (ExtendedTestAutomationRunner.equalPrinters(printer, p)) add = false;
        }
        if (add) printers.add(printer);
    }
    
    @Override
    public void run(List<Class<?>> testClasses) {
        Method method;
        try {
            method = TestAutomationRunner.class.getDeclaredMethod("runTestClass", Class.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException ex) { 
            throw new RuntimeException("No access to private static method metod \"runTestClass\"");
        }
        List<Execution> executions = new LinkedList<>();
        for (Class<?> curClass : testClasses){
            try {
                executions.add((Execution) method.invoke(null, curClass));
            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException("Can't invoke private static method metod \"runTestClass\"");
            }
        }
        method.setAccessible(false);
        for (Printer printer : this.printers) {
            printer.write(executions);
        }
    }
    
    private static boolean equalPrinters(Printer p1, Printer p2){
        if (p1 == p2) return true;
        if (p1 == null || p2 == null) return false;
        if (p1.getClass() != p2.getClass()) return false;
        boolean result = true;
        Field[] fields = p1.getClass().getDeclaredFields();
        for (Field field : fields){
            Field field_2;
            try {
                field_2 = p2.getClass().getDeclaredField(field.getName());
            } catch (NoSuchFieldException ex) {
                throw new RuntimeException("Can't find field");
            }
            field.setAccessible(true);
            field_2.setAccessible(true);
            try {
                result &= Objects.deepEquals(field.get(p1), field_2.get(p2));
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new RuntimeException("Can't compare values of fields");
            }
            field.setAccessible(false);
            field_2.setAccessible(false);
        }
        return result;
    }
    
}
