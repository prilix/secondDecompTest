package com.miguelcatalan.materialsearchview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miguelcatalan.materialsearchview.utils.AnimationUtil;
import java.lang.reflect.Field;

public class MaterialSearchView extends FrameLayout implements Filter.FilterListener {
    public static final int REQUEST_VOICE = 9999;
    private boolean allowVoiceSearch;
    private boolean ellipsize;
    private ListAdapter mAdapter;
    private int mAnimationDuration;
    /* access modifiers changed from: private */
    public ImageButton mBackBtn;
    private boolean mClearingFocus;
    private Context mContext;
    /* access modifiers changed from: private */
    public ImageButton mEmptyBtn;
    private boolean mIsSearchOpen;
    private MenuItem mMenuItem;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener;
    private OnQueryTextListener mOnQueryChangeListener;
    private SavedState mSavedState;
    private View mSearchLayout;
    /* access modifiers changed from: private */
    public EditText mSearchSrcTextView;
    private RelativeLayout mSearchTopBar;
    /* access modifiers changed from: private */
    public SearchViewListener mSearchViewListener;
    private ListView mSuggestionsListView;
    /* access modifiers changed from: private */
    public View mTintView;
    /* access modifiers changed from: private */
    public CharSequence mUserQuery;
    /* access modifiers changed from: private */
    public ImageButton mVoiceBtn;
    /* access modifiers changed from: private */
    public boolean submit;
    private Drawable suggestionIcon;

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    public interface SearchViewListener {
        void onSearchViewClosed();

        void onSearchViewShown();
    }

