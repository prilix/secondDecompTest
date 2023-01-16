package com.jch.racWiFi.mock;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jch.racWiFi.C1662R2;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.ImageUtils;
import com.jch.racWiFi.energyConsumption.model.request.AllRacEnergyUsageReqBody;
import com.jch.racWiFi.energyConsumption.model.request.ECSettings;
import com.jch.racWiFi.energyConsumption.model.request.EnergyConsumptionRequestBody;
import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.EnergyCostSettingsData;
import com.jch.racWiFi.energyConsumption.model.response.AllRac;
import com.jch.racWiFi.energyConsumption.model.response.AllRacMonthlyData;
import com.jch.racWiFi.energyConsumption.model.response.AllRacResponseBody;
import com.jch.racWiFi.energyConsumption.model.response.AllRacTotalCostDataMain;
import com.jch.racWiFi.energyConsumption.model.response.IndivisualRacCostData;
import com.jch.racWiFi.energyConsumption.model.response.RacWiseEnergyUsageResponseMain;
import com.jch.racWiFi.energyConsumption.utility.QueryFilterType;
import com.jch.racWiFi.fcm.model.FcmResponse;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.TimerEnableDisable;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.CommandStatusList;
import com.jch.racWiFi.iduManagement.model.CopySchedule;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;
import com.jch.racWiFi.iduManagement.model.Power;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import com.jch.racWiFi.iduManagement.model.TimerModels;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceServerRequestModel;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceServerResponseModel;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlStateResponseModel;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlsStateModel;
import com.jch.racWiFi.iduManagement.view.HolidayModeFragment;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoRequestBody;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoResponseBody;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.InviteMemberModels;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.UpdateNameModels;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.model.dataProvider.PermissionFactory;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.model.dto.PermissionSaveDto;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.model.RenamingDto;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.CharUtils;
import p020ua.naiksoftware.stomp.dto.StompMessage;

public class MockInterceptor {
    private static FcmResponse.Body getObject(List<FcmResponse.Body> list, String str, String str2) {
        return null;
    }

