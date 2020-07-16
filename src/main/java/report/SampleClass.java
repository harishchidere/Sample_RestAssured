package report;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.TS001;

public class SampleClass {

	@Test
	public void sampleReport() throws JSONException {

		ExtentHtmlReporter obj = new ExtentHtmlReporter("./reports/sampleReport.html");
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(obj);
		ExtentTest test = reports.createTest("CreateUserAPI", "To create the new user");
		obj.config().setTheme(Theme.STANDARD);
		obj.config().setReportName("API Automation Report");
		test.log(Status.PASS,
				"The API is invoked "
						+ "<details><summary><font color=\"green\"><b>Click Here To Open JSON</b></font></summary> \"<p><div class='json-tree' id='code-block-json-"
						+ 1 + "'></div><script>function jsonTreeCreate" + 2
						+ "() { document.getElementById('code-block-json-" + 1 + "').innerHTML = JSONTree.create("
						+ "harish" + "); }jsonTreeCreate" + 1 + "();</script></p></details>" + "successfully");
		test.log(Status.INFO, MarkupHelper.createLabel("The API is working fine", ExtentColor.BLUE));
		TS001 ts = new TS001();
		JSONObject jsonresponse = ts.testOne();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Name", "Testing");
		String url = "iyanu bey";
		test.log(Status.PASS, MarkupHelper.createCodeBlock(String.valueOf(map)));
		test.log(Status.INFO, "<font color=\"brown\"><p>Request Details</p></font>" + "<li>" + " Base URL is :-" + url
				+ " <li>" + "Query Parameters are :- " + map + "<li>");
		ExtentTest test1 = test.log(Status.PASS,
				MarkupHelper.createCodeBlock(jsonresponse.toString(), CodeLanguage.JSON));
	}

}
