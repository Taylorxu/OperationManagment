package com.wisesignsoft.OperationManagement.net.response;

import java.util.List;

/**
 * Created by ycs on 2016/11/23.
 */

public class QueryUserResourceResponse extends BaseResponse {

    /**
     * resName : 资源名称
     * resUrl : 资源URl
     */

    private List<ResourcesBean> resources;

    public List<ResourcesBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesBean> resources) {
        this.resources = resources;
    }

    public static class ResourcesBean {
        private String resName;
        private String resUrl;
        private String resPar;

        public String getResPar() {
            return resPar;
        }

        public void setResPar(String resPar) {
            this.resPar = resPar;
        }

        public String getResName() {
            return resName;
        }

        public void setResName(String resName) {
            this.resName = resName;
        }

        public String getResUrl() {
            return resUrl;
        }

        public void setResUrl(String resUrl) {
            this.resUrl = resUrl;
        }
    }
}
