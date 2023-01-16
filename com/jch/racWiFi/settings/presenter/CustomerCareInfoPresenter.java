package com.jch.racWiFi.settings.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.CustomerCareInfoModelResponse;
import com.jch.racWiFi.settings.network.CustomerCareInfoNetworkDispatcher;
import java.util.ArrayList;

public class CustomerCareInfoPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public CustomerCareInfoInterface iCustomerCareInfoInterface;

    public interface CustomerCareInfoInterface extends INetworkConnectivity {
        void onCustomerCareInfoFetchFailure(GenericResponse genericResponse);

        void onCustomerCareInfoFetchSuccess(ArrayList<CustomerCareInfoModelResponse.CustomerCareInfo> arrayList);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public CustomerCareInfoPresenter(CustomerCareInfoInterface customerCareInfoInterface) {
        this.iCustomerCareInfoInterface = customerCareInfoInterface;
    }

    public void getCustomerCareInfo(LifecycleOwner lifecycleOwner, int i, int i2) {
        new CustomerCareInfoNetworkDispatcher().getCustomerCareInfoForRac(i, i2).observeSingleEvent(lifecycleOwner, new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (CustomerCareInfoPresenter.this.iCustomerCareInfoInterface != null) {
                    if (genericResponse.unableToReachServer()) {
                        CustomerCareInfoPresenter.this.iCustomerCareInfoInterface.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        CustomerCareInfoModelResponse customerCareInfoModelResponse = (CustomerCareInfoModelResponse) genericResponse.getBodyOfType(CustomerCareInfoModelResponse.class);
                        if (customerCareInfoModelResponse != null) {
                            CustomerCareInfoPresenter.this.iCustomerCareInfoInterface.onCustomerCareInfoFetchSuccess(customerCareInfoModelResponse.customerCareInfoArrayList);
                        }
                    } else {
                        CustomerCareInfoPresenter.this.iCustomerCareInfoInterface.onCustomerCareInfoFetchFailure(genericResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iCustomerCareInfoInterface = null;
    }
}
