package com.example.TibiaTools.recyclerview;

public class ItemsCharacters {
    private String name;
    private String world;
    private String status;
    private Boolean deleted;
    private Boolean main;
    private Boolean traded;

    public ItemsCharacters(
            String name,
            String world,
            String status,
            Boolean deleted,
            Boolean main,
            Boolean traded
    ){
        this.name = name;
        this.world = world;
        this.status = status;
        this.deleted = deleted;
        this.main = main;
        this.traded = traded;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getTraded() {
        return traded;
    }

    public void setTraded(Boolean traded) {
        this.traded = traded;
    }
}
