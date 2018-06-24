package Accountsystem;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class JUnitLogicTest {

    @Test
    public void createAccount() {

        AccountDTO testAccount = new AccountDTO("","","","","");
        assertNotNull(testAccount);
    }

    @Test
    public void createProdukt() {

        ProduktDTO testProdukt = new ProduktDTO("","", 0.0, ProduktKategorie.BUEROMATERIAL, new AccountDTO("","","","",""));
        assertNotNull(testProdukt);
    }


}
