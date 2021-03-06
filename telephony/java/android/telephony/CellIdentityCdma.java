/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.telephony;

import android.os.Parcel;
import android.text.TextUtils;

import java.util.Objects;

/**
 * CellIdentity is to represent a unique CDMA cell
 */
public final class CellIdentityCdma extends CellIdentity {
    private static final String TAG = CellIdentityCdma.class.getSimpleName();
    private static final boolean DBG = false;

    // Network Id 0..65535
    private final int mNetworkId;
    // CDMA System Id 0..32767
    private final int mSystemId;
    // Base Station Id 0..65535
    private final int mBasestationId;
    /**
     * Longitude is a decimal number as specified in 3GPP2 C.S0005-A v6.0.
     * It is represented in units of 0.25 seconds and ranges from -2592000
     * to 2592000, both values inclusive (corresponding to a range of -180
     * to +180 degrees).
     */
    private final int mLongitude;
    /**
     * Latitude is a decimal number as specified in 3GPP2 C.S0005-A v6.0.
     * It is represented in units of 0.25 seconds and ranges from -1296000
     * to 1296000, both values inclusive (corresponding to a range of -90
     * to +90 degrees).
     */
    private final int mLatitude;
    // long alpha Operator Name String or Enhanced Operator Name String
    private final String mAlphaLong;
    // short alpha Operator Name String or Enhanced Operator Name String
    private final String mAlphaShort;

    /**
     * @hide
     */
    public CellIdentityCdma() {
        super(TAG, TYPE_CDMA, null, null);
        mNetworkId = Integer.MAX_VALUE;
        mSystemId = Integer.MAX_VALUE;
        mBasestationId = Integer.MAX_VALUE;
        mLongitude = Integer.MAX_VALUE;
        mLatitude = Integer.MAX_VALUE;
        mAlphaLong = null;
        mAlphaShort = null;
    }

    /**
     * public constructor
     * @param nid Network Id 0..65535
     * @param sid CDMA System Id 0..32767
     * @param bid Base Station Id 0..65535
     * @param lon Longitude is a decimal number ranges from -2592000
     *        to 2592000
     * @param lat Latitude is a decimal number ranges from -1296000
     *        to 1296000
     *
     * @hide
     */
    public CellIdentityCdma(int nid, int sid, int bid, int lon, int lat) {
        this(nid, sid, bid, lon, lat, null, null);
    }

    /**
     * public constructor
     * @param nid Network Id 0..65535
     * @param sid CDMA System Id 0..32767
     * @param bid Base Station Id 0..65535
     * @param lon Longitude is a decimal number ranges from -2592000
     *        to 2592000
     * @param lat Latitude is a decimal number ranges from -1296000
     *        to 1296000
     * @param alphal long alpha Operator Name String or Enhanced Operator Name String
     * @param alphas short alpha Operator Name String or Enhanced Operator Name String
     *
     * @hide
     */
    public CellIdentityCdma(int nid, int sid, int bid, int lon, int lat, String alphal,
                             String alphas) {
        super(TAG, TYPE_CDMA, null, null);
        mNetworkId = nid;
        mSystemId = sid;
        mBasestationId = bid;
        mLongitude = lon;
        mLatitude = lat;
        mAlphaLong = alphal;
        mAlphaShort = alphas;
    }

    private CellIdentityCdma(CellIdentityCdma cid) {
        this(cid.mNetworkId, cid.mSystemId, cid.mBasestationId, cid.mLongitude, cid.mLatitude,
                cid.mAlphaLong, cid.mAlphaShort);
    }

    CellIdentityCdma copy() {
        return new CellIdentityCdma(this);
    }

    /**
     * @return Network Id 0..65535, Integer.MAX_VALUE if unknown
     */
    public int getNetworkId() {
        return mNetworkId;
    }

    /**
     * @return System Id 0..32767, Integer.MAX_VALUE if unknown
     */
    public int getSystemId() {
        return mSystemId;
    }

