package com.yellowcong.baidu.utils;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 创建日期:2018年1月14日<br/>
 * 创建时间:下午4:46:33<br/>
 * 创建者 :yellowcong<br/>
 * 机能概要:用来发送请求获取百度的token
 */
public class TrustAnyTrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] {};
	}
	
	static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
		
	}
	
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午4:52:51<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:
	 * @param url 访问呢的目标地址
	 * @return
	 * @throws Exception
	 */
	public static String connect(String url) throws Exception {
		InputStream in = null;
		OutputStream out = null;
		byte[] buffer = new byte[4096];
		String str_return = "";
		try {
			// URL console = new URL(url);
			URL console = new URL(new String(url.getBytes("utf-8")));

			HttpURLConnection conn = (HttpURLConnection) console.openConnection();
			// 如果是https
			if (conn instanceof HttpsURLConnection) {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
				((HttpsURLConnection) conn).setSSLSocketFactory(sc.getSocketFactory());
				((HttpsURLConnection) conn).setHostnameVerifier(new TrustAnyHostnameVerifier());
			}
			// conn.setRequestProperty("Content-type", "text/html");
			// conn.setRequestProperty("Accept-Charset", "GBK");
			// conn.setRequestProperty("contentType", "GBK");
			// conn.setRequestMethod("POST");
			// conn.setDoOutput(true);
			// conn.setRequestProperty("User-Agent", "directclient");
			// PrintWriter outdate = new PrintWriter(new
			// OutputStreamWriter(conn.getOutputStream(),"utf-8"));
			// outdate.println(url);
			// outdate.close();
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";

			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return = str_return + new String(ret.getBytes("ISO-8859-1"), "utf-8");
				}
			}
			conn.disconnect();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		return str_return;
	}
}