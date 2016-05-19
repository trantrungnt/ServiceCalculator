package techkids.mad3.learnservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

//Bound: binder, Messager, AIPL
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlus;
    private EditText inputA, inputB;
    private TextView tvResultC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                //lay du lieu o day de day vao textResult
//            }
//        };
//        registerReceiver(broadcastReceiver, new IntentFilter("FILTER_SUM"));
    }

    private void initComponent()
    {
        inputA = (EditText) this.findViewById(R.id.editTextInputA);
        inputB = (EditText) this.findViewById(R.id.editTextInputB);
        tvResultC = (TextView) this.findViewById(R.id.tvDisplayResultC);
        btnPlus = (Button) this.findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id==R.id.btnPlus)
        {
            Intent intent = new Intent(MainActivity.this, ServiceCalculator.class);
            Bundle bundle = new Bundle();
            bundle.putInt("inputA", Integer.parseInt(inputA.getText().toString()));
            bundle.putInt("inputB", Integer.parseInt(inputB.getText().toString()));
            intent.putExtra("inputData", bundle);
            startService(intent);
        }
    }
}
