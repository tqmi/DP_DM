package com.dpdm.gatewayservice.models;

import com.dpdm.gateway_api.model.MyUser;

public class InternalUser {

    private MyUser user;
    private String uid;
    
    public MyUser getUser() {
        return user;
    }
    public InternalUser(MyUser user, String uid) {
        this.user = user;
        this.uid = uid;
    }
    public void setUser(MyUser user) {
        this.user = user;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    
}
