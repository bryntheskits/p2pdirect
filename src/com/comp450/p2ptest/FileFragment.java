package com.comp450.p2ptest;


import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class FileFragment extends Fragment {

	public interface FileInfo {
		public String getFilePath();
	}

	private FileInfo fileInfoActivity;
	
	public FileFragment() {
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			fileInfoActivity = (FileInfo) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement FileInfo interface");
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		String path = fileInfoActivity.getFilePath();
		ImageView imageView = (ImageView)getView().findViewById(R.id.imageView1);
		String type = FileFragment.getMimeType(path);
		type = type.substring(0,type.indexOf("/"));		
		if(type.equals("image")) {
			imageView.setVisibility(View.VISIBLE);
			Bitmap myBitmap = BitmapFactory.decodeFile(path);
			imageView.setImageBitmap(myBitmap);
			Toast.makeText(getActivity().getApplicationContext(), "Swipe to share", Toast.LENGTH_SHORT).show();
		}

	}
	
	//http://stackoverflow.com/questions/8589645/how-to-detemine-mime-type-of-file-in-android
	public static String getMimeType(String url)
	{
	    String type = null;
	    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
	    if (extension != null) {
	        MimeTypeMap mime = MimeTypeMap.getSingleton();
	        type = mime.getMimeTypeFromExtension(extension);
	    }
	    return type;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_show_file_activiy, container, false);
		
		return rootView;
	}
}