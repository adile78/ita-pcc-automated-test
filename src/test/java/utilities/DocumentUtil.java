package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentUtil {

	public static Map<String, String> getDocumentCategoryMap(String categoryList, String documentPathList) {

		List<String> catList = new ArrayList<String>();
		catList = Arrays.asList(categoryList.split(PropsUtil.getSeparator()));

		List<String> docPathList = new ArrayList<String>();
		docPathList = Arrays.asList(documentPathList.split(PropsUtil.getSeparator()));

		if (catList.size() != docPathList.size())
			return null;

		Map<String, String> catDoctMap = new HashMap<String, String>();

		for (String cat : catList) {
			
			catDoctMap.put(cat, docPathList.get((catList.indexOf(cat))));
		}

		return catDoctMap;
	}

}
