package com.pachanga.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pachanga.android.db.DatabaseContract;
import com.pachanga.android.db.DatabaseContract.Places;
import com.pachanga.android.db.DatabaseHelper;

public class PlacesListActivity extends FragmentActivity implements
		LoaderCallbacks<Cursor> {

	private static String LOG = PlacesListActivity.class.getName();
	private PlacesAdapter simpleCursorAdapter;
	private PlacesBroadcastReceiver placesBroadcastReceiver;
	private int refreshCounter = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		placesBroadcastReceiver = new PlacesBroadcastReceiver();

		setContentView(R.layout.places_list);

		getApplication().deleteDatabase(DatabaseHelper.database);
		simpleCursorAdapter = new PlacesAdapter(this,
				R.layout.place_item, null, new String[] {
						DatabaseContract.Places.NAME,
						DatabaseContract.Places.ADDRESS }, new int[] {
						R.id.tvwPlaceName, R.id.tvwPlaceAddress }, 0);

		ListView listView = (ListView) findViewById(R.id.placesList);
		listView.setAdapter(simpleCursorAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Intent intent = new Intent(ActionsConstant.PLACE_DETAIL_ACTION);
				CursorWrapper wrapper=(CursorWrapper)adapterView.getItemAtPosition(position);
				
				intent.putExtra("DetailId", wrapper.getLong(wrapper.getColumnIndex(Places._ID)));
				startActivity(intent);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		IntentFilter filter = new IntentFilter();
		filter.addAction(ActionsConstant.HTTP_ACTION);
		registerReceiver(placesBroadcastReceiver, filter);

		getSupportLoaderManager().restartLoader(0, null, this);
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(placesBroadcastReceiver);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle extras) {
		Uri uri = DatabaseContract.Places.CONTENT_URI;
		
		if (extras != null && extras.getBoolean(DatabaseContract.REFRESH)) {
			uri = uri
					.buildUpon()
					.appendQueryParameter(DatabaseContract.REFRESH,
							Boolean.TRUE.toString()).build();
			Log.d(LOG, uri.toString());
		}

		return new CursorLoader(this, uri, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (cursor.getCount() == 0) {// when cursor is empty
			// need to refresh from remote
			if (refreshCounter == 0) {
				refreshCounter++;

				Bundle extras = new Bundle();
				extras.putBoolean(DatabaseContract.REFRESH, true);

				getSupportLoaderManager().restartLoader(0, extras, this);
			}

		}
		
		simpleCursorAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursor) {
		simpleCursorAdapter.swapCursor(null);
	}

	class PlacesBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d(LOG, "Something happen maybe we should refresh");
			refreshCounter = 0;
			getSupportLoaderManager().restartLoader(0, null,
					PlacesListActivity.this);
		}

	}

}
