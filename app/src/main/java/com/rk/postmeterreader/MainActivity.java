package com.rk.postmeterreader;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReadAndWrite;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFUtils;
import com.linuxense.javadbf.DBFWriter;
import com.rk.commonmodule.channel.ChannelConstant;
import com.rk.commonmodule.channel.InfraredChannel;
import com.rk.commonmodule.channel.channelmanager.ChannelManager;
import com.rk.commonmodule.protocol.protocol645.y2007.Protocol645Constant;
import com.rk.commonmodule.protocol.protocol645.y2007.Protocol645FrameMaker;
import com.rk.commonmodule.protocol.protocol645.y2007.Protocol645FramePaser;
import com.rk.commonmodule.transfer.TransferManager;
import com.rk.commonmodule.utils.DataConvertUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mContentTextView;
    private TextView mMeterDayFreezingTextView;

    private ChannelManager.IChannelOpenAndCloseListener mChannelOpenAndCloseListener = new ChannelManager.IChannelOpenAndCloseListener() {
        @Override
        public void onOpenFail() {

        }

        @Override
        public void onOpenSuccess() {
            Log.i(TAG, "channel open successfully");
        }

        @Override
        public void onCloseFail() {

        }

        @Override
        public void onCloseSuccess() {
            Log.i(TAG, "channel close successfully");
        }
    };

    private ChannelManager.IChannelManagerSendListener mChannelManagerSendListener = new ChannelManager.IChannelManagerSendListener() {
        @Override
        public void onChannelSendBegin() {
            Log.i(TAG, "onChannelSendBegin");
        }

        @Override
        public void onChannelSendFail(String error) {
            Log.i(TAG, "onChannelSendFail, error: " + error);
        }

        @Override
        public void onChannelSendSuccess(String succss) {
            Log.i(TAG, "onChannelSendSuccess");
        }
    };

    private ChannelManager.IChannelManagerReceiveListener mChannelManagerReceiveListener = new ChannelManager.IChannelManagerReceiveListener() {
        @Override
        public void onChannelReceiveBegin() {
            Log.i(TAG, "onChannelReceiveBegin");
        }

        @Override
        public void onChannelReceiveFail(String error) {
            Log.i(TAG, "onChannelReceiveFail");
        }

        @Override
        public void onChannelReceiveSuccess(byte[] data) {
            Log.i(TAG, "onChannelReceiveSuccess, data: " + DataConvertUtils.convertByteArrayToString(data, false));
        }
    };

    private TransferManager.IChannelDataTransferListener mChannelDataTransferListener = new TransferManager.IChannelDataTransferListener() {
        @Override
        public void onSendBegin(String msg) {
            Log.i(TAG, "onSendBegin, msg: " + msg);
        }

        @Override
        public void onSendFail(String msg) {
            Log.i(TAG, "onSendFail, msg: " + msg);
        }

        @Override
        public void onSendSuccess(String msg) {
            Log.i(TAG, "onSendSuccess, msg: " + msg);
        }

        @Override
        public void onReceiveBegin(String msg) {
            Log.i(TAG, "onReceiveBegin, msg: " + msg);
        }

        @Override
        public void onReceiveFail(String msg) {
            Log.i(TAG, "onReceiveFail, msg: " + msg);
        }

        @Override
        public void onReceiveSuccess(byte[] data) {
            Log.i(TAG, "onReceiveSuccess, data: " + DataConvertUtils.convertByteArrayToString(data, false));
            Map map = Protocol645FramePaser.PROTOCOL_645_FRAME_PASER.parse(data);

            if (map != null&& map.containsKey(Protocol645Constant.ControlCode.READ_ADDRESS_VALUE_KEY)) {
                final String address = (String) map.get(Protocol645Constant.ControlCode.READ_ADDRESS_VALUE_KEY);
                Log.i(TAG, "onReceiveSuccess, get meter address： " + address);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mContentTextView != null) {
                            mContentTextView.setText(address);
                        }
                    }
                });
            } else if (map != null&& map.containsKey(Protocol645Constant.DataIdentifier.POSITIVE_ACTIVE__TOTAL_POWER_KEY)) {
                final String value = (String) map.get(Protocol645Constant.DataIdentifier.POSITIVE_ACTIVE__TOTAL_POWER_KEY);
                Log.i(TAG, "onReceiveSuccess, POSITIVE_ACTIVE__TOTAL_POW： " + value);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMeterDayFreezingTextView != null) {
                            mMeterDayFreezingTextView.setText(value);
                        }
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChannelManager.getInstance(this).setChannelOpenAndCloseListener(mChannelOpenAndCloseListener);
        ChannelManager.getInstance(this).setChannelManagerSendListener(mChannelManagerSendListener);
        ChannelManager.getInstance(this).setChannelManagerReceiveListener(mChannelManagerReceiveListener);
        TransferManager.getInstance(this).setChannelDataTransferListener(mChannelDataTransferListener);
        initView();
        requestPermissions();

    }

    private void initView() {
        Log.i(TAG, "initView");
        mContentTextView = (TextView) findViewById(R.id.meter_address);
        mMeterDayFreezingTextView = (TextView) findViewById(R.id.meter_day_freezing);
        Button testBtn = (Button) findViewById(R.id.start_IR_btn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelManager.getInstance(MainActivity.this).openChannel(ChannelConstant.Channel.CHANNEL_INFRARED);
            }
        });
        Button readMeterAddrBtn = (Button) findViewById(R.id.read_meter_address_btn);

        //TODO: just test buttons.
        //读表地址
        //68 aa aa aa aa aa aa 68 11 04 35 37 33 37 b7 16
        //fe fe fe fe 68 91 40 02 01 18 07 68 91 0a 35 37 33 37 c4 73 35 34 4b 3a 59 16

        readMeterAddrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String frameString = "68aaaaaaaaaaaa68110435373337b716";
