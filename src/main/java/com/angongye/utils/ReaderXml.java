package com.angongye.utils;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReaderXml {
 //静态块，加载文件
 private static Document document;
 static{
  InputStream resourceAsStream = ReaderXml.class.getClassLoader().getResourceAsStream("/URL_List.xml");
  SAXReader reader = new SAXReader();
     try {
         document = reader.read(resourceAsStream);
     } catch (DocumentException e) {
         throw new RuntimeException(e);
     }
 }

 public static List<String> getList(){
  List<String> list = new ArrayList<>();
  List<Node> nodes = document.selectNodes("/list/item");
  for (Node node : nodes) {
   list.add(node.getText());
  }
  return list;
 }
}
