package com.jch.racWiFi.userManagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.model.PasswordStrengthSuggestionModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.List;

public class PasswordStrengthSuggestionAdapter extends RecyclerView.Adapter<PasswordStrengthSuggestionView> {
    private List<PasswordStrengthSuggestionModel> passwordStrengthSuggestionModelList;

    public class PasswordStrengthSuggestionView_ViewBinding implements Unbinder {
        private PasswordStrengthSuggestionView target;

        public PasswordStrengthSuggestionView_ViewBinding(PasswordStrengthSuggestionView passwordStrengthSuggestionView, View view) {
            this.target = passwordStrengthSuggestionView;
            passwordStrengthSuggestionView.passwordSuggestionTv = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.password_sugg_tv, "field 'passwordSuggestionTv'", TextView.class);
            passwordStrengthSuggestionView.dummyGap = C0840Utils.findRequiredView(view, R.id.gap_view, "field 'dummyGap'");
        }

        public void unbind() {
            PasswordStrengthSuggestionView passwordStrengthSuggestionView = this.target;
            if (passwordStrengthSuggestionView != null) {
                this.target = null;
                passwordStrengthSuggestionView.passwordSuggestionTv = null;
                passwordStrengthSuggestionView.dummyGap = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public void updateAdapter(List<PasswordStrengthSuggestionModel> list) {
        this.passwordStrengthSuggestionModelList = list;
        notifyDataSetChanged();
    }

    public PasswordStrengthSuggestionView onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PasswordStrengthSuggestionView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyler_view_item_password_strength_suggestion, viewGroup, false));
    }

    public void onBindViewHolder(PasswordStrengthSuggestionView passwordStrengthSuggestionView, int i) {
        boolean z = true;
        if (i != this.passwordStrengthSuggestionModelList.size() - 1) {
            z = false;
        }
        passwordStrengthSuggestionView.bind(this.passwordStrengthSuggestionModelList.get(i), z);
    }

    public int getItemCount() {
        List<PasswordStrengthSuggestionModel> list = this.passwordStrengthSuggestionModelList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class PasswordStrengthSuggestionView extends RecyclerView.ViewHolder {
        @BindView(2131362476)
        View dummyGap;
        @BindView(2131363539)
        TextView passwordSuggestionTv;

        public PasswordStrengthSuggestionView(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void bind(PasswordStrengthSuggestionModel passwordStrengthSuggestionModel, boolean z) {
            this.passwordSuggestionTv.setText(passwordStrengthSuggestionModel.suggestion);
            if (z) {
                this.dummyGap.setVisibility(0);
            } else {
                this.dummyGap.setVisibility(8);
            }
        }
    }
}
