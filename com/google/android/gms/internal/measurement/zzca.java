package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzci;
import com.google.android.gms.internal.measurement.zzhv;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@17.5.0 */
public final class zzca {

    /* compiled from: com.google.android.gms:play-services-measurement@@17.5.0 */
    public static final class zza extends zzhv<zza, C3081zza> implements zzji {
        /* access modifiers changed from: private */
        public static final zza zzh;
        private static volatile zzjp<zza> zzi;
        private int zzc;
        private String zzd = "";
        private boolean zze;
        private boolean zzf;
        private int zzg;

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzca$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@17.5.0 */
        public static final class C3081zza extends zzhv.zzb<zza, C3081zza> implements zzji {
            private C3081zza() {
                super(zza.zzh);
            }

            public final String zza() {
                return ((zza) this.zza).zza();
            }

            public final C3081zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(str);
                return this;
            }

            public final boolean zzb() {
                return ((zza) this.zza).zzb();
            }

            public final boolean zzc() {
                return ((zza) this.zza).zzc();
            }

            public final boolean zzd() {
                return ((zza) this.zza).zzd();
            }

            public final int zze() {
                return ((zza) this.zza).zze();
            }

            /* synthetic */ C3081zza(zzcc zzcc) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        public final boolean zzb() {
            return this.zze;
        }

        public final boolean zzc() {
            return this.zzf;
        }

        public final boolean zzd() {
            return (this.zzc & 8) != 0;
        }

        public final int zze() {
            return this.zzg;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C3081zza((zzcc) null);
                case 3:
                    return zza((zzjg) zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjp<zza> zzjp = zzi;
                    if (zzjp == null) {
                        synchronized (zza.class) {
                            zzjp = zzi;
                            if (zzjp == null) {
                                zzjp = new zzhv.zza<>(zzh);
                                zzi = zzjp;
                            }
                        }
                    }
                    return zzjp;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zza = new zza();
            zzh = zza;
            zzhv.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.5.0 */
    public static final class zzb extends zzhv<zzb, zza> implements zzji {
        /* access modifiers changed from: private */
        public static final zzb zzm;
        private static volatile zzjp<zzb> zzn;
        private int zzc;
        private long zzd;
        private String zze = "";
        private int zzf;
        private zzid<zzc> zzg = zzbs();
        private zzid<zza> zzh = zzbs();
        private zzid<zzbv.zza> zzi = zzbs();
        private String zzj = "";
        private boolean zzk;
        private zzid<zzci.zza> zzl = zzbs();

        private zzb() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.5.0 */
        public static final class zza extends zzhv.zzb<zzb, zza> implements zzji {
            private zza() {
                super(zzb.zzm);
            }

            public final int zza() {
                return ((zzb) this.zza).zzf();
            }

            public final zza zza(int i) {
                return ((zzb) this.zza).zza(i);
            }

            public final zza zza(int i, zza.C3081zza zza) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i, (zza) ((zzhv) zza.zzy()));
                return this;
            }

            public final List<zzbv.zza> zzb() {
                return Collections.unmodifiableList(((zzb) this.zza).zzg());
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zzl();
                return this;
            }

            /* synthetic */ zza(zzcc zzcc) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzb() {
            return this.zzd;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        public final List<zzc> zze() {
            return this.zzg;
        }

        public final int zzf() {
            return this.zzh.size();
        }

        public final zza zza(int i) {
            return (zza) this.zzh.get(i);
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zza zza2) {
            zza2.getClass();
            zzid<zza> zzid = this.zzh;
            if (!zzid.zza()) {
                this.zzh = zzhv.zza(zzid);
            }
            this.zzh.set(i, zza2);
        }

        public final List<zzbv.zza> zzg() {
            return this.zzi;
        }

        /* access modifiers changed from: private */
        public final void zzl() {
            this.zzi = zzbs();
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public static zza zzi() {
            return (zza) zzm.zzbm();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzcc) null);
                case 3:
                    return zza((zzjg) zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0004\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzc.class, "zzh", zza.class, "zzi", zzbv.zza.class, "zzj", "zzk", "zzl", zzci.zza.class});
                case 4:
                    return zzm;
                case 5:
                    zzjp<zzb> zzjp = zzn;
                    if (zzjp == null) {
                        synchronized (zzb.class) {
                            zzjp = zzn;
                            if (zzjp == null) {
                                zzjp = new zzhv.zza<>(zzm);
                                zzn = zzjp;
                            }
                        }
                    }
                    return zzjp;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzb zzj() {
            return zzm;
        }

        static {
            zzb zzb = new zzb();
            zzm = zzb;
            zzhv.zza(zzb.class, zzb);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.5.0 */
    public static final class zzc extends zzhv<zzc, zza> implements zzji {
        /* access modifiers changed from: private */
        public static final zzc zzf;
        private static volatile zzjp<zzc> zzg;
        private int zzc;
        private String zzd = "";
        private String zze = "";

        private zzc() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.5.0 */
        public static final class zza extends zzhv.zzb<zzc, zza> implements zzji {
            private zza() {
                super(zzc.zzf);
            }

            /* synthetic */ zza(zzcc zzcc) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        public final String zzb() {
            return this.zze;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzcc) null);
                case 3:
                    return zza((zzjg) zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzjp<zzc> zzjp = zzg;
                    if (zzjp == null) {
                        synchronized (zzc.class) {
                            zzjp = zzg;
                            if (zzjp == null) {
                                zzjp = new zzhv.zza<>(zzf);
                                zzg = zzjp;
                            }
                        }
                    }
                    return zzjp;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzc2 = new zzc();
            zzf = zzc2;
            zzhv.zza(zzc.class, zzc2);
        }
    }
}
