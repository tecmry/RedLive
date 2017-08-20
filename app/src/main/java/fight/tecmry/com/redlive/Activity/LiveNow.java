package fight.tecmry.com.redlive.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/17.
 */

public class LiveNow extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView Rv;
    private EditText mEditText;
    private ImageButton Left_Button;
    private ImageButton Right_Button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livenow);
        init();
    }
    private void init()
    {
        Rv = (RecyclerView)findViewById(R.id.Live_Rv);
        mEditText = (EditText)findViewById(R.id.editText);
        Left_Button = (ImageButton) findViewById(R.id.imageButton);
        Right_Button = (ImageButton)findViewById(R.id.imageButton2);

    }

    @Override
    public void onClick(View view) {

    }
}
