package com.ezhomesixgod.xmlutil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renyang
 * @description
 * @createTime 2018-10-12 10:58
 */
public class XmlUtil {
    /**
     * 解析字符串（XML）
     *
     * @param
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(String msg)
            throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));
        // 读取输入流
        SAXReader reader = new SAXReader();
        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList){
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {

        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                public String encodeNode(String name) {
                    return name;
                }


                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * xml转对象
     * @param xml
     * @param objClass
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object fromXML(String xml, Class objClass) {
        Serializer serializer = new Persister();
        try {
            return serializer.read(objClass, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转xml
     * @param obj
     * @return
     */
    public static String toXMl(Object obj) {
        //使用注解设置别名必须在使用之前加上注解类才有作用
        xstream.processAnnotations(obj.getClass());
        return xstream.toXML(obj);
    }

    private XStream inclueUnderlineXstream = new XStream(new DomDriver(null,new XmlFriendlyNameCoder("_-", "_")));

    public XStream getXstreamInclueUnderline() {
        return inclueUnderlineXstream;
    }
    public XStream xstream() {
        return xstream;
    }
}
