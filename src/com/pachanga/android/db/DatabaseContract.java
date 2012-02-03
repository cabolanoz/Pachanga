package com.pachanga.android.db;

import android.net.Uri;

public class DatabaseContract {

	private final static String LOG = DatabaseContract.class.getName();
	public static final String REFRESH = "refresh";

	interface PlacesColumns {
		String PLACE_ID = "PlaceId";
		String NAME = "Name";
		String DESCRIPTION = "Description";
		String PICTURE = "Picture";
		String RATE = "Rate";
		String TYPE = "Type";
		String COORD_X = "CoordX";
		String COORD_Y = "CoordY";
		String ADDRESS = "Address";
		String FLAG = "Flag";
	}

	public static final String CONTENT_AUTHORITY = "com.pachanga.android";
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://"
			+ CONTENT_AUTHORITY);

	/**
	 * Paths for the content uri can be build using this on each definition of
	 * class content://com.pachanga.android/places
	 */
	private static final String PATH_PLACES = "places";
	

	public static class Places implements PlacesColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
				.appendPath(PATH_PLACES).build();

		public static String getId(Uri uri) {

			if (uri.getPathSegments().size() >= 2)
				return uri.getPathSegments().get(1);

			return null;
		}

		/**
		 * Specify the content for the record and for this entity
		 * */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.pachanga.places";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.pachanga.places";

		/**
		 * Default sorting for this entity
		 * */
		public static final String DEFAULT_SORT = PlacesColumns.PLACE_ID
				+ " ASC";

		public static Uri buildUri(String placesId) {
			return CONTENT_URI.buildUpon().appendPath(placesId).build();
		}

	}
}
