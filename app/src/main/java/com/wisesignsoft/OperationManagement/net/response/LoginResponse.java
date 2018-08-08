package com.wisesignsoft.OperationManagement.net.response;

/**
 * Created by ycs on 2016/11/23.
 */

public class LoginResponse extends BaseResponse {
    /**
     * total : 25
     * userId : 2967e352-8749-4c3a-b377-008ae1f0395d
     */

    private ReturnValueBean returnValue;

    public ReturnValueBean getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValueBean returnValue) {
        this.returnValue = returnValue;
    }

    public static class ReturnValueBean {
        private int total;
        private String userId;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
