package tests;

import framework.assertions.AssertException;
import framework.assertions.Assertions;
import framework.marker.Test;
import util.Contact;

public class ObjectEqualTest {
    
    private final Character myCharacter1 = 'A';
    private final Character myCharacter2 = 'A';
    private final Integer myInteger1 = 5;
    private final Integer myInteger2 = 5;
    private final String myString1 = "Cool";
    private final String myString2 = "Cool";
    private final Contact contact0 = new Contact(0, "Anna", "+79095486233");
    private final Contact contact1 = new Contact(0, "Anna", "+79095486233");
    private final Contact contact2 = new Contact(1, "Mike", "+79165487312");
    private final Contact contact3 = new Contact(2, "Steeve", "+79265871263", contact0);
    private final Contact contact4 = new Contact(2, "Steeve", "+79265871263", contact1);
    private final Contact contact5 = new Contact(2, "Steeve", "+79265871263", contact2);
    

    @Test
    public void testInteger() throws AssertException {
        Assertions.equalRecursively(this.myInteger1, this.myInteger2);
    }
    
    @Test
    public void testCharacer() throws AssertException {
        Assertions.equalRecursively(this.myCharacter1, this.myCharacter2);
    }
    
    @Test
    public void testString() throws AssertException {
        Assertions.equalRecursively(this.myString1, this.myString2);
    }

    @Test
    public void testObject() throws AssertException {
        Assertions.equalRecursively(this.contact0, this.contact1);
    }

    @Test
    public void testDifObject() throws AssertException {
        Assertions.equalRecursively(this.contact1, this.contact2);
    }
    
    @Test
    public void testDeepObject() throws AssertException {
        Assertions.equalRecursively(this.contact3, this.contact4);
    }
    
    @Test
    public void testDeepDifObject() throws AssertException {
        Assertions.equalRecursively(this.contact4, this.contact5);
    }
    
}