    public MaterialSearchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaterialSearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mIsSearchOpen = false;
        this.submit = false;
        this.ellipsize = false;
        this.mOnClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (view == MaterialSearchView.this.mBackBtn) {
                    MaterialSearchView.this.closeSearch();
                } else if (view == MaterialSearchView.this.mVoiceBtn) {
                    MaterialSearchView.this.onVoiceClicked();
                } else if (view == MaterialSearchView.this.mEmptyBtn) {
                    MaterialSearchView.this.mSearchSrcTextView.setText((CharSequence) null);
                } else if (view == MaterialSearchView.this.mSearchSrcTextView) {
                    MaterialSearchView.this.showSuggestions();
                } else if (view == MaterialSearchView.this.mTintView) {
                    MaterialSearchView.this.closeSearch();
                }
            }
        };
        this.mContext = context;
        initiateView();
        initStyle(attributeSet, i);
    }

    private void initStyle(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, C2692R.styleable.MaterialSearchView, i, 0);
        if (obtainStyledAttributes != null) {
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_searchBackground)) {
                setBackground(obtainStyledAttributes.getDrawable(C2692R.styleable.MaterialSearchView_searchBackground));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_android_textColor)) {
                setTextColor(obtainStyledAttributes.getColor(C2692R.styleable.MaterialSearchView_android_textColor, 0));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_android_textColorHint)) {
                setHintTextColor(obtainStyledAttributes.getColor(C2692R.styleable.MaterialSearchView_android_textColorHint, 0));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_android_hint)) {
                setHint(obtainStyledAttributes.getString(C2692R.styleable.MaterialSearchView_android_hint));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_searchVoiceIcon)) {
                setVoiceIcon(obtainStyledAttributes.getDrawable(C2692R.styleable.MaterialSearchView_searchVoiceIcon));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_searchCloseIcon)) {
                setCloseIcon(obtainStyledAttributes.getDrawable(C2692R.styleable.MaterialSearchView_searchCloseIcon));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_searchBackIcon)) {
                setBackIcon(obtainStyledAttributes.getDrawable(C2692R.styleable.MaterialSearchView_searchBackIcon));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_searchSuggestionBackground)) {
                setSuggestionBackground(obtainStyledAttributes.getDrawable(C2692R.styleable.MaterialSearchView_searchSuggestionBackground));
            }
            if (obtainStyledAttributes.hasValue(C2692R.styleable.MaterialSearchView_searchSuggestionIcon)) {
                setSuggestionIcon(obtainStyledAttributes.getDrawable(C2692R.styleable.MaterialSearchView_searchSuggestionIcon));
            }
            obtainStyledAttributes.recycle();
        }
    }

    private void initiateView() {
        LayoutInflater.from(this.mContext).inflate(C2692R.layout.search_view, this, true);
        View findViewById = findViewById(C2692R.C2695id.search_layout);
        this.mSearchLayout = findViewById;
        this.mSearchTopBar = (RelativeLayout) findViewById.findViewById(C2692R.C2695id.search_top_bar);
        this.mSuggestionsListView = (ListView) this.mSearchLayout.findViewById(C2692R.C2695id.suggestion_list);
        this.mSearchSrcTextView = (EditText) this.mSearchLayout.findViewById(C2692R.C2695id.searchTextView);
        this.mBackBtn = (ImageButton) this.mSearchLayout.findViewById(C2692R.C2695id.action_up_btn);
        this.mVoiceBtn = (ImageButton) this.mSearchLayout.findViewById(C2692R.C2695id.action_voice_btn);
        this.mEmptyBtn = (ImageButton) this.mSearchLayout.findViewById(C2692R.C2695id.action_empty_btn);
        this.mTintView = this.mSearchLayout.findViewById(C2692R.C2695id.transparent_view);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mBackBtn.setOnClickListener(this.mOnClickListener);
        this.mVoiceBtn.setOnClickListener(this.mOnClickListener);
        this.mEmptyBtn.setOnClickListener(this.mOnClickListener);
        this.mTintView.setOnClickListener(this.mOnClickListener);
        this.allowVoiceSearch = false;
        showVoice(true);
        initSearchView();
        this.mSuggestionsListView.setVisibility(8);
        setAnimationDuration(AnimationUtil.ANIMATION_DURATION_MEDIUM);
    }

    private void initSearchView() {
        this.mSearchSrcTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                MaterialSearchView.this.onSubmitQuery();
                return true;
            }
        });
        this.mSearchSrcTextView.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                CharSequence unused = MaterialSearchView.this.mUserQuery = charSequence;
                MaterialSearchView.this.startFilter(charSequence);
                MaterialSearchView.this.onTextChanged(charSequence);
            }
        });
        this.mSearchSrcTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    MaterialSearchView materialSearchView = MaterialSearchView.this;
                    materialSearchView.showKeyboard(materialSearchView.mSearchSrcTextView);
                    MaterialSearchView.this.showSuggestions();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void startFilter(CharSequence charSequence) {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null && (listAdapter instanceof Filterable)) {
            ((Filterable) listAdapter).getFilter().filter(charSequence, this);
        }
    }

    /* access modifiers changed from: private */
    public void onVoiceClicked() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        intent.putExtra("android.speech.extra.MAX_RESULTS", 1);
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 9999);
        }
    }

    /* access modifiers changed from: private */
    public void onTextChanged(CharSequence charSequence) {
        Editable text = this.mSearchSrcTextView.getText();
        this.mUserQuery = text;
        if (!TextUtils.isEmpty(text)) {
            this.mEmptyBtn.setVisibility(0);
            showVoice(false);
        } else {
            this.mEmptyBtn.setVisibility(8);
            showVoice(true);
        }
        if (this.mOnQueryChangeListener != null && !TextUtils.equals(charSequence, this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }

    /* access modifiers changed from: private */
    public void onSubmitQuery() {
        Editable text = this.mSearchSrcTextView.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            OnQueryTextListener onQueryTextListener = this.mOnQueryChangeListener;
            if (onQueryTextListener == null || !onQueryTextListener.onQueryTextSubmit(text.toString())) {
                closeSearch();
                this.mSearchSrcTextView.setText((CharSequence) null);
            }
        }
    }

    private boolean isVoiceAvailable() {
        if (!isInEditMode() && getContext().getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() != 0) {
            return false;
        }
        return true;
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard(View view) {
        if (Build.VERSION.SDK_INT <= 10 && view.hasFocus()) {
            view.clearFocus();
        }
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mSearchTopBar.setBackground(drawable);
        } else {
            this.mSearchTopBar.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundColor(int i) {
        this.mSearchTopBar.setBackgroundColor(i);
    }

    public void setTextColor(int i) {
        this.mSearchSrcTextView.setTextColor(i);
    }

    public void setHintTextColor(int i) {
        this.mSearchSrcTextView.setHintTextColor(i);
    }

    public void setHint(CharSequence charSequence) {
        this.mSearchSrcTextView.setHint(charSequence);
    }

    public void setVoiceIcon(Drawable drawable) {
        this.mVoiceBtn.setImageDrawable(drawable);
    }

    public void setCloseIcon(Drawable drawable) {
        this.mEmptyBtn.setImageDrawable(drawable);
    }

    public void setBackIcon(Drawable drawable) {
        this.mBackBtn.setImageDrawable(drawable);
    }

    public void setSuggestionIcon(Drawable drawable) {
        this.suggestionIcon = drawable;
    }

    public void setSuggestionBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mSuggestionsListView.setBackground(drawable);
        } else {
            this.mSuggestionsListView.setBackgroundDrawable(drawable);
        }
    }

    public void setCursorDrawable(int i) {
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            declaredField.set(this.mSearchSrcTextView, Integer.valueOf(i));
        } catch (Exception e) {
            Log.e("MaterialSearchView", e.toString());
        }
    }

    public void setVoiceSearch(boolean z) {
        this.allowVoiceSearch = z;
    }

    public void showSuggestions() {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null && listAdapter.getCount() > 0 && this.mSuggestionsListView.getVisibility() == 8) {
            this.mSuggestionsListView.setVisibility(0);
        }
    }

    public void setSubmitOnClick(boolean z) {
        this.submit = z;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mSuggestionsListView.setOnItemClickListener(onItemClickListener);
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        this.mSuggestionsListView.setAdapter(listAdapter);
        startFilter(this.mSearchSrcTextView.getText());
    }

    public void setSuggestions(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            this.mTintView.setVisibility(8);
            return;
        }
        this.mTintView.setVisibility(0);
        final SearchAdapter searchAdapter = new SearchAdapter(this.mContext, strArr, this.suggestionIcon, this.ellipsize);
        setAdapter(searchAdapter);
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MaterialSearchView.this.setQuery((String) searchAdapter.getItem(i), MaterialSearchView.this.submit);
            }
        });
    }

    public void dismissSuggestions() {
        if (this.mSuggestionsListView.getVisibility() == 0) {
            this.mSuggestionsListView.setVisibility(8);
        }
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            EditText editText = this.mSearchSrcTextView;
            editText.setSelection(editText.length());
            this.mUserQuery = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            onSubmitQuery();
        }
    }

    public void showVoice(boolean z) {
        if (!z || !isVoiceAvailable() || !this.allowVoiceSearch) {
            this.mVoiceBtn.setVisibility(8);
        } else {
            this.mVoiceBtn.setVisibility(0);
        }
    }

    public void setMenuItem(MenuItem menuItem) {
        this.mMenuItem = menuItem;
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                MaterialSearchView.this.showSearch();
                return true;
            }
        });
    }

    public boolean isSearchOpen() {
        return this.mIsSearchOpen;
    }

    public void setAnimationDuration(int i) {
        this.mAnimationDuration = i;
    }

    public void showSearch() {
        showSearch(true);
    }

    public void showSearch(boolean z) {
        if (!isSearchOpen()) {
            this.mSearchSrcTextView.setText((CharSequence) null);
            this.mSearchSrcTextView.requestFocus();
            if (z) {
                setVisibleWithAnimation();
            } else {
                this.mSearchLayout.setVisibility(0);
                SearchViewListener searchViewListener = this.mSearchViewListener;
                if (searchViewListener != null) {
                    searchViewListener.onSearchViewShown();
                }
            }
            this.mIsSearchOpen = true;
        }
    }

    private void setVisibleWithAnimation() {
        C26907 r0 = new AnimationUtil.AnimationListener() {
            public boolean onAnimationCancel(View view) {
                return false;
            }

            public boolean onAnimationStart(View view) {
                return false;
            }

            public boolean onAnimationEnd(View view) {
                if (MaterialSearchView.this.mSearchViewListener == null) {
                    return false;
                }
                MaterialSearchView.this.mSearchViewListener.onSearchViewShown();
                return false;
            }
        };
        if (Build.VERSION.SDK_INT >= 21) {
            this.mSearchLayout.setVisibility(0);
            AnimationUtil.reveal(this.mSearchTopBar, r0);
            return;
        }
        AnimationUtil.fadeInView(this.mSearchLayout, this.mAnimationDuration, r0);
    }

    public void closeSearch() {
        if (isSearchOpen()) {
            this.mSearchSrcTextView.setText((CharSequence) null);
            dismissSuggestions();
            clearFocus();
            this.mSearchLayout.setVisibility(8);
            SearchViewListener searchViewListener = this.mSearchViewListener;
            if (searchViewListener != null) {
                searchViewListener.onSearchViewClosed();
            }
            this.mIsSearchOpen = false;
        }
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnSearchViewListener(SearchViewListener searchViewListener) {
        this.mSearchViewListener = searchViewListener;
    }

    public void setEllipsize(boolean z) {
        this.ellipsize = z;
    }

    public void onFilterComplete(int i) {
        if (i > 0) {
            showSuggestions();
        } else {
            dismissSuggestions();
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        if (!this.mClearingFocus && isFocusable()) {
            return this.mSearchSrcTextView.requestFocus(i, rect);
        }
        return false;
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        hideKeyboard(this);
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mClearingFocus = false;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        this.mSavedState = savedState;
        CharSequence charSequence = this.mUserQuery;
        savedState.query = charSequence != null ? charSequence.toString() : null;
        this.mSavedState.isSearchOpen = this.mIsSearchOpen;
        return this.mSavedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mSavedState = savedState;
        if (savedState.isSearchOpen) {
            showSearch(false);
            setQuery(this.mSavedState.query, false);
        }
        super.onRestoreInstanceState(this.mSavedState.getSuperState());
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean isSearchOpen;
        String query;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.query = parcel.readString();
            this.isSearchOpen = parcel.readInt() != 1 ? false : true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.query);
            parcel.writeInt(this.isSearchOpen ? 1 : 0);
        }
    }
}
