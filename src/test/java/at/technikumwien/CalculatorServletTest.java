package at.technikumwien;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
public class CalculatorServletTest {

    private ServletRunner runner;
    private ServletUnitClient client;

    @Before
    public void setUp(){
        runner = new ServletRunner();
        runner.registerServlet("calculator", CalculatorServlet.class.getName());
        client = runner.newClient();
    }

    @Test
    public void testSum() throws Exception{
        WebRequest request = new GetMethodWebRequest("http://test.meterware.com/calculator");
        request.setParameter("operand1", "1");
        request.setParameter("operand2", "1");

        WebResponse response = client.getResponse(request);
        assertEquals("Summe: 2",response.getText());
    }

    @Test (expected = NumberFormatException.class)
    public void testOnlyFirstParameter() throws IOException, SAXException {
        WebRequest request = new GetMethodWebRequest("http://test.meterware.com/calculator");
        request.setParameter("operand1", "a");
        WebResponse response = client.getResponse(request);
    }

    @Test (expected = NumberFormatException.class)
    public void testOnlySecondParameter() throws IOException, SAXException {
        WebRequest request = new GetMethodWebRequest("http://test.meterware.com/calculator");
        request.setParameter("operand2", "b");
        WebResponse response = client.getResponse(request);
    }

    @After
    public void tearDown(){
        runner.shutDown();
    }


}
