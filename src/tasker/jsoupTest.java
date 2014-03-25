package tasker;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class jsoupTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void ParseDocument() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p><a href='www.yahoo.com.tw'>yahoo</a></body></html>";
		Document doc = Jsoup.parse(html);

		Elements links = doc.getElementsByTag("a");
		for (Element link : links) {
			String linkHref = link.attr("href");
			System.out.println("href: " + linkHref);
			String linkText = link.text();
			System.out.println("linkText: " + linkText);
		}

	}

	@Test
	public void loadDocumentFromUrl() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println("title" + title);
		System.out.println("all" + doc.body());
	}

	@Test
	public void loadDocumentFromFile() {
		File input = new File("D:\\open_data\\test\\jsoup\\tmp\\input.html");
		Document doc = null;
		try {
			// I can't understand why I need to set the baseURL.
			// It can't work!
			doc = Jsoup.parse(input, "UTF-8", "http://www.yahoo.com.tw/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(doc.title());
	}

	@Test
	public void tellMeMyWorkingDirectory() {
		System.out.println(new java.io.File("").getAbsolutePath());
	}
	
	
	

}
