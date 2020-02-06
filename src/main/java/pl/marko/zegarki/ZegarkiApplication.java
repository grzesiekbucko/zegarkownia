package pl.marko.zegarki;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.marko.zegarki.entity.ZegarekNetProduct;


import java.io.IOException;
import java.math.BigDecimal;

@SpringBootApplication
public class ZegarkiApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ZegarkiApplication.class, args);


	}
}