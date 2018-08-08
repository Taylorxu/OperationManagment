package com.wisesignsoft.OperationManagement;


import com.wisesignsoft.OperationManagement.db.MySharedpreferences;

/**
 * Created by ycs on 2016/11/21.
 */

public class Protocol {
    private static final String test_url = getHostUrl();
    //测试
//    private static final String test_url = "http://123.57.26.84:9180/IMS/";
//    private static final String test_url = "http://124.205.209.4:9180/IMS/";

    /*获取url*/
    public static String getHostUrl() {
        return MySharedpreferences.getServerString();
    }

    /*===================================user====================================*/
    /*user服务器*/
    public static final String user_host = "services/UserService?wsdl";
    /*user命名空间*/
    public static final String user_name_space = "http://user.extinterface.web.wisesign.cn/";

    /*获取完整的url地址*/
    public static String getUserHostUrl() {
        return getHostUrl() + user_host;
    }

    /*修改人员位置*/
    public static final String updateUserPos = "updateUserPos";
    /*修改状态*/
    public static final String updateUserSta = "updateUserSta";

    /*===================================yxyw====================================*/
    /*yxyw服务器*/
    public static final String yxyw_host = "services/YxywService?wsdl";
    /*yxyw命名空间*/
    public static final String yxyw_name_space = "http://yxyw.extinterface.web.wisesign.cn/";

    /*获取完整的yxyw地址*/
    public static String getYxywHostUrl() {
        return getHostUrl() + yxyw_host;
    }

    /*登录*/
    public static final String login = "mobileLogin";
    /*获取上送位置的时间间隔*/
    public static final String queryUserPositionInterval = "queryUserPositionInterval";
    /*获取用户权限资源*/
    public static final String queryUserResource = "queryUserResource";
    /*查询未处理的订单*/
    public static final String QueryUnhandleProcessCount = "QueryUnhandleProcessCount";
    /*查看已完成事件*/
    public static final String findEndedProcess = "findEndedProcess";
    /*查看进行中事件*/
    public static final String findHandledProcess = "findHandledProcess";
    /*查询我的待办事件*/
    public static final String findUnhandleProcess = "findUnhandleProcess";
    /*查询所有有效人员*/
    public static final String queryAllValidUsers = "queryAllValidUsers";
    /*台账数据查询*/
    public static final String queryData = "queryData";
    /*条件查询人员列表*/
    public static final String queryValidUsersByUserName = "queryValidUsersByUserName";
    /*根据人员账号获取人员详情*/
    public static final String queryValidUsersByAccount = "queryValidUsersByAccount";
    /*根据人员ids获取人员详情*/
    public static final String queryValidUsersByIds = "queryValidUsersByIds";
    /*根据操作人id以条件排序查询其所在部门的所有人员*/
    public static final String queryValidUserBydept = "queryValidUserBydept";
    /*===================================process====================================*/
    /*process服务器*/
    private static final String process_host = "services/ProcessService?wsdl";
    /*process命名空间*/
    public static final String process_name_space = "http://process.extinterface.web.wisesign.cn/";

    /*获取完整的process地址*/
    public static String getProcessHostUrl() {
        return getHostUrl() + process_host;
    }

    /*新建工单*/
    public static final String createProcessByKeyAndCreator = "createProcessByKeyAndCreator";
    /*查询可创建的工单类型*/
    public static final String findCanCreateProcess = "findCanCreateProcess";
    /*查看工单处理过程*/
    public static final String findWonhInfo = "findWonhInfo";
    /*打开待办工单*/
    public static final String openTaskDetail = "openTaskDetail";
    /*不保存工单*/
    public static final String deleteProcessInfoOnFirst = "deleteProcessInfoOnFirst";
    /*保存工单*/
    public static final String saveProcessSketch = "saveProcessSketch";
    /*创建模板*/
    public static final String crateTemplet = "crateTemplet";
    //    /*获取我的模板列表*/
//    public static final String getWoTempletListByUserAccount = "getWoTempletListByUserAccount";
    /*获取我的模板列表-这个是有权限设置的*/
    public static final String getWoTempletListByUserAccount = "getTempletsByKeys";
    /*打开模板*/
    public static final String openTeplet = "openTeplet";
    /*查看工单详情*/
    public static final String findWorkOrderDetailByPiKey = "findWorkOrderDetailByPiKey";
    /*通过模板创建工单*/
    public static final String createProcessByTemplet = "createProcessByTemplet";
    /*提交工单*/
    public static final String submitTask = "submitTask";
    /*新增模板*/
    public static final String saveTeplet = "saveTeplet";

    /*=======================================dict=============================================*/
    /*dict服务器*/
    private static final String dict_host = "services/DictService?wsdl";
    /*dict命名空间*/
    public static final String dict_name_space = "http://dict.extinterface.web.wisesign.cn/";

    /*获取完整的dict地址*/
    public static String getDictHostUrl() {
        return getHostUrl() + dict_host;
    }

    /*获取单个字典有效数据*/
    public static final String queryValidCiByModelName = "queryValidCiByModelName";
    /*获取所有字典有效数据*/
    public static final String queryAllValidDictDate = "queryAllValidDictDate";
    /*=========================================know============================================*/
    /*know服务器*/
    private static final String know_host = "services/KnowService?wsdl";
    /*know命名空间*/
    public static final String know_name_space = "http://knowledge.extinterface.web.wisesign.cn/";

