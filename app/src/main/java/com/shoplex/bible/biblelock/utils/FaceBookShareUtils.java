package com.shoplex.bible.biblelock.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by qsk on 2017/4/5.
 */

public class FaceBookShareUtils {

    private Activity mActivity ;
    private ShareDialog shareDialog;
    private CallbackManager callBackManager;
    public static final int SHARE_REQUEST_CODE = 10010;
    private ShareLinkContent.Builder shareLinkContentBuilder;

    public FaceBookShareUtils(Activity activity, CallbackManager callBackManager, FacebookCallback facebookCallback) {
        this.mActivity = activity ;
        this.callBackManager = callBackManager;
        shareDialog = new ShareDialog(mActivity);
        //注册分享状态监听回调接口
        shareDialog.registerCallback(callBackManager, facebookCallback, FaceBookShareUtils.SHARE_REQUEST_CODE);
        shareLinkContentBuilder = new ShareLinkContent.Builder();
    }
}
