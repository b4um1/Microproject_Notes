package at.fh.ooe.mc.android;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import at.fh.ooe.mc.androi.R;

class MyArrayAdapter extends ArrayAdapter<Note> {
	private final String LOG_TAG = "ArrayAdapter";
	List<Note> mList;
	Activity mContext;

	public MyArrayAdapter(Activity _activity, int _textViewResourceId,
			List<Note> _objects) {
		super(_activity.getApplicationContext(), _textViewResourceId, _objects);
		mList = _objects;
		mContext = _activity;
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent) {
		if (_convertView == null) {
			Context c = getContext();
			LayoutInflater inflater = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			_convertView = inflater.inflate(R.layout.mainlist_layout, null);
		}
		final Note element = mList.get(_position);
		if (element != null) {
			final int pos = _position;
			TextView v = (TextView) _convertView
					.findViewById(R.id.textView_title);
			v.setText(element.getTitle());

			ImageView image = (ImageView) _convertView
					.findViewById(R.id.imageView_delete);
			image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Log.i(LOG_TAG, "try to delete "+element.getTitle());
					mList.remove(pos);
					mContext.recreate();
				}

			});
		}
		return _convertView;
	}
}
