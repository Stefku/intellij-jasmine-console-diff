package ch.docksnet.jasmindiff;

import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author Stefan Zeller
 */
public class JasmineFilterProvider implements ConsoleFilterProvider {

    @NotNull
    @Override
    public Filter[] getDefaultFilters(@NotNull Project project) {
        Filter filter = new JasmineOutputFilter();
        return new Filter[]{filter};
    }

}
