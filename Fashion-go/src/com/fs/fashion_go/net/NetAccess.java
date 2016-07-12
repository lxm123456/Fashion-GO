package com.fs.fashion_go.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.fashion_go.R;
import com.nostra13.universalimageloader.utils.L;

/**
 * 网络请求类
 * 
 * @author ping 2014-4-9 下午5:00:11
 */
public class NetAccess {
	private static final String TAG = "NetAccess";

	private static RequestQueue mRequestQueue;

	private static ImageLoader mImageLoader;

	private Context mcontext;

	private Map<String, String> heads;// 提交头

	private Map<String, String> params;// 提交的参数

	private String flag;// 请求标示

	private String cookies;// 提交的cookies

	private String rewcookies;// 返回来后重置成改cookies

//	private int timeout = 25000;// volley超时时间默认25秒
	private int timeout = 30000;// volley超时时间默认25秒

	private ProgressDialog mdialog;// 加载窗口

	private NetAccessListener listener;// 回调方法
	
	private JsonRequest request;//vollery自定义请求类

	private static final int LOADINGIMAGE = R.drawable.bg_loading_image;

	private static final int ERRORIMAGE = R.drawable.bg_error_image;
	

	private static final int IMAGEMAXMEASURE = 550;// 图片最大尺寸750,0则不限制
	
	private static BitmapLruCache mLruCache;// 图片缓存器
	
	private static ImageRequest mImageRequest;// 图片请求(一直修改)

	public NetAccess(Context context) {
		mcontext = context;
	}

	/**
	 * 获取vollery自定义请求的类(可以获取请求各种信息)
	 * @author ping
	 * @create 2014-4-22 下午5:18:43
	 * @param request
	 */
	public void setRequest(JsonRequest request) {
		this.request = request;
	}
	
	/**
	 * 获取返回的数据的请求头
	 * NetAccess.getReHeaders();
	 * @author ping
	 * @create 2014-4-22 下午5:20:51
	 * @return
	 */
	public Map<String, String> getReHeaders() {
		Map<String, String> data = null; 
		if (request!=null) {
			data =  request.getReHeaders();
		}
		return data;
	}

	/**
	 * 回调接口
	 * 
	 * @author ping 2014-4-9 下午10:32:42
	 */

	public interface NetAccessListener {
		public void onAccessComplete(boolean success, String object,
				VolleyError error, String flag);
	}

	/**
	 * @param url
	 *            根据Url删除缓存
	 */
	public void clearCache(String url) {
		if (url == null) {
			mRequestQueue.getCache().clear();
		} else {
			mRequestQueue.getCache().remove(url);
		}
	}

