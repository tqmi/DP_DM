package com.dpdm.storage_service;

public class MyFile{

    private String fileBlobId;
    private String UID;
    
    public String getFileBlobId() {
        return fileBlobId;
    }
    public void setFileBlobId(String fileBlobId) {
        this.fileBlobId = fileBlobId;
    }
    public MyFile(String fileBlobId,String UID) {
        this.fileBlobId = fileBlobId;
        this.UID = UID;
    }
    public MyFile(){

    }
    public String getUID() {
        return UID;
    }
    public void setUID(String uID) {
        UID = uID;
    }
    @Override
    public String toString() {
        return "MyFile [UID=" + UID + ", fileBlobId=" + fileBlobId + "]";
    }

    
    
}
