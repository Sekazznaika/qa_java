import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestLionParam {

    @Mock
    Feline feline;

    private final String sex;
    private final boolean isValidSex;

    public TestLionParam(String sex, boolean isValidSex) {
        this.sex = sex;
        this.isValidSex = isValidSex;
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Самец", true},
                {"Самка", true},
                {"Неизвестно", false}
        });
    }

    @Test
    public void testLionCreate() throws Exception {
        if (isValidSex) {
            Lion lion = new Lion(sex, feline);
            assertNotNull(lion);
        } else {
            Exception exception = assertThrows(Exception.class, () -> new Lion(sex, feline));
            assertEquals("Используйте допустимые значения пола животного - самей или самка", exception.getMessage());
        }
    }
}