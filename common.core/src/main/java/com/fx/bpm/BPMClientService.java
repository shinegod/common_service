package com.fx.bpm;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * Created by bei2love@gmail.com on 15/8/25.
 * 引擎的客户端服务，供外部调用.
 */

public interface BPMClientService {

    /**
     * 启动流程
     *
     * @param processDefinitionKey 非空，流程定义的key
     * @param businessKey 可以为null，最长255个字，业务主键
     * @param variables 可以为null，流程变量
     * @param authenticatedUserId 可以为null，设置当前认证的用户，可用于流程启动的初始人
     * @return
     */
    ProcessInstance doProcessStart(String processDefinitionKey,String businessKey,
                                   Map<String, Object> variables,String authenticatedUserId);

    /**
     * 获取流程实例历史
     * @param processInstanceId
     * @return
     */
    HistoricProcessInstance getHistoricProcessInstance(String processInstanceId);

    /**
     * 获取流程实例历史
     * @param objects  参数顺序:procDefName、procDefKey、startTimeBegin、startTimeEnd、participantPerson、runState
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<HistoricProcessInstance> getHistoricProcessInstanceList(
            Object[] objects, int startIndex, int pageSize);

    /**
     * 获取流程实例历史
     * @param processInstanceId
     * @return
     */
    List<HistoricTaskInstance> getHistoricTaskList(String processInstanceId);

    /**
     * 通过任务标识获取任务信息
     * @param taskId
     * @return
     */
    Task getTaskById(String taskId);

    /**
     * 通过任务标识获取任务历史
     * @param taskId
     * @return
     */
    HistoricTaskInstance getHistoricTaskInstance(String taskId);
    /**
     * 获取任务变量
     * @param taskId
     * @return
     */
    Map<String,Object> getTaskVariable(String taskId);

    /**
     * 获取流程实例
     * @param definedId
     * @return
     */
    List<ProcessInstance> getProcessIntanceByDefinedId(String definedId);

    /**
     * 通过业务主键和流程定义标识获取任务实例列表
     * @param processDefinitionKey
     * @param businessKey
     * @return
     */
    List<Task> getTaskWithBusinessKey(String processDefinitionKey, String businessKey);

    /**
     * 通过流程实例获取当前最新的任务列表，可用在任务完成后获取下一步的任务列表
     * （任务必须执行到用户任务，流程刚启动或正在执行ServerTask，此接口返回的数据不保证最新，可能是上一步的）
     * @param processInstanceId
     * @return
     */
    List<Task> getTaskLatest(String processInstanceId);

    /**
     * 获取任务定义信息
     * @param processDefinitionKey
     * @param taskDefinitionKey
     * @return
     */
    TaskDefinition getTaskDefinition(String processDefinitionKey, String taskDefinitionKey);

    /**
     * 获取当前部门或人的候选任务列表(未签收)
     * @param candidateGroups 部门标识列表，可以为null
     * @param candidateUser 用户标识，可以为null
     * @return
     */
    List<Task> getTaskCandidateList(List<String> candidateGroups,String candidateUser);

    /**
     * 获取当前部门或人的候选任务列表(未签收)
     * @param candidateGroups
     * @param candidateUser
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Task> getTaskCandidateList(List<String> candidateGroups,String candidateUser,int pageIndex,int pageSize);


    /**
     * 任务签收
     * @param taskId 任务标识，非空
     * @param userId 用户标识，如果为null则执行unclaimed，不分配任何执行人。
     * @throws ActivitiObjectNotFoundException
     * @throws ActivitiTaskAlreadyClaimedException
     */
    void doTaskClaim(String taskId, String userId);

    /**
     * 完成任务
     * @param taskId
     * @param variables
     */
    void doTaskComplete(String taskId, Map<String, Object> variables);

    /**
     * 更新流程变量。可用在流程变量保存及回滚
     * @param taskId
     * @param variables
     */
    void doTaskVariableUpdate(String taskId,Map<String,Object> variables);

    /**
     * 更新业务标识
     *
     * @param processInstanceId
     * @param businessKey
     */
    void doBusinessKeyUpdate(String processInstanceId, String businessKey);

    /**
     * 获取流程图
     * @param processDefinitionId
     * @return
     */
    InputStream getProcessDiagram(String processDefinitionId);

    /**
     * 获取流程图
     * @param processInstanceId 流程实例ID
     * @param isHighlight 是否高亮显示，用于流程监控与跟踪
     * @return
     */
    InputStream getProcessDiagram(String processInstanceId,Boolean isHighlight);

    /**
     * 获取流程图
     * @param processInstanceId 流程实例ID
     * @param isHighlight 是否高亮显示，用于流程监控与跟踪
     * @return
     */
    InputStream getHistoryProcessDiagram(String processInstanceId,String taskId,Boolean isHighlight);

    /**
     * 获取任务实例的业务主键
     * @param processInstanceId
     * @return
     */
    String getBusinessKey(String processInstanceId);

    /**
     * 获取任务实例的业务主键
     * @param taskId
     * @return
     */
    String getBusinessKeyWithTaskId(String taskId);

    /**
     * 获取业务表单的外部链接
     * @param taskId
     * @return
     */
    String getFormKey(String taskId);

    /**
     * 获取流程实例
     * @param processInstanceId
     * @return
     */
    ProcessInstance getProcessInstance(String processInstanceId);

    /**
     * 获取启动流程实例的用户标识
     * @param processInstanceId
     * @return
     */
    String getProcessInstanceStartUserId(String processInstanceId);

    /**
     * 获取流程任务列表
     * @param processDefinitionId 流程定义ID
     * @return
     */
    List<Task> getTaskByProcessDefinedId(String processDefinitionId);

    /**
     * 根据流程定义标识和业务主键来查找当前任务的业务实体.
     *
     * @param procKey
     *            processDefiniKey
     * @param bizKey
     *            businessKey
     * @return entity或null
     */
    <T> T getBusinessEntity(String procKey, String bizKey);

    /**
     * 获取历史流程任务列表
     * @param processInstanceId 流程实例ID
     * @return
     */
    List<HistoricTaskInstance> getTaskHistory(String processInstanceId);


}
