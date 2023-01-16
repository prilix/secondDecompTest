package com.jch.racWiFi.iduManagement.model;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandStatusList extends ArrayList<CommandStatus> {
    public boolean areCommandExecuted() {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (!((CommandStatus) it.next()).getStatus().isDone()) {
                return false;
            }
        }
        return true;
    }
}
