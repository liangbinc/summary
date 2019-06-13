package com.lbc.mo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupUsers {
    @JsonProperty("status")
    private String status;
    @JsonProperty("users")
    private List<User> users;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class User {
        @JsonProperty("id")
        private String id;
        @JsonIgnore
        private String isDefaultGroup;
        @JsonProperty("name")
        private String name;

        private String createTime;

        private boolean isChangeQuota = true;

        public boolean isChangeQuota() {
            return isChangeQuota;
        }

        public void setChangeQuota(boolean changeQuota) {
            isChangeQuota = changeQuota;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDefaultGroup() {
            return isDefaultGroup;
        }

        public void setIsDefaultGroup(String isDefaultGroup) {
            this.isDefaultGroup = isDefaultGroup;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
