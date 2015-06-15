package foxsports.com.dfpsample;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

/**
 * Created by amitma on 6/12/15.
 */
public class DFPListAdapter extends ArrayAdapter<Integer> {

    private static final int ITEM_AD = 0;
    private static final int ITEM_TEXT = 1;
    private static final boolean MAKE_IT_WORK = false;
    private Context context;

    //
    private  static PublisherAdView publisherAdView=null;

    public DFPListAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
         if(viewType==ITEM_AD)
            return getAdView(position, convertView, parent);
        else
            return getTextView(position,convertView,parent);

    }

    private View getTextView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){

            convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.list_item,null);
        }
            return convertView;
    }

    private View getAdView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null || ((PublisherAdView)convertView).getAdUnitId()!=((MainActivity)context).getIu()){
            convertView = getAdView(context);
        }
        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        Log.e("count","position " + position + " %2 " + position%2);
        if(position%2==0)
            return ITEM_AD;
        else
            return ITEM_TEXT;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return 25;
    }

    private static PublisherAdView getAdView(Context context){
        if(MAKE_IT_WORK) {
            return createAdView(context);
        }else {
            if (publisherAdView == null)
                publisherAdView = createAdView(context);
            return publisherAdView;
        }
    }


    private static PublisherAdView createAdView( Context context){
        Log.e("ads ", "Creating Ad view");
        PublisherAdView chipAdView = new PublisherAdView(context);

        AdSize customAdSize;

        customAdSize = new AdSize(320, 50);
        chipAdView.setAdUnitId(((MainActivity)context).getIu());
        chipAdView.setBackgroundColor(0x3b3b3b);
        chipAdView.setAdSizes(customAdSize);
        //using tagholder since we a need a object that can be declared final to be used inside the listener
        final View tagHolder = new View(context);
        chipAdView.setTag(tagHolder);
        chipAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                Log.e("amit", "onAdLoaded");
                tagHolder.setTag(Boolean.valueOf("true"));

            }

            public void onAdFailedToLoad(int errorCode) {
                Log.e("amit", "onAdFailedToLoad");
                tagHolder.setTag(Boolean.valueOf("false"));
            }

            ;

            public void onAdOpened() {
                Log.e("amit", "onAdOpened");
            }

            public void onAdClosed() {
                Log.e("amit", "onAdClosed");
            }

            public void onAdLeftApplication() {
                Log.e("amit", "onAdLeftApplication");
            }
        });
        chipAdView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                Log.e("amit", "chipAdView onlayout change");
            }
        });
        chipAdView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                Log.e("amit", "chipAdView onViewAttachedToWindow ");
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                Log.e("amit", "chipAdView onViewDetachedFromWindow ");
            }
        });
        chipAdView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                Log.e("amit","chipAdView onlayout change");
            }
        });
        chipAdView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                Log.e("amit", "chipAdView onViewAttachedToWindow ");
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                Log.e("amit", "chipAdView onViewDetachedFromWindow ");
            }
        });

        chipAdView.loadAd(new PublisherAdRequest.Builder().build());
        return chipAdView;
    }
}
