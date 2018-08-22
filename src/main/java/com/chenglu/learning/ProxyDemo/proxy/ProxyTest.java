package com.chenglu.learning.ProxyDemo.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ProxyTest {
	public static void main(String[] args) {
		String addr = "http://uatoa.unionpayintl.com/unionpayoa/application/qsfymx.nsf/LiquidationCreateProcess?WSDL";
		Scanner scan = null;
		try {
			URL url = new URL(addr);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.19.69.113", 10800));
			URLConnection conn = url.openConnection(proxy);
			
			StringBuilder sb = new StringBuilder();
			scan = new Scanner(conn.getInputStream());
			while (scan.hasNextLine()) {
				sb.append(scan.nextLine()).append("\n");
			}
			System.out.println(sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
				scan = null;
			}
		}
		
	}
}
