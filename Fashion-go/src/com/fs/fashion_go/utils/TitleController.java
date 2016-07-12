package com.fs.fashion_go.utils;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fashion_go.R;

/**
 * 标题控制者
 * @author ping
 * @create 2014-5-23 上午9:13:58
 */
public class TitleController {
	private View mview;

	public TitleController(View view) {
		this.mview = view;
	}

	public TitleController(Activity activity) {
		this.mview = activity.findViewById(R.id.layout_title);
	}

	// public TitleController hide_right_tv() {
	// mview.findViewById(R.id.tv_right).setVisibility(View.GONE);
	// return this;
	// }
	//
	// public TitleController set_right_text(String str) {
	// TextView tv = (TextView) mview.findViewById(R.id.tv_right);
	// tv.setText(str);
	// return this;
	// }
	//
	// public TitleController set_right_text_bg(int resid) {
	// TextView tv = (TextView) mview.findViewById(R.id.tv_right);
	// tv.setBackgroundResource(resid);
	// return this;
	// }
	/**
	 * 
	 * @return
	 */
//	public TitleController hideRImg() {
//		mview.findViewById(R.id.iv_right).setVisibility(View.GONE);
//		return this;
//	}
//
//	public TitleController setRImg(int resid) {
//		ImageView imgv = (ImageView) mview.findViewById(R.id.iv_right);
//		imgv.setImageResource(resid);
//		return this;
//	}

	public TitleController setRText(int resid) {
		TextView rightText = (TextView) mview.findViewById(R.id.tv_right);
		rightText.setText(resid);
		return this;
	}

	public TitleController setRText(String text) {
		TextView rightText = (TextView) mview.findViewById(R.id.tv_right);
		rightText.setText(text);
		return this;
	}

	public TitleController hideRText() {
		mview.findViewById(R.id.tv_right).setVisibility(View.GONE);
		return this;
	}

	public TitleController showRText() {
		mview.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
		return this;
	}

	//
	// public TitleController set_rightBtn_bg(int resid) {
	// Button btn = (Button) mview.findViewById(R.id.btn_right);
	// btn.setBackgroundResource(resid);
	// return this;
	// }
	//
	// public TitleController set_rightBtn_text(String text) {
	// Button btn = (Button) mview.findViewById(R.id.btn_right);
	// btn.setText(text);
	// return this;
	// }
	//
	// public TitleController hide_right_btn() {
	// Button btn = (Button) mview.findViewById(R.id.btn_right);
	// btn.setVisibility(View.GONE);
	// return this;
	// }
	// public TitleController hide_left_tv() {
	// mview.findViewById(R.id.tv_left).setVisibility(View.GONE);
	// return this;
	// }
	//
	// public TitleController set_left_text(String str) {
	// TextView tv = (TextView) mview.findViewById(R.id.tv_left);
	// tv.setText(str);
	// return this;
	// }

	public TitleController hideLImg() {
		mview.findViewById(R.id.img_left).setVisibility(View.GONE);
		return this;
	}

	public TitleController showLImg() {
		mview.findViewById(R.id.img_left).setVisibility(View.VISIBLE);
		return this;
	}

	public TitleController setLImg(int resid) {
		ImageView imgv = (ImageView) mview.findViewById(R.id.img_left);
		imgv.setImageResource(resid);
		return this;
	}

	public TitleController setTitle(String title) {
		TextView tv = (TextView) mview.findViewById(R.id.tv_title);
		tv.setText(title);
		return this;
	}

	public TitleController setTitle(int restitle) {
		TextView tvTitle = (TextView) mview.findViewById(R.id.tv_title);
		tvTitle.setText(restitle);
		return this;
	}

	public TitleController setLClick(final OnClickListener clicklistener) {
		mview.findViewById(R.id.layout_left).setOnClickListener(clicklistener);
		return this;
	}

	public TitleController setRClick(final OnClickListener clicklistener) {
		mview.findViewById(R.id.layout_right).setOnClickListener(clicklistener);
		return this;
	}

}
