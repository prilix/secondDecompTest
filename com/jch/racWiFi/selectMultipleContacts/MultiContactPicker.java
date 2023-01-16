package com.jch.racWiFi.selectMultipleContacts;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.selectMultipleContacts.RxContacts.Contact;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiContactPicker {
    public static final int CHOICE_MODE_MULTIPLE = 0;
    public static final int CHOICE_MODE_SINGLE = 1;
    public static final int LOAD_ASYNC = 0;
    public static final int LOAD_SYNC = 1;

    public static class Builder implements Serializable {
        public static int noOfContactToBeSelected;
        protected transient Activity acc;
        protected Integer animationCloseEnter;
        protected Integer animationCloseExit;
        protected Integer animationOpenEnter;
        protected Integer animationOpenExit;
        protected int bubbleColor;
        protected int bubbleTextColor;
        protected LimitColumn columnLimit = LimitColumn.NONE;
        protected transient Fragment frag;
        protected int handleColor;
        protected boolean hideScrollbar;
        protected int loadingMode = 0;
        protected Integer searchIconColor;
        protected ArrayList<Long> selectedItems = new ArrayList<>();
        protected int selectionMode = 0;
        protected boolean showTrack = true;
        protected int theme = C1655R.style.MultiContactPicker_Azure;
        protected String titleText;
        protected int trackColor;

        public Builder(Activity activity) {
            this.acc = activity;
        }

        public Builder(Fragment fragment) {
            this.frag = fragment;
        }

        public Builder theme(int i) {
            this.theme = i;
            return this;
        }

        public Builder bubbleColor(int i) {
            this.bubbleColor = i;
            return this;
        }

        public Builder bubbleTextColor(int i) {
            this.bubbleTextColor = i;
            return this;
        }

        public Builder handleColor(int i) {
            this.handleColor = i;
            return this;
        }

        public Builder trackColor(int i) {
            this.trackColor = i;
            return this;
        }

        public Builder searchIconColor(Integer num) {
            this.searchIconColor = num;
            return this;
        }

        public Builder hideScrollbar(boolean z) {
            this.hideScrollbar = z;
            return this;
        }

        public Builder showTrack(boolean z) {
            this.showTrack = z;
            return this;
        }

        public Builder setChoiceMode(int i) {
            this.selectionMode = i;
            return this;
        }

        public Builder setLoadingType(int i) {
            this.loadingMode = i;
            return this;
        }

        public Builder setTitleText(String str) {
            this.titleText = str;
            return this;
        }

        public Builder limitToColumn(LimitColumn limitColumn) {
            this.columnLimit = limitColumn;
            return this;
        }

        public Builder setSelectedContacts(String... strArr) {
            this.selectedItems.clear();
            for (String parseLong : strArr) {
                this.selectedItems.add(Long.valueOf(Long.parseLong(parseLong)));
            }
            return this;
        }

        public Builder setSelectedContacts(ArrayList<ContactResult> arrayList) {
            this.selectedItems.clear();
            Iterator<ContactResult> it = arrayList.iterator();
            while (it.hasNext()) {
                this.selectedItems.add(Long.valueOf(Long.parseLong(it.next().getContactID())));
            }
            return this;
        }

        public Builder setNumberOfContactsToBeSelected(int i) {
            noOfContactToBeSelected = i;
            return this;
        }

        public Builder setActivityAnimations(Integer num, Integer num2, Integer num3, Integer num4) {
            this.animationOpenEnter = num;
            this.animationOpenExit = num2;
            this.animationCloseEnter = num3;
            this.animationCloseExit = num4;
            return this;
        }

        public void showPickerForResult(int i) {
            if (this.acc != null) {
                Intent intent = new Intent(this.acc, MultiContactPickerActivity.class);
                intent.putExtra("builder", this);
                this.acc.startActivityForResult(intent, i);
                Integer num = this.animationOpenEnter;
                if (num != null && this.animationOpenExit != null) {
                    this.acc.overridePendingTransition(num.intValue(), this.animationOpenExit.intValue());
                    return;
                }
                return;
            }
            Fragment fragment = this.frag;
            if (fragment == null) {
                throw new RuntimeException("Unable to find a context for intent. Is there a valid activity or fragment passed in the builder?");
            } else if (fragment.getActivity() != null) {
                Intent intent2 = new Intent(this.frag.getActivity(), MultiContactPickerActivity.class);
                intent2.putExtra("builder", this);
                this.frag.startActivityForResult(intent2, i);
                if (this.animationOpenEnter != null && this.animationOpenExit != null) {
                    this.frag.getActivity().overridePendingTransition(this.animationOpenEnter.intValue(), this.animationOpenExit.intValue());
                }
            }
        }
    }

    static ArrayList<ContactResult> buildResult(List<Contact> list) {
        ArrayList<ContactResult> arrayList = new ArrayList<>();
        for (Contact contactResult : list) {
            arrayList.add(new ContactResult(contactResult));
        }
        return arrayList;
    }

    public static ArrayList<ContactResult> obtainResult(Intent intent) {
        return intent.getParcelableArrayListExtra(MultiContactPickerActivity.EXTRA_RESULT_SELECTION);
    }
}