    public static Response intercept(Interceptor.Chain chain, Request request) throws IOException {
        Object obj;
        String method = request.method();
        method.hashCode();
        char c = 65535;
        switch (method.hashCode()) {
            case 70454:
                if (method.equals(Constants.HttpMethods.GET)) {
                    c = 0;
                    break;
                }
                break;
            case 79599:
                if (method.equals(Constants.HttpMethods.PUT)) {
                    c = 1;
                    break;
                }
                break;
            case 2461856:
                if (method.equals(Constants.HttpMethods.POST)) {
                    c = 2;
                    break;
                }
                break;
            case 2012838315:
                if (method.equals(Constants.HttpMethods.DELETE)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                obj = get(request);
                break;
            case 1:
                obj = put(request);
                break;
            case 2:
                obj = post(request);
                break;
            case 3:
                obj = delete(request);
                break;
            default:
                obj = null;
                break;
        }
        Response proceed = chain.proceed(request);
        Response.Builder newBuilder = proceed.newBuilder();
        if (obj == null) {
            return proceed;
        }
        newBuilder.code(200).protocol(Protocol.HTTP_2);
        newBuilder.body(ResponseBody.create(MediaType.parse("application/json"), new Gson().toJson(obj)));
        return newBuilder.build();
    }

    private static Object put(Request request) throws IOException {
        String prefix = prefix(request.url());
        prefix.hashCode();
        if (prefix.equals("/iam")) {
            return put("/iam", request);
        }
        if (!prefix.equals("/rac")) {
            return null;
        }
        return put("/rac", request);
    }

    private static Object get(Request request) throws IOException {
        String prefix = prefix(request.url());
        prefix.hashCode();
        if (prefix.equals("/iam")) {
            return get("/iam", request);
        }
        if (!prefix.equals("/rac")) {
            return null;
        }
        return get("/rac", request);
    }

    private static Object post(Request request) throws IOException {
        String prefix = prefix(request.url());
        prefix.hashCode();
        if (prefix.equals("/iam")) {
            return post("/iam", request);
        }
        if (!prefix.equals("/rac")) {
            return null;
        }
        return post("/rac", request);
    }

    private static Object delete(Request request) throws IOException {
        String prefix = prefix(request.url());
        prefix.hashCode();
        if (prefix.equals("/iam")) {
            return delete("/iam", request);
        }
        if (!prefix.equals("/rac")) {
            return null;
        }
        return delete("/rac", request);
    }

    private static Object get(String str, Request request) throws IOException {
        String subUrl = subUrl(str, request.url());
        subUrl.hashCode();
        char c = 65535;
        switch (subUrl.hashCode()) {
            case -1820472552:
                if (subUrl.equals("/ownership/groups/permissions")) {
                    c = 0;
                    break;
                }
                break;
            case -1811897057:
                if (subUrl.equals(Constants.Link.CUSTOMER_SUPPORT)) {
                    c = 1;
                    break;
                }
                break;
            case -1535116427:
                if (subUrl.equals("/family-account/v2/groups/members")) {
                    c = 2;
                    break;
                }
                break;
            case -1312817861:
                if (subUrl.equals(Constants.Link.RAC_USER_MANUAL)) {
                    c = 3;
                    break;
                }
                break;
            case -1209175891:
                if (subUrl.equals(Constants.Link.WEEKLY_TIMER_OPERATIONS)) {
                    c = 4;
                    break;
                }
                break;
            case -1001632050:
                if (subUrl.equals(Constants.Link.WEEKLY_TIMER_SCHEDULE_COUNT)) {
                    c = 5;
                    break;
                }
                break;
            case -902971116:
                if (subUrl.equals(Constants.Link.PRIVACY_POLICY)) {
                    c = 6;
                    break;
                }
                break;
            case -559162400:
                if (subUrl.equals(Constants.Link.IDU_MODEL_LIST)) {
                    c = 7;
                    break;
                }
                break;
            case -510685077:
                if (subUrl.equals(Constants.Link.FAMILY_GROUP)) {
                    c = 8;
                    break;
                }
                break;
            case -503166908:
                if (subUrl.equals("/ownership/groups/permissions/has-functional-access")) {
                    c = 9;
                    break;
                }
                break;
            case -170702725:
                if (subUrl.equals(Constants.Link.APP_VERSION)) {
                    c = 10;
                    break;
                }
                break;
            case -113684983:
                if (subUrl.equals("/scheduled-operations/timer/racs/schedules?familyId=")) {
                    c = 11;
                    break;
                }
                break;
            case 23480962:
                if (subUrl.equals(Constants.Link.SMART_FENCE_STATUS)) {
                    c = 12;
                    break;
                }
                break;
            case 158355375:
                if (subUrl.equals("/family-account/v2/groups/members?x=")) {
                    c = CharUtils.f712CR;
                    break;
                }
                break;
            case 410665941:
                if (subUrl.equals(Constants.Link.USER_INFO)) {
                    c = 14;
                    break;
                }
                break;
            case 472715591:
                if (subUrl.equals("/weather/info?language=EN")) {
                    c = 15;
                    break;
                }
                break;
            case 472715748:
                if (subUrl.equals("/weather/info?language=JP")) {
                    c = 16;
                    break;
                }
                break;
            case 538271083:
                if (subUrl.equals("/scheduled-operations/holidayModeSchedule/schedules")) {
                    c = 17;
                    break;
                }
                break;
            case 1045830221:
                if (subUrl.equals(Constants.Link.ENERGY_CONSUMPTION_GRAPH_BUDGET)) {
                    c = 18;
                    break;
                }
                break;
            case 1206309555:
                if (subUrl.equals(Constants.Link.SMART_FENCE_SETTINGS)) {
                    c = 19;
                    break;
                }
                break;
            case 1348035565:
                if (subUrl.equals(Constants.Link.FAMILY_INVITATIONS)) {
                    c = 20;
                    break;
                }
                break;
            case 1703905535:
                if (subUrl.equals(Constants.Link.EC_ALL_RAC)) {
                    c = 21;
                    break;
                }
                break;
            case 1744783406:
                if (subUrl.equals(Constants.Link.CONFIG_INIT)) {
                    c = 22;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return getData(Constants.Json.USER_PERMISSIONS, (Request) null);
            case 1:
                return getData(Constants.Json.CUSTOMER_SUPPORT, (Request) null);
            case 2:
                return getData(Constants.Json.FAMILY_LIST_BY_FAMILY_ID, (Request) null);
            case 3:
                return getData(Constants.Json.RAC_USER_MANUAL, (Request) null);
            case 4:
                return getData(Constants.Json.WEEKLY_TIMER, request);
            case 5:
                return getData();
            case 6:
                return getData(Constants.Json.PRIVACY_POLICY, (Request) null);
            case 7:
                return getData(Constants.Json.IDU_MODEL_LIST, (Request) null);
            case 8:
                return getData(Constants.Json.FAMILY_GROUP, (Request) null);
            case 9:
                return getData(Constants.Json.FUNCTIONAL_ACCESS, (Request) null);
            case 10:
                return getData(Constants.Json.APP_VERSION, (Request) null);
            case 11:
                return getData(Constants.Json.TIMER, request);
            case 12:
                return getData(Constants.Json.SMART_FENCE_STATUS, (Request) null);
            case 13:
                return getData(Constants.Json.SMART_FENCE_MEMBERS, (Request) null);
            case 14:
                return getData(Constants.Json.USER_INFO, (Request) null);
            case 15:
                return getData(Constants.Json.WEATHER_INFO_EN, (Request) null);
            case 16:
                return getData(Constants.Json.WEATHER_INFO_JP, (Request) null);
            case 17:
                return getData(Constants.Json.HOLIDAY_MODE, (Request) null);
            case 18:
                return getEnergyConsumptionBudgetDatas();
            case 19:
                return getData(Constants.Json.SMART_FENCE_SETTINGS, (Request) null);
            case 20:
                return getData(Constants.Json.FAMILY_INVITATIONS, (Request) null);
            case 21:
                return getData(Constants.Json.EC_ALL_RAC, (Request) null);
            case 22:
                return getData(Constants.Json.CONFIG_INIT, (Request) null);
            default:
                return null;
        }
    }

    private static Object put(String str, Request request) throws IOException {
        String subUrl = subUrl(str, request.url());
        subUrl.hashCode();
        char c = 65535;
        switch (subUrl.hashCode()) {
            case -1825872948:
                if (subUrl.equals(Constants.Link.UPDATE_IDU_STATE_AS_WHOLE)) {
                    c = 0;
                    break;
                }
                break;
            case -1820472552:
                if (subUrl.equals("/ownership/groups/permissions")) {
                    c = 1;
                    break;
                }
                break;
            case -1747121096:
                if (subUrl.equals(Constants.Link.IDU_RENAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1589417106:
                if (subUrl.equals(Constants.Link.STATUS)) {
                    c = 3;
                    break;
                }
                break;
            case -1209175891:
                if (subUrl.equals(Constants.Link.WEEKLY_TIMER_OPERATIONS)) {
                    c = 4;
                    break;
                }
                break;
            case -963682011:
                if (subUrl.equals(Constants.Link.UPDATE_ADDRESS)) {
                    c = 5;
                    break;
                }
                break;
            case -914688513:
                if (subUrl.equals(Constants.Link.IDU_FROST_WASH)) {
                    c = 6;
                    break;
                }
                break;
            case -629762620:
                if (subUrl.equals("/manage-idu/groups/idu/stop")) {
                    c = 7;
                    break;
                }
                break;
            case -605017350:
                if (subUrl.equals(Constants.Link.UPDATE_NAME)) {
                    c = 8;
                    break;
                }
                break;
            case -113684983:
                if (subUrl.equals("/scheduled-operations/timer/racs/schedules?familyId=")) {
                    c = 9;
                    break;
                }
                break;
            case 1952181984:
                if (subUrl.equals("/manage-idu/groups/idu/start")) {
                    c = 10;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return updateIDUState(request);
            case 1:
                return updatePermission(request.body());
            case 2:
                return renameIDU(request);
            case 3:
                return updateIDUStatus();
            case 4:
                return updateWeeklyTimer(request);
            case 5:
                return updateAddress(request.body());
            case 6:
                return iduFrostWash(request);
            case 7:
                return stopAllUnits();
            case 8:
                return updateName(request.body());
            case 9:
                return updateTimer(request.body());
            case 10:
                return startAllUnits();
            default:
                return null;
        }
    }

    private static Object post(String str, Request request) throws IOException {
        String subUrl = subUrl(str, request.url());
        subUrl.hashCode();
        char c = 65535;
        switch (subUrl.hashCode()) {
            case -1752768181:
                if (subUrl.equals(Constants.Link.ON_BOARD_IDU_INFO)) {
                    c = 0;
                    break;
                }
                break;
            case -1209175891:
                if (subUrl.equals(Constants.Link.WEEKLY_TIMER_OPERATIONS)) {
                    c = 1;
                    break;
                }
                break;
            case -945778658:
                if (subUrl.equals(Constants.Link.DELETE_PICTURE)) {
                    c = 2;
                    break;
                }
                break;
            case -918329780:
                if (subUrl.equals(Constants.Link.WEEKLY_TIMER_COPY_SCHEDULE_DAY_WISE)) {
                    c = 3;
                    break;
                }
                break;
            case -656717375:
                if (subUrl.equals(Constants.Link.ON_BOARD_IDU)) {
                    c = 4;
                    break;
                }
                break;
            case -448915340:
                if (subUrl.equals(Constants.Link.UPLOAD_PICTURE)) {
                    c = 5;
                    break;
                }
                break;
            case -399295932:
                if (subUrl.equals(Constants.Link.EC_SETTINGS)) {
                    c = 6;
                    break;
                }
                break;
            case -249645752:
                if (subUrl.equals("/family-account/groups/invite")) {
                    c = 7;
                    break;
                }
                break;
            case -159032291:
                if (subUrl.equals(Constants.Link.IDU_STATUS)) {
                    c = 8;
                    break;
                }
                break;
            case 23480962:
                if (subUrl.equals(Constants.Link.SMART_FENCE_STATUS)) {
                    c = 9;
                    break;
                }
                break;
            case 215500356:
                if (subUrl.equals(Constants.Link.INTERRUPT_HOLIDAY_MODE)) {
                    c = 10;
                    break;
                }
                break;
            case 391096950:
                if (subUrl.equals(Constants.Link.SCHEDULE_ENABLE_DISABLE)) {
                    c = 11;
                    break;
                }
                break;
            case 538271083:
                if (subUrl.equals("/scheduled-operations/holidayModeSchedule/schedules")) {
                    c = 12;
                    break;
                }
                break;
            case 615456611:
                if (subUrl.equals(Constants.Link.ENERGY_CONSUMPTION_SUMMARY)) {
                    c = CharUtils.f712CR;
                    break;
                }
                break;
            case 956930908:
                if (subUrl.equals(Constants.Link.ENERGY_CONSUMPTION_GRAPH_DATA)) {
                    c = 14;
                    break;
                }
                break;
            case 1206309555:
                if (subUrl.equals(Constants.Link.SMART_FENCE_SETTINGS)) {
                    c = 15;
                    break;
                }
                break;
            case 1376337108:
                if (subUrl.equals(Constants.Link.WEEKLY_TIMER_COPY_SCHEDULE_RAC_WISE)) {
                    c = 16;
                    break;
                }
                break;
            case 1732387689:
                if (subUrl.equals(Constants.Link.ON_BOARD_IDU_INDIA)) {
                    c = 17;
                    break;
                }
                break;
            case 2058561829:
                if (subUrl.equals(Constants.Link.RAC_CONFIGURATION_MODEL_WISE)) {
                    c = 18;
                    break;
                }
                break;
            case 2072644112:
                if (subUrl.equals(Constants.Link.ENERGY_CONSUMPTION_GRAPH_BUDGET_SAVE)) {
                    c = 19;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return onBoardIDUInfo(request.body());
            case 1:
                return addWeeklyTimer(request);
            case 2:
                return getPicture((File) null);
            case 3:
                return copyScheduleFromDayToDays(request);
            case 4:
            case 17:
                return onBoardIDU(request.body());
            case 5:
                return getPicture(new File(request.header("filePath")));
            case 6:
                return saveECSettings(request);
            case 7:
                return sendInvite(request.body());
            case 8:
                return getIDUStatus(request.body());
            case 9:
                return smartFenceStatus(request);
            case 10:
                return interrputToHolidayMode(request);
            case 11:
                return enableDisableSchedule(request);
            case 12:
                return updateScheduleHolidayMode(request);
            case 13:
                return addEnergyConsumptionSummary(request);
            case 14:
                return addEnergyConsumptionGraphDatas(request);
            case 15:
                return smartFenceSettings(request);
            case 16:
                return copyScheduleFromRacToRacs(request);
            case 18:
                return racConfiguration();
            case 19:
                return saveEnergyConsumptionBudgetDatas(request);
            default:
                return null;
        }
    }

    private static Object saveECSettings(Request request) throws IOException {
        ECSettings eCSettings = (ECSettings) new Stream(ECSettings.class, request.body()).readBody().getObject();
        if (eCSettings != null) {
            Stream stream = new Stream(new TypeToken<AllRacTotalCostDataMain>() {
            }.getType(), Constants.Json.EC_SUMMARY_V3);
            AllRacTotalCostDataMain allRacTotalCostDataMain = (AllRacTotalCostDataMain) stream.unWrapStream();
            allRacTotalCostDataMain.setAllRacsMonthlyData(getAllRacData());
            if (eCSettings.getRacIds() != null) {
                allRacTotalCostDataMain.setIndividualRacsData(getRacList(eCSettings.getRacIds()));
            }
            stream.store(new Gson().toJson((Object) allRacTotalCostDataMain));
            updateAllRac(eCSettings.getRacIds());
        }
        return new Object();
    }

    private static void updateAllRac(ArrayList<Long> arrayList) throws IOException {
        Stream stream = new Stream(new TypeToken<AllRacResponseBody>() {
        }.getType(), Constants.Json.EC_ALL_RAC);
        AllRacResponseBody allRacResponseBody = (AllRacResponseBody) stream.unWrapStream();
        List<AllRac> allRacList = allRacResponseBody.getAllRacList();
        if (!arrayList.isEmpty()) {
            for (AllRac next : allRacList) {
                next.setSelected(arrayList.contains(Long.valueOf((long) next.getRacId())));
            }
        } else {
            for (AllRac selected : allRacList) {
                selected.setSelected(false);
            }
            Stream stream2 = new Stream(new TypeToken<AllRacTotalCostDataMain>() {
            }.getType(), Constants.Json.EC_SUMMARY_V3);
            AllRacTotalCostDataMain allRacTotalCostDataMain = (AllRacTotalCostDataMain) stream2.unWrapStream();
            ArrayList<IndivisualRacCostData> individualRacsData = allRacTotalCostDataMain.getIndividualRacsData();
            allRacTotalCostDataMain.setAllRacsMonthlyData((AllRacMonthlyData) null);
            individualRacsData.removeAll(individualRacsData);
            stream2.store(new Gson().toJson((Object) allRacTotalCostDataMain));
        }
        stream.store(new Gson().toJson((Object) allRacResponseBody));
    }

    private static ArrayList<IndivisualRacCostData> getRacList(ArrayList<Long> arrayList) {
        ArrayList<IndivisualRacCostData> arrayList2 = new ArrayList<>();
        Iterator<Long> it = arrayList.iterator();
        while (it.hasNext()) {
            Long next = it.next();
            IndivisualRacCostData indivisualRacCostData = new IndivisualRacCostData();
            indivisualRacCostData.setRacId(next.intValue());
            indivisualRacCostData.setRacName(next.longValue() == 100 ? "Living" : "Dining");
            indivisualRacCostData.setVendorThingId(next.longValue() == 100 ? "JCH-801a9bbb" : "JCH-886005b9");
            int i = 7;
            indivisualRacCostData.setCost(next.longValue() == 100 ? 7 : C1662R2.attr.borderLength);
            if (next.longValue() != 100) {
                i = C1662R2.attr.bottomNavigationStyle;
            }
            indivisualRacCostData.setEnergyConsumed(i);
            arrayList2.add(indivisualRacCostData);
        }
        return arrayList2;
    }

    private static AllRacMonthlyData getAllRacData() {
        AllRacMonthlyData allRacMonthlyData = new AllRacMonthlyData();
        allRacMonthlyData.setCost(171.0d);
        allRacMonthlyData.setEnergyConsumed(168.0d);
        allRacMonthlyData.setBudget(100.0d);
        allRacMonthlyData.setCurrency("USD");
        return allRacMonthlyData;
    }

    private static Object getSmartFenceMembers(Request request, String str) throws IOException {
        HttpUrl url = request.url();
        String httpUrl = url.toString();
        String replace = httpUrl.replace(url.scheme() + "://" + url.host() + str, "");
        UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse = (UserFamilyMemberModels.UserFamilyMemberSuccessResponse) new Stream(new TypeToken<UserFamilyMemberModels.UserFamilyMemberSuccessResponse>() {
        }.getType(), Constants.Json.SMART_FENCE_MEMBERS).unWrapStream();
        if (userFamilyMemberSuccessResponse != null) {
            for (UserFamilyMemberModels.UserFamilyMemberInfo userFamilyMemberInfo : userFamilyMemberSuccessResponse.userFamilyMemberInfo) {
                String.valueOf(userFamilyMemberInfo.detailsUserInfoId).equals(replace.trim().split("/")[4]);
            }
        }
        return userFamilyMemberSuccessResponse;
    }

    private static Object smartFenceSettings(Request request) throws IOException {
        GeoFenceServerRequestModel geoFenceServerRequestModel = (GeoFenceServerRequestModel) new Stream(GeoFenceServerRequestModel.class, request.body()).readBody().getObject();
        if (geoFenceServerRequestModel == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<List<GeoFenceServerResponseModel>>() {
        }.getType(), Constants.Json.SMART_FENCE_SETTINGS);
        List<GeoFenceServerResponseModel> list = (List) stream.unWrapStream();
        for (GeoFenceServerResponseModel geoFenceServerResponseModel : list) {
            if (geoFenceServerResponseModel.getFamilyId().equals(geoFenceServerRequestModel.getFamilyId())) {
                geoFenceServerResponseModel.setAssociatedUsers(geoFenceServerRequestModel.getAssociatedUsers());
                geoFenceServerResponseModel.setAssociatedRac(geoFenceServerRequestModel.getAssociatedRac());
                geoFenceServerResponseModel.setFamilyId(geoFenceServerRequestModel.getFamilyId());
                geoFenceServerResponseModel.setRacSettings(geoFenceServerRequestModel.getRacSettings());
                geoFenceServerResponseModel.setGeoFenceSettings(geoFenceServerRequestModel.getGeoFenceSettings());
            }
        }
        stream.store(new Gson().toJson((Object) list));
        return list;
    }

    private static Object enableDisableSchedule(Request request) throws IOException {
        DetailedIduModel detailedIduModelFromRacId;
        TimerEnableDisable.ScheduleType scheduleType = (TimerEnableDisable.ScheduleType) new Stream(TimerEnableDisable.ScheduleType.class, request.body()).readBody().getObject();
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream != null) {
            IduList iduList = (IduList) unWrapStream.data;
            if (!iduList.isEmpty() && (detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId(getID(request))) != null) {
                detailedIduModelFromRacId.scheduletype = scheduleType.getSchedule();
                webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
            }
        }
        return new Object();
    }

    private static Object copyScheduleFromRacToRacs(Request request) throws IOException {
        CopySchedule.RacWise racWise = (CopySchedule.RacWise) new Stream(CopySchedule.RacWise.class, request.body()).readBody().getObject();
        if (racWise == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<List<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
        }.getType(), Constants.Json.WEEKLY_TIMER);
        List<WeeklyTimerModelV2.WeeklyTimerResponseData> list = (List) stream.unWrapStream();
        ArrayList<WeeklyTimerModelV2.WeeklyTimerResponseData> arrayList = new ArrayList<>();
        for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData : list) {
            if (racWise.getFrom() == weeklyTimerResponseData.f466id) {
                arrayList.add(weeklyTimerResponseData);
            }
        }
        long[] to = racWise.getTo();
        for (long j : to) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (j == ((WeeklyTimerModelV2.WeeklyTimerResponseData) it.next()).f466id) {
                    it.remove();
                }
            }
        }
        for (long j2 : to) {
            for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData2 : arrayList) {
                WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData3 = new WeeklyTimerModelV2.WeeklyTimerResponseData();
                weeklyTimerResponseData3.scheduleId = geterateUID() + j2;
                weeklyTimerResponseData3.f466id = j2;
                weeklyTimerResponseData3.day = weeklyTimerResponseData2.day;
                weeklyTimerResponseData3.mode = weeklyTimerResponseData2.mode;
                weeklyTimerResponseData3.power = weeklyTimerResponseData2.power;
                weeklyTimerResponseData3.startAt = weeklyTimerResponseData2.startAt;
                weeklyTimerResponseData3.temperature = weeklyTimerResponseData2.temperature;
                list.add(weeklyTimerResponseData3);
            }
        }
        stream.store(new Gson().toJson((Object) list));
        return new WeeklyTimerModelV2.WeeklyTimerResponseData();
    }

