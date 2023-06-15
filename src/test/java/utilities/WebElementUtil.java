package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author barbetti_be
 *
 */
public class WebElementUtil {

	/**
	 * Check if a web element has a certain value in his class attribute
	 * 
	 * @param element
	 *            used to check its class attribute
	 * @param lookforClassValue
	 *            used as expected html class attribute value
	 * @return Boolean true when element has html class attribute which contains
	 *         the value
	 */
	public static boolean hasClass(WebElement element, String lookforClassValue) {

		String classes = element.getAttribute("class");
		if (StringUtils.isBlank(classes))
			return false;
		for (String c : classes.split(" ")) {
			if (c.equals(lookforClassValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find the element in a list that contains a certain value in a certain
	 * attribute
	 * 
	 * @param listOfElements
	 * @param used
	 *            to define css or html attribute
	 * @param used
	 *            as expected attribute value
	 * @return the first element of @listOfElements that has css or html
	 *         attribute which contains the value
	 */
	public static WebElement contains(List<WebElement> listOfElements, String attribute, String value) {

		for (WebElement el : listOfElements) {
			if (value.equals(el.getAttribute(attribute)))
				return el;
		}

		return null;
	}

	public static void clickElement(WebDriver driver, WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}

	/**
	 * Driver waits 2 minutes(120) for the element to appear.
	 * 
	 * @param driver
	 * @param elementToWaitFor identifier of WebElement to wait for  
	 * 
	 */
	public static void waitForPresenceOf(WebDriver driver, By elementToWaitFor) {
		WebDriverWait wait = new WebDriverWait(driver, 400);
		wait.until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor));
	}

	/**
	 * @param root
	 * @return a map of label and value of a section fields table
	 */
	public static Map<String, String> getLabelValueMap(WebElement root) {

		Map<String, String> labelValueMap = new HashMap<String, String>();

		List<WebElement> fields = root.findElements(By.tagName("li"));
		for (WebElement field : fields) {
			List<WebElement> rowList = field.findElements(By.className("row"));
			for (WebElement row : rowList) {
				List<WebElement> valueList = row.findElements(By.tagName("div"));
				if (valueList.size() > 1) {
					labelValueMap.put(valueList.get(0).getText(), valueList.get(1).getText());
				}
			}
		}
		return labelValueMap;
	}
	
	/**
	 * @param root
	 * @return a list of labels of section fields table
	 */
	public static List<String> getLabelList(WebElement root) {

		List<String> labelList = new ArrayList<String>();

		List<WebElement> fields = root.findElements(By.tagName("li"));
		for (WebElement field : fields) {
			List<WebElement> rowList = field.findElements(By.className("row"));
			for (WebElement row : rowList) {
				List<WebElement> valueList = row.findElements(By.tagName("div"));
				if (valueList.size() > 1) {
					labelList.add(valueList.get(0).getText());
				}
			}
		}
		return labelList;
	}
	
	/**
	 * @param root
	 * @return a list of values of section fields table
	 */
	public static List<String> getValueList(WebElement root) {

		List<String> labelList = new ArrayList<String>();

		List<WebElement> fields = root.findElements(By.tagName("li"));
		for (WebElement field : fields) {
			List<WebElement> rowList = field.findElements(By.className("row"));
			for (WebElement row : rowList) {
				List<WebElement> valueList = row.findElements(By.tagName("div"));
				if (valueList.size() > 1) {
					labelList.add(valueList.get(1).getText());
				}
			}
		}
		return labelList;
	}

	/**
	 * @param root
	 * @return list of all rows text under root element
	 */
	public static List<String> getAccordionElementList(WebElement root) {

		List<String> accordionElementList = new ArrayList<String>();
		List<WebElement> accordionList = root.findElements(By.className(PropsUtil.getPropertyValue("accordion.inner")));
		for (WebElement accordion : accordionList) {
			List<WebElement> rowList = accordion.findElements(By.className("row"));
			for (WebElement row : rowList)
				accordionElementList.add(row.getText());
		}
		return accordionElementList;
	}

	/**
	 * @param webElementList list of WebElement
	 * @return a list of all webElement text
	 */
	public static List<String> getTextList(List<WebElement> webElementList){
		
		List<String> textList = new ArrayList<String>();
		for(WebElement element: webElementList){
			String text = element.getText();
			textList.add(text);
		}		
		return textList;
	}
}
