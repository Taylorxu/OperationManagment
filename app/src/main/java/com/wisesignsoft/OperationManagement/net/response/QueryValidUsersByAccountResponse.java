package com.wisesignsoft.OperationManagement.net.response;

/**
 * Created by ycs on 2016/12/30.
 */

public class QueryValidUsersByAccountResponse extends BaseResponse {

    /**
     * userName : 人员名称
     * userJob : 人员职位
     * userDept : 部门
     * userMove : 人员电话
     * userEmail : 人员邮箱
     * userGender : 人员性别1为男，0为女
     * userPosition : 人员位置
     * userState : 人员状态
     */

    private ReturnValueBean returnValue;

    public ReturnValueBean getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValueBean returnValue) {
        this.returnValue = returnValue;
    }

    public static class ReturnValueBean {
        private String userName;
        private String userJob;
        private String userDept;
        private String userMove;
        private String userEmail;
        private String userGender;
        private String userPosition;
        private String userState;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserJob() {
            return userJob;
        }

        public void setUserJob(String userJob) {
            this.userJob = userJob;
        }

        public String getUserDept() {
            return userDept;
        }

        public void setUserDept(String userDept) {
            this.userDept = userDept;
        }

        public String getUserMove() {
            return userMove;
        }

        public void setUserMove(String userMove) {
            this.userMove = userMove;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserGender() {
            return userGender;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public String getUserPosition() {
            return userPosition;
        }

        public void setUserPosition(String userPosition) {
            this.userPosition = userPosition;
        }

        public String getUserState() {
            return userState;
        }

        public void setUserState(String userState) {
            this.userState = userState;
        }
    }
}
