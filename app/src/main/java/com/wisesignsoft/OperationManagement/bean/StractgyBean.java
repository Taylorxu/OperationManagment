package com.wisesignsoft.OperationManagement.bean;

/**
 * "nextNode":[
 * {
 * "@name":"任务1--任务",
 * "@to":"服务登记",
 * "@isDefaultPath":"true",
 * "@taskStrategy":"{'strategyKey':'originalUser','strategyValue':'服务登记'}",
 * "@specificValueUpdate":"{'values':[{'formItemValue':{'value':'SER_APP:1a8a9c39-0226-4bdc-9792-60a7a48caa49','name':'新提交'},'formItem':{'isCanAdd':true,'parentDeptJSON':null,'precision':null,'value_label':null,'isCanUpdate':true,'isFilterUser':false,'sectionId':'Section0','id':'combobox_2','isExecute':false,'seeHisWorkOrderInfo':false,'name':'流程状态','configModifiedField':null,'dmAttrDisplayName':'流程状态','allColumnsJson':null,'isCanDelete':true,'modelName':'SER_APP','value':'','ID':'combobox_2','isMult':false,'bmModelName':'SER','modified':false,'maxChars':null,'srclib':'SER_APP','dmAttrName':'PROCESS_STATE','dataProvider':null,'required':false,'nodeType':'ComboBox','srclib_label':'服务请求','isVisible':true}}]}",
 * "@nameDesc":"退回",
 * "@isDependCondition":"false"
 * },
 * {
 * "@name":"任务1--任务2",
 * "@to":"二线处理",
 * "@isDefaultPath":"true",
 * "@taskStrategy":"{'strategyKey':'assignee','strategyValue':'{\'roleId\':\'ceacdbda-e893-45a3-96ae-d46a5e72c185\',\'roleName\':\'二线人员\'}'}",
 * "@specificValueUpdate":"{'values':[{'formItemValue':{'value':'SER_APP:3ea4da04-7e1e-430f-956c-a1ee592ed7fb','name':'二线处理'},'formItem':{'isCanAdd':true,'parentDeptJSON':null,'precision':null,'value_label':null,'isCanUpdate':true,'isFilterUser':false,'sectionId':'Section0','id':'combobox_2','isExecute':false,'seeHisWorkOrderInfo':false,'name':'流程状态','configModifiedField':null,'dmAttrDisplayName':'流程状态','allColumnsJson':null,'isCanDelete':true,'modelName':'SER_APP','value':'','ID':'combobox_2','isMult':false,'bmModelName':'SER','modified':false,'maxChars':null,'srclib':'SER_APP','dmAttrName':'PROCESS_STATE','dataProvider':null,'required':false,'nodeType':'ComboBox','srclib_label':'服务请求','isVisible':true}}]}",
 * "@nameDesc":"提交二线",
 * "@isDependCondition":"false"
 * },
 * {
 * "@name":"任务1--任务3",
 * "@to":"确认回顾",
 * "@isDefaultPath":"true",
 * "@taskStrategy":"{'strategyKey':'creator','strategyValue':''}",
 * "@specificValueUpdate":"{'values':[{'formItemValue':{'value':'SER_APP:ca2e4b1f-20a8-44f5-96ea-25a81d1cbf42','name':'确认回顾'},'formItem':{'isCanAdd':true,'parentDeptJSON':null,'precision':null,'value_label':null,'isCanUpdate':true,'isFilterUser':false,'sectionId':'Section0','id':'combobox_2','isExecute':false,'seeHisWorkOrderInfo':false,'name':'流程状态','configModifiedField':null,'dmAttrDisplayName':'流程状态','allColumnsJson':null,'isCanDelete':true,'modelName':'SER_APP','value':'','ID':'combobox_2','isMult':false,'bmModelName':'SER','modified':false,'maxChars':null,'srclib':'SER_APP','dmAttrName':'PROCESS_STATE','dataProvider':null,'required':false,'nodeType':'ComboBox','srclib_label':'服务请求','isVisible':true}}]}",
 * "@nameDesc":"提交回顾",
 * "@isDependCondition":"false"
 * }
 */

public class StractgyBean {
    /**
     * strategyValue 的值存在三种情况
     * 1 是以{@link StrategyValueBean} 为内容的 json 字符串;
     * 2 单纯的文本字符串
     * 3 空字符串
     */
    private String strategyValue;
    private String strategyKey;

    public String getStrategyValue() {
        return strategyValue;
    }

    public void setStrategyValue(String strategyValue) {
        this.strategyValue = strategyValue;
    }

    public String getStrategyKey() {
        return strategyKey;
    }

    public void setStrategyKey(String strategyKey) {
        this.strategyKey = strategyKey;
    }

    public static class StrategyValueBean {
        private String roleId;
        private String roleName;

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
