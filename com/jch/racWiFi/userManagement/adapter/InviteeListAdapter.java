package com.jch.racWiFi.userManagement.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.dashboard.FcmDashboard;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.liveData.InviteeLiveDataHolder;
import com.jch.racWiFi.userManagement.model.InviteeList;
import com.jch.racWiFi.userManagement.model.InviteeModel;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.network.InviteUserVerifyNetworkDispatcher;
import com.jch_hitachi.aircloudglobal.R;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class InviteeListAdapter extends RecyclerView.Adapter<InviteeViewHolder> {
    public static final long NOTIFICATION_DISMISS_TIMEOUT = 5000;
    FcmDashboard fcmDashboard;
    /* access modifiers changed from: private */
    public InvitationStatusCallBack invitationStatusCallBack;
    /* access modifiers changed from: private */
    public LifecycleOwner lifecycleOwner;
    /* access modifiers changed from: private */
    public InviteeList mInviteeList;

    public interface InvitationStatusCallBack {
        void invitationApiResponse(GenericResponse genericResponse);
    }

    class InviteeViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131362120)
        Button accept;
        /* access modifiers changed from: private */
        public AcceptedDecidedViewHolder acceptedDecidedViewHolder;
        private Context context;
        @BindView(2131362141)
        Button decline;
        @BindView(2131364294)
        TextView description;
        @BindView(2131364264)
        TextView homeName;
        @BindView(2131363235)
        View outerLayout;
        @BindView(2131363036)
        CircleImageView profilePic;
        @BindView(2131363549)
        ProgressBar progressBar;

        public class AcceptedDecidedViewHolder_ViewBinding implements Unbinder {
            private AcceptedDecidedViewHolder target;

            public AcceptedDecidedViewHolder_ViewBinding(AcceptedDecidedViewHolder acceptedDecidedViewHolder, View view) {
                this.target = acceptedDecidedViewHolder;
                acceptedDecidedViewHolder.outerLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_notification_accepted_declined, "field 'outerLayout'", ConstraintLayout.class);
                acceptedDecidedViewHolder.acceptedOrDeclinedImageView = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_notification_accepted_declined, "field 'acceptedOrDeclinedImageView'", ImageView.class);
                acceptedDecidedViewHolder.acceptedOrDeclinedTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_invite_accepted_declined, "field 'acceptedOrDeclinedTextView'", TextView.class);
            }

            public void unbind() {
                AcceptedDecidedViewHolder acceptedDecidedViewHolder = this.target;
                if (acceptedDecidedViewHolder != null) {
                    this.target = null;
                    acceptedDecidedViewHolder.outerLayout = null;
                    acceptedDecidedViewHolder.acceptedOrDeclinedImageView = null;
                    acceptedDecidedViewHolder.acceptedOrDeclinedTextView = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        private void showProgress() {
            this.progressBar.setVisibility(0);
        }

        /* access modifiers changed from: private */
        public void hideProgress() {
            this.progressBar.setVisibility(4);
        }

        public class AcceptedDecidedViewHolder {
            @BindView(2131362830)
            ImageView acceptedOrDeclinedImageView;
            @BindView(2131364293)
            TextView acceptedOrDeclinedTextView;
            @BindView(2131363236)
            ConstraintLayout outerLayout;

            public AcceptedDecidedViewHolder(View view) {
                ButterKnife.bind((Object) this, view);
                InviteeViewHolder.this.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#c3002f"), PorterDuff.Mode.SRC_IN);
            }

            public void showAcceptedView() {
                this.outerLayout.setVisibility(0);
                InviteeViewHolder.this.homeName.setVisibility(4);
                this.acceptedOrDeclinedImageView.setImageResource(R.drawable.ic_green_tick_svg);
                this.acceptedOrDeclinedTextView.setText(R.string.notification_lbl_inviteAccepted);
                InviteeViewHolder.this.accept.setVisibility(8);
                InviteeViewHolder.this.decline.setVisibility(8);
            }

            public void showDeclinedView() {
                this.outerLayout.setVisibility(0);
                InviteeViewHolder.this.homeName.setVisibility(4);
                this.acceptedOrDeclinedImageView.setImageResource(R.drawable.ic_close_red);
                this.acceptedOrDeclinedTextView.setText(R.string.notification_lbl_inviteDeclined);
                InviteeViewHolder.this.accept.setVisibility(8);
                InviteeViewHolder.this.decline.setVisibility(8);
            }

            public void clearView() {
                this.outerLayout.setVisibility(4);
                InviteeViewHolder.this.homeName.setVisibility(0);
                InviteeViewHolder.this.accept.setVisibility(0);
                InviteeViewHolder.this.decline.setVisibility(0);
            }
        }

        @OnClick({2131362120})
        public void onClickAccept(View view) {
            final InviteeModel inviteeModel = (InviteeModel) view.getTag();
            inviteeModel.setAccepted();
            showProgress();
            InviteeListAdapter.this.sendResponse(inviteeModel).observeSingleEvent(InviteeListAdapter.this.lifecycleOwner, new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    InviteeViewHolder.this.hideProgress();
                    InviteeListAdapter.this.invitationStatusCallBack.invitationApiResponse(genericResponse);
                    InviteeLiveDataHolder.getInstance().getInvitationAcceptedMutableLiveData().postValue(inviteeModel);
                    if (genericResponse.isApiSuccessful()) {
                        InviteeViewHolder.this.acceptedDecidedViewHolder.showAcceptedView();
                        InviteeListAdapter.this.notifyDataSetChanged();
                    }
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (InviteeListAdapter.this.lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                                InviteeLiveDataHolder.getInstance().getInviteeListMutableLiveData().postValue(InviteeListAdapter.this.mInviteeList);
                                InviteeListAdapter.this.mInviteeList.remove(inviteeModel);
                                InviteeListAdapter.this.notifyDataSetChanged();
                                InviteeListAdapter.this.fcmDashboard.getSectionAdapter().notifyDataSetChanged();
                            }
                        }
                    }, 5000);
                }
            });
        }

        @OnClick({2131362141})
        public void onClickDecline(View view) {
            final InviteeModel inviteeModel = (InviteeModel) view.getTag();
            inviteeModel.setDeclined();
            showProgress();
            InviteeListAdapter.this.sendResponse(inviteeModel).observeSingleEvent(InviteeListAdapter.this.lifecycleOwner, new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    InviteeListAdapter.this.invitationStatusCallBack.invitationApiResponse(genericResponse);
                    InviteeViewHolder.this.hideProgress();
                    if (genericResponse.isApiSuccessful()) {
                        InviteeViewHolder.this.acceptedDecidedViewHolder.showDeclinedView();
                        InviteeListAdapter.this.notifyDataSetChanged();
                    }
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (InviteeListAdapter.this.lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                                InviteeListAdapter.this.mInviteeList.remove(inviteeModel);
                                InviteeLiveDataHolder.getInstance().getInviteeListMutableLiveData().postValue(InviteeListAdapter.this.mInviteeList);
                                InviteeListAdapter.this.notifyDataSetChanged();
                                InviteeListAdapter.this.fcmDashboard.getSectionAdapter().notifyDataSetChanged();
                            }
                        }
                    }, 5000);
                }
            });
        }

        InviteeViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.context = view.getContext();
            this.acceptedDecidedViewHolder = new AcceptedDecidedViewHolder(view);
        }

        public void bind(InviteeModel inviteeModel) {
            this.accept.setTag(inviteeModel);
            this.decline.setTag(inviteeModel);
            this.homeName.setText(this.context.getString(R.string.notification_lbl_newInvite, new Object[]{inviteeModel.getFamilyName()}));
            this.description.setText(this.context.getString(R.string.notification_lbl_inviteDesc, new Object[]{inviteeModel.getFamilyName(), inviteeModel.ownerName}));
            ProfilePicture.loadIntoImageView(this.profilePic, inviteeModel.profilePicture);
            int i = C23721.f479xb7deba1f[inviteeModel.invitationState.ordinal()];
            if (i == 1) {
                this.acceptedDecidedViewHolder.showAcceptedView();
            } else if (i == 2) {
                this.acceptedDecidedViewHolder.showDeclinedView();
            } else if (i == 3) {
                this.acceptedDecidedViewHolder.clearView();
            }
        }
    }

    public class InviteeViewHolder_ViewBinding implements Unbinder {
        private InviteeViewHolder target;
        private View view7f0a0148;
        private View view7f0a015d;

        public InviteeViewHolder_ViewBinding(final InviteeViewHolder inviteeViewHolder, View view) {
            this.target = inviteeViewHolder;
            inviteeViewHolder.outerLayout = C0840Utils.findRequiredView(view, R.id.layout_notification, "field 'outerLayout'");
            inviteeViewHolder.homeName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_home_name, "field 'homeName'", TextView.class);
            inviteeViewHolder.description = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_invite_notification_detail, "field 'description'", TextView.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_accept_notofication, "field 'accept' and method 'onClickAccept'");
            inviteeViewHolder.accept = (Button) C0840Utils.castView(findRequiredView, R.id.button_accept_notofication, "field 'accept'", Button.class);
            this.view7f0a0148 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    inviteeViewHolder.onClickAccept(view);
                }
            });
            View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_decline_notification, "field 'decline' and method 'onClickDecline'");
            inviteeViewHolder.decline = (Button) C0840Utils.castView(findRequiredView2, R.id.button_decline_notification, "field 'decline'", Button.class);
            this.view7f0a015d = findRequiredView2;
            findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    inviteeViewHolder.onClickDecline(view);
                }
            });
            inviteeViewHolder.profilePic = (CircleImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_user_profile_notification, "field 'profilePic'", CircleImageView.class);
            inviteeViewHolder.progressBar = (ProgressBar) C0840Utils.findRequiredViewAsType(view, R.id.progress, "field 'progressBar'", ProgressBar.class);
        }

        public void unbind() {
            InviteeViewHolder inviteeViewHolder = this.target;
            if (inviteeViewHolder != null) {
                this.target = null;
                inviteeViewHolder.outerLayout = null;
                inviteeViewHolder.homeName = null;
                inviteeViewHolder.description = null;
                inviteeViewHolder.accept = null;
                inviteeViewHolder.decline = null;
                inviteeViewHolder.profilePic = null;
                inviteeViewHolder.progressBar = null;
                this.view7f0a0148.setOnClickListener((View.OnClickListener) null);
                this.view7f0a0148 = null;
                this.view7f0a015d.setOnClickListener((View.OnClickListener) null);
                this.view7f0a015d = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public void setInviteeList(InviteeList inviteeList, FcmDashboard fcmDashboard2) {
        this.mInviteeList = inviteeList;
        this.fcmDashboard = fcmDashboard2;
        notifyDataSetChanged();
    }

    public InviteeListAdapter(LifecycleOwner lifecycleOwner2, InvitationStatusCallBack invitationStatusCallBack2) {
        this.lifecycleOwner = lifecycleOwner2;
        this.invitationStatusCallBack = invitationStatusCallBack2;
    }

    public InviteeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new InviteeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_invite, viewGroup, false));
    }

    public void onBindViewHolder(InviteeViewHolder inviteeViewHolder, int i) {
        inviteeViewHolder.bind((InviteeModel) this.mInviteeList.get(i));
    }

    public int getItemCount() {
        InviteeList inviteeList = this.mInviteeList;
        if (inviteeList == null) {
            return 0;
        }
        return inviteeList.size();
    }

    /* renamed from: com.jch.racWiFi.userManagement.adapter.InviteeListAdapter$1 */
    static /* synthetic */ class C23721 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$userManagement$model$InviteeModel$InvitationState */
        static final /* synthetic */ int[] f479xb7deba1f;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.userManagement.model.InviteeModel$InvitationState[] r0 = com.jch.racWiFi.userManagement.model.InviteeModel.InvitationState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f479xb7deba1f = r0
                com.jch.racWiFi.userManagement.model.InviteeModel$InvitationState r1 = com.jch.racWiFi.userManagement.model.InviteeModel.InvitationState.ACCEPTED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f479xb7deba1f     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.userManagement.model.InviteeModel$InvitationState r1 = com.jch.racWiFi.userManagement.model.InviteeModel.InvitationState.DECLINED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f479xb7deba1f     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.userManagement.model.InviteeModel$InvitationState r1 = com.jch.racWiFi.userManagement.model.InviteeModel.InvitationState.NONE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.adapter.InviteeListAdapter.C23721.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public SingleLiveEvent<GenericResponse> sendResponse(InviteeModel inviteeModel) {
        return new InviteUserVerifyNetworkDispatcher().inviteUser(inviteeModel);
    }
}
