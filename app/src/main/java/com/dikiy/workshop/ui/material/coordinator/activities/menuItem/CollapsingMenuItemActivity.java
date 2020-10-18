package com.dikiy.workshop.ui.material.coordinator.activities.menuItem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dikiy.workshop.R;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

public class CollapsingMenuItemActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ConstraintLayout searchbar;
    private MenuItem itemAddPerson;
    private HeaderView toolbarHeaderViewCollapsed;
    private HeaderView toolbarHeaderViewExpanded;

    private boolean isHideToolbarTitle = false;
    private boolean isHideToolbarSearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_menu_item);
        setupUI();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        appBarLayout.addOnOffsetChangedListener(this);
        toolbarHeaderViewCollapsed.bindTo("Group Name", "Created by Developer");
        toolbarHeaderViewExpanded.bindTo("Group Name", "Created by Developer");
    }

    private void setupUI() {
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appbar);
        searchbar = findViewById(R.id.searchbar);
        toolbarHeaderViewCollapsed = findViewById(R.id.toolbar_header_view_collapsed);
        toolbarHeaderViewExpanded = findViewById(R.id.toolbar_header_view_expanded);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarTitle) {
            toolbarHeaderViewCollapsed.setVisibility(View.VISIBLE);
            isHideToolbarTitle = !isHideToolbarTitle;
        } else if (percentage < 1f && !isHideToolbarTitle) {
            toolbarHeaderViewCollapsed.setVisibility(View.GONE);
            isHideToolbarTitle = !isHideToolbarTitle;
        }

        if (percentage == 0f && !isHideToolbarSearch) {
            if (itemAddPerson != null) itemAddPerson.setVisible(false);
            searchbar.setVisibility(View.VISIBLE);
            isHideToolbarSearch = !isHideToolbarSearch;
        } else if (percentage > 0f && isHideToolbarSearch) {
            searchbar.setVisibility(View.GONE);
            if (itemAddPerson != null) itemAddPerson.setVisible(true);
            isHideToolbarSearch = !isHideToolbarSearch;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_collapsing, menu);
        itemAddPerson = menu.findItem(R.id.action_add_person);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_person) return true;
        return super.onOptionsItemSelected(item);
    }
}