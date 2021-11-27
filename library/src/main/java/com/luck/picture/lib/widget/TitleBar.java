package com.luck.picture.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.luck.picture.lib.R;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.style.PictureSelectorStyle;
import com.luck.picture.lib.style.TitleBarStyle;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.utils.StyleUtils;

/**
 * @author：luck
 * @date：2021/11/17 10:45 上午
 * @describe：TitleBar
 */
public class TitleBar extends RelativeLayout implements View.OnClickListener {

    protected RelativeLayout rlAlbumBg;
    protected ImageView ivLeftBack;
    protected ImageView ivArrow;
    protected MarqueeTextView tvTitle;
    protected TextView tvCancel;
    protected View viewAlbumClickArea;
    protected PictureSelectionConfig config;

    public TitleBar(Context context) {
        super(context);
        init();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        inflate(getContext(), R.layout.ps_title_bar, this);
        setClickable(true);
        setFocusable(true);
        ivLeftBack = findViewById(R.id.ps_iv_left_back);
        rlAlbumBg = findViewById(R.id.ps_rl_album_bg);
        viewAlbumClickArea = findViewById(R.id.ps_rl_album_click);
        tvTitle = findViewById(R.id.ps_tv_title);
        ivArrow = findViewById(R.id.ps_iv_arrow);
        tvCancel = findViewById(R.id.ps_tv_cancel);
        ivLeftBack.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        rlAlbumBg.setOnClickListener(this);
        viewAlbumClickArea.setOnClickListener(this);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey));
        config = PictureSelectionConfig.getInstance();
    }

    public ImageView getImageArrow() {
        return ivArrow;
    }

    /**
     * Set title
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitleBarStyle() {
        PictureSelectorStyle selectorStyle = PictureSelectionConfig.selectorStyle;
        TitleBarStyle titleBarStyle = selectorStyle.getTitleBarStyle();
        int titleBarHeight = titleBarStyle.getTitleBarHeight();
        if (StyleUtils.checkSizeValidity(titleBarHeight)) {
            getLayoutParams().height = titleBarHeight;
        } else {
            getLayoutParams().height = DensityUtil.dip2px(getContext(), 48);
        }
        int backgroundColor = titleBarStyle.getTitleBackgroundColor();
        if (StyleUtils.checkStyleValidity(backgroundColor)) {
            setBackgroundColor(backgroundColor);
        }
        int backResId = titleBarStyle.getTitleLeftBackResource();
        if (StyleUtils.checkStyleValidity(backResId)) {
            ivLeftBack.setImageResource(backResId);
        }
        String titleDefaultText = titleBarStyle.getTitleDefaultText();
        if (StyleUtils.checkTextValidity(titleDefaultText)) {
            tvTitle.setText(titleDefaultText);
        }
        int titleTextSize = titleBarStyle.getTitleTextSize();
        if (StyleUtils.checkSizeValidity(titleTextSize)) {
            tvTitle.setTextSize(titleTextSize);
        }
        int titleTextColor = titleBarStyle.getTitleTextColor();
        if (StyleUtils.checkStyleValidity(titleTextColor)) {
            tvTitle.setTextColor(titleTextColor);
        }
        int arrowResId = titleBarStyle.getTitleDrawableRightResource();
        if (StyleUtils.checkStyleValidity(arrowResId)) {
            ivArrow.setImageResource(arrowResId);
        }
        int albumBackgroundRes = titleBarStyle.getTitleAlbumBackgroundResource();
        if (StyleUtils.checkStyleValidity(albumBackgroundRes)) {
            rlAlbumBg.setBackgroundResource(albumBackgroundRes);
        }

        if (titleBarStyle.isHideCancelButton()) {
            tvCancel.setVisibility(GONE);
        } else {
            tvCancel.setVisibility(VISIBLE);
            int rightBackgroundRes = titleBarStyle.getTitleCancelBackgroundResource();
            if (StyleUtils.checkStyleValidity(rightBackgroundRes)) {
                tvCancel.setBackgroundResource(rightBackgroundRes);
            }
            String rightNormalText = titleBarStyle.getTitleCancelText();
            if (StyleUtils.checkTextValidity(rightNormalText)) {
                tvCancel.setText(rightNormalText);
            }
            int rightTextColor = titleBarStyle.getTitleCancelTextColor();
            if (StyleUtils.checkStyleValidity(rightTextColor)) {
                tvCancel.setTextColor(rightTextColor);
            }
            int rightTextSize = titleBarStyle.getTitleCancelTextSize();
            if (StyleUtils.checkSizeValidity(rightTextSize)) {
                tvCancel.setTextSize(rightTextSize);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ps_iv_left_back || id == R.id.ps_tv_cancel) {
            if (titleBarListener != null) {
                titleBarListener.onBackPressed();
            }
        } else if (id == R.id.ps_rl_album_bg || id == R.id.ps_rl_album_click) {
            if (titleBarListener != null) {
                titleBarListener.onShowAlbumPopWindow(this);
            }
        }
    }

    protected OnTitleBarListener titleBarListener;

    /**
     * TitleBar的功能事件回调
     *
     * @param listener
     */
    public void setOnTitleBarListener(OnTitleBarListener listener) {
        this.titleBarListener = listener;
    }

    public static class OnTitleBarListener {
        /**
         * 关闭页面
         */
        public void onBackPressed() {

        }

        /**
         * 显示专辑列表
         */
        public void onShowAlbumPopWindow(View anchor) {

        }
    }
}
