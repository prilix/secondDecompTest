package com.jch.racWiFi.iduManagement.model;

import com.google.gson.annotations.SerializedName;

public class CommandStatus {
    private BasicIDUControls basicIDUControls;
    @SerializedName("commandId")
    private String commandId;
    @SerializedName("status")
    private String status;
    @SerializedName("thingId")
    private String thingId;
    @SerializedName("vendorThingId")
    private String vendorThingId;

    public BasicIDUControls getBasicIDUControls() {
        return this.basicIDUControls;
    }

    public void setBasicIDUControls(BasicIDUControls basicIDUControls2) {
        this.basicIDUControls = basicIDUControls2;
    }

    public enum CommandStatusEnum {
        SENDING,
        INCOMPLETE,
        DONE;

        public boolean isIncomplete() {
            return equals(INCOMPLETE);
        }

        public boolean isSending() {
            return equals(SENDING);
        }

        public boolean isDone() {
            return equals(DONE);
        }
    }

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public String getCommandId() {
        return this.commandId;
    }

    public void setCommandId(String str) {
        this.commandId = str;
    }

    public CommandStatusEnum getStatus() {
        return CommandStatusEnum.valueOf(this.status);
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getThingId() {
        return this.thingId;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public String toString() {
        return "ClassPojo [vendorThingId = " + this.vendorThingId + ", commandId = " + this.commandId + ", status = " + this.status + ", thingId = " + this.thingId + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ((CommandStatus) obj).getCommandId().equals(this.commandId);
    }
}
