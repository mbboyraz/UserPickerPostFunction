//package ut.com.mbb.jira.postfunction.jira.workflow;
//
//import com.atlassian.jira.issue.CustomFieldManager;
//import com.atlassian.jira.issue.IssueFactory;
//import com.atlassian.jira.issue.IssueManager;
//import com.atlassian.jira.project.ProjectManager;
//import com.atlassian.jira.workflow.WorkflowManager;
//import com.mbb.jira.postfunction.jira.workflow.UserPickerPostFunction;
//
//import com.atlassian.jira.issue.MutableIssue;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class UserPickerPostFunctionTest
//{
//    public static final String MESSAGE = "my message";
//
//    protected UserPickerPostFunction function;
//    protected MutableIssue issue;
//
//    @Before
//    public void setup() {
//        issue = mock(MutableIssue.class);
//        when(issue.getDescription()).thenReturn("");
//        CustomFieldManager customFieldManager = mock(CustomFieldManager.class);
//        ProjectManager projectManager = mock(ProjectManager.class);
//        IssueManager issueManager = mock(IssueManager.class);
//        WorkflowManager workflowManager = mock(WorkflowManager.class);
//        function = new UserPickerPostFunction(projectManager,issueManager,customFieldManager,workflowManager) {
//            protected MutableIssue getIssue(Map transientVars) {
//                return issue;
//            }
//        };
//    }
//
//    @Test
//    public void testNullMessage() throws Exception
//    {
//        Map transientVars = Collections.emptyMap();
//        function.execute(transientVars, null, null);
//
//        verify(issue).setDescription("");
//    }
//
//    @Test
//    public void testEmptyMessage() throws Exception
//    {
//        Map transientVars = new HashMap();
//        transientVars.put("messageField", "");
//        function.execute(transientVars, null, null);
//
//        verify(issue).setDescription("");
//    }
//
//    @Test
//    public void testValidMessage() throws Exception
//    {
//        Map transientVars = new HashMap();
//        transientVars.put("messageField", MESSAGE);
//        function.execute(transientVars, null, null);
//
//        verify(issue).setDescription(MESSAGE);
//    }
//
//}
