package com.jch.racWiFi.iduManagement.view.viewImpl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.HelpWebPageModel;
import com.jch.racWiFi.Listeners.RepeatListener;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.TemperatureValueFormatter;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SavingBehaviourDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.BannerRacOfflineBinding;
import com.jch.racWiFi.databinding.LayoutErrorDisplayBinding;
import com.jch.racWiFi.fcm.model.AcActivity;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.model.Banner;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.FcmResponse;
import com.jch.racWiFi.fcm.standard.BannerListener;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.standard.HandlerListener;
import com.jch.racWiFi.fcm.util.AcActivitiesList;
import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.fcm.util.DeepLink;
import com.jch.racWiFi.fcm.view_model.FcmViewModel;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.NotificationPopupWrappers.NotificationPopupWrapper;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.model.CleaningModeEnum;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;
import com.jch.racWiFi.iduManagement.model.IDUNotificationType;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.OperationModeUIConfigration;
import com.jch.racWiFi.iduManagement.model.Power;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.TimerModels;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.presenter.IndividualIDUControlPresenterV2;
import com.jch.racWiFi.iduManagement.viewModel.HolidayModeViewModel;
import com.jch.racWiFi.iduManagement.viewModel.TimerViewModel;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyGroupsModels;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.model.dataProvider.PermissionFactory;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.presenter.GetFamilyGroupPresenter;
import com.jch.racWiFi.userManagement.presenter.PermissionPresenter;
import com.jch.racWiFi.userManagement.presenter.presenterImpl.PermissionPresenterImpl;
import com.jch.racWiFi.userManagement.view.IDevicePermissionView;
import com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment;
import com.jch.racWiFi.weather.WeatherIconsFactory;
import com.jch.racWiFi.weather.model.Weather;
import com.jch.racWiFi.weather.model.WeatherDataModel;
import com.jch.racWiFi.weather.model.WeatherDataPresenter;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import net.cachapa.expandablelayout.ExpandableLayout;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class IndividualIDUControlFragmentModelWise extends AbstractIndividualIDUControlFragmentModelWise implements IDevicePermissionView, GetFamilyGroupPresenter.IGetFamilyGroupPresenterInterface, WeatherDataPresenter.IWeatherDataPresenter {
    private static final String TAG = "IndividualIDUControlFra";
    private final Observer<AcActivitiesList> acActivitiesListObserver = new Observer<AcActivitiesList>() {
        public void onChanged(AcActivitiesList acActivitiesList) {
            Iterator it = acActivitiesList.iterator();
            while (it.hasNext()) {
                IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.equals(Integer.valueOf(((AcActivity) it.next()).getRacId().intValue()));
            }
            if (IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isOnline()) {
                IndividualIDUControlFragmentModelWise.this.mParentOuter.removeAllViews();
            }
        }
    };
    /* access modifiers changed from: private */
    public float disabledAlfa = 0.4f;
    /* access modifiers changed from: private */
    public float enabledAlfa = 1.0f;
    /* access modifiers changed from: private */
    public FanSpeedOptionsViewHolder fanSpeedOptionsViewHolder;
    /* access modifiers changed from: private */
    public GridOptionsViewHolder gridOptionsViewHolder;
    /* access modifiers changed from: private */
    public boolean holdBackButton = false;
    /* access modifiers changed from: private */
    public HolidayModeViewHolder holidayModeViewHolder;
    private final Observer<DetailedIduModel> individualDetailedIduModelObserver = new Observer<DetailedIduModel>() {
        public void onChanged(DetailedIduModel detailedIduModel) {
            if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && detailedIduModel.equals(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel)) {
                if (detailedIduModel.getRequestTypeEnum().isBucketUpdate()) {
                    IndividualIDUControlFragmentModelWise.this.mCommandDoneStatusRunnable.decrementCommandQueueCount();
                }
                IndividualIDUControlFragmentModelWise.this.mDetailedIduModelPrevState.copy(detailedIduModel);
                IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.copy(detailedIduModel);
                IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise = IndividualIDUControlFragmentModelWise.this;
                individualIDUControlFragmentModelWise.updateUIMain(individualIDUControlFragmentModelWise.mDetailedIduModel);
                if (!IndividualIDUControlFragmentModelWise.this.mCommandDoneStatusRunnable.isCommandsInQueue() && IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder != null && !IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.isTemperatureChangeInProcess) {
                    IndividualIDUControlFragmentModelWise.this.dipDisplay();
                    IndividualIDUControlFragmentModelWise.this.stopSwipeRefresh();
                    IndividualIDUControlFragmentModelWise.this.stopCommandStatusDoneHandler();
                    if (IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.didModeChanged(detailedIduModel)) {
                        IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.humiditySelected = false;
                    }
                    IndividualIDUControlFragmentModelWise.this.mDetailedIduModelPrevState.copy(detailedIduModel);
                    IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.copy(detailedIduModel);
                    IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise2 = IndividualIDUControlFragmentModelWise.this;
                    individualIDUControlFragmentModelWise2.updateUIMain(individualIDUControlFragmentModelWise2.mDetailedIduModel);
                    IndividualIDUControlFragmentModelWise.this.handleError();
                    if (IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder != null) {
                        IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.racStateUpdateAvailable();
                        IndividualIDUControlFragmentModelWise.this.stopSwipeRefresh();
                    }
                }
            }
        }
    };
    boolean isErrorShown = false;
    /* access modifiers changed from: private */
    public boolean isVisible = false;
    /* access modifiers changed from: private */
    public LayoutTopViewHolder layoutTopViewHolder;
    private AdvancedOptionViewHolder mAdvancedOptionViewHolder;
    private Handler mAfterCommandDoneStatusHandler = new Handler();
    @Inject
    Binder mBinder;
    /* access modifiers changed from: private */
    public CommandDoneStatusRunnable mCommandDoneStatusRunnable = new CommandDoneStatusRunnable();
    /* access modifiers changed from: private */
    public WeatherDataModel.WeatherDataModelResponseSuccess mCurrentWeatherInfo = new WeatherDataModel.WeatherDataModelResponseSuccess();
    /* access modifiers changed from: private */
    public DetailedIduModel mDetailedIduModel = new DetailedIduModel();
    /* access modifiers changed from: private */
    public DetailedIduModel mDetailedIduModelPrevState = new DetailedIduModel();
    private Handler mDimScreenOperationHandler = new Handler();
    private FcmViewModel mFcmViewModel;
    /* access modifiers changed from: private */
    public GetFamilyGroupPresenter mGetFamilyGroupPresenter;
    /* access modifiers changed from: private */
    public final HandlerListener<FrameLayout> mHandlerListener = IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda8.INSTANCE;
    /* access modifiers changed from: private */
    public IndividualIDUControlPresenterV2 mIndividualIDUControlPresenter;
    /* access modifiers changed from: private */
    public NotificationPopupWrapper mNotificationPopupWrapper = new NotificationPopupWrapper();
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131363255)
    FrameLayout mParentOuter;
    @BindView(2131363535)
    ConstraintLayout mParentTop;
    /* access modifiers changed from: private */
    public RacModelWiseData mRacModelWiseData;
    private HashMap<String, Boolean> mRacWisePermissionMap;
    /* access modifiers changed from: private */
    public SingleChoiceDialog mSimpleAlertDialog;
    private final Observer<MotionEvent> mTouchPointerEventObserver = new Observer<MotionEvent>() {
        public void onChanged(MotionEvent motionEvent) {
            Logger.m47e("TouchEvents", String.valueOf(motionEvent.getPointerCount()));
            if (motionEvent.getPointerCount() > 1 && IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder != null) {
                IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.mIncreaseTemperatureRepeatListener.cleanUp();
                IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.mDecreaseTemperatureRepeatListener.cleanUp();
            }
            if (IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.isExpanded() && !IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.touchedOnFanLayout(motionEvent)) {
                IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.collapseFan();
            }
            if (IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.isExpanded() && !IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.touchedOnSwingLayout(motionEvent)) {
                IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.collapseSwing();
            }
        }
    };
    private Unbinder mUnbinder;
    @BindView(2131364885)
    View mViewPopUp;
    private MainSwitchViewHolder mainSwitchViewHolder;
    /* access modifiers changed from: private */
    public OffStateViewHolder offStateViewHolder;
    private boolean offlinePopup = true;
    private final PermissionPresenter permissionPresenter = new PermissionPresenterImpl(this);
    UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel;
    @Inject
    ViewModelProviderFactory providerFactory;
    @BindView(2131363555)
    SwipeRefreshLayout pullToRefresh;
    private final Observer<WebSocketNotification.RequestType> requestTypeObserver = new Observer<WebSocketNotification.RequestType>() {
        public void onChanged(WebSocketNotification.RequestType requestType) {
            IndividualIDUControlFragmentModelWise.this.handleError();
        }
    };
    /* access modifiers changed from: private */
    public SavingBehaviourDialog savingBehaviourDialog;
    /* access modifiers changed from: private */
    public SelectModesViewHolder selectModesViewHolder;
    private SingleChoiceDialog showAlertDialog;
    /* access modifiers changed from: private */
    public SwingOptionsViewHolder swingOptionsViewHolder;
    /* access modifiers changed from: private */
    public TemperatureLayoutViewHolder temperatureLayoutViewHolder;
    /* access modifiers changed from: private */
    public boolean updatingUI = false;
    /* access modifiers changed from: private */
    public Weather weather;
    private WeatherDataPresenter weatherDataPresenter;

    static /* synthetic */ boolean lambda$showAlert$15(Dialog dialog, View view) {
        return true;
    }

    public void onAllCheckedStatusEvaluated(Boolean[] boolArr) {
    }

    public void savePermissionResponseDatas(Response<ResponseBody> response) {
    }

    public class HolidayModeViewHolder_ViewBinding implements Unbinder {
        private HolidayModeViewHolder target;

        public HolidayModeViewHolder_ViewBinding(HolidayModeViewHolder holidayModeViewHolder, View view) {
            this.target = holidayModeViewHolder;
            holidayModeViewHolder.mTextViewHolidayMode = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_in_holiday_mode, "field 'mTextViewHolidayMode'", TextView.class);
        }

        public void unbind() {
            HolidayModeViewHolder holidayModeViewHolder = this.target;
            if (holidayModeViewHolder != null) {
                this.target = null;
                holidayModeViewHolder.mTextViewHolidayMode = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class OffStateViewHolder_ViewBinding implements Unbinder {
        private OffStateViewHolder target;

        public OffStateViewHolder_ViewBinding(OffStateViewHolder offStateViewHolder, View view) {
            this.target = offStateViewHolder;
            offStateViewHolder.mTextViewIduIsOff = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_idu_is_off, "field 'mTextViewIduIsOff'", TextView.class);
        }

        public void unbind() {
            OffStateViewHolder offStateViewHolder = this.target;
            if (offStateViewHolder != null) {
                this.target = null;
                offStateViewHolder.mTextViewIduIsOff = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class GridOptionsViewHolder_ViewBinding implements Unbinder {
        private GridOptionsViewHolder target;

        public GridOptionsViewHolder_ViewBinding(GridOptionsViewHolder gridOptionsViewHolder, View view) {
            this.target = gridOptionsViewHolder;
            gridOptionsViewHolder.mOptionRecyclerView = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.recycler_view_options, "field 'mOptionRecyclerView'", RecyclerView.class);
            gridOptionsViewHolder.mBottomLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.bottom_layout, "field 'mBottomLayout'", ConstraintLayout.class);
        }

        public void unbind() {
            GridOptionsViewHolder gridOptionsViewHolder = this.target;
            if (gridOptionsViewHolder != null) {
                this.target = null;
                gridOptionsViewHolder.mOptionRecyclerView = null;
                gridOptionsViewHolder.mBottomLayout = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class MainSwitchViewHolder_ViewBinding implements Unbinder {
        private MainSwitchViewHolder target;

        public MainSwitchViewHolder_ViewBinding(MainSwitchViewHolder mainSwitchViewHolder, View view) {
            this.target = mainSwitchViewHolder;
            mainSwitchViewHolder.mSwitchMain = (CustomSwitchButton) C0840Utils.findRequiredViewAsType(view, R.id.switch_main_home, "field 'mSwitchMain'", CustomSwitchButton.class);
            mainSwitchViewHolder.mOff = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_off, "field 'mOff'", TextView.class);
            mainSwitchViewHolder.mOn = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_on, "field 'mOn'", TextView.class);
        }

        public void unbind() {
            MainSwitchViewHolder mainSwitchViewHolder = this.target;
            if (mainSwitchViewHolder != null) {
                this.target = null;
                mainSwitchViewHolder.mSwitchMain = null;
                mainSwitchViewHolder.mOff = null;
                mainSwitchViewHolder.mOn = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    class AdvancedOptionViewHolder implements Unbinder {
        private AdvanceControlRecyclerViewAdapter advanceControlRecyclerViewAdapter;
        public boolean advanceOptionsShown = false;
        @BindView(2131363124)
        LinearLayout mAdvancedControls;
        @BindView(2131363597)
        RecyclerView mAdvancedOptionsRecyclerView;
        private CleaningModeRecyclerViewAdapter mCleaningModeRecyclerViewAdapter;
        @BindView(2131362839)
        ImageView mImageViewAdvancedControls;
        @BindView(2131363138)
        ConstraintLayout mLayoutBottom;
        private List<OperationModeItem> menuItemsAdvancedCleaningMode = new ArrayList();
        private List<OperationModeItem> menuItemsAdvancedControls = new ArrayList();
        Unbinder unbinder;

        static /* synthetic */ boolean lambda$populateMenuItemsAdvancedControls$0(Dialog dialog, View view) {
            return true;
        }

        static /* synthetic */ boolean lambda$populateMenuItemsCleaningModes$3(Dialog dialog, View view) {
            return true;
        }

        class CleaningModeRecyclerViewAdapter extends RecyclerView.Adapter<CleaningModeViewHolder> {
            private Context context;
            private List<OperationModeItem> menuItemList;

            public class CleaningModeViewHolder_ViewBinding implements Unbinder {
                private CleaningModeViewHolder target;
                private View view7f0a0551;

                public CleaningModeViewHolder_ViewBinding(final CleaningModeViewHolder cleaningModeViewHolder, View view) {
                    this.target = cleaningModeViewHolder;
                    cleaningModeViewHolder.mMenuImageCleaningMode = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_cleaning_mode_recycler_view, "field 'mMenuImageCleaningMode'", ImageView.class);
                    cleaningModeViewHolder.mMenuInfoCleaningMode = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_cleaning_mode_recycler_view, "field 'mMenuInfoCleaningMode'", TextView.class);
                    View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_cleaning_mode_item, "field 'mOuterLayout' and method 'onClickItem'");
                    cleaningModeViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_cleaning_mode_item, "field 'mOuterLayout'", ConstraintLayout.class);
                    this.view7f0a0551 = findRequiredView;
                    findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                        public void doClick(View view) {
                            cleaningModeViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                        }
                    });
                }

                public void unbind() {
                    CleaningModeViewHolder cleaningModeViewHolder = this.target;
                    if (cleaningModeViewHolder != null) {
                        this.target = null;
                        cleaningModeViewHolder.mMenuImageCleaningMode = null;
                        cleaningModeViewHolder.mMenuInfoCleaningMode = null;
                        cleaningModeViewHolder.mOuterLayout = null;
                        this.view7f0a0551.setOnClickListener((View.OnClickListener) null);
                        this.view7f0a0551 = null;
                        return;
                    }
                    throw new IllegalStateException("Bindings already cleared.");
                }
            }

            public CleaningModeRecyclerViewAdapter(Context context2, List<OperationModeItem> list) {
                this.context = context2;
                this.menuItemList = list;
            }

            public CleaningModeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new CleaningModeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_items_view_cleaning_mode, viewGroup, false));
            }

            public void onBindViewHolder(CleaningModeViewHolder cleaningModeViewHolder, int i) {
                cleaningModeViewHolder.bind(this.menuItemList.get(i));
            }

            public int getItemCount() {
                return this.menuItemList.size();
            }

            class CleaningModeViewHolder extends RecyclerView.ViewHolder {
                @BindView(2131362865)
                ImageView mMenuImageCleaningMode;
                @BindView(2131364006)
                TextView mMenuInfoCleaningMode;
                @BindView(2131363153)
                ConstraintLayout mOuterLayout;

                public CleaningModeViewHolder(View view) {
                    super(view);
                    ButterKnife.bind((Object) this, view);
                }

                @OnClick({2131363153})
                public void onClickItem(ConstraintLayout constraintLayout) {
                    ((OperationModeItem) constraintLayout.getTag()).onClickListener.onClick(constraintLayout);
                }

                public void bind(OperationModeItem operationModeItem) {
                    this.mMenuImageCleaningMode.setImageResource(operationModeItem.getImageMode());
                    this.mMenuInfoCleaningMode.setText(operationModeItem.getTextMode());
                    this.mOuterLayout.setTag(operationModeItem);
                    float f = 1.0f;
                    this.mMenuImageCleaningMode.setAlpha(operationModeItem.enabled ? 1.0f : 0.5f);
                    TextView textView = this.mMenuInfoCleaningMode;
                    if (!operationModeItem.enabled) {
                        f = 0.5f;
                    }
                    textView.setAlpha(f);
                    this.mOuterLayout.setEnabled(operationModeItem.enabled);
                    this.mMenuImageCleaningMode.setEnabled(operationModeItem.enabled);
                    this.mMenuInfoCleaningMode.setEnabled(operationModeItem.enabled);
                }
            }
        }

        class AdvanceControlRecyclerViewAdapter extends RecyclerView.Adapter<AdvancedControlsViewHolder> {
            private Context context;
            private List<OperationModeItem> menuItemList;

            public class AdvancedControlsViewHolder_ViewBinding implements Unbinder {
                private AdvancedControlsViewHolder target;
                private View view7f0a0535;

                public AdvancedControlsViewHolder_ViewBinding(final AdvancedControlsViewHolder advancedControlsViewHolder, View view) {
                    this.target = advancedControlsViewHolder;
                    advancedControlsViewHolder.mMenuImageAdvancedControls = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_advanced_option_recycler_view, "field 'mMenuImageAdvancedControls'", ImageView.class);
                    advancedControlsViewHolder.mMenuImageRightSwipeAdvancedControls = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_right_swipe_recycler_view_advanced_options, "field 'mMenuImageRightSwipeAdvancedControls'", ImageView.class);
                    advancedControlsViewHolder.mMenuInfoAdvancedControls = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_advanced_option_recycler_view, "field 'mMenuInfoAdvancedControls'", TextView.class);
                    advancedControlsViewHolder.mRecyclerViewCleaningMode = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.recycler_view_cleaning_mode, "field 'mRecyclerViewCleaningMode'", RecyclerView.class);
                    View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_advanced_option_item, "field 'mOuterLayout' and method 'onClickItem'");
                    advancedControlsViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_advanced_option_item, "field 'mOuterLayout'", ConstraintLayout.class);
                    this.view7f0a0535 = findRequiredView;
                    findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                        public void doClick(View view) {
                            advancedControlsViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                        }
                    });
                }

                public void unbind() {
                    AdvancedControlsViewHolder advancedControlsViewHolder = this.target;
                    if (advancedControlsViewHolder != null) {
                        this.target = null;
                        advancedControlsViewHolder.mMenuImageAdvancedControls = null;
                        advancedControlsViewHolder.mMenuImageRightSwipeAdvancedControls = null;
                        advancedControlsViewHolder.mMenuInfoAdvancedControls = null;
                        advancedControlsViewHolder.mRecyclerViewCleaningMode = null;
                        advancedControlsViewHolder.mOuterLayout = null;
                        this.view7f0a0535.setOnClickListener((View.OnClickListener) null);
                        this.view7f0a0535 = null;
                        return;
                    }
                    throw new IllegalStateException("Bindings already cleared.");
                }
            }

            public AdvanceControlRecyclerViewAdapter(Context context2, List<OperationModeItem> list) {
                this.context = context2;
                this.menuItemList = list;
            }

            public AdvancedControlsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new AdvancedControlsViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_advanced_options, viewGroup, false));
            }

            public void onBindViewHolder(AdvancedControlsViewHolder advancedControlsViewHolder, int i) {
                advancedControlsViewHolder.bind(this.menuItemList.get(i));
            }

            public int getItemCount() {
                return this.menuItemList.size();
            }

            class AdvancedControlsViewHolder extends RecyclerView.ViewHolder {
                @BindView(2131362840)
                ImageView mMenuImageAdvancedControls;
                @BindView(2131362994)
                ImageView mMenuImageRightSwipeAdvancedControls;
                @BindView(2131363937)
                TextView mMenuInfoAdvancedControls;
                @BindView(2131363125)
                ConstraintLayout mOuterLayout;
                @BindView(2131363601)
                RecyclerView mRecyclerViewCleaningMode;

                AdvancedControlsViewHolder(View view) {
                    super(view);
                    ButterKnife.bind((Object) this, view);
                }

                /* access modifiers changed from: package-private */
                @OnClick({2131363125})
                public void onClickItem(ConstraintLayout constraintLayout) {
                    OperationModeItem operationModeItem = (OperationModeItem) constraintLayout.getTag();
                    operationModeItem.onClickListener.onClick(constraintLayout);
                    if (operationModeItem.isExpanded()) {
                        this.mMenuImageRightSwipeAdvancedControls.setRotation(90.0f);
                    } else {
                        this.mMenuImageRightSwipeAdvancedControls.setRotation(0.0f);
                    }
                }

                public void bind(OperationModeItem operationModeItem) {
                    this.mOuterLayout.setTag(operationModeItem);
                    this.mMenuImageAdvancedControls.setImageResource(operationModeItem.getImageMode());
                    this.mMenuInfoAdvancedControls.setText(operationModeItem.getTextMode());
                    float f = 1.0f;
                    this.mMenuImageAdvancedControls.setAlpha(operationModeItem.enabled ? 1.0f : 0.5f);
                    TextView textView = this.mMenuInfoAdvancedControls;
                    if (!operationModeItem.enabled) {
                        f = 0.5f;
                    }
                    textView.setAlpha(f);
                    this.mOuterLayout.setEnabled(operationModeItem.enabled);
                    this.mMenuImageAdvancedControls.setEnabled(operationModeItem.enabled);
                    this.mMenuInfoAdvancedControls.setEnabled(operationModeItem.enabled);
                }
            }
        }

        AdvancedOptionViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            this.advanceControlRecyclerViewAdapter = new AdvanceControlRecyclerViewAdapter(IndividualIDUControlFragmentModelWise.this.requireActivity(), this.menuItemsAdvancedControls);
            this.mCleaningModeRecyclerViewAdapter = new CleaningModeRecyclerViewAdapter(IndividualIDUControlFragmentModelWise.this.requireActivity(), this.menuItemsAdvancedCleaningMode);
            this.menuItemsAdvancedControls.clear();
            populateMenuItemsAdvancedControls();
            this.mAdvancedOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(IndividualIDUControlFragmentModelWise.this.requireActivity()));
            this.mAdvancedOptionsRecyclerView.setHasFixedSize(true);
            this.mAdvancedOptionsRecyclerView.setAdapter(this.advanceControlRecyclerViewAdapter);
            this.menuItemsAdvancedCleaningMode.clear();
            this.menuItemsAdvancedCleaningMode.addAll(populateMenuItemsCleaningModes());
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.updateUI(detailedIduModel);
            IndividualIDUControlFragmentModelWise.this.selectModesViewHolder.updateUI(detailedIduModel);
            IndividualIDUControlFragmentModelWise.this.gridOptionsViewHolder.updateUI(detailedIduModel);
            IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.updateUI(detailedIduModel);
            IndividualIDUControlFragmentModelWise.this.offStateViewHolder.updateUI(detailedIduModel);
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131363124})
        public void OnClickAdvancedControls() {
            if (this.advanceOptionsShown) {
                this.mAdvancedOptionsRecyclerView.setVisibility(8);
                this.advanceOptionsShown = false;
                updateUI(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                this.mImageViewAdvancedControls.setRotation(0.0f);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mLayoutBottom.getLayoutParams();
                layoutParams.topMargin = (int) ViewUtils.convertDpToPixel(4.0f, IndividualIDUControlFragmentModelWise.this.requireActivity());
                this.mLayoutBottom.setLayoutParams(layoutParams);
                return;
            }
            this.mAdvancedOptionsRecyclerView.setVisibility(0);
            this.mImageViewAdvancedControls.setRotation(90.0f);
            this.advanceOptionsShown = true;
            updateUI(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.mLayoutBottom.getLayoutParams();
            layoutParams2.topMargin = (int) ViewUtils.convertDpToPixel(58.0f, IndividualIDUControlFragmentModelWise.this.requireActivity());
            this.mLayoutBottom.setLayoutParams(layoutParams2);
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }

        public void setOffUI() {
            this.mImageViewAdvancedControls.setVisibility(4);
        }

        public void setOnUI() {
            this.mImageViewAdvancedControls.setVisibility(0);
        }

        private void populateMenuItemsAdvancedControls() {
            OperationModeItem operationModeItem = new OperationModeItem();
            if (IndividualIDUControlFragmentModelWise.this.mRacModelWiseData != null) {
                operationModeItem.enabled = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getEnableOptions().getEnableOption2().getIduFrostWash();
            }
            operationModeItem.setTextMode(IndividualIDUControlFragmentModelWise.this.getString(R.string.notification_lbl_frostWashIndoor));
            operationModeItem.setImageMode(R.drawable.ic_idu_frostwash_small);
            operationModeItem.setOnClickListener(new C2185xecc8563a(this));
            this.menuItemsAdvancedControls.add(operationModeItem);
            OperationModeItem operationModeItem2 = new OperationModeItem();
            operationModeItem2.enabled = true;
            operationModeItem2.setTextMode(IndividualIDUControlFragmentModelWise.this.getString(R.string.help_lbl_help));
            operationModeItem2.setImageMode(R.drawable.ic_help_grey_svg);
            operationModeItem2.setOnClickListener(new C2186xecc8563b(this));
            this.menuItemsAdvancedControls.add(operationModeItem2);
        }

        /* renamed from: lambda$populateMenuItemsAdvancedControls$1$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise$AdvancedOptionViewHolder */
        public /* synthetic */ void mo30974xe09cb4ee(View view) {
            if (!IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.UserFeatures.FROST_WASH)) {
                IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise = IndividualIDUControlFragmentModelWise.this;
                individualIDUControlFragmentModelWise.alertDialog(individualIDUControlFragmentModelWise.getString(R.string.common_alert_noPermissionToAccess), false);
            } else if (isCleaningModeEnabled()) {
                Toaster.makeToast(IndividualIDUControlFragmentModelWise.this.requireActivity(), IndividualIDUControlFragmentModelWise.this.getString(R.string.idu_alert_iduFrostWashAlreadyInProgress), 0);
            } else if (!IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState()) {
                if (IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isTurnedOn()) {
                    IndividualIDUControlFragmentModelWise.this.dismissSimpleAlertIfVisible();
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog = new SingleChoiceDialog(IndividualIDUControlFragmentModelWise.this.requireActivity());
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setTitleString(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_alert));
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setMessageString(IndividualIDUControlFragmentModelWise.this.getString(R.string.idu_alert_turnOffIduTostartFrostWash));
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setCancelable(false);
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setPositiveButton(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_btn_ok), C2188xecc8563d.INSTANCE);
                    if (!IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.isShowing()) {
                        IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.show();
                        return;
                    }
                    return;
                }
                IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.collapseFan();
                IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.collapseSwing();
                OnClickAdvancedControls();
                IndividualIDUControlFragmentModelWise.this.getArguments().putSerializable(CleaningModeEnum.PARCEL_KEY, CleaningModeEnum.FROST_WASH);
                IndividualIDUControlFragmentModelWise.this.getArguments().putParcelable(DetailedIduModel.PARCEL_KEY, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_individualIDUControlFragment_to_cleaningStartFragment, IndividualIDUControlFragmentModelWise.this.getArguments());
            }
        }

        /* renamed from: lambda$populateMenuItemsAdvancedControls$2$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise$AdvancedOptionViewHolder */
        public /* synthetic */ void mo30975xa5cea64d(View view) {
            IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.collapseFan();
            IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.collapseSwing();
            OnClickAdvancedControls();
            IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigate((NavDirections) IndividualIDUControlFragmentModelWiseDirections.actionIndividualIDUControlFragmentToHelpFragment(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel, HelpWebPageModel.IDU_HELP_PAGE));
        }

        private List<OperationModeItem> populateMenuItemsCleaningModes() {
            ArrayList arrayList = new ArrayList();
            OperationModeItem operationModeItem = new OperationModeItem();
            if (IndividualIDUControlFragmentModelWise.this.mRacModelWiseData != null) {
                operationModeItem.enabled = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getEnableOptions().getEnableOption2().getIduFrostWash();
            }
            operationModeItem.setImageMode(R.drawable.ic_idu_frost_wash_svg);
            operationModeItem.setTextMode(IndividualIDUControlFragmentModelWise.this.getString(R.string.notification_lbl_frostWashIndoor));
            operationModeItem.setOnClickListener(new C2187xecc8563c(this));
            arrayList.add(operationModeItem);
            return arrayList;
        }

        /* renamed from: lambda$populateMenuItemsCleaningModes$4$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise$AdvancedOptionViewHolder */
        public /* synthetic */ void mo30976x2b62f4a0(View view) {
            if (isCleaningModeEnabled()) {
                Toaster.makeToast(IndividualIDUControlFragmentModelWise.this.requireActivity(), IndividualIDUControlFragmentModelWise.this.getString(R.string.idu_alert_iduFrostWashAlreadyInProgress), 0);
            } else if (!IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState()) {
                if (IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isTurnedOn()) {
                    IndividualIDUControlFragmentModelWise.this.dismissSimpleAlertIfVisible();
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog = new SingleChoiceDialog(IndividualIDUControlFragmentModelWise.this.requireActivity());
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setTitleString(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_alert));
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setMessageString(IndividualIDUControlFragmentModelWise.this.getString(R.string.idu_alert_turnOffIduTostartFrostWash));
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setCancelable(false);
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setPositiveButton(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_btn_ok), C2189xecc8563e.INSTANCE);
                    if (!IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.isShowing()) {
                        IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.show();
                        return;
                    }
                    return;
                }
                IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.collapseFan();
                IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.collapseSwing();
                OnClickAdvancedControls();
                IndividualIDUControlFragmentModelWise.this.getArguments().putSerializable(CleaningModeEnum.PARCEL_KEY, CleaningModeEnum.FROST_WASH);
                IndividualIDUControlFragmentModelWise.this.getArguments().putParcelable(DetailedIduModel.PARCEL_KEY, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_individualIDUControlFragment_to_cleaningStartFragment, IndividualIDUControlFragmentModelWise.this.getArguments());
            }
        }

        private boolean isCleaningModeEnabled() {
            return IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isFrostWashRunning();
        }
    }

    class GridOptionsViewHolder implements Unbinder {
        private List<OptionsModel> gridOptionsModelList = new ArrayList();
        @BindView(2131362094)
        ConstraintLayout mBottomLayout;
        /* access modifiers changed from: private */
        public int[] mFanSpeedArray;
        @BindView(2131363617)
        RecyclerView mOptionRecyclerView;
        /* access modifiers changed from: private */
        public int[] mSwingModeArray;
        private OptionsGridLayoutAdapter optionsGridLayoutAdapter;
        Unbinder unbinder;

        public void updateUI(DetailedIduModel detailedIduModel) {
        }

        class OptionsGridLayoutAdapter extends RecyclerView.Adapter<OptionsViewHolder> {
            private Context context;
            /* access modifiers changed from: private */
            public DetailedIduModel detailedIduModel = new DetailedIduModel();
            private List<OptionsModel> menuItemList;

            public class OptionsViewHolder_ViewBinding implements Unbinder {
                private OptionsViewHolder target;
                private View view7f0a0577;

                public OptionsViewHolder_ViewBinding(final OptionsViewHolder optionsViewHolder, View view) {
                    this.target = optionsViewHolder;
                    optionsViewHolder.mImageViewLeft = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_fan, "field 'mImageViewLeft'", ImageView.class);
                    optionsViewHolder.mImageViewOptions = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_fan_speed, "field 'mImageViewOptions'", ImageView.class);
                    optionsViewHolder.mImageViewFanRight = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_right_swipe_fan, "field 'mImageViewFanRight'", ImageView.class);
                    optionsViewHolder.mTextViewOption = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_fan, "field 'mTextViewOption'", TextView.class);
                    View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_fan, "field 'mOuterLayout' and method 'onClickItem'");
                    optionsViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_fan, "field 'mOuterLayout'", ConstraintLayout.class);
                    this.view7f0a0577 = findRequiredView;
                    findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                        public void doClick(View view) {
                            optionsViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                        }
                    });
                    optionsViewHolder.mOnIndicator = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.on_indicator, "field 'mOnIndicator'", ImageView.class);
                }

                public void unbind() {
                    OptionsViewHolder optionsViewHolder = this.target;
                    if (optionsViewHolder != null) {
                        this.target = null;
                        optionsViewHolder.mImageViewLeft = null;
                        optionsViewHolder.mImageViewOptions = null;
                        optionsViewHolder.mImageViewFanRight = null;
                        optionsViewHolder.mTextViewOption = null;
                        optionsViewHolder.mOuterLayout = null;
                        optionsViewHolder.mOnIndicator = null;
                        this.view7f0a0577.setOnClickListener((View.OnClickListener) null);
                        this.view7f0a0577 = null;
                        return;
                    }
                    throw new IllegalStateException("Bindings already cleared.");
                }
            }

            public void setDetailedIduModel(DetailedIduModel detailedIduModel2) {
                this.detailedIduModel.copy(detailedIduModel2);
            }

            public OptionsGridLayoutAdapter(Context context2, List<OptionsModel> list) {
                this.context = context2;
                this.menuItemList = list;
            }

            public OptionsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new OptionsViewHolder(LayoutInflater.from(this.context).inflate(R.layout.grid_view_item_idu, viewGroup, false));
            }

            public void onBindViewHolder(OptionsViewHolder optionsViewHolder, int i) {
                optionsViewHolder.bind(this.menuItemList.get(i));
            }

            public int getItemCount() {
                return this.menuItemList.size();
            }

            class OptionsViewHolder extends RecyclerView.ViewHolder {
                @BindView(2131362990)
                ImageView mImageViewFanRight;
                @BindView(2131362910)
                ImageView mImageViewLeft;
                @BindView(2131362913)
                ImageView mImageViewOptions;
                @BindView(2131363517)
                ImageView mOnIndicator;
                @BindView(2131363191)
                ConstraintLayout mOuterLayout;
                @BindView(2131364220)
                TextView mTextViewOption;

                OptionsViewHolder(View view) {
                    super(view);
                    ButterKnife.bind((Object) this, view);
                }

                @OnClick({2131363191})
                public void onClickItem(ConstraintLayout constraintLayout) {
                    ((OptionsModel) constraintLayout.getTag()).onClickListener.onClick(constraintLayout);
                }

                public void bind(OptionsModel optionsModel) {
                    this.mTextViewOption.setText(optionsModel.getOptionName());
                    this.mImageViewOptions.setImageResource(optionsModel.getOptionIcon());
                    this.mImageViewFanRight.setImageResource(optionsModel.getOptionRightImage());
                    this.mImageViewLeft.setImageResource(optionsModel.getOptionLeftImage());
                    this.mOuterLayout.setTag(optionsModel);
                    this.mOnIndicator.setVisibility(optionsModel.isOnIndicator ? 0 : 4);
                    if (!optionsModel.isEnabled) {
                        setDisabled();
                    } else {
                        setEnabled();
                    }
                    if (optionsModel.isVisible) {
                        this.mOuterLayout.setVisibility(0);
                    } else {
                        this.mOuterLayout.setVisibility(4);
                    }
                    OptionsModel.Options options = optionsModel.getOptions();
                    if (options != null && options.equals(OptionsModel.Options.FAN)) {
                        setSelectedFanSpeedIcon(OptionsGridLayoutAdapter.this.detailedIduModel);
                    } else if (options != null && options.equals(OptionsModel.Options.SWING)) {
                        setSelectedSwingModeIcon(OptionsGridLayoutAdapter.this.detailedIduModel);
                    }
                }

                private void setSelectedFanSpeedIcon(DetailedIduModel detailedIduModel) {
                    this.mImageViewOptions.setImageResource(GridOptionsViewHolder.this.mFanSpeedArray[DetailedIduModel.FanSpeed.valueOf(detailedIduModel.fanSpeedStr).ordinal()]);
                }

                private void setSelectedSwingModeIcon(DetailedIduModel detailedIduModel) {
                    this.mImageViewOptions.setImageResource(GridOptionsViewHolder.this.mSwingModeArray[DetailedIduModel.SwingOption.valueOf(detailedIduModel.fanSwingStr).ordinal()]);
                }

                private void setDisabled() {
                    this.mOuterLayout.setAlpha(0.5f);
                    this.mOuterLayout.setClickable(false);
                    this.mOuterLayout.setFocusable(false);
                }

                private void setEnabled() {
                    this.mOuterLayout.setAlpha(1.0f);
                    this.mOuterLayout.setClickable(true);
                    this.mOuterLayout.setFocusable(true);
                }
            }
        }

        GridOptionsViewHolder() {
            this.optionsGridLayoutAdapter = new OptionsGridLayoutAdapter(IndividualIDUControlFragmentModelWise.this.requireActivity(), this.gridOptionsModelList);
            this.mFanSpeedArray = new int[]{R.drawable.ic_fan_speed_1of5, R.drawable.ic_fan_speed_2of5, R.drawable.ic_fan_speed_3of5, R.drawable.ic_fan_speed_4of5, R.drawable.ic_fan_speed_5of5, R.drawable.ic_fan_speed_auto};
            this.mSwingModeArray = new int[]{R.drawable.ic_enabled_swing_off_updated, R.drawable.ic_enabled_swing_vertical_updated, R.drawable.ic_enabled_swing_horizontal_updated, R.drawable.ic_enabled_swing_horizontalvertical_updated, R.drawable.ic_enabled_swing_auto_updated};
        }

        public OptionsGridLayoutAdapter getOptionsGridLayoutAdapter() {
            return this.optionsGridLayoutAdapter;
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            this.mOptionRecyclerView.setHasFixedSize(true);
            this.mOptionRecyclerView.setLayoutManager(new GridLayoutManager((Context) IndividualIDUControlFragmentModelWise.this.requireActivity(), 3, 1, false));
            this.mOptionRecyclerView.setAdapter(this.optionsGridLayoutAdapter);
        }

        public void updateOptionsListBasedOnRacType(RacModelWiseData racModelWiseData, DetailedIduModel detailedIduModel) {
            this.gridOptionsModelList.clear();
            this.gridOptionsModelList.addAll(populateOptionsList(racModelWiseData, detailedIduModel));
            this.optionsGridLayoutAdapter.notifyDataSetChanged();
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }

        private List<OptionsModel> populateOptionsList(RacModelWiseData racModelWiseData, final DetailedIduModel detailedIduModel) {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode;
            ArrayList arrayList = new ArrayList();
            if (!(racModelWiseData == null || (racModeDetailBasedOnMode = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(detailedIduModel.getOperationModeEnum())) == null)) {
                RacModelWiseData.EnableOptions enableOptions = racModelWiseData.getEnableOptions();
                RacModelWiseData.EnableOption0 enableOption0 = enableOptions.getEnableOption0();
                enableOptions.getEnableOption1();
                RacModelWiseData.EnableOption2 enableOption2 = enableOptions.getEnableOption2();
                OptionsModel optionsModel = new OptionsModel();
                optionsModel.setOptionName(R.string.idu_lbl_fanSpeed);
                optionsModel.setOptionIcon(R.drawable.ic_fan_speed_5of5);
                optionsModel.setOptionLeftImage(R.drawable.ic_fan_grey_svg);
                optionsModel.setOptionRightImage(R.drawable.ic_chevron_right_black_18dp);
                boolean z = false;
                optionsModel.setOnIndicator(false);
                if (detailedIduModel.isTurnedOn()) {
                    optionsModel.setEnabled(racModeDetailBasedOnMode.getEnableSettings().getFan() && IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.FAN) && !detailedIduModel.isInSpecialMode() && !detailedIduModel.isInErrorState() && !detailedIduModel.isHolidayModeEnabled());
                } else {
                    optionsModel.setEnabled(false);
                }
                optionsModel.setVisible(racModeDetailBasedOnMode.getVisibleSettings().getFan());
                optionsModel.setOptions(OptionsModel.Options.FAN);
                optionsModel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!IndividualIDUControlFragmentModelWise.this.ifHolidayModeNotEnabled() && !IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.isExpanded()) {
                            OptionsModel optionsModel = (OptionsModel) view.getTag();
                            IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.expandFan();
                        }
                    }
                });
                arrayList.add(optionsModel);
                OptionsModel optionsModel2 = new OptionsModel();
                optionsModel2.setOptionName(R.string.common_lbl_email);
                optionsModel2.setVisible(false);
                optionsModel2.setOptionIcon(R.drawable.ic_fan_speed_1);
                optionsModel2.setOnIndicator(false);
                optionsModel2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OptionsModel optionsModel = (OptionsModel) view.getTag();
                        if (optionsModel.isEnabled) {
                            boolean unused = optionsModel.isPermissionAvailable;
                        }
                    }
                });
                arrayList.add(optionsModel2);
                OptionsModel optionsModel3 = new OptionsModel();
                optionsModel3.setOptionName(R.string.idu_lbl_swing);
                if (detailedIduModel.isTurnedOn()) {
                    optionsModel3.setEnabled(racModeDetailBasedOnMode.getEnableSettings().getSwing() && IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.SWING) && !detailedIduModel.isInSpecialMode() && !detailedIduModel.isInErrorState() && !detailedIduModel.isHolidayModeEnabled());
                } else {
                    optionsModel3.setEnabled(false);
                }
                optionsModel3.setVisible(racModeDetailBasedOnMode.getVisibleSettings().getSwing());
                optionsModel3.setOptions(OptionsModel.Options.SWING);
                optionsModel3.setOptionIcon(R.drawable.ic_swing_grey_svg);
                optionsModel3.setOptionLeftImage(R.drawable.ic_chevron_left_black_18dp);
                optionsModel3.setOnIndicator(false);
                optionsModel3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!IndividualIDUControlFragmentModelWise.this.ifHolidayModeNotEnabled() && !IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.isExpanded()) {
                            OptionsModel optionsModel = (OptionsModel) view.getTag();
                            IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.expandSwing();
                        }
                    }
                });
                arrayList.add(optionsModel3);
                OptionsModel optionsModel4 = new OptionsModel();
                optionsModel4.setEnabled(enableOption0.isWeeklyTimer() && IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.WEEKLY_TIMER) && !detailedIduModel.isInSpecialMode() && !detailedIduModel.isInErrorState() && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isFrostWashRunning() && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isHolidayModeEnabled());
                optionsModel4.setVisible(enableOption0.isWeeklyTimer());
                optionsModel4.setOptionName(R.string.idu_lbl_weeklyTimer);
                optionsModel4.setOptionIcon(R.drawable.ic_weekly_timer_grey_new);
                optionsModel4.setOnIndicator(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isWeeklyTimerEnabled());
                optionsModel4.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (((OptionsModel) view.getTag()).isEnabled) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(DetailedIduModel.PARCEL_KEY, detailedIduModel);
                            IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.weeklyTimerFragmentV3, bundle);
                        }
                    }
                });
                arrayList.add(optionsModel4);
                OptionsModel optionsModel5 = new OptionsModel();
                optionsModel5.setEnabled(enableOption2.getEco() && racModeDetailBasedOnMode.getEnableSettings().getEco() && IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.ECO) && !detailedIduModel.isInSpecialMode() && !detailedIduModel.isInErrorState());
                optionsModel5.setVisible(racModeDetailBasedOnMode.getVisibleSettings().getEco());
                optionsModel5.setOptionName(R.string.dots);
                optionsModel5.setOptionIcon(R.drawable.ic_eco_grey_svg);
                optionsModel5.setOnIndicator(false);
                optionsModel5.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OptionsModel optionsModel = (OptionsModel) view.getTag();
                        if (optionsModel.isEnabled) {
                            boolean unused = optionsModel.isPermissionAvailable;
                        }
                    }
                });
                arrayList.add(optionsModel5);
                OptionsModel optionsModel6 = new OptionsModel();
                if (racModeDetailBasedOnMode.getEnableSettings().getTimer() && IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.TIMER) && !detailedIduModel.isInSpecialMode() && !detailedIduModel.isInErrorState() && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isFrostWashRunning() && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isHolidayModeEnabled()) {
                    z = true;
                }
                optionsModel6.setEnabled(z);
                optionsModel6.setVisible(racModeDetailBasedOnMode.getVisibleSettings().getTimer());
                optionsModel6.setOptionName(R.string.common_lbl_timer);
                optionsModel6.setOptionIcon(R.drawable.ic_grey_time_new);
                optionsModel6.setOnIndicator(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isTimerEnabled());
                optionsModel6.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (((OptionsModel) view.getTag()).isEnabled) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(DetailedIduModel.PARCEL_KEY, detailedIduModel);
                            bundle.putInt(NavigationTransitionKeyValues.NAVIGATION_FROM, 1002);
                            IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_individualIDUControlFragment_to_setTimerFragmentV2, bundle);
                        }
                    }
                });
                arrayList.add(optionsModel6);
            }
            return arrayList;
        }
    }

    class SelectModesViewHolder implements Unbinder {
        @BindView(2131362849)
        ImageView mDownArrow;
        @BindView(2131363227)
        ConstraintLayout mLayoutMode;
        private Handler mModeChangeHandler;
        private AlertDialog mModeSelectDialog;
        @BindView(2131363006)
        ImageView mSelectedModeImage;
        @BindView(2131364504)
        TextView mSelectedModeText;
        private List<OperationModeItem> menuItemsSetMode = new ArrayList();
        public boolean modeChangeUnderProgress;
        SelectModeRecyclerViewAdapter selectModeRecyclerViewAdapter;
        Unbinder unbinder;

        class SelectModeRecyclerViewAdapter extends RecyclerView.Adapter<SelectModeViewHolder> {
            private Context context;
            private List<OperationModeItem> menuItemList;

            public class SelectModeViewHolder_ViewBinding implements Unbinder {
                private SelectModeViewHolder target;
                private View view7f0a05c1;

                public SelectModeViewHolder_ViewBinding(final SelectModeViewHolder selectModeViewHolder, View view) {
                    this.target = selectModeViewHolder;
                    selectModeViewHolder.mMenuImageSetMode = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_mode_item, "field 'mMenuImageSetMode'", ImageView.class);
                    selectModeViewHolder.mMenuInfoSetMode = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_mode_item, "field 'mMenuInfoSetMode'", TextView.class);
                    View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_select_mode, "field 'mOuterLayout' and method 'onClickItem'");
                    selectModeViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_select_mode, "field 'mOuterLayout'", ConstraintLayout.class);
                    this.view7f0a05c1 = findRequiredView;
                    findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                        public void doClick(View view) {
                            selectModeViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                        }
                    });
                }

                public void unbind() {
                    SelectModeViewHolder selectModeViewHolder = this.target;
                    if (selectModeViewHolder != null) {
                        this.target = null;
                        selectModeViewHolder.mMenuImageSetMode = null;
                        selectModeViewHolder.mMenuInfoSetMode = null;
                        selectModeViewHolder.mOuterLayout = null;
                        this.view7f0a05c1.setOnClickListener((View.OnClickListener) null);
                        this.view7f0a05c1 = null;
                        return;
                    }
                    throw new IllegalStateException("Bindings already cleared.");
                }
            }

            public SelectModeRecyclerViewAdapter(Context context2, List<OperationModeItem> list) {
                this.context = context2;
                this.menuItemList = list;
            }

            public SelectModeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new SelectModeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_mode, viewGroup, false));
            }

            public void onBindViewHolder(SelectModeViewHolder selectModeViewHolder, int i) {
                selectModeViewHolder.bind(this.menuItemList.get(i));
            }

            public int getItemCount() {
                return this.menuItemList.size();
            }

            class SelectModeViewHolder extends RecyclerView.ViewHolder {
                @BindView(2131362950)
                ImageView mMenuImageSetMode;
                @BindView(2131364344)
                TextView mMenuInfoSetMode;
                @BindView(2131363265)
                ConstraintLayout mOuterLayout;

                public SelectModeViewHolder(View view) {
                    super(view);
                    ButterKnife.bind((Object) this, view);
                }

                @OnClick({2131363265})
                public void onClickItem(ConstraintLayout constraintLayout) {
                    ((OperationModeItem) constraintLayout.getTag()).onClickListener.onClick(constraintLayout);
                }

                public void bind(OperationModeItem operationModeItem) {
                    this.mMenuImageSetMode.setImageResource(operationModeItem.getImageMode());
                    this.mMenuInfoSetMode.setText(operationModeItem.getTextMode());
                    this.mOuterLayout.setTag(operationModeItem);
                }
            }
        }

        SelectModesViewHolder() {
            this.selectModeRecyclerViewAdapter = new SelectModeRecyclerViewAdapter(IndividualIDUControlFragmentModelWise.this.requireActivity(), this.menuItemsSetMode);
            this.mModeChangeHandler = new Handler();
            this.modeChangeUnderProgress = false;
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
        }

        public void updateModeListBasedOnRacType(RacModelWiseData racModelWiseData) {
            this.menuItemsSetMode.clear();
            this.menuItemsSetMode.addAll(populateMenuItemsSelectMode(racModelWiseData));
            this.selectModeRecyclerViewAdapter.notifyDataSetChanged();
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            OperationMode operationModeEnum = detailedIduModel.getOperationModeEnum();
            OperationModeUIConfigration operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(operationModeEnum);
            boolean z = true;
            if (operationModeUIConfigrationBasedOnMode != null) {
                if (detailedIduModel.isInSpecialMode()) {
                    this.mSelectedModeImage.setImageDrawable(new BitmapDrawable(IndividualIDUControlFragmentModelWise.this.requireActivity().getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) IndividualIDUControlFragmentModelWise.this.requireActivity().getResources().getDrawable(R.drawable.ic_special_state_new)).getBitmap(), 80, 80, true)));
                    this.mSelectedModeText.setText(R.string.idu_lbl_special);
                    this.mLayoutMode.setBackgroundColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.selection_bg_color));
                } else {
                    this.mLayoutMode.setBackgroundColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(operationModeUIConfigrationBasedOnMode.getModeBackgroundColorResource()));
                    this.mSelectedModeText.setText(operationModeUIConfigrationBasedOnMode.getOperationModeStringResource());
                    this.mSelectedModeImage.setImageResource(operationModeUIConfigrationBasedOnMode.getSelectedModeDrawableResource());
                }
            }
            if (detailedIduModel.isInSpecialMode()) {
                setModesNormal();
            } else if (operationModeEnum.equals(OperationMode.AUTO)) {
                setModeAuto();
            } else {
                setModesNormal();
            }
            if (detailedIduModel.getPowerEnum().equals(Power.OFF)) {
                modeOffMode();
            } else {
                modeOnMode();
            }
            ConstraintLayout constraintLayout = this.mLayoutMode;
            if (!IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.MODE) || detailedIduModel.isInErrorState()) {
                z = false;
            }
            constraintLayout.setEnabled(z);
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131363227})
        public void OnClickMode(ConstraintLayout constraintLayout) {
            if (!this.modeChangeUnderProgress && !IndividualIDUControlFragmentModelWise.this.ifHolidayModeNotEnabled()) {
                selectedMode();
            }
        }

        /* access modifiers changed from: private */
        public void operationModeChangeConfirmation(OperationMode operationMode) {
            if (IndividualIDUControlFragmentModelWise.this.mRacModelWiseData != null) {
                IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeOperationMode(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), operationMode, IndividualIDUControlFragmentModelWise.this.mRacModelWiseData);
            }
            AlertDialog alertDialog = this.mModeSelectDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        }

        public void setModeAuto() {
            this.mSelectedModeText.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.textview_color_vd_dark));
            this.mDownArrow.setColorFilter(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.textview_color_vd_dark));
        }

        public void setModesNormal() {
            this.mSelectedModeText.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.white));
            this.mDownArrow.setColorFilter(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.white));
        }

        public void modeOffMode() {
            this.mLayoutMode.setVisibility(4);
        }

        public void modeOnMode() {
            this.mLayoutMode.setVisibility(0);
        }

        public void onDestroyView() {
            stopModeChangeHandler();
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }

        private void selectedMode() {
            AlertDialog.Builder builder = new AlertDialog.Builder(IndividualIDUControlFragmentModelWise.this.requireActivity());
            builder.setCancelable(false).setView(R.layout.select_mode_dialog);
            AlertDialog alertDialog = this.mModeSelectDialog;
            if (alertDialog != null && alertDialog.isShowing()) {
                this.mModeSelectDialog.dismiss();
            }
            AlertDialog create = builder.create();
            this.mModeSelectDialog = create;
            create.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    IndividualIDUControlFragmentModelWise.this.mParent.setBackgroundResource(R.drawable.white_blur);
                }
            });
            this.mModeSelectDialog.show();
            this.mModeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    IndividualIDUControlFragmentModelWise.this.mParent.setBackgroundResource(R.drawable.transparent);
                }
            });
            WindowManager.LayoutParams attributes = this.mModeSelectDialog.getWindow().getAttributes();
            attributes.dimAmount = 0.2f;
            this.mModeSelectDialog.getWindow().setAttributes(attributes);
            this.mModeSelectDialog.getWindow().addFlags(2);
            View decorView = this.mModeSelectDialog.getWindow().getDecorView();
            ((ImageButton) decorView.findViewById(R.id.image_button_clear)).setOnClickListener(new C2190x51e706b1(this));
            RecyclerView recyclerView = (RecyclerView) decorView.findViewById(R.id.recycler_view_select_mode);
            recyclerView.setLayoutManager(new LinearLayoutManager(IndividualIDUControlFragmentModelWise.this.requireActivity()));
            Math.round(ViewUtils.convertDpToPixel(60.0f, IndividualIDUControlFragmentModelWise.this.requireActivity()));
            recyclerView.setAdapter(this.selectModeRecyclerViewAdapter);
        }

        /* renamed from: lambda$selectedMode$0$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise$SelectModesViewHolder */
        public /* synthetic */ void mo31081x78b9e087(View view) {
            this.mModeSelectDialog.dismiss();
        }

        /* access modifiers changed from: private */
        public void startModeChangeHandler(final OperationMode operationMode) {
            this.mModeChangeHandler.removeCallbacksAndMessages((Object) null);
            this.mModeChangeHandler.postDelayed(new Runnable() {
                public void run() {
                    SelectModesViewHolder.this.modeChangeUnderProgress = true;
                    IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.humiditySelected = false;
                    SelectModesViewHolder.this.operationModeChangeConfirmation(operationMode);
                }
            }, 200);
        }

        private void stopModeChangeHandler() {
            this.mModeChangeHandler.removeCallbacksAndMessages((Object) null);
        }

        private List<OperationModeItem> populateMenuItemsSelectMode(RacModelWiseData racModelWiseData) {
            OperationModeUIConfigration operationModeUIConfigrationBasedOnMode;
            ArrayList arrayList = new ArrayList();
            if (racModelWiseData != null) {
                Iterator it = racModelWiseData.getRacModeDetails().iterator();
                while (it.hasNext()) {
                    final OperationMode mode = ((RacModelWiseData.RacModeDetail) it.next()).getMode();
                    if (!(mode == null || (operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(mode)) == null)) {
                        OperationModeItem operationModeItem = new OperationModeItem();
                        operationModeItem.setImageMode(operationModeUIConfigrationBasedOnMode.getModeListDrawableResource());
                        operationModeItem.setTextMode(IndividualIDUControlFragmentModelWise.this.getString(operationModeUIConfigrationBasedOnMode.getOperationModeStringResource()));
                        operationModeItem.setDisplayOrder(mode.getDisplayOrder());
                        operationModeItem.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                SelectModesViewHolder.this.startModeChangeHandler(mode);
                            }
                        });
                        arrayList.add(operationModeItem);
                    }
                }
                if (!arrayList.isEmpty()) {
                    Collections.sort(arrayList);
                }
            }
            return arrayList;
        }
    }

    public class SelectModesViewHolder_ViewBinding implements Unbinder {
        private SelectModesViewHolder target;
        private View view7f0a059b;

        public SelectModesViewHolder_ViewBinding(final SelectModesViewHolder selectModesViewHolder, View view) {
            this.target = selectModesViewHolder;
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_mode_room_device_control, "field 'mLayoutMode' and method 'OnClickMode'");
            selectModesViewHolder.mLayoutMode = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_mode_room_device_control, "field 'mLayoutMode'", ConstraintLayout.class);
            this.view7f0a059b = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    selectModesViewHolder.OnClickMode((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickMode", 0, ConstraintLayout.class));
                }
            });
            selectModesViewHolder.mSelectedModeImage = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_selected_mode_home, "field 'mSelectedModeImage'", ImageView.class);
            selectModesViewHolder.mSelectedModeText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_selected_mode_home, "field 'mSelectedModeText'", TextView.class);
            selectModesViewHolder.mDownArrow = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_arrow_down_mode, "field 'mDownArrow'", ImageView.class);
        }

        public void unbind() {
            SelectModesViewHolder selectModesViewHolder = this.target;
            if (selectModesViewHolder != null) {
                this.target = null;
                selectModesViewHolder.mLayoutMode = null;
                selectModesViewHolder.mSelectedModeImage = null;
                selectModesViewHolder.mSelectedModeText = null;
                selectModesViewHolder.mDownArrow = null;
                this.view7f0a059b.setOnClickListener((View.OnClickListener) null);
                this.view7f0a059b = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class AdvancedOptionViewHolder_ViewBinding implements Unbinder {
        private AdvancedOptionViewHolder target;
        private View view7f0a0534;

        public AdvancedOptionViewHolder_ViewBinding(final AdvancedOptionViewHolder advancedOptionViewHolder, View view) {
            this.target = advancedOptionViewHolder;
            advancedOptionViewHolder.mAdvancedOptionsRecyclerView = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.recycler_view_advanced_option, "field 'mAdvancedOptionsRecyclerView'", RecyclerView.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_advanced_controls, "field 'mAdvancedControls' and method 'OnClickAdvancedControls'");
            advancedOptionViewHolder.mAdvancedControls = (LinearLayout) C0840Utils.castView(findRequiredView, R.id.layout_advanced_controls, "field 'mAdvancedControls'", LinearLayout.class);
            this.view7f0a0534 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    advancedOptionViewHolder.OnClickAdvancedControls();
                }
            });
            advancedOptionViewHolder.mImageViewAdvancedControls = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_advanced_controls, "field 'mImageViewAdvancedControls'", ImageView.class);
            advancedOptionViewHolder.mLayoutBottom = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_bottom_room_device_control, "field 'mLayoutBottom'", ConstraintLayout.class);
        }

        public void unbind() {
            AdvancedOptionViewHolder advancedOptionViewHolder = this.target;
            if (advancedOptionViewHolder != null) {
                this.target = null;
                advancedOptionViewHolder.mAdvancedOptionsRecyclerView = null;
                advancedOptionViewHolder.mAdvancedControls = null;
                advancedOptionViewHolder.mImageViewAdvancedControls = null;
                advancedOptionViewHolder.mLayoutBottom = null;
                this.view7f0a0534.setOnClickListener((View.OnClickListener) null);
                this.view7f0a0534 = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class TemperatureLayoutViewHolder_ViewBinding implements Unbinder {
        private TemperatureLayoutViewHolder target;
        private View view7f0a09ae;
        private View view7f0a0ab5;

        public TemperatureLayoutViewHolder_ViewBinding(final TemperatureLayoutViewHolder temperatureLayoutViewHolder, View view) {
            this.target = temperatureLayoutViewHolder;
            temperatureLayoutViewHolder.mLayoutTemprature = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_set_temprature_room_device_control, "field 'mLayoutTemprature'", ConstraintLayout.class);
            temperatureLayoutViewHolder.mIncreaseTemprature = (ImageButton) C0840Utils.findRequiredViewAsType(view, R.id.image_button_increase_temprature, "field 'mIncreaseTemprature'", ImageButton.class);
            temperatureLayoutViewHolder.mDecreaseTemprature = (ImageButton) C0840Utils.findRequiredViewAsType(view, R.id.image_button_decrease_temprature, "field 'mDecreaseTemprature'", ImageButton.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.text_view_set_temp, "field 'mSetTemperatureTitle' and method 'OnClickModeSelectTemp'");
            temperatureLayoutViewHolder.mSetTemperatureTitle = (TextView) C0840Utils.castView(findRequiredView, R.id.text_view_set_temp, "field 'mSetTemperatureTitle'", TextView.class);
            this.view7f0a0ab5 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    temperatureLayoutViewHolder.OnClickModeSelectTemp((TextView) C0840Utils.castParam(view, "doClick", 0, "OnClickModeSelectTemp", 0, TextView.class));
                }
            });
            temperatureLayoutViewHolder.mTemperature = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature, "field 'mTemperature'", TextView.class);
            temperatureLayoutViewHolder.mTempratureUnit = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature_unit, "field 'mTempratureUnit'", TextView.class);
            temperatureLayoutViewHolder.mHumidityValueTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_humidity_percentage_idu_control, "field 'mHumidityValueTextView'", TextView.class);
            temperatureLayoutViewHolder.mPercentageSymbol = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_percent, "field 'mPercentageSymbol'", TextView.class);
            View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_humidity_title_roomdevice_control, "field 'mHumidityTitle' and method 'OnClickModeSelectHumidity'");
            temperatureLayoutViewHolder.mHumidityTitle = (TextView) C0840Utils.castView(findRequiredView2, R.id.text_view_humidity_title_roomdevice_control, "field 'mHumidityTitle'", TextView.class);
            this.view7f0a09ae = findRequiredView2;
            findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    temperatureLayoutViewHolder.OnClickModeSelectHumidity((TextView) C0840Utils.castParam(view, "doClick", 0, "OnClickModeSelectHumidity", 0, TextView.class));
                }
            });
        }

        public void unbind() {
            TemperatureLayoutViewHolder temperatureLayoutViewHolder = this.target;
            if (temperatureLayoutViewHolder != null) {
                this.target = null;
                temperatureLayoutViewHolder.mLayoutTemprature = null;
                temperatureLayoutViewHolder.mIncreaseTemprature = null;
                temperatureLayoutViewHolder.mDecreaseTemprature = null;
                temperatureLayoutViewHolder.mSetTemperatureTitle = null;
                temperatureLayoutViewHolder.mTemperature = null;
                temperatureLayoutViewHolder.mTempratureUnit = null;
                temperatureLayoutViewHolder.mHumidityValueTextView = null;
                temperatureLayoutViewHolder.mPercentageSymbol = null;
                temperatureLayoutViewHolder.mHumidityTitle = null;
                this.view7f0a0ab5.setOnClickListener((View.OnClickListener) null);
                this.view7f0a0ab5 = null;
                this.view7f0a09ae.setOnClickListener((View.OnClickListener) null);
                this.view7f0a09ae = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class LayoutTopViewHolder_ViewBinding implements Unbinder {
        private LayoutTopViewHolder target;
        private View view7f0a011e;
        private View view7f0a0408;

        public LayoutTopViewHolder_ViewBinding(final LayoutTopViewHolder layoutTopViewHolder, View view) {
            this.target = layoutTopViewHolder;
            layoutTopViewHolder.mLayoutMain = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_main_room_device_control, "field 'mLayoutMain'", ConstraintLayout.class);
            layoutTopViewHolder.mLayoutTop = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_top_room_device_control, "field 'mLayoutTop'", ConstraintLayout.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'OnClickBack'");
            layoutTopViewHolder.mBack = (ImageButton) C0840Utils.castView(findRequiredView, R.id.back_button, "field 'mBack'", ImageButton.class);
            this.view7f0a011e = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    layoutTopViewHolder.OnClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickBack", 0, ImageButton.class));
                }
            });
            layoutTopViewHolder.mRoomName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_room_title, "field 'mRoomName'", TextView.class);
            View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.image_button_refresh_idu_state, "field 'mRefreshIduState' and method 'onClickRefresh'");
            layoutTopViewHolder.mRefreshIduState = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.image_button_refresh_idu_state, "field 'mRefreshIduState'", ImageButton.class);
            this.view7f0a0408 = findRequiredView2;
            findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    layoutTopViewHolder.onClickRefresh();
                }
            });
            layoutTopViewHolder.mLastUpdatedOnTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_last_updated_on, "field 'mLastUpdatedOnTitle'", TextView.class);
            layoutTopViewHolder.mLayoutTemprature = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_temprature_room_device_control, "field 'mLayoutTemprature'", ConstraintLayout.class);
            layoutTopViewHolder.mRoomTemperature = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_room_temprature, "field 'mRoomTemperature'", TextView.class);
            layoutTopViewHolder.mRoomTemperatureUnit = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_room_temprature_unit, "field 'mRoomTemperatureUnit'", TextView.class);
            layoutTopViewHolder.mImageViewTemperature = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_temprature_home, "field 'mImageViewTemperature'", ImageView.class);
            layoutTopViewHolder.mImageViewSun = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_sun, "field 'mImageViewSun'", ImageView.class);
            layoutTopViewHolder.mRoomTemperatureTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_room_temprature_title, "field 'mRoomTemperatureTitle'", TextView.class);
            layoutTopViewHolder.mAreaName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_area_name, "field 'mAreaName'", TextView.class);
            layoutTopViewHolder.mWeatherType = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_weather_type, "field 'mWeatherType'", TextView.class);
        }

        public void unbind() {
            LayoutTopViewHolder layoutTopViewHolder = this.target;
            if (layoutTopViewHolder != null) {
                this.target = null;
                layoutTopViewHolder.mLayoutMain = null;
                layoutTopViewHolder.mLayoutTop = null;
                layoutTopViewHolder.mBack = null;
                layoutTopViewHolder.mRoomName = null;
                layoutTopViewHolder.mRefreshIduState = null;
                layoutTopViewHolder.mLastUpdatedOnTitle = null;
                layoutTopViewHolder.mLayoutTemprature = null;
                layoutTopViewHolder.mRoomTemperature = null;
                layoutTopViewHolder.mRoomTemperatureUnit = null;
                layoutTopViewHolder.mImageViewTemperature = null;
                layoutTopViewHolder.mImageViewSun = null;
                layoutTopViewHolder.mRoomTemperatureTitle = null;
                layoutTopViewHolder.mAreaName = null;
                layoutTopViewHolder.mWeatherType = null;
                this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
                this.view7f0a011e = null;
                this.view7f0a0408.setOnClickListener((View.OnClickListener) null);
                this.view7f0a0408 = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class SwingOptionsViewHolder_ViewBinding implements Unbinder {
        private SwingOptionsViewHolder target;
        private View view7f0a05d5;
        private View view7f0a05d6;
        private View view7f0a05d7;
        private View view7f0a05d8;

        public SwingOptionsViewHolder_ViewBinding(final SwingOptionsViewHolder swingOptionsViewHolder, View view) {
            this.target = swingOptionsViewHolder;
            swingOptionsViewHolder.mOuterLayoutSwing = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.outer_layout_swing, "field 'mOuterLayoutSwing'", ConstraintLayout.class);
            swingOptionsViewHolder.mExpandableLayoutSwing = (ExpandableLayout) C0840Utils.findRequiredViewAsType(view, R.id.expandable_layout_swing, "field 'mExpandableLayoutSwing'", ExpandableLayout.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_swing_off, "field 'mSwingOff' and method 'onClickSwingSpeed'");
            swingOptionsViewHolder.mSwingOff = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_swing_off, "field 'mSwingOff'", ConstraintLayout.class);
            this.view7f0a05d7 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    swingOptionsViewHolder.onClickSwingSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickSwingSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.layout_swing_vertical, "field 'mSwingVertical' and method 'onClickSwingSpeed'");
            swingOptionsViewHolder.mSwingVertical = (ConstraintLayout) C0840Utils.castView(findRequiredView2, R.id.layout_swing_vertical, "field 'mSwingVertical'", ConstraintLayout.class);
            this.view7f0a05d8 = findRequiredView2;
            findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    swingOptionsViewHolder.onClickSwingSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickSwingSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.layout_swing_horizontal, "field 'mSwingHorizontal' and method 'onClickSwingSpeed'");
            swingOptionsViewHolder.mSwingHorizontal = (ConstraintLayout) C0840Utils.castView(findRequiredView3, R.id.layout_swing_horizontal, "field 'mSwingHorizontal'", ConstraintLayout.class);
            this.view7f0a05d6 = findRequiredView3;
            findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    swingOptionsViewHolder.onClickSwingSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickSwingSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.layout_swing_horizintal_and_vertical, "field 'mSwingHorizontalAndVertical' and method 'onClickSwingSpeed'");
            swingOptionsViewHolder.mSwingHorizontalAndVertical = (ConstraintLayout) C0840Utils.castView(findRequiredView4, R.id.layout_swing_horizintal_and_vertical, "field 'mSwingHorizontalAndVertical'", ConstraintLayout.class);
            this.view7f0a05d5 = findRequiredView4;
            findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    swingOptionsViewHolder.onClickSwingSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickSwingSpeed", 0, ConstraintLayout.class));
                }
            });
            swingOptionsViewHolder.mSwingAuto = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_swing_auto, "field 'mSwingAuto'", ConstraintLayout.class);
        }

        public void unbind() {
            SwingOptionsViewHolder swingOptionsViewHolder = this.target;
            if (swingOptionsViewHolder != null) {
                this.target = null;
                swingOptionsViewHolder.mOuterLayoutSwing = null;
                swingOptionsViewHolder.mExpandableLayoutSwing = null;
                swingOptionsViewHolder.mSwingOff = null;
                swingOptionsViewHolder.mSwingVertical = null;
                swingOptionsViewHolder.mSwingHorizontal = null;
                swingOptionsViewHolder.mSwingHorizontalAndVertical = null;
                swingOptionsViewHolder.mSwingAuto = null;
                this.view7f0a05d7.setOnClickListener((View.OnClickListener) null);
                this.view7f0a05d7 = null;
                this.view7f0a05d8.setOnClickListener((View.OnClickListener) null);
                this.view7f0a05d8 = null;
                this.view7f0a05d6.setOnClickListener((View.OnClickListener) null);
                this.view7f0a05d6 = null;
                this.view7f0a05d5.setOnClickListener((View.OnClickListener) null);
                this.view7f0a05d5 = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class FanSpeedOptionsViewHolder_ViewBinding implements Unbinder {
        private FanSpeedOptionsViewHolder target;
        private View view7f0a0579;
        private View view7f0a057a;
        private View view7f0a057b;
        private View view7f0a057c;
        private View view7f0a057d;
        private View view7f0a057e;

        public FanSpeedOptionsViewHolder_ViewBinding(final FanSpeedOptionsViewHolder fanSpeedOptionsViewHolder, View view) {
            this.target = fanSpeedOptionsViewHolder;
            fanSpeedOptionsViewHolder.mOuterLayoutFan = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.outer_layout_fan, "field 'mOuterLayoutFan'", ConstraintLayout.class);
            fanSpeedOptionsViewHolder.mExpandableLayoutFan = (ExpandableLayout) C0840Utils.findRequiredViewAsType(view, R.id.expandable_layout_fan, "field 'mExpandableLayoutFan'", ExpandableLayout.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_fan_speed_one, "field 'mFanSpeedOne' and method 'onClickFanSpeed'");
            fanSpeedOptionsViewHolder.mFanSpeedOne = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_fan_speed_one, "field 'mFanSpeedOne'", ConstraintLayout.class);
            this.view7f0a057c = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    fanSpeedOptionsViewHolder.onClickFanSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickFanSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.layout_fan_speed_two, "field 'mFanSpeedTwo' and method 'onClickFanSpeed'");
            fanSpeedOptionsViewHolder.mFanSpeedTwo = (ConstraintLayout) C0840Utils.castView(findRequiredView2, R.id.layout_fan_speed_two, "field 'mFanSpeedTwo'", ConstraintLayout.class);
            this.view7f0a057e = findRequiredView2;
            findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    fanSpeedOptionsViewHolder.onClickFanSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickFanSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.layout_fan_speed_three, "field 'mFanSpeedThree' and method 'onClickFanSpeed'");
            fanSpeedOptionsViewHolder.mFanSpeedThree = (ConstraintLayout) C0840Utils.castView(findRequiredView3, R.id.layout_fan_speed_three, "field 'mFanSpeedThree'", ConstraintLayout.class);
            this.view7f0a057d = findRequiredView3;
            findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    fanSpeedOptionsViewHolder.onClickFanSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickFanSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.layout_fan_speed_four, "field 'mFanSpeedFour' and method 'onClickFanSpeed'");
            fanSpeedOptionsViewHolder.mFanSpeedFour = (ConstraintLayout) C0840Utils.castView(findRequiredView4, R.id.layout_fan_speed_four, "field 'mFanSpeedFour'", ConstraintLayout.class);
            this.view7f0a057b = findRequiredView4;
            findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    fanSpeedOptionsViewHolder.onClickFanSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickFanSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView5 = C0840Utils.findRequiredView(view, R.id.layout_fan_speed_five, "field 'mFanSpeedFive' and method 'onClickFanSpeed'");
            fanSpeedOptionsViewHolder.mFanSpeedFive = (ConstraintLayout) C0840Utils.castView(findRequiredView5, R.id.layout_fan_speed_five, "field 'mFanSpeedFive'", ConstraintLayout.class);
            this.view7f0a057a = findRequiredView5;
            findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    fanSpeedOptionsViewHolder.onClickFanSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickFanSpeed", 0, ConstraintLayout.class));
                }
            });
            View findRequiredView6 = C0840Utils.findRequiredView(view, R.id.layout_fan_speed_auto, "field 'mFanSpeedAuto' and method 'onClickFanSpeed'");
            fanSpeedOptionsViewHolder.mFanSpeedAuto = (ConstraintLayout) C0840Utils.castView(findRequiredView6, R.id.layout_fan_speed_auto, "field 'mFanSpeedAuto'", ConstraintLayout.class);
            this.view7f0a0579 = findRequiredView6;
            findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    fanSpeedOptionsViewHolder.onClickFanSpeed((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickFanSpeed", 0, ConstraintLayout.class));
                }
            });
            fanSpeedOptionsViewHolder.mFanArray = view.getContext().getResources().getStringArray(R.array.idu_array_fan);
        }

        public void unbind() {
            FanSpeedOptionsViewHolder fanSpeedOptionsViewHolder = this.target;
            if (fanSpeedOptionsViewHolder != null) {
                this.target = null;
                fanSpeedOptionsViewHolder.mOuterLayoutFan = null;
                fanSpeedOptionsViewHolder.mExpandableLayoutFan = null;
                fanSpeedOptionsViewHolder.mFanSpeedOne = null;
                fanSpeedOptionsViewHolder.mFanSpeedTwo = null;
                fanSpeedOptionsViewHolder.mFanSpeedThree = null;
                fanSpeedOptionsViewHolder.mFanSpeedFour = null;
                fanSpeedOptionsViewHolder.mFanSpeedFive = null;
                fanSpeedOptionsViewHolder.mFanSpeedAuto = null;
                this.view7f0a057c.setOnClickListener((View.OnClickListener) null);
                this.view7f0a057c = null;
                this.view7f0a057e.setOnClickListener((View.OnClickListener) null);
                this.view7f0a057e = null;
                this.view7f0a057d.setOnClickListener((View.OnClickListener) null);
                this.view7f0a057d = null;
                this.view7f0a057b.setOnClickListener((View.OnClickListener) null);
                this.view7f0a057b = null;
                this.view7f0a057a.setOnClickListener((View.OnClickListener) null);
                this.view7f0a057a = null;
                this.view7f0a0579.setOnClickListener((View.OnClickListener) null);
                this.view7f0a0579 = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    /* access modifiers changed from: private */
    public boolean isPermissionAvailable(String str) {
        Boolean bool;
        HashMap<String, Boolean> hashMap = this.mRacWisePermissionMap;
        if (hashMap == null || (bool = hashMap.get(str)) == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void stopSwipeRefresh() {
        this.pullToRefresh.setEnabled(true);
        this.pullToRefresh.setRefreshing(false);
    }

    /* access modifiers changed from: private */
    public boolean isRacConfigAvailable() {
        return this.mRacModelWiseData != null;
    }

    /* access modifiers changed from: private */
    public void showRacConfigNotAvailableAlert() {
        if (this.mRacModelWiseData == null) {
            dismissSimpleAlertIfVisible();
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
            this.mSimpleAlertDialog = singleChoiceDialog;
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            this.mSimpleAlertDialog.setMessageString(getString(R.string.common_alert_unableToFindRacConfig));
            this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_alert_backToHome), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda6(this));
            this.mSimpleAlertDialog.show();
        }
    }

    /* renamed from: lambda$showRacConfigNotAvailableAlert$0$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30953xb044e695(Dialog dialog, View view) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return true;
    }

    public void fetchPermissionResponseDatas(Response<AllPermissionDataDto> response) {
        if (response != null) {
            int code = response.code();
            if (code != 200) {
                if (code == 401) {
                    this.permissionPresenter.performInitTask(this, true, this.permissionViewModel);
                } else if (code != 404) {
                    dismissPleaseWaitDialog();
                    Toast.makeText(getContext(), getString(R.string.common_alert_couldnotFetchPermsData), 1).show();
                } else {
                    dismissPleaseWaitDialog();
                    Toast.makeText(getContext(), getString(R.string.common_alert_couldnotFetchPermsData), 1).show();
                }
            } else if (response.body() != null) {
                this.mRacWisePermissionMap = racWisePermission(PermissionFactory.getInstance().cookUserPermission(response.body(), this.mDetailedIduModel.f454id), FamilyGroupList.getCurrentFamily().role.f480id);
                Logger.m49i("", "rac wise per = " + this.mRacWisePermissionMap);
                updateUIMain(this.mDetailedIduModel);
            }
        } else {
            dismissPleaseWaitDialog();
            Toast.makeText(getContext(), getString(R.string.common_alert_couldnotFetchPermsData), 1).show();
        }
        getCoreActivity().dismissPleaseWaitDialog();
    }

    private HashMap<String, Boolean> racWisePermission(List<PermissionModel> list, int i) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        FamilyGroupsModels.Role.UserRole userRoleFromRoleId = FamilyGroupsModels.Role.getUserRoleFromRoleId(i);
        for (PermissionModel next : list) {
            if (userRoleFromRoleId.equals(FamilyGroupsModels.Role.UserRole.MEMBER) || userRoleFromRoleId.equals(FamilyGroupsModels.Role.UserRole.CHILD)) {
                hashMap.put(next.permissionName, next.levelWisePermission[i - 1]);
            } else if (userRoleFromRoleId.equals(FamilyGroupsModels.Role.UserRole.OWNER)) {
                hashMap.put(next.permissionName, true);
            }
        }
        return hashMap;
    }

    public void onUserFamilyGroupFetchSuccess(FamilyGroupsModels.FamilyGroupsModelResponseSuccess familyGroupsModelResponseSuccess) {
        FamilyGroupList familyGroupList = this.mFragmentToActivityCallback.getFamilyGroupList();
        familyGroupList.updateList(familyGroupsModelResponseSuccess.familyResult);
        familyGroupList.updateCurrentFamily(getCoreActivity());
        this.permissionPresenter.performInitTask(requireActivity(), true, this.permissionViewModel);
    }

    public void onUserFamilyGroupFetchFailure(FamilyGroupsModels.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure) {
        if (familyGroupsModelResponseSuccessFailure.code != null) {
            String str = familyGroupsModelResponseSuccessFailure.code;
            str.hashCode();
            if (str.equals("NFE002")) {
                Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE002), 0).show();
            } else if (!str.equals("NFE013")) {
                Toast.makeText(getContext(), getResources().getString(R.string.myAcc_alert_unableToFetchFamily), 1).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE013), 0).show();
            }
        } else {
            callRefreshApiFamilyGroup(familyGroupsModelResponseSuccessFailure.statusCode);
        }
        Logger.m47e("Family_Group_API", "failureResponse" + familyGroupsModelResponseSuccessFailure.statusCode);
    }

    private void callRefreshApiFamilyGroup(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                IndividualIDUControlFragmentModelWise.this.callRefreshFamilyGroup();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callRefreshFamilyGroup() {
        dismissPleaseWaitDialog();
        this.mGetFamilyGroupPresenter.getFamilyGroup();
    }

    public void onGetWeatherDataSuccess(WeatherDataModel.WeatherDataModelResponseSuccess weatherDataModelResponseSuccess) {
        this.mCurrentWeatherInfo = weatherDataModelResponseSuccess;
        this.layoutTopViewHolder.setWeatherData(weatherDataModelResponseSuccess);
    }

    public void onGetWeatherDataFailure(WeatherDataModel.WeatherDataModelFailureResponse weatherDataModelFailureResponse) {
        Logger.m47e("Weather", weatherDataModelFailureResponse + "");
        this.layoutTopViewHolder.setWeatherData((WeatherDataModel.WeatherDataModelResponseSuccess) null);
        callRefreshApiForWeather(weatherDataModelFailureResponse.statusCode);
    }

    private void callRefreshApiForWeather(int i) {
        if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    IndividualIDUControlFragmentModelWise.this.callRefreshWeather();
                }
            }, false);
        }
    }

    /* access modifiers changed from: private */
    public void callRefreshWeather() {
        dismissPleaseWaitDialog();
        this.weatherDataPresenter.getWeatherDataForRac(this.mDetailedIduModel.f454id.intValue(), LocaleConfiguration.getLanguageCodeForCurrentLocale());
    }

    private class CommandDoneStatusRunnable implements Runnable {
        public static final int COMMAND_STATUS_DONE_HANDLER_TIMEOUT = 20000;
        private int commandCount;

        private CommandDoneStatusRunnable() {
            this.commandCount = 0;
        }

        /* access modifiers changed from: private */
        public boolean isCommandsInQueue() {
            return this.commandCount != 0;
        }

        /* access modifiers changed from: private */
        public void addCommandQueueCount() {
            this.commandCount++;
        }

        /* access modifiers changed from: private */
        public void decrementCommandQueueCount() {
            int i = this.commandCount;
            this.commandCount = i > 0 ? i - 1 : 0;
        }

        public void reset() {
            this.commandCount = 0;
        }

        public void run() {
            if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                reset();
            }
        }
    }

    private void scheduleCommandStatusDoneHandler() {
        Logger.m47e("done_status_handler", "started");
        this.mAfterCommandDoneStatusHandler.removeCallbacksAndMessages((Object) null);
        this.mAfterCommandDoneStatusHandler.postDelayed(this.mCommandDoneStatusRunnable, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    /* access modifiers changed from: private */
    public void stopCommandStatusDoneHandler() {
        Logger.m47e("done_status_handler", "stopped");
        this.mAfterCommandDoneStatusHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public void dimDisplay() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            scheduleDimDisplayHandler();
            this.holdBackButton = true;
            this.mParent.setEnabled(true);
            this.mParent.setClickable(true);
        }
    }

    /* access modifiers changed from: private */
    public void dipDisplay() {
        removeDimDisplayHandler();
        this.holdBackButton = false;
        this.mParent.setEnabled(false);
        this.mParent.setClickable(false);
    }

    private void scheduleDimDisplayHandler() {
        this.mDimScreenOperationHandler.removeCallbacksAndMessages((Object) null);
        this.mDimScreenOperationHandler.postDelayed(new Runnable() {
            public void run() {
                IndividualIDUControlFragmentModelWise.this.dipDisplay();
                IndividualIDUControlFragmentModelWise.this.stopSwipeRefresh();
                IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.stopRefreshButtonRotation();
                IndividualIDUControlFragmentModelWise.this.mCommandDoneStatusRunnable.reset();
                IndividualIDUControlFragmentModelWise.this.dismissSimpleAlertIfVisible();
                IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog = new SingleChoiceDialog(IndividualIDUControlFragmentModelWise.this.requireActivity());
                IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setTitleString(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_alert));
                IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setMessageString(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_alert_somethingWentWrong));
                IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setCancelable(false);
                IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.setPositiveButton(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                    public boolean onButtonClickListener(Dialog dialog, View view) {
                        if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                            IndividualIDUControlFragmentModelWise.this.updateUIMain(IndividualIDUControlFragmentModelWise.this.mDetailedIduModelPrevState, true);
                        }
                        return true;
                    }
                });
                if (!IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.isShowing()) {
                    IndividualIDUControlFragmentModelWise.this.mSimpleAlertDialog.show();
                }
            }
        }, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    private void removeDimDisplayHandler() {
        this.mDimScreenOperationHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public void handleError() {
        if (this.mDetailedIduModel.isInErrorState()) {
            showError(this.mDetailedIduModel.iduErrorStatus);
            return;
        }
        this.mParentOuter.removeAllViews();
        this.mNotificationPopupWrapper.showPopupsBasedOnIduState(requireActivity(), this.mDetailedIduModel);
    }

    private void showError(DetailedIduModel.IduErrorStatus iduErrorStatus) {
        Banner banner = this.mBinder.getBanner(iduErrorStatus, new BannerListener<DetailedIduModel.IduErrorStatus, LayoutErrorDisplayBinding>() {
            public void onClick(View view, DetailedIduModel.IduErrorStatus iduErrorStatus, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
                IndividualIDUControlFragmentModelWise.this.mParentOuter.removeAllViews();
            }

            public void onItemClick(View view, DetailedIduModel.IduErrorStatus iduErrorStatus, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.Keys.DETAILED_IDU_MODEL, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                bundle.putString("vendorThingId", IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getVendorThingId());
                bundle.putString(Constants.FCM.ERROR_CODE, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduErrorStatus.errorCode);
                bundle.putString(Constants.FCM.SUB_CATEGORY, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduErrorStatus.subCategory);
                bundle.putString(Constants.FCM.RAC_NAME, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getName());
                bundle.putLong("racId", (long) IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getId().intValue());
                bundle.putInt(Constants.FCM.FAMILY_ID, FamilyGroupList.getCurrentFamily().familyId);
                bundle.putParcelable(Constants.Keys.ERROR_STATUS, iduErrorStatus);
                IndividualIDUControlFragmentModelWise.this.getCoreActivity().getNavController().navigate((int) R.id.action_individualIDUControlFragment_to_customerCareFragment, bundle);
            }
        }, true);
        if (this.mDetailedIduModel.isOnline()) {
            this.mParentOuter.removeAllViews();
        }
        View root = banner.getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mParentOuter.addView(root);
    }

    /* access modifiers changed from: private */
    public void dismissSimpleAlertIfVisible() {
        SingleChoiceDialog singleChoiceDialog = this.mSimpleAlertDialog;
        if (singleChoiceDialog != null && singleChoiceDialog.isShowing()) {
            this.mSimpleAlertDialog.dismiss();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        incomingIntent(requireActivity().getIntent());
        if (getActivity() != null) {
            FcmViewModel fcmViewModel = (FcmViewModel) new ViewModelProvider((ViewModelStoreOwner) getActivity(), (ViewModelProvider.Factory) this.providerFactory).get(FcmViewModel.class);
            this.mFcmViewModel = fcmViewModel;
            fcmViewModel.getErrorLiveData().removeObservers(getViewLifecycleOwner());
            this.mFcmViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda0(this));
            this.mFcmViewModel.getAlertLiveEvent().observe(getViewLifecycleOwner(), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda11(this));
        }
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ void mo30951x2845ed93(List list) {
        if (list != null && !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Error error = (Error) it.next();
                if (error.getRacId() != null && this.mDetailedIduModel.f454id.intValue() == error.getRacId().intValue()) {
                    commonTask(error, (CallBackListener) new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                        }
                    });
                }
            }
            if (!this.isErrorShown) {
                if (this.mDetailedIduModel.isOnline()) {
                    this.mParentOuter.removeAllViews();
                }
                this.mFcmViewModel.getAcActivitiesLiveEvent().observe(getViewLifecycleOwner(), this.acActivitiesListObserver);
                return;
            }
            this.mFcmViewModel.getAcActivitiesLiveEvent().removeObserver(this.acActivitiesListObserver);
        }
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ void mo30952xcfc1c754(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Alert alert = (Alert) it.next();
            if (!(alert.getRacId() == null || this.mDetailedIduModel.f454id.intValue() != alert.getRacId().intValue() || alert.getSubCategory() == null)) {
                switch (C213825.$SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory[alert.getSubCategory().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        this.mFragmentToActivityCallback.refreshIndividualIduState(getCoreActivity(), this.mDetailedIduModel.f454id.intValue());
                        break;
                    case 9:
                        this.mFragmentToActivityCallback.refreshIndividualIduState(getCoreActivity(), this.mDetailedIduModel.f454id.intValue());
                        break;
                }
            }
        }
    }

    private void commonTask(Error error, final CallBackListener callBackListener) {
        Banner banner = this.mBinder.getBanner(error, (BannerListener<Error, LayoutErrorDisplayBinding>) new BannerListener<Error, LayoutErrorDisplayBinding>() {
            public void onClick(View view, Error error, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
                IndividualIDUControlFragmentModelWise.this.mParentOuter.removeAllViews();
                IndividualIDUControlFragmentModelWise.this.clearNotification(error.getId(), new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        IndividualIDUControlFragmentModelWise.this.getAllNotifications(callBackListener);
                    }
                });
            }

            public void onItemClick(View view, Error error, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
                Bundle bundle = error.getBundle();
                bundle.putParcelable(Constants.Keys.DETAILED_IDU_MODEL, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                bundle.putString("vendorThingId", IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getVendorThingId());
                bundle.putString(Constants.FCM.RAC_NAME, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getName());
                bundle.putString(Constants.FCM.ERROR_CODE, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduErrorStatus.errorCode);
                bundle.putString(Constants.FCM.SUB_CATEGORY, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduErrorStatus.subCategory);
                bundle.putLong("racId", (long) IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getId().intValue());
                bundle.putInt(Constants.FCM.FAMILY_ID, FamilyGroupList.getCurrentFamily().familyId);
                IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_individualIDUControlFragment_to_customerCareFragment, bundle);
                IndividualIDUControlFragmentModelWise.this.mHandlerListener.onDelay(200, IndividualIDUControlFragmentModelWise.this.mParentOuter);
            }
        });
        if (this.mDetailedIduModel.isOnline()) {
            this.mParentOuter.removeAllViews();
        }
        View root = banner.getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mParentOuter.addView(root);
        this.isErrorShown = true;
    }

    /* access modifiers changed from: private */
    public void clearNotification(String str, CallBackListener callBackListener) {
        this.mFcmViewModel.clearNotification(str).removeObservers(getViewLifecycleOwner());
        this.mFcmViewModel.clearNotification(str).observe(getViewLifecycleOwner(), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda14(this, str, callBackListener));
    }

    /* renamed from: lambda$clearNotification$3$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ void mo30939x7e102c49(final String str, final CallBackListener callBackListener, Resource resource) {
        if (resource != null) {
            int i = C213825.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(TAG, "clearNotification: Error - " + resource.message);
                if (((FcmResponse) resource.response).getMeta().getCode() == 401) {
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            IndividualIDUControlFragmentModelWise.this.clearNotification(str, callBackListener);
                        }
                    }, false);
                }
            } else if (i == 2) {
                Logger.m47e(TAG, "clearNotification: Loading - " + resource.message);
            } else if (i == 3) {
                Logger.m47e(TAG, "clearNotification: SUCCESS - " + resource.message);
                Integer value = this.mFcmViewModel.getNotificationCountLiveData().getValue();
                if (value != null && value.intValue() > 0) {
                    this.mFcmViewModel.getNotificationCountLiveData().setValue(Integer.valueOf(value.intValue() - 1));
                }
                callBackListener.onSuccess();
            }
        }
    }

    /* access modifiers changed from: private */
    public void getAllNotifications(CallBackListener callBackListener) {
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            this.mFcmViewModel.getNotifications().removeObservers(getViewLifecycleOwner());
            this.mFcmViewModel.getNotifications().observe(getViewLifecycleOwner(), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda12(this, callBackListener));
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.viewImpl.IndividualIDUControlFragmentModelWise$25 */
    static /* synthetic */ class C213825 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$di$model$Resource$Status;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$Type;

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$model$RacModelWiseData$TemperatureSettingType */
        static final /* synthetic */ int[] f473xf7673082;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|(2:21|22)|23|25|26|(2:27|28)|29|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|(3:47|48|50)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|(2:21|22)|23|25|26|(2:27|28)|29|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|(3:47|48|50)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(41:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|25|26|(2:27|28)|29|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|50) */
        /* JADX WARNING: Can't wrap try/catch for region: R(44:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|21|22|23|25|26|27|28|29|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|50) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0094 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00a9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00bf */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00ca */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00d6 */
        static {
            /*
                com.jch.racWiFi.di.model.Resource$Status[] r0 = com.jch.racWiFi.p010di.model.Resource.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$di$model$Resource$Status = r0
                r1 = 1
                com.jch.racWiFi.di.model.Resource$Status r2 = com.jch.racWiFi.p010di.model.Resource.Status.ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.di.model.Resource$Status r3 = com.jch.racWiFi.p010di.model.Resource.Status.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.di.model.Resource$Status r4 = com.jch.racWiFi.p010di.model.Resource.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode[] r3 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode = r3
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r4 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r3 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r4 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r3 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x004d }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r4 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x004d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType[] r3 = com.jch.racWiFi.iduManagement.model.RacModelWiseData.TemperatureSettingType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f473xf7673082 = r3
                com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r4 = com.jch.racWiFi.iduManagement.model.RacModelWiseData.TemperatureSettingType.RELATIVE     // Catch:{ NoSuchFieldError -> 0x005e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                com.jch.racWiFi.fcm.util.Type[] r3 = com.jch.racWiFi.fcm.util.Type.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$jch$racWiFi$fcm$util$Type = r3
                com.jch.racWiFi.fcm.util.Type r4 = com.jch.racWiFi.fcm.util.Type.ERRORS     // Catch:{ NoSuchFieldError -> 0x006f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                int[] r3 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.jch.racWiFi.fcm.util.Type r4 = com.jch.racWiFi.fcm.util.Type.ALERTS     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                com.jch.racWiFi.fcm.util.AlertSubCategory[] r3 = com.jch.racWiFi.fcm.util.AlertSubCategory.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory = r3
                com.jch.racWiFi.fcm.util.AlertSubCategory r4 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_UPDATED     // Catch:{ NoSuchFieldError -> 0x008a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x008a }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x008a }
            L_0x008a:
                int[] r1 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0094 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r3 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_ALL     // Catch:{ NoSuchFieldError -> 0x0094 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0094 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0094 }
            L_0x0094:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x009e }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x009e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009e }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009e }
            L_0x009e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00a9 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x00a9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a9 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a9 }
            L_0x00a9:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_ALL     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00bf }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_INTERRUPTION     // Catch:{ NoSuchFieldError -> 0x00bf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bf }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00bf }
            L_0x00bf:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00ca }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_MULTIPLE_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x00ca }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ca }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ca }
            L_0x00ca:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00d6 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x00d6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d6 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d6 }
            L_0x00d6:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00e2 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.RAC_OFFLINE     // Catch:{ NoSuchFieldError -> 0x00e2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e2 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e2 }
            L_0x00e2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.viewImpl.IndividualIDUControlFragmentModelWise.C213825.<clinit>():void");
        }
    }

    /* renamed from: lambda$getAllNotifications$4$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ void mo30945x7c3f252f(final CallBackListener callBackListener, Resource resource) {
        if (resource != null) {
            int i = C213825.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(TAG, "getAllNotifications: Error - " + resource.message);
                if (resource.response != null && ((FcmResponse) resource.response).getMeta().getCode() == 401) {
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            IndividualIDUControlFragmentModelWise.this.getAllNotifications(callBackListener);
                        }
                    }, false);
                }
            } else if (i == 2) {
                Logger.m47e(TAG, "getAllNotifications: Loading - " + resource.message);
            } else if (i == 3) {
                Logger.m47e(TAG, "getAllNotifications: persistToken: Success");
                callBackListener.onSuccess();
            }
        }
    }

    private void incomingIntent(final Intent intent) {
        Bundle extras;
        DeepLink deepLink;
        Alert alert;
        if (intent != null && (extras = intent.getExtras()) != null && (deepLink = (DeepLink) extras.getParcelable(DeepLink.PARCEL_KEY)) != null) {
            int i = C213825.$SwitchMap$com$jch$racWiFi$fcm$util$Type[deepLink.getType().ordinal()];
            if (i == 1) {
                Error error = (Error) extras.getParcelable(Error.PARCEL_KEY);
                if (error != null) {
                    commonTask(error, (CallBackListener) new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            intent.removeExtra(DeepLink.PARCEL_KEY);
                            intent.removeExtra(Error.PARCEL_KEY);
                        }
                    });
                }
            } else if (i == 2 && (alert = (Alert) extras.getParcelable(Alert.PARCEL_KEY)) != null) {
                int i2 = C213825.$SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory[alert.getSubCategory().ordinal()];
            }
        }
    }

    static /* synthetic */ void lambda$new$5(FrameLayout frameLayout) {
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.showAlertDialog = new SingleChoiceDialog(requireActivity());
        Bundle arguments = getArguments();
        if (arguments != null) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) arguments.getParcelable(DetailedIduModel.PARCEL_KEY);
            if (detailedIduModel != null) {
                this.mDetailedIduModel.copy(detailedIduModel);
                this.mDetailedIduModelPrevState.copy(this.mDetailedIduModel);
            }
            this.weather = (Weather) arguments.getParcelable(DetailedIduModel.WEATHER_DETAILS);
        }
        this.savingBehaviourDialog = new SavingBehaviourDialog(requireActivity());
        this.mRacModelWiseData = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.mDetailedIduModel.cloudId);
        UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel2 = new UserPermissionsManageUsersFragment.PermissionViewModel();
        this.permissionViewModel = permissionViewModel2;
        permissionViewModel2.iduName = this.mDetailedIduModel.name;
        this.permissionViewModel.iduId = this.mDetailedIduModel.f454id.intValue();
        this.permissionViewModel.mode = 2;
        showRacConfigNotAvailableAlert();
        if (NetworkConnectivity.isNetworkAvailable(requireActivity().getApplicationContext())) {
            this.permissionPresenter.performInitTask(this, true, this.permissionViewModel);
        }
        this.mNotificationPopupWrapper.setIduNotificationList(this.mFragmentToActivityCallback, this.mFragmentToActivityCallback.getIduNotificationAdapter().getIduNotificationItemsList());
    }

    private void showNetworkCheckAlert() {
        dismissSimpleAlertIfVisible();
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                return true;
            }
        });
        singleChoiceDialog.show();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.idu_control_screen_model_wise, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mGetFamilyGroupPresenter = new GetFamilyGroupPresenter(this);
        this.weatherDataPresenter = new WeatherDataPresenter(this);
        enableDisableTimerIndicator();
        this.mNotificationPopupWrapper.setOnNotificationShowCallBack(new NotificationPopupWrapper.OnNotificationShowCallBack() {
            public void onNotificationShowCallBack(IDUNotificationType iDUNotificationType) {
                IndividualIDUControlFragmentModelWise.this.mViewPopUp.setVisibility(0);
            }

            public void onNotificationDismissCallBack(IDUNotificationType iDUNotificationType) {
                if (!IndividualIDUControlFragmentModelWise.this.mNotificationPopupWrapper.isAnyBannerShowing()) {
                    IndividualIDUControlFragmentModelWise.this.mViewPopUp.setVisibility(8);
                }
            }
        });
        handleError();
        this.pullToRefresh.setOnRefreshListener(new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda15(this));
        this.temperatureLayoutViewHolder = new TemperatureLayoutViewHolder();
        this.offStateViewHolder = new OffStateViewHolder();
        this.selectModesViewHolder = new SelectModesViewHolder();
        this.gridOptionsViewHolder = new GridOptionsViewHolder();
        this.mainSwitchViewHolder = new MainSwitchViewHolder();
        this.layoutTopViewHolder = new LayoutTopViewHolder();
        this.holidayModeViewHolder = new HolidayModeViewHolder();
        this.fanSpeedOptionsViewHolder = new FanSpeedOptionsViewHolder();
        this.swingOptionsViewHolder = new SwingOptionsViewHolder();
        this.mAdvancedOptionViewHolder = new AdvancedOptionViewHolder();
        IndividualIDUControlPresenterV2 individualIDUControlPresenterV2 = new IndividualIDUControlPresenterV2(this, new IndividualIDUControlPresenterV2.ITemperatureHumidityCommandCallback() {
            public void onTemperatureCommandSentCallback() {
                if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    if (IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder != null) {
                        IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.mIncreaseTemperatureRepeatListener.cleanUp();
                        IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.mDecreaseTemperatureRepeatListener.cleanUp();
                        IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.isTemperatureChangeInProcess = false;
                    }
                    IndividualIDUControlFragmentModelWise.this.dimDisplay();
                    IndividualIDUControlFragmentModelWise.this.mCommandDoneStatusRunnable.addCommandQueueCount();
                    IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.startRefreshButtonRotation();
                }
            }

            public void onHumidityCommandSentCallback() {
                if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    if (IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder != null) {
                        IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.mIncreaseTemperatureRepeatListener.cleanUp();
                        IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.mDecreaseTemperatureRepeatListener.cleanUp();
                        IndividualIDUControlFragmentModelWise.this.temperatureLayoutViewHolder.isTemperatureChangeInProcess = false;
                    }
                    IndividualIDUControlFragmentModelWise.this.dimDisplay();
                    IndividualIDUControlFragmentModelWise.this.mCommandDoneStatusRunnable.addCommandQueueCount();
                    IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.startRefreshButtonRotation();
                }
            }

            public void onAnyOtherCommandSentCallback() {
                if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise = IndividualIDUControlFragmentModelWise.this;
                    individualIDUControlFragmentModelWise.updateUIMain(individualIDUControlFragmentModelWise.mDetailedIduModel);
                    new Handler().postDelayed(new C2184x96f516f7(this), 200);
                    IndividualIDUControlFragmentModelWise.this.mCommandDoneStatusRunnable.addCommandQueueCount();
                    IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.startRefreshButtonRotation();
                }
            }

            /* renamed from: lambda$onAnyOtherCommandSentCallback$0$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise$16 */
            public /* synthetic */ void mo30960x5a7e4fd6() {
                if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    IndividualIDUControlFragmentModelWise.this.dimDisplay();
                }
            }
        }, getViewLifecycleOwner());
        this.mIndividualIDUControlPresenter = individualIDUControlPresenterV2;
        individualIDUControlPresenterV2.setDetailedIduModel(this.mDetailedIduModel);
        this.mIndividualIDUControlPresenter.setRacModelWiseData(this.mRacModelWiseData);
        this.mIndividualIDUControlPresenter.registerEventBus();
        this.gridOptionsViewHolder.onCreateView(inflate);
        this.temperatureLayoutViewHolder.onCreateView(inflate);
        this.offStateViewHolder.onCreateView(inflate);
        this.selectModesViewHolder.onCreateView(inflate);
        this.mainSwitchViewHolder.onCreateView(inflate);
        this.layoutTopViewHolder.onCreateView(inflate);
        this.fanSpeedOptionsViewHolder.onCreateView(inflate);
        this.swingOptionsViewHolder.onCreateView(inflate);
        this.mAdvancedOptionViewHolder.onCreateView(inflate);
        this.holidayModeViewHolder.onCreateView(inflate);
        this.gridOptionsViewHolder.getOptionsGridLayoutAdapter().setDetailedIduModel(this.mDetailedIduModel);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (!IndividualIDUControlFragmentModelWise.this.updatingUI && !IndividualIDUControlFragmentModelWise.this.holdBackButton && IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController() != null) {
                    IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigateUp();
                }
            }
        });
        onModelWiseRacInfoAvailable(this.mRacModelWiseData, this.mDetailedIduModel);
        Logger.m49i("", "SetTimerFragmentV2 Schedule Type 4 " + this.mDetailedIduModel.scheduletype);
        this.weatherDataPresenter.getWeatherDataForRac(this.mDetailedIduModel.f454id.intValue(), LocaleConfiguration.getLanguageCodeForCurrentLocale());
        return inflate;
    }

    /* renamed from: lambda$onCreateView$7$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ void mo30946x37ed4ccf() {
        this.pullToRefresh.setEnabled(false);
        this.pullToRefresh.setRefreshing(true);
        this.layoutTopViewHolder.onClickRefresh();
    }

    public void enableDisableTimerIndicator() {
        DetailedIduModel detailedIduModel = this.mDetailedIduModel;
        if (detailedIduModel != null && detailedIduModel.isTimerEnabled()) {
            TimerViewModel timerViewModel = (TimerViewModel) ViewModelProviders.m37of(requireActivity()).get(TimerViewModel.class);
            timerViewModel.getTimerSchedule(this.mDetailedIduModel.f454id.intValue(), false);
            timerViewModel.getTimerFetchResponseMutableLiveData().observe(getViewLifecycleOwner(), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda13(this, timerViewModel));
            timerViewModel.getTimerEnabledResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<TimerEnabledModel.TimerEnabledResponse>() {
                public void onChanged(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
                    if (timerEnabledResponse.isSuccessful()) {
                        IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.refreshIndividualIduState(IndividualIDUControlFragmentModelWise.this.getCoreActivity(), IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue());
                    }
                }
            });
        }
    }

    /* renamed from: lambda$enableDisableTimerIndicator$8$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ void mo30944xb1ce385(TimerViewModel timerViewModel, TimerModels.TimerFetchResponse timerFetchResponse) {
        TimerModels.ResponseData responseData;
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && timerFetchResponse.isSuccessful() && (responseData = (TimerModels.ResponseData) timerFetchResponse.response) != null) {
            int i = C213825.$SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode[WeeklyTimerMode.valueOf(this.mDetailedIduModel.scheduletype).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3 && timerFetchResponse.isFromTimerPage && timerViewModel.getRestTimeOfExecutionInMinute(responseData.startsAt) <= 0 && timerViewModel.getRestTimeOfExecutionInMinute(responseData.endsAt) <= 0) {
                        timerViewModel.operationTimerScheduleDisable((long) this.mDetailedIduModel.f454id.intValue());
                    }
                } else if (timerViewModel.getRestTimeOfExecutionInMinute(responseData.endsAt) <= 0) {
                    timerViewModel.operationTimerScheduleDisable((long) this.mDetailedIduModel.f454id.intValue());
                }
            } else if (timerViewModel.getRestTimeOfExecutionInMinute(responseData.startsAt) <= 0) {
                timerViewModel.operationTimerScheduleDisable((long) this.mDetailedIduModel.f454id.intValue());
            }
        }
    }

    public boolean ifHolidayModeNotEnabled() {
        if (!this.mDetailedIduModel.isHolidayModeEnabled()) {
            return false;
        }
        confirmationForDisableHoliday();
        return true;
    }

    private void confirmationForDisableHoliday() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setMessageString(getResources().getString(R.string.holidayMode_alert_acRunningInHolidayMode));
        confirmationDialog.setTitleString(getActivity().getString(R.string.holidayMode_alert_stopHolidayMode));
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_yes), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda16(this));
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda17.INSTANCE);
        confirmationDialog.setParentView(this.mParentTop);
        confirmationDialog.show();
    }

    /* renamed from: lambda$confirmationForDisableHoliday$9$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30943x9015c63b(Dialog dialog, View view) {
        dialog.dismiss();
        interrputHolidayMode();
        return false;
    }

    /* access modifiers changed from: private */
    public void interrputHolidayMode() {
        HolidayModeViewModel holidayModeViewModel = (HolidayModeViewModel) ViewModelProviders.m35of((Fragment) this).get(HolidayModeViewModel.class);
        showPleaseWaitDialog();
        holidayModeViewModel.interrputToHolidayMode(this.mDetailedIduModel);
        holidayModeViewModel.interruptHolidayModeSinglLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new Observer<HolidayModeModel.HolidayModeInterruptResponse>() {
            public void onChanged(HolidayModeModel.HolidayModeInterruptResponse holidayModeInterruptResponse) {
                if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    IndividualIDUControlFragmentModelWise.this.dismissPleaseWaitDialog();
                    if (holidayModeInterruptResponse.isSuccessful()) {
                        IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.refreshIndividualIduState(IndividualIDUControlFragmentModelWise.this.getCoreActivity(), IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue());
                        Toaster.makeToast(IndividualIDUControlFragmentModelWise.this.getActivity(), IndividualIDUControlFragmentModelWise.this.getResources().getString(R.string.holidayMode_alert_disabledSuccessFully), 1);
                        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
                            IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.scheduletype = WeeklyTimerMode.SCHEDULE_DISABLED.name();
                            IndividualIDUControlFragmentModelWise.this.holidayModeViewHolder.updateUI(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                            return;
                        }
                        return;
                    }
                    int i = holidayModeInterruptResponse.statusCode;
                    if (i == 401) {
                        IndividualIDUControlFragmentModelWise.this.getCoreActivity().refreshToken(new CallBackListener() {
                            public void onFailure() {
                            }

                            public void onSuccess() {
                                IndividualIDUControlFragmentModelWise.this.interrputHolidayMode();
                            }
                        }, false);
                    } else if (i == 403) {
                        IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise = IndividualIDUControlFragmentModelWise.this;
                        individualIDUControlFragmentModelWise.showErrorPopUp(individualIDUControlFragmentModelWise.getString(R.string.errorCode_alert_FAE007));
                    } else if (i != 404) {
                        IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise2 = IndividualIDUControlFragmentModelWise.this;
                        individualIDUControlFragmentModelWise2.showErrorPopUp(individualIDUControlFragmentModelWise2.getString(R.string.errorCode_alert_somethingWentWorng));
                    } else {
                        IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise3 = IndividualIDUControlFragmentModelWise.this;
                        individualIDUControlFragmentModelWise3.showErrorPopUp(individualIDUControlFragmentModelWise3.getString(holidayModeInterruptResponse.getErrorMessageStringId(String.valueOf(holidayModeInterruptResponse.statusCode))));
                    }
                }
            }
        });
    }

    public void onStart() {
        super.onStart();
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIndividualIduUpdateSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.individualDetailedIduModelObserver);
        this.mFragmentToActivityCallback.getNotificationRequestTypeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.requestTypeObserver);
        this.mFragmentToActivityCallback.getTouchPointerCountSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mTouchPointerEventObserver);
        updateUIMain(this.mDetailedIduModel);
    }

    public void onResume() {
        super.onResume();
        this.mFragmentToActivityCallback.refreshIndividualIduState(getCoreActivity(), this.mDetailedIduModel.f454id.intValue());
    }

    public void onStop() {
        super.onStop();
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIndividualIduUpdateSingleLiveEvent().removeObserver(this.individualDetailedIduModelObserver);
        this.mFragmentToActivityCallback.getNotificationRequestTypeSingleLiveEvent().removeObserver(this.requestTypeObserver);
        this.mFragmentToActivityCallback.getTouchPointerCountSingleLiveEvent().removeObserver(this.mTouchPointerEventObserver);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mGetFamilyGroupPresenter.removeCallbacks();
        this.mIndividualIDUControlPresenter.unregisterEventBus();
        this.mIndividualIDUControlPresenter.cleanUp();
        this.weatherDataPresenter.removeCallbacks();
        this.mBinder.unBind();
        removeDimDisplayHandler();
        stopCommandStatusDoneHandler();
        this.gridOptionsViewHolder.onDestroyView();
        this.temperatureLayoutViewHolder.onDestroyView();
        this.offStateViewHolder.onDestroyView();
        this.selectModesViewHolder.onDestroyView();
        this.mainSwitchViewHolder.onDestroyView();
        this.layoutTopViewHolder.onDestroyView();
        this.fanSpeedOptionsViewHolder.onDestroyView();
        this.swingOptionsViewHolder.onDestroyView();
        this.mAdvancedOptionViewHolder.onDestroyView();
        this.holidayModeViewHolder.onDestroyView();
        this.mNotificationPopupWrapper.dismissAll();
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onIduWholeCommandUpdateSuccess() {
        super.onIduWholeCommandUpdateSuccess();
        this.selectModesViewHolder.modeChangeUnderProgress = false;
        dimDisplay();
        scheduleCommandStatusDoneHandler();
    }

    public void onIduWholeCommandUpdateFailure(int i, GenericResponse.GenericErrorBody genericErrorBody) {
        super.onIduWholeCommandUpdateFailure(i, genericErrorBody);
        String str = genericErrorBody != null ? genericErrorBody.code : null;
        this.selectModesViewHolder.modeChangeUnderProgress = false;
        this.mCommandDoneStatusRunnable.decrementCommandQueueCount();
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            updateUIMain(this.mDetailedIduModelPrevState, true);
            dipDisplay();
            stopSwipeRefresh();
            this.layoutTopViewHolder.stopRefreshButtonRotation();
            if (i == 403 || i == 404 || i == 406) {
                dismissSimpleAlertIfVisible();
                SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
                this.mSimpleAlertDialog = singleChoiceDialog;
                singleChoiceDialog.setTitleString(getString(R.string.common_alert));
                this.mSimpleAlertDialog.setMessageString(getString(R.string.idu_alert_userNotAuthorized));
                this.mSimpleAlertDialog.setCancelable(false);
                this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_alert_backToHome), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda5(this));
                this.mSimpleAlertDialog.show();
            } else if (i != 412) {
                if (i != 423) {
                    if (i == 429 && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                        dismissSimpleAlertIfVisible();
                        SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(requireActivity());
                        this.mSimpleAlertDialog = singleChoiceDialog2;
                        singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
                        this.mSimpleAlertDialog.setMessageString(getString(R.string.common_alert_prevComndIsUnderExecution));
                        this.mSimpleAlertDialog.setCancelable(false);
                        this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_btn_ok), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda4(this));
                        this.mSimpleAlertDialog.show();
                        updateUIMain(this.mDetailedIduModelPrevState, true);
                    }
                } else if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    showAlert(getString(R.string.common_alert_anotherUserOpertRac, this.mDetailedIduModel.name));
                }
            } else if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            } else {
                if (str != null) {
                    str.hashCode();
                    if (str.equals(IndividualIDUControlPresenterV2.IndividualIDUControlResponseConstants.PCF001_NOT_BELONG_TO_FAMILY)) {
                        dismissSimpleAlertIfVisible();
                        SingleChoiceDialog singleChoiceDialog3 = new SingleChoiceDialog(requireActivity());
                        this.mSimpleAlertDialog = singleChoiceDialog3;
                        singleChoiceDialog3.setTitleString(getString(R.string.common_alert));
                        this.mSimpleAlertDialog.setMessageString(getString(R.string.errorCode_alert_PCF001));
                        this.mSimpleAlertDialog.setCancelable(false);
                        this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_alert_backToHome), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda2(this));
                        if (!this.mSimpleAlertDialog.isShowing()) {
                            this.mSimpleAlertDialog.show();
                        }
                    } else if (str.equals("PCF009")) {
                        dismissSimpleAlertIfVisible();
                        commonTask(this.mDetailedIduModel, (CallBackListener) new CallBackListener() {
                            public void onFailure() {
                            }

                            public void onSuccess() {
                            }
                        });
                    }
                } else {
                    dismissSimpleAlertIfVisible();
                    SingleChoiceDialog singleChoiceDialog4 = new SingleChoiceDialog(requireActivity());
                    this.mSimpleAlertDialog = singleChoiceDialog4;
                    singleChoiceDialog4.setTitleString(getString(R.string.common_alert));
                    this.mSimpleAlertDialog.setMessageString(getString(R.string.common_alert_currentlyOffline, this.mDetailedIduModel.name));
                    this.mSimpleAlertDialog.setCancelable(false);
                    this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_alert_backToHome), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda3(this));
                    if (!this.mSimpleAlertDialog.isShowing()) {
                        this.mSimpleAlertDialog.show();
                    }
                }
            }
        }
    }

    /* renamed from: lambda$onIduWholeCommandUpdateFailure$11$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30947x414098b4(Dialog dialog, View view) {
        if (this.mFragmentToActivityCallback.getNavController() == null) {
            return false;
        }
        this.mSimpleAlertDialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return false;
    }

    /* renamed from: lambda$onIduWholeCommandUpdateFailure$12$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30948xe8bc7275(Dialog dialog, View view) {
        if (this.mFragmentToActivityCallback.getNavController() == null) {
            return false;
        }
        this.mSimpleAlertDialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return false;
    }

    /* renamed from: lambda$onIduWholeCommandUpdateFailure$13$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30949x90384c36(Dialog dialog, View view) {
        this.mSimpleAlertDialog.dismiss();
        return false;
    }

    /* renamed from: lambda$onIduWholeCommandUpdateFailure$14$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30950x37b425f7(Dialog dialog, View view) {
        this.mSimpleAlertDialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return false;
    }

    private void showAlert(String str) {
        this.showAlertDialog.setTitleString(getString(R.string.common_alert));
        this.showAlertDialog.setMessageString(str);
        this.showAlertDialog.setCancelable(false);
        this.showAlertDialog.setPositiveButton(getString(R.string.common_btn_ok), IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda7.INSTANCE);
        SingleChoiceDialog singleChoiceDialog = this.showAlertDialog;
        if (singleChoiceDialog != null && !singleChoiceDialog.isShowing()) {
            this.showAlertDialog.show();
        }
    }

    public void commandExecutionSuccess() {
        super.commandExecutionSuccess();
        dipDisplay();
        stopSwipeRefresh();
        this.layoutTopViewHolder.stopRefreshButtonRotation();
        this.selectModesViewHolder.modeChangeUnderProgress = false;
    }

    public void commandDidNotExecute(CommandStatus.CommandStatusEnum commandStatusEnum) {
        super.commandDidNotExecute(commandStatusEnum);
        this.selectModesViewHolder.modeChangeUnderProgress = false;
        this.mCommandDoneStatusRunnable.decrementCommandQueueCount();
        dipDisplay();
        stopSwipeRefresh();
        this.layoutTopViewHolder.stopRefreshButtonRotation();
        if (commandStatusEnum.isIncomplete()) {
            stopCommandStatusDoneHandler();
            this.mCommandDoneStatusRunnable.reset();
            dismissSimpleAlertIfVisible();
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
            this.mSimpleAlertDialog = singleChoiceDialog;
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            this.mSimpleAlertDialog.setMessageString(getString(R.string.idu_alert_cmndNotReceivedByRac, this.mDetailedIduModel.name));
            this.mSimpleAlertDialog.setCancelable(false);
            this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_btn_ok), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda18(this));
            this.mSimpleAlertDialog.show();
        } else if (commandStatusEnum.isSending()) {
            stopCommandStatusDoneHandler();
            this.mCommandDoneStatusRunnable.reset();
            dismissSimpleAlertIfVisible();
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(requireActivity());
            this.mSimpleAlertDialog = singleChoiceDialog2;
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            this.mSimpleAlertDialog.setMessageString(getString(R.string.common_alert_somethingWentWrong));
            this.mSimpleAlertDialog.setCancelable(false);
            this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_btn_ok), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda19(this));
            this.mSimpleAlertDialog.show();
        }
    }

    /* renamed from: lambda$commandDidNotExecute$16$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30940x49b13975(Dialog dialog, View view) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            updateUIMain(this.mDetailedIduModelPrevState, true);
        }
        return true;
    }

    /* renamed from: lambda$commandDidNotExecute$17$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30941xf12d1336(Dialog dialog, View view) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            updateUIMain(this.mDetailedIduModelPrevState, true);
        }
        return true;
    }

    class TemperatureLayoutViewHolder implements Unbinder {
        public boolean humiditySelected = false;
        public boolean isTemperatureChangeInProcess = false;
        /* access modifiers changed from: private */
        public RepeatListener mDecreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
            public void onClick(View view) {
                if (IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.isExpanded() || IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.isExpanded()) {
                    IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.collapseFan();
                    IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.collapseSwing();
                }
                TemperatureLayoutViewHolder.this.isTemperatureChangeInProcess = true;
                if (IndividualIDUControlFragmentModelWise.this.ifHolidayModeNotEnabled()) {
                    return;
                }
                if (TemperatureLayoutViewHolder.this.humiditySelected) {
                    TemperatureLayoutViewHolder.this.decreaseHumidity();
                } else {
                    TemperatureLayoutViewHolder.this.decreaseTemperature();
                }
            }
        });
        @BindView(2131362802)
        ImageButton mDecreaseTemprature;
        @BindView(2131364270)
        TextView mHumidityTitle;
        @BindView(2131364267)
        TextView mHumidityValueTextView;
        /* access modifiers changed from: private */
        public RepeatListener mIncreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
            public void onClick(View view) {
                if (IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.isExpanded() || IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.isExpanded()) {
                    IndividualIDUControlFragmentModelWise.this.fanSpeedOptionsViewHolder.collapseFan();
                    IndividualIDUControlFragmentModelWise.this.swingOptionsViewHolder.collapseSwing();
                }
                TemperatureLayoutViewHolder.this.isTemperatureChangeInProcess = true;
                if (IndividualIDUControlFragmentModelWise.this.ifHolidayModeNotEnabled()) {
                    return;
                }
                if (TemperatureLayoutViewHolder.this.humiditySelected) {
                    TemperatureLayoutViewHolder.this.increaseHumidity();
                } else {
                    TemperatureLayoutViewHolder.this.increaseTemperature();
                }
            }
        });
        @BindView(2131362811)
        ImageButton mIncreaseTemprature;
        @BindView(2131363274)
        ConstraintLayout mLayoutTemprature;
        @BindView(2131364413)
        TextView mPercentageSymbol;
        @BindView(2131364533)
        TextView mSetTemperatureTitle;
        @BindView(2131364630)
        TextView mTemperature;
        @BindView(2131364640)
        TextView mTempratureUnit;
        public float previousTemp;
        Unbinder unbinder;

        TemperatureLayoutViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            this.mTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
            this.mIncreaseTemprature.setOnTouchListener(this.mIncreaseTemperatureRepeatListener);
            this.mDecreaseTemprature.setOnTouchListener(this.mDecreaseTemperatureRepeatListener);
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131364533})
        public void OnClickModeSelectTemp(TextView textView) {
            setTemperatureUI();
            updatePermission();
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131364270})
        public void OnClickModeSelectHumidity(TextView textView) {
            setHumidityUI();
            updatePermission();
        }

        public void updateTemperatureUI(DetailedIduModel detailedIduModel) {
            String str;
            if (!(this.mIncreaseTemprature == null || this.mDecreaseTemprature == null)) {
                if (!IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.TEMPRATURE_SMALL)) {
                    this.mIncreaseTemprature.setAlpha(0.3f);
                    this.mDecreaseTemprature.setAlpha(0.3f);
                } else {
                    this.mIncreaseTemprature.setAlpha(1.0f);
                    this.mDecreaseTemprature.setAlpha(1.0f);
                }
            }
            detailedIduModel.getOperationModeEnum();
            if (!IndividualIDUControlFragmentModelWise.this.isRacConfigAvailable()) {
                IndividualIDUControlFragmentModelWise.this.showRacConfigNotAvailableAlert();
                return;
            }
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getOperationModeEnum());
            if (racModeDetailBasedOnMode != null) {
                RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
                if (temperatureSettingType != null && temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    float referenceTemperature = detailedIduModel.relativeTemperature + racModeDetailBasedOnMode.getReferenceTemperature();
                    int i = (int) referenceTemperature;
                    if (i != Integer.MAX_VALUE) {
                        if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                            str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                        } else {
                            float f = IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature;
                            String convertedTemperatureAuto = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                            i = (int) detailedIduModel.relativeTemperature;
                            str = convertedTemperatureAuto;
                        }
                        if (i != Integer.MAX_VALUE) {
                            this.mTemperature.setText(str);
                        } else {
                            this.mTemperature.setText("--");
                        }
                    } else {
                        this.mTemperature.setText("--");
                    }
                } else if (((int) detailedIduModel.iduTemperature) != Integer.MAX_VALUE) {
                    this.mTemperature.setText(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(detailedIduModel.iduTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel));
                    this.mTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
                    this.previousTemp = detailedIduModel.iduTemperature;
                } else {
                    this.mTemperature.setText("--");
                }
            }
        }

        public void updateHumidityUI(DetailedIduModel detailedIduModel) {
            this.mHumidityValueTextView.setText(detailedIduModel.humidity);
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            updateTemperatureUI(detailedIduModel);
            updateHumidityUI(detailedIduModel);
            setTemperatureVisibility(detailedIduModel);
            OperationMode operationModeEnum = detailedIduModel.getOperationModeEnum();
            if (operationModeEnum.equals(OperationMode.FAN)) {
                setFanModeUI();
                removeHumidityLabel();
            } else if (!IndividualIDUControlFragmentModelWise.this.isRacConfigAvailable()) {
                IndividualIDUControlFragmentModelWise.this.showRacConfigNotAvailableAlert();
                return;
            } else {
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationModeEnum);
                RacModelWiseData.BleSettings bleSettings = null;
                if (racModeDetailBasedOnMode != null) {
                    bleSettings = racModeDetailBasedOnMode.getEnableSettings();
                }
                if (bleSettings == null || !bleSettings.getHumidity()) {
                    removeHumidityLabel();
                } else {
                    makeHumidityLabelVisible();
                }
                if (this.humiditySelected) {
                    setHumidityUI();
                } else {
                    setTemperatureUI();
                }
            }
            updatePermission();
        }

        private void setTemperatureVisibility(DetailedIduModel detailedIduModel) {
            OperationMode operationModeEnum = detailedIduModel.getOperationModeEnum();
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationModeEnum);
            if (detailedIduModel.getPowerEnum().equals(Power.OFF)) {
                this.mLayoutTemprature.setVisibility(8);
            } else if (operationModeEnum.equals(OperationMode.FAN)) {
                this.mLayoutTemprature.setVisibility(4);
            } else if (operationModeEnum.equals(OperationMode.AUTO)) {
                if (racModeDetailBasedOnMode != null && !racModeDetailBasedOnMode.getVisibleSettings().getTemperature()) {
                    this.mLayoutTemprature.setVisibility(4);
                }
            } else if (detailedIduModel.isInSpecialMode()) {
                this.mLayoutTemprature.setVisibility(0);
                setTemperatureLayoutDisabled();
            } else {
                this.mLayoutTemprature.setVisibility(0);
                setTemperatureLayoutEnabled();
            }
        }

        private void updatePermission() {
            boolean z = true;
            if (this.humiditySelected) {
                this.mIncreaseTemprature.setEnabled(IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.HUMIDITY_SMALL) && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInSpecialMode() && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState());
                ImageButton imageButton = this.mDecreaseTemprature;
                if (!IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.HUMIDITY_SMALL) || IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInSpecialMode() || IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState()) {
                    z = false;
                }
                imageButton.setEnabled(z);
                return;
            }
            this.mIncreaseTemprature.setEnabled(IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.TEMPRATURE_SMALL) && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInSpecialMode() && !IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState());
            ImageButton imageButton2 = this.mDecreaseTemprature;
            if (!IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.TEMPRATURE_SMALL) || IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInSpecialMode() || IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState()) {
                z = false;
            }
            imageButton2.setEnabled(z);
        }

        public void setTemperatureLayoutEnabled() {
            this.mIncreaseTemprature.setEnabled(!IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState());
            this.mDecreaseTemprature.setEnabled(!IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState());
            this.mSetTemperatureTitle.setEnabled(!IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState());
            this.mHumidityTitle.setEnabled(!IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInErrorState());
            this.mLayoutTemprature.setAlpha(1.0f);
        }

        public void setTemperatureLayoutDisabled() {
            this.mIncreaseTemprature.setEnabled(false);
            this.mDecreaseTemprature.setEnabled(false);
            this.mSetTemperatureTitle.setEnabled(false);
            this.mHumidityTitle.setEnabled(false);
            this.mLayoutTemprature.setAlpha(0.2f);
        }

        public void setHumidityUI() {
            this.humiditySelected = true;
            this.mHumidityValueTextView.setVisibility(0);
            this.mPercentageSymbol.setVisibility(0);
            this.mTemperature.setVisibility(4);
            this.mTempratureUnit.setVisibility(4);
            this.mSetTemperatureTitle.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.textview_color_vd_light));
            this.mHumidityTitle.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.color_text_dark));
            this.mIncreaseTemprature.setVisibility(0);
            this.mDecreaseTemprature.setVisibility(0);
            this.mSetTemperatureTitle.setVisibility(0);
            this.mHumidityValueTextView.setVisibility(0);
            this.mPercentageSymbol.setVisibility(0);
        }

        public void setTemperatureUI() {
            this.humiditySelected = false;
            this.mHumidityValueTextView.setVisibility(4);
            this.mPercentageSymbol.setVisibility(4);
            this.mTemperature.setVisibility(0);
            this.mTempratureUnit.setVisibility(0);
            this.mSetTemperatureTitle.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.color_text_dark));
            this.mHumidityTitle.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.textview_color_vd_light));
            this.mIncreaseTemprature.setVisibility(0);
            this.mDecreaseTemprature.setVisibility(0);
            this.mSetTemperatureTitle.setVisibility(0);
            this.mTemperature.setVisibility(0);
            this.mTempratureUnit.setVisibility(0);
        }

        public void setFanModeUI() {
            this.mIncreaseTemprature.setVisibility(4);
            this.mDecreaseTemprature.setVisibility(4);
            this.mTemperature.setVisibility(4);
            this.mTempratureUnit.setVisibility(4);
            this.mSetTemperatureTitle.setVisibility(4);
            this.mHumidityValueTextView.setVisibility(4);
            this.mPercentageSymbol.setVisibility(4);
        }

        public void makeHumidityLabelVisible() {
            this.mHumidityTitle.setVisibility(0);
        }

        public void removeHumidityLabel() {
            this.mHumidityTitle.setVisibility(4);
        }

        public void onDestroyView() {
            this.mIncreaseTemperatureRepeatListener.cleanUp();
            this.mDecreaseTemperatureRepeatListener.cleanUp();
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }

        /* access modifiers changed from: private */
        public void increaseHumidity() {
            if (IndividualIDUControlFragmentModelWise.this.mRacModelWiseData != null) {
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getOperationModeEnum());
                if (IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity != null) {
                    int parseInt = Integer.parseInt(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity);
                    if (racModeDetailBasedOnMode != null) {
                        long j = (long) parseInt;
                        if (j < racModeDetailBasedOnMode.getMaxHumidity()) {
                            int humiditySettingPitchType = (int) (j + racModeDetailBasedOnMode.getHumiditySettingPitchType());
                            IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity = String.valueOf(humiditySettingPitchType);
                            String str = IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity;
                            this.mHumidityValueTextView.setText(str);
                            IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeHumidity(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), str);
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public void decreaseHumidity() {
            if (IndividualIDUControlFragmentModelWise.this.mRacModelWiseData != null) {
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getOperationModeEnum());
                if (IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity != null) {
                    int parseInt = Integer.parseInt(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity);
                    if (racModeDetailBasedOnMode != null) {
                        long j = (long) parseInt;
                        if (j > racModeDetailBasedOnMode.getMinHumidity()) {
                            int humiditySettingPitchType = (int) (j - racModeDetailBasedOnMode.getHumiditySettingPitchType());
                            IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity = String.valueOf(humiditySettingPitchType);
                            String str = IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.humidity;
                            this.mHumidityValueTextView.setText(str);
                            IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeHumidity(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), str);
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public void increaseTemperature() {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode;
            RacModelWiseData.TemperatureSettingType temperatureSettingType;
            String str;
            if (IndividualIDUControlFragmentModelWise.this.mRacModelWiseData != null && (racModeDetailBasedOnMode = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getOperationModeEnum())) != null && (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) != null) {
                if (C213825.f473xf7673082[temperatureSettingType.ordinal()] != 1) {
                    float f = IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature;
                    if (f != Float.MAX_VALUE && f < ((float) racModeDetailBasedOnMode.getMaxTemperature())) {
                        IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature += racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                        this.mTemperature.setText(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel));
                        IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeTemperature(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature);
                        return;
                    }
                    return;
                }
                float f2 = IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature;
                if (f2 != Float.MAX_VALUE && f2 < ((float) racModeDetailBasedOnMode.getMaxTemperature())) {
                    IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature += racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                    float referenceTemperature = racModeDetailBasedOnMode.getReferenceTemperature() + IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature;
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                    } else {
                        str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                    }
                    this.mTemperature.setText(str);
                    IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeTemperature(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature);
                }
            }
        }

        /* access modifiers changed from: private */
        public void decreaseTemperature() {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode;
            RacModelWiseData.TemperatureSettingType temperatureSettingType;
            String str;
            if (IndividualIDUControlFragmentModelWise.this.mRacModelWiseData != null && (racModeDetailBasedOnMode = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getOperationModeEnum())) != null && (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) != null) {
                if (C213825.f473xf7673082[temperatureSettingType.ordinal()] != 1) {
                    float f = IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature;
                    if (f != Float.MAX_VALUE && f > ((float) racModeDetailBasedOnMode.getMinTemperature())) {
                        IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature -= racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                        this.mTemperature.setText(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel));
                        IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeTemperature(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature);
                        return;
                    }
                    return;
                }
                float f2 = IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature;
                if (f2 != Float.MAX_VALUE && f2 > ((float) racModeDetailBasedOnMode.getMinTemperature())) {
                    IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature -= racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                    float referenceTemperature = racModeDetailBasedOnMode.getReferenceTemperature() + IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature;
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                    } else {
                        str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.relativeTemperature), IndividualIDUControlFragmentModelWise.this.mRacModelWiseData, IndividualIDUControlFragmentModelWise.this.mDetailedIduModel);
                    }
                    this.mTemperature.setText(str);
                    IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeTemperature(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.iduTemperature);
                }
            }
        }
    }

    class FanSpeedOptionsViewHolder implements Unbinder {
        private int currentFanSpeed;
        @BindView(2131362445)
        ExpandableLayout mExpandableLayoutFan;
        @BindArray(2130903045)
        String[] mFanArray;
        @BindView(2131363193)
        ConstraintLayout mFanSpeedAuto;
        @BindView(2131363194)
        ConstraintLayout mFanSpeedFive;
        @BindView(2131363195)
        ConstraintLayout mFanSpeedFour;
        @BindView(2131363196)
        ConstraintLayout mFanSpeedOne;
        private ConstraintLayout[] mFanSpeedSelectionArray = new ConstraintLayout[6];
        @BindView(2131363197)
        ConstraintLayout mFanSpeedThree;
        @BindView(2131363198)
        ConstraintLayout mFanSpeedTwo;
        @BindView(2131363521)
        ConstraintLayout mOuterLayoutFan;
        Unbinder unbinder;

        FanSpeedOptionsViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            ConstraintLayout[] constraintLayoutArr = this.mFanSpeedSelectionArray;
            constraintLayoutArr[0] = this.mFanSpeedOne;
            constraintLayoutArr[1] = this.mFanSpeedTwo;
            constraintLayoutArr[2] = this.mFanSpeedThree;
            constraintLayoutArr[3] = this.mFanSpeedFour;
            constraintLayoutArr[4] = this.mFanSpeedFive;
            constraintLayoutArr[5] = this.mFanSpeedAuto;
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131363193, 2131363196, 2131363198, 2131363197, 2131363195, 2131363194})
        public void onClickFanSpeed(ConstraintLayout constraintLayout) {
            switch (constraintLayout.getId()) {
                case R.id.layout_fan_speed_auto:
                    confirmFanSpeedChange(DetailedIduModel.FanSpeed.AUTO);
                    return;
                case R.id.layout_fan_speed_five:
                    confirmFanSpeedChange(DetailedIduModel.FanSpeed.LV5);
                    return;
                case R.id.layout_fan_speed_four:
                    confirmFanSpeedChange(DetailedIduModel.FanSpeed.LV4);
                    return;
                case R.id.layout_fan_speed_one:
                    confirmFanSpeedChange(DetailedIduModel.FanSpeed.LV1);
                    return;
                case R.id.layout_fan_speed_three:
                    confirmFanSpeedChange(DetailedIduModel.FanSpeed.LV3);
                    return;
                case R.id.layout_fan_speed_two:
                    confirmFanSpeedChange(DetailedIduModel.FanSpeed.LV2);
                    return;
                default:
                    return;
            }
        }

        private void confirmFanSpeedChange(DetailedIduModel.FanSpeed fanSpeed) {
            IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeFanSpeed(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), fanSpeed.name());
        }

        public void updateUI(RacModelWiseData racModelWiseData, DetailedIduModel detailedIduModel) {
            highLightFanSpeed(detailedIduModel);
            updateFanSpeedOptionsBasedOnModel(racModelWiseData, detailedIduModel);
            if (this.mExpandableLayoutFan.isExpanded()) {
                collapseFan();
            }
        }

        private void highLightFanSpeed(DetailedIduModel detailedIduModel) {
            for (int i = 0; i < this.mFanSpeedSelectionArray.length; i++) {
                if (i == DetailedIduModel.FanSpeed.valueOf(detailedIduModel.fanSpeedStr).ordinal()) {
                    this.mFanSpeedSelectionArray[i].setBackgroundColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.dark_grey));
                } else {
                    this.mFanSpeedSelectionArray[i].setBackground((Drawable) null);
                }
            }
        }

        private void setUIAuto() {
            this.mFanSpeedAuto.setEnabled(true);
            this.mFanSpeedThree.setEnabled(false);
            this.mFanSpeedFour.setEnabled(false);
            this.mFanSpeedFive.setEnabled(false);
            this.mFanSpeedThree.setAlpha(IndividualIDUControlFragmentModelWise.this.disabledAlfa);
            this.mFanSpeedFour.setAlpha(IndividualIDUControlFragmentModelWise.this.disabledAlfa);
            this.mFanSpeedFive.setAlpha(IndividualIDUControlFragmentModelWise.this.disabledAlfa);
            this.mFanSpeedAuto.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
        }

        private void setUINormal() {
            this.mFanSpeedAuto.setEnabled(true);
            this.mFanSpeedThree.setEnabled(true);
            this.mFanSpeedFour.setEnabled(true);
            this.mFanSpeedFive.setEnabled(true);
            this.mFanSpeedAuto.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
            this.mFanSpeedThree.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
            this.mFanSpeedFour.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
            this.mFanSpeedFive.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
        }

        private void setUIFan() {
            this.mFanSpeedAuto.setEnabled(false);
            this.mFanSpeedThree.setEnabled(true);
            this.mFanSpeedFour.setEnabled(true);
            this.mFanSpeedFive.setEnabled(true);
            this.mFanSpeedAuto.setAlpha(IndividualIDUControlFragmentModelWise.this.disabledAlfa);
            this.mFanSpeedThree.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
            this.mFanSpeedFour.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
            this.mFanSpeedFive.setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }

        public void disableFanSpeeds(int i, int i2) {
            while (i <= i2) {
                this.mFanSpeedSelectionArray[i].setAlpha(IndividualIDUControlFragmentModelWise.this.disabledAlfa);
                this.mFanSpeedSelectionArray[i].setEnabled(false);
                i++;
            }
        }

        public void enableFanSpeeds(int i, int i2) {
            while (i <= i2) {
                this.mFanSpeedSelectionArray[i].setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
                this.mFanSpeedSelectionArray[i].setEnabled(true);
                i++;
            }
        }

        public void expandFan() {
            this.mExpandableLayoutFan.expand();
            this.mOuterLayoutFan.setVisibility(0);
            this.mOuterLayoutFan.setBackground(IndividualIDUControlFragmentModelWise.this.getResources().getDrawable(R.drawable.oval_shape_layout));
        }

        public void collapseFan() {
            if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                this.mExpandableLayoutFan.collapse();
                this.mOuterLayoutFan.setVisibility(8);
                this.mOuterLayoutFan.setBackground((Drawable) null);
            }
        }

        public boolean isExpanded() {
            return this.mExpandableLayoutFan.isExpanded();
        }

        public void updateFanSpeedOptionsBasedOnModel(RacModelWiseData racModelWiseData, DetailedIduModel detailedIduModel) {
            boolean[] booleanArray = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(detailedIduModel.getOperationModeEnum()).getEnableFanSpeedDefaults().getBooleanArray();
            for (int i = 0; i < booleanArray.length; i++) {
                if (booleanArray[i]) {
                    this.mFanSpeedSelectionArray[i].setVisibility(0);
                } else {
                    this.mFanSpeedSelectionArray[i].setVisibility(8);
                }
            }
        }

        private int[] computeEnabledIndex(RacModelWiseData.RacModeDetail racModeDetail) {
            int[] iArr = new int[3];
            int i = 0;
            for (boolean z : racModeDetail.getEnableFanSpeedDefaults().getBooleanArray()) {
                if (z) {
                    i++;
                }
            }
            iArr[1] = i;
            return iArr;
        }

        private int[] computeDisabledIndex(RacModelWiseData.RacModeDetail racModeDetail) {
            int[] iArr = new int[3];
            boolean[] booleanArray = racModeDetail.getEnableFanSpeedDefaults().getBooleanArray();
            int i = 0;
            for (boolean z : booleanArray) {
                if (!z) {
                    i++;
                }
            }
            iArr[0] = i;
            iArr[1] = booleanArray.length;
            return iArr;
        }

        public boolean touchedOnFanLayout(MotionEvent motionEvent) {
            return ViewUtils.ViewTouchUtil.touchedOnThisViewArea(this.mExpandableLayoutFan, motionEvent);
        }
    }

    class SwingOptionsViewHolder implements Unbinder {
        private int currentFanSpeed;
        @BindView(2131362446)
        ExpandableLayout mExpandableLayoutSwing;
        @BindView(2131363523)
        ConstraintLayout mOuterLayoutSwing;
        @BindView(2131363284)
        ConstraintLayout mSwingAuto;
        @BindView(2131363286)
        ConstraintLayout mSwingHorizontal;
        @BindView(2131363285)
        ConstraintLayout mSwingHorizontalAndVertical;
        @BindView(2131363287)
        ConstraintLayout mSwingOff;
        private ConstraintLayout[] mSwingSelectionArray = new ConstraintLayout[5];
        @BindView(2131363288)
        ConstraintLayout mSwingVertical;
        Unbinder unbinder;

        SwingOptionsViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            ConstraintLayout[] constraintLayoutArr = this.mSwingSelectionArray;
            int i = 0;
            constraintLayoutArr[0] = this.mSwingOff;
            boolean z = true;
            constraintLayoutArr[1] = this.mSwingVertical;
            constraintLayoutArr[2] = this.mSwingHorizontal;
            constraintLayoutArr[3] = this.mSwingHorizontalAndVertical;
            constraintLayoutArr[4] = this.mSwingAuto;
            RacModelWiseData.Swing swing = IndividualIDUControlFragmentModelWise.this.mRacModelWiseData.getSwing();
            if (!swing.getHorizontal() || !swing.getVertical()) {
                z = false;
            }
            this.mSwingHorizontalAndVertical.setEnabled(z);
            ConstraintLayout constraintLayout = this.mSwingHorizontalAndVertical;
            IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise = IndividualIDUControlFragmentModelWise.this;
            constraintLayout.setAlpha(z ? individualIDUControlFragmentModelWise.enabledAlfa : individualIDUControlFragmentModelWise.disabledAlfa);
            this.mSwingHorizontalAndVertical.setVisibility(z ? 0 : 4);
            this.mSwingHorizontal.setEnabled(swing.getHorizontal());
            this.mSwingHorizontal.setAlpha(swing.getHorizontal() ? IndividualIDUControlFragmentModelWise.this.enabledAlfa : IndividualIDUControlFragmentModelWise.this.disabledAlfa);
            this.mSwingHorizontal.setVisibility(swing.getHorizontal() ? 0 : 4);
            this.mSwingVertical.setEnabled(swing.getVertical());
            this.mSwingVertical.setAlpha(swing.getVertical() ? IndividualIDUControlFragmentModelWise.this.enabledAlfa : IndividualIDUControlFragmentModelWise.this.disabledAlfa);
            ConstraintLayout constraintLayout2 = this.mSwingVertical;
            if (!swing.getVertical()) {
                i = 4;
            }
            constraintLayout2.setVisibility(i);
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131363287, 2131363288, 2131363286, 2131363285})
        public void onClickSwingSpeed(ConstraintLayout constraintLayout) {
            switch (constraintLayout.getId()) {
                case R.id.layout_swing_horizintal_and_vertical:
                    confirmSwingOptionChange(DetailedIduModel.SwingOption.BOTH);
                    return;
                case R.id.layout_swing_horizontal:
                    confirmSwingOptionChange(DetailedIduModel.SwingOption.HORIZONTAL);
                    return;
                case R.id.layout_swing_off:
                    confirmSwingOptionChange(DetailedIduModel.SwingOption.OFF);
                    return;
                case R.id.layout_swing_vertical:
                    confirmSwingOptionChange(DetailedIduModel.SwingOption.VERTICAL);
                    return;
                default:
                    return;
            }
        }

        private void confirmSwingOptionChange(DetailedIduModel.SwingOption swingOption) {
            IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.getSwingOptionEnum();
            IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeSwingOperation(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), swingOption.name());
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            highLightFanSwing(detailedIduModel);
            if (this.mExpandableLayoutSwing.isExpanded()) {
                collapseSwing();
            }
        }

        private void highLightFanSwing(DetailedIduModel detailedIduModel) {
            if (detailedIduModel.getSwingOptionEnum().equals(DetailedIduModel.SwingOption.AUTO)) {
                this.mSwingAuto.setVisibility(0);
            } else {
                this.mSwingAuto.setVisibility(4);
            }
            for (int i = 0; i < this.mSwingSelectionArray.length; i++) {
                if (i == DetailedIduModel.SwingOption.valueOf(detailedIduModel.fanSwingStr).ordinal()) {
                    this.mSwingSelectionArray[i].setBackgroundColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.dark_grey));
                } else {
                    this.mSwingSelectionArray[i].setBackground((Drawable) null);
                }
            }
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }

        public void disableFanSpeeds(int i, int i2) {
            while (i <= i2) {
                this.mSwingSelectionArray[i].setAlpha(IndividualIDUControlFragmentModelWise.this.disabledAlfa);
                this.mSwingSelectionArray[i].setEnabled(false);
                i++;
            }
        }

        public void enableFanSpeeds(int i, int i2) {
            while (i <= i2) {
                this.mSwingSelectionArray[i].setAlpha(IndividualIDUControlFragmentModelWise.this.enabledAlfa);
                this.mSwingSelectionArray[i].setEnabled(true);
                i++;
            }
        }

        public void expandSwing() {
            this.mExpandableLayoutSwing.expand();
            this.mOuterLayoutSwing.setVisibility(0);
            this.mOuterLayoutSwing.setBackground(IndividualIDUControlFragmentModelWise.this.getResources().getDrawable(R.drawable.oval_shape_layout));
        }

        public void collapseSwing() {
            if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                this.mExpandableLayoutSwing.collapse();
                this.mOuterLayoutSwing.setVisibility(8);
                this.mOuterLayoutSwing.setBackground((Drawable) null);
            }
        }

        public boolean isExpanded() {
            return this.mExpandableLayoutSwing.isExpanded();
        }

        public boolean touchedOnSwingLayout(MotionEvent motionEvent) {
            return ViewUtils.ViewTouchUtil.touchedOnThisViewArea(this.mExpandableLayoutSwing, motionEvent);
        }
    }

    class MainSwitchViewHolder implements Unbinder {
        @BindView(2131364390)
        TextView mOff;
        @BindView(2131364392)
        TextView mOn;
        @BindView(2131363791)
        CustomSwitchButton mSwitchMain;
        Unbinder unbinder;
        ViewOutlineProvider viewOutlineProviderCircle;

        MainSwitchViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            this.mSwitchMain.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                public void onCheckedChanged(final SwitchButton switchButton, final boolean z) {
                    switchButton.setEnabled(false);
                    IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.setTitleString(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_alert_saveChanges));
                    IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.setMessageString(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_alert_saveChangesDesc));
                    IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.setOperationTitleString(IndividualIDUControlFragmentModelWise.this.getString(R.string.idu_alert_powerDispalyInfo));
                    IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.setOperationString(IndividualIDUControlFragmentModelWise.this.getString(z ? R.string.common_lbl_on : R.string.common_lbl_off));
                    IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.setPositiveButton(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_btn_yes), new SavingBehaviourDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.changeOnOffRac(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue(), (z ? Power.ON : Power.OFF).name());
                            IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.dismiss();
                            switchButton.setEnabled(IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.ON_OFF));
                            return false;
                        }
                    });
                    IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.setNegativeButton(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_btn_no), new SavingBehaviourDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.dismiss();
                            switchButton.setEnabled(IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.ON_OFF));
                            MainSwitchViewHolder.this.mSwitchMain.setCheckedSilent(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isTurnedOn());
                            return false;
                        }
                    });
                    IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.setParentView(IndividualIDUControlFragmentModelWise.this.mParent);
                    if (!IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.isShowing()) {
                        IndividualIDUControlFragmentModelWise.this.savingBehaviourDialog.show();
                    }
                }
            });
            this.viewOutlineProviderCircle = new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    outline.setOval(0, 0, view.getWidth(), view.getHeight());
                }
            };
            ViewUtils.setOutlineProviderSwitch(this.mSwitchMain);
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            this.mSwitchMain.setCheckedSilent(detailedIduModel.getPowerEnum().equals(Power.ON));
            if (detailedIduModel.getPowerEnum().equals(Power.OFF)) {
                this.mOff.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.textview_color_vd_light));
                this.mOn.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.color_disabled_views));
            } else {
                this.mOff.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.color_disabled_views));
                this.mOn.setTextColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.textview_color_vd_light));
            }
            this.mSwitchMain.setEnabled(IndividualIDUControlFragmentModelWise.this.isPermissionAvailable(UserPermissions.IduFeatures.ON_OFF) && !detailedIduModel.isInErrorState());
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }
    }

    class OffStateViewHolder implements Unbinder {
        private ConstraintSet constraintSetOriginal;
        @BindView(2131364277)
        TextView mTextViewIduIsOff;
        Unbinder unbinder;

        OffStateViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            if (detailedIduModel.getPowerEnum().equals(Power.OFF)) {
                offTextVisible();
            } else {
                offTextInvisible();
            }
        }

        public void offTextVisible() {
            this.mTextViewIduIsOff.setVisibility(0);
            setOffConstraints();
        }

        public void offTextInvisible() {
            this.mTextViewIduIsOff.setVisibility(8);
            removeOffConstraints();
        }

        private void setOffConstraints() {
            ConstraintSet constraintSet = new ConstraintSet();
            this.constraintSetOriginal = new ConstraintSet();
            constraintSet.clone(IndividualIDUControlFragmentModelWise.this.mParentTop);
            this.constraintSetOriginal.clone(IndividualIDUControlFragmentModelWise.this.mParentTop);
            constraintSet.clear(R.id.layout_switch_room_device_control, 3);
            constraintSet.connect(R.id.layout_switch_room_device_control, 3, R.id.text_view_idu_is_off, 4);
            constraintSet.connect(R.id.layout_switch_room_device_control, 4, R.id.layout_mode_room_device_control, 3);
            constraintSet.applyTo(IndividualIDUControlFragmentModelWise.this.mParentTop);
        }

        private void removeOffConstraints() {
            ConstraintSet constraintSet = new ConstraintSet();
            this.constraintSetOriginal = new ConstraintSet();
            constraintSet.clone(IndividualIDUControlFragmentModelWise.this.mParentTop);
            this.constraintSetOriginal.clone(IndividualIDUControlFragmentModelWise.this.mParentTop);
            constraintSet.clear(R.id.layout_switch_room_device_control, 3);
            constraintSet.clear(R.id.layout_switch_room_device_control, 4);
            constraintSet.connect(R.id.layout_switch_room_device_control, 3, R.id.text_view_in_holiday_mode, 4);
            constraintSet.applyTo(IndividualIDUControlFragmentModelWise.this.mParentTop);
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }
    }

    class HolidayModeViewHolder implements Unbinder {
        @BindView(2131364281)
        TextView mTextViewHolidayMode;
        Unbinder unbinder;

        HolidayModeViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            if (detailedIduModel.scheduletype.equalsIgnoreCase(WeeklyTimerMode.HOLIDAY_MODE_ENABLED.name())) {
                offTextVisible();
            } else {
                offTextInvisible();
            }
        }

        public void offTextVisible() {
            this.mTextViewHolidayMode.setVisibility(0);
        }

        public void offTextInvisible() {
            this.mTextViewHolidayMode.setVisibility(8);
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }
    }

    class LayoutTopViewHolder implements Unbinder {
        int disabledColor;
        @BindView(2131363962)
        TextView mAreaName;
        @BindView(2131362078)
        ImageButton mBack;
        @BindView(2131363013)
        ImageView mImageViewSun;
        @BindView(2131363022)
        ImageView mImageViewTemperature;
        @BindView(2131364309)
        TextView mLastUpdatedOnTitle;
        @BindView(2131363221)
        ConstraintLayout mLayoutMain;
        @BindView(2131363296)
        ConstraintLayout mLayoutTemprature;
        @BindView(2131363305)
        ConstraintLayout mLayoutTop;
        @BindView(2131362824)
        ImageButton mRefreshIduState;
        @BindView(2131364459)
        TextView mRoomName;
        @BindView(2131364453)
        TextView mRoomTemperature;
        @BindView(2131364456)
        TextView mRoomTemperatureTitle;
        @BindView(2131364457)
        TextView mRoomTemperatureUnit;
        @BindView(2131364732)
        TextView mWeatherType;
        /* access modifiers changed from: private */
        public boolean refreshing;
        private RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        Unbinder unbinder;

        LayoutTopViewHolder() {
            this.disabledColor = IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.color_disabled_views);
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            this.rotateAnimation.setDuration(2000);
            this.rotateAnimation.setRepeatCount(15);
            this.rotateAnimation.setInterpolator(new LinearInterpolator());
            this.rotateAnimation.setRepeatMode(1);
            this.rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    LayoutTopViewHolder.this.refreshing = true;
                }

                public void onAnimationEnd(Animation animation) {
                    LayoutTopViewHolder.this.refreshing = false;
                }
            });
            this.mRefreshIduState.setImageDrawable(R.drawable.ic_update_icon_new);
            if (IndividualIDUControlFragmentModelWise.this.mCurrentWeatherInfo.weatherResult != null) {
                IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.setWeatherData(IndividualIDUControlFragmentModelWise.this.mCurrentWeatherInfo);
            }
        }

        public void updateLastUpdatedTime(DetailedIduModel detailedIduModel) {
            long j = detailedIduModel.updatedAt;
            if (j != Long.MAX_VALUE) {
                String timeFromMilliSec = DateAndTimeUtils.getTimeFromMilliSec(j, "HH:mm");
                TextView textView = this.mLastUpdatedOnTitle;
                textView.setText(IndividualIDUControlFragmentModelWise.this.getString(R.string.common_lbl_lastUpdatedAt) + " ");
                this.mLastUpdatedOnTitle.append(timeFromMilliSec);
            }
        }

        private void updateStatusBarColorOnOff(DetailedIduModel detailedIduModel) {
            OperationModeUIConfigration operationModeUIConfigrationBasedOnMode;
            if (detailedIduModel.isTurnedOn()) {
                OperationMode operationModeEnum = detailedIduModel.getOperationModeEnum();
                if (detailedIduModel.isInSpecialMode()) {
                    IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.changeStatusBarColor(R.color.white);
                } else if (operationModeEnum != null && (operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(operationModeEnum)) != null) {
                    IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.changeStatusBarColor(operationModeUIConfigrationBasedOnMode.getStatusBarColor());
                }
            } else {
                IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.changeStatusBarColor(R.color.white);
            }
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            detailedIduModel.getOperationModeEnum();
            if (detailedIduModel.roomTemperature == null) {
                makeRoomTempViewsHidden();
            } else if (detailedIduModel.roomTemperature.floatValue() == Float.MAX_VALUE) {
                makeRoomTempViewsHidden();
            } else {
                makeRoomTempViewsVisible();
                try {
                    this.mRoomTemperature.setText(TemperatureValueFormatter.getConvertedValue((double) detailedIduModel.roomTemperature.floatValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mRoomTemperatureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
            this.mRoomName.setText(detailedIduModel.name);
            if (detailedIduModel.getPowerEnum().equals(Power.OFF)) {
                greyoutTopLayout();
            } else {
                setNormalTopLayout(detailedIduModel);
            }
            updateStatusBarColorOnOff(detailedIduModel);
            updateLastUpdatedTime(detailedIduModel);
            this.mRefreshIduState.setEnabled(!detailedIduModel.isInErrorState());
        }

        private void makeRoomTempViewsHidden() {
            this.mRoomTemperature.setVisibility(4);
            this.mRoomTemperatureUnit.setVisibility(4);
            this.mRoomTemperatureTitle.setVisibility(4);
            this.mImageViewTemperature.setVisibility(4);
        }

        private void makeRoomTempViewsVisible() {
            this.mRoomTemperature.setVisibility(0);
            this.mRoomTemperatureUnit.setVisibility(0);
            this.mRoomTemperatureTitle.setVisibility(0);
            this.mImageViewTemperature.setVisibility(0);
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131362078})
        public void OnClickBack(ImageButton imageButton) {
            IndividualIDUControlFragmentModelWise.this.mFragmentToActivityCallback.getNavController().navigateUp();
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131362824})
        public void onClickRefresh() {
            if (!this.refreshing) {
                IndividualIDUControlFragmentModelWise.this.dimDisplay();
                startRefreshButtonRotation();
                IndividualIDUControlFragmentModelWise.this.mGetFamilyGroupPresenter.getFamilyGroup();
                IndividualIDUControlFragmentModelWise.this.mIndividualIDUControlPresenter.refreshRacState(IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.f454id.intValue());
            }
        }

        /* access modifiers changed from: private */
        public void startRefreshButtonRotation() {
            if (!IndividualIDUControlFragmentModelWise.this.mDetailedIduModel.isInSpecialMode()) {
                this.mRefreshIduState.startAnimation(this.rotateAnimation);
            }
        }

        /* access modifiers changed from: private */
        public void stopRefreshButtonRotation() {
            this.rotateAnimation.cancel();
            this.rotateAnimation.reset();
        }

        public void racStateUpdateAvailable() {
            this.refreshing = false;
            stopRefreshButtonRotation();
        }

        public void greyoutTopLayout() {
            if (!this.mRefreshIduState.isDrawableAlreadySet(R.drawable.ic_refresh_disabled_svg)) {
                this.mRefreshIduState.setImageDrawable(R.drawable.ic_refresh_disabled_svg);
            }
            this.mLayoutMain.setBackground((Drawable) null);
            this.mRoomName.setTextColor(this.disabledColor);
            this.mLastUpdatedOnTitle.setTextColor(this.disabledColor);
            this.mRoomTemperature.setTextColor(this.disabledColor);
            this.mRoomTemperatureUnit.setTextColor(this.disabledColor);
            this.mRoomTemperatureTitle.setTextColor(this.disabledColor);
            this.mWeatherType.setTextColor(this.disabledColor);
            this.mAreaName.setTextColor(this.disabledColor);
            this.mImageViewTemperature.setImageDrawable(IndividualIDUControlFragmentModelWise.this.getResources().getDrawable(R.drawable.ic_temperature_disbaled_svg));
            this.mImageViewTemperature.setColorFilter(this.disabledColor);
            this.mBack.setColorFilter(-3355444);
            if (IndividualIDUControlFragmentModelWise.this.weather != null) {
                this.mImageViewSun.setColorFilter(this.disabledColor);
            }
        }

        public void setNormalTopLayout(DetailedIduModel detailedIduModel) {
            int i;
            if (!this.mRefreshIduState.isDrawableAlreadySet(R.drawable.ic_update_icon_new)) {
                this.mRefreshIduState.setImageDrawable(R.drawable.ic_update_icon_new);
            }
            OperationMode operationModeEnum = detailedIduModel.getOperationModeEnum();
            if (operationModeEnum != null) {
                OperationModeUIConfigration operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(operationModeEnum);
                if (operationModeUIConfigrationBasedOnMode != null) {
                    if (detailedIduModel.isInSpecialMode()) {
                        this.mLayoutMain.setBackgroundColor(IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.white));
                        setSpecialModeUI(detailedIduModel);
                    } else {
                        this.mLayoutMain.setBackground(IndividualIDUControlFragmentModelWise.this.getResources().getDrawable(operationModeUIConfigrationBasedOnMode.getMainBackgroundDrawableResource()));
                        setNormalUI(detailedIduModel);
                    }
                }
                if (operationModeEnum.equals(OperationMode.AUTO) || detailedIduModel.isInSpecialMode()) {
                    i = IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.textview_color_vd_light);
                } else {
                    i = IndividualIDUControlFragmentModelWise.this.getResources().getColor(R.color.white);
                }
                this.mRoomName.setTextColor(i);
                this.mLastUpdatedOnTitle.setTextColor(i);
                this.mRoomTemperature.setTextColor(i);
                this.mRoomTemperatureUnit.setTextColor(i);
                this.mRoomTemperatureTitle.setTextColor(i);
                this.mWeatherType.setTextColor(i);
                this.mAreaName.setTextColor(i);
                this.mImageViewTemperature.setColorFilter(i);
                this.mImageViewSun.setColorFilter(i);
                this.mBack.setColorFilter(i);
                PorterDuffColorFilter porterDuffColorFilter = (PorterDuffColorFilter) this.mRefreshIduState.getColorFilter();
                PorterDuffColorFilter porterDuffColorFilter2 = new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_ATOP);
                if (porterDuffColorFilter == null) {
                    this.mRefreshIduState.setColorFilter(i);
                }
                if (porterDuffColorFilter != null && !porterDuffColorFilter.equals(porterDuffColorFilter2)) {
                    this.mRefreshIduState.setColorFilter(i);
                }
            }
        }

        public void setSpecialModeUI(DetailedIduModel detailedIduModel) {
            if (detailedIduModel.roomTemperature == null || detailedIduModel.roomTemperature.floatValue() == Float.MAX_VALUE) {
                this.mRoomTemperature.setVisibility(4);
                this.mRoomTemperatureUnit.setVisibility(4);
                this.mRoomTemperatureTitle.setVisibility(4);
                this.mImageViewTemperature.setVisibility(4);
            }
            this.mWeatherType.setVisibility(4);
            this.mAreaName.setVisibility(4);
            this.mImageViewSun.setVisibility(4);
            if (this.mRefreshIduState.getVisibility() == 0) {
                this.mRefreshIduState.setVisibility(4);
            }
        }

        public void setNormalUI(DetailedIduModel detailedIduModel) {
            if (!(detailedIduModel.roomTemperature == null || detailedIduModel.roomTemperature.floatValue() == Float.MAX_VALUE)) {
                this.mRoomTemperature.setVisibility(0);
                this.mRoomTemperatureUnit.setVisibility(0);
                this.mRoomTemperatureTitle.setVisibility(0);
                this.mImageViewTemperature.setVisibility(0);
            }
            this.mWeatherType.setVisibility(0);
            this.mAreaName.setVisibility(0);
            this.mImageViewSun.setVisibility(0);
            if (this.mRefreshIduState.getVisibility() == 4) {
                this.mRefreshIduState.setVisibility(0);
            }
        }

        /* access modifiers changed from: private */
        public void setWeatherData(WeatherDataModel.WeatherDataModelResponseSuccess weatherDataModelResponseSuccess) {
            if (weatherDataModelResponseSuccess == null) {
                this.mImageViewSun.setImageResource(R.drawable.ic_error_outline_white_24dp);
                return;
            }
            this.mAreaName.setText(weatherDataModelResponseSuccess.weatherResult.cityName);
            String str = weatherDataModelResponseSuccess.weatherResult.weathers[0].description;
            LocaleConfiguration.getCurrentAppLocale().getLanguage().toLowerCase();
            String convertedValue = TemperatureValueFormatter.getConvertedValue(weatherDataModelResponseSuccess.weatherResult.mainParameters.temperature);
            TextView textView = this.mWeatherType;
            textView.setText(convertedValue + IndividualIDUControlFragmentModelWise.this.getString(TemperatureUnit.getTemperatureUnitFromSettings()) + " " + str + ",");
            this.mImageViewSun.setImageResource(WeatherIconsFactory.getWeatherIcon(Integer.parseInt(weatherDataModelResponseSuccess.weatherResult.weathers[0].f489id)));
        }

        public void onDestroyView() {
            unbind();
        }

        public void unbind() {
            Unbinder unbinder2 = this.unbinder;
            if (unbinder2 != null) {
                unbinder2.unbind();
            }
        }
    }

    private static class OptionsModel {
        /* access modifiers changed from: private */
        public boolean isEnabled;
        /* access modifiers changed from: private */
        public boolean isOnIndicator;
        /* access modifiers changed from: private */
        public boolean isPermissionAvailable;
        /* access modifiers changed from: private */
        public boolean isVisible;
        /* access modifiers changed from: private */
        public View.OnClickListener onClickListener;
        private int optionIcon;
        private int optionLeftImage;
        private int optionName;
        private int optionRightImage;
        private Options options;

        private OptionsModel() {
            this.isVisible = true;
        }

        public enum Options {
            FAN(R.string.common_lbl_fan),
            SWING(R.string.idu_lbl_swing);
            
            boolean enabledInOff;
            int nameResId;

            private Options(int i) {
                this.nameResId = i;
            }

            public Options getEnumFromOptionNameResId(int i) {
                if (i == R.string.common_lbl_fan) {
                    return FAN;
                }
                if (i != R.string.idu_lbl_swing) {
                    return null;
                }
                return SWING;
            }
        }

        public Options getOptions() {
            return this.options;
        }

        public void setOptions(Options options2) {
            this.options = options2;
        }

        public int getOptionName() {
            return this.optionName;
        }

        public void setOptionName(int i) {
            this.optionName = i;
        }

        public int getOptionIcon() {
            return this.optionIcon;
        }

        public void setOptionIcon(int i) {
            this.optionIcon = i;
        }

        public int getOptionRightImage() {
            return this.optionRightImage;
        }

        public void setOptionRightImage(int i) {
            this.optionRightImage = i;
        }

        public int getOptionLeftImage() {
            return this.optionLeftImage;
        }

        public void setOptionLeftImage(int i) {
            this.optionLeftImage = i;
        }

        public boolean isVisible() {
            return this.isVisible;
        }

        public void setVisible(boolean z) {
            this.isVisible = z;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public void setEnabled(boolean z) {
            this.isEnabled = z;
        }

        public boolean isPermissionAvailable() {
            return this.isPermissionAvailable;
        }

        public void setPermissionAvailable(boolean z) {
            this.isPermissionAvailable = z;
        }

        public View.OnClickListener getOnClickListener() {
            return this.onClickListener;
        }

        public void setOnClickListener(View.OnClickListener onClickListener2) {
            this.onClickListener = onClickListener2;
        }

        public boolean isOnIndicator() {
            return this.isOnIndicator;
        }

        public void setOnIndicator(boolean z) {
            this.isOnIndicator = z;
        }
    }

    public class OperationModeItem implements Comparable<OperationModeItem> {
        private int displayOrder;
        /* access modifiers changed from: private */
        public boolean enabled;
        private boolean expanded;
        private int mImageMode;
        private int mImageSwipe;
        private String mTextMode;
        /* access modifiers changed from: private */
        public View.OnClickListener onClickListener;
        private boolean toExpand;

        public OperationModeItem() {
        }

        public int getDisplayOrder() {
            return this.displayOrder;
        }

        public void setDisplayOrder(int i) {
            this.displayOrder = i;
        }

        public boolean tobeExpanded() {
            return this.toExpand;
        }

        public void setOnClickListener(View.OnClickListener onClickListener2) {
            this.onClickListener = onClickListener2;
        }

        public int getImageMode() {
            return this.mImageMode;
        }

        public void setImageMode(int i) {
            this.mImageMode = i;
        }

        public String getTextMode() {
            return this.mTextMode;
        }

        public void setTextMode(String str) {
            this.mTextMode = str;
        }

        public int getImageSwipe() {
            return this.mImageSwipe;
        }

        public void setImageSwipe(int i) {
            this.mImageSwipe = i;
        }

        public boolean isExpanded() {
            return this.expanded;
        }

        public void setExpanded(boolean z) {
            this.expanded = z;
        }

        public void setToExpand(boolean z) {
            this.toExpand = z;
        }

        public int compareTo(OperationModeItem operationModeItem) {
            if (operationModeItem == null) {
                return 0;
            }
            return Integer.compare(this.displayOrder, operationModeItem.displayOrder);
        }
    }

    public void onModelWiseRacInfoAvailable(RacModelWiseData racModelWiseData, DetailedIduModel detailedIduModel) {
        this.selectModesViewHolder.updateModeListBasedOnRacType(racModelWiseData);
        this.gridOptionsViewHolder.updateOptionsListBasedOnRacType(racModelWiseData, detailedIduModel);
        this.fanSpeedOptionsViewHolder.updateFanSpeedOptionsBasedOnModel(racModelWiseData, detailedIduModel);
    }

    public void updateUIMain(DetailedIduModel detailedIduModel) {
        updateUIMain(detailedIduModel, false);
    }

    public void updateUIMain(DetailedIduModel detailedIduModel, boolean z) {
        this.updatingUI = true;
        Logger.m47e("updateUI", String.valueOf(true));
        if (z) {
            this.mDetailedIduModel.copy(this.mDetailedIduModelPrevState);
        }
        if (!detailedIduModel.online) {
            dismissSimpleAlertIfVisible();
            new Handler().postDelayed(new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda10(this, detailedIduModel), 1000);
            return;
        }
        this.temperatureLayoutViewHolder.updateUI(detailedIduModel);
        this.layoutTopViewHolder.updateUI(detailedIduModel);
        this.mainSwitchViewHolder.updateUI(detailedIduModel);
        this.selectModesViewHolder.updateUI(detailedIduModel);
        this.offStateViewHolder.updateUI(detailedIduModel);
        this.fanSpeedOptionsViewHolder.updateUI(this.mRacModelWiseData, detailedIduModel);
        this.swingOptionsViewHolder.updateUI(detailedIduModel);
        this.holidayModeViewHolder.updateUI(detailedIduModel);
        handleError();
        GridOptionsViewHolder gridOptionsViewHolder2 = this.gridOptionsViewHolder;
        if (gridOptionsViewHolder2 != null) {
            gridOptionsViewHolder2.updateOptionsListBasedOnRacType(this.mRacModelWiseData, detailedIduModel);
            this.gridOptionsViewHolder.getOptionsGridLayoutAdapter().setDetailedIduModel(detailedIduModel);
            this.gridOptionsViewHolder.getOptionsGridLayoutAdapter().notifyDataSetChanged();
        }
        this.updatingUI = false;
        Logger.m47e("updateUI", String.valueOf(false));
    }

    /* renamed from: lambda$updateUIMain$18$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ void mo30954x568bf8f7(DetailedIduModel detailedIduModel) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            commonTask(detailedIduModel, (CallBackListener) new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                }
            });
        }
    }

    public void onNetworkCallFailure(Throwable th) {
        super.onNetworkCallFailure(th);
        GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
        dismissPleaseWaitDialog();
        getCoreActivity().dismissPleaseWaitDialog();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    IndividualIDUControlFragmentModelWise.this.dipDisplay();
                    IndividualIDUControlFragmentModelWise.this.stopSwipeRefresh();
                    IndividualIDUControlFragmentModelWise.this.layoutTopViewHolder.stopRefreshButtonRotation();
                }
            }
        }, 200);
    }

    public void onFanSpeedUpdatedSuccess() {
        super.onFanSpeedUpdatedSuccess();
        this.fanSpeedOptionsViewHolder.collapseFan();
    }

    public void onFanSpeedUpdatedFailure() {
        super.onFanSpeedUpdatedFailure();
        this.fanSpeedOptionsViewHolder.collapseFan();
    }

    public void onSwingOperationUpdatedSuccess() {
        super.onSwingOperationUpdatedSuccess();
        this.swingOptionsViewHolder.collapseSwing();
    }

    public void onSwingOperationUpdatedFailure() {
        super.onSwingOperationUpdatedFailure();
        this.swingOptionsViewHolder.collapseSwing();
    }

    private void commonTask(DetailedIduModel detailedIduModel, CallBackListener callBackListener) {
        if (this.offlinePopup) {
            SingleChoiceDialog singleChoiceDialog = this.mSimpleAlertDialog;
            if (singleChoiceDialog != null && singleChoiceDialog.isShowing()) {
                this.mSimpleAlertDialog.dismiss();
            }
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(requireActivity());
            this.mSimpleAlertDialog = singleChoiceDialog2;
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            this.mSimpleAlertDialog.setMessageString(getString(R.string.common_alert_currentlyOffline, this.mDetailedIduModel.name));
            this.mSimpleAlertDialog.setCancelable(false);
            this.mSimpleAlertDialog.setPositiveButton(getString(R.string.common_alert_backToHome), new IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda1(this));
            if (!this.mSimpleAlertDialog.isShowing()) {
                this.mSimpleAlertDialog.show();
                return;
            }
            return;
        }
        Banner banner = this.mBinder.getBanner(detailedIduModel, (BannerListener<DetailedIduModel, BannerRacOfflineBinding>) new BannerListener<DetailedIduModel, BannerRacOfflineBinding>() {
            public void onClick(View view, DetailedIduModel detailedIduModel, BannerRacOfflineBinding bannerRacOfflineBinding) {
                IndividualIDUControlFragmentModelWise.this.mParentOuter.removeAllViews();
                IndividualIDUControlFragmentModelWise.this.mBinder.unBind();
            }

            public void onItemClick(View view, DetailedIduModel detailedIduModel, BannerRacOfflineBinding bannerRacOfflineBinding) {
                if (!IndividualIDUControlFragmentModelWise.this.isVisible) {
                    IndividualIDUControlFragmentModelWise.this.isVisible = true;
                    bannerRacOfflineBinding.layoutDesc.setVisibility(0);
                    bannerRacOfflineBinding.textViewViewHideDetails.setText(IndividualIDUControlFragmentModelWise.this.getResources().getString(R.string.notification_btn_hideDetail));
                    return;
                }
                bannerRacOfflineBinding.layoutDesc.setVisibility(8);
                bannerRacOfflineBinding.textViewViewHideDetails.setText(IndividualIDUControlFragmentModelWise.this.getResources().getString(R.string.notification_btn_viewDetail));
                IndividualIDUControlFragmentModelWise.this.isVisible = false;
            }
        });
        if (this.mDetailedIduModel.isOnline()) {
            this.mParentOuter.removeAllViews();
        }
        View root = banner.getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mParentOuter.addView(root);
    }

    /* renamed from: lambda$commonTask$19$com-jch-racWiFi-iduManagement-view-viewImpl-IndividualIDUControlFragmentModelWise */
    public /* synthetic */ boolean mo30942xdd4c361e(Dialog dialog, View view) {
        if (this.mFragmentToActivityCallback.getNavController() == null) {
            return false;
        }
        this.mSimpleAlertDialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return false;
    }

    public void unAuthorized() {
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                if (IndividualIDUControlFragmentModelWise.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise = IndividualIDUControlFragmentModelWise.this;
                    individualIDUControlFragmentModelWise.updateUIMain(individualIDUControlFragmentModelWise.mDetailedIduModelPrevState, true);
                }
            }
        }, false);
    }
}
