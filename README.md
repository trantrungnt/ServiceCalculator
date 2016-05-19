# ServiceCalculator

+ Serive dùng để xử lý các tác vụ nặng, nó cũng tương tự Async Task nhưng có thêm nhiều phương thức hay ho hơn, nhưng không tác động trực tiếp được vào giao diện UI mà phải thông qua Intent để chuyển dữ liệu vào UI

##Yêu cầu
+ Dùng IntentService và BroadCastReceiver để tính toán phép: cộng, trừ, nhân chia. Sau đó gửi kết quả về thông qua BroadCastReceiver và hiển thị lên giao diện

![Lý thuyết về Service](http://i477.photobucket.com/albums/rr132/trungepu/13223525_1004821056276841_1097650210_o_zps94iiyfth.jpg)

##Chú ý khi code
+ Ta có thể định nghĩa 1 Service tính toán ngầm các chức năng: Cộng trừ, Nhân, Chia. Sau đó, Service sẽ trả lại kết quả từng phép tính và gửi vào Intent + Bundle. Giao diện sẽ nhận kết quả vừa tính toán được và hiển thị lên màn hình.

```
public class ServiceCalculator extends IntentService {
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundleGetData = intent.getBundleExtra("inputData");
        int inputA = bundleGetData.getInt("inputA");
        int inputB = bundleGetData.getInt("inputB");

        Log.d("So a", String.valueOf(inputA));
        Log.d("So b", String.valueOf(inputB));

        Intent intentResult = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("sum", inputA + inputB);

        intentResult.putExtra("RESULT_SUM", bundle);
        intentResult.setAction("FILTER_SUM");
        sendBroadcast(intentResult);

    }

    public ServiceCalculator()
    {
        super("ServiceCalculator");
    }
}
```

##Môi trường phát triển
+ Bộ công cụ Android Studio 2.1
+ Máy ảo Genymotion dùng api thấp nhất api 17 - max api 23
