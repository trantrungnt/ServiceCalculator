package techkids.mad3.learnservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//Bound: binder, Messager, AIPL
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlus, btnSubtraction, btnMultiplication, btnDivision;
    private EditText inputA, inputB;
    private TextView tvResultC;
    private Intent intent;
    private Bundle bundle;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //lay du lieu o day de day vao textResult
                bundle = intent.getBundleExtra("RESULT_CALCULATOR");
                String character = bundle.getString("character");

                switch (character) {
                    case "+":
                        result = bundle.getDouble("sum");
                    break;
                    case "-":
                        result = bundle.getDouble("subtraction");
                        break;
                    case "*":
                        result = bundle.getDouble("multiplication");
                        break;
                    case "/":
                        result = bundle.getDouble("division");
                        break;
                }

                tvResultC.setText(String.valueOf(result));
            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter("FILTER_CALCULATOR"));
    }

    private void initComponent()
    {
        inputA = (EditText) this.findViewById(R.id.editTextInputA);
        inputB = (EditText) this.findViewById(R.id.editTextInputB);
        tvResultC = (TextView) this.findViewById(R.id.tvDisplayResultC);
        btnPlus = (Button) this.findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);
        btnSubtraction = (Button) this.findViewById(R.id.btnSubtraction);
        btnSubtraction.setOnClickListener(this);
        btnMultiplication = (Button) this.findViewById(R.id.btnMultiplication);
        btnMultiplication.setOnClickListener(this);
        btnDivision = (Button) this.findViewById(R.id.btnDivision);
        btnDivision.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String strInputA, strInputB;
        int numberA, numberB;

        strInputA = inputA.getText().toString();
        strInputB = inputB.getText().toString();

        intent = new Intent(MainActivity.this, ServiceCalculator.class);
        bundle = new Bundle();

        switch (id)
        {
            case R.id.btnPlus:
                bundle.putString("character", "+");
                break;

            case R.id.btnSubtraction:
                bundle.putString("character", "-");
                break;

            case R.id.btnMultiplication:
                bundle.putString("character", "*");
                break;

            case R.id.btnDivision:
                bundle.putString("character", "/");
                break;
        }

        if(!TextUtils.isEmpty(strInputA) && !TextUtils.isEmpty(strInputB))
        {
            numberA = Integer.parseInt(strInputA);
            numberB = Integer.parseInt(strInputB);
            bundle.putInt("inputA", numberA);
            bundle.putInt("inputB", numberB);
            intent.putExtra("inputData", bundle);
            startService(intent);
        } else
        {
            Toast.makeText(getApplicationContext(), "You need to type 2 numbers", Toast.LENGTH_SHORT).show();
        }
    }

}
