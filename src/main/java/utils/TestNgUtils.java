package utils;

import org.testng.ITestContext;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestNgUtils {

    public Map<String, String> getTestContextInJson(ITestContext testContext){
        int passedTest = testContext.getPassedTests().getAllResults().size();
        int failedTest = testContext.getFailedTests().getAllResults().size();
        int skippedTest = testContext.getSkippedTests().getAllResults().size();
        float totalTest = passedTest + failedTest + skippedTest;
        LogHelper.getInstance().info("Total Passed test: " + passedTest);
        LogHelper.getInstance().info("Total Failed test: " + failedTest);
        LogHelper.getInstance().info("Total Skipped test: " + skippedTest);
        LogHelper.getInstance().info("Total test: " + totalTest);
        DecimalFormat df = new DecimalFormat("#.##");
        String passingRate = totalTest == 0 ? "100" : String.format("%.2f",(passedTest / totalTest) * 100);
        Map<String, String> mapTestContext = new HashMap<>();
        mapTestContext.put("passed" , passedTest + "");
        mapTestContext.put("failed" , failedTest + "");
        mapTestContext.put("skipped" , skippedTest + "");
        mapTestContext.put("passPercentage" ,  passingRate);
        LogHelper.getInstance().info("Passing rate: " + passingRate);
        return mapTestContext;
    }
    public Map<String, String> getTestContextInJson(List<ITestContext> testContexts){
        Map<String, String> mapTestContext = new HashMap<>();
        int passedTest = 0;
        int failedTest = 0;
        int skippedTest = 0;
        float totalTest;

        for (ITestContext testContext: testContexts) {
            passedTest += testContext.getPassedTests().getAllResults().size();
            failedTest += testContext.getFailedTests().getAllResults().size();
            skippedTest += testContext.getSkippedTests().getAllResults().size();
        }

        totalTest = passedTest + failedTest + skippedTest;
        LogHelper.getInstance().info("Total Passed test: " + passedTest);
        LogHelper.getInstance().info("Total Failed test: " + failedTest);
        LogHelper.getInstance().info("Total Skipped test: " + skippedTest);
        LogHelper.getInstance().info("Total test: " + totalTest);
        DecimalFormat df = new DecimalFormat("#.##");
        String passingRate = totalTest == 0 ? "100" : String.format("%.2f",(passedTest / totalTest) * 100);
        mapTestContext.put("passed" , passedTest + "");
        mapTestContext.put("failed" , failedTest + "");
        mapTestContext.put("skipped" , skippedTest + "");
        mapTestContext.put("passPercentage" ,  passingRate);
        LogHelper.getInstance().info("Passing rate: " + passingRate);
        return mapTestContext;
    }
}
