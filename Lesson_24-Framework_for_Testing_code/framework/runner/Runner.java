package framework.runner;

import java.util.List;

public interface Runner {
    void run(List<Class<?>> testClasses);
}
