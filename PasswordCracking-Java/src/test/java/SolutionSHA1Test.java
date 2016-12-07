import com.company.Helper;
import com.company.SolutionSHA1;
import org.junit.Assert;
import org.junit.Test;

/**
 * Developed by Mantas on 06/12/2016.
 */

public class SolutionSHA1Test {

    protected static final String OUTPUT_FILE = "salt-cracked.txt";

    @Test
    public void testCracking() {
        Assert.assertEquals(25, SolutionSHA1.crack().size());
    }

    @Test
    public void testPrinting() {
        Helper.printFreqs(SolutionSHA1.crack(), OUTPUT_FILE);
    }

}
