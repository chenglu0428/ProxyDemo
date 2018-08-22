package com.chenglu.learning.ProxyDemo.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProxySelectorTest {
	public void init() {
		ProxySelector.setDefault(new ProxySelector() {
			
			@Override
			public List<Proxy> select(URI uri) {
				List<Proxy> list = new ArrayList<Proxy>();
				list.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.19.69.113", 10800)));
				return list;
			}
			
			@Override
			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
				System.out.println(uri + " cannot connect.");
			}
		});
	}
	
	public static void main(String[] args) {
		new ProxySelectorTest().init();
		String addr = "http://uatoa.unionpayintl.com/unionpayoa/application/qsfymx.nsf/LiquidationCreateProcess?WSDL";
		Scanner scanner = null;
		try {
			URL url = new URL(addr);
			URLConnection connection = url.openConnection();
			scanner = new Scanner(connection.getInputStream());
			StringBuilder sb = new StringBuilder();
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append("\n");
			}
			System.out.println(sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
		}
	}
}
