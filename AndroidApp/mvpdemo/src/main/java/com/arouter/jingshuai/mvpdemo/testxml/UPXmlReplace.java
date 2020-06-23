package com.arouter.jingshuai.mvpdemo.testxml;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Created by jings on 2020/6/6.
 */

public class UPXmlReplace {

    private static final String LOG_TAG = "UPActCard";
    public static final String KEY_APPMSG = "appmsg";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_COLOR = "color";
    public static final String KEY_FONT = "font";
    public static final String KEY_BODY = "body";
    public static final String KEY_STATUS = "status";
    public static final String KEY_URL = "url";
    public static final String KEY_TYPE = "type";
    public static final String KEY_TEXT = "text";
    public static final String KEY_BTNS = "btns";
    public static final String KEY_BTN = "btn";
    public static final String KEY_NAME = "name";
    public static final String KEY_STYLES = "styles";
    public static final String KEY_BTNVERTICAL = "btnVertical";
    public static final String KEY_BGCOLOR = "bgColor";
    public static final String KEY_MSG = "msg";
    public static final String KEY_COVER = "cover";
    public static final String KEY_COVER_TYPE = "covertype";

    public static final String msgXml_all = "<?xml version=\"1.0\"?><msg"
        + "><appmsg appid=\"\" sdkver=\"0\"><mmreader><category type=\"20\" "
        + "count=\"1\"><topnew><cover "
        + "type=\"1\"><![CDATA[Cover]]></cover><digest><![CDATA[]]></digest></topnew><item"
        + "><itemshowtype>0</itemshowtype><url><![CDATA[URl]]></url><title><content><![CDATA"
        + "[??]]></content><color><![CDATA[??]]></color><font><![CDATA[???????]]></font></title"
        + "><body><content><![CDATA[??]]></content><color><![CDATA[??]]></color><font><![CDATA"
        + "[??]]></font></body><status><type><![CDATA[1]]></type><text><content><![CDATA"
        + "[??]]></content><color><![CDATA[C]]></color><font><![CDATA[M]]></font></text><btns"
        + "><btn><name><![CDATA[??????]]></name><type><![CDATA[view]]></type><url><![CDATA[www"
        + ".baidu.com]]></url><color><![CDATA[color]]></color><font><![CDATA[s]]></font></btn"
        + "></btns></status><sytles><btnVertical><![CDATA[1]]></btnVertical><bgColor><![CDATA"
        + "[#123456]]></bgColor></sytles></item></category></mmreader></appmsg></msg>";

    public static final String msgXml = "<?xml version=\"1.0\"?><item><itemshowtype>0</itemshowtype><status><type><![CDATA[1]]></type"
    + "><text><content><![CDATA[??]]></content><color><![CDATA[C]]></color><font><![CDATA[M"
    + "]]></font></text><btns><btn><name><![CDATA[??????]]></name><type><![CDATA[view]]></type"
    + "><url><![CDATA[www.baidu.com]]></url><color><![CDATA[color]]></color><font><![CDATA[s"
    + "]]></font></btn></btns></status><sytles><btnVertical><![CDATA[1]]></btnVertical><bgColor"
    + "><![CDATA[#123456]]></bgColor></sytles></item>";

    public static final String replacemsgXml_all = "<?xml version=\"1.0\"?><msg"
        + "><appmsg appid=\"\" sdkver=\"0\"><mmreader><category type=\"20\" "
        + "count=\"1\"><topnew><cover "
        + "type=\"1\"><![CDATA[Cover]]></cover><digest><![CDATA[]]></digest></topnew><item"
        + "><itemshowtype>0</itemshowtype><status><type><![CDATA[1]]></type><text><content"
        + "><![CDATA[??]]></content><color><![CDATA[C]]></color><font><![CDATA[M]]></font></text"
        + "><btns><btn><name><![CDATA[??????]]></name><type><![CDATA[view]]></type><url><![CDATA"
        + "[www.sohu.com]]></url><color><![CDATA[color]]></color><font><![CDATA[s]]></font></btn"
        + "></btns></status><sytles><btnVertical><![CDATA[1]]></btnVertical><bgColor><![CDATA"
        + "[#ffffff]]></bgColor></sytles></item></category></mmreader></appmsg></msg>";

    public static final String replacemsgXml = "<?xml version=\"1.0\"?><item><itemshowtype>0"
        + "</itemshowtype><status><type><![CDATA[1]]></type><text><content><![CDATA"
        + "[??]]></content><color><![CDATA[C]]></color><font><![CDATA[M]]></font></text><btns"
        + "><btn><name><![CDATA[??????]]></name><type><![CDATA[view]]></type><url><![CDATA[www"
        + ".sohu.com]]></url><color><![CDATA[color]]></color><font><![CDATA[s]]></font></btn"
        + "></btns></status><sytles><btnVertical><![CDATA[1]]></btnVertical><bgColor><![CDATA"
        + "[#ffffff]]></bgColor></sytles></item>";

