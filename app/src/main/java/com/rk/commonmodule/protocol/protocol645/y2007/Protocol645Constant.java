package com.rk.commonmodule.protocol.protocol645.y2007;

public class Protocol645Constant {
    public static final String ADDRESS = "address";
    public static final String CTRL_CODE = "control_code";
    public static final String DATA_LENGTH = "data_length";
    public static final String DATA_IDENTIFIER = "data_identifier";
    public static final String DATA = "data";

    public static final String FRAME_OK = "frame_ok";

    public static final byte FRAME_BEGIN = (byte) 0x68;
    public static final byte FRAME_END = (byte) 0x16;

    public static final int ADDRESS_LENGTH = 12;

    public class ControlCode {
        // read meter address
        public static final byte READ_ADDRESS_REQUEST = (byte) 0x13;
        public static final byte READ_ADDRESS_RESPOND_OK = (byte) 0x93;
        public static final String READ_ADDRESS_VALUE_KEY = "address_value";
        // read meter data
        public static final byte READ_DATA_REQUEST = (byte) 0x11;
        public static final byte READ_DATA_RESPOND_OK = (byte) 0x91;
        public static final byte READ_DATA_RESPOND_OK_CONTINUE = (byte) 0xB1;
        public static final byte READ_DATA_RESPOND_ERROR = (byte) 0xD1;
    }
}
