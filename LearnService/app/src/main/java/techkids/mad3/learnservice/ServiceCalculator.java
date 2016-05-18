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
    @Override
    protected void onHandleIntent(Intent intent) {
        int inputA = intent.getExtras().getInt("inputA");
        int inputB = intent.getExtras().getInt("inputB");

        Log.d("So a", String.valueOf(inputA));
        Log.d("So b", String.valueOf(inputB));

        Intent intentResult = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("sum", inputA + inputB);
        intentResult.setAction("FilterSum");
        sendBroadcast(intentResult);

    }

    public ServiceCalculator()
    {
        super("ServiceCalculator");
    }


}
