package com.github.whujack.config;

/**
 * @author Created By LiJie at 2018/03/14
 *
 */
public class TableConfiguration {
    private String name;
    private Boolean enableSelect;
    private Boolean enableUpdate;
    private Boolean enableDelete;
    private Boolean enableInsert;

    public String getName() {
        return name;
    }

    public TableConfiguration setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getEnableSelect() {
        return enableSelect;
    }

    public TableConfiguration setEnableSelect(Boolean enableSelect) {
        this.enableSelect = enableSelect;
        return this;
    }

    public Boolean getEnableUpdate() {
        return enableUpdate;
    }

    public TableConfiguration setEnableUpdate(Boolean enableUpdate) {
        this.enableUpdate = enableUpdate;
        return this;
    }

    public Boolean getEnableDelete() {
        return enableDelete;
    }

    public TableConfiguration setEnableDelete(Boolean enableDelete) {
        this.enableDelete = enableDelete;
        return this;
    }

    public Boolean getEnableInsert() {
        return enableInsert;
    }

    public TableConfiguration setEnableInsert(Boolean enableInsert) {
        this.enableInsert = enableInsert;
        return this;
    }
}
