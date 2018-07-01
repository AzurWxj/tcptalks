import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import util.MD5Encoder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;



public class MD5EncoderTest {
    @Test
    public void testEncode() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String copy=MD5Encoder.encode("他山之石，可以攻玉");
        Logger.getLogger(MD5EncoderTest.class).info(copy.length());
        String encryption=MD5Encoder.encode("0123456789");
        String validation="eB5eJF1ptWaXm4bijSPyxw==";
        Assert.assertEquals(encryption,validation);
    }
}
