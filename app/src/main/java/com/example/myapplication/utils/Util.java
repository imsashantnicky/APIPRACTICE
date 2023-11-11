package com.example.myapplication.utils;

import android.app.Application;
import android.util.Log;
import com.example.myapplication.network.Network;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Util extends Application {

    public static Network apinetwork;

    //Find the IP Address of mobile
    public static String getLocalIpAddress() {
        String ipAddress = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress.getAddress().length == 4) {
                        ipAddress = inetAddress.getHostAddress();
                        Log.d("ipAddress", ipAddress);
                        return ipAddress;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
