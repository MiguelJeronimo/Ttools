package com.example.ttools.recyclerview;

public class ItemsRecyclerViewGuilds {
    private String lbName;
    private String lbLogoUrl;
    private String lbDescripcion;
    public ItemsRecyclerViewGuilds(String lbName,String lbLogoUrl, String lbDescripcion){
        this.lbDescripcion=lbDescripcion;
        this.lbLogoUrl=lbLogoUrl;
        this.lbName=lbName;
    }

    public String getLbName() {
        return lbName;
    }

    public void setLbName(String lbName) {
        this.lbName = lbName;
    }

    public String getLbLogoUrl() {
        return lbLogoUrl;
    }

    public void setLbLogoUrl(String lbLogoUrl) {
        this.lbLogoUrl = lbLogoUrl;
    }

    public String getLbDescripcion() {
        return lbDescripcion;
    }

    public void setLbDescripcion(String lbDescripcion) {
        this.lbDescripcion = lbDescripcion;
    }
}
