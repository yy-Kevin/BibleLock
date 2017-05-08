package com.shoplex.bible.biblelock.utils;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.shoplex.bible.biblelock.R;

import java.util.ArrayList;
import java.util.List;

import static com.shoplex.bible.biblelock.server.ServiceActivity.TAG;

/**
 * Created by qsk on 2017/5/3.
 */

public class AdLoadUtil {

    private static  NativeAd nativeAd;

    public static void showNativeAd(final Activity activity, final View view) {

        nativeAd = new NativeAd(activity, "YOUR_PLACEMENT_ID");
        nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback

                Log.i(TAG,"onError onError " + error.toString());

            }

            @Override
            public void onAdLoaded(Ad ad) {

                Log.i(TAG,"1848266248745233 1848266248745233 ");
//                if (nativeAd ==) {
//                    nativeAd.unregisterView();
//                }

                // Add the Ad view into the ad container.
                LinearLayout nativeAdContainer = (LinearLayout) view.findViewById(R.id.native_ad_container);
                LayoutInflater inflater = LayoutInflater.from(activity);
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.activity_ad, nativeAdContainer, false);
                nativeAdContainer.addView(adView);

                // Create native UI using the ad metadata.
                ImageView nativeAdIcon = (ImageView) adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = (TextView) adView.findViewById(R.id.native_ad_title);
                MediaView nativeAdMedia = (MediaView) adView.findViewById(R.id.native_ad_media);
                TextView nativeAdSocialContext = (TextView) adView.findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = (TextView) adView.findViewById(R.id.native_ad_body);
                Button nativeAdCallToAction = (Button) adView.findViewById(R.id.native_ad_call_to_action);

                // Set the Text.
                nativeAdTitle.setText(nativeAd.getAdTitle());
                nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                nativeAdBody.setText(nativeAd.getAdBody());
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());

                // Download and display the ad icon.
                NativeAd.Image adIcon = nativeAd.getAdIcon();
                NativeAd.downloadAndDisplayImage(adIcon, nativeAdIcon);

                // Download and display the cover image.
                nativeAdMedia.setNativeAd(nativeAd);

                // Add the AdChoices icon
                LinearLayout adChoicesContainer = (LinearLayout) activity.findViewById(R.id.ad_choices_container);
                AdChoicesView adChoicesView = new AdChoicesView(activity, nativeAd, true);
                adChoicesContainer.addView(adChoicesView);

                // Register the Title and CTA button to listen for clicks.
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);
                nativeAd.registerViewForInteraction(nativeAdContainer,clickableViews);

            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.i(TAG,"onAdClicked onAdClicked ");
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.i(TAG,"onLoggingImpression onLoggingImpression ");


            }
        });

        nativeAd.loadAd(NativeAd.MediaCacheFlag.ALL);
    }
}