    public static void runTest(){
        //initFromString(msgXml,replacemsgXml);
        regReplace(msgXml,replacemsgXml);
    }


    public static String regReplace(String sourceXml, String replaceXml){
       // String regex = "<status>([^</status>]*)";
        String resultStr = "";
        String regex = "<status>(.*?)</status>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(replaceXml);
        String statusreplaceString = "";
        if(matcher.find()) { // while ?????
            statusreplaceString = matcher.group(1);
        }
        if(!TextUtils.isEmpty(statusreplaceString)){
            Log.v("xml","doc statusreplaceString = "+statusreplaceString);
            resultStr = sourceXml.replaceAll(regex,statusreplaceString);
        }

        Log.v("xml","doc resultStr = "+resultStr);
        return resultStr;

    }


    public static void  initFromString(String msg, String replaceContent) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            if(TextUtils.isEmpty(msg)){
                return;
            }
            DocumentBuilder builderrp = factory.newDocumentBuilder();

            StringReader srrp = new StringReader(replaceContent);
            InputSource isrp = new InputSource(srrp);
            Document docrp = builderrp.parse(isrp);

            Node statuslistrp = null;
            Node styleslistrp = null;
            Element rootElementrp = docrp.getDocumentElement();

            NodeList nodelistrp = rootElementrp.getChildNodes();
            for (int i = 0; i < nodelistrp.getLength(); i++) {
                Node type = nodelistrp.item(i);
                String nodeName = type.getNodeName();
                if (type.getFirstChild() == null) {
                    continue;
                }
                if (KEY_URL.equals(nodeName)) {
                    // mUrl = type.getFirstChild().getNodeValue();
                } else if (KEY_COVER_TYPE.equals(nodeName)) {
                    // mCoverType = type.getFirstChild().getNodeValue();
                } else if (KEY_COVER.equals(nodeName)) {
                    //mCover = type.getFirstChild().getNodeValue();
                } else if (KEY_TITLE.equals(nodeName)) {
                    NodeList titlelist = type.getChildNodes();
                } else if (KEY_BODY.equals(nodeName)) {
                    NodeList bodylist = type.getChildNodes();
                } else if (KEY_STATUS.equals(nodeName)) {
                    statuslistrp = type;
                } else if (KEY_STYLES.equals(nodeName)) {
                    styleslistrp = type;
                }
            }

            StringReader sr = new StringReader(msg);
            InputSource is = new InputSource(sr);
            Document doc = builderrp.parse(is);

            Element rootElement = doc.getDocumentElement();

            NodeList nodelist = rootElement.getChildNodes();
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node type = nodelist.item(i);
                String nodeName = type.getNodeName();
                if (type.getFirstChild() == null) {
                    continue;
                }
                if (KEY_URL.equals(nodeName)) {
                   // mUrl = type.getFirstChild().getNodeValue();
                } else if (KEY_COVER_TYPE.equals(nodeName)) {
                   // mCoverType = type.getFirstChild().getNodeValue();
                } else if (KEY_COVER.equals(nodeName)) {
                    //mCover = type.getFirstChild().getNodeValue();
                } else if (KEY_TITLE.equals(nodeName)) {
                    NodeList titlelist = type.getChildNodes();

                } else if (KEY_BODY.equals(nodeName)) {
                    NodeList bodylist = type.getChildNodes();
                } else if (KEY_STATUS.equals(nodeName)) {

                    Node parentStstusNode = type.getParentNode();
                    parentStstusNode.replaceChild(statuslistrp,type);

                } else if (KEY_STYLES.equals(nodeName)) {
                    Node parentStyleNode = type.getParentNode();
                    parentStyleNode.replaceChild(styleslistrp,type);
                }
            }

            String XMLString = toStringFromDoc(doc);
            Log.v("xml","doc XMLString = "+XMLString);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean replaceNodeList( Node node, NodeList removeList,  NodeList addList){
        for(int i=0;i<removeList.getLength();i++){
            Node childNode = removeList.item(i);
            node.removeChild(childNode);
        }
        return true;
    }


    public static String toStringFromDoc(Document document) {
        String result = null;

        if (document != null) {
            StringWriter strWtr = new StringWriter();
            StreamResult strResult = new StreamResult(strWtr);
            TransformerFactory tfac = TransformerFactory.newInstance();
            try {
                javax.xml.transform.Transformer t = tfac.newTransformer();
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
                // text
                t.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
                t.transform(new DOMSource(document.getDocumentElement()),
                    strResult);
            } catch (Exception e) {
                System.err.println("XML.toString(Document): " + e);
            }
            result = strResult.getWriter().toString();
            try {
                strWtr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
