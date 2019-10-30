package com.rk.postmeterreader;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReadAndWrite;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFUtils;
import com.linuxense.javadbf.DBFWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        requestPermissions();

    }

    private void initView() {
        Log.i(TAG, "initView");
        mContentTextView = (TextView) findViewById(R.id.content);
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

            reader.writeItem(62, DbfConstant.Colume.JCBZ, "1");
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
}
