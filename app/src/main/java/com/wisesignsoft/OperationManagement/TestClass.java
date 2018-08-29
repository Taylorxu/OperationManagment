package com.wisesignsoft.OperationManagement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class TestClass {
    static String jsonString = "[{'expreDesc':'[ { 'dataLinkCondition':'OBJ_DOA=1', 'results': [ { 'dataLinkControlType':'5','resultAttr':'OBJ_DOACPXH','resultValue':'true'}, { 'dataLinkControlType':'5','resultAttr':'OBJ_DOACPDM','resultValue':'true'} ] }, { 'dataLinkCondition':'OBJ_DOA=2', 'results': [ { 'dataLinkControlType':'5','resultAttr':'OBJ_DOACPXH','resultValue':'false'}, { 'dataLinkControlType':'5','resultAttr':'OBJ_DOACPDM','resultValue':'false'} ] }]','mx_internal_uid':'15226469-CFA6-BC60-88E8-1F16ACF3BC31','methodName':'commonDataLinkage','ID':'radiobuttons_23','methodDesc':'通用联动方法','nodeType':'RadioButtons','name':'是否DOA'},{'expreDesc':'[ { 'obj_Srt':'OBJ_SRT', 'obj_Vip':'OBJ_SYSFVIP' }] ','mx_internal_uid':'C49ED01B-F93B-147D-DE14-6D4BC3BF0122','methodName':'objectNoContentCuty','ID':'resmodelselect_1','methodDesc':'事件流程-事件基本信息客户编号联动客户类别','nodeType':'ResModelSelect','name':'项目选择'},{'expreDesc':'[ { 'dataLinkCondition':'OBJ_NSECH=1 and OBJ_OBJSTA=7', 'results': [ { 'dataLinkControlType':'3','resultAttr':'OBJ_EXPJ','resultValue':'true'}, { 'dataLinkControlType':'5','resultAttr':'OBJ_EXPJ','resultValue':'true'} ] }, { 'dataLinkCondition':'OBJ_NSECH=2', 'results': [ { 'dataLinkControlType':'3','resultAttr':'OBJ_EXPJ','resultValue':'false'}, { 'dataLinkControlType':'5','resultAttr':'OBJ_EXPJ','resultValue':'false'} ] }]','mx_internal_uid':'B222415C-75D8-421D-CDCB-B218C573E2AA','methodName':'commonDataLinkage','ID':'radiobuttons_2','methodDesc':'通用联动方法','nodeType':'RadioButtons','name':'是否需要二线协助'},{'expreDesc':'[ { 'customerDmAttrName':'OBJ_SYSFVIP', 'systemDmAttrName':'OBJ_SYXTJB', 'useDmAttrName':'OBJ_SYYYYX', 'conServerDmAttrName':'OBJ_SCA', 'levelGradeDmAttrName':'OBJ_SLEVER', 'solveTimeLimitDmAttrName':'OBJ_JFT', 'getTimeDmAttrName':'OBJ_ATST', 'objPriority':'OBJ_YXJA' }] ','mx_internal_uid':'6F9123C3-4EDC-CF31-3852-5BB6E4CEE620','methodName':'thingLinkLevelGrade','ID':'combobox_29','methodDesc':'事件流程首页 联动服务等级三个字段','nodeType':'ComboBox','name':'应用影响'},{'expreDesc':'[ { 'customerDmAttrName':'OBJ_SYSFVIP', 'systemDmAttrName':'OBJ_SYXTJB', 'useDmAttrName':'OBJ_SYYYYX', 'conServerDmAttrName':'OBJ_SCA', 'levelGradeDmAttrName':'OBJ_SLEVER', 'solveTimeLimitDmAttrName':'OBJ_JFT', 'getTimeDmAttrName':'OBJ_ATST', 'objPriority':'OBJ_YXJA' }] ','mx_internal_uid':'6B7C3F63-DDF6-5807-D7C8-5BB70AE864CD','methodName':'thingLinkLevelGrade','ID':'combobox_28','methodDesc':'事件流程首页 联动服务等级三个字段','nodeType':'ComboBox','name':'系统级别'},{'expreDesc':'[ { 'customerDmAttrName':'OBJ_SYSFVIP', 'systemDmAttrName':'OBJ_SYXTJB', 'useDmAttrName':'OBJ_SYYYYX', 'conServerDmAttrName':'OBJ_SCA', 'levelGradeDmAttrName':'OBJ_SLEVER', 'solveTimeLimitDmAttrName':'OBJ_JFT', 'getTimeDmAttrName':'OBJ_ATST', 'objPriority':'OBJ_YXJA' }] ','mx_internal_uid':'BD33B9CB-3090-3B15-5085-5BB70B476387','methodName':'thingLinkLevelGrade','ID':'combobox_27','methodDesc':'事件流程首页 联动服务等级三个字段','nodeType':'ComboBox','name':'客户类别'},{'expreDesc':'[ { 'dataLinkCondition':'OBJ_JFT=1小时', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTB','resultValue':'60'} ] }, { 'dataLinkCondition':'OBJ_JFT=2小时', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTB','resultValue':'120'} ] }, { 'dataLinkCondition':'OBJ_JFT=4小时', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTB','resultValue':'240'} ] },  { 'dataLinkCondition':'OBJ_JFT=6小时', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTB','resultValue':'360'} ] },  { 'dataLinkCondition':'OBJ_JFT=8小时', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTB','resultValue':'480'} ] },  { 'dataLinkCondition':'OBJ_SLEVER=S1 and OBJ_SFZB=0', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'720'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'false'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_SFFQWTLC','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'1'} ] }, { 'dataLinkCondition':'OBJ_SLEVER=S1 and OBJ_SFZB=1', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'720'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_START','resultValue':'1'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_SFFQWTLC','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'1'} ] }, { 'dataLinkCondition':'OBJ_SLEVER=S2 and OBJ_SFZB=0', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'1440'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'false'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_SFFQWTLC','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'1'} ] }, { 'dataLinkCondition':'OBJ_SLEVER=S2 and OBJ_SFZB=1', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'1440'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_START','resultValue':'1'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_SFFQWTLC','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'1'} ] }, { 'dataLinkCondition':'OBJ_SLEVER=S3 and OBJ_SFZB=0', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'1440'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'false'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_SFFQWTLC','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'1'} ] },  { 'dataLinkCondition':'OBJ_SLEVER=S3 and OBJ_SFZB=1', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'1440'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_START','resultValue':'1'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_SFFQWTLC','resultValue':'false'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'1'} ] },  { 'dataLinkCondition':'OBJ_SLEVER=S4', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'4320'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'true'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'2'} ] },  { 'dataLinkCondition':'OBJ_SLEVER=S5', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'4320'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'true'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'2'} ] },  { 'dataLinkCondition':'OBJ_SLEVER=S6', 'results': [ { 'dataLinkControlType':'1','resultAttr':'TESTC','resultValue':'8640'}, { 'dataLinkControlType':'3','resultAttr':'OBJ_START','resultValue':'true'}, { 'dataLinkControlType':'1','resultAttr':'OBJ_SFFQWTLC','resultValue':'2'} ] }]','mx_internal_uid':'5808CC89-F767-EC3D-2793-9F344CCEA911','methodName':'commonDataLinkage','ID':'textinput_32','methodDesc':'通用联动方法','nodeType':'TextInput','name':'服务等级'},{'expreDesc':'[ { 'resSrTableAttrName':'OBJ_SRT', 'subStrListAttrName':'REL_REQUIRMENT_CONTACT' }]','mx_internal_uid':'E0B5A6B5-86B5-E206-636D-5E2075405C93','methodName':'LinkCustomerAddress','ID':'resmodelselect_1','methodDesc':'项目选择联动客户联系人','nodeType':'ResModelSelect','name':'项目选择'},{'expreDesc':'[ { 'groupInsi':'OBJ_FIRP', 'regionInsi':'OBJ_ENBLO', 'curTaskName':'CURRENT_TASKNAME', 'taskName':'信息填写' }] ','mx_internal_uid':'20C06798-9C33-52E6-6ABD-690A811431FF','methodName':'personnelAreaInsi','ID':'singleuserchoose_4','methodDesc':'创建人-工作量所属区域','nodeType':'SingleUserChoose','name':'一线工程师'}]";

    public static void main(String arg[]) {

        Gson gson = new Gson();
        List links = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
        }.getType());
        System.out.print(links);
    }
}
