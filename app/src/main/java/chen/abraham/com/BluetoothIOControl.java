package chen.abraham.com;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BluetoothIOControl extends Activity {

    private static final String TAG = BluetoothIOControl.class.getSimpleName();
    private static final boolean D = true;



    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    // Layout Views
    private TextView _title;
    //private ListView mConversationView;
    //private EditText mOutEditText;
    private EditText mBT11EditText, mBT21EditText,
    mBT31EditText, mBT41EditText, mBT51EditText, mBT61EditText, mBT71EditText, mBT81EditText ;
    //private Button mSendButton;
    private Button _BT11On, _BT21On, _BT31On,
            _BT41On, _BT51On, _BT61On, _BT71On,
            _BT81On, _BT91On, _BTA1On;

    private Button _BT11Off, _BT21Off,
            _BT31Off, _BT41Off, _BT51Off,
            _BT61Off, _BT71Off, _BT81Off,
            _BT91Off, _BTA1Off;

    private Button btn_device, btn_disconnect, btn_connect;
    // Name of the connected device
    private String _connectedDeviceName = null;
    private String address = null;

    // Array adapter for the conversation thread
    //private ArrayAdapter<String> mConversationArrayAdapter;
    // String buffer for outgoing messages
    //private StringBuffer mOutStringBuffer;
    // Local Bluetooth adapter
    private BluetoothAdapter _bluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothIOControlService _chatService = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(D) Log.e(TAG, "+++ ON CREATE +++");

        // Set up the window layout
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.hotlife_blucontrol);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        ButterKnife.bind(this);

        // Set up the custom title
        _title = (TextView) findViewById(R.id.title_left_text);
        _title.setText(R.string.app_name);
        _title = (TextView) findViewById(R.id.title_right_text);

        // Get local Bluetooth adapter
        _bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (_bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }


    }
    private Object[] activities = {
    		"BT1010",
    		"BT1020",
    		"BT1030",
    		"BT1040",
    		"BT1050",
    		"BT1060",
    		"BT1070",
    		"BT1080"
    };
    @Override
    public void onStart() {
        super.onStart();
        if(D) Log.e(TAG, "++ ON START ++");
        if (!_bluetoothAdapter.isEnabled()) {
        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else {
            if (_chatService == null) setupChat();
        }


    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if(D) Log.e(TAG, "+ ON RESUME +");
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (_chatService != null) {
            // Only if the state is NONE, do we know that we haven't started already
            if (_chatService.getState() == ConnectionState.NONE) {
              // Start the Bluetooth chat services
              _chatService.start();
            }
        }

    }


    @OnClick(R.id.mBT11On) public void _BT11On(){ sendMessage("BT1011"+"\r\n");}
    @OnClick(R.id.mBT11Off) public void _BT11Off(){sendMessage( "BT1010"+"\r\n");}
    @OnClick(R.id.mBT21On) public void _BT21On(){ sendMessage("BT1021"+"\r\n");}
    @OnClick(R.id.mBT21Off) public void _BT21Off(){sendMessage("BT1020"+"\r\n");}
    @OnClick(R.id.mBT31On) public void _BT31On(){sendMessage("BT1031"+"\r\n");}
    @OnClick(R.id.mBT31Off) public void _BT31Off(){sendMessage( "BT1030"+"\r\n");}
    @OnClick(R.id.mBT41On) public void _BT41On(){sendMessage("BT1041"+"\r\n");}
    @OnClick(R.id.mBT41Off) public void _BT41Off(){sendMessage("BT1040"+"\r\n");}
    @OnClick(R.id.mBT51On) public void _BT51On(){sendMessage( "BT1051"+"\r\n");}
    @OnClick(R.id.mBT51Off) public void _BT51Off(){sendMessage("BT1050"+"\r\n");}
    @OnClick(R.id.mBT61On) public void _BT61On(){sendMessage("BT1061"+"\r\n");}
    @OnClick(R.id.mBT61Off) public void _BT61Off(){sendMessage("BT1060"+"\r\n");}
    @OnClick(R.id.mBT71On) public void _BT71On(){sendMessage("BT1071"+"\r\n");}
    @OnClick(R.id.mBT71Off) public void _BT71Off(){sendMessage("BT1070"+"\r\n");}
    @OnClick(R.id.mBT81On) public void _BT81On(){sendMessage("BT1081"+"\r\n");}
    @OnClick(R.id.mBT81Off) public void _BT81Off(){sendMessage("BT1080"+"\r\n");}
    @OnClick(R.id.mBT91On) public void _BT91On(){sendMessage("BT1091"+"\r\n");}
    @OnClick(R.id.mBT91Off) public void _BT91Off(){sendMessage("BT1090"+"\r\n");}
    @OnClick(R.id.mBTA1On) public void _BTA1On(){sendMessage("BT10A1"+"\r\n");}
    @OnClick(R.id.mBTA1Off) public void _BTA1Off(){sendMessage("BT10A0"+"\r\n");}


    @OnClick(R.id.btn_device) public void btn_device(){scanbt();}


    @OnClick(R.id.btn_disconnect) public void btn_disconnect(){
        if (_chatService.getState() == ConnectionState.CONNECTED) {
            _chatService.stop();
            _chatService.start();
        }
    }

    @OnClick(R.id.btn_connect) public void btn_connect(){createNXTConnection();}

    private void setupChat() {

        // Initialize the BluetoothChatService to perform bluetooth connections
        _chatService = new BluetoothIOControlService(this, mHandler);
    }
    private void createNXTConnection() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> bondedDevices = btAdapter.getBondedDevices();
		BluetoothDevice nxtDevice = null;

		for (BluetoothDevice bluetoothDevice : bondedDevices)
		{
		    if (bluetoothDevice.getName().equals(_connectedDeviceName)) {
		        nxtDevice = bluetoothDevice;
		        break;
		    }
		}

		if (nxtDevice == null)
		{
		    Toast toast = Toast.makeText(this, "No paired BT device found", Toast.LENGTH_SHORT);
		    toast.show();
		    return;
		}
        	_chatService.connect(nxtDevice);

    }
    @Override
    public synchronized void onPause() {
        super.onPause();
        if(D) Log.e(TAG, "- ON PAUSE -");
    }

    @Override
    public void onStop() {
        super.onStop();
        if(D) Log.e(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the Bluetooth chat services
        if (_chatService != null) _chatService.stop();
        if(D) Log.e(TAG, "--- ON DESTROY ---");
    }
    private void resetport(){
    	if (_chatService.getState() == ConnectionState.CONNECTED){
        	CharSequence[] list = new CharSequence[activities.length];
    		for (int i = 0; i < list.length; i++) {
    			//list[i] = (String)activities[i * 2];
    			String message = (String)activities[i];
                sendMessage(message+"\r\n");
    		}
        }
    }
    private void scanbt(){
    	Intent serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    }
    private void ensureDiscoverable() {
        if(D) Log.d(TAG, "ensure discoverable");
        if (_bluetoothAdapter.getScanMode() !=
            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
    private void hotlifeonly(){
    	String hotlifecode = "001AFF";
		String[] checkcode = address.split(":");
		String data1 = checkcode[0]+checkcode[1]+checkcode[2];
		if(data1 != hotlifecode){
            // Attempt to connect to the device
			_chatService.stop();
    		_chatService.start();

        }
    }

    /**
     * Sends a message.
     * @param message  A string of text to send.
     */
    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (_chatService.getState() != ConnectionState.CONNECTED) {
            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            _chatService.write(send);
        }
    }
    // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MessageTypes.STATE_CHANGE:
                if(D) Log.i(TAG, "STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                case ConnectionState.CONNECTED:
                    _title.setText(R.string.title_connected_to);
                    _title.append(_connectedDeviceName);
                    resetport();
                    //mConversationArrayAdapter.clear();
                    break;
                case ConnectionState.CONNECTING:
					// Get the BLuetoothDevice object
					_title.setText(R.string.title_connecting);
                    break;
                case ConnectionState.LISTEN:
                case ConnectionState.NONE:
                    _title.setText(R.string.title_not_connected);
                    break;
                }
                break;
            case MessageTypes.DEVICE_NAME:
                // save the connected device's name
                _connectedDeviceName = msg.getData().getString(DEVICE_NAME);
                Toast.makeText(getApplicationContext(), "Connected to "
                               + _connectedDeviceName, Toast.LENGTH_SHORT).show();
                break;
            case MessageTypes.TOAST:
                Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
                               Toast.LENGTH_SHORT).show();
                break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
        case REQUEST_CONNECT_DEVICE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                // Get the device MAC address
                String address = data.getExtras()
                                     .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                // Get the BLuetoothDevice object
                /*String[] hotlifecode = {"00","1A","FF"};
                String[] checkcode = address.split(":");
                String[] changcode = new String[3];
                changcode[0] = checkcode[0];             
                changcode[1] = checkcode[1];
                changcode[2] = checkcode[2];
                
                // Attempt to connect to the device
                if(changcode[0] == hotlifecode[0] && changcode[1] == hotlifecode[1] && changcode[2] == hotlifecode[2]){	                	
                    // Attempt to connect to the device
        			_chatService.stop();
            		_chatService.start();
                } 
                else{
                	
                }*/
                BluetoothDevice device = _bluetoothAdapter.getRemoteDevice(address);
                _chatService.connect(device);




            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth is now enabled, so set up a chat session
                setupChat();
            } else {
                // User did not enable Bluetooth or an error occured
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.scan:
            // Launch the DeviceListActivity to see devices and do scan
            Intent serverIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
            return true;
        case R.id.discoverable:
            // Ensure this device is discoverable by others
            ensureDiscoverable();
            return true;
        }
        return false;
    }














}