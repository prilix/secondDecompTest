package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil;

import android.os.Handler;
import androidx.exifinterface.media.ExifInterface;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.C1662R2;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PairingViaUDP {
    /* access modifiers changed from: private */
    public int cmdSequenceNumber = -1;
    /* access modifiers changed from: private */

    /* renamed from: ds */
    public DatagramSocket f477ds;
    /* access modifiers changed from: private */
    public String finishCmd;
    private Handler handler = new Handler();
    /* access modifiers changed from: private */
    public OnPairingStatus onPairingStatus;
    /* access modifiers changed from: private */
    public String passwordCmd;
    private String passwordHomeRouter;
    private boolean socketCreated = false;
    /* access modifiers changed from: private */
    public String ssidCmd;
    private String ssidHomeRouter;
    private Thread thread;

    public interface OnPairingStatus {
        void onPairingCommandSequenceComplete();

        void onPasswordAckReceived();

        void onSsidAckReceived();

        void onWrongAckReceived();
    }

    public PairingViaUDP(WiFiCredentialModel wiFiCredentialModel, WiFiCredentialModel wiFiCredentialModel2, OnPairingStatus onPairingStatus2) {
        this.ssidHomeRouter = wiFiCredentialModel2.getSsid();
        this.passwordHomeRouter = wiFiCredentialModel2.getPassword();
        this.onPairingStatus = onPairingStatus2;
        int length = this.ssidHomeRouter.getBytes().length;
        String valueOf = String.valueOf(length);
        if (length <= 9) {
            valueOf = 0 + valueOf;
        }
        this.ssidCmd = "01" + valueOf + this.ssidHomeRouter;
        int length2 = this.passwordHomeRouter.getBytes().length;
        String valueOf2 = String.valueOf(length2);
        if (length2 <= 9) {
            valueOf2 = 0 + valueOf2;
        }
        this.passwordCmd = "02" + valueOf2 + this.passwordHomeRouter;
        this.finishCmd = "0400";
    }

    public void startParing() {
        if (this.thread == null) {
            Thread thread2 = new Thread(new Runnable() {
                public void run() {
                    try {
                        InetAddress byName = InetAddress.getByName("192.168.1.1");
                        PairingViaUDP.this.f477ds = new DatagramSocket();
                        PairingViaUDP.this.f477ds.connect(byName, C1662R2.C1665id.text_view_enter_otp_sub_title_email);
                        byte[] bytes = PairingViaUDP.this.ssidCmd.getBytes();
                        PairingViaUDP.this.f477ds.send(new DatagramPacket(bytes, bytes.length, byName, C1662R2.C1665id.text_view_enter_otp_sub_title_email));
                        Logger.m49i("UDP_RESPONSE_TIME", String.valueOf(System.nanoTime()));
                        PairingViaUDP pairingViaUDP = PairingViaUDP.this;
                        pairingViaUDP.cmdSequenceNumber = pairingViaUDP.cmdSequenceNumber + 1;
                        byte[] bArr = new byte[50];
                        DatagramPacket datagramPacket = new DatagramPacket(bArr, 50);
                        PairingViaUDP.this.f477ds.receive(datagramPacket);
                        Logger.m49i("UDP_RESPONSE_TIME", String.valueOf(System.nanoTime()));
                        String str = new String(bArr, 0, datagramPacket.getLength());
                        Logger.m49i("receive", String.valueOf(str.length()));
                        if (!str.startsWith("03")) {
                            if (!str.startsWith(ExifInterface.GPS_MEASUREMENT_3D)) {
                                PairingViaUDP.this.onPairingStatus.onWrongAckReceived();
                                PairingViaUDP.this.f477ds.close();
                                PairingViaUDP.this.f477ds = null;
                                return;
                            }
                        }
                        PairingViaUDP.this.onPairingStatus.onSsidAckReceived();
                        Thread.sleep(2000);
                        byte[] bytes2 = PairingViaUDP.this.passwordCmd.getBytes();
                        PairingViaUDP.this.f477ds.send(new DatagramPacket(bytes2, bytes2.length, byName, C1662R2.C1665id.text_view_enter_otp_sub_title_email));
                        Logger.m49i("UDP_RESPONSE_TIME", String.valueOf(System.nanoTime()));
                        PairingViaUDP pairingViaUDP2 = PairingViaUDP.this;
                        pairingViaUDP2.cmdSequenceNumber = pairingViaUDP2.cmdSequenceNumber + 1;
                        byte[] bArr2 = new byte[50];
                        DatagramPacket datagramPacket2 = new DatagramPacket(bArr2, 50);
                        PairingViaUDP.this.f477ds.receive(datagramPacket2);
                        Logger.m49i("UDP_RESPONSE_TIME", String.valueOf(System.nanoTime()));
                        String str2 = new String(bArr2, 0, datagramPacket2.getLength());
                        Logger.m49i("receive", String.valueOf(str2.length()));
                        if (!str2.startsWith("03")) {
                            if (!str2.startsWith(ExifInterface.GPS_MEASUREMENT_3D)) {
                                PairingViaUDP.this.onPairingStatus.onWrongAckReceived();
                                PairingViaUDP.this.f477ds.close();
                                PairingViaUDP.this.f477ds = null;
                                return;
                            }
                        }
                        PairingViaUDP.this.onPairingStatus.onPasswordAckReceived();
                        Thread.sleep(2000);
                        byte[] bytes3 = PairingViaUDP.this.finishCmd.getBytes();
                        PairingViaUDP.this.f477ds.send(new DatagramPacket(bytes3, bytes3.length, byName, C1662R2.C1665id.text_view_enter_otp_sub_title_email));
                        Logger.m49i("UDP_RESPONSE_TIME", String.valueOf(System.nanoTime()));
                        PairingViaUDP pairingViaUDP3 = PairingViaUDP.this;
                        pairingViaUDP3.cmdSequenceNumber = pairingViaUDP3.cmdSequenceNumber + 1;
                        PairingViaUDP.this.onPairingStatus.onPairingCommandSequenceComplete();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        PairingViaUDP.this.f477ds.close();
                        PairingViaUDP.this.f477ds = null;
                        throw th;
                    }
                    PairingViaUDP.this.f477ds.close();
                    PairingViaUDP.this.f477ds = null;
                }
            });
            this.thread = thread2;
            thread2.setPriority(10);
            this.thread.start();
        }
    }

    public void interuptParing() {
        if (this.thread != null) {
            this.f477ds.close();
            this.thread.interrupt();
        }
    }
}
