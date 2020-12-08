package com.example.administrator.outchecknewstandard.utils;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLTools implements Serializable {
    public static HashMap<String, String> getXmlHead(String xmldoc) {
        Map<String, String> map = new HashMap();
        try {
            Element lan = (Element) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement().getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            String message = lan.getElementsByTagName("message").item(0).getTextContent();
            map.put("message", message);
            map.put("rownum", lan.getElementsByTagName("rownum").item(0).getTextContent());
            System.out.println("返回数据-----" + WebserviceInit.getURLDecoderString(message));
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getXmlBodyW(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            String rownum = lan.getElementsByTagName("rownum").item(0).getTextContent();
            map.put("rownum", rownum);
            if (Integer.parseInt(rownum) > 0) {
                NodeList list2 = languages.getElementsByTagName("body");
                NodeList list3 = languages.getElementsByTagName("vehispara");
                Element lan2 = (Element) list2.item(0);
                map.put("cj", lan2.getElementsByTagName("cj").item(0).getTextContent());
                map.put("fj", lan2.getElementsByTagName("fj").item(0).getTextContent());
                map.put("cjhgl", lan2.getElementsByTagName("cjhgl").item(0).getTextContent());
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getXmlBodyt(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            String rownum = lan.getElementsByTagName("rownum").item(0).getTextContent();
            map.put("rownum", rownum);
            if (Integer.parseInt(rownum) > 0) {
                NodeList list2 = languages.getElementsByTagName("body");
                NodeList list3 = languages.getElementsByTagName("vehispara");
                String a = "0";
                for (int i = 0; i < list2.getLength(); i++) {
                    Element lan2 = (Element) list2.item(i);
                    map.put("jylsh" + a, lan2.getElementsByTagName("jylsh").item(0).getTextContent());
                    map.put("testtimes" + a, lan2.getElementsByTagName("testtimes").item(0).getTextContent());
                    map.put("tsno" + a, lan2.getElementsByTagName("tsno").item(0).getTextContent());
                    map.put("jcrq" + a, lan2.getElementsByTagName("jcrq").item(0).getTextContent());
                    map.put("license" + a, lan2.getElementsByTagName("license").item(0).getTextContent());
                    map.put("licensecode" + a, lan2.getElementsByTagName("licensecode").item(0).getTextContent());
                    map.put("result" + a, lan2.getElementsByTagName("result").item(0).getTextContent());
                    map.put("jclx" + a, lan2.getElementsByTagName("jclx").item(0).getTextContent());
                    map.put("testtype" + a, lan2.getElementsByTagName("testtype").item(0).getTextContent());
                    a = a + "" + i;
                }
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getSearchXmlBody(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            String rownum = lan.getElementsByTagName("rownum").item(0).getTextContent();
            map.put("rownum", rownum);
            NodeList list2 = languages.getElementsByTagName("body");
            if (Integer.parseInt(rownum) > 0) {
                NodeList list3 = languages.getElementsByTagName("vehispara");
                String a = "0";
                for (int i = 0; i < list2.getLength(); i++) {
                    Element lan2 = (Element) list2.item(i);
                    map.put("jylsh" + a, lan2.getElementsByTagName("jylsh").item(0).getTextContent());
                    map.put("testtimes" + a, lan2.getElementsByTagName("testtimes").item(0).getTextContent());
                    map.put("tsno" + a, lan2.getElementsByTagName("tsno").item(0).getTextContent());
                    map.put("jcrq" + a, lan2.getElementsByTagName("jcrq").item(0).getTextContent());
                    map.put("license" + a, lan2.getElementsByTagName("license").item(0).getTextContent());
                    map.put("licensecode" + a, lan2.getElementsByTagName("licensecode").item(0).getTextContent());
                    map.put("result" + a, lan2.getElementsByTagName("result").item(0).getTextContent());
                    map.put("shzt" + a, lan2.getElementsByTagName("shzt").item(0).getTextContent());
                    map.put("jclx" + a, lan2.getElementsByTagName("jclx").item(0).getTextContent());
                    map.put("testtype" + a, lan2.getElementsByTagName("testtype").item(0).getTextContent());
                    a = a + "" + i;
                }
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getDownloadXmlBody(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            map.put("rownum", lan.getElementsByTagName("rownum").item(0).getTextContent());
            NodeList list2 = languages.getElementsByTagName("body");
            if (list2.getLength() != 0) {
                NodeList list3 = languages.getElementsByTagName("vehispara");
                String a = "0";
                for (int i = 0; i < list2.getLength(); i++) {
                    Element lan2 = (Element) list2.item(i);
                    map.put("id" + a, lan2.getElementsByTagName("id").item(0).getTextContent());
                    map.put("mtitle" + a, lan2.getElementsByTagName("mtitle").item(0).getTextContent());
                    map.put("author" + a, lan2.getElementsByTagName("author").item(0).getTextContent());
                    map.put("pdate" + a, lan2.getElementsByTagName("pdate").item(0).getTextContent());
                    a = a + "" + i;
                }
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getDownloadDetailXmlBody(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            NodeList list2 = languages.getElementsByTagName("body");
            if (list2.getLength() != 0) {
                NodeList list3 = languages.getElementsByTagName("vehispara");
                Element lan2 = (Element) list2.item(0);
                map.put("mtitle", lan2.getElementsByTagName("mtitle").item(0).getTextContent());
                map.put("author", lan2.getElementsByTagName("author").item(0).getTextContent());
                map.put("pdate", lan2.getElementsByTagName("pdate").item(0).getTextContent());
                map.put("htitle", lan2.getElementsByTagName("htitle").item(0).getTextContent());
                map.put("jianjie", lan2.getElementsByTagName("jianjie").item(0).getTextContent());
                map.put("mbody", lan2.getElementsByTagName("mbody").item(0).getTextContent());
                map.put("adate", lan2.getElementsByTagName("adate").item(0).getTextContent());
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getVerifyXmlBody(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            NodeList list2 = languages.getElementsByTagName("body");
            if (list2.getLength() != 0) {
                NodeList list3 = languages.getElementsByTagName("vehispara");
                Element lan2 = (Element) list2.item(0);
                map.put("tsno", lan2.getElementsByTagName("tsno").item(0).getTextContent());
                map.put("jcrq", lan2.getElementsByTagName("jcrq").item(0).getTextContent());
                map.put("license", lan2.getElementsByTagName("license").item(0).getTextContent());
                map.put("result", lan2.getElementsByTagName("result").item(0).getTextContent());
                map.put("testno", lan2.getElementsByTagName("testno").item(0).getTextContent());
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getXmlPaiZ(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            String rownum = lan.getElementsByTagName("rownum").item(0).getTextContent();
            map.put("rownum", rownum);
            NodeList list2 = languages.getElementsByTagName("body");
            if (Integer.parseInt(rownum) != 0) {
                NodeList list3 = languages.getElementsByTagName("vehispara");
                String a = "0";
                for (int i = 0; i < list3.getLength(); i++) {
                    Element lan2 = (Element) list2.item(0);
                    map.put("itemname" + a, lan2.getElementsByTagName("itemname").item(i).getTextContent());
                    map.put("itemcode" + a, lan2.getElementsByTagName("itemcode").item(i).getTextContent());
                    map.put("codes", "1");
                    a = a + "" + i;
                }
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getXmlPaiZt(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            String rownum = lan.getElementsByTagName("rownum").item(0).getTextContent();
            map.put("rownum", rownum);
            NodeList list2 = languages.getElementsByTagName("body");
            int str = Integer.parseInt(rownum);
            if (list2.getLength() != 0) {
                NodeList list3 = languages.getElementsByTagName("vehispara");
                String a = "0";
                for (int i = 0; i < list3.getLength(); i++) {
                    Element lan2 = (Element) list2.item(0);
                    map.put("oi_value" + a, lan2.getElementsByTagName("oi_value").item(i).getTextContent());
                    map.put("oi_name" + a, lan2.getElementsByTagName("oi_name").item(i).getTextContent());
                    map.put("oi_code" + a, lan2.getElementsByTagName("oi_code").item(i).getTextContent());
                    map.put("codes", "1");
                    a = a + "" + i;
                }
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getXmlBodyView(String xmldoc) {
        System.out.println("返回数据-----" + xmldoc);
        Map<String, String> map = new HashMap();
        try {
            Element languages = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmldoc))).getDocumentElement();
            System.out.println(languages + "+languages===================---");
            Element lan = (Element) languages.getElementsByTagName("head").item(0);
            map.put("code", lan.getElementsByTagName("code").item(0).getTextContent());
            String rownum = lan.getElementsByTagName("rownum").item(0).getTextContent();
            map.put("rownum", rownum);
            map.put("message", lan.getElementsByTagName("message").item(0).getTextContent());
            NodeList list2 = languages.getElementsByTagName("body");
            if (Integer.parseInt(rownum) != 0) {
                NodeList list3 = languages.getElementsByTagName("h_data_base");
                Element lan2 = (Element) list2.item(0);
                map.put("jylsh", lan2.getElementsByTagName("jylsh").item(0).getTextContent());
                map.put("tsno", lan2.getElementsByTagName("tsno").item(0).getTextContent());
                map.put("jcrq", lan2.getElementsByTagName("jcrq").item(0).getTextContent());
                map.put("jcjsy", lan2.getElementsByTagName("jcjsy").item(0).getTextContent());
                map.put("jcczy", lan2.getElementsByTagName("jcczy").item(0).getTextContent());
                map.put("result", lan2.getElementsByTagName("result").item(0).getTextContent());
                map.put("testno", lan2.getElementsByTagName("testno").item(0).getTextContent());
                String vehicletype4 = lan2.getElementsByTagName("testtype").item(0).getTextContent();
                NodeList list4;
                Element lan4;
                if (vehicletype4.equals("1")) {
                    map.put("testtype", "双怠速法");
                    list4 = languages.getElementsByTagName("h_data_vtie");
                    lan4 = (Element) list2.item(1);
                    map.put("wd", lan4.getElementsByTagName("wd").item(0).getTextContent());
                    map.put("dqy", lan4.getElementsByTagName("dqy").item(0).getTextContent());
                    map.put("xdsd", lan4.getElementsByTagName("xdsd").item(0).getTextContent());
                    map.put("lambda", lan4.getElementsByTagName("lambda").item(0).getTextContent());
                    map.put("lambdajudge", lan4.getElementsByTagName("lambdajudge").item(0).getTextContent());
                    map.put("lscolimit", lan4.getElementsByTagName("lscolimit").item(0).getTextContent());
                    map.put("lscoresult", lan4.getElementsByTagName("lscoresult").item(0).getTextContent());
                    map.put("lscojudge", lan4.getElementsByTagName("lscojudge").item(0).getTextContent());
                    map.put("lshclimit", lan4.getElementsByTagName("lshclimit").item(0).getTextContent());
                    map.put("lshcresult", lan4.getElementsByTagName("lshcresult").item(0).getTextContent());
                    map.put("lshcjudge", lan4.getElementsByTagName("lshcjudge").item(0).getTextContent());
                    map.put("dszs", lan4.getElementsByTagName("dszs").item(0).getTextContent());
                    map.put("ddsjywd", lan4.getElementsByTagName("ddsjywd").item(0).getTextContent());
                    map.put("hscolimit", lan4.getElementsByTagName("hscolimit").item(0).getTextContent());
                    map.put("hscoresult", lan4.getElementsByTagName("hscoresult").item(0).getTextContent());
                    map.put("hscojudge", lan4.getElementsByTagName("hscojudge").item(0).getTextContent());
                    map.put("hshcresult", lan4.getElementsByTagName("hshcresult").item(0).getTextContent());
                    map.put("hshclimit", lan4.getElementsByTagName("hshclimit").item(0).getTextContent());
                    map.put("hshcjudge", lan4.getElementsByTagName("hshcjudge").item(0).getTextContent());
                    map.put("gdszs", lan4.getElementsByTagName("gdszs").item(0).getTextContent());
                    map.put("gdsjywd", lan4.getElementsByTagName("gdsjywd").item(0).getTextContent());
                } else if (vehicletype4.equals("2")) {
                    map.put("testtype", "稳态工况法");
                    list4 = languages.getElementsByTagName("h_data_vasm");
                    lan4 = (Element) list2.item(1);
                    map.put("hc5025limit", lan4.getElementsByTagName("hc5025limit").item(0).getTextContent());
                    map.put("hc5025", lan4.getElementsByTagName("hc5025").item(0).getTextContent());
                    map.put("hc5025judge", lan4.getElementsByTagName("hc5025judge").item(0).getTextContent());
                    map.put("co5025limit", lan4.getElementsByTagName("co5025limit").item(0).getTextContent());
                    map.put("co5025", lan4.getElementsByTagName("co5025").item(0).getTextContent());
                    map.put("co5025judge", lan4.getElementsByTagName("co5025judge").item(0).getTextContent());
                    map.put("no5025limit", lan4.getElementsByTagName("no5025limit").item(0).getTextContent());
                    map.put("no5025", lan4.getElementsByTagName("no5025").item(0).getTextContent());
                    map.put("no5025judge", lan4.getElementsByTagName("no5025judge").item(0).getTextContent());
                    map.put("fdjzs5025", lan4.getElementsByTagName("fdjzs5025").item(0).getTextContent());
                    map.put("fdjyw5025", lan4.getElementsByTagName("fdjyw5025").item(0).getTextContent());
                    map.put("hc2540limit", lan4.getElementsByTagName("hc2540limit").item(0).getTextContent());
                    map.put("hc2540", lan4.getElementsByTagName("hc2540").item(0).getTextContent());
                    map.put("hc2540judge", lan4.getElementsByTagName("hc2540judge").item(0).getTextContent());
                    map.put("co2540limit", lan4.getElementsByTagName("co2540limit").item(0).getTextContent());
                    map.put("co2540", lan4.getElementsByTagName("co2540").item(0).getTextContent());
                    map.put("co2540judge", lan4.getElementsByTagName("co2540judge").item(0).getTextContent());
                    map.put("no2540limit", lan4.getElementsByTagName("no2540limit").item(0).getTextContent());
                    map.put("no2540", lan4.getElementsByTagName("no2540").item(0).getTextContent());
                    map.put("no2540judge", lan4.getElementsByTagName("no2540judge").item(0).getTextContent());
                    map.put("fdjzs2540", lan4.getElementsByTagName("fdjzs2540").item(0).getTextContent());
                    map.put("fdjyw2540", lan4.getElementsByTagName("fdjyw2540").item(0).getTextContent());
                    map.put("wd", lan4.getElementsByTagName("wd").item(0).getTextContent());
                    map.put("dqy", lan4.getElementsByTagName("dqy").item(0).getTextContent());
                    map.put("xdsd", lan4.getElementsByTagName("xdsd").item(0).getTextContent());
                } else if (vehicletype4.equals("4")) {
                    map.put("testtype", "加载减速法");
                    list4 = languages.getElementsByTagName("h_data_vlug");
                    lan4 = (Element) list2.item(1);
                    map.put("maxpower", lan4.getElementsByTagName("maxpower").item(0).getTextContent());
                    map.put("maxpowerlimit", lan4.getElementsByTagName("maxpowerlimit").item(0).getTextContent());
                    map.put("zdlbglzs", lan4.getElementsByTagName("zdlbglzs").item(0).getTextContent());
                    map.put("zdlbgljudge", lan4.getElementsByTagName("zdlbgljudge").item(0).getTextContent());
                    map.put("smokeklimit", lan4.getElementsByTagName("smokeklimit").item(0).getTextContent());
                    map.put("ydjudge", lan4.getElementsByTagName("ydjudge").item(0).getTextContent());
                    map.put("k100", lan4.getElementsByTagName("k100").item(0).getTextContent());
                    map.put("k90", lan4.getElementsByTagName("k90").item(0).getTextContent());
                    map.put("k80", lan4.getElementsByTagName("k80").item(0).getTextContent());
                    map.put("wd", lan4.getElementsByTagName("wd").item(0).getTextContent());
                    map.put("dqy", lan4.getElementsByTagName("dqy").item(0).getTextContent());
                    map.put("xdsd", lan4.getElementsByTagName("xdsd").item(0).getTextContent());
                } else if (vehicletype4 == "3") {
                    map.put("testtype", "简易瞬态工况法1");
                    list4 = languages.getElementsByTagName("h_data_vmas");
                    lan4 = (Element) list2.item(1);
                    map.put("wd", lan4.getElementsByTagName("wd").item(0).getTextContent());
                    map.put("dqy", lan4.getElementsByTagName("dqy").item(0).getTextContent());
                    map.put("xdsd", lan4.getElementsByTagName("xdsd").item(0).getTextContent());
                    map.put("colimit", lan4.getElementsByTagName("colimit").item(0).getTextContent());
                    map.put("co", lan4.getElementsByTagName("co").item(0).getTextContent());
                    map.put("cojudge", lan4.getElementsByTagName("cojudge").item(0).getTextContent());
                    map.put("hclimit", lan4.getElementsByTagName("hclimit").item(0).getTextContent());
                    map.put("hc", lan4.getElementsByTagName("hc").item(0).getTextContent());
                    map.put("hcjudge", lan4.getElementsByTagName("hcjudge").item(0).getTextContent());
                    map.put("noxlimit", lan4.getElementsByTagName("noxlimit").item(0).getTextContent());
                    map.put("nox", lan4.getElementsByTagName("nox").item(0).getTextContent());
                    map.put("noxjudge", lan4.getElementsByTagName("noxjudge").item(0).getTextContent());
                } else if (vehicletype4 == "3-2") {
                    map.put("testtype", "简易瞬态工况法2");
                    list4 = languages.getElementsByTagName("h_data_vmas");
                    lan4 = (Element) list2.item(1);
                    map.put("wd", lan4.getElementsByTagName("wd").item(0).getTextContent());
                    map.put("dqy", lan4.getElementsByTagName("dqy").item(0).getTextContent());
                    map.put("xdsd", lan4.getElementsByTagName("xdsd").item(0).getTextContent());
                    map.put("colimit", lan4.getElementsByTagName("colimit").item(0).getTextContent());
                    map.put("co", lan4.getElementsByTagName("co").item(0).getTextContent());
                    map.put("cojudge", lan4.getElementsByTagName("cojudge").item(0).getTextContent());
                    map.put("hcnoxlimit", lan4.getElementsByTagName("hcnoxlimit").item(0).getTextContent());
                    map.put("hcnox", lan4.getElementsByTagName("hcnox").item(0).getTextContent());
                    map.put("hcnoxjudge", lan4.getElementsByTagName("hcnoxjudge").item(0).getTextContent());
                } else if (vehicletype4 == "5") {
                    map.put("testtype", "不透光烟度法");
                    list4 = languages.getElementsByTagName("h_data_vflm");
                    lan4 = (Element) list2.item(1);
                    map.put("wd", lan4.getElementsByTagName("wd").item(0).getTextContent());
                    map.put("dqy", lan4.getElementsByTagName("dqy").item(0).getTextContent());
                    map.put("xdsd", lan4.getElementsByTagName("xdsd").item(0).getTextContent());
                    map.put("idlerev", lan4.getElementsByTagName("idlerev").item(0).getTextContent());
                    map.put("smokek3", lan4.getElementsByTagName("smokek3").item(0).getTextContent());
                    map.put("smokek2", lan4.getElementsByTagName("smokek2").item(0).getTextContent());
                    map.put("smokek1", lan4.getElementsByTagName("smokek1").item(0).getTextContent());
                    map.put("smokeavg", lan4.getElementsByTagName("smokeavg").item(0).getTextContent());
                    map.put("smokeklimit", lan4.getElementsByTagName("smokeklimit").item(0).getTextContent());
                } else if (vehicletype4 == "6" || vehicletype4 == "10") {
                    map.put("testtype", "滤纸式烟度法");
                    list4 = languages.getElementsByTagName("h_data_vftm");
                    lan4 = (Element) list2.item(1);
                    map.put("wd", lan4.getElementsByTagName("wd").item(0).getTextContent());
                    map.put("dqy", lan4.getElementsByTagName("dqy").item(0).getTextContent());
                    map.put("xdsd", lan4.getElementsByTagName("xdsd").item(0).getTextContent());
                    map.put("fdjdszs", lan4.getElementsByTagName("fdjdszs").item(0).getTextContent());
                    map.put("smokerb3", lan4.getElementsByTagName("smokerb3").item(0).getTextContent());
                    map.put("smokerb2", lan4.getElementsByTagName("smokerb2").item(0).getTextContent());
                    map.put("smokerb1", lan4.getElementsByTagName("smokerb1").item(0).getTextContent());
                    map.put("smokerbavg", lan4.getElementsByTagName("smokerbavg").item(0).getTextContent());
                    map.put("rblimit", lan4.getElementsByTagName("rblimit").item(0).getTextContent());
                }
                NodeList list5 = languages.getElementsByTagName("h_jcz_gwxx");
                Element lan5 = (Element) list2.item(2);
                map.put("sbmc", lan5.getElementsByTagName("sbmc").item(0).getTextContent());
                map.put("sbrzbm", lan5.getElementsByTagName("sbrzbm").item(0).getTextContent());
                map.put("sbxh", lan5.getElementsByTagName("sbxh").item(0).getTextContent());
                map.put("sbzzc", lan5.getElementsByTagName("sbzzc").item(0).getTextContent());
                NodeList list6 = languages.getElementsByTagName("h_vehicle");
                Element lan6 = (Element) list2.item(3);
                map.put("license", lan6.getElementsByTagName("license").item(0).getTextContent());
                map.put("licensecode", lan6.getElementsByTagName("licensecode").item(0).getTextContent());
                map.put("vehicletype", lan6.getElementsByTagName("vehicletype").item(0).getTextContent());
                map.put("owner", lan6.getElementsByTagName("owner").item(0).getTextContent());
                map.put("lxdz", lan6.getElementsByTagName("lxdz").item(0).getTextContent());
                map.put("lxdh", lan6.getElementsByTagName("lxdh").item(0).getTextContent());
                map.put("usetype", lan6.getElementsByTagName("usetype").item(0).getTextContent());
                map.put("vin", lan6.getElementsByTagName("vin").item(0).getTextContent());
                map.put("gvm", lan6.getElementsByTagName("gvm").item(0).getTextContent());
                map.put("rm", lan6.getElementsByTagName("rm").item(0).getTextContent());
                map.put("registerdate", lan6.getElementsByTagName("registerdate").item(0).getTextContent());
                map.put("drivemode", lan6.getElementsByTagName("drivemode").item(0).getTextContent());
                map.put("qdltqy", lan6.getElementsByTagName("qdltqy").item(0).getTextContent());
                map.put("cylinders", lan6.getElementsByTagName("cylinders").item(0).getTextContent());
                map.put("gear", lan6.getElementsByTagName("gear").item(0).getTextContent());
                map.put("dws", lan6.getElementsByTagName("dws").item(0).getTextContent());
                map.put("engine", lan6.getElementsByTagName("engine").item(0).getTextContent());
                map.put("fdjh", lan6.getElementsByTagName("fdjh").item(0).getTextContent());
                map.put("ed", lan6.getElementsByTagName("ed").item(0).getTextContent());
                map.put("fueltype", lan6.getElementsByTagName("fueltype").item(0).getTextContent());
                map.put("odometer", lan6.getElementsByTagName("odometer").item(0).getTextContent());
                map.put("chzhq", lan6.getElementsByTagName("chzhq").item(0).getTextContent());
                map.put("airin", lan6.getElementsByTagName("airin").item(0).getTextContent());
                map.put("fuelsupply", lan6.getElementsByTagName("fuelsupply").item(0).getTextContent());
                map.put("sjcys", lan6.getElementsByTagName("sjcys").item(0).getTextContent());
                map.put("ssxq", lan6.getElementsByTagName("ssxq").item(0).getTextContent());
                map.put("enginespeed", lan6.getElementsByTagName("enginespeed").item(0).getTextContent());
                map.put("mdate", lan6.getElementsByTagName("mdate").item(0).getTextContent());
                map.put("obd", lan6.getElementsByTagName("obd").item(0).getTextContent());
            }
            return (HashMap) map;
        } catch (IOException e) {
            System.out.println("map IOException");
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> getXmlBody(String xmldoc) {
        Map<String, String> map = new HashMap();
        try {
            for (org.jdom2.Element childEle : new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement().getChild("body").getChild("vehispara").getChildren()) {
                map.put(childEle.getName(), childEle.getText());
            }
            return map;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static Map<String, String> getXmlVeh(String xmldoc) {
        Map<String, String> map = new HashMap();
        try {
            for (org.jdom2.Element childEle : ((org.jdom2.Element) new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement().getChild("body").getChildren().get(0)).getChildren()) {
                map.put(childEle.getName(), childEle.getText());
            }
            return map;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static List<Map<String, String>> getXmlBodys(String xmldoc) {
        List<Map<String, String>> mapList = new ArrayList();
        try {
            for (org.jdom2.Element bodyChild : new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement().getChild("body").getChildren("vehispara")) {
                Map<String, String> map = new HashMap();
                for (org.jdom2.Element vehChild : bodyChild.getChildren()) {
                    map.put(vehChild.getName(), vehChild.getText());
                }
                mapList.add(map);
            }
            return mapList;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static List<Map<String, String>> getReportDetailXmlBody(String xmldoc) {
        List<Map<String, String>> mapList = new ArrayList();
        try {
            Map<String, String> map;
            org.jdom2.Element elt = new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement();
            List<org.jdom2.Element> bodyChildren = elt.getChild("body").getChildren("h_data_base");
            if (bodyChildren != null) {
                for (org.jdom2.Element bodyChild : bodyChildren) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild : bodyChild.getChildren()) {
                        map.put(vehChild.getName(), vehChild.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren1 = elt.getChild("body").getChildren("h_data_vtie");
            if (bodyChildren1 != null) {
                for (org.jdom2.Element bodyChild2 : bodyChildren1) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild2 : bodyChild2.getChildren()) {
                        map.put(vehChild2.getName(), vehChild2.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren8 = elt.getChild("body").getChildren("h_data_vasm");
            if (bodyChildren1 != null) {
                for (org.jdom2.Element bodyChild3 : bodyChildren8) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild22 : bodyChild3.getChildren()) {
                        map.put(vehChild22.getName(), vehChild22.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren2 = elt.getChild("body").getChildren("h_data_vlug");
            if (bodyChildren2 != null) {
                for (org.jdom2.Element bodyChild4 : bodyChildren2) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild222 : bodyChild4.getChildren()) {
                        map.put(vehChild222.getName(), vehChild222.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren3 = elt.getChild("body").getChildren("h_data_vmas");
            if (bodyChildren3 != null) {
                for (org.jdom2.Element bodyChild5 : bodyChildren3) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild2222 : bodyChild5.getChildren()) {
                        map.put(vehChild2222.getName(), vehChild2222.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren4 = elt.getChild("body").getChildren("h_data_vflm");
            if (bodyChildren4 != null) {
                for (org.jdom2.Element bodyChild6 : bodyChildren4) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild22222 : bodyChild6.getChildren()) {
                        map.put(vehChild22222.getName(), vehChild22222.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren5 = elt.getChild("body").getChildren("h_data_vftm");
            if (bodyChildren5 != null) {
                for (org.jdom2.Element bodyChild7 : bodyChildren5) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild222222 : bodyChild7.getChildren()) {
                        map.put(vehChild222222.getName(), vehChild222222.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren6 = elt.getChild("body").getChildren("h_jcz_gwxx");
            if (bodyChildren6 != null) {
                for (org.jdom2.Element bodyChild8 : bodyChildren6) {
                    map = new HashMap();
                    for (org.jdom2.Element vehChild2222222 : bodyChild8.getChildren()) {
                        map.put(vehChild2222222.getName(), vehChild2222222.getText());
                    }
                    mapList.add(map);
                }
            }
            List<org.jdom2.Element> bodyChildren7 = elt.getChild("body").getChildren("h_vehicle");
            if (bodyChildren7 == null) {
                return mapList;
            }
            for (org.jdom2.Element bodyChild9 : bodyChildren7) {
                map = new HashMap();
                for (org.jdom2.Element vehChild22222222 : bodyChild9.getChildren()) {
                    map.put(vehChild22222222.getName(), vehChild22222222.getText());
                }
                mapList.add(map);
            }
            return mapList;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static Map<String, String> getXmlBodypic(String xmldoc) {
        Map<String, String> map = new HashMap();
        try {
            for (org.jdom2.Element childEle : new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement().getChild("body").getChild("photo").getChildren()) {
                map.put(childEle.getName(), childEle.getText());
            }
            return map;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static List<Map<String, String>> getXmlBodysinfo(String xmldoc) {
        List<Map<String, String>> mapList = new ArrayList();
        try {
            for (org.jdom2.Element bodyChild : new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement().getChild("body").getChildren("vehicle")) {
                Map<String, String> map = new HashMap();
                for (org.jdom2.Element vehChild : bodyChild.getChildren()) {
                    map.put(vehChild.getName(), vehChild.getText());
                }
                mapList.add(map);
            }
            return mapList;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static List<Map<String, String>> getXmlBodysunderpan(String xmldoc) {
        List<Map<String, String>> mapList = new ArrayList();
        try {
            for (org.jdom2.Element bodyChild : new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement().getChild("body").getChildren("chassis")) {
                Map<String, String> map = new HashMap();
                for (org.jdom2.Element vehChild : bodyChild.getChildren()) {
                    map.put(vehChild.getName(), vehChild.getText());
                }
                mapList.add(map);
            }
            return mapList;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static List<Map<String, String>> getXmlBodyspic(String xmldoc) {
        List<Map<String, String>> mapList = new ArrayList();
        try {
            for (org.jdom2.Element bodyChild : new SAXBuilder().build(new InputSource(new StringReader(xmldoc))).getRootElement().getChild("body").getChildren("photodes")) {
                Map<String, String> map = new HashMap();
                for (org.jdom2.Element vehChild : bodyChild.getChildren()) {
                    map.put(vehChild.getName(), vehChild.getText());
                }
                mapList.add(map);
            }
            return mapList;
        } catch (JDOMException e) {
            return null;
        } catch (IOException e2) {
            System.out.println("map IOException");
            return null;
        } catch (Exception e3) {
            return null;
        }
    }
}
