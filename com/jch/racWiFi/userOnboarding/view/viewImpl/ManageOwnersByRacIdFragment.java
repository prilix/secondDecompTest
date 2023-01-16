package com.jch.racWiFi.userOnboarding.view.viewImpl;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.RemoveOwnerOfAcDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userOnboarding.model.RacOwnersDataModel;
import com.jch.racWiFi.userOnboarding.presenter.DeviceDetailsPresenter;
import com.jch.racWiFi.userOnboarding.presenter.GetRacOwnersPresenter;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.DeviceDetailsPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.DeviceDetailsView;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okhttp3.ResponseBody;
import p011de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class ManageOwnersByRacIdFragment extends GenericFragment implements GetRacOwnersPresenter.IRacOwnersPresenterInterface, DeviceDetailsView {
    private ConfirmationDialog confirmationDialog;
    /* access modifiers changed from: private */
    public DeviceDetailsPresenter deviceDetailsPresenter;
    private List<UserRecyclerItemModel> familyGroupMenuItemsUsers = new ArrayList();
    private GetRacOwnersPresenter getRacOwnersPresenter;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131364331)
    TextView mSubTitle;
    @BindView(2131364363)
    TextView mTitle;
    @BindView(2131364330)
    TextView msubTitleMessage;
    NavController navController;
    /* access modifiers changed from: private */
    public long racID;
    private String racName = "";
    /* access modifiers changed from: private */
    public RemoveOwnerOfAcDialog removeHomeDialog;
    private Unbinder unbinder;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;

    public void onDeviceRenamed(String str) {
    }

    public void onRenamingFailed(Response<ResponseBody> response) {
    }

    class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
        Context context;
        List<UserRecyclerItemModel> mUserRecyclerItemModelsList;

        public class UserViewHolder_ViewBinding implements Unbinder {
            private UserViewHolder target;

            public UserViewHolder_ViewBinding(UserViewHolder userViewHolder, View view) {
                this.target = userViewHolder;
                userViewHolder.mProfileCircleImageView = (CircleImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_user_profile_manage_user, "field 'mProfileCircleImageView'", CircleImageView.class);
                userViewHolder.mHomeNameTV = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_name_manage_user, "field 'mHomeNameTV'", TextView.class);
                userViewHolder.mRoleNameTV = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_type_manage_user, "field 'mRoleNameTV'", TextView.class);
                userViewHolder.mCloseIV = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_right_swipe, "field 'mCloseIV'", ImageView.class);
            }

            public void unbind() {
                UserViewHolder userViewHolder = this.target;
                if (userViewHolder != null) {
                    this.target = null;
                    userViewHolder.mProfileCircleImageView = null;
                    userViewHolder.mHomeNameTV = null;
                    userViewHolder.mRoleNameTV = null;
                    userViewHolder.mCloseIV = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        UserRecyclerViewAdapter(List list, Context context2) {
            this.mUserRecyclerItemModelsList = list;
            this.context = context2;
        }

        public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new UserViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_manage_owners_by_rac, viewGroup, false));
        }

        public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
            userViewHolder.bind(this.mUserRecyclerItemModelsList.get(i));
        }

        public int getItemCount() {
            return this.mUserRecyclerItemModelsList.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131362982)
            ImageView mCloseIV;
            @BindView(2131364701)
            TextView mHomeNameTV;
            @BindView(2131363035)
            CircleImageView mProfileCircleImageView;
            @BindView(2131364717)
            TextView mRoleNameTV;

            public UserViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }

            public void bind(final UserRecyclerItemModel userRecyclerItemModel) {
                if (FamilyGroupList.getCurrentFamily().familyId != userRecyclerItemModel.familyId) {
                    this.mHomeNameTV.setText(userRecyclerItemModel.getName());
                    this.mRoleNameTV.setText(userRecyclerItemModel.familyName);
                    this.mCloseIV.setVisibility(0);
                    if (userRecyclerItemModel.profilePicture != null) {
                        ProfilePicture.loadIntoImageView(this.mProfileCircleImageView, userRecyclerItemModel.profilePicture);
                    } else {
                        this.mProfileCircleImageView.setImageResource(R.drawable.ic_user_pic);
                    }
                    this.mCloseIV.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            ManageOwnersByRacIdFragment.this.saveSelectedChanges(userRecyclerItemModel);
                        }
                    });
                    return;
                }
                if (userRecyclerItemModel.profilePicture != null) {
                    ProfilePicture.loadIntoImageView(this.mProfileCircleImageView, userRecyclerItemModel.profilePicture);
                } else {
                    this.mProfileCircleImageView.setImageResource(R.drawable.ic_user_pic);
                }
                this.mHomeNameTV.setText(ManageOwnersByRacIdFragment.this.getString(R.string.manageUser_lbl_me));
                this.mRoleNameTV.setText(ManageOwnersByRacIdFragment.this.getString(R.string.manageUser_lbl_myHome));
                this.mCloseIV.setVisibility(4);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.manage_owners_frames, viewGroup, false);
        this.unbinder = ButterKnife.bind((Object) this, inflate);
        this.navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        this.deviceDetailsPresenter = new DeviceDetailsPresenterImpl(this);
        Bundle arguments = getArguments();
        this.racID = arguments.getLong(Values.RAC_ID);
        this.racName = arguments.getString(Values.RAC_NAME);
        this.familyGroupMenuItemsUsers.clear();
        this.userRecyclerViewAdapter = new UserRecyclerViewAdapter(this.familyGroupMenuItemsUsers, getActivity());
        GetRacOwnersPresenter getRacOwnersPresenter2 = new GetRacOwnersPresenter(this);
        this.getRacOwnersPresenter = getRacOwnersPresenter2;
        getRacOwnersPresenter2.registerEventBus();
        showPleaseWaitDialog();
        this.getRacOwnersPresenter.getFamilyGroup(FamilyGroupList.getCurrentFamily().familyId, this.racID);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view_manage_homes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.userRecyclerViewAdapter);
        this.mTitle.setText(getString(R.string.manageUser_lbl_ACSettings));
        this.mSubTitle.setText(getString(R.string.manageAc_lbl_manageOwners));
        this.msubTitleMessage.setText(getString(R.string.manageAc_lbl_manageOwnerAcDesc));
        return inflate;
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
        this.getRacOwnersPresenter.unregisterEventBus();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onNetworkCallSuccess() {
        Logger.m47e("NETWORK_FAILURE", "called onNetworkcallSuccess() fragment");
        dismissPleaseWaitDialog();
    }

    public void onNetworkCallFailure(Throwable th) {
        Logger.m47e("NETWORK_FAILURE", "called onNetwork() fragment");
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        Logger.m47e("NETWORK_FAILURE", "called onNetwork() fragment");
    }

    @OnClick({2131362078})
    public void onClickBackButton() {
        this.navController.navigateUp();
    }

    private List<UserRecyclerItemModel> populateMenuItemsUsers(RacOwnersDataModel.RacOwnersDataModelResponseSuccess racOwnersDataModelResponseSuccess) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < racOwnersDataModelResponseSuccess.familyResult.length; i++) {
            UserRecyclerItemModel userRecyclerItemModel = new UserRecyclerItemModel();
            userRecyclerItemModel.setName(racOwnersDataModelResponseSuccess.familyResult[i].createdBy);
            userRecyclerItemModel.setFamilyId(racOwnersDataModelResponseSuccess.familyResult[i].familyId);
            userRecyclerItemModel.setRoleName(racOwnersDataModelResponseSuccess.familyResult[i].role.name);
            String str = racOwnersDataModelResponseSuccess.familyResult[i].familyName;
            userRecyclerItemModel.setFamilyName(getString(R.string.common_lbl_homeColon) + " " + str);
            userRecyclerItemModel.setProfilePicture(racOwnersDataModelResponseSuccess.familyResult[i].pictureData);
            userRecyclerItemModel.setProfilePicture(racOwnersDataModelResponseSuccess.familyResult[i].pictureData);
            if (FamilyGroupList.getCurrentFamily().familyId == racOwnersDataModelResponseSuccess.familyResult[i].familyId) {
                arrayList.add(0, userRecyclerItemModel);
            } else {
                arrayList.add(userRecyclerItemModel);
            }
        }
        return arrayList;
    }

    public void onRacOwnersFetchSuccess(RacOwnersDataModel.RacOwnersDataModelResponseSuccess racOwnersDataModelResponseSuccess) {
        dismissPleaseWaitDialog();
        int i = UserInfo.getCurrentUserInfo(getCoreActivity()).familyId;
        this.familyGroupMenuItemsUsers.clear();
        this.familyGroupMenuItemsUsers.addAll(populateMenuItemsUsers(racOwnersDataModelResponseSuccess));
        Collections.sort(this.familyGroupMenuItemsUsers, new Comparator<UserRecyclerItemModel>() {
            public int compare(UserRecyclerItemModel userRecyclerItemModel, UserRecyclerItemModel userRecyclerItemModel2) {
                if (userRecyclerItemModel.familyId == UserInfo.getCurrentUserInfo(ManageOwnersByRacIdFragment.this.getCoreActivity()).familyId) {
                    return -1;
                }
                if (userRecyclerItemModel2.familyId == UserInfo.getCurrentUserInfo(ManageOwnersByRacIdFragment.this.getCoreActivity()).familyId) {
                    return 1;
                }
                return userRecyclerItemModel.name.toLowerCase().compareTo(userRecyclerItemModel2.name.toLowerCase());
            }
        });
        this.userRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void onRacOwnersFetchFailure(RacOwnersDataModel.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure) {
        if (familyGroupsModelResponseSuccessFailure.statusCode == 500) {
            Toaster.makeToast(getActivity(), getString(R.string.errorCode_alert_PCF001), 0);
        } else {
            Toaster.makeToast(getActivity(), getString(R.string.manageAc_alert_unableToFetchAcOwners), 0);
        }
        dismissPleaseWaitDialog();
    }

    public void onDeviceRemoved() {
        dismissPleaseWaitDialog();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onDeviceNotRemoved(int i, GenericResponse.GenericErrorBody genericErrorBody) {
        dismissPleaseWaitDialog();
        if (i == 403) {
            Toast.makeText(getContext(), getString(R.string.forbidden_action), 0).show();
        } else if (i != 404) {
            if (i == 406) {
                Toast.makeText(getContext(), getString(R.string.kii_exception), 0).show();
            } else if (i != 412) {
                Toast.makeText(getContext(), getString(R.string.manageAc_alert_couldNotRemoveAC), 0).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.errorCode_alert_PCF009), 0).show();
            }
        } else if (genericErrorBody.code != null) {
            String str = genericErrorBody.code;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1995143803:
                    if (str.equals("NFE002")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1995143801:
                    if (str.equals("NFE004")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143773:
                    if (str.equals(StatusCode.NO_RAC_FOUND)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1995143772:
                    if (str.equals(StatusCode.RAC_DOES_NOT_BELONG_TO_THIS_FAMILY)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE002), 0).show();
                    return;
                case 1:
                    Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE004), 0).show();
                    return;
                case 2:
                    Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE011), 0).show();
                    return;
                case 3:
                    Toast.makeText(getContext(), getString(R.string.errorCode_alert_NFE012), 0).show();
                    return;
                default:
                    Toast.makeText(getContext(), getString(R.string.manageAc_alert_acDeletedOrNotExists), 0).show();
                    return;
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.manageAc_alert_acDeletedOrNotExists), 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void saveSelectedChanges(final UserRecyclerItemModel userRecyclerItemModel) {
        RemoveOwnerOfAcDialog removeOwnerOfAcDialog = new RemoveOwnerOfAcDialog(getContext());
        this.removeHomeDialog = removeOwnerOfAcDialog;
        removeOwnerOfAcDialog.setText(getString(R.string.common_lbl_homeColon) + " " + userRecyclerItemModel.getName(), this.racName);
        this.removeHomeDialog.setParentView((View) null);
        this.removeHomeDialog.yseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ManageOwnersByRacIdFragment.this.showPleaseWaitDialog();
                ManageOwnersByRacIdFragment.this.deviceDetailsPresenter.unBoardIdu(ManageOwnersByRacIdFragment.this, userRecyclerItemModel.familyId, ManageOwnersByRacIdFragment.this.racID);
                ManageOwnersByRacIdFragment.this.removeHomeDialog.dismiss();
            }
        });
        this.removeHomeDialog.show();
    }

    public class UserRecyclerItemModel {
        /* access modifiers changed from: private */
        public int familyId;
        /* access modifiers changed from: private */
        public String familyName;
        /* access modifiers changed from: private */
        public String name;
        private View.OnClickListener onClickListener;
        /* access modifiers changed from: private */
        public ProfilePicture profilePicture;
        private String roleName;

        public UserRecyclerItemModel() {
        }

        public String getRoleName() {
            return this.roleName;
        }

        public void setRoleName(String str) {
            this.roleName = str;
        }

        public int getFamilyId() {
            return this.familyId;
        }

        public void setFamilyId(int i) {
            this.familyId = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public View.OnClickListener getOnClickListener() {
            return this.onClickListener;
        }

        public void setOnClickListener(View.OnClickListener onClickListener2) {
            this.onClickListener = onClickListener2;
        }

        public String getFamilyName() {
            return this.familyName;
        }

        public void setFamilyName(String str) {
            this.familyName = str;
        }

        public ProfilePicture getProfilePicture() {
            return this.profilePicture;
        }

        public void setProfilePicture(ProfilePicture profilePicture2) {
            this.profilePicture = profilePicture2;
        }
    }
}
