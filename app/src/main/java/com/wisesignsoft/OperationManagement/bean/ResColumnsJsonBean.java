package com.wisesignsoft.OperationManagement.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ycs on 2016/12/28.
 */

public class ResColumnsJsonBean implements Parcelable {

    /**
     * headerText : 配置项编号
     * width : 200
     * ID : textinput_1
     * requiredObj : {"value":0,"name":"否"}
     * dataFieldObj : {"value":"CI_CODE","type":"TextInput","name":"CI_CODE"}
     */

    private String headerText;
    private int width;
    private String ID;
    /**
     * value : 0
     * name : 否
     */

    private RequiredObjBean requiredObj;
    /**
     * value : CI_CODE
     * type : TextInput
     * name : CI_CODE
     */

    private DataFieldObjBean dataFieldObj;

    protected ResColumnsJsonBean(Parcel in) {
        headerText = in.readString();
        width = in.readInt();
        ID = in.readString();
        requiredObj = in.readParcelable(RequiredObjBean.class.getClassLoader());
        dataFieldObj = in.readParcelable(DataFieldObjBean.class.getClassLoader());
    }

    public static final Creator<ResColumnsJsonBean> CREATOR = new Creator<ResColumnsJsonBean>() {
        @Override
        public ResColumnsJsonBean createFromParcel(Parcel in) {
            return new ResColumnsJsonBean(in);
        }

        @Override
        public ResColumnsJsonBean[] newArray(int size) {
            return new ResColumnsJsonBean[size];
        }
    };

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public RequiredObjBean getRequiredObj() {
        return requiredObj;
    }

    public void setRequiredObj(RequiredObjBean requiredObj) {
        this.requiredObj = requiredObj;
    }

    public DataFieldObjBean getDataFieldObj() {
        return dataFieldObj;
    }

    public void setDataFieldObj(DataFieldObjBean dataFieldObj) {
        this.dataFieldObj = dataFieldObj;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(headerText);
        dest.writeInt(width);
        dest.writeString(ID);
        dest.writeParcelable(requiredObj,flags);
        dest.writeParcelable(dataFieldObj,flags);
    }

    public static class RequiredObjBean implements Parcelable {
        private int value;
        private String name;

        protected RequiredObjBean(Parcel in) {
            value = in.readInt();
            name = in.readString();
        }

        public static final Creator<RequiredObjBean> CREATOR = new Creator<RequiredObjBean>() {
            @Override
            public RequiredObjBean createFromParcel(Parcel in) {
                return new RequiredObjBean(in);
            }

            @Override
            public RequiredObjBean[] newArray(int size) {
                return new RequiredObjBean[size];
            }
        };

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(value);
            dest.writeString(name);
        }
    }

    public static class DataFieldObjBean implements Parcelable {
        private String value;
        private String type;
        private String name;

        protected DataFieldObjBean(Parcel in) {
            value = in.readString();
            type = in.readString();
            name = in.readString();
        }

        public static final Creator<DataFieldObjBean> CREATOR = new Creator<DataFieldObjBean>() {
            @Override
            public DataFieldObjBean createFromParcel(Parcel in) {
                return new DataFieldObjBean(in);
            }

            @Override
            public DataFieldObjBean[] newArray(int size) {
                return new DataFieldObjBean[size];
            }
        };

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(value);
            dest.writeString(type);
            dest.writeString(name);
        }
    }
}