    /*获取完整的know地址*/
    public static String getKnowHostUrl() {
        return getHostUrl() + know_host;
    }

    /*查询知识库列表*/
    public static final String findKnowledgeList = "findKnowledgeList";
    /*查看知识详情*/
    public static final String openKnowDetail = "openKnowDetail";
    /*=========================================ci==========================================*/
    /*ci服务器*/
    private static final String ci_host = "services/CiService?wsdl";
    /*ci命名空间*/
    public static final String ci_name_space = "http://ci.extinterface.web.wisesign.cn/";

    /*获取完整的ci地址*/
    public static String getCiHostUrl() {
        return getHostUrl() + ci_host;
    }

    /*查询知识库列表*/
    public static final String queryCiModel = "queryCiModel";
    /*业务模型查询*/
    public static final String queryModelByBmModelName = "queryModelByBmModelName";
    /*台账详情查询*/
    public static final String queryCiDetail = "queryCiDetail";
    /*创建一个台账*/
    public static final String createCi = "createCi";
    /*添加一个配置项*/
    public static final String addCi = "addCi";
    //作废一个台账
    public static final String cancelCi = "cancelCi";
    //修改某个台账保存
    public static final String updateCi = "updateCi";
    //修改界面数据查询
    public static final String updateDetail = "queryCiDetailForUpdate";
    //查询 登录人是否有新增台账权限
    public static final String queryUserAuth = "queryUserAuth";
    //查询登录人 是否有单挑台账 修改和作废
    public static final String queryUserAuthById = "queryUserAuthById";
    /*====================================message===========================================*/
    /*message服务器*/
    private static final String message_host = "services/MessageService?wsdl";
    /*message命名空间*/
    public static final String message_name_space = "http://msg.extinterface.web.wisesign.cn/";

    /*获取完整的message地址*/
    public static String getMessageHostUrl() {
        return getHostUrl() + message_host;
    }

    /*查询未读消息列表*/
    public static final String findUnReadedMsg = "findUnReadedMsg";
    /*查询未读消息数量*/
    public static final String findUnReadedMsgCount = "findUnReadedMsgCount";
    /*标记消息为已读*/
    public static final String setMsgReaded = "setMsgReaded";
    /*====================================role============================================*/
    /*role服务器*/
    private static final String role_host = "services/RoleService?wsdl";
    /*role命名空间*/
    public static final String role_name_space = "http://role.extinterface.web.wisesign.cn/";

    /*获取完整的role地址*/
    public static String getRoleHostUrl() {
        return getHostUrl() + role_host;
    }

    /*查询角色列表*/
    public static final String findRoleByGroupId = "findRoleByGroupId";
    /*根据角色ids查询角色*/
    public static final String findRoleByIds = "findRoleByIds";
    /*查询角色下人员*/
    public static final String findUserByRoleId = "findUserByRoleId";
    /*====================================dept============================================*/
    /*dept服务器*/
    private static final String dept_host = "services/DeptService?wsdl";
    /*dept命名空间*/
    public static final String dept_name_space = "http://dept.extinterface.web.wisesign.cn/";

    /*获取完整的dept地址*/
    public static String getDeptHostUrl() {
        return getHostUrl() + dept_host;
    }

    /*查询所有组织机构以树形返回*/
    public static final String findAllDeptTree = "findAllDeptTree";
    /*查询所有组织机构*/
    public static final String findAllDept = "findAllDept";
    /*查询组织下人员*/
    public static final String findUserByDeptId = "findUserByDeptId";
    /*根据ids获取部门信息*/
    public static final String findDeptByIds = "findDeptByIds";
    /*====================================attachment============================================*/
    /*attachment服务器*/
    private static final String attachment_host = "services/AttachmentService?wsdl";
    /*attachment命名空间*/
    public static final String attachment_name_space = "http://attachment.extinterface.web.wisesign.cn/";

    /*获取完整的attachment地址*/
    public static String getAttachmentHostUrl() {
        return getHostUrl() + attachment_host;
    }

    /*附件上传接口*/
    public static final String uploadFile = "uploadFile";
    /*根据附件ids查询附件信息*/
    public static final String queryAttchmentByIds = "queryAttchmentByIds";
    /*附件下载的方法*/
    private static final String attachMentDownloadToMove = "AttachMentDownloadToMove";
    /*根据附件ids查询附件信息*/
    public static final String findAttachmentInfo = "findAttachmentInfo";
    /*根据附件ids查询附件预览地址*/
    public static final String previewOnline = "previewOnline";

    /*下载附件的路径*/
    public static String getAttachmentLoadUrl() {
        return getHostUrl() + attachMentDownloadToMove + "?";
    }

    /*====================================business============================================*/
    /*business服务器*/
    private static final String business_host = "services/BusinessService?wsdl";
    /*business命名空间*/
    public static final String business_name_space = "http://cmdb.extinterface.web.wisesign.cn/";

    /*获取完整的business地址*/
    public static String getBusinessHostUrl() {
        return getHostUrl() + business_host;
    }

    /*调用联动接口*/
    public static final String invokeDataLinkageMethod = "invokeDataLinkageMethod";

    /*====================================岗位组件============================================*/

    private static final String postService_host = "services/PostService?wsdl";

    public static final String post_name_space = "http://post.extinterface.web.wisesign.cn/";

    public static String getPostServiceHostUrl() {
        return getHostUrl() + postService_host;
    }

    public static String queryPostModelList = "queryPostModel";
}
