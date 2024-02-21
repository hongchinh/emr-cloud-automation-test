package utils;
import org.testng.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class BaseTestNGListener implements ITestListener {


    public BaseTestNGListener() {

    }

    @Override
    public void onTestStart(ITestResult result) {

        LogHelper.getInstance().info("Test started: " + result.getName());
//        LoggerUtils.getInstance().info("Test class started: " + result.getTestClass().getName());
//        LoggerUtils.getInstance().info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogHelper.getInstance().info("Test stop with SUCCESS: " + result.getName());
//        LoggerUtils.getInstance().info("Test stop with SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogHelper.getInstance().info("Test stop with FAILED: " + result.getName());
//        LoggerUtils.getInstance().info("Test stop with FAILED: " + result.getName());
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogHelper.getInstance().info("Test stop with SKIPPED: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext testContext) {
        Map<String, String> mapContext = new TestNgUtils().getTestContextInJson(testContext);
        String currentDir = System.getProperty("user.dir");
//        try {
//            mapContext.put("reportPortal", TestNGService.ITEM_TREE.getLaunchId().blockingGet().trim());
//        }catch (NullPointerException npe){
//            mapContext.put("reportPortal", "");
//        }
        String finalResult = JsonUtils.parseMapToJsonString(mapContext);
        new FileUtlis().writeFile("testResult.json", finalResult , currentDir);
        LogHelper.getInstance().info("----------------Test Finished----------------");
    }
//    @Override
//    public void onFinish(ISuite suite){
//        List<ISuiteResult> suiteResults = new ArrayList<>(suite.getResults().values());
//        List<ITestContext> testContexts = suiteResults.stream().map(ISuiteResult::getTestContext).collect(toList());
//
//        Map<String, String> mapContext = new TestNgUtils().getTestContextInJson(testContexts);
//        String currentDir = System.getProperty("user.dir");
//
//        String finalResult = JsonUtils.parseMapToJsonString(mapContext);
//        new FileUtlis().writeFile("testResult.json", finalResult , currentDir);
//        LoggerUtils.getInstance().info("----------------Test Suite Finished----------------");
//    }

}
