package com.jch.racWiFi.iduManagement.view.viewImpl;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.HelpWebPageModel;
import com.jch.racWiFi.MainGraphDirections;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch_hitachi.aircloudglobal.R;
import java.io.Serializable;
import java.util.HashMap;

public class IndividualIDUControlFragmentModelWiseDirections {
    private IndividualIDUControlFragmentModelWiseDirections() {
    }

    public static ActionIndividualIDUControlFragmentToHelpFragment actionIndividualIDUControlFragmentToHelpFragment(DetailedIduModel detailedIduModel, HelpWebPageModel helpWebPageModel) {
        return new ActionIndividualIDUControlFragmentToHelpFragment(detailedIduModel, helpWebPageModel);
    }

    public static NavDirections actionIndividualIDUControlFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_individualIDUControlFragment_to_homePageFragment);
    }

    public static NavDirections actionIndividualIDUControlFragmentToSetTimerFragmentV2() {
        return new ActionOnlyNavDirections(R.id.action_individualIDUControlFragment_to_setTimerFragmentV2);
    }

    public static NavDirections actionIndividualIDUControlFragmentToCleaningStartFragment() {
        return new ActionOnlyNavDirections(R.id.action_individualIDUControlFragment_to_cleaningStartFragment);
    }

    public static NavDirections actionWeeklyTimerDevicesFragmentToWeeklyTimerFragmentV3() {
        return new ActionOnlyNavDirections(R.id.action_weeklyTimerDevicesFragment_to_weeklyTimerFragmentV3);
    }

    public static NavDirections actionIndividualIDUControlFragmentToCustomerCareFragment() {
        return new ActionOnlyNavDirections(R.id.action_individualIDUControlFragment_to_customerCareFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }

    public static class ActionIndividualIDUControlFragmentToHelpFragment implements NavDirections {
        private final HashMap arguments;

        public int getActionId() {
            return R.id.action_individualIDUControlFragment_to_helpFragment;
        }

        private ActionIndividualIDUControlFragmentToHelpFragment(DetailedIduModel detailedIduModel, HelpWebPageModel helpWebPageModel) {
            HashMap hashMap = new HashMap();
            this.arguments = hashMap;
            if (detailedIduModel != null) {
                hashMap.put("detailedIduModel", detailedIduModel);
                if (helpWebPageModel != null) {
                    hashMap.put("helpWebPageModel", helpWebPageModel);
                    return;
                }
                throw new IllegalArgumentException("Argument \"helpWebPageModel\" is marked as non-null but was passed a null value.");
            }
            throw new IllegalArgumentException("Argument \"detailedIduModel\" is marked as non-null but was passed a null value.");
        }

        public ActionIndividualIDUControlFragmentToHelpFragment setDetailedIduModel(DetailedIduModel detailedIduModel) {
            if (detailedIduModel != null) {
                this.arguments.put("detailedIduModel", detailedIduModel);
                return this;
            }
            throw new IllegalArgumentException("Argument \"detailedIduModel\" is marked as non-null but was passed a null value.");
        }

        public ActionIndividualIDUControlFragmentToHelpFragment setHelpWebPageModel(HelpWebPageModel helpWebPageModel) {
            if (helpWebPageModel != null) {
                this.arguments.put("helpWebPageModel", helpWebPageModel);
                return this;
            }
            throw new IllegalArgumentException("Argument \"helpWebPageModel\" is marked as non-null but was passed a null value.");
        }

        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (this.arguments.containsKey("detailedIduModel")) {
                DetailedIduModel detailedIduModel = (DetailedIduModel) this.arguments.get("detailedIduModel");
                if (Parcelable.class.isAssignableFrom(DetailedIduModel.class) || detailedIduModel == null) {
                    bundle.putParcelable("detailedIduModel", Parcelable.class.cast(detailedIduModel));
                } else if (Serializable.class.isAssignableFrom(DetailedIduModel.class)) {
                    bundle.putSerializable("detailedIduModel", Serializable.class.cast(detailedIduModel));
                } else {
                    throw new UnsupportedOperationException(DetailedIduModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
            }
            if (this.arguments.containsKey("helpWebPageModel")) {
                HelpWebPageModel helpWebPageModel = (HelpWebPageModel) this.arguments.get("helpWebPageModel");
                if (Parcelable.class.isAssignableFrom(HelpWebPageModel.class) || helpWebPageModel == null) {
                    bundle.putParcelable("helpWebPageModel", Parcelable.class.cast(helpWebPageModel));
                } else if (Serializable.class.isAssignableFrom(HelpWebPageModel.class)) {
                    bundle.putSerializable("helpWebPageModel", Serializable.class.cast(helpWebPageModel));
                } else {
                    throw new UnsupportedOperationException(HelpWebPageModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
            }
            return bundle;
        }

        public DetailedIduModel getDetailedIduModel() {
            return (DetailedIduModel) this.arguments.get("detailedIduModel");
        }

        public HelpWebPageModel getHelpWebPageModel() {
            return (HelpWebPageModel) this.arguments.get("helpWebPageModel");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ActionIndividualIDUControlFragmentToHelpFragment actionIndividualIDUControlFragmentToHelpFragment = (ActionIndividualIDUControlFragmentToHelpFragment) obj;
            if (this.arguments.containsKey("detailedIduModel") != actionIndividualIDUControlFragmentToHelpFragment.arguments.containsKey("detailedIduModel")) {
                return false;
            }
            if (getDetailedIduModel() == null ? actionIndividualIDUControlFragmentToHelpFragment.getDetailedIduModel() != null : !getDetailedIduModel().equals(actionIndividualIDUControlFragmentToHelpFragment.getDetailedIduModel())) {
                return false;
            }
            if (this.arguments.containsKey("helpWebPageModel") != actionIndividualIDUControlFragmentToHelpFragment.arguments.containsKey("helpWebPageModel")) {
                return false;
            }
            if (getHelpWebPageModel() == null ? actionIndividualIDUControlFragmentToHelpFragment.getHelpWebPageModel() == null : getHelpWebPageModel().equals(actionIndividualIDUControlFragmentToHelpFragment.getHelpWebPageModel())) {
                return getActionId() == actionIndividualIDUControlFragmentToHelpFragment.getActionId();
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((getDetailedIduModel() != null ? getDetailedIduModel().hashCode() : 0) + 31) * 31;
            if (getHelpWebPageModel() != null) {
                i = getHelpWebPageModel().hashCode();
            }
            return ((hashCode + i) * 31) + getActionId();
        }

        public String toString() {
            return "ActionIndividualIDUControlFragmentToHelpFragment(actionId=" + getActionId() + "){detailedIduModel=" + getDetailedIduModel() + ", helpWebPageModel=" + getHelpWebPageModel() + "}";
        }
    }
}
