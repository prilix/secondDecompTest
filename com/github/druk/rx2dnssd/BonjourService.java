package com.github.druk.rx2dnssd;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BonjourService implements Parcelable {
    public static final Parcelable.Creator<BonjourService> CREATOR = new Parcelable.Creator<BonjourService>() {
        public BonjourService createFromParcel(Parcel parcel) {
            return new BonjourService(parcel);
        }

        public BonjourService[] newArray(int i) {
            return new BonjourService[i];
        }
    };
    public static final int LOST = 256;
    /* access modifiers changed from: private */
    public final Map<String, String> dnsRecords;
    /* access modifiers changed from: private */
    public final String domain;
    /* access modifiers changed from: private */
    public final int flags;
    /* access modifiers changed from: private */
    public final String hostname;
    /* access modifiers changed from: private */
    public final int ifIndex;
    /* access modifiers changed from: private */
    public final List<InetAddress> inetAddresses;
    /* access modifiers changed from: private */
    public final int port;
    /* access modifiers changed from: private */
    public final String regType;
    /* access modifiers changed from: private */
    public final String serviceName;

    public int describeContents() {
        return 0;
    }

    protected BonjourService(Builder builder) {
        this.flags = builder.flags;
        this.serviceName = builder.serviceName;
        this.regType = builder.regType;
        this.domain = builder.domain;
        this.ifIndex = builder.ifIndex;
        this.inetAddresses = Collections.unmodifiableList(builder.inetAddresses);
        this.dnsRecords = Collections.unmodifiableMap(builder.dnsRecords);
        this.hostname = builder.hostname;
        this.port = builder.port;
    }

    protected BonjourService(Parcel parcel) {
        this.flags = parcel.readInt();
        this.serviceName = parcel.readString();
        this.regType = parcel.readString();
        this.domain = parcel.readString();
        this.dnsRecords = readMap(parcel);
        this.inetAddresses = readAddresses(parcel);
        this.ifIndex = parcel.readInt();
        this.hostname = parcel.readString();
        this.port = parcel.readInt();
    }

    private static void writeAddresses(Parcel parcel, List<InetAddress> list) {
        if (list == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(list.size());
        for (InetAddress writeSerializable : list) {
            parcel.writeSerializable(writeSerializable);
        }
    }

    private static List<InetAddress> readAddresses(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readInt; i++) {
            arrayList.add((InetAddress) parcel.readSerializable());
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static void writeMap(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Map.Entry next : map.entrySet()) {
            parcel.writeString((String) next.getKey());
            parcel.writeString((String) next.getValue());
        }
    }

    private static Map<String, String> readMap(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public int getFlags() {
        return this.flags;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getRegType() {
        return this.regType;
    }

    public String getDomain() {
        return this.domain;
    }

    public int getIfIndex() {
        return this.ifIndex;
    }

    public String getHostname() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public Map<String, String> getTxtRecords() {
        return this.dnsRecords;
    }

    public Inet4Address getInet4Address() {
        for (InetAddress next : this.inetAddresses) {
            if (next instanceof Inet4Address) {
                return (Inet4Address) next;
            }
        }
        return null;
    }

    public Inet6Address getInet6Address() {
        for (InetAddress next : this.inetAddresses) {
            if (next instanceof Inet6Address) {
                return (Inet6Address) next;
            }
        }
        return null;
    }

    public List<InetAddress> getInetAddresses() {
        return this.inetAddresses;
    }

    public boolean isLost() {
        return (this.flags & 256) == 256;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BonjourService)) {
            return false;
        }
        BonjourService bonjourService = (BonjourService) obj;
        if (this.ifIndex != bonjourService.ifIndex) {
            return false;
        }
        String str = this.serviceName;
        if (str == null ? bonjourService.serviceName != null : !str.equals(bonjourService.serviceName)) {
            return false;
        }
        String str2 = this.regType;
        if (str2 == null ? bonjourService.regType != null : !str2.equals(bonjourService.regType)) {
            return false;
        }
        String str3 = this.domain;
        String str4 = bonjourService.domain;
        if (str3 != null) {
            if (!str3.equals(str4)) {
                return false;
            }
            return true;
        } else if (str4 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.serviceName;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.regType;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.domain;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.ifIndex;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.flags);
        parcel.writeString(this.serviceName);
        parcel.writeString(this.regType);
        parcel.writeString(this.domain);
        writeMap(parcel, this.dnsRecords);
        writeAddresses(parcel, this.inetAddresses);
        parcel.writeInt(this.ifIndex);
        parcel.writeString(this.hostname);
        parcel.writeInt(this.port);
    }

    public String toString() {
        return "BonjourService{flags=" + this.flags + ", domain='" + this.domain + '\'' + ", regType='" + this.regType + '\'' + ", serviceName='" + this.serviceName + '\'' + ", dnsRecords=" + this.dnsRecords + ", ifIndex=" + this.ifIndex + ", hostname='" + this.hostname + '\'' + ", port=" + this.port + '}';
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Map<String, String> dnsRecords = new HashMap();
        /* access modifiers changed from: private */
        public final String domain;
        /* access modifiers changed from: private */
        public final int flags;
        /* access modifiers changed from: private */
        public String hostname;
        /* access modifiers changed from: private */
        public final int ifIndex;
        /* access modifiers changed from: private */
        public List<InetAddress> inetAddresses = new ArrayList();
        /* access modifiers changed from: private */
        public int port;
        /* access modifiers changed from: private */
        public final String regType;
        /* access modifiers changed from: private */
        public final String serviceName;

        public Builder(int i, int i2, String str, String str2, String str3) {
            this.flags = i;
            this.serviceName = str;
            this.regType = str2;
            this.domain = str3;
            this.ifIndex = i2;
        }

        public Builder(BonjourService bonjourService) {
            this.flags = bonjourService.flags;
            this.serviceName = bonjourService.serviceName;
            this.regType = bonjourService.regType;
            this.domain = bonjourService.domain;
            this.ifIndex = bonjourService.ifIndex;
            this.dnsRecords = new HashMap(bonjourService.dnsRecords);
            this.inetAddresses = new ArrayList(bonjourService.inetAddresses);
            this.hostname = bonjourService.hostname;
            this.port = bonjourService.port;
        }

        public Builder hostname(String str) {
            this.hostname = str;
            return this;
        }

        public Builder port(int i) {
            this.port = i;
            return this;
        }

        public Builder dnsRecords(Map<String, String> map) {
            this.dnsRecords = map;
            return this;
        }

        public Builder inet4Address(Inet4Address inet4Address) {
            this.inetAddresses.add(inet4Address);
            return this;
        }

        public Builder inet6Address(Inet6Address inet6Address) {
            this.inetAddresses.add(inet6Address);
            return this;
        }

        public BonjourService build() {
            return new BonjourService(this);
        }

        public void inetAddress(InetAddress inetAddress) {
            this.inetAddresses.add(inetAddress);
        }
    }
}
