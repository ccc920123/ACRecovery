package com.example.administrator.outchecknewstandard.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.example.administrator.outchecknewstandard.callback.WebServiceCallBack;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WebserviceInit {
    WebServiceCallBack callBackInterFace;
    Context context;
    String endPoint = "";
    SoapSerializationEnvelope envelope;
    Exception ex = null;
    String methodName = "";
    Handler myHandler;
    String nameSpace = "";
    String soapAction = "";
    SoapObject soapObject;
    String strXml = "";
    HttpTransportSE transport;
    int version = 100;

    public WebserviceInit setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
        return this;
    }

    public WebserviceInit setContext(Context context) {
        this.context = context;
        return this;
    }

    public WebserviceInit setMethodName(String methodName) {
        this.methodName = methodName;
        this.soapObject = new SoapObject(this.nameSpace, methodName);
        this.envelope = new SoapSerializationEnvelope(this.version);
        this.envelope.dotNet = false;
        this.envelope.bodyOut = this.soapObject;
        this.envelope.setOutputSoapObject(this.soapObject);
        return this;
    }

    public WebserviceInit setEndPoint(String endPoint) {
        this.endPoint = endPoint;
        this.transport = new HttpTransportSE(endPoint);
        this.transport.debug = false;
        return this;
    }

    public WebserviceInit setSoapSerializationVersion(int version) {
        this.version = version;
        return this;
    }

    public WebserviceInit setSoapAction(String soapAction) {
        this.soapAction = soapAction;
        return this;
    }

    public WebserviceInit addParameter(String key, String value) {
        this.soapObject.addProperty(key, value);
        return this;
    }

    public WebserviceInit setTransportDebug(boolean boo) {
        this.transport.debug = boo;
        return this;
    }

    public WebserviceInit setEnvelopeDotNet(boolean boo) {
        this.envelope.dotNet = boo;
        return this;
    }

    public WebserviceInit setCallBackData(final WebServiceCallBack callBackInterFace) {
        this.callBackInterFace = callBackInterFace;
        new Thread() {
            public void run() {
                super.run();
                try {
                    WebserviceInit.this.transport.call(WebserviceInit.this.soapAction, WebserviceInit.this.envelope);
                    Object object = WebserviceInit.this.envelope.getResponse();
                    SoapObject sb = (SoapObject) WebserviceInit.this.envelope.bodyIn;
                    WebserviceInit.this.strXml = sb.getProperty(0).toString();
                    Message message = new Message();
                    message.arg1 = 1;
                    WebserviceInit.this.myHandler.sendMessage(message);
                } catch (IOException e) {
                    WebserviceInit.this.ex = e;
                    Looper.prepare();
                    callBackInterFace.callBackData(WebserviceInit.this.ex, "");
                    Looper.loop();
                } catch (XmlPullParserException e2) {
                    WebserviceInit.this.ex = e2;
                    Looper.prepare();
                    callBackInterFace.callBackData(WebserviceInit.this.ex, "");
                    Looper.loop();
                } catch (Exception e3) {
                    WebserviceInit.this.ex = e3;
                    Looper.prepare();
                    callBackInterFace.callBackData(WebserviceInit.this.ex, "");
                    Looper.loop();
                }
            }
        }.start();
        this.myHandler = new Handler(this.context.getMainLooper()) {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == 1) {
                    String strout = "";
                    if (WebserviceInit.this.strXml == null || WebserviceInit.this.strXml == "") {
                        strout = "101";
                    } else if (WebserviceInit.getEncoding(WebserviceInit.this.strXml).equals("UTF-8")) {
                        strout = WebserviceInit.utf8Togb2312(WebserviceInit.this.strXml);
                    } else {
                        strout = WebserviceInit.this.strXml;
                    }
                    callBackInterFace.callBackData(WebserviceInit.this.ex, strout);
                }
            }
        };
        return this;
    }

    public static String getURLDecoderString(String str) {
        String result = "";
        if (str == null) {
            return "";
        }
        try {
            result = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception e) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception e2) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception e3) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception e4) {
        }
        return "";
    }

    public static String utf8Togb2312(String str) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            switch (c) {
                case '%':
                    try {
                        sb.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
                        i += 2;
                        break;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException();
                    }
                case '+':
                    sb.append(' ');
                    break;
                default:
                    sb.append(c);
                    break;
            }
            i++;
        }
        try {
            return new String(sb.toString().getBytes("8859_1"), "UTF-8");
        } catch (Exception e2) {
            return null;
        }
    }
}
