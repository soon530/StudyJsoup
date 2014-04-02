package tasker;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

// target website: http://cwisweb.sfaa.gov.tw/index.jsp
public class CwiswebTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void loadDocumentFromFile() {
		File input = new File("D:\\open_data\\test\\jsoup\\tmp\\cwisweb.htm");
		Document doc = null;
		try {
			// I can't understand why I need to set the baseURL.
			// It can't work!
			doc = Jsoup.parse(input, "UTF-8", "http://www.yahoo.com.tw/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// [width=100%],[border=0],[cellspacing=0],
		Elements items = doc.select("table").select("[cellpadding=8]")
				.select("tr");
		// Elements tables = items.select("[cellpadding=8]");

		// System.out.println("all" + items);

		int x = 0;
		int y = 0;
		for (Element element : items) {
			// System.out.println( "[" + count++ + "]" + element);
			Elements tds = element.select("td");

			for (Element td : tds) {
				System.out.println("[" + x + "]" + "[" + y + "]" + td.text());
				y++;
			}
			x++;
		}
	}

	/**
	 * 直接撈全部的話很慢
	 * @throws IOException
	 */
	@Test
	public void loadDocumentFromUrl() throws IOException {
		int page = 0;
		int offset = page * 10;
		String url = "http://cwisweb.sfaa.gov.tw/04nanny/03list.jsp?"
				+ "MEM_REGION=&MEM_CITY=&d1=&imageField.x=44&imageField.y=20&offset="
				+ offset;
		Document doc = Jsoup.connect(url).timeout(10000).get();
		String title = doc.title();
		System.out.println("title" + title);
		System.out.println("all" + doc.body());
	}

	/**
	 * 加上縣市的話快很多
	 * @throws IOException
	 */
	@Test
	public void loadDocumentFromUrlWithRegion() throws IOException {
		int page = 0;
		int offset = page * 10;
		String url = "http://cwisweb.sfaa.gov.tw/04nanny/03list.jsp?"
				+ "MEM_REGION=6400000000&MEM_CITY=&d1=&imageField.x=44&imageField.y=20&offset="
				+ offset;
		Document doc = Jsoup.connect(url).timeout(1000).get();
		String title = doc.title();
		System.out.println("title" + title);
		System.out.println("all" + doc.body());
	}
	
	@Test
	public void loadBabySitterDetailPageFromUrl() throws IOException {
		String sn = "E222138751";
		String url = "http://cwisweb.sfaa.gov.tw/04nanny/03view.jsp";
		Document doc = Jsoup.connect(url).data("sn", sn).timeout(3000).post();
		String title = doc.title();
		System.out.println("title" + title);
		System.out.println("all" + doc.body());
	}


}
