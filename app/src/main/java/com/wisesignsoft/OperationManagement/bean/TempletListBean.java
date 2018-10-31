package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

/**
 * 我的模板model
 */
public class TempletListBean {
    private String total;
    private List<DataBean> data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String formData;
        private String id;
        private String nodeName;
        private String pikey;
        private String processKey;
        private String processKeyDes;
        private String remark;
        private SaveTimeBean saveTime;
        private boolean selected;
        private String sketchTitle;
        private String taskId;
        private String userAccount;
        private String userName;
        private String userType;

        public String getFormData() {
            return formData;
        }

        public void setFormData(String formData) {
            this.formData = formData;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getPikey() {
            return pikey;
        }

        public void setPikey(String pikey) {
            this.pikey = pikey;
        }

        public String getProcessKey() {
            return processKey;
        }

        public void setProcessKey(String processKey) {
            this.processKey = processKey;
        }

        public String getProcessKeyDes() {
            return processKeyDes;
        }

        public void setProcessKeyDes(String processKeyDes) {
            this.processKeyDes = processKeyDes;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public SaveTimeBean getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(SaveTimeBean saveTime) {
            this.saveTime = saveTime;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getSketchTitle() {
            return sketchTitle;
        }

        public void setSketchTitle(String sketchTitle) {
            this.sketchTitle = sketchTitle;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public static class SaveTimeBean {
            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
