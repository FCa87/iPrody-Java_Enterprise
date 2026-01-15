package framework.runner;

import framework.assertions.AssertException;
import framework.execution.Execution;
import framework.execution.ExecutionItem;
import framework.marker.Test;
import framework.printer.Printer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestAutomationRunner implements Runner{
    private final Printer printer;

    public TestAutomationRunner(Printer printer) {
        this.printer = printer;
    }


    @Override
    public void run(List<Class<?>> testClasses) {
        var executions = testClasses.stream()
                .map(TestAutomationRunner::runTestClass)
                .toList();

        printer.write(executions);
    }


    private static Object getInstance(Class<?> testClass) {
        try {
            return testClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Execution runTestClass(Class<?> testClass) {
        Object testClassInstance = getInstance(testClass);

        LocalDateTime startTime = LocalDateTime.now();
        var executionItems = Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .map(method -> runTestMethod(method, testClassInstance))
                .toList();
        LocalDateTime endTime = LocalDateTime.now();

        return new Execution(testClass, executionItems, startTime, endTime);
    }


    private static ExecutionItem runTestMethod(Method method, Object testClassInstance) {
        try {
            method.invoke(testClassInstance);
        } catch (InvocationTargetException e) {
            if(e.getCause() instanceof AssertException assertException) {
                return new ExecutionItem(method, assertException.getAssertResult());
            }

            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Error: test method " + method.getName() +" doesn't throw AssertException");
    }
}
