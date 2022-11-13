package com.example.demo;

import com.example.demo.dto.Forecast;
import com.example.demo.dto.ForecastData;
import com.example.demo.dto.Place;
import com.example.demo.dto.Wind;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ResponseParser {



    public static List<Forecast> parseForecasts(String xml) {
        List<Forecast> forecasts = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new StringReader(xml)));

            NodeList foreCastList = document.getElementsByTagName("forecast");
            for (int i = 0; i < foreCastList.getLength(); i++) {
                Node node = foreCastList.item(i);
                String date = node.getAttributes().item(0).getTextContent();
                NodeList dayData = getNodelistByTagname(node, "day");
                NodeList nightData = getNodelistByTagname(node, "night");
                ForecastData dayForecast = parseForecastData(dayData);
                ForecastData nightForecast = parseForecastData(nightData);
                Forecast forecast = new Forecast(date, nightForecast, dayForecast);
                forecasts.add(forecast);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return forecasts;
    }

    private static ForecastData parseForecastData(NodeList nodes) {
        NodeList placeNodes = getNodeListCollectionByTagname(nodes, "place");
        NodeList windNodes = getNodeListCollectionByTagname(nodes, "wind");
        List<Place> places = parsePlaces(placeNodes);
        List<Wind> winds = parseWinds(windNodes);
        String phenomenon = getNodeValueByTagname(nodes, "phenomenon");
        String tempmin = getNodeValueByTagname(nodes, "tempmin");
        String tempmax = getNodeValueByTagname(nodes, "tempmax");
        String text = getNodeValueByTagname(nodes, "text");
        String sea = getNodeValueByTagname(nodes, "sea");
        String peipsi = getNodeValueByTagname(nodes, "peipsi");
        return new ForecastData(phenomenon, tempmin, tempmax, text, places, winds, sea, peipsi);
    }

    private static NodeList getNodelistByTagname(Node node, String tagName) {
        if (!isElementNode(node)) {
            return null;
        }
        return ((Element) node).getElementsByTagName(tagName);
    }

    private static NodeList getNodeListCollectionByTagname(NodeList nodeList, String tagName) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (isElementNode(node)) {
                NodeList elementsByTagName = ((Element) node).getElementsByTagName(tagName);
                if (elementsByTagName.getLength() > 0) {
                    return elementsByTagName;
                }
            }
        }
        return null;
    }

    private static String getNodeValueByTagname(NodeList nodelist, String tagName) {
        for (int i = 0; i < nodelist.getLength(); i++) {
            Node node = nodelist.item(i);
            if (isElementNode(node)) {
                Node item = ((Element) node).getElementsByTagName(tagName).item(0);
                if (item != null) {
                    return item.getTextContent();
                }
            }
        }
        return null;
    }

    private static String getNodeValueByTagname(Node node, String tagName) {
        if (isElementNode(node)) {
            Node item = ((Element) node).getElementsByTagName(tagName).item(0);
            if (item != null) {
                return item.getTextContent();
            }
        }
        return null;
    }

    private static Boolean isElementNode(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }

    private static List<Place> parsePlaces(NodeList placeNodes) {
        if (placeNodes == null) {
            return null;
        }
        List<Place> placeList = new ArrayList<>();
        try {
            for (int i = 0; i < placeNodes.getLength(); i++) {
                String placeName = getNodeValueByTagname(placeNodes.item(i), "name");
                String phenomenon = getNodeValueByTagname(placeNodes.item(i), "phenomenon");
                String tempMax = getNodeValueByTagname(placeNodes.item(i), "tempmax");
                String tempMin = getNodeValueByTagname(placeNodes.item(i), "tempmin");
                if (Stream.of(placeName, phenomenon).allMatch(Objects::nonNull)) {
                    placeList.add(new Place(placeName, phenomenon, tempMax, tempMin));
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (placeList.isEmpty()) {
            return null;
        }
        return placeList;
    }

    private static List<Wind> parseWinds(NodeList placeNodes) {
        if (placeNodes == null) {
            return null;
        }
        List<Wind> windList = new ArrayList<>();
        for (int i = 0; i < placeNodes.getLength(); i++) {
            String placeName = getNodeValueByTagname(placeNodes.item(i), "name");
            String phenomenon = getNodeValueByTagname(placeNodes.item(i), "direction");
            String speedmin = getNodeValueByTagname(placeNodes.item(i), "speedmin");
            String speedmax = getNodeValueByTagname(placeNodes.item(i), "speedmax");
            String gust = getNodeValueByTagname(placeNodes.item(i), "gust");
            if (Stream.of(placeName, phenomenon, speedmin, speedmax, gust).allMatch(Objects::nonNull)) {
                windList.add(new Wind(placeName, phenomenon, speedmin, speedmax, gust));
            }
        }
        if (windList.isEmpty()) {
            return null;
        }
        return windList;
    }
}