    private static Object copyScheduleFromDayToDays(Request request) throws IOException {
        CopySchedule.DayWise dayWise = (CopySchedule.DayWise) new Stream(CopySchedule.DayWise.class, request.body()).readBody().getObject();
        if (dayWise == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<List<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
        }.getType(), Constants.Json.WEEKLY_TIMER);
        List<WeeklyTimerModelV2.WeeklyTimerResponseData> list = (List) stream.unWrapStream();
        ArrayList<WeeklyTimerModelV2.WeeklyTimerResponseData> arrayList = new ArrayList<>();
        for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData : list) {
            if (dayWise.getRacId() == weeklyTimerResponseData.f466id && dayWise.getFrom().equalsIgnoreCase(weeklyTimerResponseData.day)) {
                arrayList.add(weeklyTimerResponseData);
            }
        }
        String[] to = dayWise.getTo();
        for (String str : to) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData2 = (WeeklyTimerModelV2.WeeklyTimerResponseData) it.next();
                if (dayWise.getRacId() == weeklyTimerResponseData2.f466id && str.equalsIgnoreCase(weeklyTimerResponseData2.day)) {
                    it.remove();
                }
            }
        }
        for (String str2 : to) {
            for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData3 : arrayList) {
                WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData4 = new WeeklyTimerModelV2.WeeklyTimerResponseData();
                weeklyTimerResponseData4.day = str2;
                weeklyTimerResponseData4.scheduleId = weeklyTimerResponseData3.f466id + geterateUID();
                weeklyTimerResponseData4.f466id = weeklyTimerResponseData3.f466id;
                weeklyTimerResponseData4.mode = weeklyTimerResponseData3.mode;
                weeklyTimerResponseData4.power = weeklyTimerResponseData3.power;
                weeklyTimerResponseData4.startAt = weeklyTimerResponseData3.startAt;
                weeklyTimerResponseData4.temperature = weeklyTimerResponseData3.temperature;
                list.add(weeklyTimerResponseData4);
            }
        }
        stream.store(new Gson().toJson((Object) list));
        return new WeeklyTimerModelV2.WeeklyTimerResponseData();
    }

    private static Object smartFenceStatus(Request request) throws IOException {
        LocationControlsStateModel locationControlsStateModel = (LocationControlsStateModel) new Stream(LocationControlsStateModel.class, request.body()).readBody().getObject();
        if (locationControlsStateModel == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<List<LocationControlStateResponseModel>>() {
        }.getType(), Constants.Json.SMART_FENCE_STATUS);
        List<LocationControlStateResponseModel> list = (List) stream.unWrapStream();
        for (LocationControlStateResponseModel locationControlStateResponseModel : list) {
            if (String.valueOf(locationControlStateResponseModel.familyId).equals(request.url().queryParameter(Constants.FCM.FAMILY_ID))) {
                locationControlStateResponseModel.enabled = locationControlsStateModel.enabled;
            }
        }
        stream.store(new Gson().toJson((Object) list));
        return list;
    }

    private static Object addEnergyConsumptionSummary(Request request) throws IOException {
        if (((AllRacEnergyUsageReqBody) new Stream(AllRacEnergyUsageReqBody.class, request.body()).readBody().getObject()) != null) {
            return new Stream(new TypeToken<AllRacTotalCostDataMain>() {
            }.getType(), Constants.Json.EC_SUMMARY_V3).unWrapStream();
        }
        return null;
    }

    private static Object addEnergyConsumptionGraphDatas(Request request) throws IOException {
        String str;
        EnergyConsumptionRequestBody energyConsumptionRequestBody = (EnergyConsumptionRequestBody) new Stream(EnergyConsumptionRequestBody.class, request.body()).readBody().getObject();
        if (energyConsumptionRequestBody == null) {
            return null;
        }
        QueryFilterType valueOf = QueryFilterType.valueOf(energyConsumptionRequestBody.getQuery().getType());
        QueryFilterType valueOf2 = QueryFilterType.valueOf(energyConsumptionRequestBody.getQuery().getTime());
        int i = C230136.f478xeb78465e[valueOf.ordinal()];
        if (i != 1) {
            str = i != 2 ? valueOf2 == QueryFilterType.CURR ? Constants.Json.ENERGY_CONSUMPTION_YEARLY_GRAPH : Constants.Json.ENERGY_CONSUMPTION_PREV_YEAR_GRAPH : valueOf2 == QueryFilterType.CURR ? Constants.Json.ENERGY_CONSUMPTION_MONTHLY_GRAPH : Constants.Json.ENERGY_CONSUMPTION_PREV_MONTH_GRAPH;
        } else {
            str = valueOf2 == QueryFilterType.CURR ? Constants.Json.ENERGY_CONSUMPTION_WEEKLY_GRAPH : Constants.Json.ENERGY_CONSUMPTION_PREV_WEEK_GRAPH;
        }
        return new Stream(new TypeToken<RacWiseEnergyUsageResponseMain[]>() {
        }.getType(), str).unWrapStream();
    }

    /* renamed from: com.jch.racWiFi.mock.MockInterceptor$36 */
    static /* synthetic */ class C230136 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$energyConsumption$utility$QueryFilterType */
        static final /* synthetic */ int[] f478xeb78465e;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType[] r0 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f478xeb78465e = r0
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.WEEKLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f478xeb78465e     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.MONTHLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.mock.MockInterceptor.C230136.<clinit>():void");
        }
    }

    private static Object getEnergyConsumptionBudgetDatas() throws IOException {
        return new Stream(new TypeToken<Object>() {
        }.getType(), Constants.Json.ENERGY_CONSUMPTION_BUDGET_DATA).unWrapStream();
    }

    private static Object saveEnergyConsumptionBudgetDatas(Request request) throws IOException {
        EnergyCostSettingsData energyCostSettingsData = (EnergyCostSettingsData) new Stream(EnergyCostSettingsData.class, request.body()).readBody().getObject();
        if (energyCostSettingsData == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<Object>() {
        }.getType(), Constants.Json.ENERGY_CONSUMPTION_BUDGET_DATA);
        Stream stream2 = new Stream(new TypeToken<AllRacTotalCostDataMain>() {
        }.getType(), Constants.Json.EC_SUMMARY_V3);
        AllRacTotalCostDataMain allRacTotalCostDataMain = (AllRacTotalCostDataMain) stream2.unWrapStream();
        AllRacMonthlyData allRacsMonthlyData = allRacTotalCostDataMain.getAllRacsMonthlyData();
        allRacsMonthlyData.setCurrency(energyCostSettingsData.getCurrency());
        allRacsMonthlyData.setBudget(energyCostSettingsData.getBudget());
        stream.store(new Gson().toJson((Object) energyCostSettingsData));
        stream2.store(new Gson().toJson((Object) allRacTotalCostDataMain));
        return stream.unWrapStream();
    }

    private static Object addWeeklyTimer(Request request) throws IOException {
        WeeklyTimerModelV2.AddRequestData addRequestData = (WeeklyTimerModelV2.AddRequestData) new Stream(WeeklyTimerModelV2.AddRequestData.class, request.body()).readBody().getObject();
        if (addRequestData == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<ArrayList<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
        }.getType(), Constants.Json.WEEKLY_TIMER);
        List list = (List) stream.unWrapStream();
        if (list == null) {
            return null;
        }
        WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData = new WeeklyTimerModelV2.WeeklyTimerResponseData();
        weeklyTimerResponseData.scheduleId = addRequestData.f463id + geterateUID();
        weeklyTimerResponseData.f466id = addRequestData.f463id;
        weeklyTimerResponseData.day = addRequestData.day;
        weeklyTimerResponseData.mode = addRequestData.mode;
        weeklyTimerResponseData.power = addRequestData.power;
        weeklyTimerResponseData.startAt = addRequestData.startAt;
        weeklyTimerResponseData.temperature = addRequestData.temperature;
        list.add(weeklyTimerResponseData);
        stream.store(new Gson().toJson((Object) list));
        return list;
    }

    private static Object updateWeeklyTimer(Request request) throws IOException {
        WeeklyTimerModelV2.UpdateRequestData updateRequestData = (WeeklyTimerModelV2.UpdateRequestData) new Stream(WeeklyTimerModelV2.UpdateRequestData.class, request.body()).readBody().getObject();
        if (updateRequestData == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<List<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
        }.getType(), Constants.Json.WEEKLY_TIMER);
        List<WeeklyTimerModelV2.WeeklyTimerResponseData> list = (List) stream.unWrapStream();
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData : list) {
            if (weeklyTimerResponseData.scheduleId == updateRequestData.scheduleId) {
                weeklyTimerResponseData.temperature = updateRequestData.temperature;
                weeklyTimerResponseData.startAt = updateRequestData.startAt;
                weeklyTimerResponseData.power = updateRequestData.power;
                weeklyTimerResponseData.mode = updateRequestData.mode;
                weeklyTimerResponseData.day = updateRequestData.day;
            }
        }
        stream.store(new Gson().toJson((Object) list));
        return list;
    }

    private static Object updateTimer(RequestBody requestBody) throws IOException {
        TimerModels.UpdateRequestData updateRequestData = (TimerModels.UpdateRequestData) new Stream(TimerModels.UpdateRequestData.class, requestBody).readBody().getObject();
        if (updateRequestData == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<List<TimerModels.ResponseData>>() {
        }.getType(), Constants.Json.TIMER);
        List<TimerModels.ResponseData> list = (List) stream.unWrapStream();
        if (list == null) {
            list = new ArrayList<>();
        }
        if (updateRequestData.f462id == 0) {
            TimerModels.ResponseData responseData = new TimerModels.ResponseData();
            responseData.f461id = updateRequestData.racId + 1000;
            responseData.racId = updateRequestData.racId;
            responseData.endsAt = updateRequestData.endsAt;
            responseData.mode = updateRequestData.mode;
            responseData.power = updateRequestData.power;
            responseData.startsAt = updateRequestData.startsAt;
            responseData.temperature = updateRequestData.temperature;
            responseData.displayFormat = updateRequestData.displayFormat;
            list.add(responseData);
            stream.store(new Gson().toJson((Object) list));
            return responseData;
        }
        for (TimerModels.ResponseData responseData2 : list) {
            if (responseData2.racId == updateRequestData.racId) {
                responseData2.f461id = updateRequestData.f462id;
                responseData2.racId = updateRequestData.racId;
                responseData2.endsAt = updateRequestData.endsAt;
                responseData2.mode = updateRequestData.mode;
                responseData2.power = updateRequestData.power;
                responseData2.startsAt = updateRequestData.startsAt;
                responseData2.temperature = updateRequestData.temperature;
                responseData2.displayFormat = updateRequestData.displayFormat;
                list.add(responseData2);
                stream.store(new Gson().toJson((Object) list));
                return responseData2;
            }
        }
        return null;
    }

    private static Object updatePermission(Request request) throws IOException {
        PrintStream printStream = System.out;
        printStream.println("Object " + ((PermissionSaveDto) new Stream(PermissionSaveDto.class, request.body()).readBody().getObject()).toString());
        for (PermissionModel next : PermissionFactory.getInstance().cookUserPermission((AllPermissionDataDto) new Stream(new TypeToken<AllPermissionDataDto>() {
        }.getType(), Constants.Json.USER_PERMISSIONS).unWrapStream(), 14)) {
            if (next.featureID.intValue() == 13) {
                next.clickableMemberDisable = false;
            }
        }
        return null;
    }

    private static Object updatePermission(RequestBody requestBody) throws IOException {
        return new Stream(new TypeToken<Object>() {
        }.getType(), Constants.Json.USER_PERMISSIONS).unWrapStream();
    }

    private static Object interrputToHolidayMode(Request request) throws IOException {
        DetailedIduModel detailedIduModelFromRacId;
        HolidayModeModel.InterruptHolidayMode interruptHolidayMode = (HolidayModeModel.InterruptHolidayMode) new Stream(HolidayModeModel.InterruptHolidayMode.class, request.body()).readBody().getObject();
        Stream stream = new Stream(new TypeToken<List<HolidayModeModel.HolidayModeResponseData>>() {
        }.getType(), Constants.Json.HOLIDAY_MODE);
        int intValue = interruptHolidayMode.iduList.get(0).intValue();
        List<HolidayModeModel.HolidayModeResponseData> list = (List) stream.unWrapStream();
        for (HolidayModeModel.HolidayModeResponseData holidayModeResponseData : list) {
            if (intValue == holidayModeResponseData.racId) {
                holidayModeResponseData.scheduleTypes = WeeklyTimerMode.SCHEDULE_DISABLED.name();
            }
        }
        stream.store(new Gson().toJson((Object) list));
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream != null) {
            IduList iduList = (IduList) unWrapStream.data;
            if (!iduList.isEmpty() && (detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId(interruptHolidayMode.iduList.get(0).intValue())) != null) {
                detailedIduModelFromRacId.scheduletype = WeeklyTimerMode.SCHEDULE_DISABLED.name();
                webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
            }
        }
        return new Object();
    }

    private static Object updateScheduleHolidayMode(Request request) throws IOException {
        DetailedIduModel detailedIduModelFromRacId;
        DetailedIduModel detailedIduModelFromRacId2;
        HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestDataV2 = (HolidayModeModel.HolidayModeRequestDataV2) new Stream(HolidayModeModel.HolidayModeRequestDataV2.class, request.body()).readBody().getObject();
        if (holidayModeRequestDataV2 == null) {
            return null;
        }
        Stream stream = new Stream(new TypeToken<HolidayModeModel.HolidayModeRequestDataV2>() {
        }.getType(), Constants.Json.HOLIDAY_MODE);
        ArrayList arrayList = new ArrayList();
        Map<Integer, Boolean> map = holidayModeRequestDataV2.iduList;
        int i = 0;
        int i2 = 0;
        for (Map.Entry<Integer, Boolean> value : map.entrySet()) {
            if (((Boolean) value.getValue()).booleanValue()) {
                i2++;
            }
        }
        HolidayModeModel.HolidayModeResponse holidayModeResponse = new HolidayModeModel.HolidayModeResponse();
        holidayModeResponse.message = "Success";
        holidayModeResponse.result = new HolidayModeModel.Response[i2];
        for (Map.Entry next : map.entrySet()) {
            if (((Boolean) next.getValue()).booleanValue()) {
                HolidayModeModel.HolidayModeResponseData holidayModeResponseData = new HolidayModeModel.HolidayModeResponseData();
                holidayModeResponseData.racId = ((Integer) next.getKey()).intValue();
                holidayModeResponseData.holidayModeId = holidayModeRequestDataV2.holidayModeId;
                holidayModeResponseData.scheduleTypes = holidayModeRequestDataV2.scheduleType;
                holidayModeResponseData.endsAt = holidayModeRequestDataV2.endDate;
                holidayModeResponseData.temperature = holidayModeRequestDataV2.temperature;
                holidayModeResponseData.mode = "HEATING";
                arrayList.add(holidayModeResponseData);
                Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
                WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
                if (unWrapStream != null) {
                    IduList iduList = (IduList) unWrapStream.data;
                    if (!iduList.isEmpty() && (detailedIduModelFromRacId2 = iduList.getDetailedIduModelFromRacId(((Integer) next.getKey()).intValue())) != null) {
                        detailedIduModelFromRacId2.scheduletype = WeeklyTimerMode.HOLIDAY_MODE_ENABLED.name();
                        webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
                    }
                }
                HolidayModeModel.Response response = new HolidayModeModel.Response();
                response.racId = ((Integer) next.getKey()).intValue();
                response.message = "Success";
                holidayModeResponse.result[i] = response;
                i++;
            } else {
                Stream<WebSocketNotification<IduList>> webSocketNotificationStream2 = getWebSocketNotificationStream();
                WebSocketNotification unWrapStream2 = webSocketNotificationStream2.unWrapStream();
                if (unWrapStream2 != null) {
                    IduList iduList2 = (IduList) unWrapStream2.data;
                    if (!iduList2.isEmpty() && (detailedIduModelFromRacId = iduList2.getDetailedIduModelFromRacId(((Integer) next.getKey()).intValue())) != null) {
                        detailedIduModelFromRacId.scheduletype = WeeklyTimerMode.SCHEDULE_DISABLED.name();
                        webSocketNotificationStream2.store(new Gson().toJson((Object) unWrapStream2));
                    }
                }
            }
        }
        stream.store(new Gson().toJson((Object) arrayList));
        return holidayModeResponse;
    }

    private static Object sendInvite(RequestBody requestBody) {
        List list = (List) new Stream(new TypeToken<List<InviteMemberModels.InviteMemberSuccessResponse>>() {
        }.getType(), requestBody).readBody().getObject();
        return new InviteMemberModels.InviteMemberSuccessResponse();
    }

    private static Object delete(String str, Request request) throws IOException {
        String subUrl = subUrl(str, request.url());
        subUrl.hashCode();
        char c = 65535;
        switch (subUrl.hashCode()) {
            case -1259075155:
                if (subUrl.equals("/ownership/groups/members")) {
                    c = 0;
                    break;
                }
                break;
            case -1054785170:
                if (subUrl.equals(Constants.Link.WEEKLY_TIMER_1)) {
                    c = 1;
                    break;
                }
                break;
            case -945778658:
                if (subUrl.equals(Constants.Link.DELETE_PICTURE)) {
                    c = 2;
                    break;
                }
                break;
            case 1888148625:
                if (subUrl.equals("/manage-idu/groups/idu-list/")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Stream stream = new Stream(new TypeToken<UserFamilyMemberModels.UserFamilyMemberSuccessResponse>() {
                }.getType(), Constants.Json.FAMILY_LIST_BY_FAMILY_ID);
                UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse = (UserFamilyMemberModels.UserFamilyMemberSuccessResponse) stream.unWrapStream();
                List<UserFamilyMemberModels.UserFamilyMemberInfo> list = userFamilyMemberSuccessResponse.userFamilyMemberInfo;
                if (list.remove(getObject(list, getID(request)))) {
                    stream.store(new Gson().toJson((Object) userFamilyMemberSuccessResponse));
                }
                return userFamilyMemberSuccessResponse;
            case 1:
                return removeWeeklySchedule(request);
            case 2:
                return getPicture((File) null);
            case 3:
                return removeIDU(request);
            default:
                return null;
        }
    }

    private static Object removeNotification(Request request) throws IOException {
        String queryParameter = request.url().queryParameter(Constants.FCM.NOTIFICATION_ID);
        String queryParameter2 = request.url().queryParameter(Constants.FCM.NOTIFICATION_CATEGORY);
        Stream stream = new Stream(new TypeToken<List<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
        }.getType(), Constants.Json.USER_NOTIFICATIONS);
        List list = (List) stream.unWrapStream();
        if (list == null || list.isEmpty()) {
            return null;
        }
        list.remove(getObject((List<FcmResponse.Body>) list, queryParameter, queryParameter2));
        stream.store(new Gson().toJson((Object) list));
        return list;
    }

    private static Object removeWeeklySchedule(Request request) throws IOException {
        List<String> pathSegments = request.url().pathSegments();
        if (pathSegments.isEmpty()) {
            return null;
        }
        int parseInt = Integer.parseInt(pathSegments.get(4));
        long parseLong = Long.parseLong(pathSegments.get(6));
        Stream stream = new Stream(new TypeToken<List<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
        }.getType(), Constants.Json.WEEKLY_TIMER);
        List list = (List) stream.unWrapStream();
        if (list == null || list.isEmpty()) {
            return null;
        }
        list.remove(getObject((List<WeeklyTimerModelV2.WeeklyTimerResponseData>) list, parseLong, parseInt));
        stream.store(new Gson().toJson((Object) list));
        return list;
    }

    private static WeeklyTimerModelV2.WeeklyTimerResponseData getObject(List<WeeklyTimerModelV2.WeeklyTimerResponseData> list, long j, int i) {
        for (WeeklyTimerModelV2.WeeklyTimerResponseData next : list) {
            if (next.scheduleId == j && next.f466id == ((long) i)) {
                return next;
            }
        }
        return null;
    }

    private static Object removeIDU(Request request) throws IOException {
        DetailedIduModel detailedIduModelFromRacId;
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream == null) {
            return null;
        }
        IduList iduList = (IduList) unWrapStream.data;
        if (iduList.isEmpty() || (detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId(getID(request))) == null) {
            return null;
        }
        iduList.remove(detailedIduModelFromRacId);
        webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
        return unWrapStream;
    }

    private static WeeklyTimerModelV2.WeeklyTimerResponseData getObject(List<WeeklyTimerModelV2.WeeklyTimerResponseData> list, WeeklyTimerModelV2.UpdateRequestData updateRequestData) {
        for (WeeklyTimerModelV2.WeeklyTimerResponseData next : list) {
            if (next.f466id == updateRequestData.f464id && next.scheduleId == updateRequestData.scheduleId) {
                return next;
            }
        }
        return null;
    }

    private static UserFamilyMemberModels.UserFamilyMemberInfo getObject(List<UserFamilyMemberModels.UserFamilyMemberInfo> list, int i) {
        for (UserFamilyMemberModels.UserFamilyMemberInfo next : list) {
            if (next.detailsUserInfoId == i) {
                return next;
            }
        }
        return null;
    }

    private static Object racConfiguration() throws IOException {
        return new Stream(new TypeToken<ArrayList<RacModelWiseData>>() {
        }.getType(), Constants.Json.RAC_CONFIGURATION_MODEL_WISE).unWrapStream();
    }

    private static Object getIDUStatus(RequestBody requestBody) {
        CommandStatusList commandStatusList = (CommandStatusList) new Stream(CommandStatusList.class, requestBody).readBody().getObject();
        if (commandStatusList == null) {
            return null;
        }
        CommandStatus commandStatus = new CommandStatus();
        commandStatus.setCommandId("123456789");
        commandStatus.setStatus("DONE");
        commandStatus.setVendorThingId(((CommandStatus) commandStatusList.get(0)).getVendorThingId());
        commandStatus.setThingId(((CommandStatus) commandStatusList.get(0)).getThingId());
        return Collections.singletonList(commandStatus);
    }

    private static Object iduFrostWash(Request request) throws IOException {
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream == null) {
            return null;
        }
        IduList iduList = (IduList) unWrapStream.data;
        if (iduList.isEmpty()) {
            return null;
        }
        DetailedIduModel detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId(getID(request));
        DetailedIduModel detailedIduModel = (DetailedIduModel) new Stream(DetailedIduModel.class, request.body()).readBody().getObject();
        if (detailedIduModelFromRacId == null || detailedIduModel == null) {
            return null;
        }
        CommandStatus commandStatus = new CommandStatus();
        commandStatus.setCommandId("123456789");
        commandStatus.setStatus("SENDING");
        commandStatus.setVendorThingId(detailedIduModel.vendorThingId);
        commandStatus.setThingId(detailedIduModel.vendorThingId);
        detailedIduModelFromRacId.copy(detailedIduModel);
        detailedIduModelFromRacId.iduFrostWashStatus.active = true;
        detailedIduModelFromRacId.updatedAt = System.currentTimeMillis();
        webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
        WebSocketNotification unWrapStream2 = webSocketNotificationStream.unWrapStream();
        return commandStatus;
    }

    private static Object renameIDU(Request request) throws IOException {
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream == null) {
            return null;
        }
        IduList iduList = (IduList) unWrapStream.data;
        if (iduList.isEmpty()) {
            return null;
        }
        DetailedIduModel detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId(getID(request));
        RenamingDto renamingDto = (RenamingDto) new Stream(RenamingDto.class, request.body()).readBody().getObject();
        if (detailedIduModelFromRacId == null || renamingDto == null) {
            return null;
        }
        detailedIduModelFromRacId.name = renamingDto.getName();
        webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
        return unWrapStream;
    }

    private static Object onBoardIDUInfo(RequestBody requestBody) throws IOException {
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream == null) {
            return null;
        }
        IduList iduList = (IduList) unWrapStream.data;
        if (iduList.isEmpty()) {
            return null;
        }
        DetailedIduModel detailedIduModel = (DetailedIduModel) iduList.get(iduList.size() - 1);
        OnboardingInfoRequestBody onboardingInfoRequestBody = (OnboardingInfoRequestBody) new Stream(OnboardingInfoRequestBody.class, requestBody).readBody().getObject();
        if (onboardingInfoRequestBody == null || detailedIduModel == null) {
            return null;
        }
        detailedIduModel.name = onboardingInfoRequestBody.getName();
        detailedIduModel.f454id = Integer.valueOf(detailedIduModel.f454id.intValue() + 1);
        detailedIduModel.vendorThingId = onboardingInfoRequestBody.getVendorThingId();
        iduList.add(detailedIduModel);
        webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
        OnboardingInfoResponseBody onboardingInfoResponseBody = new OnboardingInfoResponseBody();
        onboardingInfoResponseBody.setId(Long.valueOf((long) detailedIduModel.f454id.intValue()));
        onboardingInfoResponseBody.setName(detailedIduModel.name);
        onboardingInfoResponseBody.setVendorThingId(detailedIduModel.vendorThingId);
        onboardingInfoResponseBody.setThingId(detailedIduModel.vendorThingId);
        return onboardingInfoResponseBody;
    }

    private static Object onBoardIDU(RequestBody requestBody) throws IOException {
        return new Stream(OnBoardingModel.class, requestBody).readBody().getObject();
    }

    private static Stream<WebSocketNotification<IduList>> getWebSocketNotificationStream() {
        return new Stream<>(new TypeToken<WebSocketNotification<IduList>>() {
        }.getType(), Constants.Json.WEB_SOCKET_NOTIFICATION);
    }

    private static IduList getIduList() throws IOException {
        WebSocketNotification unWrapStream = getWebSocketNotificationStream().unWrapStream();
        if (unWrapStream == null) {
            return null;
        }
        IduList iduList = (IduList) unWrapStream.data;
        if (!iduList.isEmpty()) {
            return iduList;
        }
        return null;
    }

    private static DetailedIduModel getDetailedIduModel(int i) throws IOException {
        DetailedIduModel detailedIduModelFromRacId;
        IduList iduList = getIduList();
        if (iduList == null || (detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId(i)) == null) {
            return null;
        }
        return detailedIduModelFromRacId;
    }

    private static ProfilePicture getPicture(File file) {
        ProfilePicture profilePicture = new ProfilePicture();
        if (file != null) {
            profilePicture.profilePicBitmap = ImageUtils.getBitmapFromFile(file);
            profilePicture.filePath = file.getAbsolutePath();
        } else {
            profilePicture.profilePicBitmap = null;
            profilePicture.filePath = null;
        }
        profilePicture.demoMode = true;
        updateProfilePicture(profilePicture);
        return profilePicture;
    }

    private static Object stopAllUnits() throws IOException {
        StopAllUnitsModels.StopAllnitsSuccessResponse stopAllnitsSuccessResponse = new StopAllUnitsModels.StopAllnitsSuccessResponse();
        stopAllnitsSuccessResponse.allSucceeded = true;
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream != null) {
            IduList iduList = (IduList) unWrapStream.data;
            if (!iduList.isEmpty()) {
                Iterator it = iduList.iterator();
                while (it.hasNext()) {
                    DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
                    detailedIduModel.power = Power.OFF.name();
                    detailedIduModel.iduFrostWashStatus.active = false;
                    detailedIduModel.updatedAt = System.currentTimeMillis();
                }
                webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
            }
        }
        return stopAllnitsSuccessResponse;
    }

    private static Object startAllUnits() throws IOException {
        StopAllUnitsModels.StartAllUnitsSuccessResponse startAllUnitsSuccessResponse = new StopAllUnitsModels.StartAllUnitsSuccessResponse();
        startAllUnitsSuccessResponse.allSucceeded = true;
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream != null) {
            IduList iduList = (IduList) unWrapStream.data;
            if (!iduList.isEmpty()) {
                Iterator it = iduList.iterator();
                while (it.hasNext()) {
                    ((DetailedIduModel) it.next()).power = Power.ON.name();
                }
                webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
            }
        }
        return startAllUnitsSuccessResponse;
    }

    private static Object updateIDUStatus() {
        CommandStatus commandStatus = new CommandStatus();
        commandStatus.setCommandId("123456789");
        commandStatus.setStatus("SENDING");
        return commandStatus;
    }

    private static Object updateIDUState(Request request) throws IOException {
        DetailedIduModel detailedIduModelFromRacId;
        DetailedIduModel detailedIduModel = (DetailedIduModel) new Stream(DetailedIduModel.class, request.body()).readBody().getObject();
        if (detailedIduModel == null) {
            return null;
        }
        CommandStatus commandStatus = new CommandStatus();
        commandStatus.setCommandId("123456789");
        commandStatus.setStatus("SENDING");
        commandStatus.setVendorThingId(detailedIduModel.vendorThingId);
        commandStatus.setThingId(detailedIduModel.vendorThingId);
        Stream<WebSocketNotification<IduList>> webSocketNotificationStream = getWebSocketNotificationStream();
        WebSocketNotification unWrapStream = webSocketNotificationStream.unWrapStream();
        if (unWrapStream == null) {
            return null;
        }
        IduList iduList = (IduList) unWrapStream.data;
        if (iduList.isEmpty() || (detailedIduModelFromRacId = iduList.getDetailedIduModelFromRacId(getID(request))) == null) {
            return null;
        }
        detailedIduModelFromRacId.copy(detailedIduModel);
        webSocketNotificationStream.store(new Gson().toJson((Object) unWrapStream));
        return commandStatus;
    }

    private static int getID(Request request) {
        List<String> pathSegments = request.url().pathSegments();
        return Integer.parseInt(pathSegments.get(pathSegments.size() - 1));
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e A[Catch:{ IOException -> 0x0058 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void updateProfilePicture(com.jch.racWiFi.userManagement.model.ProfilePicture r9) {
        /*
            com.jch.racWiFi.mock.Stream r0 = getUserInfoStream()
            r1 = 0
            java.lang.Object r2 = r0.unWrapStream()     // Catch:{ IOException -> 0x001d }
            com.jch.racWiFi.UserInfo r2 = (com.jch.racWiFi.UserInfo) r2     // Catch:{ IOException -> 0x001d }
            r2.profilePicture = r9     // Catch:{ IOException -> 0x001a }
            com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch:{ IOException -> 0x001a }
            r1.<init>()     // Catch:{ IOException -> 0x001a }
            java.lang.String r1 = r1.toJson((java.lang.Object) r2)     // Catch:{ IOException -> 0x001a }
            r0.store(r1)     // Catch:{ IOException -> 0x001a }
            goto L_0x0022
        L_0x001a:
            r0 = move-exception
            r1 = r2
            goto L_0x001e
        L_0x001d:
            r0 = move-exception
        L_0x001e:
            r0.printStackTrace()
            r2 = r1
        L_0x0022:
            com.jch.racWiFi.mock.Stream r0 = new com.jch.racWiFi.mock.Stream
            com.jch.racWiFi.mock.MockInterceptor$27 r1 = new com.jch.racWiFi.mock.MockInterceptor$27
            r1.<init>()
            java.lang.reflect.Type r1 = r1.getType()
            java.lang.String r3 = "family_group.json"
            r0.<init>((java.lang.reflect.Type) r1, (java.lang.String) r3)
            java.lang.Object r1 = r0.unWrapStream()     // Catch:{ IOException -> 0x0058 }
            com.jch.racWiFi.userManagement.model.FamilyGroupsModels$FamilyGroupsModelResponseSuccess r1 = (com.jch.racWiFi.userManagement.model.FamilyGroupsModels.FamilyGroupsModelResponseSuccess) r1     // Catch:{ IOException -> 0x0058 }
            com.jch.racWiFi.userManagement.model.FamilyGroupsModels$FamilyResult[] r3 = r1.familyResult     // Catch:{ IOException -> 0x0058 }
            int r4 = r3.length     // Catch:{ IOException -> 0x0058 }
            r5 = 0
        L_0x003c:
            if (r5 >= r4) goto L_0x004b
            r6 = r3[r5]     // Catch:{ IOException -> 0x0058 }
            int r7 = r6.familyId     // Catch:{ IOException -> 0x0058 }
            int r8 = r2.familyId     // Catch:{ IOException -> 0x0058 }
            if (r7 != r8) goto L_0x0048
            r6.creatorProfilePic = r9     // Catch:{ IOException -> 0x0058 }
        L_0x0048:
            int r5 = r5 + 1
            goto L_0x003c
        L_0x004b:
            com.google.gson.Gson r9 = new com.google.gson.Gson     // Catch:{ IOException -> 0x0058 }
            r9.<init>()     // Catch:{ IOException -> 0x0058 }
            java.lang.String r9 = r9.toJson((java.lang.Object) r1)     // Catch:{ IOException -> 0x0058 }
            r0.store(r9)     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r9 = move-exception
            r9.printStackTrace()
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.mock.MockInterceptor.updateProfilePicture(com.jch.racWiFi.userManagement.model.ProfilePicture):void");
    }

    private static UserInfo updateAddress(RequestBody requestBody) throws IOException {
        Stream<UserInfo> userInfoStream = getUserInfoStream();
        UserInfo unWrapStream = userInfoStream.unWrapStream();
        UserAddress userAddress = (UserAddress) new Stream(UserAddress.class, requestBody).readBody().getObject();
        if (!(unWrapStream == null || userAddress == null)) {
            unWrapStream.userAddress.copy(userAddress);
        }
        userInfoStream.store(new Gson().toJson((Object) unWrapStream));
        return unWrapStream;
    }

    private static UserInfo updateName(RequestBody requestBody) throws IOException {
        Stream<UserInfo> userInfoStream = getUserInfoStream();
        UserInfo unWrapStream = userInfoStream.unWrapStream();
        UpdateNameModels.UpdateName updateName = (UpdateNameModels.UpdateName) new Stream(UpdateNameModels.UpdateName.class, requestBody).readBody().getObject();
        if (!(unWrapStream == null || updateName == null)) {
            unWrapStream.firstName = updateName.firstName;
            unWrapStream.lastName = updateName.lastName;
            unWrapStream.middleName = updateName.middleName;
        }
        userInfoStream.store(new Gson().toJson((Object) unWrapStream));
        return unWrapStream;
    }

    private static Stream<UserInfo> getUserInfoStream() {
        return new Stream<>(new TypeToken<UserInfo>() {
        }.getType(), Constants.Json.USER_INFO);
    }

    private static Object getData(String str, Request request) throws IOException {
        str.hashCode();
        boolean z = true;
        char c = 65535;
        switch (str.hashCode()) {
            case -1050013908:
                if (str.equals(Constants.Json.HOLIDAY_MODE)) {
                    c = 0;
                    break;
                }
                break;
            case 1010044015:
                if (str.equals(Constants.Json.WEEKLY_TIMER)) {
                    c = 1;
                    break;
                }
                break;
            case 1615913457:
                if (str.equals(Constants.Json.TIMER)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Stream stream = new Stream(new TypeToken<List<HolidayModeModel.HolidayModeResponseData>>() {
                }.getType(), Constants.Json.HOLIDAY_MODE);
                if (stream.unWrapStream() == null) {
                    return new Stream(new TypeToken<Object>() {
                    }.getType(), str).unWrapStream();
                }
                Iterator it = ((List) stream.unWrapStream()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                    } else if (((HolidayModeModel.HolidayModeResponseData) it.next()).scheduleTypes.equalsIgnoreCase(HolidayModeFragment.HolidayMode.HOLIDAY_MODE_ENABLED.name())) {
                    }
                }
                if (!z) {
                    return new ArrayList();
                }
                return new Stream(new TypeToken<Object>() {
                }.getType(), str).unWrapStream();
            case 1:
                Stream stream2 = new Stream(new TypeToken<List<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
                }.getType(), Constants.Json.WEEKLY_TIMER);
                List<String> pathSegments = request.url().pathSegments();
                long parseLong = Long.parseLong(pathSegments.get(pathSegments.size() - 2));
                List<WeeklyTimerModelV2.WeeklyTimerResponseData> list = (List) stream2.unWrapStream();
                for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData : list) {
                    PrintStream printStream = System.out;
                    printStream.println("WEEKLY_TIMER _DATA " + weeklyTimerResponseData.scheduleId + " " + weeklyTimerResponseData.f466id);
                }
                if (list == null || list.size() <= 0) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData2 : list) {
                    if (weeklyTimerResponseData2.f466id == parseLong) {
                        arrayList.add(weeklyTimerResponseData2);
                    }
                }
                return arrayList;
            case 2:
                List<String> pathSegments2 = request.url().pathSegments();
                long parseLong2 = Long.parseLong(pathSegments2.get(pathSegments2.size() - 2));
                List<TimerModels.ResponseData> list2 = (List) new Stream(new TypeToken<List<TimerModels.ResponseData>>() {
                }.getType(), Constants.Json.TIMER).unWrapStream();
                TimerModels.ResponseData responseData = new TimerModels.ResponseData();
                if (list2 != null) {
                    for (TimerModels.ResponseData responseData2 : list2) {
                        if (responseData2.racId == parseLong2) {
                            responseData.f461id = responseData2.f461id;
                            responseData.racId = responseData2.racId;
                            responseData.startsAt = responseData2.startsAt;
                            responseData.endsAt = responseData2.endsAt;
                            responseData.mode = responseData2.mode;
                            responseData.temperature = responseData2.temperature;
                            responseData.power = responseData2.power;
                            return responseData;
                        }
                    }
                }
                return null;
            default:
                return new Stream(new TypeToken<Object>() {
                }.getType(), str).unWrapStream();
        }
    }

    private static Object getData() throws IOException {
        List<WeeklyTimerModelV2.WeeklyTimerResponseData> list = (List) new Stream(new TypeToken<List<WeeklyTimerModelV2.WeeklyTimerResponseData>>() {
        }.getType(), Constants.Json.WEEKLY_TIMER).unWrapStream();
        HashMap hashMap = new HashMap();
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (WeeklyTimerModelV2.WeeklyTimerResponseData weeklyTimerResponseData : list) {
            if (hashMap.containsKey(Long.valueOf(weeklyTimerResponseData.f466id))) {
                hashMap.put(Long.valueOf(weeklyTimerResponseData.f466id), Integer.valueOf(((Integer) hashMap.get(Long.valueOf(weeklyTimerResponseData.f466id))).intValue() + 1));
            } else {
                hashMap.put(Long.valueOf(weeklyTimerResponseData.f466id), 1);
            }
        }
        return hashMap;
    }

    private static String subUrl(String str, HttpUrl httpUrl) {
        String httpUrl2 = httpUrl.toString();
        String replace = httpUrl2.replace(httpUrl.scheme() + "://" + httpUrl.host() + str, "");
        if (replace.contains(Constants.Link.WEATHER_INFO)) {
            return httpUrl.queryParameter(com.amplitude.api.Constants.AMP_TRACKING_OPTION_LANGUAGE).equals("JP") ? "/weather/info?language=JP" : "/weather/info?language=EN";
        }
        if (replace.startsWith(Constants.Link.FAMILY_LIST_1) && replace.endsWith(Constants.Link.MEMBERS)) {
            return "/family-account/v2/groups/members";
        }
        if (replace.contains(Constants.Link.EC_ALL_RAC)) {
            return Constants.Link.EC_ALL_RAC;
        }
        if (replace.contains(Constants.Link.IDU_MODEL_LIST)) {
            return replace.substring(0, 27);
        }
        if (replace.startsWith(Constants.Link.OWNERSHIP_GROUPS) && replace.endsWith(Constants.Link.MEMBERS)) {
            return "/ownership/groups/members";
        }
        if (replace.startsWith(Constants.Link.OWNERSHIP_GROUPS) && replace.endsWith(Constants.Link.FUNCTIONAL_ACCESS_2)) {
            return "/ownership/groups/permissions/has-functional-access";
        }
        if (replace.startsWith(Constants.Link.MANAGE_IDU_GROUPS) && replace.endsWith(Constants.Link.IDU_START)) {
            return "/manage-idu/groups/idu/start";
        }
        if (replace.startsWith(Constants.Link.MANAGE_IDU_GROUPS) && replace.endsWith(Constants.Link.IDU_STOP)) {
            return "/manage-idu/groups/idu/stop";
        }
        if (replace.startsWith(Constants.Link.MANAGE_IDU_GROUPS) && replace.contains(Constants.Link.IDU_LIST)) {
            return "/manage-idu/groups/idu-list/";
        }
        if (replace.startsWith(Constants.Link.OWNERSHIP_GROUPS) && replace.contains(Constants.Link.PERMISSIONS)) {
            return "/ownership/groups/permissions";
        }
        if (replace.startsWith(Constants.Link.INVITE_USER) && replace.contains(Constants.Link.INVITE)) {
            return "/family-account/groups/invite";
        }
        if (replace.startsWith(Constants.Link.WEEKLY_TIMER_SCHEDULE_COUNT)) {
            return Constants.Link.WEEKLY_TIMER_SCHEDULE_COUNT;
        }
        if (replace.startsWith(Constants.Link.WEEKLY_TIMER_1) && replace.contains(Constants.Link.WEEKLY_TIMER_2)) {
            return Constants.Link.WEEKLY_TIMER_OPERATIONS;
        }
        if (replace.startsWith(Constants.Link.TIMER_1) && replace.contains(Constants.Link.WEEKLY_TIMER_2)) {
            return "/scheduled-operations/timer/racs/schedules?familyId=";
        }
        if (replace.startsWith(Constants.Link.OWNERSHIP_GROUPS) && replace.contains(Constants.Link.MEMBERS)) {
            return "/ownership/groups/members";
        }
        if (replace.contains(Constants.Link.IDU_FROST_WASH)) {
            return Constants.Link.IDU_FROST_WASH;
        }
        if (replace.contains(Constants.Link.RAC_USER_MANUAL)) {
            return Constants.Link.RAC_USER_MANUAL;
        }
        if (replace.contains(Constants.Link.CUSTOMER_SUPPORT)) {
            return Constants.Link.CUSTOMER_SUPPORT;
        }
        if (replace.contains(Constants.Link.WEEKLY_TIMER_OPERATIONS)) {
            return Constants.Link.WEEKLY_TIMER_OPERATIONS;
        }
        if (replace.contains(Constants.Link.WEEKLY_TIMER_COPY_SCHEDULE_DAY_WISE)) {
            return Constants.Link.WEEKLY_TIMER_COPY_SCHEDULE_DAY_WISE;
        }
        if (replace.contains(Constants.Link.WEEKLY_TIMER_COPY_SCHEDULE_RAC_WISE)) {
            return Constants.Link.WEEKLY_TIMER_COPY_SCHEDULE_RAC_WISE;
        }
        if (replace.contains(Constants.Link.WEEKLY_TIMER_1)) {
            return Constants.Link.WEEKLY_TIMER_1;
        }
        if (replace.contains(Constants.Link.UPDATE_IDU_STATE_AS_WHOLE)) {
            return Constants.Link.UPDATE_IDU_STATE_AS_WHOLE;
        }
        if (replace.contains(Constants.Link.SMART_FENCE_STATUS)) {
            return Constants.Link.SMART_FENCE_STATUS;
        }
        if (replace.contains(Constants.Link.SMART_FENCE_SETTINGS)) {
            return Constants.Link.SMART_FENCE_SETTINGS;
        }
        if (replace.startsWith(Constants.Link.FAMILY_LIST_1) && replace.contains("members?x=")) {
            return "/family-account/v2/groups/members?x=";
        }
        if (replace.contains("/scheduled-operations/holidayModeSchedule/schedules")) {
            return "/scheduled-operations/holidayModeSchedule/schedules";
        }
        if (replace.contains(Constants.Link.IDU_RENAME)) {
            return Constants.Link.IDU_RENAME;
        }
        if (replace.contains(Constants.Link.SCHEDULE_ENABLE_DISABLE)) {
            return Constants.Link.SCHEDULE_ENABLE_DISABLE;
        }
        if (!replace.contains(Constants.Link.INTERRUPT_HOLIDAY_MODE) && !replace.contains(Constants.Link.INTERRUPT_HOLIDAY_MODE)) {
            return (!replace.startsWith(Constants.Link.STATUS) || !replace.contains("?familyId=")) ? replace : Constants.Link.STATUS;
        }
        return Constants.Link.INTERRUPT_HOLIDAY_MODE;
    }

    private static String prefix(HttpUrl httpUrl) {
        return File.separator + httpUrl.pathSegments().get(0);
    }

    public static StompMessage getStompMessage() throws IOException {
        return new StompMessage("", new ArrayList(), new Gson().toJson((Object) getWebSocketNotificationStream().unWrapStream()));
    }

    private static long geterateUID() {
        return Long.parseLong(String.format("%05d", new Object[]{Integer.valueOf(new SecureRandom().nextInt(100000))}));
    }
}
