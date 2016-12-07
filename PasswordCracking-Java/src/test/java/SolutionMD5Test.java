import com.company.Helper;
import com.company.SolutionMD5;
import org.junit.Test;

/**
 * Developed by Mantas on 05/12/2016.
 */
public class SolutionMD5Test {

    protected static final String OUTPUT_FILE = "md5-cracked.txt";

    @Test
    public void testFrequencies() {
        SolutionMD5 md5 = new SolutionMD5();
    }

    @Test
    public void testCracking() {
        SolutionMD5 md5 = new SolutionMD5();
        md5.crack();
    }

    @Test
    public void testPrinting() {
        SolutionMD5 md5 = new SolutionMD5();
        Helper.printFreqs(md5.crack(), OUTPUT_FILE);
    }
}
