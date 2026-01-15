package tests;

import framework.assertions.AssertException;
import framework.assertions.Assertions;
import framework.marker.Test;

public class MassivesContainTest {
    
    private final Integer[] big = {1, 2, 3, 4, 5, 6, 7};
    private final Integer[] small = {3, 4, 5};
    private final Integer[] other = {5, 4, 3};
    
    @Test
    public void equalMass() throws AssertException {
        Assertions.contains(this.big, this.big);
    }

    @Test
    public void lessMass() throws AssertException {
        Assertions.contains(this.big, this.small);
    }

    @Test
    public void moreMass() throws AssertException {
        Assertions.contains(this.small, this.big);
    }
    
    @Test
    public void noMass() throws AssertException {
        Assertions.contains(this.big, this.other);
    }

}
