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
        int inputA = bundleGetData.getInt("inputA");
        int inputB = bundleGetData.getInt("inputB");
        String character = bundleGetData.getString("character");

        Log.d("So a", String.valueOf(inputA));
        Log.d("So b", String.valueOf(inputB));


        switch (character)
        {
            case "+":
                intentResult = new Intent();
                bundle = new Bundle();
                bundle.putInt("sum", inputA + inputB);

                intentResult.putExtra("RESULT_SUM", bundle);
                intentResult.setAction("FILTER_SUM");
                sendBroadcast(intentResult);

                break;
        }
    }

    public ServiceCalculator()
    {
        super("ServiceCalculator");
    }
}
