package factories;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import enums.PolicyCategoryType;
import pageobjects.DetailPolicy;
import pageobjects.ListPolicy;

public class PccPolicyFactory {

	/**
	 * @param driver
	 * @param policy
	 * @param categoryType it must be a PolicyCategoryType
	 * @return
	 */
	public static ListPolicy getCategorizedListPolicy(WebDriver driver, ListPolicy policy, PolicyCategoryType categoryType) {

		ListPolicy categorizedPolicy = null;

		String category = categoryType.getCategory();
		Class[] cArg = new Class[2];
		cArg[0] = WebDriver.class;
		cArg[1] = ListPolicy.class;

		// compose class name
		String className = "pccpageobjects.Pcc" + StringUtils.capitalize(category).replaceAll("\\s+", "")
				+ "ListPolicy";

		Class c;
		try {
			c = Class.forName(className);
			categorizedPolicy = (ListPolicy) c.getDeclaredConstructor(cArg).newInstance(driver, policy);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return categorizedPolicy;
	}

	/**
	 * @param driver
	 * @param policy
	 * @param categoryType it must be a PolicyCategoryType
	 * @return
	 */
	public static DetailPolicy getCategorizedDetailPolicy(WebDriver driver, DetailPolicy policy, PolicyCategoryType categoryType) {

		DetailPolicy categorizedPolicy = null;

		//String category = PolicyCategoryType.valueOf(categoryType).getCategory();
		String category = categoryType.getCategory();
		
		Class[] cArg = new Class[2];
		cArg[0] = WebDriver.class;
		cArg[1] = DetailPolicy.class;

		// compose class name
		String className = "pccpageobjects.Pcc" + StringUtils.capitalize(category).replaceAll("\\s+", "")
				+ "DetailPolicy";

		Class c;
		try {
			c = Class.forName(className);
			categorizedPolicy = (DetailPolicy) c.getDeclaredConstructor(cArg).newInstance(driver, policy);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return categorizedPolicy;
	}

}
