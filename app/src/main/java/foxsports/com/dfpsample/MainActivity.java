package foxsports.com.dfpsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {

    private String iu ="/20893548/FSGOTEST";

    public String getIu() {
        return iu;
    }

    private void setIu(String iu) {
        this.iu = iu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DFPListAdapter dfpListAdapter = new DFPListAdapter(MainActivity.this,0);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(dfpListAdapter);

        Button fs1 = (Button) findViewById(R.id.fs1);
        Button fs2 = (Button) findViewById(R.id.fs2);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.fs1)
                    setIu("/20893548/FSGOTEST/fs1");
                else if(view.getId()==R.id.fs2)
                    setIu("/20893548/FSGOTEST/fs2");
                dfpListAdapter.notifyDataSetChanged();
            }
        };

        fs1.setOnClickListener(onClickListener);
        fs2.setOnClickListener(onClickListener);

    }


}
