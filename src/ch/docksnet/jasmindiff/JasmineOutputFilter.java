package ch.docksnet.jasmindiff;

/**
 * @author Stefan Zeller
 */

import com.intellij.diff.DiffContentFactory;
import com.intellij.diff.DiffManager;
import com.intellij.diff.contents.DiffContent;
import com.intellij.diff.requests.DiffRequest;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.UIUtil;

public class JasmineOutputFilter implements Filter {

    private final JasmineMatcher jasmineMatcher = new JasmineMatcher();

    @Override
    public Result applyFilter(String textLine, int endPoint) {
        int startPoint = endPoint - textLine.length();

        JasmineMatch match = jasmineMatcher.hasToEqual(textLine);

        if (!match.hasMatch()) {
            return null;
        } else {
            String expected = match.expected();
            String toEqual = match.toEqual();
            return new Result(startPoint, endPoint, createLink(expected, toEqual));
        }
    }

    private HyperlinkInfo createLink(final String expected, final String toEqual) {
        return new HyperlinkInfo() {
            @Override
            public void navigate(final Project project) {
                UIUtil.invokeLaterIfNeeded(new Runnable() {
                    @Override
                    public void run() {
                        DiffContentFactory myContentFactory = DiffContentFactory.getInstance();

                        DiffContent content1 = myContentFactory.create(expected);
                        DiffContent content2 = myContentFactory.create(toEqual);

                        String title1 = "expected";
                        String title2 = "to be equal";

                        String title = "Jasmine diff";

                        DiffRequest request = new SimpleDiffRequest(title, content1, content2, title1, title2);

                        DiffManager.getInstance().showDiff(project, request);
                    }
                });

            }
        };
    }

}
