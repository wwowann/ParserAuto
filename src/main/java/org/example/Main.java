package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final String urlSite = "https://rolf-renault-spb.ru";

        List<CarBrand> listCarsRenault = new ArrayList<>();
        Document doc = Jsoup.connect(urlSite).get();
        Elements imgElements = doc.getElementsByAttributeValue("class", "img-responsive click");
        imgElements.forEach(imgElement -> {
                    String url = imgElement.attr("src");
                    String carBrand = imgElement.attr("data-push");
                    try {
                        convertToImage(urlSite, url, carBrand);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    listCarsRenault.add(new CarBrand(carBrand, url));
                }
        );
        listCarsRenault.forEach(System.out::println);
    }

    public static void convertToImage(String urlSite, String url, String carBrand) throws IOException {
        InputStream inputStream = new URL(urlSite + "/" + url).openStream();
        Files.copy(inputStream, Paths.get("imagesRenault/" + carBrand + ".png"), StandardCopyOption.REPLACE_EXISTING);


    }
}