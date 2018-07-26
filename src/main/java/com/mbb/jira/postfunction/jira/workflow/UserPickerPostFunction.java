package com.mbb.jira.postfunction.jira.workflow;


import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.user.DelegatingApplicationUser;
import com.atlassian.jira.workflow.WorkflowException;
import com.atlassian.jira.workflow.WorkflowManager;
import com.atlassian.jira.workflow.function.issue.AbstractJiraFunctionProvider;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.opensymphony.module.propertyset.PropertySet;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * This is the post-function class that gets executed at the end of the transition.
 * Any parameters that were saved in your factory class will be available in the transientVars Map.
 */
@Scanned
public class UserPickerPostFunction extends AbstractJiraFunctionProvider
{
    private static final Logger log = LoggerFactory.getLogger(UserPickerPostFunction.class);
    public static final String FIELD_MESSAGE = "messageField";
    @JiraImport
    private final WorkflowManager workflowManager;
    @JiraImport
    private ProjectManager projectManager;
    @JiraImport
    private IssueManager issueManager;
    @JiraImport
    private CustomFieldManager customFieldManager;
    private String userPickerFrom;
    private String userPickerTo;
    private String[] userPickers;
    String message;
    public UserPickerPostFunction(ProjectManager projectManager, IssueManager issueManager, CustomFieldManager customFieldManager, WorkflowManager workflowManager) {
        this.projectManager = projectManager;
        this.issueManager = issueManager;
        this.customFieldManager = customFieldManager;
        this.workflowManager = workflowManager;
    }


    public void execute(Map transientVars, Map args, PropertySet ps) throws WorkflowException {
        MutableIssue issue = getIssue(transientVars);
        message = (String) transientVars.get(FIELD_MESSAGE);


        String customF = (String) args.get("customFields");
        userPickers = customF.split(",");

        userPickerFrom = userPickers[0];
        userPickerTo = userPickers[1];

        copyUserPicker(issue, userPickerFrom, userPickerTo);
        if (null == message) {
            message = "";
        }

        issue.setDescription(issue.getDescription() + message);
    }

    public void copyUserPicker(MutableIssue issue, String fromCustomFieldName, String toCustomFieldName) {

        CustomField copyTo = customFieldManager.getCustomFieldObject(toCustomFieldName);
        DelegatingApplicationUser usrCopyFrom = (DelegatingApplicationUser) customFieldManager.getCustomFieldObject(userPickerFrom).getValue(issue);


        if (usrCopyFrom != null) {
            Object oldValue = issue.getCustomFieldValue(copyTo);
            issue.setCustomFieldValue(copyTo, usrCopyFrom);
        } else {
            Log.error("User Picker Copy From is Empty");
            message = "Error: User Picker Copy From is Empty";
        }

    }
}