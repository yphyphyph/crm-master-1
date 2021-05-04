package com.shangma.cn.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;


public class Demo {
    /**
     * 导包
     * <dependency>
     * <groupId>org.jsoup</groupId>
     * <artifactId>jsoup</artifactId>
     * <version>1.11.3</version>
     * </dependency>
     */
    public static void main(String[] args) throws IOException {
        //内容拼接
        StringBuffer stringBuffer = new StringBuffer();
        Document document = Jsoup.connect("https://www.amazon.cn/").get();
        Elements select = document.getElementsByClass("searchSelect");
        for (int i = 0; i < select.size(); i++) {
            Element element = select.get(i);
            Elements options = element.getElementsByTag("option");
            for (int j = 0; j < options.size(); j++) {
                Element option = options.get(j);
                stringBuffer.append(option.text());
                stringBuffer.append("\r\n");
            }
        }
        String result = stringBuffer.toString();
        FileWriter fileWriter  = new FileWriter("D:\\amazon.txt");
        fileWriter.write(result);
        fileWriter.close();
    }


}
