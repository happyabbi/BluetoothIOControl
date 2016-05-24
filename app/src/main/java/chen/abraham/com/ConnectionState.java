package  chen.abraham.com;

/**
 * Created by AbrahamChen on 2016/5/23.
 */
public class ConnectionState {
    // Constants that indicate the current connection state
    public static final int NONE = 0;       // we're doing nothing
    public static final int LISTEN = 1;     // now listening for incoming connections
    public static final int CONNECTING = 2; // now initiating an outgoing connection
    public static final int CONNECTED = 3;  // now connected to a remote device
}
