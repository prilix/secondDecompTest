package com.jch.racWiFi.iduManagement.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.RepeatListener;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.SaveChangesListener;
import com.jch.racWiFi.Utils.TemperatureValueFormatter;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.OperationModeUIConfigration;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SetModeAndTemperatureModelWise extends GenericFragment {
    public static final String HUMIDITY = "Humidity";
    private static final String HUMIDTYPERCENT = "HumidityPercent";
    private static final String MODE = "ModeSelected";
    public static final String TEMPERTAURE = "Temperature";
    Bundle bundle;
    private SaveChangesListener changesListener;
    boolean humidityPercentSelected = false;
    public boolean humiditySelected = false;
    private LayoutTopViewHolder layoutTopViewHolder;
    /* access modifiers changed from: private */
    public DetailedIduModel mDetailedIduModel = new DetailedIduModel();
    @BindView(2131363530)
    ConstraintLayout mParent;
    /* access modifiers changed from: private */
    public RacModelWiseData mRacModelWiseData;
    private Unbinder mUnbinder;
    private int navigatingFrom;
    /* access modifiers changed from: private */
    public DetailedIduModel referenceDetailedIduModel;
    private SelectModesViewHolder selectModesViewHolder;
    /* access modifiers changed from: private */
    public OperationMode selectedOperationMode = OperationMode.COOLING;
    private TemperatureLayoutViewHolder temperatureLayoutViewHolder;

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
            this.selectModeRecyclerViewAdapter = new SelectModeRecyclerViewAdapter(SetModeAndTemperatureModelWise.this.getActivity(), this.menuItemsSetMode);
            this.mModeChangeHandler = new Handler();
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
            if (operationModeUIConfigrationBasedOnMode != null) {
                this.mLayoutMode.setBackgroundColor(SetModeAndTemperatureModelWise.this.getResources().getColor(operationModeUIConfigrationBasedOnMode.getModeBackgroundColorResource()));
                this.mSelectedModeText.setText(operationModeUIConfigrationBasedOnMode.getOperationModeStringResource());
                this.mSelectedModeImage.setImageResource(operationModeUIConfigrationBasedOnMode.getSelectedModeDrawableResource());
            }
            if (operationModeEnum.equals(OperationMode.AUTO)) {
                setModeAuto();
            } else {
                setModesNormal();
            }
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131363227})
        public void OnClickMode(ConstraintLayout constraintLayout) {
            selectedMode();
        }

        /* access modifiers changed from: private */
        public void operationModeChangeConfirmation(OperationMode operationMode) {
            SetModeAndTemperatureModelWise.this.selectedOperationMode = operationMode;
            SetModeAndTemperatureModelWise.this.mDetailedIduModel.mode = SetModeAndTemperatureModelWise.this.selectedOperationMode.name();
            SetModeAndTemperatureModelWise.this.mDetailedIduModel.copyDefaults(SetModeAndTemperatureModelWise.this.mRacModelWiseData);
            SetModeAndTemperatureModelWise setModeAndTemperatureModelWise = SetModeAndTemperatureModelWise.this;
            setModeAndTemperatureModelWise.updateUIMain(setModeAndTemperatureModelWise.mDetailedIduModel);
            AlertDialog alertDialog = this.mModeSelectDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        }

        public void setModeAuto() {
            this.mSelectedModeText.setTextColor(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.textview_color_vd_dark));
            this.mDownArrow.setColorFilter(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.textview_color_vd_dark));
        }

        public void setModesNormal() {
            this.mSelectedModeText.setTextColor(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.white));
            this.mDownArrow.setColorFilter(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.white));
        }

        private void selectedMode() {
            AlertDialog.Builder builder = new AlertDialog.Builder(SetModeAndTemperatureModelWise.this.getActivity());
            builder.setCancelable(false).setView(R.layout.select_mode_dialog);
            AlertDialog create = builder.create();
            this.mModeSelectDialog = create;
            create.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    SetModeAndTemperatureModelWise.this.mParent.setBackgroundResource(R.drawable.white_blur);
                }
            });
            this.mModeSelectDialog.show();
            this.mModeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    SetModeAndTemperatureModelWise.this.mParent.setBackgroundResource(R.drawable.transparent);
                }
            });
            WindowManager.LayoutParams attributes = this.mModeSelectDialog.getWindow().getAttributes();
            attributes.dimAmount = 0.2f;
            this.mModeSelectDialog.getWindow().setAttributes(attributes);
            this.mModeSelectDialog.getWindow().addFlags(2);
            View decorView = this.mModeSelectDialog.getWindow().getDecorView();
            ((ImageButton) decorView.findViewById(R.id.image_button_clear)).setOnClickListener(new C1991x25b7073b(this));
            RecyclerView recyclerView = (RecyclerView) decorView.findViewById(R.id.recycler_view_select_mode);
            recyclerView.setLayoutManager(new LinearLayoutManager(SetModeAndTemperatureModelWise.this.getActivity()));
            Math.round(ViewUtils.convertDpToPixel(60.0f, SetModeAndTemperatureModelWise.this.getActivity()));
            recyclerView.getLayoutParams();
            recyclerView.setAdapter(this.selectModeRecyclerViewAdapter);
        }

        /* renamed from: lambda$selectedMode$0$com-jch-racWiFi-iduManagement-view-SetModeAndTemperatureModelWise$SelectModesViewHolder */
        public /* synthetic */ void mo30543xcff6eaf9(View view) {
            this.mModeSelectDialog.dismiss();
        }

        /* access modifiers changed from: private */
        public void startModeChangeHandler(final OperationMode operationMode) {
            this.mModeChangeHandler.removeCallbacksAndMessages((Object) null);
            this.mModeChangeHandler.postDelayed(new Runnable() {
                public void run() {
                    SelectModesViewHolder.this.operationModeChangeConfirmation(operationMode);
                }
            }, 200);
        }

        private void stopModeChangeHandler() {
            this.mModeChangeHandler.removeCallbacksAndMessages((Object) null);
        }

        private List<OperationModeItem> populateMenuItemsSelectMode(RacModelWiseData racModelWiseData) {
            ArrayList arrayList = new ArrayList();
            if (racModelWiseData != null) {
                Iterator it = racModelWiseData.getRacModeDetails().iterator();
                while (it.hasNext()) {
                    final OperationMode mode = ((RacModelWiseData.RacModeDetail) it.next()).getMode();
                    OperationModeUIConfigration operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(mode);
                    if (operationModeUIConfigrationBasedOnMode != null) {
                        OperationModeItem operationModeItem = new OperationModeItem();
                        operationModeItem.setImageMode(operationModeUIConfigrationBasedOnMode.getModeListDrawableResource());
                        operationModeItem.setTextMode(SetModeAndTemperatureModelWise.this.getString(operationModeUIConfigrationBasedOnMode.getOperationModeStringResource()));
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

    public class LayoutTopViewHolder_ViewBinding implements Unbinder {
        private LayoutTopViewHolder target;
        private View view7f0a011e;
        private View view7f0a0a77;

        public LayoutTopViewHolder_ViewBinding(final LayoutTopViewHolder layoutTopViewHolder, View view) {
            this.target = layoutTopViewHolder;
            layoutTopViewHolder.mLayoutTop = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent_top, "field 'mLayoutTop'", ConstraintLayout.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'OnClickBack'");
            layoutTopViewHolder.mBack = (ImageButton) C0840Utils.castView(findRequiredView, R.id.back_button, "field 'mBack'", ImageButton.class);
            this.view7f0a011e = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    layoutTopViewHolder.OnClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickBack", 0, ImageButton.class));
                }
            });
            layoutTopViewHolder.mSetModeAndTemperature = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_set_mode_and_temprature, "field 'mSetModeAndTemperature'", TextView.class);
            View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_save, "field 'mSave' and method 'onClickSaveModeAndTemperature'");
            layoutTopViewHolder.mSave = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.text_view_save, "field 'mSave'", ImageButton.class);
            this.view7f0a0a77 = findRequiredView2;
            findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    layoutTopViewHolder.onClickSaveModeAndTemperature();
                }
            });
        }

        public void unbind() {
            LayoutTopViewHolder layoutTopViewHolder = this.target;
            if (layoutTopViewHolder != null) {
                this.target = null;
                layoutTopViewHolder.mLayoutTop = null;
                layoutTopViewHolder.mBack = null;
                layoutTopViewHolder.mSetModeAndTemperature = null;
                layoutTopViewHolder.mSave = null;
                this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
                this.view7f0a011e = null;
                this.view7f0a0a77.setOnClickListener((View.OnClickListener) null);
                this.view7f0a0a77 = null;
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

    public void onCreate(Bundle bundle2) {
        super.onCreate(bundle2);
        this.referenceDetailedIduModel = (DetailedIduModel) getArguments().getParcelable(DetailedIduModel.PARCEL_KEY);
        this.mRacModelWiseData = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.referenceDetailedIduModel.cloudId);
        DetailedIduModel detailedIduModel = this.referenceDetailedIduModel;
        if (detailedIduModel != null) {
            this.mDetailedIduModel.copy(detailedIduModel);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle2) {
        View inflate = layoutInflater.inflate(R.layout.set_mode_and_temprature_global, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.temperatureLayoutViewHolder = new TemperatureLayoutViewHolder();
        this.selectModesViewHolder = new SelectModesViewHolder();
        this.layoutTopViewHolder = new LayoutTopViewHolder();
        this.temperatureLayoutViewHolder.onCreateView(inflate);
        this.selectModesViewHolder.onCreateView(inflate);
        this.layoutTopViewHolder.onCreateView(inflate);
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            int i = arguments.getInt(NavigationTransitionKeyValues.NAVIGATION_FROM);
            this.navigatingFrom = i;
            if (i == 1001) {
                getArguments().putInt(NavigationTransitionKeyValues.NAVIGATION_FROM, 1003);
            }
            this.selectedOperationMode = OperationMode.valueOf(this.mDetailedIduModel.mode);
            updateUIMain(this.mDetailedIduModel);
            onModelWiseRacInfoAvailable(this.mRacModelWiseData);
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                SetModeAndTemperatureModelWise.this.onGoBack();
            }
        });
        return inflate;
    }

    public void onStart() {
        super.onStart();
        updateUIMain(this.mDetailedIduModel);
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.temperatureLayoutViewHolder.onDestroyView();
        this.selectModesViewHolder.onDestroyView();
        this.layoutTopViewHolder.onDestroyView();
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    class TemperatureLayoutViewHolder implements Unbinder {
        public boolean isTemperatureChangeInProcess = false;
        private RepeatListener mDecreaseTemperatureRepeatListener;
        @BindView(2131362802)
        ImageButton mDecreaseTemprature;
        @BindView(2131364270)
        TextView mHumidityTitle;
        @BindView(2131364267)
        TextView mHumidityValueTextView;
        private RepeatListener mIncreaseTemperatureRepeatListener;
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
        private TemperatureHumidityDataModel temperatureHumidityDataModel;
        Unbinder unbinder;

        TemperatureLayoutViewHolder() {
            this.temperatureHumidityDataModel = new TemperatureHumidityDataModel();
            this.mIncreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
                public void onClick(View view) {
                    TemperatureLayoutViewHolder.this.isTemperatureChangeInProcess = true;
                    if (SetModeAndTemperatureModelWise.this.humiditySelected) {
                        TemperatureLayoutViewHolder.this.increaseHumidity();
                    } else {
                        TemperatureLayoutViewHolder.this.increaseTemperature();
                    }
                }
            });
            this.mDecreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
                public void onClick(View view) {
                    TemperatureLayoutViewHolder.this.isTemperatureChangeInProcess = true;
                    if (SetModeAndTemperatureModelWise.this.humiditySelected) {
                        TemperatureLayoutViewHolder.this.decreaseHumidity();
                    } else {
                        TemperatureLayoutViewHolder.this.decreaseTemperature();
                    }
                }
            });
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
            this.mIncreaseTemprature.setOnTouchListener(this.mIncreaseTemperatureRepeatListener);
            this.mDecreaseTemprature.setOnTouchListener(this.mDecreaseTemperatureRepeatListener);
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131364533})
        public void OnClickModeSelectTemp(TextView textView) {
            setTemperatureUI();
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131364270})
        public void OnClickModeSelectHumidity(TextView textView) {
            setHumidityUI();
        }

        public TemperatureHumidityDataModel getTemperatureHumidityDataModel() {
            return this.temperatureHumidityDataModel;
        }

        public void updateTemperatureUI(DetailedIduModel detailedIduModel) {
            String str;
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = SetModeAndTemperatureModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(SetModeAndTemperatureModelWise.this.mDetailedIduModel.getOperationModeEnum());
            if (racModeDetailBasedOnMode != null) {
                RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
                if (temperatureSettingType != null && temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    float referenceTemperature = detailedIduModel.relativeTemperature + racModeDetailBasedOnMode.getReferenceTemperature() + racModeDetailBasedOnMode.getDefaultTemperature();
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                    } else {
                        str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                    }
                    this.mTemperature.setText(str);
                    this.mTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
                } else if (detailedIduModel.iduTemperature != Float.MAX_VALUE) {
                    this.mTemperature.setText(TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(detailedIduModel.iduTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel));
                    this.mTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
                    this.previousTemp = detailedIduModel.iduTemperature;
                }
            }
        }

        public void updateHumidityUI(DetailedIduModel detailedIduModel) {
            this.mHumidityValueTextView.setText(detailedIduModel.humidity);
            this.temperatureHumidityDataModel.setHumidity(detailedIduModel.humidity);
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            updateTemperatureUI(detailedIduModel);
            updateHumidityUI(detailedIduModel);
            OperationMode operationModeEnum = detailedIduModel.getOperationModeEnum();
            if (operationModeEnum.equals(OperationMode.FAN)) {
                this.mLayoutTemprature.setVisibility(0);
                setFanModeUI();
                removeHumidityLabel();
            } else if (operationModeEnum.equals(OperationMode.AUTO)) {
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = SetModeAndTemperatureModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(operationModeEnum);
                if (racModeDetailBasedOnMode != null && !racModeDetailBasedOnMode.getVisibleSettings().getTemperature()) {
                    this.mLayoutTemprature.setVisibility(4);
                }
            } else {
                this.mLayoutTemprature.setVisibility(0);
                setTemperatureUI();
                removeHumidityLabel();
            }
        }

        public void setHumidityUI() {
            SetModeAndTemperatureModelWise.this.humiditySelected = true;
            this.mHumidityValueTextView.setVisibility(0);
            this.mPercentageSymbol.setVisibility(0);
            this.mTemperature.setVisibility(4);
            this.mTempratureUnit.setVisibility(4);
            this.mSetTemperatureTitle.setTextColor(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.textview_color_vd_light));
            this.mHumidityTitle.setTextColor(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.color_text_dark));
            this.mIncreaseTemprature.setVisibility(0);
            this.mDecreaseTemprature.setVisibility(0);
            this.mSetTemperatureTitle.setVisibility(0);
            this.mHumidityValueTextView.setVisibility(0);
            this.mPercentageSymbol.setVisibility(0);
        }

        public void setTemperatureUI() {
            SetModeAndTemperatureModelWise.this.humiditySelected = false;
            this.mHumidityValueTextView.setVisibility(4);
            this.mPercentageSymbol.setVisibility(4);
            this.mTemperature.setVisibility(0);
            this.mTempratureUnit.setVisibility(0);
            this.mSetTemperatureTitle.setTextColor(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.color_text_dark));
            this.mHumidityTitle.setTextColor(SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.textview_color_vd_light));
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
            if (SetModeAndTemperatureModelWise.this.mRacModelWiseData != null) {
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = SetModeAndTemperatureModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(SetModeAndTemperatureModelWise.this.mDetailedIduModel.getOperationModeEnum());
                if (SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity != null) {
                    int parseInt = Integer.parseInt(SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity);
                    if (racModeDetailBasedOnMode != null) {
                        long j = (long) parseInt;
                        if (j < racModeDetailBasedOnMode.getMaxHumidity()) {
                            int humiditySettingPitchType = (int) (j + racModeDetailBasedOnMode.getHumiditySettingPitchType());
                            SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity = String.valueOf(humiditySettingPitchType);
                            this.mHumidityValueTextView.setText(SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity);
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public void decreaseHumidity() {
            if (SetModeAndTemperatureModelWise.this.mRacModelWiseData != null) {
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = SetModeAndTemperatureModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(SetModeAndTemperatureModelWise.this.mDetailedIduModel.getOperationModeEnum());
                if (SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity != null) {
                    int parseInt = Integer.parseInt(SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity);
                    if (racModeDetailBasedOnMode != null) {
                        long j = (long) parseInt;
                        if (j > racModeDetailBasedOnMode.getMinHumidity()) {
                            int humiditySettingPitchType = (int) (j - racModeDetailBasedOnMode.getHumiditySettingPitchType());
                            SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity = String.valueOf(humiditySettingPitchType);
                            this.mHumidityValueTextView.setText(SetModeAndTemperatureModelWise.this.mDetailedIduModel.humidity);
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
            if (SetModeAndTemperatureModelWise.this.mRacModelWiseData != null && (racModeDetailBasedOnMode = SetModeAndTemperatureModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(SetModeAndTemperatureModelWise.this.mDetailedIduModel.getOperationModeEnum())) != null && (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) != null) {
                if (C19782.f471xf7673082[temperatureSettingType.ordinal()] != 1) {
                    float f = SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature;
                    if (f != Float.MAX_VALUE && f < ((float) racModeDetailBasedOnMode.getMaxTemperature())) {
                        SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature += racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                        String convertedTemperature = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                        this.mTemperature.setText(convertedTemperature);
                        this.temperatureHumidityDataModel.setTemperature(SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature);
                        this.temperatureHumidityDataModel.setTemperatureForDisplay(convertedTemperature);
                        return;
                    }
                    return;
                }
                float f2 = SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature;
                if (f2 != Float.MAX_VALUE && f2 < ((float) racModeDetailBasedOnMode.getMaxTemperature())) {
                    SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature += racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                    float referenceTemperature = racModeDetailBasedOnMode.getReferenceTemperature() + SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature;
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                    } else {
                        str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                    }
                    this.mTemperature.setText(str);
                    this.temperatureHumidityDataModel.setRelativeTemperature(SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature);
                    this.temperatureHumidityDataModel.setRelativeTemperatureForDisplay(str);
                }
            }
        }

        /* access modifiers changed from: private */
        public void decreaseTemperature() {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode;
            RacModelWiseData.TemperatureSettingType temperatureSettingType;
            String str;
            if (SetModeAndTemperatureModelWise.this.mRacModelWiseData != null && (racModeDetailBasedOnMode = SetModeAndTemperatureModelWise.this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(SetModeAndTemperatureModelWise.this.mDetailedIduModel.getOperationModeEnum())) != null && (temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType()) != null) {
                if (C19782.f471xf7673082[temperatureSettingType.ordinal()] != 1) {
                    float f = SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature;
                    if (f != Float.MAX_VALUE && f > ((float) racModeDetailBasedOnMode.getMinTemperature())) {
                        SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature -= racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                        String convertedTemperature = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                        this.mTemperature.setText(convertedTemperature);
                        this.temperatureHumidityDataModel.setTemperature(SetModeAndTemperatureModelWise.this.mDetailedIduModel.iduTemperature);
                        this.temperatureHumidityDataModel.setTemperatureForDisplay(convertedTemperature);
                        return;
                    }
                    return;
                }
                float f2 = SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature;
                if (f2 != Float.MAX_VALUE && f2 > ((float) racModeDetailBasedOnMode.getMinTemperature())) {
                    SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature -= racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                    float referenceTemperature = racModeDetailBasedOnMode.getReferenceTemperature() + SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature;
                    RacModelWiseData.TemperatureSettingType visibleTemperatureSettingType = racModeDetailBasedOnMode.getVisibleTemperatureSettingType();
                    if (visibleTemperatureSettingType == null || !visibleTemperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                        str = TemperatureValueFormatter.getConvertedTemperature(Float.valueOf(referenceTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                    } else {
                        str = TemperatureValueFormatter.getConvertedTemperatureAuto(Float.valueOf(SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature), SetModeAndTemperatureModelWise.this.mRacModelWiseData, SetModeAndTemperatureModelWise.this.mDetailedIduModel);
                    }
                    this.mTemperature.setText(str);
                    this.temperatureHumidityDataModel.setRelativeTemperature(SetModeAndTemperatureModelWise.this.mDetailedIduModel.relativeTemperature);
                    this.temperatureHumidityDataModel.setRelativeTemperatureForDisplay(str);
                }
            }
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.SetModeAndTemperatureModelWise$2 */
    static /* synthetic */ class C19782 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$model$RacModelWiseData$TemperatureSettingType */
        static final /* synthetic */ int[] f471xf7673082;

        static {
            int[] iArr = new int[RacModelWiseData.TemperatureSettingType.values().length];
            f471xf7673082 = iArr;
            try {
                iArr[RacModelWiseData.TemperatureSettingType.RELATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public boolean onBackPressed() {
        return super.onBackPressed();
    }

    class LayoutTopViewHolder implements Unbinder {
        @BindView(2131362078)
        ImageButton mBack;
        @BindView(2131363535)
        ConstraintLayout mLayoutTop;
        @BindView(2131364471)
        ImageButton mSave;
        @BindView(2131364529)
        TextView mSetModeAndTemperature;
        Unbinder unbinder;

        LayoutTopViewHolder() {
        }

        public void onCreateView(View view) {
            this.unbinder = ButterKnife.bind((Object) this, view);
        }

        /* access modifiers changed from: package-private */
        @OnClick({2131362078})
        public void OnClickBack(ImageButton imageButton) {
            SetModeAndTemperatureModelWise.this.onGoBack();
        }

        private void updateStatusBarColorOnOff() {
            OperationModeUIConfigration operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(SetModeAndTemperatureModelWise.this.mDetailedIduModel.getOperationModeEnum());
            if (operationModeUIConfigrationBasedOnMode != null) {
                SetModeAndTemperatureModelWise.this.mFragmentToActivityCallback.changeStatusBarColor(operationModeUIConfigrationBasedOnMode.getStatusBarColor());
            }
        }

        public void updateUI(DetailedIduModel detailedIduModel) {
            setNormalTopLayout(detailedIduModel);
            updateStatusBarColorOnOff();
        }

        public void setNormalTopLayout(DetailedIduModel detailedIduModel) {
            int i;
            OperationMode operationModeEnum = detailedIduModel.getOperationModeEnum();
            OperationModeUIConfigration operationModeUIConfigrationBasedOnMode = OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(operationModeEnum);
            if (operationModeUIConfigrationBasedOnMode != null) {
                this.mLayoutTop.setBackground(SetModeAndTemperatureModelWise.this.getResources().getDrawable(operationModeUIConfigrationBasedOnMode.getModeBackgroundColorResource()));
            }
            if (operationModeEnum.equals(OperationMode.AUTO)) {
                i = SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.textview_color_vd_light);
            } else {
                i = SetModeAndTemperatureModelWise.this.getResources().getColor(R.color.white);
            }
            this.mSetModeAndTemperature.setTextColor(i);
            this.mSave.setColorFilter(i);
            this.mBack.setColorFilter(i);
        }

        @OnClick({2131364471})
        public void onClickSaveModeAndTemperature() {
            SetModeAndTemperatureModelWise.this.mDetailedIduModel.mode = SetModeAndTemperatureModelWise.this.selectedOperationMode.name();
            SetModeAndTemperatureModelWise.this.referenceDetailedIduModel.copy(SetModeAndTemperatureModelWise.this.mDetailedIduModel);
            SetModeAndTemperatureModelWise.this.onGoBack();
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

    public void onGoBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public class OperationModeItem implements Comparable<OperationModeItem> {
        private int displayOrder;
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

    public void onModelWiseRacInfoAvailable(RacModelWiseData racModelWiseData) {
        this.selectModesViewHolder.updateModeListBasedOnRacType(racModelWiseData);
    }

    public void updateUIMain(DetailedIduModel detailedIduModel) {
        this.temperatureLayoutViewHolder.updateUI(detailedIduModel);
        this.selectModesViewHolder.updateUI(detailedIduModel);
        this.layoutTopViewHolder.updateUI(detailedIduModel);
    }

    public class TemperatureHumidityDataModel {
        String humidity;
        float relativeTemperature;
        String relativeTemperatureForDisplay;
        float temperature;
        String temperatureForDisplay;

        public TemperatureHumidityDataModel() {
        }

        public String getTemperatureForDisplay() {
            return this.temperatureForDisplay;
        }

        public void setTemperatureForDisplay(String str) {
            this.temperatureForDisplay = str;
        }

        public String getRelativeTemperatureForDisplay() {
            return this.relativeTemperatureForDisplay;
        }

        public void setRelativeTemperatureForDisplay(String str) {
            this.relativeTemperatureForDisplay = str;
        }

        public float getRelativeTemperature() {
            return this.relativeTemperature;
        }

        public void setRelativeTemperature(float f) {
            this.relativeTemperature = f;
        }

        public String getHumidity() {
            return this.humidity;
        }

        public void setHumidity(String str) {
            this.humidity = str;
        }

        public float getTemperature() {
            return this.temperature;
        }

        public void setTemperature(float f) {
            this.temperature = f;
        }
    }
}
