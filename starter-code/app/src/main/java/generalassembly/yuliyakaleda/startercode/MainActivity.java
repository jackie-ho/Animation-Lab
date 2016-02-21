package generalassembly.yuliyakaleda.startercode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.wish_edit);
        final TextView textView = (TextView) findViewById(R.id.wish_text);
        ListView listView = (ListView) findViewById(R.id.wish_list);
        Button wishSubmit = (Button) findViewById(R.id.wish_button);
        Button removeWish = (Button)findViewById(R.id.remove_wish);
        //TODO: set up all the view and event listeners.

        final LinkedList<String> wishArray = new LinkedList<>();
        final ArrayList<String> tempArray = new ArrayList<>();
        final ArrayAdapter<String> wishAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, wishArray);
        listView.setAdapter(wishAdapter);

        wishSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String wish = editText.getText().toString();
                textView.setText(wish);
                editText.setText("");
                Animation textAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation);
                textView.startAnimation(textAnimation);
                textAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        tempArray.clear();
                        tempArray.addAll(wishArray);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //Move wish to top of list
                        wishArray.clear();
                        wishArray.add(wish);
                        wishArray.addAll(tempArray);
                        wishAdapter.notifyDataSetChanged();
                        textView.setText("");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


            }
        });

        removeWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wishArray != null && !wishArray.isEmpty()) {
                    wishArray.remove(0);
                    wishAdapter.notifyDataSetChanged();
                }
            }
        });

    }


}
