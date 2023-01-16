package com.jch.racWiFi.selectMultipleContacts;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.selectMultipleContacts.MultiContactPicker;
import com.jch.racWiFi.selectMultipleContacts.RxContacts.Contact;
import com.jch.racWiFi.selectMultipleContacts.Views.RoundLetterView;
import com.jch_hitachi.aircloudglobal.R;
import com.l4digital.fastscroll.FastScroller;
import java.util.ArrayList;
import java.util.List;

class MultiContactPickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FastScroller.SectionIndexer, Filterable {
    /* access modifiers changed from: private */
    public List<Contact> contactItemList;
    /* access modifiers changed from: private */
    public List<Contact> contactItemListOriginal;
    Context context;
    /* access modifiers changed from: private */
    public String currentFilterQuery;
    /* access modifiers changed from: private */
    public ContactSelectListener listener;

    interface ContactSelectListener {
        void onContactSelected(Contact contact, int i);
    }

    MultiContactPickerAdapter(List<Contact> list, ContactSelectListener contactSelectListener, Context context2) {
        this.contactItemList = list;
        this.contactItemListOriginal = list;
        this.listener = contactSelectListener;
        this.context = context2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ContactViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_contact_pick_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof ContactViewHolder) {
            ContactViewHolder contactViewHolder = (ContactViewHolder) viewHolder;
            final Contact item = getItem(i);
            contactViewHolder.tvContactName.setText(item.getDisplayName());
            contactViewHolder.vRoundLetterView.setTitleText(String.valueOf(item.getDisplayName().charAt(0)));
            contactViewHolder.vRoundLetterView.setBackgroundColor(item.getBackgroundColor());
            if (item.getPhoneNumbers().size() > 0) {
                String replaceAll = item.getPhoneNumbers().get(0).getNumber().replaceAll("\\s+", "");
                if (!replaceAll.equals(item.getDisplayName().replaceAll("\\s+", ""))) {
                    contactViewHolder.tvNumber.setVisibility(0);
                    contactViewHolder.tvNumber.setText(replaceAll);
                } else {
                    contactViewHolder.tvNumber.setVisibility(8);
                }
            } else if (item.getEmails().size() > 0) {
                String replaceAll2 = item.getEmails().get(0).replaceAll("\\s+", "");
                if (!replaceAll2.equals(item.getDisplayName().replaceAll("\\s+", ""))) {
                    contactViewHolder.tvNumber.setVisibility(0);
                    contactViewHolder.tvNumber.setText(replaceAll2);
                } else {
                    contactViewHolder.tvNumber.setVisibility(8);
                }
            } else {
                contactViewHolder.tvNumber.setVisibility(8);
            }
            highlightTerm(contactViewHolder.tvContactName, this.currentFilterQuery, contactViewHolder.tvContactName.getText().toString());
            if (item.isSelected()) {
                contactViewHolder.ivSelectedState.setVisibility(0);
            } else {
                contactViewHolder.ivSelectedState.setVisibility(4);
            }
            contactViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (item.getIsClicked()) {
                        MultiContactPickerAdapter.this.setContactSelected(item.getId());
                        if (MultiContactPickerAdapter.this.listener != null) {
                            MultiContactPickerAdapter.this.listener.onContactSelected(MultiContactPickerAdapter.this.getItem(i), MultiContactPickerAdapter.this.getSelectedContactsCount());
                        }
                        item.setIsClicked(false);
                        MultiContactPickerAdapter.this.notifyDataSetChanged();
                    } else if (MultiContactPickerAdapter.this.getSelectedContactsCount() < MultiContactPicker.Builder.noOfContactToBeSelected) {
                        MultiContactPickerAdapter.this.setContactSelected(item.getId());
                        if (MultiContactPickerAdapter.this.listener != null) {
                            MultiContactPickerAdapter.this.listener.onContactSelected(MultiContactPickerAdapter.this.getItem(i), MultiContactPickerAdapter.this.getSelectedContactsCount());
                        }
                        item.setIsClicked(true);
                        MultiContactPickerAdapter.this.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MultiContactPickerAdapter.this.context, "exceeded", 0).show();
                    }
                }
            });
        }
    }

    private void highlightTerm(TextView textView, String str, String str2) {
        if (str == null || str.isEmpty()) {
            textView.setText(str2);
            return;
        }
        int indexOf = str2.toLowerCase().indexOf(str.toLowerCase());
        int length = str.length() + indexOf;
        if (indexOf != -1) {
            SpannableString spannableString = new SpannableString(str2);
            spannableString.setSpan(new TextAppearanceSpan((String) null, 1, -1, new ColorStateList(new int[][]{new int[0]}, new int[]{-16777216}), (ColorStateList) null), indexOf, length, 33);
            textView.setText(spannableString);
            return;
        }
        textView.setText(str2);
    }

    /* access modifiers changed from: protected */
    public void setAllSelected(boolean z) {
        for (Contact next : this.contactItemList) {
            next.setSelected(z);
            ContactSelectListener contactSelectListener = this.listener;
            if (contactSelectListener != null) {
                contactSelectListener.onContactSelected(next, getSelectedContactsCount());
            }
        }
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void setContactSelected(long j) {
        int itemPosition = getItemPosition(this.contactItemList, j);
        this.contactItemList.get(itemPosition).setSelected(!this.contactItemList.get(itemPosition).isSelected());
    }

    private int getItemPosition(List<Contact> list, long j) {
        int i = 0;
        for (Contact id : list) {
            if (id.getId() == j) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getSelectedContactsCount() {
        if (getSelectedContacts() != null) {
            return getSelectedContacts().size();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public List<Contact> getSelectedContacts() {
        ArrayList arrayList = new ArrayList();
        for (Contact next : this.contactItemListOriginal) {
            if (next.isSelected()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void clearSelectedContacts() {
        getSelectedContacts().clear();
        notifyDataSetChanged();
    }

    public int getItemCount() {
        List<Contact> list = this.contactItemList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public Contact getItem(int i) {
        return this.contactItemList.get(i);
    }

    public String getSectionText(int i) {
        try {
            return String.valueOf(this.contactItemList.get(i).getDisplayName().charAt(0));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
            return "";
        }
    }

    private class ContactViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView ivSelectedState;
        /* access modifiers changed from: private */
        public final View mView;
        /* access modifiers changed from: private */
        public TextView tvContactName;
        /* access modifiers changed from: private */
        public TextView tvNumber;
        /* access modifiers changed from: private */
        public RoundLetterView vRoundLetterView;

        ContactViewHolder(View view) {
            super(view);
            this.mView = view;
            this.vRoundLetterView = (RoundLetterView) view.findViewById(R.id.vRoundLetterView);
            this.tvContactName = (TextView) view.findViewById(R.id.tvContactName);
            this.tvNumber = (TextView) view.findViewById(R.id.tvNumber);
            this.ivSelectedState = (ImageView) view.findViewById(R.id.ivSelectedState);
        }
    }

    public void filterOnText(String str) {
        this.currentFilterQuery = str;
        getFilter().filter(this.currentFilterQuery);
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                MultiContactPickerAdapter.this.contactItemList = (List) filterResults.values;
                MultiContactPickerAdapter.this.notifyDataSetChanged();
            }

            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                List list;
                if (charSequence.length() == 0) {
                    list = MultiContactPickerAdapter.this.contactItemListOriginal;
                    MultiContactPickerAdapter.this.currentFilterQuery = null;
                } else {
                    list = MultiContactPickerAdapter.this.getFilteredResults(charSequence.toString().toLowerCase());
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = list;
                return filterResults;
            }
        };
    }

    /* access modifiers changed from: private */
    public List<Contact> getFilteredResults(String str) {
        ArrayList arrayList = new ArrayList();
        for (Contact next : this.contactItemListOriginal) {
            if (next.getDisplayName().toLowerCase().contains(str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