//                ArrayList<Byte> frameList = DataConvertUtils.convertHexStringToByteArray(frameString, frameString.length(),false);
//                byte[] frame = new byte[frameList.size()];
//                for (int i = 0; i < frame.length; i++) {
//                    frame[i] = frameList.get(i);
//                }
//                TransferManager.getInstance(MainActivity.this).setChannel(new InfraredChannel());
//                TransferManager.getInstance(MainActivity.this).send(frame, frame.length);
                requestMeterAddress();

            }
        });

        //读正向有功总电能
        //68 91 40 02 01 18 07 68 11 04 33 33 34 33 a5 16
        //FE FE FE FE 68 91 40 02 01 18 07 68 91 08 33 33 34 33 33 33 33 33 F5 16
        Button readPositiveActivePower = (Button) findViewById(R.id.read_day_freezing_btn);
        readPositiveActivePower.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                String frameString = "6891400201180768110433333433a516";
//                ArrayList<Byte> frameList = DataConvertUtils.convertHexStringToByteArray(frameString, frameString.length(),false);
//                byte[] frame = new byte[frameList.size()];
//                for (int i = 0; i < frame.length; i++) {
//                    frame[i] = frameList.get(i);
//                }
//                TransferManager.getInstance(MainActivity.this).setChannel(new InfraredChannel());
//                TransferManager.getInstance(MainActivity.this).send(frame, frame.length);
                requestPositiveActiveTotalPowser();
            }
        });
    }

    private void readDbf() {
        Log.i(TAG, "readDbf");
        DBFReadAndWrite reader = null;
        try {
            String dbfPath = Environment.getExternalStorageDirectory() + "/" + "nwcb.dbf";
            File dbfFile = new File(dbfPath);
            if (!dbfFile.exists()) {
                Log.i(TAG, "readDbf, dbf file not exist");
                return;
            }
            // create a DBFReader object
            reader = new DBFReadAndWrite(new FileInputStream(dbfPath), Charset.forName("GBK"), dbfPath);
            //reader.setCharactersetName("GBK"); // fix chinese messy code
            // get the field count if you want for some reasons like the following
            int numberOfFields = reader.getFieldCount();

            // use this count to fetch all field information
            // if required
            int columeIdx = 0;
            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i);
                // do something with it if you want
                // refer the JavaDoc API reference for more details
                Log.i(TAG, "readDbf, field[" + i + "] = " + field.getName() + ", type: " + field.getType() + ", size: " + field.getLength());
                if ((DbfConstant.Colume.CBQDMC).equals(field.getName())) {
                     columeIdx = i;
                     //break;
                }

            }
            // Now, lets us start reading the rows
            Object[] rowObjects;
            while ((rowObjects = reader.nextRecord()) != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < rowObjects.length; i++) {
                    System.out.println(rowObjects[i]);
                    if (rowObjects[i] != null) {
                        sb.append(rowObjects[i]).append(" | ");
                    } else {
                        sb.append("null").append(" | ");
                    }
                }
                Log.i(TAG, "readDbf, row = " + sb.toString());
                mContentTextView.setText(rowObjects[columeIdx].toString());
                break;
            }

            reader.writeItem(3, DbfConstant.Colume.CBQDMC, "鼎信股份通信");
            // By now, we have iterated through all of the rows
        } catch (DBFException e) {
            Log.e(TAG, "DBFException, e: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "IOException, e: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
        }
    }

    private void dbfWrite() {
        Log.i(TAG, "dbfWrite");
        DBFWriter writer = null;
        try {
            String dbfPath = Environment.getExternalStorageDirectory() + "/" + "test.dbf";
//            File dbfFile = new File(dbfPath);
//            if (!dbfFile.exists()) {
//                Log.i(TAG, "readDbf, dbf file not exist");
//                return;
//            }
            writer = new DBFWriter(new FileOutputStream(dbfPath), Charset.forName("GBK"));
            //int columeIdx = writer.
            DBFField[] fields = new DBFField[3];

            fields[0] = new DBFField();
            fields[0].setName("emp_code");
            fields[0].setType(DBFDataType.CHARACTER);
            fields[0].setLength(10);

            fields[1] = new DBFField();
            fields[1].setName("emp_name");
            fields[1].setType(DBFDataType.CHARACTER);
            fields[1].setLength(20);

            fields[2] = new DBFField();
            fields[2].setName("salary");
            fields[2].setType(DBFDataType.NUMERIC);
            fields[2].setLength(12);
            fields[2].setDecimalCount(4);

            writer.setFields(fields);

            // now populate DBFWriter

            Object rowData[] = new Object[3];
            rowData[0] = "1000";
            rowData[1] = "John 春和景明";
            rowData[2] = new Double(5000.00);

            writer.addRecord(rowData);

            rowData = new Object[3];
            rowData[0] = "1001";
            rowData[1] = "Lalit";
            rowData[2] = new Double(3400.00);

            writer.addRecord(rowData);

            rowData = new Object[3];
            rowData[0] = "1002";
            rowData[1] = "Rohit";
            rowData[2] = new Double(7350.00);

            writer.addRecord(rowData);

        } catch (Exception e) {
            Log.e(TAG, "dbfWrite, write dbf error: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
                writer = null;
            }

        }
    }

    private void requestPermissions(){
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Storage", R.drawable.permission_ic_storage));
        HiPermission.create(this)
                .permissions(permissionItems)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        finish();
                    }
                    @Override
                    public void onFinish() {
                        readDbf();
                        //dbfWrite();
                    }
                    @Override
                    public void onDeny(String permission, int position) {
                        Log.i(TAG, "onDeny");
                    }
                    @Override
                    public void onGuarantee(String permission, int position) {
                        Log.i(TAG, "onGuarantee");
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ChannelManager.getInstance(this).openTty();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ChannelManager.getInstance(this).closeTty();
    }

    private void requestMeterAddress() {
        Map map = new HashMap();
        map.put(Protocol645Constant.ADDRESS, "AAAAAAAAAAAA");
        map.put(Protocol645Constant.CTRL_CODE, Protocol645Constant.ControlCode.READ_ADDRESS_REQUEST);
        map.put(Protocol645Constant.DATA_LENGTH, 0);
        byte[] frame = Protocol645FrameMaker.PROTOCOL_645_FRAME_MAKER.makeFrame(map);
        Log.i(TAG, "requestMeterAddress, frame: " + DataConvertUtils.convertByteArrayToString(frame, false));
        TransferManager.getInstance(MainActivity.this).setChannel(new InfraredChannel());
        TransferManager.getInstance(MainActivity.this).send(frame, frame.length);
    }

    private void requestPositiveActiveTotalPowser() {
        Map map = new HashMap();
        map.put(Protocol645Constant.ADDRESS, mContentTextView.getText().toString());
        map.put(Protocol645Constant.CTRL_CODE, Protocol645Constant.ControlCode.READ_DATA_REQUEST);
        map.put(Protocol645Constant.DATA_LENGTH, 4);
        map.put(Protocol645Constant.DATA_IDENTIFIER, new byte[]{0x00, 0x01, 0x00, 0x00});
        byte[] frame = Protocol645FrameMaker.PROTOCOL_645_FRAME_MAKER.makeFrame(map);
        Log.i(TAG, "requestMeterAddress, frame: " + DataConvertUtils.convertByteArrayToString(frame, false));
        TransferManager.getInstance(MainActivity.this).setChannel(new InfraredChannel());
        if (frame == null || frame.length <= 0) {
            Log.i(TAG, "requestPositiveActiveTotalPowser, no frame");
            return;
        }
        TransferManager.getInstance(MainActivity.this).send(frame, frame.length);
    }
}
