package info.ipeanut.youngsamples.third.ownview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.ipeanut.youngsamples.SampleAdapter;
import info.ipeanut.youngsamples.SampleBean;
import info.ipeanut.youngsamples.third.ownview.baseSliderView.BaseSliderActivity;
import info.ipeanut.youngsamples.third.ownview.basepage.BasePageActivity;

/**
 * Created by chenshaosina on 15/12/8.
 */
public class ThirdOwnViewActivity extends ListActivity {

    private static String[] names = {
            "BasePage",
            "BaseSliderView"

    };
    private static Class[] cls = {
            BasePageActivity.class,
            BaseSliderActivity.class

    };
    private List<SampleBean> samples = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepateSamples();
        setListAdapter(new SampleAdapter(this,samples));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(((SampleBean)(getListAdapter().getItem(position))).intent);
    }

    private void prepateSamples() {
        samples = new ArrayList<>();
        for (int i=0;i<names.length;i++){
            Intent intent = new Intent();
            intent.setClass(this,cls[i]);
            SampleBean sb = new SampleBean(names[i],intent);
            samples.add(sb);
        }
    }
}
