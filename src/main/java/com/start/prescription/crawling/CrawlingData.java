package com.start.prescription.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CrawlingData {
    private WebDriver driver;

    private static final String keyword = "병원";
    private static final String url = "https://map.naver.com/v5/search";

    public void process() {

        // 크롬 드라이버 세팅 (드라이버 설치 경로 입력)
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chrome96\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // 브라우저 선택
        driver = new ChromeDriver(options);

        getDataList();

        // 탭 닫기
        driver.close();
        // 브라우저 닫기
        driver.quit();
    }

    // 데이터 가져오기
    private void getDataList() {

        // (1) 브라우저에서 url로 이동한다.
        driver.get(url);
        // 브라우저 로딩될 때까지 잠시 기다린다.
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        // (2) 검색결과 iframe으로 frame을 바꾼다.
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#searchIframe")));

        // 검색 결과 장소 목록을 elements에 담는다.
        List<WebElement> elements = driver.findElements(By.cssSelector(".C6RjW>.place_bluelink"));

        System.out.println("TestTest**********************************");
        System.out.println("elements.size() = " + elements.size());

        // (3) 첫번째 검색결과를 클릭한다.
        elements.get(0).click();

        // 요소가 로드될 때까지 기다린다.
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 현재 프레임에서 상위 프레임으로 이동한다.
        driver.switchTo().defaultContent();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        // (4) 상세정보가 나오는 프레임으로 이동한다.
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#entryIframe")));

        // (5) 상세정보 프레임에서 주소 정보가 들어있는 곳으로 이동한다.
        List<WebElement> placeSectionContents = driver.findElements(By.cssSelector(".place_section_content"));
        WebElement homeElement = placeSectionContents.get(1);

        // (6) "주소" 버튼 요소를 찾아 클릭한다.
        WebElement addressButton = homeElement.findElement(By.className("LDgIH"));
        addressButton.click();

        // (7) "도로명"과 "지번" 정보가 들어있는 div 요소를 찾아서, 해당 정보를 가져온다.
        WebElement addressDiv = driver.findElement(By.className("Y31Sf"));
        List<WebElement> addressInfos = addressDiv.findElements(By.className("nQ7Lh"));

        for (WebElement addressInfo : addressInfos) {
            WebElement addressType = addressInfo.findElement(By.tagName("span"));
            String address = addressInfo.getText().replace(addressType.getText(), "").trim();
            System.out.println(addressType.getText() + " : " + address);
        }

    }

}
