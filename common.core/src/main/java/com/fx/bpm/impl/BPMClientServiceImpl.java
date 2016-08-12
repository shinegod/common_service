package com.fx.bpm.impl;

import com.fx.bpm.BPMClientService;
import com.fx.util.Constants;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/8/25.
 */
//@Component
public class BPMClientServiceImpl implements BPMClientService {

    private static final Logger logger = LoggerFactory.getLogger(BPMClientServiceImpl.class);

    @Autowired
    private ProcessEngine processEngine;

    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public ProcessInstance doProcessStart(String processDefinitionKey, String businessKey, Map<String, Object> variables, String authenticatedUserId) {
        try {
            processEngine.getIdentityService().setAuthenticatedUserId(authenticatedUserId);
            return processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, businessKey, variables);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            processEngine.getIdentityService().setAuthenticatedUserId(null);
        }
    }

    @Override
    public HistoricProcessInstance getHistoricProcessInstance(String processInstanceId) {
        return processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public List<HistoricProcessInstance> getHistoricProcessInstanceList(Object[] objects, int startIndex, int pageSize) {
        return getHistoricProcessInstanceQueryByObjectArray(objects).listPage(startIndex, pageSize);
    }

    private HistoricProcessInstanceQuery getHistoricProcessInstanceQueryByObjectArray(
            Object[] objects) {
        HistoricProcessInstanceQuery query = processEngine.getHistoryService().createHistoricProcessInstanceQuery();
        if (objects != null) {
            if (objects.length > 0 && objects[0] != null) {
                List<ProcessDefinition> procDefList = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKeyLike(objects[0].toString()).list();
                List<String> deployIdList = new ArrayList<String>();
                for (ProcessDefinition procDef : procDefList) {
                    deployIdList.add(procDef.getDeploymentId());
                }
                //发现如果查询出来的集合为空，那这个条件会被忽略
                if (deployIdList.size() == 0) {
                    deployIdList.add("");
                }
                query.deploymentIdIn(deployIdList);
            }
            if (objects.length > 1 && objects[1] != null) {
                query.processDefinitionKey(objects[1].toString());
            }
            if (objects.length > 2 && objects[2] != null) {
                query.startedAfter((Date) objects[2]);
            }
            if (objects.length > 3 && objects[3] != null) {
                query.startedBefore((Date) objects[3]);
            }
            if (objects.length > 4 && objects[4] != null) {
                query.involvedUser(objects[4].toString());
            }
            if (objects.length > 5 && objects[5] != null) {
                if ("finished".equals(objects[5].toString())) {
                    query.finished();
                } else if ("running".equals(objects[5].toString())) {
                    query.unfinished();
                }
            }
            if (objects.length > 6 && objects[6] != null) {
                query.startedBy(objects[6].toString());
            }
        }
        query.orderByProcessInstanceStartTime().desc().orderByProcessInstanceEndTime().desc();
        return query;
    }

    @Override
    public List<HistoricTaskInstance> getHistoricTaskList(String processInstanceId) {
        return processEngine.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
    }

    @Override
    public Task getTaskById(String taskId) {
        return processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public HistoricTaskInstance getHistoricTaskInstance(String taskId) {
        return processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
    }

    @Override
    public Map<String, Object> getTaskVariable(String taskId) {
        return processEngine.getTaskService().getVariables(taskId);
    }

    @Override
    public List<ProcessInstance> getProcessIntanceByDefinedId(String definedId) {
        return processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionId(definedId).orderByProcessInstanceId().desc().list();
    }

    @Override
    public List<Task> getTaskWithBusinessKey(String processDefinitionKey, String businessKey) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().list();

        return list;
    }

    @Override
    public List<Task> getTaskLatest(String processInstanceId) {
        return processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).list();
    }

    @Override
    public TaskDefinition getTaskDefinition(String processDefinitionKey, String taskDefinitionKey) {
        ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
                .latestVersion().singleResult();
        if (processDefinition == null || taskDefinitionKey == null) {
            return null;
        }
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) processEngine.getRepositoryService())
                .getDeployedProcessDefinition(processDefinition.getId());
        Map<String, TaskDefinition> tasks = processDefinitionEntity.getTaskDefinitions();
        return tasks.get(taskDefinitionKey);
    }

    @Override
    public List<Task> getTaskCandidateList(List<String> candidateGroups, String candidateUser) {
        List<Task> tasks = new ArrayList<Task>();
        if (candidateGroups != null && candidateGroups.size() > 0) {
            tasks.addAll(processEngine.getTaskService().createTaskQuery().active().taskCandidateGroupIn(candidateGroups).list());
        }
        if (candidateUser != null && candidateUser.length() > 0) {
            tasks.addAll(processEngine.getTaskService().createTaskQuery().active().taskCandidateUser(candidateUser).list());
        }
        return tasks;
    }

    @Override
    public List<Task> getTaskCandidateList(List<String> candidateGroups, String candidateUser, int pageIndex, int pageSize) {
        List<Task> tasks = new ArrayList<Task>();
        if (candidateGroups != null && candidateGroups.size() > 0) {
            tasks.addAll(processEngine.getTaskService().createTaskQuery().active().taskCandidateGroupIn(candidateGroups).listPage(pageIndex, pageSize));
        }
        if (candidateUser != null && candidateUser.length() > 0) {
            tasks.addAll(processEngine.getTaskService().createTaskQuery().active().taskCandidateUser(candidateUser).listPage(pageIndex, pageSize));
        }
        return tasks;
    }

    @Override
    public void doTaskClaim(String taskId, String userId) {
        processEngine.getTaskService().claim(taskId, userId);
    }

    @Override
    public void doTaskComplete(String taskId, Map<String, Object> variables) {
        processEngine.getTaskService().setVariablesLocal(taskId, variables);
        processEngine.getTaskService().complete(taskId, variables);
    }

    @Override
    public void doTaskVariableUpdate(String taskId, Map<String, Object> variables) {
        processEngine.getTaskService().setVariables(taskId, variables);
    }

    @Override
    public void doBusinessKeyUpdate(String processInstanceId, String businessKey) {
        processEngine.getRuntimeService().updateBusinessKey(processInstanceId, businessKey);
    }

    @Override
    public InputStream getProcessDiagram(String processDefinitionId) {
        ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId)
                .singleResult();
        String resourceName = processDefinition.getDiagramResourceName();
        return processEngine.getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
    }

    @Override
    public InputStream getProcessDiagram(String processInstanceId, Boolean isHighlight) {
        ProcessEngineFactoryBean processEngine1 = (ProcessEngineFactoryBean) processEngine;
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HistoricProcessInstance histProcInst = getHistoricProcessInstance(processInstanceId);
        String processDefinitionId = histProcInst.getProcessDefinitionId();
        // if (!isHighlight) {
        // return getProcessDiagram(processDefinitionId);
        // } else {
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(processDefinitionId);
        bpmnModel.getLabelLocationMap().clear();// 清除lable

        Context.setProcessEngineConfiguration(processEngine1.getProcessEngineConfiguration());

        if (!isHighlight) {
            return processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel, Constants.DEFAULT_DIAGRAM_IMAGE_EXTENSION, new ArrayList<String>(),
                    new ArrayList<String>());
        } else {// 高亮节点和流程线
            List<String> activeActivityIds = processEngine.getRuntimeService().getActiveActivityIds(processInstanceId);
            ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) processEngine.getRepositoryService())
                    .getDeployedProcessDefinition(processDefinitionId);
            List<String> highLightedFlows = getHighLightedFlows(processDefinitionEntity, histProcInst.getId());
            return processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel, Constants.DEFAULT_DIAGRAM_IMAGE_EXTENSION, activeActivityIds, highLightedFlows);
        }
    }

    @Override
    public InputStream getHistoryProcessDiagram(String processInstanceId, String taskId, Boolean isHighlight) {

        ProcessEngineFactoryBean processEngine1 = (ProcessEngineFactoryBean) processEngine;
        HistoricProcessInstance processInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = processInstance.getProcessDefinitionId();
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(processDefinitionId);
        bpmnModel.getLabelLocationMap().clear();
        Context.setProcessEngineConfiguration(processEngine1.getProcessEngineConfiguration());
        if (!isHighlight) {
            return processEngine1.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel, Constants.DEFAULT_DIAGRAM_IMAGE_EXTENSION, new ArrayList<String>(), new ArrayList<String>());
        } else {
            List<String> activeActivityIds = new ArrayList<String>();
            List<String> activTasks = null;
            if (processInstance.getEndTime() == null)
                activTasks = processEngine.getRuntimeService().getActiveActivityIds(processInstanceId);
            if (activTasks != null && activTasks.size() > 0)
                activeActivityIds.addAll(activTasks);
            if (activeActivityIds.size() == 0)
                activeActivityIds.add(taskId);
            ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) processEngine.getRepositoryService()).getDeployedProcessDefinition(processDefinitionId);
            List<String> highLightedFlows = getHighLightedFlows(processDefinitionEntity, processInstance.getId());
            return processEngine1.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel, Constants.DEFAULT_DIAGRAM_IMAGE_EXTENSION, activeActivityIds, highLightedFlows);
        }
    }

    /**
     * 新版本的getHighLightedFlows方法
     *
     * @param processDefinition
     * @param processInstanceId
     * @return
     */
    private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinition, String processInstanceId) {

        List<String> highLightedFlows = new ArrayList<String>();
        List<HistoricActivityInstance> historicActivityInstances = processEngine.getHistoryService().createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                        // 用开始时间升序不准确,直接默认按id升序排是准确的
                        // .orderByHistoricActivityInstanceStartTime().asc()/*.orderByActivityId().asc()*/
                .list();

        LinkedList<HistoricActivityInstance> hisActInstList = new LinkedList<HistoricActivityInstance>();
        hisActInstList.addAll(historicActivityInstances);
        getHighlightedFlows(processDefinition.getActivities(), hisActInstList, highLightedFlows);
        return highLightedFlows;
    }

    /**
     * getHighlightedFlows
     *
     *
     * @param activityList
     * @param hisActInstList
     * @param highLightedFlows
     */
    private void getHighlightedFlows(List<ActivityImpl> activityList, LinkedList<HistoricActivityInstance> hisActInstList, List<String> highLightedFlows) {

        // Activity定义中的startEvent活动
        List<ActivityImpl> startEventActList = new ArrayList<ActivityImpl>();
        Map<String, ActivityImpl> activityMap = new HashMap<String, ActivityImpl>(activityList.size());
        for (ActivityImpl activity : activityList) {

            activityMap.put(activity.getId(), activity);

            String actType = (String) activity.getProperty("type");
            if (actType != null && actType.toLowerCase().indexOf("startevent") >= 0) {
                startEventActList.add(activity);
            }
        }

        // 检查第一个节点是否是startEvent(如果流程是由别的callActivity节启动的则没有startEvent),
        // 如果不是startEvent节点则要找出startEvent的高亮flow
        HistoricActivityInstance firstHistActInst = hisActInstList.getFirst();
        String firstActType = (String) firstHistActInst.getActivityType();
        if (firstActType != null && firstActType.toLowerCase().indexOf("startevent") < 0) {
            PvmTransition startTrans = getStartTransaction(startEventActList, firstHistActInst);
            if (startTrans != null) {
                highLightedFlows.add(startTrans.getId());
            }
        }

        while (hisActInstList.size() > 0) {
            HistoricActivityInstance histActInst = hisActInstList.removeFirst();
            ActivityImpl activity = activityMap.get(histActInst.getActivityId());

            boolean isParallel = false;
            String type = histActInst.getActivityType();
            if ("parallelGateway".equals(type) || "inclusiveGateway".equals(type)) {
                isParallel = true;
            } else if ("subProcess".equals(histActInst.getActivityType())) {
                getHighlightedFlows(activity.getActivities(), hisActInstList, highLightedFlows);
            }

            List<PvmTransition> outgoingTrans = new ArrayList<PvmTransition>();
            outgoingTrans.addAll(activity.getOutgoingTransitions());
            outgoingTrans.addAll(getBoundaryEventOutgoingTransitions(activity));

            List<String> activityHighLightedFlowIds = getHighlightedFlows(outgoingTrans, hisActInstList, isParallel);

            highLightedFlows.addAll(activityHighLightedFlowIds);
        }

    }

    /**
     * 查找startEventActList列表中,谁的outgoing flow连接的是firstActInst
     *
     * @param startEventActList
     * @param firstActInst
     * @return
     */
    private PvmTransition getStartTransaction(List<ActivityImpl> startEventActList, HistoricActivityInstance firstActInst) {
        for (ActivityImpl startEventAct : startEventActList) {
            for (PvmTransition trans : startEventAct.getOutgoingTransitions()) {
                if (trans.getDestination().getId().equals(firstActInst.getActivityId())) {
                    return trans;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param activity
     * @return
     */
    private List<PvmTransition> getBoundaryEventOutgoingTransitions(ActivityImpl activity) {
        List<PvmTransition> boundaryTrans = new ArrayList<PvmTransition>();
        for (ActivityImpl subActivity : activity.getActivities()) {
            String type = (String) subActivity.getProperty("type");
            if (type != null && type.toLowerCase().indexOf("boundary") >= 0) {
                boundaryTrans.addAll(subActivity.getOutgoingTransitions());
            }
        }
        return boundaryTrans;
    }

    /**
     * 获取单个activity的高亮flow
     *
     * @param pvmTransitionList
     * @param hisActInstList
     * @param isParallel
     *            是否只有单一的outgoing(比如说exclusiveGateway, Task上的BoundaryEvent)
     * @return
     */
    private List<String> getHighlightedFlows(List<PvmTransition> pvmTransitionList, LinkedList<HistoricActivityInstance> hisActInstList, boolean isParallel) {

        List<String> highLightedFlowIds = new ArrayList<String>();

        PvmTransition earliestTrans = null;
        HistoricActivityInstance earliestHisActInst = null;

        for (PvmTransition pvmTransition : pvmTransitionList) {
            logger.info("pvmTransition id:{}",pvmTransition.getId());
            if(pvmTransition.getId() == null){
                continue;
            }
            String destActId = pvmTransition.getDestination().getId();
            HistoricActivityInstance destHisActInst = findHisActInst(hisActInstList, destActId);
            if (destHisActInst != null) {

                if (isParallel) {
                    highLightedFlowIds.add(pvmTransition.getId());
                } else {
                    if (earliestHisActInst == null || (earliestHisActInst.getId().compareTo(destHisActInst.getId()) > 0)) {// 用开始时间比较不准确,直接默认按id比较是准确的
                        earliestTrans = pvmTransition;
                        earliestHisActInst = destHisActInst;
                    }
                }
            }
        }

        if ((!isParallel) && earliestTrans != null) {
            highLightedFlowIds.add(earliestTrans.getId());
        }

        return highLightedFlowIds;
    }

    private HistoricActivityInstance findHisActInst(LinkedList<HistoricActivityInstance> hisActInstList, String actId) {
        for (HistoricActivityInstance hisActInst : hisActInstList) {
            if (hisActInst.getActivityId().equals(actId)) {
                return hisActInst;
            }
        }
        return null;
    }

    @Override
    public String getBusinessKey(String processInstanceId) {
        ProcessInstance instance = this.getProcessInstance(processInstanceId);
        if (instance == null) {
            return null;
        }
        return instance.getBusinessKey();
    }

    @Override
    public String getBusinessKeyWithTaskId(String taskId) {
        Task task = getTaskById(taskId);
        if (task == null) {
            return null;
        }
        return this.getBusinessKey(task.getProcessInstanceId());
    }

    @Override
    public String getFormKey(String taskId) {
        TaskFormData taskFormData = processEngine.getFormService().getTaskFormData(taskId);
        if (taskFormData == null) {
            return null;
        }
        return taskFormData.getFormKey();
    }

    @Override
    public ProcessInstance getProcessInstance(String processInstanceId) {
        return processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public String getProcessInstanceStartUserId(String processInstanceId) {
        if (processInstanceId == null) {
            return null;
        }
        HistoricProcessInstance hpi = this.processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
                .singleResult();
        if (hpi == null) {
            return null;
        }
        return hpi.getStartUserId();
    }

    @Override
    public List<Task> getTaskByProcessDefinedId(String processDefinitionId) {
        return processEngine.getTaskService().createTaskQuery().processDefinitionId(processDefinitionId).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBusinessEntity(String processDefinitionKey, String businessKey) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = this.getTaskWithBusinessKey(processDefinitionKey, businessKey);
        for (Task task : list) {
            if (taskService.hasVariable(task.getId(), Constants.BPM.VAR_ENTITY)) {
                return (T) taskService.getVariable(task.getId(), Constants.BPM.VAR_ENTITY);
            }
        }
        return null;
    }

    @Override
    public List<HistoricTaskInstance> getTaskHistory(String processInstanceId) {
        return processEngine.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).finished().orderByHistoricTaskInstanceStartTime().asc().list();
    }
}
