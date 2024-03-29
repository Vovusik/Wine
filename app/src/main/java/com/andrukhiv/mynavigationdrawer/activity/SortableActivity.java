package com.andrukhiv.mynavigationdrawer.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.andrukhiv.mynavigationdrawer.R;
import com.andrukhiv.mynavigationdrawer.SortableGrapesTableView;
import com.andrukhiv.mynavigationdrawer.adapters.SortableTableDataAdapter;
import com.andrukhiv.mynavigationdrawer.database.DbAdapter;
import com.andrukhiv.mynavigationdrawer.models.SpecificationsModel;
import java.util.ArrayList;
import de.codecrafters.tableview.SortState;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.providers.SortStateViewProvider;


public class SortableActivity extends AppCompatActivity {

    DbAdapter mDbHelper;
    ArrayList<SpecificationsModel> sortable;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortable);

        final SortableGrapesTableView grapesTableView = findViewById(R.id.tableView);
        if (grapesTableView != null) {
            final SortableTableDataAdapter grapesTableDataAdapter =
                    new SortableTableDataAdapter(this, DbAdapter.getAllGrapes(), grapesTableView);
            grapesTableView.addDataClickListener(new GrapesClickListener());
            grapesTableView.setDataAdapter(grapesTableDataAdapter);
            grapesTableView.setHeaderElevation(10);

            grapesTableView.setHeaderBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        assert grapesTableView != null;
        grapesTableView.setHeaderSortStateViewProvider(new MySortStateViewProvider());
        grapesTableView.setSaveEnabled( true );

        mDbHelper = DbAdapter.getInstance(getApplicationContext());
        sortable = DbAdapter.getAllGrapes();
    }


    private static class MySortStateViewProvider implements SortStateViewProvider {

        private static final int NO_IMAGE_RES = -1;

        @Override
        public int getSortStateViewResource(SortState state) {
            switch (state) {
                case SORTABLE:
                    return R.drawable.sortable_ic_arrow_defoult;
                case SORTED_ASC:
                    return R.drawable.sortable_ic_arrow_up;
                case SORTED_DESC:
                    return R.drawable.sortable_ic_arrow_down;
                default:
                    return NO_IMAGE_RES;
            }
        }
    }


    private class GrapesClickListener implements TableDataClickListener<SpecificationsModel> {

        @Override
        public void onDataClicked(final int rowIndex, final SpecificationsModel clickedData) {

            Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_GRAPES_ID, sortable.get(clickedData.getId()));
            startActivity(intent);
        }
    }
}