package org.mybatis.xml;

import org.junit.Test;
import org.mybatis.generator.config.xml.ParserEntityResolver;
import org.mybatis.generator.exception.XMLParserException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chencw
 * @date 2019/5/30 10:22
 * @since
 */
public class BaseXmlTest {


    @Test
    public void test01() throws XMLParserException {
        parseXml01();
    }

    private void parseXml01() throws XMLParserException {
        List<String> parseErrors = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(new ParserEntityResolver());
            Document document = null;
            try {
                File f = new File("D:\\workspace\\USP\\baseline\\USP-DAO\\MybatisGenerator\\etradeConfig2.xml");
                document = builder.parse(f);
            } catch (SAXParseException e) {
                throw new XMLParserException(parseErrors);
            } catch (SAXException e) {
                if (e.getException() == null) {
                    parseErrors.add(e.getMessage());
                } else {
                    parseErrors.add(e.getException().getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (parseErrors.size() > 0) {
                throw new XMLParserException(parseErrors);
            }

        } catch (ParserConfigurationException e) {
            parseErrors.add(e.getMessage());
            throw new XMLParserException(parseErrors);
        }
    }

    private void parseXml02() {
        File f = new File("D:\\workspace\\USP\\baseline\\USP-DAO\\MybatisGenerator\\etradeConfig2.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            Document document = db.parse(f);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
