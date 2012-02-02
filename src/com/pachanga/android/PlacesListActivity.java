package com.pachanga.android;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

import com.pachanga.android.db.DatabaseContract;

public class PlacesListActivity extends FragmentActivity implements
		LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places_list);

		adapter = new SimpleCursorAdapter(this, R.layout.place_item, null,
				new String[] { DatabaseContract.Places.NAME,
						DatabaseContract.Places.ADDRESS }, new int[] {
						R.id.tvwPlaceName, R.id.tvwPlaceAddress }, 0);

		ListView listView = (ListView) findViewById(R.id.listPlaces);
		listView.setAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		getSupportLoaderManager().restartLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle extras) {
		return new CursorLoader(this, DatabaseContract.Places.CONTENT_URI, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursor) {
		adapter.swapCursor(null);
	}

}
