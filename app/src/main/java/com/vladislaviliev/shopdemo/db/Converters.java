package com.vladislaviliev.shopdemo.db;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Converters {
    @TypeConverter
    public LocalDate stringToDate(@NonNull final String s) {
        return LocalDate.parse(s);
    }

    @TypeConverter
    public String dateToString(@NonNull final LocalDate d) {
        return d.toString();
    }

    @TypeConverter
    public String bigDecimalToString(@NonNull final BigDecimal d) {
        return d.toString();
    }

    @TypeConverter
    public BigDecimal stringToBigDecimal(@NonNull final String s) {
        return new BigDecimal(s);
    }

    @TypeConverter
    public String uriToString(@NonNull final Uri uri) {
        return uri.toString();
    }

    @TypeConverter
    public Uri stringToUri(@NonNull final String s) {
        return Uri.parse(s);
    }
}