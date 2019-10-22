package com.dib.wsclient;

import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSocketFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class CustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
	

	public CustomClientHttpRequestFactory() {
	}
	

	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
	    try {
	        if (!(connection instanceof HttpsURLConnection)) {
	            throw new RuntimeException("An instance of HttpsURLConnection is expected");
	        }

	        final HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;

	        TrustManager[] trustAllCerts = new TrustManager[]{
	                new X509TrustManager() {
	                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                    }

						@Override
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
								 {
						
						}

						@Override
						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
								{
		
						}

	                }
	        };
	        SSLContext sslContext = SSLContext.getInstance("TLS");
	        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
	        final SSLSocketFactory factory = sslContext.getSocketFactory(); 
	        final SSLSocketFactory wrappedFactory = new CustomSSLSocketFactory(factory, new String[] {"TLSv1"});
	        httpsConnection.setSSLSocketFactory(wrappedFactory);
	        httpsConnection.setHostnameVerifier((hostname, session) -> true);

	        super.prepareConnection(httpsConnection, httpMethod);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	


}
