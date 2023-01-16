package com.jch.racWiFi.weather.model;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.weather.model.WeatherDataModel;

public class WeatherDataPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IWeatherDataPresenter iWeatherDataPresenter;

    public interface IWeatherDataPresenter extends INetworkConnectivity {
        void onGetWeatherDataFailure(WeatherDataModel.WeatherDataModelFailureResponse weatherDataModelFailureResponse);

        void onGetWeatherDataSuccess(WeatherDataModel.WeatherDataModelResponseSuccess weatherDataModelResponseSuccess);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public WeatherDataPresenter(IWeatherDataPresenter iWeatherDataPresenter2) {
        this.iWeatherDataPresenter = iWeatherDataPresenter2;
    }

    public void getWeatherData(String str) {
        final SingleLiveEvent<GenericResponse> weatherData = new WeatherDataNetworkDispatcher().getWeatherData(str);
        weatherData.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                weatherData.removeObserver(this);
                if (WeatherDataPresenter.this.iWeatherDataPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        WeatherDataPresenter.this.iWeatherDataPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        WeatherDataPresenter.this.iWeatherDataPresenter.onGetWeatherDataSuccess((WeatherDataModel.WeatherDataModelResponseSuccess) genericResponse.getBodyOfType(WeatherDataModel.WeatherDataModelResponseSuccess.class));
                    } else {
                        WeatherDataModel.WeatherDataModelFailureResponse weatherDataModelFailureResponse = (WeatherDataModel.WeatherDataModelFailureResponse) genericResponse.getErrorBodyOfType(WeatherDataModel.WeatherDataModelFailureResponse.class);
                        weatherDataModelFailureResponse.statusCode = genericResponse.getResponse().code();
                        WeatherDataPresenter.this.iWeatherDataPresenter.onGetWeatherDataFailure(weatherDataModelFailureResponse);
                    }
                }
            }
        });
    }

    public void getWeatherDataForRac(int i, String str) {
        final SingleLiveEvent<GenericResponse> weatherDataForRac = new WeatherDataNetworkDispatcher().getWeatherDataForRac(i, str);
        weatherDataForRac.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                weatherDataForRac.removeObserver(this);
                if (WeatherDataPresenter.this.iWeatherDataPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        WeatherDataPresenter.this.iWeatherDataPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        WeatherDataPresenter.this.iWeatherDataPresenter.onGetWeatherDataSuccess((WeatherDataModel.WeatherDataModelResponseSuccess) genericResponse.getBodyOfType(WeatherDataModel.WeatherDataModelResponseSuccess.class));
                    } else {
                        WeatherDataModel.WeatherDataModelFailureResponse weatherDataModelFailureResponse = (WeatherDataModel.WeatherDataModelFailureResponse) genericResponse.getErrorBodyOfType(WeatherDataModel.WeatherDataModelFailureResponse.class);
                        weatherDataModelFailureResponse.statusCode = genericResponse.getResponse().code();
                        WeatherDataPresenter.this.iWeatherDataPresenter.onGetWeatherDataFailure(weatherDataModelFailureResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        if (this.iWeatherDataPresenter != null) {
            this.iWeatherDataPresenter = null;
        }
    }
}
