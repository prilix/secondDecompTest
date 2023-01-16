package com.jch.racWiFi.iduManagement.network;

import android.os.Handler;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.CommandStatusList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class CommandExecutionNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<CommandStatusList> {
    public static final String METHOD_GET_IDU_STATUS = "/rac/status/command";
    private List<CommandStatus> commandStatusList = new ArrayList();
    private SingleLiveEvent<CommandStatusList> commandStatusSingleLiveEvent = new SingleLiveEvent<>();
    private int secondsCount = 0;

    public interface IDUStateApi {
        @POST("/rac/status/command")
        Call<CommandStatusList> getLatestRacState(@Body List<CommandStatus> list);
    }

    public void onFailure(Call<CommandStatusList> call, Throwable th) {
    }

    public CommandExecutionNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<CommandStatusList> pollForCmdStatus(List<CommandStatus> list) {
        Call<CommandStatusList> latestRacState = ((IDUStateApi) getRetrofitService().create(IDUStateApi.class)).getLatestRacState(list);
        this.commandStatusList.clear();
        this.commandStatusList.addAll(list);
        latestRacState.enqueue(this);
        return this.commandStatusSingleLiveEvent;
    }

    public void onResponse(final Call<CommandStatusList> call, Response<CommandStatusList> response) {
        if (response.isSuccessful()) {
            CommandStatusList body = response.body();
            boolean z = false;
            if (body != null) {
                Iterator it = body.iterator();
                while (it.hasNext()) {
                    CommandStatus commandStatus = (CommandStatus) it.next();
                    commandStatus.setBasicIDUControls(this.commandStatusList.get(this.commandStatusList.indexOf(commandStatus)).getBasicIDUControls());
                }
                Iterator it2 = body.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    CommandStatus commandStatus2 = (CommandStatus) it2.next();
                    if (!commandStatus2.getStatus().equals(CommandStatus.CommandStatusEnum.DONE)) {
                        commandStatus2.getStatus().equals(CommandStatus.CommandStatusEnum.INCOMPLETE);
                        break;
                    }
                }
            }
            if (z) {
                this.commandStatusSingleLiveEvent.postValue(body);
                return;
            }
            int i = this.secondsCount + 1;
            this.secondsCount = i;
            if (i >= 12) {
                this.commandStatusSingleLiveEvent.postValue(body);
            } else {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        call.clone().enqueue(CommandExecutionNetworkDispatcher.this);
                    }
                }, 1000);
            }
        }
    }
}
