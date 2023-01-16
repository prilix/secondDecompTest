package com.jch.racWiFi;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.databinding.AccountCreationSuccessConstarintVdBindingImpl;
import com.jch.racWiFi.databinding.AddDeviceStep1Of5FrameBindingImpl;
import com.jch.racWiFi.databinding.AddDeviceStep1Of5VdBindingImpl;
import com.jch.racWiFi.databinding.BannerBudgetConsumedBindingImpl;
import com.jch.racWiFi.databinding.BannerPlannedMaintenanceBindingImpl;
import com.jch.racWiFi.databinding.BannerRacOfflineBindingImpl;
import com.jch.racWiFi.databinding.BudgetLayoutBindingImpl;
import com.jch.racWiFi.databinding.CustomerCareDevicesBindingImpl;
import com.jch.racWiFi.databinding.CustomerCareVdGlobalBindingImpl;
import com.jch.racWiFi.databinding.DeviceSettingFrameNewBindingImpl;
import com.jch.racWiFi.databinding.DeviceSettingsVdWithSkipBindingImpl;
import com.jch.racWiFi.databinding.DialogAcAlreadyAddedOtherHomeBindingImpl;
import com.jch.racWiFi.databinding.DialogAcAlreadyAddedSameHomeBindingImpl;
import com.jch.racWiFi.databinding.DialogAddressUpdateBindingImpl;
import com.jch.racWiFi.databinding.DialogEnergyCostBillingStructureBindingImpl;
import com.jch.racWiFi.databinding.DialogEnergyCostCurrencyBindingImpl;
import com.jch.racWiFi.databinding.DialogSetModeAndTempHolidaymodeBindingImpl;
import com.jch.racWiFi.databinding.DilogEnergyBudgetBindingImpl;
import com.jch.racWiFi.databinding.DilogEnergyConsuptionCostBindingImpl;
import com.jch.racWiFi.databinding.DirectOnboardMainFrameBindingImpl;
import com.jch.racWiFi.databinding.DirectOnboardMainVdBindingImpl;
import com.jch.racWiFi.databinding.EnableWpsBuiltInStep4Of4FrameBindingImpl;
import com.jch.racWiFi.databinding.EnableWpsBuiltInStep4Of4VdBindingImpl;
import com.jch.racWiFi.databinding.EnterNameFrameWithBubbleBindingImpl;
import com.jch.racWiFi.databinding.EnterNameVdWithBubbleBindingImpl;
import com.jch.racWiFi.databinding.FragCreateAccountStep4BindingImpl;
import com.jch.racWiFi.databinding.FragMyAccountAddressBindingImpl;
import com.jch.racWiFi.databinding.FragementEnergyConsuptionBudgetSetupBindingImpl;
import com.jch.racWiFi.databinding.FragmentApAddRacIndiaBindingImpl;
import com.jch.racWiFi.databinding.FragmentConsumedEnergyGraphBindingImpl;
import com.jch.racWiFi.databinding.FragmentEcHomeBindingImpl;
import com.jch.racWiFi.databinding.FragmentEcSelectAcBindingImpl;
import com.jch.racWiFi.databinding.FragmentEnergyConsumptionBindingImpl;
import com.jch.racWiFi.databinding.FragmentLoginBindingImpl;
import com.jch.racWiFi.databinding.FragmentSelectWpsOrApBindingImpl;
import com.jch.racWiFi.databinding.FragmentServiceRequestBindingImpl;
import com.jch.racWiFi.databinding.FragmentSplashBindingImpl;
import com.jch.racWiFi.databinding.FragmentUnableToScanQrSsidBindingImpl;
import com.jch.racWiFi.databinding.GridViewItemPeakHoursMonthsBindingImpl;
import com.jch.racWiFi.databinding.HelpFrameGlobalAppBindingImpl;
import com.jch.racWiFi.databinding.HelpVdGlobalAppBindingImpl;
import com.jch.racWiFi.databinding.HomePageUpdatedVdBindingImpl;
import com.jch.racWiFi.databinding.ItemFcmAlertBindingImpl;
import com.jch.racWiFi.databinding.ItemFcmReminderBindingImpl;
import com.jch.racWiFi.databinding.ItemFcmSmartFenceBindingImpl;
import com.jch.racWiFi.databinding.LayoutBannerErrorBindingImpl;
import com.jch.racWiFi.databinding.LayoutCleaningAndTrialBindingImpl;
import com.jch.racWiFi.databinding.LayoutCreateAccountStep4BodyBindingImpl;
import com.jch.racWiFi.databinding.LayoutCreateAccountStep4SubBodyBindingImpl;
import com.jch.racWiFi.databinding.LayoutCreateAccountStep4ZipcodeAlertMsgBindingImpl;
import com.jch.racWiFi.databinding.LayoutEcHeaderBindingImpl;
import com.jch.racWiFi.databinding.LayoutEcNoDataBindingImpl;
import com.jch.racWiFi.databinding.LayoutEnergyBudgetBindingImpl;
import com.jch.racWiFi.databinding.LayoutErrorDisplayBindingImpl;
import com.jch.racWiFi.databinding.LayoutGracTooltipBindingImpl;
import com.jch.racWiFi.databinding.LayoutHolidayModeBindingImpl;
import com.jch.racWiFi.databinding.LayoutPeakHoursAndMonthBindingImpl;
import com.jch.racWiFi.databinding.LayoutProgressiveUnitBillBindingImpl;
import com.jch.racWiFi.databinding.LayoutRacListBindingImpl;
import com.jch.racWiFi.databinding.LayoutSelectAcBindingImpl;
import com.jch.racWiFi.databinding.LayoutSimpleFlatRateBindingImpl;
import com.jch.racWiFi.databinding.LayoutStepProgressBarBindingImpl;
import com.jch.racWiFi.databinding.LayoutTimerForResendOtpBindingImpl;
import com.jch.racWiFi.databinding.LocationControlDialogBindingImpl;
import com.jch.racWiFi.databinding.LocationServicesDisabledDialogBindingImpl;
import com.jch.racWiFi.databinding.MainActivityBindingImpl;
import com.jch.racWiFi.databinding.ManageDevicesFrameBindingImpl;
import com.jch.racWiFi.databinding.ManageDevicesVdBindingImpl;
import com.jch.racWiFi.databinding.ManageUsersFrameBindingImpl;
import com.jch.racWiFi.databinding.ManageUsersVdBindingImpl;
import com.jch.racWiFi.databinding.MyAccountPictureFrameBindingImpl;
import com.jch.racWiFi.databinding.MyAccountPictureVdBindingImpl;
import com.jch.racWiFi.databinding.NotificationSectionBindingImpl;
import com.jch.racWiFi.databinding.NotificationsBindingImpl;
import com.jch.racWiFi.databinding.OnboardingStep1Of4IndiaBindingImpl;
import com.jch.racWiFi.databinding.OnboardingStep1Of4IndiaSubLayoutBindingImpl;
import com.jch.racWiFi.databinding.OnboardingStep2Of4IndiaBindingImpl;
import com.jch.racWiFi.databinding.QrCodeScannedFrameBindingImpl;
import com.jch.racWiFi.databinding.QrCodeScannedVdBindingImpl;
import com.jch.racWiFi.databinding.RecycleViewItemsSmartFenceMultipleDevicesBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewAppSettingsBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewItemBillingStructureBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewItemCurrencyBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewItemSmartFenceUserSelectionBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewItemsAddProgressiveUnitBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewItemsFamilyHomeBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewItemsHolidaymodeBindingImpl;
import com.jch.racWiFi.databinding.RecyclerViewItemsNotificationsBindingImpl;
import com.jch.racWiFi.databinding.ScheduleSettingsWeeklyTimerBindingImpl;
import com.jch.racWiFi.databinding.SearchCurrencyBindingImpl;
import com.jch.racWiFi.databinding.SelectAirConditionerTypeSelectionNewBindingImpl;
import com.jch.racWiFi.databinding.SettingsFrameBindingImpl;
import com.jch.racWiFi.databinding.SettingsVdBindingImpl;
import com.jch.racWiFi.databinding.SingleRowEnergyConsumptionBindingImpl;
import com.jch.racWiFi.databinding.SmartFenceArrivingInfoWindowLayoutBindingImpl;
import com.jch.racWiFi.databinding.SmartFenceDialogSetModeTempLocationControlsBindingImpl;
import com.jch.racWiFi.databinding.SmartFenceFragmentDummyBindingImpl;
import com.jch.racWiFi.databinding.SmartFenceGeoFenceSettingsFragmentBindingImpl;
import com.jch.racWiFi.databinding.SmartFenceLeavingInfoWindowLayoutBindingImpl;
import com.jch.racWiFi.databinding.SmartFenceSelectAcsBindingImpl;
import com.jch.racWiFi.databinding.SmartFenceSelectUsersBindingImpl;
import com.jch.racWiFi.databinding.SmartfenceFragmentBindingImpl;
import com.jch.racWiFi.databinding.SocialLoginFacebookLayoutBindingImpl;
import com.jch.racWiFi.databinding.SocialLoginGoogleLayoutBindingImpl;
import com.jch.racWiFi.databinding.SocialLoginLinkedinLayoutBindingImpl;
import com.jch.racWiFi.databinding.SocialLoginTwitterLayoutBindingImpl;
import com.jch.racWiFi.databinding.SocialLoginWechatLayoutBindingImpl;
import com.jch.racWiFi.databinding.ToolbarCreateAccountStep4BindingImpl;
import com.jch.racWiFi.databinding.ToolbarEnergyConsumptionBindingImpl;
import com.jch.racWiFi.databinding.WeeklyTimerCopyFrameBindingImpl;
import com.jch.racWiFi.databinding.WeeklyTimerCopyVdBindingImpl;
import com.jch.racWiFi.databinding.WeeklyTimerGlobalFrameBindingImpl;
import com.jch.racWiFi.databinding.WeeklyTimerGlobalVdBindingImpl;
import com.jch.racWiFi.databinding.WeeklyTimerMainFrameBindingImpl;
import com.jch.racWiFi.databinding.WeeklyTimerMainVdBindingImpl;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACCOUNTCREATIONSUCCESSCONSTARINTVD = 1;
    private static final int LAYOUT_ADDDEVICESTEP1OF5FRAME = 2;
    private static final int LAYOUT_ADDDEVICESTEP1OF5VD = 3;
    private static final int LAYOUT_BANNERBUDGETCONSUMED = 4;
    private static final int LAYOUT_BANNERPLANNEDMAINTENANCE = 5;
    private static final int LAYOUT_BANNERRACOFFLINE = 6;
    private static final int LAYOUT_BUDGETLAYOUT = 7;
    private static final int LAYOUT_CUSTOMERCAREDEVICES = 8;
    private static final int LAYOUT_CUSTOMERCAREVDGLOBAL = 9;
    private static final int LAYOUT_DEVICESETTINGFRAMENEW = 10;
    private static final int LAYOUT_DEVICESETTINGSVDWITHSKIP = 11;
    private static final int LAYOUT_DIALOGACALREADYADDEDOTHERHOME = 12;
    private static final int LAYOUT_DIALOGACALREADYADDEDSAMEHOME = 13;
    private static final int LAYOUT_DIALOGADDRESSUPDATE = 14;
    private static final int LAYOUT_DIALOGENERGYCOSTBILLINGSTRUCTURE = 15;
    private static final int LAYOUT_DIALOGENERGYCOSTCURRENCY = 16;
    private static final int LAYOUT_DIALOGSETMODEANDTEMPHOLIDAYMODE = 17;
    private static final int LAYOUT_DILOGENERGYBUDGET = 18;
    private static final int LAYOUT_DILOGENERGYCONSUPTIONCOST = 19;
    private static final int LAYOUT_DIRECTONBOARDMAINFRAME = 20;
    private static final int LAYOUT_DIRECTONBOARDMAINVD = 21;
    private static final int LAYOUT_ENABLEWPSBUILTINSTEP4OF4FRAME = 22;
    private static final int LAYOUT_ENABLEWPSBUILTINSTEP4OF4VD = 23;
    private static final int LAYOUT_ENTERNAMEFRAMEWITHBUBBLE = 24;
    private static final int LAYOUT_ENTERNAMEVDWITHBUBBLE = 25;
    private static final int LAYOUT_FRAGCREATEACCOUNTSTEP4 = 26;
    private static final int LAYOUT_FRAGEMENTENERGYCONSUPTIONBUDGETSETUP = 28;
    private static final int LAYOUT_FRAGMENTAPADDRACINDIA = 29;
    private static final int LAYOUT_FRAGMENTCONSUMEDENERGYGRAPH = 30;
    private static final int LAYOUT_FRAGMENTECHOME = 31;
    private static final int LAYOUT_FRAGMENTECSELECTAC = 32;
    private static final int LAYOUT_FRAGMENTENERGYCONSUMPTION = 33;
    private static final int LAYOUT_FRAGMENTLOGIN = 34;
    private static final int LAYOUT_FRAGMENTSELECTWPSORAP = 35;
    private static final int LAYOUT_FRAGMENTSERVICEREQUEST = 36;
    private static final int LAYOUT_FRAGMENTSPLASH = 37;
    private static final int LAYOUT_FRAGMENTUNABLETOSCANQRSSID = 38;
    private static final int LAYOUT_FRAGMYACCOUNTADDRESS = 27;
    private static final int LAYOUT_GRIDVIEWITEMPEAKHOURSMONTHS = 39;
    private static final int LAYOUT_HELPFRAMEGLOBALAPP = 40;
    private static final int LAYOUT_HELPVDGLOBALAPP = 41;
    private static final int LAYOUT_HOMEPAGEUPDATEDVD = 42;
    private static final int LAYOUT_ITEMFCMALERT = 43;
    private static final int LAYOUT_ITEMFCMREMINDER = 44;
    private static final int LAYOUT_ITEMFCMSMARTFENCE = 45;
    private static final int LAYOUT_LAYOUTBANNERERROR = 46;
    private static final int LAYOUT_LAYOUTCLEANINGANDTRIAL = 47;
    private static final int LAYOUT_LAYOUTCREATEACCOUNTSTEP4BODY = 48;
    private static final int LAYOUT_LAYOUTCREATEACCOUNTSTEP4SUBBODY = 49;
    private static final int LAYOUT_LAYOUTCREATEACCOUNTSTEP4ZIPCODEALERTMSG = 50;
    private static final int LAYOUT_LAYOUTECHEADER = 51;
    private static final int LAYOUT_LAYOUTECNODATA = 52;
    private static final int LAYOUT_LAYOUTENERGYBUDGET = 53;
    private static final int LAYOUT_LAYOUTERRORDISPLAY = 54;
    private static final int LAYOUT_LAYOUTGRACTOOLTIP = 55;
    private static final int LAYOUT_LAYOUTHOLIDAYMODE = 56;
    private static final int LAYOUT_LAYOUTPEAKHOURSANDMONTH = 57;
    private static final int LAYOUT_LAYOUTPROGRESSIVEUNITBILL = 58;
    private static final int LAYOUT_LAYOUTRACLIST = 59;
    private static final int LAYOUT_LAYOUTSELECTAC = 60;
    private static final int LAYOUT_LAYOUTSIMPLEFLATRATE = 61;
    private static final int LAYOUT_LAYOUTSTEPPROGRESSBAR = 62;
    private static final int LAYOUT_LAYOUTTIMERFORRESENDOTP = 63;
    private static final int LAYOUT_LOCATIONCONTROLDIALOG = 64;
    private static final int LAYOUT_LOCATIONSERVICESDISABLEDDIALOG = 65;
    private static final int LAYOUT_MAINACTIVITY = 66;
    private static final int LAYOUT_MANAGEDEVICESFRAME = 67;
    private static final int LAYOUT_MANAGEDEVICESVD = 68;
    private static final int LAYOUT_MANAGEUSERSFRAME = 69;
    private static final int LAYOUT_MANAGEUSERSVD = 70;
    private static final int LAYOUT_MYACCOUNTPICTUREFRAME = 71;
    private static final int LAYOUT_MYACCOUNTPICTUREVD = 72;
    private static final int LAYOUT_NOTIFICATIONS = 74;
    private static final int LAYOUT_NOTIFICATIONSECTION = 73;
    private static final int LAYOUT_ONBOARDINGSTEP1OF4INDIA = 75;
    private static final int LAYOUT_ONBOARDINGSTEP1OF4INDIASUBLAYOUT = 76;
    private static final int LAYOUT_ONBOARDINGSTEP2OF4INDIA = 77;
    private static final int LAYOUT_QRCODESCANNEDFRAME = 78;
    private static final int LAYOUT_QRCODESCANNEDVD = 79;
    private static final int LAYOUT_RECYCLERVIEWAPPSETTINGS = 81;
    private static final int LAYOUT_RECYCLERVIEWITEMBILLINGSTRUCTURE = 82;
    private static final int LAYOUT_RECYCLERVIEWITEMCURRENCY = 83;
    private static final int LAYOUT_RECYCLERVIEWITEMSADDPROGRESSIVEUNIT = 85;
    private static final int LAYOUT_RECYCLERVIEWITEMSFAMILYHOME = 86;
    private static final int LAYOUT_RECYCLERVIEWITEMSHOLIDAYMODE = 87;
    private static final int LAYOUT_RECYCLERVIEWITEMSMARTFENCEUSERSELECTION = 84;
    private static final int LAYOUT_RECYCLERVIEWITEMSNOTIFICATIONS = 88;
    private static final int LAYOUT_RECYCLEVIEWITEMSSMARTFENCEMULTIPLEDEVICES = 80;
    private static final int LAYOUT_SCHEDULESETTINGSWEEKLYTIMER = 89;
    private static final int LAYOUT_SEARCHCURRENCY = 90;
    private static final int LAYOUT_SELECTAIRCONDITIONERTYPESELECTIONNEW = 91;
    private static final int LAYOUT_SETTINGSFRAME = 92;
    private static final int LAYOUT_SETTINGSVD = 93;
    private static final int LAYOUT_SINGLEROWENERGYCONSUMPTION = 94;
    private static final int LAYOUT_SMARTFENCEARRIVINGINFOWINDOWLAYOUT = 95;
    private static final int LAYOUT_SMARTFENCEDIALOGSETMODETEMPLOCATIONCONTROLS = 96;
    private static final int LAYOUT_SMARTFENCEFRAGMENT = 102;
    private static final int LAYOUT_SMARTFENCEFRAGMENTDUMMY = 97;
    private static final int LAYOUT_SMARTFENCEGEOFENCESETTINGSFRAGMENT = 98;
    private static final int LAYOUT_SMARTFENCELEAVINGINFOWINDOWLAYOUT = 99;
    private static final int LAYOUT_SMARTFENCESELECTACS = 100;
    private static final int LAYOUT_SMARTFENCESELECTUSERS = 101;
    private static final int LAYOUT_SOCIALLOGINFACEBOOKLAYOUT = 103;
    private static final int LAYOUT_SOCIALLOGINGOOGLELAYOUT = 104;
    private static final int LAYOUT_SOCIALLOGINLINKEDINLAYOUT = 105;
    private static final int LAYOUT_SOCIALLOGINTWITTERLAYOUT = 106;
    private static final int LAYOUT_SOCIALLOGINWECHATLAYOUT = 107;
    private static final int LAYOUT_TOOLBARCREATEACCOUNTSTEP4 = 108;
    private static final int LAYOUT_TOOLBARENERGYCONSUMPTION = 109;
    private static final int LAYOUT_WEEKLYTIMERCOPYFRAME = 110;
    private static final int LAYOUT_WEEKLYTIMERCOPYVD = 111;
    private static final int LAYOUT_WEEKLYTIMERGLOBALFRAME = 112;
    private static final int LAYOUT_WEEKLYTIMERGLOBALVD = 113;
    private static final int LAYOUT_WEEKLYTIMERMAINFRAME = 114;
    private static final int LAYOUT_WEEKLYTIMERMAINVD = 115;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(115);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.account_creation_success_constarint_vd, 1);
        sparseIntArray.put(R.layout.add_device_step_1_of_5_frame, 2);
        sparseIntArray.put(R.layout.add_device_step_1_of_5_vd, 3);
        sparseIntArray.put(R.layout.banner_budget_consumed, 4);
        sparseIntArray.put(R.layout.banner_planned_maintenance, 5);
        sparseIntArray.put(R.layout.banner_rac_offline, 6);
        sparseIntArray.put(R.layout.budget_layout, 7);
        sparseIntArray.put(R.layout.customer_care_devices, 8);
        sparseIntArray.put(R.layout.customer_care_vd_global, 9);
        sparseIntArray.put(R.layout.device_setting_frame_new, 10);
        sparseIntArray.put(R.layout.device_settings_vd_with_skip, 11);
        sparseIntArray.put(R.layout.dialog_ac_already_added_other_home, 12);
        sparseIntArray.put(R.layout.dialog_ac_already_added_same_home, 13);
        sparseIntArray.put(R.layout.dialog_address_update, 14);
        sparseIntArray.put(R.layout.dialog_energy_cost_billing_structure, 15);
        sparseIntArray.put(R.layout.dialog_energy_cost_currency, 16);
        sparseIntArray.put(R.layout.dialog_set_mode_and_temp_holidaymode, 17);
        sparseIntArray.put(R.layout.dilog_energy_budget, 18);
        sparseIntArray.put(R.layout.dilog_energy_consuption_cost, 19);
        sparseIntArray.put(R.layout.direct_onboard_main_frame, 20);
        sparseIntArray.put(R.layout.direct_onboard_main_vd, 21);
        sparseIntArray.put(R.layout.enable_wps_built_in_step_4_of_4_frame, 22);
        sparseIntArray.put(R.layout.enable_wps_built_in_step_4_of_4_vd, 23);
        sparseIntArray.put(R.layout.enter_name_frame_with_bubble, 24);
        sparseIntArray.put(R.layout.enter_name_vd_with_bubble, 25);
        sparseIntArray.put(R.layout.frag_create_account_step_4, 26);
        sparseIntArray.put(R.layout.frag_my_account_address, 27);
        sparseIntArray.put(R.layout.fragement_energy_consuption_budget_setup, 28);
        sparseIntArray.put(R.layout.fragment_ap_add_rac_india, 29);
        sparseIntArray.put(R.layout.fragment_consumed_energy_graph, 30);
        sparseIntArray.put(R.layout.fragment_ec_home, 31);
        sparseIntArray.put(R.layout.fragment_ec_select_ac, 32);
        sparseIntArray.put(R.layout.fragment_energy_consumption, 33);
        sparseIntArray.put(R.layout.fragment_login, 34);
        sparseIntArray.put(R.layout.fragment_select_wps_or_ap, 35);
        sparseIntArray.put(R.layout.fragment_service_request, 36);
        sparseIntArray.put(R.layout.fragment_splash, 37);
        sparseIntArray.put(R.layout.fragment_unable_to_scan_qr_ssid, 38);
        sparseIntArray.put(R.layout.grid_view_item_peak_hours_months, 39);
        sparseIntArray.put(R.layout.help_frame_global_app, 40);
        sparseIntArray.put(R.layout.help_vd_global_app, 41);
        sparseIntArray.put(R.layout.home_page_updated_vd, 42);
        sparseIntArray.put(R.layout.item_fcm_alert, 43);
        sparseIntArray.put(R.layout.item_fcm_reminder, 44);
        sparseIntArray.put(R.layout.item_fcm_smart_fence, 45);
        sparseIntArray.put(R.layout.layout_banner_error, 46);
        sparseIntArray.put(R.layout.layout_cleaning_and_trial, 47);
        sparseIntArray.put(R.layout.layout_create_account_step_4_body, 48);
        sparseIntArray.put(R.layout.layout_create_account_step_4_sub_body, 49);
        sparseIntArray.put(R.layout.layout_create_account_step_4_zipcode_alert_msg, 50);
        sparseIntArray.put(R.layout.layout_ec_header, 51);
        sparseIntArray.put(R.layout.layout_ec_no_data, 52);
        sparseIntArray.put(R.layout.layout_energy_budget, 53);
        sparseIntArray.put(R.layout.layout_error_display, 54);
        sparseIntArray.put(R.layout.layout_grac_tooltip, 55);
        sparseIntArray.put(R.layout.layout_holiday_mode, 56);
        sparseIntArray.put(R.layout.layout_peak_hours_and_month, 57);
        sparseIntArray.put(R.layout.layout_progressive_unit_bill, 58);
        sparseIntArray.put(R.layout.layout_rac_list, 59);
        sparseIntArray.put(R.layout.layout_select_ac, 60);
        sparseIntArray.put(R.layout.layout_simple_flat_rate, 61);
        sparseIntArray.put(R.layout.layout_step_progress_bar, 62);
        sparseIntArray.put(R.layout.layout_timer_for_resend_otp, 63);
        sparseIntArray.put(R.layout.location_control_dialog, 64);
        sparseIntArray.put(R.layout.location_services_disabled_dialog, 65);
        sparseIntArray.put(R.layout.main_activity, 66);
        sparseIntArray.put(R.layout.manage_devices_frame, 67);
        sparseIntArray.put(R.layout.manage_devices_vd, 68);
        sparseIntArray.put(R.layout.manage_users_frame, 69);
        sparseIntArray.put(R.layout.manage_users_vd, 70);
        sparseIntArray.put(R.layout.my_account_picture_frame, 71);
        sparseIntArray.put(R.layout.my_account_picture_vd, 72);
        sparseIntArray.put(R.layout.notification_section, 73);
        sparseIntArray.put(R.layout.notifications, 74);
        sparseIntArray.put(R.layout.onboarding_step_1_of_4_india, 75);
        sparseIntArray.put(R.layout.onboarding_step_1_of_4_india_sub_layout, 76);
        sparseIntArray.put(R.layout.onboarding_step_2_of_4_india, 77);
        sparseIntArray.put(R.layout.qr_code_scanned_frame, 78);
        sparseIntArray.put(R.layout.qr_code_scanned_vd, 79);
        sparseIntArray.put(R.layout.recycle_view_items_smart_fence_multiple_devices, 80);
        sparseIntArray.put(R.layout.recycler_view_app_settings, 81);
        sparseIntArray.put(R.layout.recycler_view_item_billing_structure, 82);
        sparseIntArray.put(R.layout.recycler_view_item_currency, 83);
        sparseIntArray.put(R.layout.recycler_view_item_smart_fence_user_selection, 84);
        sparseIntArray.put(R.layout.recycler_view_items_add_progressive_unit, 85);
        sparseIntArray.put(R.layout.recycler_view_items_family_home, 86);
        sparseIntArray.put(R.layout.recycler_view_items_holidaymode, 87);
        sparseIntArray.put(R.layout.recycler_view_items_notifications, 88);
        sparseIntArray.put(R.layout.schedule_settings_weekly_timer, 89);
        sparseIntArray.put(R.layout.search_currency, 90);
        sparseIntArray.put(R.layout.select_air_conditioner_type_selection_new, 91);
        sparseIntArray.put(R.layout.settings_frame, 92);
        sparseIntArray.put(R.layout.settings_vd, 93);
        sparseIntArray.put(R.layout.single_row_energy_consumption, 94);
        sparseIntArray.put(R.layout.smart_fence_arriving_info_window_layout, 95);
        sparseIntArray.put(R.layout.smart_fence_dialog_set_mode_temp_location_controls, 96);
        sparseIntArray.put(R.layout.smart_fence_fragment_dummy, 97);
        sparseIntArray.put(R.layout.smart_fence_geo_fence_settings_fragment, 98);
        sparseIntArray.put(R.layout.smart_fence_leaving_info_window_layout, 99);
        sparseIntArray.put(R.layout.smart_fence_select_acs, 100);
        sparseIntArray.put(R.layout.smart_fence_select_users, 101);
        sparseIntArray.put(R.layout.smartfence_fragment, 102);
        sparseIntArray.put(R.layout.social_login_facebook_layout, 103);
        sparseIntArray.put(R.layout.social_login_google_layout, 104);
        sparseIntArray.put(R.layout.social_login_linkedin_layout, 105);
        sparseIntArray.put(R.layout.social_login_twitter_layout, 106);
        sparseIntArray.put(R.layout.social_login_wechat_layout, 107);
        sparseIntArray.put(R.layout.toolbar_create_account_step_4, 108);
        sparseIntArray.put(R.layout.toolbar_energy_consumption, 109);
        sparseIntArray.put(R.layout.weekly_timer_copy_frame, 110);
        sparseIntArray.put(R.layout.weekly_timer_copy_vd, 111);
        sparseIntArray.put(R.layout.weekly_timer_global_frame, 112);
        sparseIntArray.put(R.layout.weekly_timer_global_vd, 113);
        sparseIntArray.put(R.layout.weekly_timer_main_frame, 114);
        sparseIntArray.put(R.layout.weekly_timer_main_vd, 115);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 1:
                if ("layout/account_creation_success_constarint_vd_0".equals(obj)) {
                    return new AccountCreationSuccessConstarintVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for account_creation_success_constarint_vd is invalid. Received: " + obj);
            case 2:
                if ("layout/add_device_step_1_of_5_frame_0".equals(obj)) {
                    return new AddDeviceStep1Of5FrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for add_device_step_1_of_5_frame is invalid. Received: " + obj);
            case 3:
                if ("layout/add_device_step_1_of_5_vd_0".equals(obj)) {
                    return new AddDeviceStep1Of5VdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for add_device_step_1_of_5_vd is invalid. Received: " + obj);
            case 4:
                if ("layout/banner_budget_consumed_0".equals(obj)) {
                    return new BannerBudgetConsumedBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for banner_budget_consumed is invalid. Received: " + obj);
            case 5:
                if ("layout/banner_planned_maintenance_0".equals(obj)) {
                    return new BannerPlannedMaintenanceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for banner_planned_maintenance is invalid. Received: " + obj);
            case 6:
                if ("layout/banner_rac_offline_0".equals(obj)) {
                    return new BannerRacOfflineBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for banner_rac_offline is invalid. Received: " + obj);
            case 7:
                if ("layout/budget_layout_0".equals(obj)) {
                    return new BudgetLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for budget_layout is invalid. Received: " + obj);
            case 8:
                if ("layout/customer_care_devices_0".equals(obj)) {
                    return new CustomerCareDevicesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for customer_care_devices is invalid. Received: " + obj);
            case 9:
                if ("layout/customer_care_vd_global_0".equals(obj)) {
                    return new CustomerCareVdGlobalBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for customer_care_vd_global is invalid. Received: " + obj);
            case 10:
                if ("layout/device_setting_frame_new_0".equals(obj)) {
                    return new DeviceSettingFrameNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for device_setting_frame_new is invalid. Received: " + obj);
            case 11:
                if ("layout/device_settings_vd_with_skip_0".equals(obj)) {
                    return new DeviceSettingsVdWithSkipBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for device_settings_vd_with_skip is invalid. Received: " + obj);
            case 12:
                if ("layout/dialog_ac_already_added_other_home_0".equals(obj)) {
                    return new DialogAcAlreadyAddedOtherHomeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_ac_already_added_other_home is invalid. Received: " + obj);
            case 13:
                if ("layout/dialog_ac_already_added_same_home_0".equals(obj)) {
                    return new DialogAcAlreadyAddedSameHomeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_ac_already_added_same_home is invalid. Received: " + obj);
            case 14:
                if ("layout/dialog_address_update_0".equals(obj)) {
                    return new DialogAddressUpdateBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_address_update is invalid. Received: " + obj);
            case 15:
                if ("layout/dialog_energy_cost_billing_structure_0".equals(obj)) {
                    return new DialogEnergyCostBillingStructureBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_energy_cost_billing_structure is invalid. Received: " + obj);
            case 16:
                if ("layout/dialog_energy_cost_currency_0".equals(obj)) {
                    return new DialogEnergyCostCurrencyBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_energy_cost_currency is invalid. Received: " + obj);
            case 17:
                if ("layout/dialog_set_mode_and_temp_holidaymode_0".equals(obj)) {
                    return new DialogSetModeAndTempHolidaymodeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_set_mode_and_temp_holidaymode is invalid. Received: " + obj);
            case 18:
                if ("layout/dilog_energy_budget_0".equals(obj)) {
                    return new DilogEnergyBudgetBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dilog_energy_budget is invalid. Received: " + obj);
            case 19:
                if ("layout/dilog_energy_consuption_cost_0".equals(obj)) {
                    return new DilogEnergyConsuptionCostBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dilog_energy_consuption_cost is invalid. Received: " + obj);
            case 20:
                if ("layout/direct_onboard_main_frame_0".equals(obj)) {
                    return new DirectOnboardMainFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for direct_onboard_main_frame is invalid. Received: " + obj);
            case 21:
                if ("layout/direct_onboard_main_vd_0".equals(obj)) {
                    return new DirectOnboardMainVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for direct_onboard_main_vd is invalid. Received: " + obj);
            case 22:
                if ("layout/enable_wps_built_in_step_4_of_4_frame_0".equals(obj)) {
                    return new EnableWpsBuiltInStep4Of4FrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for enable_wps_built_in_step_4_of_4_frame is invalid. Received: " + obj);
            case 23:
                if ("layout/enable_wps_built_in_step_4_of_4_vd_0".equals(obj)) {
                    return new EnableWpsBuiltInStep4Of4VdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for enable_wps_built_in_step_4_of_4_vd is invalid. Received: " + obj);
            case 24:
                if ("layout/enter_name_frame_with_bubble_0".equals(obj)) {
                    return new EnterNameFrameWithBubbleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for enter_name_frame_with_bubble is invalid. Received: " + obj);
            case 25:
                if ("layout/enter_name_vd_with_bubble_0".equals(obj)) {
                    return new EnterNameVdWithBubbleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for enter_name_vd_with_bubble is invalid. Received: " + obj);
            case 26:
                if ("layout/frag_create_account_step_4_0".equals(obj)) {
                    return new FragCreateAccountStep4BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for frag_create_account_step_4 is invalid. Received: " + obj);
            case 27:
                if ("layout/frag_my_account_address_0".equals(obj)) {
                    return new FragMyAccountAddressBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for frag_my_account_address is invalid. Received: " + obj);
            case 28:
                if ("layout/fragement_energy_consuption_budget_setup_0".equals(obj)) {
                    return new FragementEnergyConsuptionBudgetSetupBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragement_energy_consuption_budget_setup is invalid. Received: " + obj);
            case 29:
                if ("layout/fragment_ap_add_rac_india_0".equals(obj)) {
                    return new FragmentApAddRacIndiaBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_ap_add_rac_india is invalid. Received: " + obj);
            case 30:
                if ("layout/fragment_consumed_energy_graph_0".equals(obj)) {
                    return new FragmentConsumedEnergyGraphBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_consumed_energy_graph is invalid. Received: " + obj);
            case 31:
                if ("layout/fragment_ec_home_0".equals(obj)) {
                    return new FragmentEcHomeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_ec_home is invalid. Received: " + obj);
            case 32:
                if ("layout/fragment_ec_select_ac_0".equals(obj)) {
                    return new FragmentEcSelectAcBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_ec_select_ac is invalid. Received: " + obj);
            case 33:
                if ("layout/fragment_energy_consumption_0".equals(obj)) {
                    return new FragmentEnergyConsumptionBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_energy_consumption is invalid. Received: " + obj);
            case 34:
                if ("layout/fragment_login_0".equals(obj)) {
                    return new FragmentLoginBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + obj);
            case 35:
                if ("layout/fragment_select_wps_or_ap_0".equals(obj)) {
                    return new FragmentSelectWpsOrApBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_select_wps_or_ap is invalid. Received: " + obj);
            case 36:
                if ("layout/fragment_service_request_0".equals(obj)) {
                    return new FragmentServiceRequestBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_service_request is invalid. Received: " + obj);
            case 37:
                if ("layout/fragment_splash_0".equals(obj)) {
                    return new FragmentSplashBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_splash is invalid. Received: " + obj);
            case 38:
                if ("layout/fragment_unable_to_scan_qr_ssid_0".equals(obj)) {
                    return new FragmentUnableToScanQrSsidBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_unable_to_scan_qr_ssid is invalid. Received: " + obj);
            case 39:
                if ("layout/grid_view_item_peak_hours_months_0".equals(obj)) {
                    return new GridViewItemPeakHoursMonthsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for grid_view_item_peak_hours_months is invalid. Received: " + obj);
            case 40:
                if ("layout/help_frame_global_app_0".equals(obj)) {
                    return new HelpFrameGlobalAppBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for help_frame_global_app is invalid. Received: " + obj);
            case 41:
                if ("layout/help_vd_global_app_0".equals(obj)) {
                    return new HelpVdGlobalAppBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for help_vd_global_app is invalid. Received: " + obj);
            case 42:
                if ("layout/home_page_updated_vd_0".equals(obj)) {
                    return new HomePageUpdatedVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for home_page_updated_vd is invalid. Received: " + obj);
            case 43:
                if ("layout/item_fcm_alert_0".equals(obj)) {
                    return new ItemFcmAlertBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_fcm_alert is invalid. Received: " + obj);
            case 44:
                if ("layout/item_fcm_reminder_0".equals(obj)) {
                    return new ItemFcmReminderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_fcm_reminder is invalid. Received: " + obj);
            case 45:
                if ("layout/item_fcm_smart_fence_0".equals(obj)) {
                    return new ItemFcmSmartFenceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_fcm_smart_fence is invalid. Received: " + obj);
            case 46:
                if ("layout/layout_banner_error_0".equals(obj)) {
                    return new LayoutBannerErrorBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_banner_error is invalid. Received: " + obj);
            case 47:
                if ("layout/layout_cleaning_and_trial_0".equals(obj)) {
                    return new LayoutCleaningAndTrialBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_cleaning_and_trial is invalid. Received: " + obj);
            case 48:
                if ("layout/layout_create_account_step_4_body_0".equals(obj)) {
                    return new LayoutCreateAccountStep4BodyBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_create_account_step_4_body is invalid. Received: " + obj);
            case 49:
                if ("layout/layout_create_account_step_4_sub_body_0".equals(obj)) {
                    return new LayoutCreateAccountStep4SubBodyBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_create_account_step_4_sub_body is invalid. Received: " + obj);
            case 50:
                if ("layout/layout_create_account_step_4_zipcode_alert_msg_0".equals(obj)) {
                    return new LayoutCreateAccountStep4ZipcodeAlertMsgBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_create_account_step_4_zipcode_alert_msg is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 51:
                if ("layout/layout_ec_header_0".equals(obj)) {
                    return new LayoutEcHeaderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_ec_header is invalid. Received: " + obj);
            case 52:
                if ("layout/layout_ec_no_data_0".equals(obj)) {
                    return new LayoutEcNoDataBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_ec_no_data is invalid. Received: " + obj);
            case 53:
                if ("layout/layout_energy_budget_0".equals(obj)) {
                    return new LayoutEnergyBudgetBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_energy_budget is invalid. Received: " + obj);
            case 54:
                if ("layout/layout_error_display_0".equals(obj)) {
                    return new LayoutErrorDisplayBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_error_display is invalid. Received: " + obj);
            case 55:
                if ("layout/layout_grac_tooltip_0".equals(obj)) {
                    return new LayoutGracTooltipBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_grac_tooltip is invalid. Received: " + obj);
            case 56:
                if ("layout/layout_holiday_mode_0".equals(obj)) {
                    return new LayoutHolidayModeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_holiday_mode is invalid. Received: " + obj);
            case 57:
                if ("layout/layout_peak_hours_and_month_0".equals(obj)) {
                    return new LayoutPeakHoursAndMonthBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_peak_hours_and_month is invalid. Received: " + obj);
            case 58:
                if ("layout/layout_progressive_unit_bill_0".equals(obj)) {
                    return new LayoutProgressiveUnitBillBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_progressive_unit_bill is invalid. Received: " + obj);
            case 59:
                if ("layout/layout_rac_list_0".equals(obj)) {
                    return new LayoutRacListBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_rac_list is invalid. Received: " + obj);
            case 60:
                if ("layout/layout_select_ac_0".equals(obj)) {
                    return new LayoutSelectAcBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_select_ac is invalid. Received: " + obj);
            case 61:
                if ("layout/layout_simple_flat_rate_0".equals(obj)) {
                    return new LayoutSimpleFlatRateBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_simple_flat_rate is invalid. Received: " + obj);
            case 62:
                if ("layout/layout_step_progress_bar_0".equals(obj)) {
                    return new LayoutStepProgressBarBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_step_progress_bar is invalid. Received: " + obj);
            case 63:
                if ("layout/layout_timer_for_resend_otp_0".equals(obj)) {
                    return new LayoutTimerForResendOtpBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_timer_for_resend_otp is invalid. Received: " + obj);
            case 64:
                if ("layout/location_control_dialog_0".equals(obj)) {
                    return new LocationControlDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for location_control_dialog is invalid. Received: " + obj);
            case 65:
                if ("layout/location_services_disabled_dialog_0".equals(obj)) {
                    return new LocationServicesDisabledDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for location_services_disabled_dialog is invalid. Received: " + obj);
            case 66:
                if ("layout/main_activity_0".equals(obj)) {
                    return new MainActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for main_activity is invalid. Received: " + obj);
            case 67:
                if ("layout/manage_devices_frame_0".equals(obj)) {
                    return new ManageDevicesFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for manage_devices_frame is invalid. Received: " + obj);
            case 68:
                if ("layout/manage_devices_vd_0".equals(obj)) {
                    return new ManageDevicesVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for manage_devices_vd is invalid. Received: " + obj);
            case 69:
                if ("layout/manage_users_frame_0".equals(obj)) {
                    return new ManageUsersFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for manage_users_frame is invalid. Received: " + obj);
            case 70:
                if ("layout/manage_users_vd_0".equals(obj)) {
                    return new ManageUsersVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for manage_users_vd is invalid. Received: " + obj);
            case 71:
                if ("layout/my_account_picture_frame_0".equals(obj)) {
                    return new MyAccountPictureFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for my_account_picture_frame is invalid. Received: " + obj);
            case 72:
                if ("layout/my_account_picture_vd_0".equals(obj)) {
                    return new MyAccountPictureVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for my_account_picture_vd is invalid. Received: " + obj);
            case 73:
                if ("layout/notification_section_0".equals(obj)) {
                    return new NotificationSectionBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for notification_section is invalid. Received: " + obj);
            case 74:
                if ("layout/notifications_0".equals(obj)) {
                    return new NotificationsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for notifications is invalid. Received: " + obj);
            case 75:
                if ("layout/onboarding_step_1_of_4_india_0".equals(obj)) {
                    return new OnboardingStep1Of4IndiaBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for onboarding_step_1_of_4_india is invalid. Received: " + obj);
            case 76:
                if ("layout/onboarding_step_1_of_4_india_sub_layout_0".equals(obj)) {
                    return new OnboardingStep1Of4IndiaSubLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for onboarding_step_1_of_4_india_sub_layout is invalid. Received: " + obj);
            case 77:
                if ("layout/onboarding_step_2_of_4_india_0".equals(obj)) {
                    return new OnboardingStep2Of4IndiaBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for onboarding_step_2_of_4_india is invalid. Received: " + obj);
            case 78:
                if ("layout/qr_code_scanned_frame_0".equals(obj)) {
                    return new QrCodeScannedFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for qr_code_scanned_frame is invalid. Received: " + obj);
            case 79:
                if ("layout/qr_code_scanned_vd_0".equals(obj)) {
                    return new QrCodeScannedVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for qr_code_scanned_vd is invalid. Received: " + obj);
            case 80:
                if ("layout/recycle_view_items_smart_fence_multiple_devices_0".equals(obj)) {
                    return new RecycleViewItemsSmartFenceMultipleDevicesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycle_view_items_smart_fence_multiple_devices is invalid. Received: " + obj);
            case 81:
                if ("layout/recycler_view_app_settings_0".equals(obj)) {
                    return new RecyclerViewAppSettingsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_app_settings is invalid. Received: " + obj);
            case 82:
                if ("layout/recycler_view_item_billing_structure_0".equals(obj)) {
                    return new RecyclerViewItemBillingStructureBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_item_billing_structure is invalid. Received: " + obj);
            case 83:
                if ("layout/recycler_view_item_currency_0".equals(obj)) {
                    return new RecyclerViewItemCurrencyBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_item_currency is invalid. Received: " + obj);
            case 84:
                if ("layout/recycler_view_item_smart_fence_user_selection_0".equals(obj)) {
                    return new RecyclerViewItemSmartFenceUserSelectionBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_item_smart_fence_user_selection is invalid. Received: " + obj);
            case 85:
                if ("layout/recycler_view_items_add_progressive_unit_0".equals(obj)) {
                    return new RecyclerViewItemsAddProgressiveUnitBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_items_add_progressive_unit is invalid. Received: " + obj);
            case 86:
                if ("layout/recycler_view_items_family_home_0".equals(obj)) {
                    return new RecyclerViewItemsFamilyHomeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_items_family_home is invalid. Received: " + obj);
            case 87:
                if ("layout/recycler_view_items_holidaymode_0".equals(obj)) {
                    return new RecyclerViewItemsHolidaymodeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_items_holidaymode is invalid. Received: " + obj);
            case 88:
                if ("layout/recycler_view_items_notifications_0".equals(obj)) {
                    return new RecyclerViewItemsNotificationsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for recycler_view_items_notifications is invalid. Received: " + obj);
            case 89:
                if ("layout/schedule_settings_weekly_timer_0".equals(obj)) {
                    return new ScheduleSettingsWeeklyTimerBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for schedule_settings_weekly_timer is invalid. Received: " + obj);
            case 90:
                if ("layout/search_currency_0".equals(obj)) {
                    return new SearchCurrencyBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for search_currency is invalid. Received: " + obj);
            case 91:
                if ("layout/select_air_conditioner_type_selection_new_0".equals(obj)) {
                    return new SelectAirConditionerTypeSelectionNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for select_air_conditioner_type_selection_new is invalid. Received: " + obj);
            case 92:
                if ("layout/settings_frame_0".equals(obj)) {
                    return new SettingsFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for settings_frame is invalid. Received: " + obj);
            case 93:
                if ("layout/settings_vd_0".equals(obj)) {
                    return new SettingsVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for settings_vd is invalid. Received: " + obj);
            case 94:
                if ("layout/single_row_energy_consumption_0".equals(obj)) {
                    return new SingleRowEnergyConsumptionBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for single_row_energy_consumption is invalid. Received: " + obj);
            case 95:
                if ("layout/smart_fence_arriving_info_window_layout_0".equals(obj)) {
                    return new SmartFenceArrivingInfoWindowLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smart_fence_arriving_info_window_layout is invalid. Received: " + obj);
            case 96:
                if ("layout/smart_fence_dialog_set_mode_temp_location_controls_0".equals(obj)) {
                    return new SmartFenceDialogSetModeTempLocationControlsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smart_fence_dialog_set_mode_temp_location_controls is invalid. Received: " + obj);
            case 97:
                if ("layout/smart_fence_fragment_dummy_0".equals(obj)) {
                    return new SmartFenceFragmentDummyBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smart_fence_fragment_dummy is invalid. Received: " + obj);
            case 98:
                if ("layout/smart_fence_geo_fence_settings_fragment_0".equals(obj)) {
                    return new SmartFenceGeoFenceSettingsFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smart_fence_geo_fence_settings_fragment is invalid. Received: " + obj);
            case 99:
                if ("layout/smart_fence_leaving_info_window_layout_0".equals(obj)) {
                    return new SmartFenceLeavingInfoWindowLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smart_fence_leaving_info_window_layout is invalid. Received: " + obj);
            case 100:
                if ("layout/smart_fence_select_acs_0".equals(obj)) {
                    return new SmartFenceSelectAcsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smart_fence_select_acs is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding2(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 101:
                if ("layout/smart_fence_select_users_0".equals(obj)) {
                    return new SmartFenceSelectUsersBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smart_fence_select_users is invalid. Received: " + obj);
            case 102:
                if ("layout/smartfence_fragment_0".equals(obj)) {
                    return new SmartfenceFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for smartfence_fragment is invalid. Received: " + obj);
            case 103:
                if ("layout/social_login_facebook_layout_0".equals(obj)) {
                    return new SocialLoginFacebookLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for social_login_facebook_layout is invalid. Received: " + obj);
            case 104:
                if ("layout/social_login_google_layout_0".equals(obj)) {
                    return new SocialLoginGoogleLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for social_login_google_layout is invalid. Received: " + obj);
            case 105:
                if ("layout/social_login_linkedin_layout_0".equals(obj)) {
                    return new SocialLoginLinkedinLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for social_login_linkedin_layout is invalid. Received: " + obj);
            case 106:
                if ("layout/social_login_twitter_layout_0".equals(obj)) {
                    return new SocialLoginTwitterLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for social_login_twitter_layout is invalid. Received: " + obj);
            case 107:
                if ("layout/social_login_wechat_layout_0".equals(obj)) {
                    return new SocialLoginWechatLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for social_login_wechat_layout is invalid. Received: " + obj);
            case 108:
                if ("layout/toolbar_create_account_step_4_0".equals(obj)) {
                    return new ToolbarCreateAccountStep4BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for toolbar_create_account_step_4 is invalid. Received: " + obj);
            case 109:
                if ("layout/toolbar_energy_consumption_0".equals(obj)) {
                    return new ToolbarEnergyConsumptionBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for toolbar_energy_consumption is invalid. Received: " + obj);
            case 110:
                if ("layout/weekly_timer_copy_frame_0".equals(obj)) {
                    return new WeeklyTimerCopyFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for weekly_timer_copy_frame is invalid. Received: " + obj);
            case 111:
                if ("layout/weekly_timer_copy_vd_0".equals(obj)) {
                    return new WeeklyTimerCopyVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for weekly_timer_copy_vd is invalid. Received: " + obj);
            case 112:
                if ("layout/weekly_timer_global_frame_0".equals(obj)) {
                    return new WeeklyTimerGlobalFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for weekly_timer_global_frame is invalid. Received: " + obj);
            case 113:
                if ("layout/weekly_timer_global_vd_0".equals(obj)) {
                    return new WeeklyTimerGlobalVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for weekly_timer_global_vd is invalid. Received: " + obj);
            case 114:
                if ("layout/weekly_timer_main_frame_0".equals(obj)) {
                    return new WeeklyTimerMainFrameBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for weekly_timer_main_frame is invalid. Received: " + obj);
            case 115:
                if ("layout/weekly_timer_main_vd_0".equals(obj)) {
                    return new WeeklyTimerMainVdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for weekly_timer_main_vd is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            int i3 = (i2 - 1) / 50;
            if (i3 == 0) {
                return internalGetViewDataBinding0(dataBindingComponent, view, i2, tag);
            }
            if (i3 == 1) {
                return internalGetViewDataBinding1(dataBindingComponent, view, i2, tag);
            }
            if (i3 != 2) {
                return null;
            }
            return internalGetViewDataBinding2(dataBindingComponent, view, i2, tag);
        }
        throw new RuntimeException("view must have a tag");
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(12);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "enabled");
            sparseArray.put(2, "endDate");
            sparseArray.put(3, "holidayModeViewModel");
            sparseArray.put(4, "iduList");
            sparseArray.put(5, Constants.FCM.MODE);
            sparseArray.put(6, "selected");
            sparseArray.put(7, Constants.FCM.TEMPERATURE);
            sparseArray.put(8, "weeklyTimerCopyTimerScheduleViewModel");
            sparseArray.put(9, "weeklyTimerScheduleSettingsViewModel");
            sparseArray.put(10, "weeklyTimerSelcetDeviceViewModel");
            sparseArray.put(11, "weeklyTimerViewModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(115);
            sKeys = hashMap;
            hashMap.put("layout/account_creation_success_constarint_vd_0", Integer.valueOf(R.layout.account_creation_success_constarint_vd));
            hashMap.put("layout/add_device_step_1_of_5_frame_0", Integer.valueOf(R.layout.add_device_step_1_of_5_frame));
            hashMap.put("layout/add_device_step_1_of_5_vd_0", Integer.valueOf(R.layout.add_device_step_1_of_5_vd));
            hashMap.put("layout/banner_budget_consumed_0", Integer.valueOf(R.layout.banner_budget_consumed));
            hashMap.put("layout/banner_planned_maintenance_0", Integer.valueOf(R.layout.banner_planned_maintenance));
            hashMap.put("layout/banner_rac_offline_0", Integer.valueOf(R.layout.banner_rac_offline));
            hashMap.put("layout/budget_layout_0", Integer.valueOf(R.layout.budget_layout));
            hashMap.put("layout/customer_care_devices_0", Integer.valueOf(R.layout.customer_care_devices));
            hashMap.put("layout/customer_care_vd_global_0", Integer.valueOf(R.layout.customer_care_vd_global));
            hashMap.put("layout/device_setting_frame_new_0", Integer.valueOf(R.layout.device_setting_frame_new));
            hashMap.put("layout/device_settings_vd_with_skip_0", Integer.valueOf(R.layout.device_settings_vd_with_skip));
            hashMap.put("layout/dialog_ac_already_added_other_home_0", Integer.valueOf(R.layout.dialog_ac_already_added_other_home));
            hashMap.put("layout/dialog_ac_already_added_same_home_0", Integer.valueOf(R.layout.dialog_ac_already_added_same_home));
            hashMap.put("layout/dialog_address_update_0", Integer.valueOf(R.layout.dialog_address_update));
            hashMap.put("layout/dialog_energy_cost_billing_structure_0", Integer.valueOf(R.layout.dialog_energy_cost_billing_structure));
            hashMap.put("layout/dialog_energy_cost_currency_0", Integer.valueOf(R.layout.dialog_energy_cost_currency));
            hashMap.put("layout/dialog_set_mode_and_temp_holidaymode_0", Integer.valueOf(R.layout.dialog_set_mode_and_temp_holidaymode));
            hashMap.put("layout/dilog_energy_budget_0", Integer.valueOf(R.layout.dilog_energy_budget));
            hashMap.put("layout/dilog_energy_consuption_cost_0", Integer.valueOf(R.layout.dilog_energy_consuption_cost));
            hashMap.put("layout/direct_onboard_main_frame_0", Integer.valueOf(R.layout.direct_onboard_main_frame));
            hashMap.put("layout/direct_onboard_main_vd_0", Integer.valueOf(R.layout.direct_onboard_main_vd));
            hashMap.put("layout/enable_wps_built_in_step_4_of_4_frame_0", Integer.valueOf(R.layout.enable_wps_built_in_step_4_of_4_frame));
            hashMap.put("layout/enable_wps_built_in_step_4_of_4_vd_0", Integer.valueOf(R.layout.enable_wps_built_in_step_4_of_4_vd));
            hashMap.put("layout/enter_name_frame_with_bubble_0", Integer.valueOf(R.layout.enter_name_frame_with_bubble));
            hashMap.put("layout/enter_name_vd_with_bubble_0", Integer.valueOf(R.layout.enter_name_vd_with_bubble));
            hashMap.put("layout/frag_create_account_step_4_0", Integer.valueOf(R.layout.frag_create_account_step_4));
            hashMap.put("layout/frag_my_account_address_0", Integer.valueOf(R.layout.frag_my_account_address));
            hashMap.put("layout/fragement_energy_consuption_budget_setup_0", Integer.valueOf(R.layout.fragement_energy_consuption_budget_setup));
            hashMap.put("layout/fragment_ap_add_rac_india_0", Integer.valueOf(R.layout.fragment_ap_add_rac_india));
            hashMap.put("layout/fragment_consumed_energy_graph_0", Integer.valueOf(R.layout.fragment_consumed_energy_graph));
            hashMap.put("layout/fragment_ec_home_0", Integer.valueOf(R.layout.fragment_ec_home));
            hashMap.put("layout/fragment_ec_select_ac_0", Integer.valueOf(R.layout.fragment_ec_select_ac));
            hashMap.put("layout/fragment_energy_consumption_0", Integer.valueOf(R.layout.fragment_energy_consumption));
            hashMap.put("layout/fragment_login_0", Integer.valueOf(R.layout.fragment_login));
            hashMap.put("layout/fragment_select_wps_or_ap_0", Integer.valueOf(R.layout.fragment_select_wps_or_ap));
            hashMap.put("layout/fragment_service_request_0", Integer.valueOf(R.layout.fragment_service_request));
            hashMap.put("layout/fragment_splash_0", Integer.valueOf(R.layout.fragment_splash));
            hashMap.put("layout/fragment_unable_to_scan_qr_ssid_0", Integer.valueOf(R.layout.fragment_unable_to_scan_qr_ssid));
            hashMap.put("layout/grid_view_item_peak_hours_months_0", Integer.valueOf(R.layout.grid_view_item_peak_hours_months));
            hashMap.put("layout/help_frame_global_app_0", Integer.valueOf(R.layout.help_frame_global_app));
            hashMap.put("layout/help_vd_global_app_0", Integer.valueOf(R.layout.help_vd_global_app));
            hashMap.put("layout/home_page_updated_vd_0", Integer.valueOf(R.layout.home_page_updated_vd));
            hashMap.put("layout/item_fcm_alert_0", Integer.valueOf(R.layout.item_fcm_alert));
            hashMap.put("layout/item_fcm_reminder_0", Integer.valueOf(R.layout.item_fcm_reminder));
            hashMap.put("layout/item_fcm_smart_fence_0", Integer.valueOf(R.layout.item_fcm_smart_fence));
            hashMap.put("layout/layout_banner_error_0", Integer.valueOf(R.layout.layout_banner_error));
            hashMap.put("layout/layout_cleaning_and_trial_0", Integer.valueOf(R.layout.layout_cleaning_and_trial));
            hashMap.put("layout/layout_create_account_step_4_body_0", Integer.valueOf(R.layout.layout_create_account_step_4_body));
            hashMap.put("layout/layout_create_account_step_4_sub_body_0", Integer.valueOf(R.layout.layout_create_account_step_4_sub_body));
            hashMap.put("layout/layout_create_account_step_4_zipcode_alert_msg_0", Integer.valueOf(R.layout.layout_create_account_step_4_zipcode_alert_msg));
            hashMap.put("layout/layout_ec_header_0", Integer.valueOf(R.layout.layout_ec_header));
            hashMap.put("layout/layout_ec_no_data_0", Integer.valueOf(R.layout.layout_ec_no_data));
            hashMap.put("layout/layout_energy_budget_0", Integer.valueOf(R.layout.layout_energy_budget));
            hashMap.put("layout/layout_error_display_0", Integer.valueOf(R.layout.layout_error_display));
            hashMap.put("layout/layout_grac_tooltip_0", Integer.valueOf(R.layout.layout_grac_tooltip));
            hashMap.put("layout/layout_holiday_mode_0", Integer.valueOf(R.layout.layout_holiday_mode));
            hashMap.put("layout/layout_peak_hours_and_month_0", Integer.valueOf(R.layout.layout_peak_hours_and_month));
            hashMap.put("layout/layout_progressive_unit_bill_0", Integer.valueOf(R.layout.layout_progressive_unit_bill));
            hashMap.put("layout/layout_rac_list_0", Integer.valueOf(R.layout.layout_rac_list));
            hashMap.put("layout/layout_select_ac_0", Integer.valueOf(R.layout.layout_select_ac));
            hashMap.put("layout/layout_simple_flat_rate_0", Integer.valueOf(R.layout.layout_simple_flat_rate));
            hashMap.put("layout/layout_step_progress_bar_0", Integer.valueOf(R.layout.layout_step_progress_bar));
            hashMap.put("layout/layout_timer_for_resend_otp_0", Integer.valueOf(R.layout.layout_timer_for_resend_otp));
            hashMap.put("layout/location_control_dialog_0", Integer.valueOf(R.layout.location_control_dialog));
            hashMap.put("layout/location_services_disabled_dialog_0", Integer.valueOf(R.layout.location_services_disabled_dialog));
            hashMap.put("layout/main_activity_0", Integer.valueOf(R.layout.main_activity));
            hashMap.put("layout/manage_devices_frame_0", Integer.valueOf(R.layout.manage_devices_frame));
            hashMap.put("layout/manage_devices_vd_0", Integer.valueOf(R.layout.manage_devices_vd));
            hashMap.put("layout/manage_users_frame_0", Integer.valueOf(R.layout.manage_users_frame));
            hashMap.put("layout/manage_users_vd_0", Integer.valueOf(R.layout.manage_users_vd));
            hashMap.put("layout/my_account_picture_frame_0", Integer.valueOf(R.layout.my_account_picture_frame));
            hashMap.put("layout/my_account_picture_vd_0", Integer.valueOf(R.layout.my_account_picture_vd));
            hashMap.put("layout/notification_section_0", Integer.valueOf(R.layout.notification_section));
            hashMap.put("layout/notifications_0", Integer.valueOf(R.layout.notifications));
            hashMap.put("layout/onboarding_step_1_of_4_india_0", Integer.valueOf(R.layout.onboarding_step_1_of_4_india));
            hashMap.put("layout/onboarding_step_1_of_4_india_sub_layout_0", Integer.valueOf(R.layout.onboarding_step_1_of_4_india_sub_layout));
            hashMap.put("layout/onboarding_step_2_of_4_india_0", Integer.valueOf(R.layout.onboarding_step_2_of_4_india));
            hashMap.put("layout/qr_code_scanned_frame_0", Integer.valueOf(R.layout.qr_code_scanned_frame));
            hashMap.put("layout/qr_code_scanned_vd_0", Integer.valueOf(R.layout.qr_code_scanned_vd));
            hashMap.put("layout/recycle_view_items_smart_fence_multiple_devices_0", Integer.valueOf(R.layout.recycle_view_items_smart_fence_multiple_devices));
            hashMap.put("layout/recycler_view_app_settings_0", Integer.valueOf(R.layout.recycler_view_app_settings));
            hashMap.put("layout/recycler_view_item_billing_structure_0", Integer.valueOf(R.layout.recycler_view_item_billing_structure));
            hashMap.put("layout/recycler_view_item_currency_0", Integer.valueOf(R.layout.recycler_view_item_currency));
            hashMap.put("layout/recycler_view_item_smart_fence_user_selection_0", Integer.valueOf(R.layout.recycler_view_item_smart_fence_user_selection));
            hashMap.put("layout/recycler_view_items_add_progressive_unit_0", Integer.valueOf(R.layout.recycler_view_items_add_progressive_unit));
            hashMap.put("layout/recycler_view_items_family_home_0", Integer.valueOf(R.layout.recycler_view_items_family_home));
            hashMap.put("layout/recycler_view_items_holidaymode_0", Integer.valueOf(R.layout.recycler_view_items_holidaymode));
            hashMap.put("layout/recycler_view_items_notifications_0", Integer.valueOf(R.layout.recycler_view_items_notifications));
            hashMap.put("layout/schedule_settings_weekly_timer_0", Integer.valueOf(R.layout.schedule_settings_weekly_timer));
            hashMap.put("layout/search_currency_0", Integer.valueOf(R.layout.search_currency));
            hashMap.put("layout/select_air_conditioner_type_selection_new_0", Integer.valueOf(R.layout.select_air_conditioner_type_selection_new));
            hashMap.put("layout/settings_frame_0", Integer.valueOf(R.layout.settings_frame));
            hashMap.put("layout/settings_vd_0", Integer.valueOf(R.layout.settings_vd));
            hashMap.put("layout/single_row_energy_consumption_0", Integer.valueOf(R.layout.single_row_energy_consumption));
            hashMap.put("layout/smart_fence_arriving_info_window_layout_0", Integer.valueOf(R.layout.smart_fence_arriving_info_window_layout));
            hashMap.put("layout/smart_fence_dialog_set_mode_temp_location_controls_0", Integer.valueOf(R.layout.smart_fence_dialog_set_mode_temp_location_controls));
            hashMap.put("layout/smart_fence_fragment_dummy_0", Integer.valueOf(R.layout.smart_fence_fragment_dummy));
            hashMap.put("layout/smart_fence_geo_fence_settings_fragment_0", Integer.valueOf(R.layout.smart_fence_geo_fence_settings_fragment));
            hashMap.put("layout/smart_fence_leaving_info_window_layout_0", Integer.valueOf(R.layout.smart_fence_leaving_info_window_layout));
            hashMap.put("layout/smart_fence_select_acs_0", Integer.valueOf(R.layout.smart_fence_select_acs));
            hashMap.put("layout/smart_fence_select_users_0", Integer.valueOf(R.layout.smart_fence_select_users));
            hashMap.put("layout/smartfence_fragment_0", Integer.valueOf(R.layout.smartfence_fragment));
            hashMap.put("layout/social_login_facebook_layout_0", Integer.valueOf(R.layout.social_login_facebook_layout));
            hashMap.put("layout/social_login_google_layout_0", Integer.valueOf(R.layout.social_login_google_layout));
            hashMap.put("layout/social_login_linkedin_layout_0", Integer.valueOf(R.layout.social_login_linkedin_layout));
            hashMap.put("layout/social_login_twitter_layout_0", Integer.valueOf(R.layout.social_login_twitter_layout));
            hashMap.put("layout/social_login_wechat_layout_0", Integer.valueOf(R.layout.social_login_wechat_layout));
            hashMap.put("layout/toolbar_create_account_step_4_0", Integer.valueOf(R.layout.toolbar_create_account_step_4));
            hashMap.put("layout/toolbar_energy_consumption_0", Integer.valueOf(R.layout.toolbar_energy_consumption));
            hashMap.put("layout/weekly_timer_copy_frame_0", Integer.valueOf(R.layout.weekly_timer_copy_frame));
            hashMap.put("layout/weekly_timer_copy_vd_0", Integer.valueOf(R.layout.weekly_timer_copy_vd));
            hashMap.put("layout/weekly_timer_global_frame_0", Integer.valueOf(R.layout.weekly_timer_global_frame));
            hashMap.put("layout/weekly_timer_global_vd_0", Integer.valueOf(R.layout.weekly_timer_global_vd));
            hashMap.put("layout/weekly_timer_main_frame_0", Integer.valueOf(R.layout.weekly_timer_main_frame));
            hashMap.put("layout/weekly_timer_main_vd_0", Integer.valueOf(R.layout.weekly_timer_main_vd));
        }
    }
}
