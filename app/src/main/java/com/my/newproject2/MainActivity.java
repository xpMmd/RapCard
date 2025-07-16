package com.my.newproject2;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.support.v4.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.billingclient.*;
import com.anjlab.android.iab.v3.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.color.MaterialColors;
import com.my.newproject2.databinding.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private MainBinding binding;
	private String Shenase = "";
	BillingProcessor bp;
	private String LOG_TAG = "";
	private boolean ready = false;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = MainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		setSupportActionBar(binding.Toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		binding.Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		
		binding.button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!ready) {
					SketchwareUtil.showMessage(getApplicationContext(), "x");
					return;
				}
				bp.purchase(MainActivity.this, Shenase);
			}
		});
	}
	
	private void initializeLogic() {
		_GetResponse();
		Shenase = "Vip_a";
		if(!BillingProcessor.isIabServiceAvailable(this)) {
			
			 
			
		}
		bp = new BillingProcessor(this, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNygx8YQlcXx0YJzTlxYzySXMwBOrkarJXdYyyiqNcuWXu1SdUOoihh0ftu7vuHQSDnQcLxz0Sa6wA8k07BY9cxIqwtR7VQq+Yyzk+P4LJwUUd9SomjhUdUi5824l3yuwLXcN23QICB/hOGhZ7sGYNb6m0V7Yi1Zt1m7OyO1SI3wIDAQAB", "fb7b22cb-ff66-4809-8160-543d60d2c2c8", new BillingProcessor.IBillingHandler() {
			
			@Override
			public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
				
				SketchwareUtil.showMessage(getApplicationContext(), "خرید");
				
			}
			@Override
			public void onBillingError(int errorCode, @Nullable Throwable error) {
				
				final String MessageError = Integer.toString(errorCode);
				
				SketchwareUtil.showMessage(getApplicationContext(), MessageError);
				
			}
			@Override
			public void onBillingInitialized() {
				
				SketchwareUtil.showMessage(getApplicationContext(), " ");
				ready = true;
				updateInfoBilling();
				
			}
			@Override
			public void onPurchaseHistoryRestored() {
				for(String sku : bp.listOwnedProducts())
				Log.d(LOG_TAG, "Owned Managed Product: " + sku);
				for(String sku : bp.listOwnedSubscriptions())
				Log.d(LOG_TAG, "Owned Subscription: " + sku);
				updateInfoBilling();
			}
			
		});
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		if (!bp.handleActivityResult(_requestCode, _resultCode, _data))super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (bp != null)
		bp.release();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		updateInfoBilling();
	}
	public void _GetResponse() {
	}
	private void updateInfoBilling() {
		final String buyProduct = Shenase+" is "+(bp.isPurchased(Shenase) ? "" : " not")+" purchased";
		final String subcribeProduct = ""+" is "+(bp.isSubscribed("") ? "" : " not")+" subscribed";
		 
	}
	{
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}