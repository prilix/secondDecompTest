package com.jch.racWiFi.gooleMaps;

import android.content.res.AssetManager;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;

public class CustomMapTileProvider implements TileProvider {
    private static final int BUFFER_SIZE = 16384;
    private static final int TILE_HEIGHT = 256;
    private static final int TILE_WIDTH = 256;
    private AssetManager mAssets;

    public CustomMapTileProvider(AssetManager assetManager) {
        this.mAssets = assetManager;
    }

    public Tile getTile(int i, int i2, int i3) {
        byte[] readTileImage = readTileImage(i, i2, i3);
        if (readTileImage == null) {
            return null;
        }
        return new Tile(256, 256, readTileImage);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:6|7|(2:8|(1:10)(1:66))|11|(2:13|14)|15|16|17) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002c */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0048 A[SYNTHETIC, Splitter:B:34:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x004f A[SYNTHETIC, Splitter:B:38:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x005b A[SYNTHETIC, Splitter:B:47:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0062 A[SYNTHETIC, Splitter:B:51:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x006a A[SYNTHETIC, Splitter:B:58:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0071 A[SYNTHETIC, Splitter:B:62:0x0071] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0043=Splitter:B:31:0x0043, B:44:0x0056=Splitter:B:44:0x0056} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] readTileImage(int r6, int r7, int r8) {
        /*
            r5 = this;
            r0 = 0
            android.content.res.AssetManager r1 = r5.mAssets     // Catch:{ IOException -> 0x0053, OutOfMemoryError -> 0x0040, all -> 0x003d }
            java.lang.String r6 = r5.getTileFilename(r6, r7, r8)     // Catch:{ IOException -> 0x0053, OutOfMemoryError -> 0x0040, all -> 0x003d }
            java.io.InputStream r6 = r1.open(r6)     // Catch:{ IOException -> 0x0053, OutOfMemoryError -> 0x0040, all -> 0x003d }
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x003a, OutOfMemoryError -> 0x0037, all -> 0x0034 }
            r7.<init>()     // Catch:{ IOException -> 0x003a, OutOfMemoryError -> 0x0037, all -> 0x0034 }
            r8 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r8]     // Catch:{ IOException -> 0x0032, OutOfMemoryError -> 0x0030 }
        L_0x0014:
            r2 = 0
            int r3 = r6.read(r1, r2, r8)     // Catch:{ IOException -> 0x0032, OutOfMemoryError -> 0x0030 }
            r4 = -1
            if (r3 == r4) goto L_0x0020
            r7.write(r1, r2, r3)     // Catch:{ IOException -> 0x0032, OutOfMemoryError -> 0x0030 }
            goto L_0x0014
        L_0x0020:
            r7.flush()     // Catch:{ IOException -> 0x0032, OutOfMemoryError -> 0x0030 }
            byte[] r8 = r7.toByteArray()     // Catch:{ IOException -> 0x0032, OutOfMemoryError -> 0x0030 }
            if (r6 == 0) goto L_0x002c
            r6.close()     // Catch:{ Exception -> 0x002c }
        L_0x002c:
            r7.close()     // Catch:{ Exception -> 0x002f }
        L_0x002f:
            return r8
        L_0x0030:
            r8 = move-exception
            goto L_0x0043
        L_0x0032:
            r8 = move-exception
            goto L_0x0056
        L_0x0034:
            r8 = move-exception
            r7 = r0
            goto L_0x0067
        L_0x0037:
            r8 = move-exception
            r7 = r0
            goto L_0x0043
        L_0x003a:
            r8 = move-exception
            r7 = r0
            goto L_0x0056
        L_0x003d:
            r8 = move-exception
            r7 = r0
            goto L_0x0068
        L_0x0040:
            r8 = move-exception
            r6 = r0
            r7 = r6
        L_0x0043:
            r8.printStackTrace()     // Catch:{ all -> 0x0066 }
            if (r6 == 0) goto L_0x004d
            r6.close()     // Catch:{ Exception -> 0x004c }
            goto L_0x004d
        L_0x004c:
        L_0x004d:
            if (r7 == 0) goto L_0x0052
            r7.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0052:
            return r0
        L_0x0053:
            r8 = move-exception
            r6 = r0
            r7 = r6
        L_0x0056:
            r8.printStackTrace()     // Catch:{ all -> 0x0066 }
            if (r6 == 0) goto L_0x0060
            r6.close()     // Catch:{ Exception -> 0x005f }
            goto L_0x0060
        L_0x005f:
        L_0x0060:
            if (r7 == 0) goto L_0x0065
            r7.close()     // Catch:{ Exception -> 0x0065 }
        L_0x0065:
            return r0
        L_0x0066:
            r8 = move-exception
        L_0x0067:
            r0 = r6
        L_0x0068:
            if (r0 == 0) goto L_0x006f
            r0.close()     // Catch:{ Exception -> 0x006e }
            goto L_0x006f
        L_0x006e:
        L_0x006f:
            if (r7 == 0) goto L_0x0074
            r7.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0074:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.gooleMaps.CustomMapTileProvider.readTileImage(int, int, int):byte[]");
    }

    private String getTileFilename(int i, int i2, int i3) {
        return "map/" + i3 + '/' + i + '/' + i2 + ".png";
    }
}
