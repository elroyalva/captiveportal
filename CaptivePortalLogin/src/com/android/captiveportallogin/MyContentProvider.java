package com.android.captiveportallogin;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by elroy on 12/7/16.
 */

public class MyContentProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        throw new UnsupportedOperationException("Not Supported by this provider");
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s , String[] strings){
        throw new UnsupportedOperationException("Not Supported by this provider");
    }

    @Override
    public int delete(Uri uri, String s, String[] strings){
        throw new UnsupportedOperationException("Not Supported by this provider");
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues){
        throw new UnsupportedOperationException("Not Supported by this provider");
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException{
        String fileName =uri.getLastPathSegment();
        if(fileName == null) {
            throw new FileNotFoundException();
        }
        File privateFile = new File(getContext().getFilesDir(), fileName);

        return ParcelFileDescriptor.open(privateFile,ParcelFileDescriptor.MODE_READ_ONLY);
    }
}