    /**
     * @return Base Station Id 0..65535, Integer.MAX_VALUE if unknown
     */
    public int getBasestationId() {
        return mBasestationId;
    }

    /**
     * @return Base station longitude, which is a decimal number as
     * specified in 3GPP2 C.S0005-A v6.0. It is represented in units
     * of 0.25 seconds and ranges from -2592000 to 2592000, both
     * values inclusive (corresponding to a range of -180
     * to +180 degrees). Integer.MAX_VALUE if unknown.
     */
    public int getLongitude() {
        return mLongitude;
    }

    /**
     * @return Base station latitude, which is a decimal number as
     * specified in 3GPP2 C.S0005-A v6.0. It is represented in units
     * of 0.25 seconds and ranges from -1296000 to 1296000, both
     * values inclusive (corresponding to a range of -90
     * to +90 degrees). Integer.MAX_VALUE if unknown.
     */
    public int getLatitude() {
        return mLatitude;
    }

    /**
     * @return The long alpha tag associated with the current scan result (may be the operator
     * name string or extended operator name string). May be null if unknown.
     */
    public CharSequence getOperatorAlphaLong() {
        return mAlphaLong;
    }

    /**
     * @return The short alpha tag associated with the current scan result (may be the operator
     * name string or extended operator name string).  May be null if unknown.
     */
    public CharSequence getOperatorAlphaShort() {
        return mAlphaShort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mNetworkId, mSystemId, mBasestationId, mLatitude, mLongitude,
                mAlphaLong, mAlphaShort);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof CellIdentityCdma)) {
            return false;
        }

        CellIdentityCdma o = (CellIdentityCdma) other;

        return mNetworkId == o.mNetworkId
                && mSystemId == o.mSystemId
                && mBasestationId == o.mBasestationId
                && mLatitude == o.mLatitude
                && mLongitude == o.mLongitude
                && TextUtils.equals(mAlphaLong, o.mAlphaLong)
                && TextUtils.equals(mAlphaShort, o.mAlphaShort);
    }

    @Override
    public String toString() {
        return new StringBuilder(TAG)
        .append(":{ mNetworkId=").append(mNetworkId)
        .append(" mSystemId=").append(mSystemId)
        .append(" mBasestationId=").append(mBasestationId)
        .append(" mLongitude=").append(mLongitude)
        .append(" mLatitude=").append(mLatitude)
        .append(" mAlphaLong=").append(mAlphaLong)
        .append(" mAlphaShort=").append(mAlphaShort)
        .append("}").toString();
    }

    /** Implement the Parcelable interface */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (DBG) log("writeToParcel(Parcel, int): " + toString());
        super.writeToParcel(dest, TYPE_CDMA);
        dest.writeInt(mNetworkId);
        dest.writeInt(mSystemId);
        dest.writeInt(mBasestationId);
        dest.writeInt(mLongitude);
        dest.writeInt(mLatitude);
        dest.writeString(mAlphaLong);
        dest.writeString(mAlphaShort);
    }

    /** Construct from Parcel, type has already been processed */
    private CellIdentityCdma(Parcel in) {
        super(TAG, TYPE_CDMA, in);
        mNetworkId = in.readInt();
        mSystemId = in.readInt();
        mBasestationId = in.readInt();
        mLongitude = in.readInt();
        mLatitude = in.readInt();
        mAlphaLong = in.readString();
        mAlphaShort = in.readString();

        if (DBG) log(toString());
    }

    /** Implement the Parcelable interface */
    @SuppressWarnings("hiding")
    public static final Creator<CellIdentityCdma> CREATOR =
            new Creator<CellIdentityCdma>() {
        @Override
        public CellIdentityCdma createFromParcel(Parcel in) {
            in.readInt();   // skip
            return createFromParcelBody(in);
        }

        @Override
        public CellIdentityCdma[] newArray(int size) {
            return new CellIdentityCdma[size];
        }
    };

    /** @hide */
    protected static CellIdentityCdma createFromParcelBody(Parcel in) {
        return new CellIdentityCdma(in);
    }
}