	/**
	 * 初始化
	 * 
	 * @author ping 2014-4-9 下午6:41:52
	 * @param context
	 * @return
	 */
	public static NetAccess request(Context context) {
		// 网络请求类初始化
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		}
		// 图片(缓存)请求类初始化
		// 图片缓存类初始化
				if (mLruCache == null) {
					int cacheSize = 0;
					if (context == null) {
						// LruCache通过构造函数传入缓存值，以KB为单位。
						int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
						// 使用最大可用内存值的1/8作为缓存的大小。
						cacheSize = maxMemory / 8;
					} else {
						// Use 1/8th of the available memory for this memory cache.
						int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
						cacheSize = 1024 * 1024 * memClass / 8;// 0.5>>50331648
					}
					mLruCache = new BitmapLruCache(cacheSize);
				}
				// 图片(缓存)请求类初始化
				if (mImageLoader == null) {
					mImageLoader = new ImageLoader(mRequestQueue, mLruCache);
				}
		return new NetAccess(context);
	}


	/**
	 * 获取缓存
	 * 
	 * @author ping 2014-4-10 下午1:57:11
	 * @param url
	 * @return
	 */
	public static String getCache(String url) {
		String result = null;
		try {
			// 获取缓存
			Entry cachedata = mRequestQueue.getCache().get(url);
			if (cachedata != null) {
				if (cachedata.data != null) {
					result = JsonRequest.decode(cachedata.data);
				}
			}
		} catch (Exception e) {
			// 可能出现
			// mRequestQueue空指针
		}
		return result;
	}

	/**
	 * 获取缓存
	 * 
	 * @author ping
	 * @create 2014-5-24 下午6:04:09
	 * @param url
	 * @param cls
	 * @return
	 */
	public static <T> T getCache(String url, Class<T> cls) {
		T result = null;
		try {
			// 获取缓存
			Entry cachedata = mRequestQueue.getCache().get(url);
			if (cachedata != null) {
				if (cachedata.data != null) {
					Object obj = null;
					if (cls.equals(Bitmap.class)) {
						if (IMAGEMAXMEASURE != 0) {
							BitmapFactory.Options opts = new BitmapFactory.Options();
							opts.inJustDecodeBounds = true;
							BitmapFactory.decodeByteArray(cachedata.data, 0, cachedata.data.length, opts);
							opts.inSampleSize = calculateSampleSize(opts, IMAGEMAXMEASURE, IMAGEMAXMEASURE);
							opts.inJustDecodeBounds = false;
							obj = BitmapFactory.decodeByteArray(cachedata.data, 0, cachedata.data.length, opts);
						} else {
							obj = BitmapFactory.decodeByteArray(cachedata.data, 0, cachedata.data.length);
						}
					} else {
						obj = JsonRequest.decode(cachedata.data);
					}

					result = (T) obj;

				}
			}
		} catch (Exception e) {
			// 可能出现
			// 空指针、 (T) obj;强制失败
		}
		return result;
	}

	/**
	 * 设置请求头
	 * 
	 * @author ping 2014-4-10 上午9:44:42
	 * @param heads
	 * @return
	 */
	public NetAccess setHeaders(Map<String, String> heads) {
		this.heads = heads;
		return this;
	}

	/**
	 * 设置请求内容
	 * 
	 * @author ping 2014-4-10 上午9:44:52
	 * @param params
	 * @return
	 */
	public NetAccess setParams(Map<String, String> params) {
		this.params = params;
		return this;
	}

	/**
	 * 设置请求内容
	 * 
	 * @author ping 2014-4-10 上午9:45:06
	 * @param params
	 * @return
	 */
	public NetAccess setParams2(Map<String, Object> params) {
		Map<String, String> param2 = new HashMap<String, String>();
		if (params != null) {
			for (String key : params.keySet()) {
				param2.put(key, params.get(key) + "");
			}
		}
		this.params = param2;
		return this;
	}

	/**
	 * 设置请求标示
	 * 
	 * @author ping 2014-4-10 上午9:45:16
	 * @param flag
	 * @return
	 */
	public NetAccess setFlag(String flag) {
		this.flag = flag;
		return this;
	}

	/**
	 * 设置服务器返回的cookies
	 * 
	 * @author ping 2014-4-10 上午9:45:35
	 * @param rewcookies
	 * @return
	 */
	public NetAccess setRewcookies(String rewcookies) {
		this.rewcookies = rewcookies;
		return this;
	}

	/**
	 * 设置请求cookies
	 * 
	 * @author ping 2014-4-10 上午9:46:02
	 * @param cookies
	 * @return
	 */
	public NetAccess setCookies(String cookies) {
		this.cookies = cookies;
		return this;
	}

	/**
	 * 设置显示加载中Dialog窗口
	 * 
	 * @author ping 2014-4-10 上午9:46:16
	 * @param isShow
	 * @return
	 */
	public NetAccess showDialog(boolean canCancel) {
		if (!(mdialog == null || !mdialog.isShowing())) {
			mdialog.dismiss();
		}
		mdialog = ProgressDialog.show(mcontext, null, "加载中", true, true,null);
		mdialog.setCancelable(canCancel);
		return this;
	}

	/**
	 * 设置超时时间，默认20秒
	 * 
	 * @author ping 2014-4-10 上午9:47:15
	 * @param timeout
	 * @return
	 */
	public NetAccess setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}

	/**
	 * 开始get请求
	 * 
	 * @author ping 2014-4-10 上午9:49:01
	 * @param url
	 * @param listener
	 */
	synchronized public void byGet(String url, NetAccessListener listener) {
		url += getParamStr(params);

		L.i(TAG, "gurl-->" + url);
		startrequest(url, Method.GET, false, listener);
	}

	/**
	 * 获取缓存后，在进行get请求
	 * 
	 * @author ping 2014-4-10 上午9:49:10
	 * @param url
	 * @param listener
	 */
	synchronized public void byCacheGet(String url, NetAccessListener listener) {
		url += getParamStr(params);
		String cache = getCache(url);
		if (cache != null) {
			L.i(TAG, "cache-->" + cache);
			if (listener != null) {
				listener.onAccessComplete(true, cache, null, flag);
			}
		}

		L.i(TAG, "gurl-->" + url);
		startrequest(url, Method.GET, true, listener);
	}

	/**
	 * 开始post 请求
	 * 
	 * @author ping 2014-4-10 上午9:49:10
	 * @param url
	 * @param listener
	 */
	synchronized public void byPost(String url, NetAccessListener listener) {
		L.i(TAG, "purl-->" + url + getParamStr(params));
//		System.out.println(url + getParamStr(params));
		startrequest(url, Method.POST, false, listener);
	}

	/**
	 * 获取缓存后，在进行post请求
	 * 
	 * @author ping 2014-4-10 上午9:49:10
	 * @param url
	 * @param listener
	 */
	synchronized public void byCachePost(String url, NetAccessListener listener) {
		String cache = getCache(url);
		if (cache != null) {
			L.i(TAG, "cache-->" + cache);
			if (listener != null) {
				listener.onAccessComplete(true, cache, null, flag);
			}
		}

		L.i(TAG, "purl-->" + url + getParamStr(params));
		startrequest(url, Method.POST, true, listener);
	}

	/**
	 * 开始请求
	 * 
	 * @author ping 2014-4-11 下午12:52:26
	 * @param url
	 *            请求链接(post不包含参数、get包含参数)
	 * @param savecache
	 *            是否缓存
	 */
	synchronized private void startrequest(String url, int method,
			boolean savecache, NetAccessListener listener) {
		oms.add(new OM(url, method, savecache, listener));
		if (mdialog != null) {
			mdialog.show();
		}
		
		
		this.listener = listener;

		ResponseListener relistener = new ResponseListener(this);
		request = new JsonRequest(method, url, relistener,relistener);
		request.setHeaders(heads);
		if (method != Method.GET) {
			request.setParams(params);
		}
		request.setCookies(cookies);
		request.setRewcookies(rewcookies);
		request.setTimeout(timeout);
		request.setShouldCache(savecache);
		request.setTag(TextUtils.isEmpty(flag) ? TAG : flag);
		mRequestQueue.add(request);
		mRequestQueue.start();
//		request.getReHeaders()
	}
	
	class OM{
		public OM(String url,int method,boolean savecache,NetAccessListener listener){
			url0 = url;
			method0 = method;
			savecache0 = savecache;
			listener0 = listener;
		}
		private String url0;
		private int method0;
		private boolean savecache0;
		private NetAccessListener listener0;
	}
	private List<OM> oms = new ArrayList<NetAccess.OM>();
	private void requestOM(){
		for (OM om:oms) {
			startrequest(om.url0, om.method0, om.savecache0, om.listener0);
		}
		oms.clear();
	}

	/**
	 * 请求回调
	 * 
	 * @author ping 2014-4-10 上午9:49:18
	 */
	private static class ResponseListener implements Response.Listener<String>,
			Response.ErrorListener {
		private NetAccess net;
		
		public ResponseListener(NetAccess netaccess) {
			this.net = netaccess;
		}

		@Override
		public void onResponse(String response) {
			L.i(TAG, "callback-->" + response);
			
			if (!(net.mdialog == null || !net.mdialog.isShowing())) {
				net.mdialog.dismiss();	
			}

			if (net.listener != null) {
				net.listener.onAccessComplete(true, response, null, net.flag);
			}
		}

		@Override
		public void onErrorResponse(VolleyError error) {
			L.i(TAG, "callback-->error:" + error.getMessage());

			if (!(net.mdialog == null || !net.mdialog.isShowing())) {
				net.mdialog.dismiss();
			}

			//TODO 做个特殊处理，在5.0上会出现java.io.InterruptedIOException的错误，这时重新请求一次
			if (error.getMessage()!= null && error.getMessage().contains("java.io.InterruptedIOException")) {
				net.requestOM();
				return;
			}
			
			if (net.listener != null) {
				net.listener.onAccessComplete(false, null, error, net.flag);
			}
		}
	}
	
	/**
	 * 图片异步加载
	 * 
	 * @author ping 2014-4-10 上午9:50:02
	 * @param imageview
	 * @param url
	 */
	public static void image(ImageView imageview, String url) {
		image(imageview, url, LOADINGIMAGE, ERRORIMAGE, IMAGEMAXMEASURE);
	}

	public static void image(final ImageView imageview, String url, int loadingimg, int errorimg) {
		image(imageview, url, loadingimg, errorimg, IMAGEMAXMEASURE);
	}

	/**
	 * 图片异步加载
	 * 
	 * @author ping
	 * @create 2014-4-17 上午10:33:29
	 * @param imageview
	 * @param url
	 * @param loadingimg
	 *            加载中显示的图片
	 * @param errorimg
	 *            加载错误时显示的图片
	 */
	public static void image(final ImageView imageview, final String url, final int loadingimg, final int errorimg, final int maxmeasure) {
		if (imageview == null) {
			return;
		}

		if (url == null) {
			imageview.setImageResource(errorimg);
			return;
		}

		request(null);

		imageview.setTag(url);
		final String urlkey = getCacheKey(url, maxmeasure, maxmeasure);
		Bitmap bm = mLruCache.getBitmap(urlkey);
		// Bitmap bm = mLruCache.getBitmap(getCacheKey(url, maxmeasure, maxmeasure));
		if (bm == null) {
			// bm = getCache(url, Bitmap.class);
			// if (bm != null) {
			// mLruCache.putBitmap(getCacheKey(url, maxmeasure, maxmeasure),
			// bm);
			// }
		}

		if (bm == null) {
			imageview.setImageResource(loadingimg);
			// mImageLoader.get(url, ImageLoader.getImageListener(imageview,
			// loadingimg, errorimg), maxmeasure, maxmeasure);
			mImageLoader.get(url, new ImageListener() {

				public void onErrorResponse(VolleyError error) {
					if (errorimg != 0) {
						Bitmap cache = mLruCache.get(urlkey);
						if (cache == null) {
							cache = getCache(url, Bitmap.class);
							if (cache != null) {
								mLruCache.putBitmap(getCacheKey(url, maxmeasure, maxmeasure), cache);
							}
						}
						if (cache == null) {
							imageview.setImageResource(errorimg);
						} else {
							imageview.setImageBitmap(cache);

						}
					}
				}

				public void onResponse(ImageContainer response, boolean isImmediate) {
					Bitmap bitmap = response.getBitmap();
					if (bitmap != null) {
						if (imageview.getTag().toString().equals(url)) {
							imageview.setImageBitmap(bitmap);
							mLruCache.putBitmap(urlkey, bitmap);
						}
					} else if (loadingimg != 0) {
						imageview.setImageResource(R.drawable.bg_loading_image);
					}
				}
			}, maxmeasure, maxmeasure);
		} else {
			final int bmsize = bm.getRowBytes() * bm.getHeight();
			imageview.setImageBitmap(bm);
			mImageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
				@Override
				public void onResponse(Bitmap bitmap) {
					if (imageview.getTag().toString().equals(url)) {
						imageview.setImageBitmap(bitmap);
						if (bmsize != bitmap.getRowBytes() * bitmap.getHeight()) {
							// 图片改变了，更新图片缓存
							mLruCache.putBitmap(getCacheKey(url, maxmeasure, maxmeasure), bitmap);
						}
					} else if (!bitmap.isRecycled()) {
						bitmap.recycle();
					}
				}
			}, maxmeasure, maxmeasure, Config.ARGB_8888, new ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError arg0) {
					Bitmap cache = mLruCache.get(urlkey);
					if (cache == null) {
						cache = getCache(url, Bitmap.class);
						if (cache != null) {
							mLruCache.putBitmap(getCacheKey(url, maxmeasure, maxmeasure), cache);
						}
					}
					if (cache == null) {
						imageview.setImageResource(errorimg);
					} else {
						imageview.setImageBitmap(cache);

					}
					// imageview.setImageResource(errorimg);
				}
			});
			if (mRequestQueue == null) {
				mRequestQueue = Volley.newRequestQueue(imageview.getContext());
			}
			mRequestQueue.add(mImageRequest);
		}
	}
	
	private static String getCacheKey(String url, int maxWidth, int maxHeight) {
		return (new StringBuilder(url.length() + 12)).append("#W").append(maxWidth).append("#H").append(maxHeight).append(url).toString();
	}
	private static int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}


	/**
	 * 获取参数(得到 ?a=12&b=123)
	 * 
	 * @author ping 2014-4-10 上午9:27:01
	 * @param params
	 * @return
	 */
	private String getParamStr(Map<String, String> params) {
		StringBuffer bf = new StringBuffer("?");
		if (params != null) {
			for (String key : params.keySet()) {
				bf.append(key + "=" + params.get(key) + "&");
			}
		}
		String str = bf.toString();
		return str.substring(0, str.length() - 1);
	}

}
