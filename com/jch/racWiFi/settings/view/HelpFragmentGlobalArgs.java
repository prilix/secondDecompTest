package com.jch.racWiFi.settings.view;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.jch.racWiFi.HelpWebPageModel;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import java.io.Serializable;
import java.util.HashMap;

public class HelpFragmentGlobalArgs implements NavArgs {
    /* access modifiers changed from: private */
    public final HashMap arguments;

    private HelpFragmentGlobalArgs() {
        this.arguments = new HashMap();
    }

    private HelpFragmentGlobalArgs(HashMap hashMap) {
        HashMap hashMap2 = new HashMap();
        this.arguments = hashMap2;
        hashMap2.putAll(hashMap);
    }

    public static HelpFragmentGlobalArgs fromBundle(Bundle bundle) {
        HelpFragmentGlobalArgs helpFragmentGlobalArgs = new HelpFragmentGlobalArgs();
        bundle.setClassLoader(HelpFragmentGlobalArgs.class.getClassLoader());
        if (!bundle.containsKey("detailedIduModel")) {
            throw new IllegalArgumentException("Required argument \"detailedIduModel\" is missing and does not have an android:defaultValue");
        } else if (Parcelable.class.isAssignableFrom(DetailedIduModel.class) || Serializable.class.isAssignableFrom(DetailedIduModel.class)) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) bundle.get("detailedIduModel");
            if (detailedIduModel != null) {
                helpFragmentGlobalArgs.arguments.put("detailedIduModel", detailedIduModel);
                if (!bundle.containsKey("helpWebPageModel")) {
                    throw new IllegalArgumentException("Required argument \"helpWebPageModel\" is missing and does not have an android:defaultValue");
                } else if (Parcelable.class.isAssignableFrom(HelpWebPageModel.class) || Serializable.class.isAssignableFrom(HelpWebPageModel.class)) {
                    HelpWebPageModel helpWebPageModel = (HelpWebPageModel) bundle.get("helpWebPageModel");
                    if (helpWebPageModel != null) {
                        helpFragmentGlobalArgs.arguments.put("helpWebPageModel", helpWebPageModel);
                        return helpFragmentGlobalArgs;
                    }
                    throw new IllegalArgumentException("Argument \"helpWebPageModel\" is marked as non-null but was passed a null value.");
                } else {
                    throw new UnsupportedOperationException(HelpWebPageModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
            } else {
                throw new IllegalArgumentException("Argument \"detailedIduModel\" is marked as non-null but was passed a null value.");
            }
        } else {
            throw new UnsupportedOperationException(DetailedIduModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
    }

    public DetailedIduModel getDetailedIduModel() {
        return (DetailedIduModel) this.arguments.get("detailedIduModel");
    }

    public HelpWebPageModel getHelpWebPageModel() {
        return (HelpWebPageModel) this.arguments.get("helpWebPageModel");
    }

    public Bundle toBundle() {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HelpFragmentGlobalArgs helpFragmentGlobalArgs = (HelpFragmentGlobalArgs) obj;
        if (this.arguments.containsKey("detailedIduModel") != helpFragmentGlobalArgs.arguments.containsKey("detailedIduModel")) {
            return false;
        }
        if (getDetailedIduModel() == null ? helpFragmentGlobalArgs.getDetailedIduModel() != null : !getDetailedIduModel().equals(helpFragmentGlobalArgs.getDetailedIduModel())) {
            return false;
        }
        if (this.arguments.containsKey("helpWebPageModel") != helpFragmentGlobalArgs.arguments.containsKey("helpWebPageModel")) {
            return false;
        }
        return getHelpWebPageModel() == null ? helpFragmentGlobalArgs.getHelpWebPageModel() == null : getHelpWebPageModel().equals(helpFragmentGlobalArgs.getHelpWebPageModel());
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getDetailedIduModel() != null ? getDetailedIduModel().hashCode() : 0) + 31) * 31;
        if (getHelpWebPageModel() != null) {
            i = getHelpWebPageModel().hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "HelpFragmentGlobalArgs{detailedIduModel=" + getDetailedIduModel() + ", helpWebPageModel=" + getHelpWebPageModel() + "}";
    }

    public static class Builder {
        private final HashMap arguments;

        public Builder(HelpFragmentGlobalArgs helpFragmentGlobalArgs) {
            HashMap hashMap = new HashMap();
            this.arguments = hashMap;
            hashMap.putAll(helpFragmentGlobalArgs.arguments);
        }

        public Builder(DetailedIduModel detailedIduModel, HelpWebPageModel helpWebPageModel) {
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

        public HelpFragmentGlobalArgs build() {
            return new HelpFragmentGlobalArgs(this.arguments);
        }

        public Builder setDetailedIduModel(DetailedIduModel detailedIduModel) {
            if (detailedIduModel != null) {
                this.arguments.put("detailedIduModel", detailedIduModel);
                return this;
            }
            throw new IllegalArgumentException("Argument \"detailedIduModel\" is marked as non-null but was passed a null value.");
        }

        public Builder setHelpWebPageModel(HelpWebPageModel helpWebPageModel) {
            if (helpWebPageModel != null) {
                this.arguments.put("helpWebPageModel", helpWebPageModel);
                return this;
            }
            throw new IllegalArgumentException("Argument \"helpWebPageModel\" is marked as non-null but was passed a null value.");
        }

        public DetailedIduModel getDetailedIduModel() {
            return (DetailedIduModel) this.arguments.get("detailedIduModel");
        }

        public HelpWebPageModel getHelpWebPageModel() {
            return (HelpWebPageModel) this.arguments.get("helpWebPageModel");
        }
    }
}
