package frost.com.htvchallengetruck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

//import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private TextView tvNoTask;
    private ImageView imgvImageOfIssue;
    private Button btnNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("frost", "onCreate: "+ FirebaseInstanceId.getInstance().getToken());
        tvNoTask = (TextView)findViewById(R.id.tv_no_task);
        imgvImageOfIssue = (ImageView)findViewById(R.id.imgvIssueImage);
        btnNavigate = (Button)findViewById(R.id.btn_navigate);



        if(getIntent() != null && getIntent().getBundleExtra("OpenMain").equals("true")){
            tvNoTask.setVisibility(View.GONE);
            imgvImageOfIssue.setVisibility(View.VISIBLE);
            btnNavigate.setVisibility(View.VISIBLE);

            Picasso.with(this)
                    .load("http://146.148.111.64/resources/issueId"+getIntent().getBundleExtra("issueId")+".png")
//                    .placeholder(R.drawable.user_placeholder)
//                    .error(R.drawable.user_placeholder_error)
                    .into(imgvImageOfIssue);
        }else {
            tvNoTask.setVisibility(View.VISIBLE);
            imgvImageOfIssue.setVisibility(View.GONE);
            btnNavigate.setVisibility(View.GONE);
        }
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
