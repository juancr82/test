package jmeter;

import java.io.Serializable;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.assertions.DurationAssertion;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
 
public class ExampleJavaTestClass extends AbstractJavaSamplerClient implements Serializable {
    private static final long serialVersionUID = 1L;
 
    // set up default arguments for the JMeter GUI
    @Override
    public Arguments getDefaultParameters() {
        Arguments defaultParameters = new Arguments();
        defaultParameters.addArgument("URL", "http://www.google.com/");
        defaultParameters.addArgument("SEARCHFOR", "newspaint");
        return defaultParameters;
    }
 
    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        // pull parameters
        String urlString = context.getParameter( "URL" );
        String searchFor = context.getParameter( "SEARCHFOR" );
     
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
         
        try {
        	for (int i = 0; i < 10000000; i++){
        		
        	}
            java.net.URL url = new java.net.URL(urlString + "?q=" + searchFor);
            java.net.HttpURLConnection connection = (
                java.net.HttpURLConnection
            )url.openConnection(); // have to cast connection
            connection.setRequestMethod("GET");
            connection.connect();
 
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful( true );
            result.setResponseMessage( "Successfully performed action" );
            result.setResponseCodeOK(); // 200 code
            //throw new Exception();
        } catch (Exception e) {
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful( false );
            result.setResponseMessage( "Exception: " + e );
 
            // get stack trace as a String to return as document data
            java.io.StringWriter stringWriter = new java.io.StringWriter();
            e.printStackTrace( new java.io.PrintWriter( stringWriter ) );
            result.setResponseData( stringWriter.toString() );
            result.setDataType( org.apache.jmeter.samplers.SampleResult.TEXT );
            result.setResponseCode( "500" );
        }
 
        return result;
    }
}
