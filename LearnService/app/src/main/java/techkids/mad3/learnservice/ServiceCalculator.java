package techkids.mad3.learnservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by TrungNT on 5/18/2016.
 */
public class ServiceCalculator extends IntentService {
    private Intent intentResult;
    private Bundle bundle;

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundleGetData = intent.getBundleExtra("inputData");
        double inputA = bundleGetData.getDouble("inputA");
        double inputB = bundleGetData.getDouble("inputB");
        String character = bundleGetData.getString("character");

        Log.d("So a", String.valueOf(inputA));
        Log.d("So b", String.valueOf(inputB));


        intentResult = new Intent();
        bundle = new Bundle();

        switch (character)
        {
            case "+":
                bundle.putDouble("sum", inputA + inputB);
                bundle.putString("character", "+");
                break;
            case "-":
                bundle.putDouble("subtraction", inputA - inputB);
                bundle.putString("character", "-");
                break;
            case "*":
                bundle.putDouble("multiplication", inputA * inputB);
                bundle.putString("character", "*");
                break;
            case "/":
                bundle.putDouble("division", inputA / inputB);
                bundle.putString("character", "/");
                break;
        }

        intentResult.putExtra("RESULT_CALCULATOR", bundle);
        intentResult.setAction("FILTER_CALCULATOR");
        sendBroadcast(intentResult);
    }

    public ServiceCalculator()
    {
        super("ServiceCalculator");
    }
}
