package com.jch.racWiFi.selectMultipleContacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jch.racWiFi.selectMultipleContacts.MultiContactPicker;
import com.jch.racWiFi.selectMultipleContacts.MultiContactPickerAdapter;
import com.jch.racWiFi.selectMultipleContacts.RxContacts.C2319RxContacts;
import com.jch.racWiFi.selectMultipleContacts.RxContacts.Contact;
import com.jch_hitachi.aircloudglobal.R;
import com.l4digital.fastscroll.FastScrollRecyclerView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p012io.reactivex.Observer;
import p012io.reactivex.android.schedulers.AndroidSchedulers;
import p012io.reactivex.disposables.CompositeDisposable;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.functions.Consumer;
import p012io.reactivex.functions.Predicate;
import p012io.reactivex.schedulers.Schedulers;

public class MultiContactPickerActivity extends AppCompatActivity implements MaterialSearchView.OnQueryTextListener {
    public static final String EXTRA_RESULT_SELECTION = "extra_result_selection";
    /* access modifiers changed from: private */
    public MultiContactPickerAdapter adapter;
    /* access modifiers changed from: private */
    public boolean allSelected = false;
    private Integer animationCloseEnter;
    private Integer animationCloseExit;
    /* access modifiers changed from: private */
    public MultiContactPicker.Builder builder;
    /* access modifiers changed from: private */
    public List<Contact> contactList = new ArrayList();
    private LinearLayout controlPanel;
    /* access modifiers changed from: private */
    public CompositeDisposable disposables;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private FastScrollRecyclerView recyclerView;
    private MenuItem searchMenuItem;
    private MaterialSearchView searchView;
    private Toolbar toolbar;
    /* access modifiers changed from: private */
    public TextView tvNoContacts;
    /* access modifiers changed from: private */
    public TextView tvSelectAll;
    private TextView tvSelectBtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.builder = (MultiContactPicker.Builder) intent.getSerializableExtra("builder");
            this.disposables = new CompositeDisposable();
            setTheme(this.builder.theme);
            setContentView((int) R.layout.activity_multi_contact_picker);
            this.toolbar = (Toolbar) findViewById(R.id.toolbar);
            this.searchView = (MaterialSearchView) findViewById(R.id.search_view);
            this.controlPanel = (LinearLayout) findViewById(R.id.controlPanel);
            this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
            TextView textView = (TextView) findViewById(R.id.tvSelectAll);
            this.tvSelectAll = textView;
            textView.setVisibility(8);
            this.tvSelectBtn = (TextView) findViewById(R.id.tvSelect);
            this.tvNoContacts = (TextView) findViewById(R.id.tvNoContacts);
            this.recyclerView = (FastScrollRecyclerView) findViewById(R.id.recyclerView);
            initialiseUI(this.builder);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            this.adapter = new MultiContactPickerAdapter(this.contactList, new MultiContactPickerAdapter.ContactSelectListener() {
                public void onContactSelected(Contact contact, int i) {
                    MultiContactPickerActivity.this.updateSelectBarContents(i);
                    if (MultiContactPickerActivity.this.builder.selectionMode == 1) {
                        MultiContactPickerActivity.this.finishPicking();
                    }
                }
            }, getApplicationContext());
            loadContacts();
            this.recyclerView.setAdapter(this.adapter);
            this.tvSelectBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MultiContactPickerActivity.this.finishPicking();
                }
            });
            this.tvSelectAll.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MultiContactPickerActivity multiContactPickerActivity = MultiContactPickerActivity.this;
                    multiContactPickerActivity.allSelected = !multiContactPickerActivity.allSelected;
                    if (MultiContactPickerActivity.this.adapter != null) {
                        MultiContactPickerActivity.this.adapter.setAllSelected(MultiContactPickerActivity.this.allSelected);
                    }
                    if (MultiContactPickerActivity.this.allSelected) {
                        MultiContactPickerActivity.this.tvSelectAll.setText(MultiContactPickerActivity.this.getString(R.string.android_myAcc_lbl_unselectAll));
                    } else {
                        MultiContactPickerActivity.this.tvSelectAll.setText(MultiContactPickerActivity.this.getString(R.string.android_myAcc_lbl_selectAll));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void finishPicking() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.adapter.getSelectedContacts());
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT_SELECTION, MultiContactPicker.buildResult(arrayList));
        setResult(-1, intent);
        finish();
        overrideAnimation();
    }

    private void overrideAnimation() {
        Integer num = this.animationCloseEnter;
        if (num != null && this.animationCloseExit != null) {
            overridePendingTransition(num.intValue(), this.animationCloseExit.intValue());
        }
    }

    /* access modifiers changed from: private */
    public void updateSelectBarContents(int i) {
        this.tvSelectBtn.setEnabled(i > 0);
        if (i > 0) {
            TextView textView = this.tvSelectBtn;
            textView.setText(getString(R.string.createAccount_btn_finish) + " (" + i + ")");
            return;
        }
        this.tvSelectBtn.setText(getString(R.string.createAccount_btn_finish));
    }

    private void initialiseUI(MultiContactPicker.Builder builder2) {
        setSupportActionBar(this.toolbar);
        this.searchView.setOnQueryTextListener(this);
        this.animationCloseEnter = builder2.animationCloseEnter;
        this.animationCloseExit = builder2.animationCloseExit;
        if (builder2.bubbleColor != 0) {
            this.recyclerView.setBubbleColor(builder2.bubbleColor);
        }
        if (builder2.handleColor != 0) {
            this.recyclerView.setHandleColor(builder2.handleColor);
        }
        if (builder2.bubbleTextColor != 0) {
            this.recyclerView.setBubbleTextColor(builder2.bubbleTextColor);
        }
        if (builder2.trackColor != 0) {
            this.recyclerView.setTrackColor(builder2.trackColor);
        }
        this.recyclerView.setHideScrollbar(builder2.hideScrollbar);
        this.recyclerView.setTrackVisible(builder2.showTrack);
        if (builder2.selectionMode == 1) {
            this.controlPanel.setVisibility(8);
        } else {
            this.controlPanel.setVisibility(0);
        }
        if (builder2.selectionMode == 1 && builder2.selectedItems.size() > 0) {
            throw new RuntimeException("You must be using MultiContactPicker.CHOICE_MODE_MULTIPLE in order to use setSelectedContacts()");
        } else if (builder2.titleText != null) {
            setTitle(builder2.titleText);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            setResult(0);
            finish();
            overrideAnimation();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void loadContacts() {
        this.tvSelectAll.setEnabled(false);
        this.progressBar.setVisibility(0);
        C2319RxContacts.fetch(this.builder.columnLimit, (Context) this).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                MultiContactPickerActivity.this.disposables.add(disposable);
            }
        }).filter(new Predicate<Contact>() {
            public boolean test(Contact contact) throws Exception {
                return contact.getDisplayName() != null;
            }
        }).subscribe(new Observer<Contact>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(Contact contact) {
                MultiContactPickerActivity.this.contactList.add(contact);
                if (MultiContactPickerActivity.this.builder.selectedItems.contains(Long.valueOf(contact.getId()))) {
                    MultiContactPickerActivity.this.adapter.setContactSelected(contact.getId());
                }
                Collections.sort(MultiContactPickerActivity.this.contactList, new Comparator<Contact>() {
                    public int compare(Contact contact, Contact contact2) {
                        return contact.getDisplayName().compareToIgnoreCase(contact2.getDisplayName());
                    }
                });
                if (MultiContactPickerActivity.this.builder.loadingMode == 0 && MultiContactPickerActivity.this.adapter != null) {
                    MultiContactPickerActivity.this.adapter.notifyDataSetChanged();
                }
            }

            public void onError(Throwable th) {
                MultiContactPickerActivity.this.progressBar.setVisibility(8);
                th.printStackTrace();
            }

            public void onComplete() {
                if (MultiContactPickerActivity.this.contactList.size() == 0) {
                    MultiContactPickerActivity.this.tvNoContacts.setVisibility(0);
                }
                if (MultiContactPickerActivity.this.adapter != null && MultiContactPickerActivity.this.builder.loadingMode == 1) {
                    MultiContactPickerActivity.this.adapter.notifyDataSetChanged();
                }
                if (MultiContactPickerActivity.this.adapter != null) {
                    MultiContactPickerActivity multiContactPickerActivity = MultiContactPickerActivity.this;
                    multiContactPickerActivity.updateSelectBarContents(multiContactPickerActivity.adapter.getSelectedContactsCount());
                }
                MultiContactPickerActivity.this.progressBar.setVisibility(8);
                MultiContactPickerActivity.this.tvSelectAll.setEnabled(true);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mcp_menu_main, menu);
        MenuItem findItem = menu.findItem(R.id.mcp_action_search);
        this.searchMenuItem = findItem;
        setSearchIconColor(findItem, this.builder.searchIconColor);
        this.searchView.setMenuItem(this.searchMenuItem);
        return true;
    }

    private void setSearchIconColor(MenuItem menuItem, Integer num) {
        Drawable icon;
        if (num != null && (icon = menuItem.getIcon()) != null) {
            Drawable wrap = DrawableCompat.wrap(icon);
            DrawableCompat.setTint(wrap.mutate(), num.intValue());
            menuItem.setIcon(wrap);
        }
    }

    public boolean onQueryTextSubmit(String str) {
        MultiContactPickerAdapter multiContactPickerAdapter = this.adapter;
        if (multiContactPickerAdapter == null) {
            return false;
        }
        multiContactPickerAdapter.filterOnText(str);
        return false;
    }

    public boolean onQueryTextChange(String str) {
        MultiContactPickerAdapter multiContactPickerAdapter = this.adapter;
        if (multiContactPickerAdapter == null) {
            return false;
        }
        multiContactPickerAdapter.filterOnText(str);
        return false;
    }

    public void onBackPressed() {
        if (this.searchView.isSearchOpen()) {
            this.searchView.closeSearch();
            return;
        }
        super.onBackPressed();
        overrideAnimation();
    }

    public void onDestroy() {
        this.disposables.clear();
        super.onDestroy();
    }
}
